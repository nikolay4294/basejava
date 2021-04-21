package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> mapStorage = new TreeMap<>();

    @Override
    protected int findIndex(String uuid) {
        Set<String> keyList = mapStorage.keySet();
        if (keyList.contains(uuid)) {
            return 1;
        }
        return -1;
    }

    @Override
    protected void doUpdate(Resume resume, int index) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doSave(Resume resume, int index) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGetResume(String uuid, int index) {
        return mapStorage.get(uuid);
    }

    @Override
    protected void doDeleteResume(String uuid, int index) {
        mapStorage.remove(uuid);
    }

    @Override
    public void clear() {
        mapStorage.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> value = (List<Resume>) mapStorage.values();
        return value;
    }

    @Override
    public int size() {
        return mapStorage.size();
    }
}
