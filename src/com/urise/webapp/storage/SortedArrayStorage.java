package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {
    /*
    private static class ResumeComparator implements Comparator<Resume> {

        @Override
        public int compare(Resume o1, Resume o2) {
            return o1.getUuid().compareTo(o2.getUuid());
        }
    }
  */
    private static final Comparator<Resume> RESUME_COMPARATOR = (o1, o2) -> o1.getUuid().compareTo(o2.getUuid());

    @Override
    protected Integer findIndex(String uuid) {
        Resume searchKey = new Resume(uuid, "Петр Петрович");
        return Arrays.binarySearch(storage, 0, countResume, searchKey, RESUME_COMPARATOR);
    }

    @Override
    protected void saveToArray(Resume resume, int index) {
        int inputNum = -index - 1;
        System.arraycopy(storage, inputNum, storage, inputNum + 1, countResume - inputNum);
        storage[inputNum] = resume;
    }

    @Override
    protected void deleteFromArray(int index) {
        int num = countResume - index - 1;
        if (num > 0) {
            System.arraycopy(storage, index + 1, storage, index, num);
        }
    }
}