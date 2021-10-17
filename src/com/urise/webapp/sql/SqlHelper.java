package com.urise.webapp.sql;

import com.urise.webapp.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public PreparedStatement getConnectionТ(String sql) {
        try {
            Connection conn = connectionFactory.getConnection();
            return conn.prepareStatement(sql);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    public void setString(PreparedStatement ps, int parameter, String value) {
        try {
            ps.setString(parameter, value);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    public void execute(PreparedStatement ps) {
        try {
            ps.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}
