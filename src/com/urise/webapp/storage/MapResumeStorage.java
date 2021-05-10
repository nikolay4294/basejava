package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage<Resume> {

    private Map<String, Resume> mapStorage = new TreeMap<>();

    @Override
    protected Resume findIndex(String uuid) {
        count = (mapStorage.containsKey(uuid)) ? 1 : -1;
        return mapStorage.get(uuid);
    }

    @Override
    protected void doUpdate(Resume resume, Resume searchKey) {
        mapStorage.replace(resume.getUuid(), resume);
    }

    @Override
    protected void doSave(Resume resume, Resume searchKey) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(Resume searchKey) {
        return mapStorage.get(searchKey.getUuid());
    }

    @Override
    protected void doDelete(Resume searchKey) {
        mapStorage.remove(searchKey.getUuid());
    }

    @Override
    public void clear() {
        mapStorage.clear();
    }

    @Override
    public List<Resume> doGetList() {
        return new ArrayList<>(mapStorage.values());
    }

    @Override
    public int size() {
        return mapStorage.size();
    }
}