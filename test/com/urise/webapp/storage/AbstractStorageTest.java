package com.urise.webapp.storage;

import com.urise.webapp.Config;
import com.urise.webapp.ResumeTestData;
import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractStorageTest {
    protected final static File STORAGE_DIR = Config.get().getStorageDir();
    protected Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final Resume R1;
    private static final Resume R2;
    private static final Resume R3;
    private static final Resume R4;

    static {
        //R1 = new Resume(UUID_1, "Name1");
        R1 = ResumeTestData.createResume(UUID_1, "Name1");
        //R2 = new Resume(UUID_2, "Name2");
        R2 = ResumeTestData.createResume(UUID_2, "Name2");
        //R3 = new Resume(UUID_3, "Name3");
        R3 = ResumeTestData.createResume(UUID_3, "Name3");
        //R4 = new Resume(UUID_4, "Name4");
        R4 = ResumeTestData.createResume(UUID_4, "Name4");
    }

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(R2);
        storage.save(R1);
        storage.save(R3);
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        Resume resume = ResumeTestData.createResume("uuid2", UUID_2);
        storage.update(resume);
        Assert.assertTrue(resume.equals(storage.get(UUID_2)));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(R4);
    }

    @Test
    public void save() {
        storage.save(R4);
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(R4, storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveAlreadyExist() {
        storage.save(R1);
    }

    @Test
    public void get() {
        Resume actualResume = storage.get(UUID_1);
        Assert.assertEquals(R1, actualResume);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_4);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.size());
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_4);
    }

    @Test
    public void getAllSorted() {
        List<Resume> actualList = storage.getAllSorted();
        List<Resume> expectedList = Arrays.asList(R1, R2, R3);
        expectedList.sort(Resume::compareTo);
        Assert.assertEquals(expectedList, actualList);
    }
}