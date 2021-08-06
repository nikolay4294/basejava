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
                        }
                        for (Organization o : organizationList) {
                            Organization.Position p = organizationList.get(0).getPositions().get(0);
                            dos.writeUTF(p.getDescription() == null ? " " : p.getDescription());
                            dos.writeUTF(p.getTitle());
                            writeLocalDate(dos, p.getStartDate());
                            writeLocalDate(dos, p.getEndDate());
                        }
                        break;
                }
            }
        }
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate ld) throws IOException {
        dos.writeInt(ld.getDayOfMonth());
        dos.writeInt(ld.getDayOfMonth());
        dos.writeInt(ld.getYear());
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
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        resume.addSections(sectionType, new TextSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        resume.addSections(sectionType, new ListSection(doGetListSections(dis)));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        for (int j = 0; j < dis.readInt(); j++) {
                            resume.addSections(sectionType, new OrganizationSection(new Organization(getLinks(dis), getPositions(dis))));
                        }
                }
            }
            return null;
        }
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }

    private List<String> doGetListSections(DataInputStream dis) throws IOException {
        List<String> listSections = new ArrayList<>(dis.readInt());
        for (int j = 0; j < dis.readInt(); j++) {
            listSections.add(dis.readUTF());
        }
        return listSections;
    }

    private Link getLinks(DataInputStream dis) throws IOException {
        return new Link(dis.readUTF(), dis.readUTF() == " " ? null : dis.readUTF());
    }

    private List<Organization.Position> getPositions(DataInputStream dis) throws IOException {
        List<Organization.Position> positions = new ArrayList<>(dis.readInt());
        positions.add(new Organization.Position(
                readLocalDate(dis), readLocalDate(dis), dis.readUTF(), dis.readUTF() == " " ? null : dis.readUTF())
        );
        return positions;
    }
}