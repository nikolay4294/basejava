package com.urise.webapp.storage;

import com.urise.webapp.storage.Strategy.JsonStreamSerializer;

public class JsonPathStorageTest extends AbstractStorageTest{
    public JsonPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.toPath(), new JsonStreamSerializer()));
    }
}
