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
    public void doUpdate(Object resume, Object index) {
        int index1 = (int) index;
        Resume resume1 = (Resume) resume;
        listStorage.set(index1, resume1);
    }

    @Override
    public void doSave(Object resume, Object index) {
        Resume resume1 = (Resume) resume;
        listStorage.add(resume1);
    }

    @Override
    public Resume doGet(Object uuid, Object index) {
        int index1 = (int) index;
        return listStorage.get(index1);
    }

    @Override
    public void doDelete(Object uuid, Object index) {
        int index1 = (int) index;
        listStorage.remove(index1);
    }

    @Override
    public int size() {
        return listStorage.size();
    }

    @Override
    protected int findIndex(Object uuid) {
        String uuid1 = (String) uuid;
        for (int i = 0; i < listStorage.size(); i++) {
            if (listStorage.get(i).getUuid().equals(uuid1)) {
                return i;
            }
        }
        return -1;
    }
}
