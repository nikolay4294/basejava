package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    final private Resume[] storage = new Resume[10_000];
    private int countResume;

    @Override
    public void save(Resume resume) {
        String uuid = resume.getUuid();
        int index = findIndex(uuid);
        if (countResume < storage.length) {
            if (index == -1) {
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

    /**
     * @return int, checks the database for a resume and returns the serial number in base.
     */
    protected int findIndex(String uuid) {
        for (int i = 0; i < countResume; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
