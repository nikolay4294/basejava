package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer findIndex(String uuid) {
        for (int i = 0; i < countResume; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected int checkResumeInStorage(Integer searchKey) {
        return searchKey;
    }

    @Override
    protected void saveToArray(Resume resume, int index) {
        storage[countResume] = resume;
    }

    @Override
    protected void deleteFromArray(int index) {
        storage[index] = storage[countResume - 1];
    }
}