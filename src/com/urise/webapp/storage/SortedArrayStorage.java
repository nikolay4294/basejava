package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int findIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, countResume, searchKey);
    }

    @Override
    protected void saveResume(Resume resume, int index) {
        int inputNum = -index - 1;
        System.arraycopy(storage, inputNum, storage, inputNum + 1, countResume - inputNum);
        storage[inputNum] = resume;
    }

    @Override
    protected void deleteResume(int index) {
        int num = countResume - index - 1;
        if (num > 0) {
            System.arraycopy(storage, index + 1, storage, index, num);
        }
    }
}
