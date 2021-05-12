package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapResumeStorage extends AbstractStorage<Resume> {

    private Map<String, Resume> mapStorage = new TreeMap<>();

    @Override
    protected Resume findIndex(String uuid) {
        return (mapStorage.containsKey(uuid)) ? mapStorage.get(uuid) : null;
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
    protected int checkResumeInStorage(Resume searchKey) {
        return searchKey == null ? -1 : 1;
    }

    @Override
    public int size() {
        return mapStorage.size();
    }
}