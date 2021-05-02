package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    protected abstract int findIndex(String uuid);

    protected abstract void doUpdate(Resume resume, Object index);

    protected abstract void doSave(Resume resume, Object index);

    protected abstract Resume doGet(String uuid, Object index);

    protected abstract void doDelete(String uuid, Object index);

    protected abstract List<Resume> doGetList();

    private int doExistException(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) throw new NotExistStorageException(uuid);
        return index;
    }

    private int doNotExistException(String uuid) {
        int index = findIndex(uuid);
        if(index >= 0) throw new ExistStorageException(uuid);
        return index;
    }

    public final void update(Resume resume) {
        String uuid = resume.getUuid();
        doExistException(uuid);
        doUpdate(resume, doExistException(uuid));
        System.out.println("Резюме " + resume.getUuid() + " успешно обновлено");
    }

    public final void save(Resume resume) {
        String uuid = resume.getUuid();
        doNotExistException(uuid);
        doSave(resume, doNotExistException(uuid));
        System.out.println("Резюме " + resume.getUuid() + " успешно добавлено.");
    }

    public final Resume get(String uuid) {
        doExistException(uuid);
        System.out.println("Резюме " + uuid + " найдено");
        return doGet(uuid, doExistException(uuid));
    }

    public final void delete(String uuid) {
        doExistException(uuid);
        doDelete(uuid, doExistException(uuid));
        System.out.println("Резюме " + uuid + " удалено.");
    }

    public final List<Resume> getAllSorted() {
        List<Resume> list = doGetList();
        Collections.sort(list);
        return list;
    }
}