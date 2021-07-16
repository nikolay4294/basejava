package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {

    private Path directory;
    Strategy strategy;

    public PathStorage(Path directory, Strategy strategy) {
        this.directory = directory;
        this.strategy = strategy;
    }

    @Override
    protected Path findSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected void doUpdate(Resume resume, Path path) {
        try {
            //doWrite(resume, new BufferedOutputStream(Files.newOutputStream((path))));
            strategy.doWrite(resume, Files.newOutputStream((path)));
        } catch (IOException e) {
            throw new StorageException("Update resume is error", path.getFileName().toString());
        }
    }

    @Override
    protected void doSave(Resume resume, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Save resume error ", path.getFileName().toString());
        }
        doUpdate(resume, path);
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            //return doRead(new BufferedInputStream(Files.newInputStream(path)));
            return strategy.doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Resume get error", path.getFileName().toString());
        }
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Delete error", path.getFileName().toString());
        }
    }

    @Override
    protected List<Resume> doGetList() {
        try {
            return createPathList(directory).map(this::doGet).collect(Collectors.toList());
        } catch (StorageException e) {
            throw new StorageException("Get list error", null);
        }
    }

    @Override
    protected boolean isResumeExist(Path path) {
        return Files.isRegularFile(path);
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error ", null);
        }
    }

    @Override
    public int size() {
        if (createPathList(directory) == null) {
            throw new StorageException("Directory read  error", null);
        }
        return (int) createPathList(directory).map(this::doGet).count();
    }

    private static Stream<Path> createPathList(Path directory) {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("createPathList error", null);
        }
    }
}