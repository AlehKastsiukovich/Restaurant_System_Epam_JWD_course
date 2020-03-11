package by.epam.javatraining.restaurant.connectionpool;

import org.apache.log4j.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    private static final Logger logger = Logger.getLogger(ConnectionPool.class);

    private static final int CONNECTION_POOL_CAPACITY = 10;
    private static final String DATABASE_PROPERTIES_FILE_NAME = "database.properties";
    private static final String DATABASE_PROPERTIES_DRIVER = "db.driver";
    private static final String DATABASE_PROPERTIES_USER = "db.user";
    private static final String DATABASE_PROPERTIES_PASSWORD = "db.password";
    private static final String DATABASE_PROPERTIES_URL = "db.url";

    private final BlockingQueue<Connection> availableConnections;
    private final List<Connection> usedConnections;
    private Properties dbProperties;

    private ConnectionPool() {
        availableConnections = new LinkedBlockingQueue<>();
        usedConnections = new ArrayList<>();
        dbProperties = new Properties();
    }

    private static class ConnectionPollHolder {
        private static final ConnectionPool INSTANCE = new ConnectionPool();
    }

    public static ConnectionPool getInstance() {
        return ConnectionPollHolder.INSTANCE;
    }

    public void buildConnectionPool() {
        try {
            dbProperties.load(new FileInputStream(new File(getClass()
                    .getClassLoader()
                    .getResource(DATABASE_PROPERTIES_FILE_NAME).getPath())));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = availableConnections.take();
            usedConnections.add(connection);
        } catch (InterruptedException e) {
            logger.warn(e);
        }

        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            try {
                if (availableConnections != null)
                    usedConnections.remove(connection);
                availableConnections.put(connection);
            } catch (InterruptedException e) {
                logger.error(e);
            }
        }
    }

}
