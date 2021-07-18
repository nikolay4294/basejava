package com.urise.webapp.storage;

import com.urise.webapp.storage.Strategy.ObjectStreamStrategy;

public class ObjectStreamStrategyTest extends AbstractStorageTest {

    public ObjectStreamStrategyTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamStrategy()));
    }
}