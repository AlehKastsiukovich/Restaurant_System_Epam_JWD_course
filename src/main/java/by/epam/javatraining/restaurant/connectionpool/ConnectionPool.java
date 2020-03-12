package by.epam.javatraining.restaurant.connectionpool;

import com.mysql.cj.jdbc.Driver;
import org.apache.log4j.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    private static final Logger logger = Logger.getLogger(ConnectionPool.class);

    private static final int CONNECTION_POOL_CAPACITY = 10;
    private static final String DATABASE_PROPERTIES_FILE_NAME = "database.properties";
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

    public void initializeConnectionPool() {
        try {
            dbProperties.load(new FileInputStream(new File(getClass()
                    .getClassLoader()
                    .getResource(DATABASE_PROPERTIES_FILE_NAME).getPath())));

            String user = dbProperties.getProperty(DATABASE_PROPERTIES_USER);
            String password = dbProperties.getProperty(DATABASE_PROPERTIES_PASSWORD);
            String databaseUrl = dbProperties.getProperty(DATABASE_PROPERTIES_URL);

            DriverManager.registerDriver(new Driver());

            fillAvailableConnections(databaseUrl, user, password);

        } catch (SQLException | IOException e) {
            logger.error(e);
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = availableConnections.take();
            usedConnections.add(connection);
        } catch (InterruptedException e) {
            logger.warn("Can't get connection", e);
        }

        return connection;
    }

    public void retrieveConnection(Connection connection) {
        if (connection != null && usedConnections.remove(connection)) {
            try {
                availableConnections.put(connection);
            } catch (InterruptedException e) {
                logger.error("Can't retrieve", e);
            }
        }
    }

    public void closeAllPoolConnections() {
        closeAvailableConnections();
        closeUsedConnections();
    }

    private void closeAvailableConnections() {
        try {
            while (!availableConnections.isEmpty()) {
                availableConnections.take().close();
            }
        } catch (SQLException | InterruptedException e) {
            logger.error("Can't close available connections", e);
        }
    }

    private void closeUsedConnections() {
        try {
            for (Connection connection : usedConnections) {
                ProxyConnection proxyConnection = (ProxyConnection) connection;
                proxyConnection.forceClose();
            }
        } catch (SQLException e) {
            logger.error("Cant't close used connections", e);
        }
    }

    private void fillAvailableConnections(String url, String user, String password) throws SQLException {
        for (int i = 0; i < CONNECTION_POOL_CAPACITY; i++) {
            availableConnections.add(new ProxyConnection(DriverManager.getConnection(url, user, password)));
        }
    }
}
