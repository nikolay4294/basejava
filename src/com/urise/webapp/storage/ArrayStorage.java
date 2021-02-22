package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int countResume;

    public void clear() {
        Arrays.fill(storage, 0, countResume, null);
        countResume = 0;
    }

    public void update(Resume resume) {
        String uuid = resume.getUuid();
        if (chekStorage(uuid) >= 0) {
            storage[chekStorage(uuid)] = resume;
            System.out.println("Резюме " + resume.getUuid() + " успешно обновлено");
        }
        System.out.println("Ошибка! Резюме " + resume.getUuid() + " нет в базе данных");
    }

    public void save(Resume resume) {
        String uuid = resume.getUuid();
        if (chekStorage(uuid) == -1 || this.countResume < 10000) {
            storage[countResume] = resume;
            countResume++;
            System.out.println("Резюме " + resume.getUuid() + " успешно добавлено.");
        } else if (this.countResume >= 10000) {
            System.out.println("База переполнена, резюме не добавлено.");
        }
    }

    public Resume get(String uuid) {
        if (chekStorage(uuid) >= 0) {
            System.out.println("Резюме " + uuid + " найдено");
            return storage[chekStorage(uuid)];
        }
        System.out.println("Запрашиваемое резюме " + uuid + " отсутствует в базе.");
        return null;
    }

    public void delete(String uuid) {
        int number = chekStorage(uuid);
        storage[number] = null;
        countResume--;
        System.out.println("Резюме " + uuid + " удалено.");

        if (number < 0) {
            System.out.println("Резюме " + uuid + " отсутствует в базе, удалить его невозможно.");
        }

        for (int j = number; j < countResume; j++) {
            storage[j] = storage[j + 1];
        }
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
    private int chekStorage(String uuid) {
        for (int i = 0; i < countResume - 1; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
