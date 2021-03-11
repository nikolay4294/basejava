package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int countResume = 0;

    protected abstract int findIndex(String uuid);

    protected abstract void saveResume(Resume resume, int index);

    protected abstract void deleteResume(int index);

    @Override
    public void clear() {
        Arrays.fill(storage, 0, countResume, null);
        countResume = 0;
    }

    //шаблонный метод, так как реализует абстрактный метод findIndex
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

    //шаблонный метод, так как реализует абстрактный метод findIndex
    public final void save(Resume resume) {
        String uuid = resume.getUuid();
        int index = findIndex(uuid);
        if (countResume < storage.length) {
            if (index > 0) {
                System.out.println("Резюме " + resume.getUuid() + " не добавлено, так как уже существует в базе");
            } else {
                saveResume(resume, index);
                countResume++;
                System.out.println("Резюме " + resume.getUuid() + " успешно добавлено.");
            }
        } else {
            System.out.println("База переполнена, резюме " + resume.getUuid() + " не добавлено.");
        }
    }

    //шаблонный метод, так как реализует абстрактный метод findIndex
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

    //шаблонный метод, так как реализует абстрактный метод findIndex
    public final void delete(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            deleteResume(index);
            countResume--;
            System.out.println("Резюме " + uuid + " удалено.");
        } else {
            System.out.println("Резюме " + uuid + " отсутствует в базе, удалить его невозможно.");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, countResume);
    }

    public int size() {
        return countResume;
    }
}
