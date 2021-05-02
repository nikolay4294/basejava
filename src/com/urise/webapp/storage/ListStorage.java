package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

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
    public void doUpdate(Resume resume, Object index) {
        int index1 = (int) index;
        listStorage.set(index1, resume);
    }

    @Override
    public void doSave(Resume resume, Object index) {
        listStorage.add(resume);
    }

    @Override
    public Resume doGet(String uuid, Object index) {
        int index1 = (int) index;
        return listStorage.get(index1);
    }

    @Override
    public void doDelete(String uuid, Object index) {
        int index1 = (int) index;
        listStorage.remove(index1);
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
