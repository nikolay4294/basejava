package com.urise.webapp.storage.Strategy;

import com.urise.webapp.model.*;

import java.io.*;
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
                dos.writeUTF(String.valueOf(entry.getKey()));
                switch (entry.getKey().name()) {
                    case ("PERSONAL"):
                    case ("OBJECTIVE"):
                        dos.writeUTF(String.valueOf((sections.get(entry.getKey()))));
                        //dos.writeUTF(Objects.requireNonNull(getTextSection()));
                        break;
                    case ("ACHIEVEMENT"):
                    case ("QUALIFICATIONS"):
                        dos.writeUTF(String.valueOf(sections.get(entry.getKey())));
                        //dos.writeUTF(Objects.requireNonNull(getListSection()));
                        break;
                    case ("EXPERIENCE"):
                    case ("EDUCATION"):
                        dos.writeUTF(String.valueOf(sections.get(entry.getKey())));
                        //dos.writeUTF(Objects.requireNonNull(getOrganizationSection()));
                        break;
                }
            }
        }
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
                switch (dis.readUTF()) {
                    case ("PERSONAL"):
                    case ("OBJECTIVE"):
                        resume.addSections(SectionType.PERSONAL, );
                }
            }
            return null;
        }
    }

    private String getListSection() {
        ListSection ls = new ListSection();
        List<String> item = ls.getItems();
        for (String value : item) {
            return value;
        }
        return null;
    }

    private String getTextSection() {
        TextSection ts = new TextSection();
        return ts.getContent();
    }

    private String getOrganizationSection() {
        OrganizationSection os = new OrganizationSection();
        List<Organization> organizations = os.getOrganizations();
        for (Organization o : organizations) {
            return String.valueOf(o.getPositions());
        }
        return null;
    }
}