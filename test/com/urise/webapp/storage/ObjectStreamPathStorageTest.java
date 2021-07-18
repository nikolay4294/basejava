package com.urise.webapp.storage;

import com.urise.webapp.storage.Strategy.ObjectStreamStrategy;

public class ObjectStreamPathStorageTest extends AbstractStorageTest {

    public ObjectStreamPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.toPath(), new ObjectStreamStrategy()));
    }
}