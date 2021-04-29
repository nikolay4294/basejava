package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    protected abstract int findIndex(String uuid);

    protected abstract void doUpdate(Resume resume, int index);

    protected abstract void doSave(Resume resume, int index);

    protected abstract Resume doGet(String uuid, int index);

    protected abstract void doDelete(String uuid, int index);

    protected abstract List doGetList();

    private void doNotExistExceptions(String uuid, int index) {
        if (index < 0) throw new NotExistStorageException(uuid);
    }

    private void doExistException(String uuid, int index) {
        if(index >= 0) throw new ExistStorageException(uuid);
    }

    public final void update(Resume resume) {
        String uuid = resume.getUuid();
        int index = findIndex(uuid);
        doNotExistExceptions(uuid, index);
        doUpdate(resume, index);
        System.out.println("Резюме " + resume.getUuid() + " успешно обновлено");
    }

    public final void save(Resume resume) {
        String uuid = resume.getUuid();
        int index = findIndex(uuid);
        doExistException(uuid, index);
        doSave(resume, index);
        System.out.println("Резюме " + resume.getUuid() + " успешно добавлено.");
    }

    public final Resume get(String uuid) {
        int index = findIndex(uuid);
        doNotExistExceptions(uuid, index);
        System.out.println("Резюме " + uuid + " найдено");
        return doGet(uuid, index);
    }

    public final void delete(String uuid) {
        int index = findIndex(uuid);
        doNotExistExceptions(uuid, index);
        doDelete(uuid, index);
        System.out.println("Резюме " + uuid + " удалено.");
    }

    public final List<Resume> getAllSorted() {
        List<Resume> list = doGetList();
        Collections.sort(list);
        return list;
    }
}