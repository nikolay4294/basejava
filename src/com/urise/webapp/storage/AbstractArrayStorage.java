package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected static int countResume = 0;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    protected abstract void saveResume(Resume resume, int index);

    protected abstract void deleteResume(int index);


    public void clear() {
        Arrays.fill(storage, 0, countResume, null);
        countResume = 0;
    }

    public void doUpdate(Resume resume, int index) {
        storage[index] = resume;
    }

    public void doSave(Resume resume, int index) {
        if (countResume < storage.length) {
            saveResume(resume, index);
            countResume++;
        } else {
            throw new StorageException("База переполнена", resume.getUuid());
        }
    }

    public final Resume doGetResume(String uuid, int index) {
        return storage[index];
    }

    public final void doDeleteResume(String uuid, int index) {
        deleteResume(index);
        countResume--;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, countResume);
    }

    public int size() {
        return countResume;
    }
}