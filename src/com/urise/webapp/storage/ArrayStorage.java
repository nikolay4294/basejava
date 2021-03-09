package com.urise.webapp.storage;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    /**
     * @return int, checks the database for a resume and returns the serial number in base.
     */
    @Override
    protected int findIndex(String uuid) {
        for (int i = 0; i < countResume; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
