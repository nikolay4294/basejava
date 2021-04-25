package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MapResumeStorage extends AbstractStorage {
    private Map<Resume, Resume> mapStorage = new TreeMap<>();

    @Override
    protected int findIndex(String uuid) {
        Set<Resume> keyList = mapStorage.keySet();
        for (Resume r: keyList){
            return r.getUuid().equals(uuid)? 1: -1;
        }
        return 0;
    }

    @Override
    protected void doUpdate(Resume resume, int index) {
        System.out.println(index);
        mapStorage.put(resume,resume);
    }

    @Override
    protected void doSave(Resume resume, int index) {
        mapStorage.put(resume,resume);
    }

    @Override
    protected Resume doGetResume(String uuid, int index) {
        Set<Resume> keyList = mapStorage.keySet();
        for (Resume r: keyList){
            return r.getUuid().equals(uuid)? r: null;
        }
        return null;
    }

    @Override
    protected void doDeleteResume(String uuid, int index) {
        Set<Resume> keyList = mapStorage.keySet();
        for (Resume r: keyList){
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
        List<Resume> value = (List<Resume>) mapStorage.values();
        return value;
    }

    @Override
    public int size() {
        return mapStorage.size();
    }
}
