package ru.geekbrains.online.chat.server;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {
    private static final Logger log = LogManager.getLogger(DbConnection.class);

    private Connection connection;
    private Statement stmt;

    public Statement getStmt() {
        return stmt;
    }

    public DbConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            this.stmt = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            log.throwing(Level.ERROR, e);
            throw new RuntimeException("Невозможно подключиться к базе данных");
        }
    }

    public void close() {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                log.throwing(Level.ERROR, e);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                log.throwing(Level.ERROR, e);
            }
        }
    }
}
