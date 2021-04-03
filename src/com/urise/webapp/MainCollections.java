package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MainCollections {
    private static final String UUID_1 = "uuid1";
    private static final Resume r1 = new Resume(UUID_1);

    private static final String UUID_2 = "uuid2";
    private static final Resume r2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid3";
    private static final Resume r3 = new Resume(UUID_3);

    private static final String UUID_4 = "uuid4";
    private static final Resume r4 = new Resume(UUID_4);

    public static void main(String[] args) {
        Collection<Resume> collection = new ArrayList<>();
        collection.add(r1);
        collection.add(r2);
        collection.add(r3);

        for (Resume r : collection) {
            System.out.println(r);
            if (r.getUuid().equals(UUID_1)) {
                //               collection.remove(r);
            }
        }
        Iterator<Resume> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Resume resume = iterator.next();
            if (resume.getUuid().equals(UUID_1)) {
                iterator.remove();
            }
        }
        System.out.println(collection.toString());

        Map<String, Resume> map = new HashMap<>();
        map.put(UUID_1, r1);
        map.put(UUID_2, r2);
        map.put(UUID_3, r3);

        for (String uuid : map.keySet()) {
            System.out.println(map.get(uuid));
        }

        for (Map.Entry<String, Resume> entry : map.entrySet()) {
            System.out.println(entry.getValue());
        }
    }
}