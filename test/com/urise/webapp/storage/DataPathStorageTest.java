package com.urise.webapp.storage;

import com.urise.webapp.storage.Strategy.DataStreamSerializer;

public class DataPathStorageTest extends AbstractStorageTest {

    public DataPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.toPath(), new DataStreamSerializer()));
    }
}