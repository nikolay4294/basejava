package com.urise.webapp.exception;

public class NotExistStorageException extends StorageException {

    public NotExistStorageException(String uuid) {
        super("База переполнена, резюме " + uuid + " не добавлено", uuid);
    }
}
