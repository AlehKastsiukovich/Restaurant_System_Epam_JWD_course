package by.epam.javatraining.restaurant.runner;

import by.epam.javatraining.restaurant.builder.PositionBuilder;
import by.epam.javatraining.restaurant.entity.Order;
import by.epam.javatraining.restaurant.entity.Position;
import by.epam.javatraining.restaurant.exception.ServiceException;
import by.epam.javatraining.restaurant.factory.ServiceFactory;
import by.epam.javatraining.restaurant.pool.ConnectionPool;
import by.epam.javatraining.restaurant.service.OrderService;
import by.epam.javatraining.restaurant.service.PositionService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Runner {

    public static void main(String[] args) {
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.initializeConnectionPool();

        PositionService service = ServiceFactory.INSTANCE.getPositionService();
        Position position = null;
        try {
            position = service.getPositionById(5);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        System.out.println(position.toString());
        System.out.println(position.getPositionImage());
//        String query = "update positions set  pos_picture = (?) where item_id = (?)";
//        try (PreparedStatement statement = pool.getConnection().prepareStatement(query)) {
//            statement.setBinaryStream(1, new FileInputStream(new File("C:/Users/AlehKastsiukovich/Desktop/navi.jpg")));
//            statement.setInt(2, 5);
//            statement.executeUpdate();
//        } catch (SQLException | FileNotFoundException e) {
//            e.printStackTrace();
//        }

//        String getQuery = "select pos_picture from positions where item_id = 5";
//        try (PreparedStatement preparedStatement = pool.getConnection().prepareStatement(getQuery)) {
//            try (ResultSet set = preparedStatement.executeQuery()) {
//                while (set.next()) {
//                    Blob blob = set.getBlob("pos_picture");
//                    byte[] bytes = blob.getBytes(1,(int)blob.length());
//                    BufferedImage image = null;
//                    image = ImageIO.read(new ByteArrayInputStream(bytes));
//                    System.out.println(image.getType());
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
