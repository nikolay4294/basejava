package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

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
        if (file.exists()) {
            try {
                doWrite(resume, file);
            } catch (IOException e) {
                throw new StorageException("Update error", file.getName(), e);
            }
        }
    }

    @Override
    protected void doSave(Resume resume, File file) {
        try {
            file.createNewFile();
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    protected abstract void doWrite(Resume resume, File file) throws IOException;
    protected abstract Resume doRead(File file);

    @Override
    protected Resume doGet(File file) {
        return doRead(file);
    }

    @Override
    protected void doDelete(File file) {
        file.delete();
    }

    @Override
    protected List<Resume> doGetList() {
        File[] file = directory.listFiles();
        assert file != null;
        List<Resume> list = new ArrayList<>(file.length);
        for(File f : file){
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
        if(directory.isDirectory()){
            for (File file : Objects.requireNonNull(directory.listFiles())){
                file.delete();
            }
        }
    }

    @Override
    public int size() {
        File[] file = directory.listFiles();
        if(file != null){
            return file.length;
        } else return 0;
    }
}
