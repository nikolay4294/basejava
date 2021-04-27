package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected static int countResume = 0;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    protected abstract void saveToArray(Resume resume, int index);

    protected abstract void deleteFromArray(int index);


    public void clear() {
        Arrays.fill(storage, 0, countResume, null);
        countResume = 0;
    }

    public void doUpdate(Resume resume, int index) {
        storage[index] = resume;
    }

    public void doSave(Resume resume, int index) {
        if (countResume < storage.length) {
            saveToArray(resume, index);
            countResume++;
        } else {
            throw new StorageException("База переполнена", resume.getUuid());
        }
    }

    public final Resume doGet(String uuid, int index) {
        return storage[index];
    }

    public final void doDelete(String uuid, int index) {
        deleteFromArray(index);
        countResume--;
    }

    public int size() {
        return countResume;
    }

    public List<Resume> getAllSorted() {
        Resume[] newStorage = Arrays.copyOfRange(storage,0,countResume);
        return Arrays.asList(newStorage);
    }
}