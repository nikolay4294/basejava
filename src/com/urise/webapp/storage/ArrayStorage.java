package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    final private Resume[] storage = new Resume[10_000];
    private int countResume;

    public void clear() {
        Arrays.fill(storage, 0, countResume, null);
        countResume = 0;
    }

    public void update(Resume resume) {
        String uuid = resume.getUuid();
        int index = findIndex(uuid);
        if (index >= 0) {
            storage[index] = resume;
            System.out.println("Резюме " + resume.getUuid() + " успешно обновлено");
        } else
            System.out.println("Ошибка! Резюме " + resume.getUuid() + " нет в базе данных");
    }

    public void save(Resume resume) {
        String uuid = resume.getUuid();
        int index = findIndex(uuid);
        if (countResume < storage.length) {
            if (index == -1) {
                storage[countResume] = resume;
                countResume++;
                System.out.println("Резюме " + resume.getUuid() + " успешно добавлено.");
            } else {
                System.out.println("Резюме " + resume.getUuid() + " не добавлено, так как уже существует в базе");
            }
        } else
            System.out.println("База переполнена, резюме " + resume.getUuid() + " не добавлено.");
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            System.out.println("Резюме " + uuid + " найдено");
            return storage[index];
        } else
        System.out.println("Запрашиваемое резюме " + uuid + " отсутствует в базе.");
        return null;
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            storage[index] = null;
            countResume--;
            System.out.println("Резюме " + uuid + " удалено.");
            storage[index] = storage[countResume];
        } else
            System.out.println("Резюме " + uuid + " отсутствует в базе, удалить его невозможно.");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, countResume);
    }

    public int size() {
        return countResume;
    }

    /**
     * @return int, checks the database for a resume and returns the serial number in base.
     */
    private int findIndex(String uuid) {
        for (int i = 0; i < countResume; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
