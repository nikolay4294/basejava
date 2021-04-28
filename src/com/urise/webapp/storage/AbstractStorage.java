package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected abstract int findIndex(String uuid);

    protected abstract void doUpdate(Resume resume, int index);

    protected abstract void doSave(Resume resume, int index);

    protected abstract Resume doGet(String uuid, int index);

    protected abstract void doDelete(String uuid, int index);

    private void doNotExistException(String uuid) {
        throw new NotExistStorageException(uuid);
    }

    private void doExistException(String uuid) {
        throw new ExistStorageException(uuid);
    }

    public final void update(Resume resume) {
        String uuid = resume.getUuid();
        int index = findIndex(uuid);
        if (index >= 0) {
            doUpdate(resume, index);
            System.out.println("Резюме " + resume.getUuid() + " успешно обновлено");
        } else {
            doNotExistException(uuid);
        }
    }

    public final void save(Resume resume) {
        String uuid = resume.getUuid();
        int index = findIndex(uuid);
        if (index >= 0) {
            doExistException(uuid);
        } else {
            doSave(resume, index);
            System.out.println("Резюме " + resume.getUuid() + " успешно добавлено.");
        }
    }

    public final Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            System.out.println("Резюме " + uuid + " найдено");
            return doGet(uuid, index);
        } else {
            doNotExistException(uuid);
        }
        return null;
    }

    public final void delete(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            doDelete(uuid, index);
            System.out.println("Резюме " + uuid + " удалено.");
        } else {
            doNotExistException(uuid);
        }
    }
}