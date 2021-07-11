package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;

public class SavePath implements Strategy{
    @Override
    public void save(Resume resume, OutputStream os) {
        try(ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(resume);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void read(InputStream is) {
        /*
        try(ObjectInputStream ois = new ObjectInputStream(is)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Error read resume", null, e);
        }

         */
    }
}
