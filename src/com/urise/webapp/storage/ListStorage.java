package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage{

    protected ArrayList<Resume> arrayList = new ArrayList<>();

    @Override
    public void doClearResume() {
        arrayList.clear();
    }

    @Override
    public void doUpdate(Resume resume, int index) {
        arrayList.set(index, resume);
    }

    @Override
    public void doSave(Resume resume, int index) {
        arrayList.add(resume);
    }

    @Override
    public Resume doGetResume(String uuid, int index) {
        return arrayList.get(index);
    }

    @Override
    public void doDeleteResume(String uuid, int index) {
        arrayList.remove(index);
    }

    @Override
    public Resume[] getAll() {
        for(Resume r : arrayList) {
            System.out.println(r);
        }
        return null;
    }

    @Override
        protected int findIndex(String uuid) {
            return arrayList.indexOf(uuid);
    }
}
