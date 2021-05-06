package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract Integer findIndex(String uuid);

    protected abstract void doUpdate(Resume resume, SK index);

    protected abstract void doSave(Resume resume, SK index);

    protected abstract Resume doGet(SK searchKey);

    protected abstract void doDelete(SK searchKey);

    protected abstract List<Resume> doGetList();

    private SK doExistException(String uuid) {
        Integer index = findIndex(uuid);
        if (index < 0) {
            LOG.warning("Резюме " + uuid + " не существует");
            throw new NotExistStorageException(uuid);
        }
        SK num = (SK) index;
        return num;
    }

    private SK doNotExistException(String uuid) {
        Integer index = findIndex(uuid);
        if (index >= 0) {
            LOG.warning("Резюме " + uuid + " уже существует");
            throw new ExistStorageException(uuid);
        }
        SK num = (SK) index;
        return num;
    }

    public final void update(Resume resume) {
        LOG.info("Update " + resume);
        String uuid = resume.getUuid();
        doUpdate(resume, doExistException(uuid));
        System.out.println("Резюме " + resume.getUuid() + " успешно обновлено");
    }

    public final void save(Resume resume) {
        LOG.info("Save " + resume);
        String uuid = resume.getUuid();
        doSave(resume, doNotExistException(uuid));
        System.out.println("Резюме " + resume.getUuid() + " успешно добавлено.");
    }

    public final Resume get(String uuid) {
        LOG.info("Get " + uuid);
        doExistException(uuid);
        System.out.println("Резюме " + uuid + " найдено");
        SK result = (SK) uuid;
        return doGet(result);
    }

    public final void delete(String uuid) {
        LOG.info("Delete " + uuid);
        doExistException(uuid);
        doDelete((SK) uuid);
        System.out.println("Резюме " + uuid + " удалено.");
    }

    public final List<Resume> getAllSorted() {
        List<Resume> list = doGetList();
        list.sort(Resume::compareTo);
        return list;
    }
}