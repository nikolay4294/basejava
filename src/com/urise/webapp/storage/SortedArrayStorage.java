package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void clear() {
        Arrays.fill(storage, 0, countResume, null);
        countResume = 0;
    }

    @Override
    public void update(Resume resume) {
        String uuid = resume.getUuid();
        int index = findIndex(uuid);
        if (index >= 0) {
            storage[index] = resume;
            System.out.println("Резюме " + resume.getUuid() + " успешно обновлено");
        } else {
            System.out.println("Ошибка! Резюме " + resume.getUuid() + " нет в базе данных");
        }
    }

    @Override
    public void save(Resume resume) {
        String uuid = resume.getUuid();
        int index = -(findIndex(uuid));
        if (countResume < storage.length) {
            if (index > 0) {
                storage[countResume] = resume;
                countResume++;
                System.out.println("Резюме " + resume.getUuid() + " успешно добавлено.");
            } else {
                System.out.println("Резюме " + resume.getUuid() + " не добавлено, так как уже существует в базе");
            }
        } else {
            System.out.println("База переполнена, резюме " + resume.getUuid() + " не добавлено.");
        }
    }

    @Override
    public void delete(String uuid) {
        int index = -(findIndex(uuid));
        if (index >= 0) {
            countResume--;
            System.out.println("Резюме " + uuid + " удалено.");
            storage[index] = storage[countResume];
        } else {
            System.out.println("Резюме " + uuid + " отсутствует в базе, удалить его невозможно.");
        }
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, countResume);
    }

    @Override
    protected int findIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, countResume, searchKey);
    }
}
