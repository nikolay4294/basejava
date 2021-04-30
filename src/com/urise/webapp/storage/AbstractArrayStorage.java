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

    public void doUpdate(Object resume, Object index) {
        Resume resume1 = (Resume) resume;
        int index1 = (int) index;
        storage[index1] = resume1;
    }

    public void doSave(Object resume, Object index) {
        Resume resume1 = (Resume) resume;
        int index1 = (int) index;
        if (countResume < storage.length) {
            saveToArray(resume1, index1);
            countResume++;
        } else {
            throw new StorageException("База переполнена", resume1.getUuid());
        }
    }

    public final Resume doGet(Object uuid, Object index) {
        int index1 = (int) index;
        return storage[index1];
    }

    public final void doDelete(Object uuid, Object index) {
        int index1 = (int) index;
        deleteFromArray(index1);
        countResume--;
    }

    public int size() {
        return countResume;
    }

    public List<Resume> doGetList() {
        Resume[] newStorage = Arrays.copyOfRange(storage,0,countResume);
        return Arrays.asList(newStorage);
    }
}