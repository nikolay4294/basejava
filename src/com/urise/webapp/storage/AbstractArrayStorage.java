package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
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
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    //шаблонный метод, так как реализует абстрактный метод findIndex и saveResume
    public final void save(Resume resume) {
        String uuid = resume.getUuid();
        int index = findIndex(uuid);
        if (countResume < storage.length) {
            if (index >= 0) {
                throw new ExistStorageException(uuid);
            } else {
                saveResume(resume, index);
                countResume++;
                System.out.println("Резюме " + resume.getUuid() + " успешно добавлено.");
            }
        } else {
            throw new StorageException("База переполнена", resume.getUuid());
        }
    }

    //шаблонный метод, так как реализует абстрактный метод findIndex
    public final Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            System.out.println("Резюме " + uuid + " найдено");
            return storage[index];
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    //шаблонный метод, так как реализует абстрактный метод findIndex и deleteResume
    public final void delete(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            deleteResume(index);
            countResume--;
            System.out.println("Резюме " + uuid + " удалено.");
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, countResume);
    }

    public int size() {
        return countResume;
    }
}
