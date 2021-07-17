package com.urise.webapp.storage.Strategy;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.AbstractStorage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileStorage extends AbstractStorage<File> {
    private static File directory;
    private Strategy strategy;

    public FileStorage(File directory, Strategy strategy) {
        this.directory = directory;
        this.strategy = strategy;
    }

    @Override
    protected File findSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void doUpdate(Resume resume, File file) {
        try {
            strategy.doWrite(resume, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Update error", file.getName(), e);
        }
    }

    @Override
    protected void doSave(Resume resume, File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
        doUpdate(resume, file);
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return strategy.doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File delete error", file.getName());
        }
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("File delete error", file.getName());
        }
    }

    @Override
    protected List<Resume> doGetList() {
        List<Resume> list = new ArrayList<>(createFilesList().length);
        for (File f : createFilesList()) {
            list.add(doGet(f));
        }
        return list;
    }

    @Override
    protected boolean isResumeExist(File file) {
        return file.exists();
    }

    @Override
    public void clear() {
        for (File file : createFilesList()) {
            file.delete();
        }
    }

    @Override
    public int size() {
        return createFilesList().length;
    }

    private static File[] createFilesList() {
        try {
            return directory.listFiles();
        } catch (StorageException e) {
            throw new StorageException("createFileList error", null);
        }
    }
}