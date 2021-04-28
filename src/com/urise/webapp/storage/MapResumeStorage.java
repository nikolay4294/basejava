package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage {
    private Map<Resume, Resume> mapStorage = new TreeMap<>();

    @Override
    protected int findIndex(String uuid) {
        List<Resume> keyList = new ArrayList<>(mapStorage.keySet());
        for (Resume r : keyList) {
            if (r.getUuid().equals(uuid)) return 1;
        }
        return -1;
    }

    @Override
    protected void doUpdate(Resume resume, int index) {
        List<Resume> keyList = new ArrayList<>(mapStorage.keySet());
        for (Resume r : keyList) {
            if (r.equals(resume)) {
                mapStorage.put(r, resume);
            }
        }
    }

    @Override
    protected void doSave(Resume resume, int index) {
        mapStorage.put(resume, resume);
    }

    @Override
    protected Resume doGet(String uuid, int index) {
        List<Resume> value = new ArrayList<>(mapStorage.values());
        for (Resume r : value) {
            if (r.getUuid().equals(uuid)) return r;
        }
        return null;
    }

    @Override
    protected void doDelete(String uuid, int index) {
        List<Resume> valueList = new ArrayList<>(mapStorage.values());
        for (Resume r : valueList) {
            if (r.getUuid().equals(uuid)) {
                mapStorage.remove(r);
            }
        }
    }

    @Override
    public void clear() {
        mapStorage.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> value = new ArrayList<>(mapStorage.values());
        Collections.sort(value);
        return value;
    }

    @Override
    public int size() {
        return mapStorage.size();
    }
}
