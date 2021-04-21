package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.List;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected int findIndex(String uuid) {
        for (int i = 0; i < countResume; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void saveResume(Resume resume, int index) {
        storage[countResume] = resume;
    }

    @Override
    protected void deleteResume(int index) {
        storage[index] = storage[countResume - 1];
    }

    @Override
    public List<Resume> getAllSorted() {
        return null;
    }
}
