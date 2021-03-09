package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    final private Resume[] storage = new Resume[10_000];
    protected int countResume;

    /**
     * @return int, checks the database for a resume and returns the serial number in base.
     */
    @Override
    protected int findIndex(String uuid) {
        for (int i = 0; i < countResume; i++) {
            System.out.println("файндИндекс " + uuid);
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
