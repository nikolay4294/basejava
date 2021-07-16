package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileStorage extends AbstractStorage<File> {
    private File directory;
    Strategy strategy;

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
            //doWrite(resume, new BufferedOutputStream(new FileOutputStream(file)));
            strategy.doWrite(resume, new BufferedOutputStream(new FileOutputStream(file)));
            //fileStorage.strategy.doWrite();
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
            //return doRead(new BufferedInputStream(new FileInputStream(file)));
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
        try {
            List<Resume> list = new ArrayList<>(createFilesList(directory).length);
            for (File f : createFilesList(directory)) {
                list.add(doGet(f));
            }
            return list;
        } catch (StorageException e) {
            throw new StorageException("File not found", null);
        }
    }

    @Override
    protected boolean isResumeExist(File file) {
        return file.exists();
    }

    @Override
    public void clear() {
        if (createFilesList(directory) == null) {
            throw new StorageException("File not delete", null);
        }
        for (File file : createFilesList(directory)) {
            file.delete();
        }
    }

    @Override
    public int size() {
        if (createFilesList(directory) == null) {
            throw new StorageException("Directory read  error", null);
        }
        return createFilesList(directory).length;
    }

    private static File[] createFilesList(File directory) {
        return directory.listFiles();
    }
}