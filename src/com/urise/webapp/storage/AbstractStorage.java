package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract SK findIndex(String uuid);

    protected abstract void doUpdate(Resume resume, SK searchKey);

    protected abstract void doSave(Resume resume, SK searchKey);

    protected abstract Resume doGet(SK searchKey);

    protected abstract void doDelete(SK searchKey);

    protected abstract List<Resume> doGetList();

    protected abstract int checkResumeInStorage(SK searchKey);

    private SK getIndexIfResumeExist(String uuid) {
        SK searchKey = findIndex(uuid);
        if (checkResumeInStorage(searchKey) < 0) {
            LOG.warning("Резюме " + uuid + " не существует");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getIndexIfResumeNotExist(String uuid) {
        SK searchKey = findIndex(uuid);
        if (checkResumeInStorage(searchKey) >= 0) {
            LOG.warning("Резюме " + uuid + " уже существует");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    public final void update(Resume resume) {
        LOG.info("Update " + resume);
        String uuid = resume.getUuid();
        doUpdate(resume, getIndexIfResumeExist(uuid));
        System.out.println("Резюме " + uuid + " успешно обновлено");
    }

    public final void save(Resume resume) {
        LOG.info("Save " + resume);
        String uuid = resume.getUuid();
        doSave(resume, getIndexIfResumeNotExist(uuid));
        System.out.println("Резюме " + uuid + " успешно добавлено.");
    }

    public final Resume get(String uuid) {
        LOG.info("Get " + uuid);
        System.out.println("Резюме " + uuid + " найдено");
        return doGet(getIndexIfResumeExist(uuid));
    }

    public final void delete(String uuid) {
        LOG.info("Delete " + uuid);
        doDelete(getIndexIfResumeExist(uuid));
        System.out.println("Резюме " + uuid + " удалено.");
    }

    public final List<Resume> getAllSorted() {
        List<Resume> list = doGetList();
        list.sort(Resume::compareTo);
        return list;
    }
}