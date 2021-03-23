package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; //

        if (o == null || getClass() != o.getClass()) return false;
        AbstractArrayStorageTest that = (AbstractArrayStorageTest) o;
        return storage.equals(that.storage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storage);
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
        Assert.assertSame(resume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("uuid4"));
    }

    //результат ожидание/актуальный совпадает, но тест не проходит....
    @Test
    public void save() {
        Assert.assertEquals(3, storage.size());
        Assert.assertEquals("резюме нет в базе данных", uuid1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume("uuid4"));
    }

    @Test
    public void get() {
        Resume expected = new Resume(UUID_2);
        Resume actual = storage.get(UUID_2);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void delete() {
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_1);
        storage.get(UUID_1);
    }

    @Test
    public void getAll() {
        Resume[] expectedStorage = new Resume[storage.size()];
        Resume[] actualStorage = storage.getAll();
        expectedStorage[0] = uuid1;
        expectedStorage[1] = uuid2;
        expectedStorage[2] = uuid3;
        Assert.assertArrayEquals(expectedStorage, actualStorage);
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