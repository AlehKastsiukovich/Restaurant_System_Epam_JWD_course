package by.epam.javatraining.restaurant.dao;

public interface ModelDAO <T, Key> {

    void create(T t);

    T read(Key key);

    void update(T t);

    void delete(T t);
}
