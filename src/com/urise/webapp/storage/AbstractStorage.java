package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    protected abstract int findIndex(Object uuid);

    protected abstract void doUpdate(Object resume, Object index);

    protected abstract void doSave(Object resume, Object index);

    protected abstract Resume doGet(Object uuid, Object index);

    protected abstract void doDelete(Object uuid, Object index);

    protected abstract List<Resume> doGetList();

    private int doNotExistExceptions(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) throw new NotExistStorageException(uuid);
        return index;
    }

    private int doExistException(String uuid) {
        int index = findIndex(uuid);
        if(index >= 0) throw new ExistStorageException(uuid);
        return index;
    }

    public final void update(Resume resume) {
        String uuid = resume.getUuid();
        doNotExistExceptions(uuid);
        doUpdate(resume, doNotExistExceptions(uuid));
        System.out.println("Резюме " + resume.getUuid() + " успешно обновлено");
    }

    public final void save(Resume resume) {
        String uuid = resume.getUuid();
        doExistException(uuid);
        doSave(resume, doExistException(uuid));
        System.out.println("Резюме " + resume.getUuid() + " успешно добавлено.");
    }

    public final Resume get(String uuid) {
        doNotExistExceptions(uuid);
        System.out.println("Резюме " + uuid + " найдено");
        return doGet(uuid, doNotExistExceptions(uuid));
    }

    public final void delete(String uuid) {
        doNotExistExceptions(uuid);
        doDelete(uuid, doNotExistExceptions(uuid));
        System.out.println("Резюме " + uuid + " удалено.");
    }

    public final List<Resume> getAllSorted() {
        List<Resume> list = doGetList();
        Collections.sort(list);
        return list;
    }
}