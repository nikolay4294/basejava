package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapStorage extends AbstractStorage<String> {

    private Map<String, Resume> mapStorage = new TreeMap<>();

    @Override
    protected String findSearchKey(String uuid) {
        return uuid;
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
    protected boolean isResumeExist(String searchKey) {
        return mapStorage.containsKey(searchKey);
    }

    @Override
    public int size() {
        return mapStorage.size();
    }
}