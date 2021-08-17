package com.urise.webapp.storage.Strategy;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements Serializer {
    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());

            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());

            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            Map<SectionType, Section> sections = resume.getSections();
            dos.writeInt(sections.size());

            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                SectionType sectionType = entry.getKey();
                Section section = entry.getValue();
                dos.writeUTF(sectionType.name());

                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((TextSection) section).getContent());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> list = new ArrayList<>(((ListSection) section).getItems());
                        dos.writeInt(list.size());
                        for (String s : list) {
                            dos.writeUTF(s);
                        }
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> organizationList = new ArrayList<>(((OrganizationSection) section).getOrganizations());
                        dos.writeInt(organizationList.size());
                        for (Organization o : organizationList) {
                            Link link = o.getHomePage();
                            writeLink(dos, link);
                            writePositions(dos, o);
                        }
                        break;
                }
            }
        }
    }

    private void writePositions(DataOutputStream dos, Organization organization) throws IOException {
        List<Organization.Position> positionList = organization.getPositions();
        dos.writeInt(positionList.size());

        for (Organization.Position position : positionList) {
            writeLocalDate(dos, position.getStartDate());
            writeLocalDate(dos, position.getEndDate());
            dos.writeUTF(position.getTitle());
            String description = position.getDescription();
            dos.writeUTF(description == null ? ("") : description);
        }
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate ld) throws IOException {
        dos.writeInt(ld.getYear());
        dos.writeInt(ld.getMonth().getValue());
    }

    private void writeLink(DataOutputStream dos, Link link) throws IOException {
        dos.writeUTF(link.getName());
        String url = link.getUrl();
        dos.writeUTF(url == null ? ("") : url);
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);

            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            int sectionsSize = dis.readInt();
            for (int i = 0; i < sectionsSize; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.addSections(sectionType, readSection(dis, sectionType));
            }
            return resume;
        }
    }

    private Section readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        switch (sectionType) {
            case PERSONAL:
            case OBJECTIVE:
                return new TextSection(dis.readUTF());
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                int sizeListSections = dis.readInt();
                return new ListSection(getListSections(dis, sizeListSections));
            case EXPERIENCE:
            case EDUCATION:
                int size = dis.readInt();
                List<Organization> organizationList = new ArrayList<>(size);
                for (int i = 0; i < size; i++) {
                    organizationList.add(new Organization(readLink(dis), readPositions(dis)));
                }
                return new OrganizationSection(organizationList);

            default:
                throw new IllegalStateException();
        }
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }

    private List<String> getListSections(DataInputStream dis, int sizeList) throws IOException {
        List<String> listSections = new ArrayList<>(sizeList);
        for (int i = 0; i < sizeList; i++) {
            listSections.add(dis.readUTF());
        }
        return listSections;
    }

    private Link readLink(DataInputStream dis) throws IOException {
        String organizationName = dis.readUTF();
        return new Link(organizationName, readDescriptionAndUrl(dis));

    }

    private List<Organization.Position> readPositions(DataInputStream dis) throws IOException {
        List<Organization.Position> list = new ArrayList<>();
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            list.add(i, new Organization.Position(readLocalDate(dis), readLocalDate(dis), dis.readUTF(), readDescriptionAndUrl(dis)));

        }
        return list;
    }

    private String readDescriptionAndUrl(DataInputStream dis) throws IOException {
        String value = dis.readUTF();
        return value.equals("") ? null : value;
    }
}