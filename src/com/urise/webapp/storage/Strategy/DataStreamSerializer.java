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

                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(sectionType.name());
                        dos.writeUTF(((TextSection) section).getContent());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        dos.writeUTF(sectionType.name());
                        List<String> list = new ArrayList<>(((ListSection) section).getItems());
                        dos.writeInt(list.size());
                        for (String s : list) {
                            dos.writeUTF(s);
                        }
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        dos.writeUTF(sectionType.name());
                        List<Organization> organizationList = new ArrayList<>(((OrganizationSection) section).getOrganizations());
                        dos.writeInt(organizationList.size());
                        for (Organization o : organizationList) {
                            Link link = o.getHomePage();
                            writeLink(dos, link);
                        }
                        for (int j = 0; j < organizationList.size() - 1; j++) {
                            Organization.Position p = organizationList.get(j).getPositions().get(j);
                            writeLocalDate(dos, p.getStartDate());
                            writeLocalDate(dos, p.getEndDate());
                            dos.writeUTF(p.getTitle());
                            dos.writeUTF(p.getDescription() == null ? " " : p.getDescription());
                        }
                        break;
                }
            }
        }
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate ld) throws IOException {
        dos.writeInt(ld.getYear());
        dos.writeInt(ld.getMonth().getValue());
    }

    private void writeLink(DataOutputStream dos, Link link) throws IOException {
        dos.writeUTF(link.getName());
        dos.writeUTF(link.getUrl() == null ? " " : link.getUrl());
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

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }

    private List<String> doGetListSections(DataInputStream dis, int sizeList) throws IOException {
        List<String> listSections = new ArrayList<>(sizeList);
        for (int i = 0; i < sizeList; i++) {
            listSections.add(dis.readUTF());
        }
        return listSections;
    }

    private Link getLink(DataInputStream dis) throws IOException {
        return new Link(dis.readUTF(), dis.readUTF() == " " ? null : dis.readUTF());
    }

    private List<Organization.Position> getPositions(DataInputStream dis, int sizeOrganizationList) throws IOException {
        List<Organization.Position> listPositions = new ArrayList<>(sizeOrganizationList);
        for (int i = 0; i < sizeOrganizationList; i++){
            listPositions.add(new Organization.Position(
                readLocalDate(dis), readLocalDate(dis), dis.readUTF(), dis.readUTF()));
        }
         return listPositions;
    }

    private Section readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        Section section = null;
        switch (sectionType) {
            case PERSONAL:
            case OBJECTIVE:
                section = new TextSection(dis.readUTF());
            break;
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                int sizeListSections = dis.readInt();
                section = new ListSection(doGetListSections(dis, sizeListSections));
            break;
            case EXPERIENCE:
            case EDUCATION:
                int sizeOrganizationList = dis.readInt();
                section = new OrganizationSection(new Organization(getLink(dis), getPositions(dis, sizeOrganizationList)));
        }
        return section;
    }
}