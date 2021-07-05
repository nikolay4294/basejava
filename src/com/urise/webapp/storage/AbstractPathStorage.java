package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private Path directory;

    protected AbstractPathStorage() {
    }

    protected abstract void doWrite(Resume resume, OutputStream os) throws IOException;

    protected abstract Resume doRead(InputStream is) throws IOException;

    protected AbstractPathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
    }

        @Override
        protected Path findSearchKey (String uuid){
            return Paths.get(uuid);
            //return new Path(directory, uuid);
        }

        @Override
        protected void doUpdate (Resume resume, Path path){
            try {
                Files.list(directory).
                        filter(val -> val == path).
                        forEach((Consumer<? super Path>) Files.createFile(path));
            } catch (IOException e) {
                throw new StorageException("Update resume is error", path.getFileName().toString());
            }

        }

        @Override
        protected void doSave (Resume resume, Path path){
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new StorageException("Save resume error ", path.toString());
            }
        }

        @Override
        protected Resume doGet (Path path){
            try {
                Files.list(directory).
                        filter(val -> val == path).
                        forEach(this::doDelete);
            } catch (IOException e) {
                throw new StorageException("Path delete error", path.toString());
            }
            return null;
        }

        @Override
        protected void doDelete (Path path){
            try {
                Files.delete(path);
            } catch (IOException e) {
                throw new StorageException("Path delete error", path.toString());
            }
        }

        @Override
        protected List<Resume> doGetList () {
            try {
                 Files.list(directory).
                        map(this::doGet).
                        collect(Collectors.toList());
            } catch (IOException e) {
                throw new StorageException("Get List error", e.toString());
            }
            return null;
        }

        @Override
        protected boolean isResumeExist (Path path){
            return Files.exists(path);
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
        public int size () {
            try {
                //return (int) Files.size(directory);
                Files.list(directory).count();
            } catch (IOException e) {
                throw new StorageException("Directory read  error", null);
            }
            return 0;
        }
    }