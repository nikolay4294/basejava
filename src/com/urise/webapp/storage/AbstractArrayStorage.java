package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {

    protected static final int STORAGE_LIMIT = 10_000;
    protected static int countResume = 0;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    protected abstract void saveToArray(Resume resume, int index);

    protected abstract void deleteFromArray(int index);


    public void clear() {
        Arrays.fill(storage, 0, countResume, null);
        countResume = 0;
    }

    public void doUpdate(Resume resume, Integer searchKey) {
        storage[searchKey] = resume;
    }

    public void doSave(Resume resume, Integer searchKey) {
        if (countResume < storage.length) {
            saveToArray(resume, searchKey);
            countResume++;
        } else {
            throw new StorageException("База переполнена", resume.getUuid());
        }
    }

    public final Resume doGet(Integer searchKey) {
        return storage[searchKey];
    }

    public final void doDelete(Integer searchKey) {
        deleteFromArray(searchKey);
        countResume--;
    }

    public int size() {
        return countResume;
    }

    public List<Resume> doGetList() {
        Resume[] newStorage = Arrays.copyOf(storage, countResume);
        return Arrays.asList(newStorage);
    }

    protected boolean isResumeExist(Integer searchKey) {
        return searchKey >= 0;
    }
}