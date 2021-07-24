package com.urise.webapp.storage;

import com.urise.webapp.storage.Strategy.ObjectStreamSerializer;

public class ObjectPathStorageTest extends AbstractStorageTest {

    public ObjectPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.toPath(), new ObjectStreamSerializer()));
    }
}