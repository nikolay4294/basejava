package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected abstract int findIndex(String uuid);

    protected abstract void doUpdate(Resume resume, int index);

    protected abstract void doSave(Resume resume, int index);

    protected abstract Resume doGetResume(String uuid, int index);

    protected abstract void doDeleteResume(String uuid, int index);

    public final void update(Resume resume) {
        String uuid = resume.getUuid();
        int index = findIndex(uuid);
        if (index >= 0) {
            doUpdate(resume, index);
            System.out.println("Резюме " + resume.getUuid() + " успешно обновлено");
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    public final void save(Resume resume) {
        String uuid = resume.getUuid();
        int index = findIndex(uuid);
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            doSave(resume, index);
            System.out.println("Резюме " + resume.getUuid() + " успешно добавлено.");
        }
    }

    public final Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            System.out.println("Резюме " + uuid + " найдено");
            return doGetResume(uuid, index);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public final void delete(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            doDeleteResume(uuid, index);
            System.out.println("Резюме " + uuid + " удалено.");
        } else {
            throw new NotExistStorageException(uuid);
        }
    }
}