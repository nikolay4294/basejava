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
        for (int i = 0; i < countResume; i++) {
            storage[i] = null;
        }
        countResume = 0;
    }

    public void update(Resume resume) {
        for (int i = 0; i < countResume; i++) {
            if (storage[i].equals(resume)) {
                storage[i] = resume;
                System.out.println("Резюме успешно обновлено");
            }
        }
        System.out.println("Ошибка! Такого резюме нет в базе данных");
    }

    public void save(Resume r) {
        for (int i = 0; i <= countResume; i++) {
            if (chekStorage(r) || chekCountResume(this.countResume)) {
                storage[countResume] = r;
                countResume++;
                System.out.println("Резюме успешно добавлено.");
                break;
            } else if (!chekStorage(r)) {
                System.out.println("База переполнена, резюме не добавлено.");
            }
        }
    }

    /**
     * @return boolean, checks the database for a resume
     */
    private boolean chekStorage(Resume r) {
        for (int i = 0; i < countResume; i++) {
            if (storage[i].equals(r)) {
                return false;
            }
        }
        return true;
    }

    private boolean chekCountResume(int countResume) {
        if (countResume <= 10000) {
            return true;
        }
        return false;
    }

    public Resume get(String uuid) {
        for (int i = 0; i < countResume; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                System.out.println("Резюме найдено");
                return storage[i];
            }
        }
        System.out.println("Запрашиваемое резюме отсутствует в базе.");
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < countResume; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                System.out.println(storage[i].getUuid());
                storage[i] = null;
                countResume--;
                System.out.println("Резюме удалено.");
            } else {
                System.out.println("Резюме отсутствует в базе, удалить его невозможно.");
            }
            for (int j = i; j < countResume; j++) {
                storage[j] = storage[j + 1];
            }
            break;
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
}
