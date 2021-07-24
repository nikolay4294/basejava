package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.Strategy.Serializer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {

    private static Path directory;
    private Serializer strategy;

    public PathStorage(Path directory, Serializer strategy) {
        Objects.requireNonNull(directory, "directory must no be null");
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
            throw new StorageException("Update resume is error", getFileName(path), e);
        }
    }

    @Override
    protected void doSave(Resume resume, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Save resume error ", getFileName(path), e);
        }
        doUpdate(resume, path);
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return strategy.doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Resume get error", getFileName(path), e);
        }
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Delete error", getFileName(path), e);
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
    public void clear() {
        createPathList().forEach(this::doDelete);
    }

    @Override
    public int size() {
        return (int) createPathList().map(this::doGet).count();
    }

    private static Stream<Path> createPathList() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("createPathList error", null, e);
        }
    }

    private String getFileName(Path path){
        return path.getFileName().toString();
    }
}