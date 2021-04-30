package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage {

    private Map<String, Resume> mapStorage = new TreeMap<>();

    @Override
    protected int findIndex(Object uuid) {
        String uuid1 = (String) uuid;
        return (mapStorage.containsKey(uuid1))? 1 : -1;
    }

    @Override
    protected void doUpdate(Object resume, Object index) {
        Resume resume1 = (Resume) resume;
        mapStorage.replace(resume1.getUuid(),resume1);
    }

    @Override
    protected void doSave(Object resume, Object index) {
        Resume resume1 = (Resume) resume;
        mapStorage.put(resume1.getUuid(), resume1);
    }

    @Override
    protected Resume doGet(Object uuid, Object index) {
        String uuid1 = (String) uuid;
        return mapStorage.get(uuid1);
    }

    @Override
    protected void doDelete(Object uuid, Object index) {
        String uuid1 = (String) uuid;
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
