package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract SK findSearchKey(String uuid);

    protected abstract void doUpdate(Resume resume, SK searchKey);

    protected abstract void doSave(Resume resume, SK searchKey);

    protected abstract Resume doGet(SK searchKey);

    protected abstract void doDelete(SK searchKey);

    protected abstract List<Resume> doGetList();

    protected abstract boolean isResumeExist(SK searchKey);

    public final void update(Resume resume) {
        LOG.info("Update " + resume);
        String uuid = resume.getUuid();
        doUpdate(resume, searchKey(uuid));
        System.out.println("Резюме " + uuid + " успешно обновлено");
    }

    public final void save(Resume resume) {
        LOG.info("Save " + resume);
        String uuid = resume.getUuid();
        doSave(resume, searchKeyNotExist(uuid));
        System.out.println("Резюме " + uuid + " успешно добавлено.");
    }

    public final Resume get(String uuid) {
        LOG.info("Get " + uuid);
        System.out.println("Резюме " + uuid + " найдено");
        return doGet(searchKey(uuid));
    }

    public final void delete(String uuid) {
        LOG.info("Delete " + uuid);
        doDelete(searchKey(uuid));
        System.out.println("Резюме " + uuid + " удалено.");
    }

    public final List<Resume> getAllSorted() {
        List<Resume> list = doGetList();
        list.sort(Resume::compareTo);
        return list;
    }

    private SK searchKey(String uuid) {
        SK searchKey = findSearchKey(uuid);
        if (!isResumeExist(searchKey)) {
            LOG.warning("Резюме " + uuid + " не существует");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK searchKeyNotExist(String uuid) {
        SK searchKey = findSearchKey(uuid);
        if (isResumeExist(searchKey)) {
            LOG.warning("Резюме " + uuid + " уже существует");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }
}