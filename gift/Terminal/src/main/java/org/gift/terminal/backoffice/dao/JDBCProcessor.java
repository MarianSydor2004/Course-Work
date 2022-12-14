package org.gift.terminal.backoffice.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCProcessor {
    private static final Logger LOG = LogManager.getLogger();

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASS = "123456";

    private Connection connection;

    public JDBCProcessor() {
        //TODO Set default settings => change later
        //TODO organize connection pool
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASS);
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            LOG.error("Cannot connect to database", ex);
        } catch (ClassNotFoundException ex) {
            LOG.error("Cannot load driver for database", ex);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                LOG.error("Cannot close database connection", ex);
            }
        }
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            LOG.error("Cannot get connection to database", ex);
        }
        return null;
    }
}
