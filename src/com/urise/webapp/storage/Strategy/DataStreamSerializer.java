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

                switch (entry.getKey().name()) {
                    case ("PERSONAL"):
                    case ("OBJECTIVE"):
                        dos.writeUTF(((TextSection) section).getContent());
                        break;
                    case ("ACHIEVEMENT"):
                    case ("QUALIFICATIONS"):
                        List<String> list = new ArrayList<>(((ListSection) section).getItems());
                        dos.writeInt(list.size());
                        list.forEach(s -> {
                            try {
                                dos.writeUTF(s);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                        break;
                    case ("EXPERIENCE"):
                    case ("EDUCATION"):
                        List<Organization> organizationList = new ArrayList<>(((OrganizationSection) section).getOrganizations());
                        dos.writeInt(organizationList.size());

                        organizationList.forEach(o -> {
                            try {
                                dos.writeUTF((o.getHomePage()).getName());
                                dos.writeUTF((o.getHomePage()).getUrl());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });

                        List<Organization.Position> positions = new ArrayList<>();
                        organizationList.forEach(o -> o.getPositions().add((Organization.Position) positions));
                        positions.forEach(p -> {
                            try {
                                dos.writeUTF(p.getDescription());
                                dos.writeUTF(p.getTitle());
                                localDate(dos, p.getStartDate());
                                localDate(dos, p.getEndDate());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    break;
                }
            }
        }
    }

    private void localDate(DataOutputStream dos, LocalDate ld) throws IOException {
        dos.writeInt(ld.getDayOfMonth());
        dos.writeInt(ld.getDayOfYear());
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


                }
            }
            return null;
        }

    }

}