package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.sql.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {

    public final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.getConnection("DELETE FROM resume");
    }

    @Override
    public void update(Resume r) {
        PreparedStatement ps = sqlHelper.getConnection("UPDATE resume SET full_name = ? WHERE uuid = ?");
        sqlHelper.setString(ps, 1, r.getFullName());
        sqlHelper.setString(ps, 2, r.getUuid());
    }

    @Override
    public void save(Resume r) {
        PreparedStatement ps = sqlHelper.getConnection("INSERT INTO resume (uuid, full_name) VALUES (?,?)");
        sqlHelper.setString(ps, 1, r.getUuid());
        sqlHelper.setString(ps, 2, r.getFullName());
        sqlHelper.execute(ps);
    }

    @Override
    public Resume get(String uuid) {
        PreparedStatement ps = sqlHelper.getConnection("SELECT * FROM resume r WHERE r.uuid = ?");
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
            PreparedStatement ps = sqlHelper.getConnection("DELETE FROM resume WHERE uuid = ?");
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
            PreparedStatement ps = sqlHelper.getConnection("SELECT * FROM resume GROUP BY full_name, uuid");
            ResultSet rs = sqlHelper.executeQuery(ps);
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
            PreparedStatement ps = sqlHelper.getConnection("SELECT * FROM resume GROUP BY full_name, uuid");
            ResultSet rs = sqlHelper.executeQuery(ps);
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}
