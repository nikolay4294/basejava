package com.urise.webapp.sql;

import com.urise.webapp.exception.StorageException;

import java.sql.*;

public class SqlHelper {
    ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public PreparedStatement getConnection(String sql) {
        try {
            Connection conn = connectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            return ps;
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

    public ResultSet executeQuery(PreparedStatement ps) {
        try {
            return ps.executeQuery();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}
