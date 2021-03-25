package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final Resume r1 = new Resume(UUID_1);

    private static final String UUID_2 = "uuid2";
    private static final Resume r2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid3";
    private static final Resume r3 = new Resume(UUID_3);

    private static final String UUID_4 = "uuid4";
    private static final Resume r4 = new Resume(UUID_4);

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
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

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(r4);
    }

    @Test
    public void save() {
        storage.save(r4);
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(r4, storage.get(UUID_4));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void checkArrayIsFull() {
        Resume[] resumes = new Resume[AbstractArrayStorage.STORAGE_LIMIT];
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                resumes[i] = new Resume();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            Assert.fail("переполнение произошло раньше времени");
        }
        resumes[AbstractArrayStorage.STORAGE_LIMIT + 1] = r4;
    }


    @Test
    public void get() {
        Resume actualResume = storage.get(UUID_1);
        Assert.assertEquals(r1, actualResume);
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
    public void getAll() {
        Resume[] expectedStorage = {r1, r2, r3};
        Resume[] actualStorage = storage.getAll();
        Assert.assertArrayEquals(expectedStorage, actualStorage);
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }
}