package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.io.InputStream;
import java.io.OutputStream;

public interface Strategy {
    void save(Resume resume, OutputStream os);
    Resume read(InputStream is);
}
