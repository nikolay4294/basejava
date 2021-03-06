package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int countResume = 0;

    public int size() {
        return countResume;
    }

    public void clear() {
        Arrays.fill(storage, 0, countResume, null);
        countResume = 0;
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            System.out.println("Резюме " + uuid + " найдено");
            return storage[index];
        } else {
            System.out.println("Запрашиваемое резюме " + uuid + " отсутствует в базе.");
            return null;
        }
    }

    protected abstract int findIndex(String uuid);
}
