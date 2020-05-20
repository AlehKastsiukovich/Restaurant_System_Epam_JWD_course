package by.epam.javatraining.restaurant.pool;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    private static final int CONNECTION_POOL_CAPACITY = 30;
    private static final String DATABASE_PROPERTIES_FILE_NAME = "database.properties";
    private static final String DATABASE_PROPERTIES_USER = "db.user";
    private static final String DATABASE_PROPERTIES_PASSWORD = "db.password";
    private static final String DATABASE_PROPERTIES_URL = "db.url";
    private static final String DATABASE_PROPERTIES_DRIVER = "db.driver";

    private final BlockingQueue<Connection> availableConnections;
    private final List<Connection> usedConnections;
    private final Properties dbProperties;
    private final AtomicBoolean isInitialized;
    private final AtomicBoolean isPoolClosing;
    private final Lock initLock;

    private ConnectionPool() {
        availableConnections = new LinkedBlockingQueue<>();
        usedConnections = new LinkedList<>();
        dbProperties = new Properties();
        isInitialized = new AtomicBoolean(false);
        isPoolClosing = new AtomicBoolean(false);
        initLock = new ReentrantLock();
    }

    private static class ConnectionPollHolder {
        private static final ConnectionPool INSTANCE = new ConnectionPool();
    }

    public static ConnectionPool getInstance() {
        return ConnectionPollHolder.INSTANCE;
    }

    public void initializeConnectionPool() {

            try {
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream(DATABASE_PROPERTIES_FILE_NAME);
                dbProperties.load(inputStream);

                String user = dbProperties.getProperty(DATABASE_PROPERTIES_USER);
                String password = dbProperties.getProperty(DATABASE_PROPERTIES_PASSWORD);
                String databaseUrl = dbProperties.getProperty(DATABASE_PROPERTIES_URL);
                String driver = dbProperties.getProperty(DATABASE_PROPERTIES_DRIVER);

                Class.forName(driver);

                fillAvailableConnections(databaseUrl, user, password);

            } catch (IOException | ClassNotFoundException e) {
                LOGGER.error(e);
            }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = availableConnections.take();
            usedConnections.add(connection);
        } catch (InterruptedException e) {
            LOGGER.warn("Can't get connection", e);
        }

        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            usedConnections.remove(connection);
            availableConnections.add(connection);
        } else {
            LOGGER.warn("Cant release null connection");
        }
    }


    public void closeAllPoolConnections() throws SQLException, InterruptedException {
        closeAvailableConnections();
        closeUsedConnections();
    }

    private void closeAvailableConnections() throws SQLException, InterruptedException {
        while (!availableConnections.isEmpty()) {
            availableConnections.take().close();
        }
    }

    private void closeUsedConnections() {
        try {
            for (Connection connection : usedConnections) {
                ProxyConnection proxyConnection = (ProxyConnection) connection;
                proxyConnection.forceClose();
            }
        } catch (SQLException e) {
            LOGGER.error("Cant't close used connections", e);
        }
    }

    private void fillAvailableConnections(String url, String user, String password) {
        for (int i = 0; i < CONNECTION_POOL_CAPACITY; i++) {
            try {
                availableConnections.add(new ProxyConnection(DriverManager.getConnection(url, user, password)));
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
    }
}
