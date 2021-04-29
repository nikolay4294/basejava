package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage {

    private Map<Resume, Resume> mapStorage = new TreeMap<>();

    @Override
    protected int findIndex(String uuid) {
        Object ob = uuid;
        return (mapStorage.containsValue(ob))? 1 : -1;
    }

    @Override
    protected void doUpdate(Resume resume, int index) {
        mapStorage.replace(resume,resume);
    }

    @Override
    protected void doSave(Resume resume, int index) {
        mapStorage.put(resume, resume);
    }

    @Override
    protected Resume doGet(String uuid, int index) {
        Object ob = uuid;
        Resume r = mapStorage.get(uuid);
        return r;
    }

    @Override
    protected void doDelete(String uuid, int index) {
        Object ob = uuid;
        mapStorage.remove(uuid);
    }

    @Override
    public void clear() {
        mapStorage.clear();
    }

    @Override
    public List<Resume> doGetList() {
        List<Resume> value = new ArrayList<>(mapStorage.values());
        return value;
    }

    @Override
    public int size() {
        return mapStorage.size();
    }
}
