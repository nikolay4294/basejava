package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    private List<Resume> listStorage = new ArrayList<>();

    @Override
    public void clear() {
        listStorage.clear();
    }

    @Override
    public List<Resume> doGetList() {
        return listStorage;
    }

    @Override
    protected int checkResumeInStorage(Integer searchKey) {
        return searchKey;
    }

    @Override
    public void doUpdate(Resume resume, Integer searchKey) {
        listStorage.set(searchKey, resume);
    }

    @Override
    public void doSave(Resume resume, Integer searchKey) {
        listStorage.add(resume);
    }

    @Override
    public Resume doGet(Integer searchKey) {
        return listStorage.get(searchKey);
    }

    @Override
    public void doDelete(Integer searchKey) {
        listStorage.remove((int) searchKey);
    }

    @Override
    public int size() {
        return listStorage.size();
    }

    @Override
    protected Integer findIndex(String uuid) {
        for (int i = 0; i < listStorage.size(); i++) {
            if (listStorage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}