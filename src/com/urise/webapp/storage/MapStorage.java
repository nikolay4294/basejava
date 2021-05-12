package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapStorage extends AbstractStorage<String> {

    private Map<String, Resume> mapStorage = new TreeMap<>();

    @Override
    protected String findIndex(String uuid) {
        return (mapStorage.containsKey(uuid)) ? uuid : null;
    }

    @Override
    protected void doUpdate(Resume resume, String searchKey) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doSave(Resume resume, String searchKey) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(String searchKey) {
        return mapStorage.get(searchKey);
    }

    @Override
    protected void doDelete(String searchKey) {
        mapStorage.remove(searchKey);
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
    protected int checkResumeInStorage(String searchKey) {
        return searchKey == null ? -1 : 1;
    }

    @Override
    public int size() {
        return mapStorage.size();
    }
}