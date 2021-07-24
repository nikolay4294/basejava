package com.urise.webapp.storage;

import com.urise.webapp.storage.Strategy.XmlStreamSerializer;

public class XmlPathStorageTest extends AbstractStorageTest {

    public XmlPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.toPath(), new XmlStreamSerializer()));
    }
}
