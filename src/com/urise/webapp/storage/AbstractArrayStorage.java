package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int countResume = 0;

    public final void clear() {
        Arrays.fill(storage, 0, countResume, null);
        countResume = 0;
    }

    public final void update(Resume resume) {
        String uuid = resume.getUuid();
        int index = findIndex(uuid);
        if (index >= 0) {
            storage[index] = resume;
            System.out.println("Резюме " + resume.getUuid() + " успешно обновлено");
        } else {
            System.out.println("Ошибка! Резюме " + resume.getUuid() + " нет в базе данных");
        }
    }

    public final Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            System.out.println("Резюме " + uuid + " найдено");
            return storage[index];
        } else {
            System.out.println("Запрашиваемое резюме " + uuid + " отсутствует в базе.");
            return null;
        }
    }

    public final void delete(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            countResume--;
            System.out.println("Резюме " + uuid + " удалено.");
            storage[index] = storage[countResume];
        } else {
            System.out.println("Резюме " + uuid + " отсутствует в базе, удалить его невозможно.");
        }
    }

    public final Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, countResume);
    }

    public final int size() {
        return countResume;
    }

    protected abstract int findIndex(String uuid);
}
