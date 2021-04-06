package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> listStorage = new ArrayList<>();

    @Override
    public void clear() {
        listStorage.removeAll(listStorage);
    }

    @Override
    public void doUpdate(Resume resume, int index) {
        listStorage.set(index, resume);
    }

    @Override
    public void doSave(Resume resume, int index) {
        listStorage.add(resume);
    }

    @Override
    public Resume doGetResume(String uuid, int index) {
        return listStorage.get(index);
    }

    @Override
    public void doDeleteResume(String uuid, int index) {
        listStorage.remove(index);
    }

    @Override
    public Resume[] getAll() {
        Resume[] resumes = new Resume[3];
        for (int i = 0; i < listStorage.size(); i++) {
            resumes[i] = listStorage.get(i);
        }
        return resumes;
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
