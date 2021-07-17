package com.urise.webapp.storage.Strategy;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.AbstractStorage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {

    private static Path directory;
    private Strategy strategy;

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
            strategy.doWrite(resume, new BufferedOutputStream(Files.newOutputStream((path))));
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
        return createPathList().map(this::doGet).collect(Collectors.toList());
    }

    @Override
    protected boolean isResumeExist(Path path) {
        return Files.isRegularFile(path);
    }

    @Override
    public void clear() throws IOException {
        Files.list(directory).forEach(this::doDelete);
    }

    @Override
    public int size() {
        return (int) createPathList().map(this::doGet).count();
    }

    private static Stream<Path> createPathList() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("createPathList error", null);
        }
    }
}