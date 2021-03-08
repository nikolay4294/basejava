package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume resume) {
        String uuid = resume.getUuid();
        int index = -(findIndex(uuid));
        if (countResume < storage.length) {
            if (index > 0) {
                storage[countResume] = resume;
                countResume++;
                System.out.println("Резюме " + resume.getUuid() + " успешно добавлено.");
            } else {
                System.out.println("Резюме " + resume.getUuid() + " не добавлено, так как уже существует в базе");
            }
        } else {
            System.out.println("База переполнена, резюме " + resume.getUuid() + " не добавлено.");
        }
    }

    @Override
    protected int findIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, countResume, searchKey);
    }
}
