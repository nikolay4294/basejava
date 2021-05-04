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
    public void doUpdate(Resume resume, Integer index) {
        listStorage.set(index, resume);
    }

    @Override
    public void doSave(Resume resume, Integer index) {
        listStorage.add(resume);
    }

    @Override
    public Resume doGet(Integer searchKey) {
        //int index = findIndex(searchKey);
        //return listStorage.get(index);
        return listStorage.get(searchKey);
    }

    @Override
    public void doDelete(Integer searchKey) {
        //int index = findIndex(searchKey);
        //listStorage.remove(index);
        listStorage.remove(searchKey);
    }

    @Override
    public int size() {
        return listStorage.size();
    }

    @Override
    protected int findIndex(String uuid) {
        for (int i = 0; i < listStorage.size(); i++) {
            if (listStorage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}