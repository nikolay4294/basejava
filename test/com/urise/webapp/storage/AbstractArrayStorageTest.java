package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    private Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final Resume uuid1 = new Resume(UUID_1);

    private static final String UUID_2 = "uuid2";
    private static final Resume uuid2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid3";
    private static final Resume uuid3 = new Resume(UUID_3);

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        Resume resume = new Resume(UUID_2);
        storage.update(resume);
        Assert.assertSame(resume, storage.get(UUID_2));
    }

    @Test
    public void save() {
        Resume resume = new Resume(UUID_3);
        storage.save(resume);
    }

    @Test
    public void get() {
        Resume resume = new Resume(UUID_2);
        storage.get(UUID_2);
        Assert.assertEquals(resume, storage.get(UUID_2));
    }

    @Test
    public void delete() {
        storage.delete("uuid1");
        Assert.assertEquals(2,storage.size());
        Assert.assertTrue("резюме не удалено",storage.size() == 2);
    }

    @Test
    public void getAll() {
        ArrayStorage storageTest = new ArrayStorage();
        storageTest.save(new Resume("uuid1"));
        storageTest.save(new Resume("uuid2"));
        storageTest.save(new Resume("uuid3"));
        //Assert.
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }
}