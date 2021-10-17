package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.sql.SqlHelper;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {

    public final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        PreparedStatement ps = sqlHelper.getConnectionТ("DELETE FROM resume");
        sqlHelper.execute(ps);
    }

    @Override
    public void update(Resume r) {
        PreparedStatement ps = sqlHelper.getConnectionТ("UPDATE resume SET full_name = ? WHERE uuid = ?");
        sqlHelper.setString(ps, 1, r.getFullName());
        sqlHelper.setString(ps, 2, r.getUuid());
        try {
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(r.getUuid());
            }
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void save(Resume r) {
        try {
            PreparedStatement ps = sqlHelper.getConnectionТ("INSERT INTO resume (uuid, full_name) VALUES (?,?)");
            sqlHelper.setString(ps, 1, r.getUuid());
            sqlHelper.setString(ps, 2, r.getFullName());
            sqlHelper.execute(ps);
        } catch (Exception e) {
            throw new ExistStorageException(r.getUuid());
        }
    }

    @Override
    public Resume get(String uuid) {
        PreparedStatement ps = sqlHelper.getConnectionТ("SELECT * FROM resume r WHERE r.uuid = ?");
        sqlHelper.setString(ps, 1, uuid);
        try {
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            Resume r = new Resume(uuid, rs.getString("full_name"));
            return r;
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void delete(String uuid) {
        try {
            PreparedStatement ps = sqlHelper.getConnectionТ("DELETE FROM resume WHERE uuid = ?");
            sqlHelper.setString(ps, 1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public List<Resume> getAllSorted() {
        try {
            PreparedStatement ps = sqlHelper.getConnectionТ("SELECT * FROM resume r ORDER BY uuid, full_name");
            ResultSet rs = ps.executeQuery();
            List<Resume> resumes = new ArrayList<>();
            while (rs.next()) {
                resumes.add(new Resume(rs.getString("uuid"), rs.getString("full_name")));
            }
            return resumes;
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public int size() {
        try {
            PreparedStatement ps = sqlHelper.getConnectionТ("SELECT count(*) FROM resume");
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}