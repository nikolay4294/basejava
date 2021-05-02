package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> mapStorage = new TreeMap<>();

    @Override
    protected int findIndex(String uuid) {
        return mapStorage.containsKey(uuid) ? 1 : -1;
    }

    @Override
    protected void doUpdate(Resume resume, Object index) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doSave(Resume resume, Object index) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(String uuid, Object index) {
        return mapStorage.get(uuid);
    }

    @Override
    protected void doDelete(String uuid, Object index) {
        mapStorage.remove(uuid);
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
