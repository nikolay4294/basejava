package com.urise.webapp.storage;

import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

public class SortedArrayStorageTest extends AbstractArrayStorageTest{

    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }
}