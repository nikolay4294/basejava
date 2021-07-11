package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;
    Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void executeSaveStrategy(Resume resume, OutputStream os) {
        strategy.save(resume, os);
    }

    public void executeReadStrategy(InputStream is) {
        strategy.read(is);
    }

    protected abstract void doWrite(Resume resume, OutputStream os) throws IOException;

    protected abstract Resume doRead(InputStream is) throws IOException;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    protected File findSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void doUpdate(Resume resume, File file) {
        try {
            doWrite(resume, new BufferedOutputStream(new FileOutputStream(file)));
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
            return doRead(new BufferedInputStream(new FileInputStream(file)));
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
