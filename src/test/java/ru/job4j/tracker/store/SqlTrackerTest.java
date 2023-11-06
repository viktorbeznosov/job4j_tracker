package ru.job4j.tracker.store;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.SqlTracker;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.assertj.core.api.Assertions.*;

public class SqlTrackerTest {

    private static Connection connection;

    @BeforeAll
    public static void initConnection() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("db/liquibase_test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterAll
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @AfterEach
    public void wipeTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("delete from items;")) {
            statement.execute();
        }
        try (PreparedStatement statement = connection.prepareStatement("ALTER TABLE items ALTER COLUMN id RESTART WITH 1")) {
            statement.execute();
        }
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item(1, "item");
        tracker.add(item);
        assertThat(tracker.findById(item.getId()).getName()).isEqualTo(item.getName());
    }

    @Test
    public void whenShowAllItems() {
        SqlTracker tracker = new SqlTracker(connection);
        List<Item> items = new ArrayList<>();
        Item item = new Item(1, "item");
        Item item2 = new Item(2, "item2");
        items.add(item);
        items.add(item2);
        tracker.add(item);
        tracker.add(item2);
        assertThat(tracker.findAll().containsAll(items));
    }

    @Test
    public void whenEditItem() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item(1, "item");
        Item item2 = new Item("item2");
        tracker.add(item);
        tracker.replace(1, item2);
        assertThat(tracker.findById(item.getId()).getName()).isEqualTo(item2.getName());
    }

    @Test
    public void whenDeleteItemAndFindByDeletedNameIsEmpty() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item(1, "item");
        Item item2 = new Item(2, "item2");
        Item item3 = new Item(3, "item3");
        tracker.add(item);
        tracker.add(item2);
        tracker.add(item3);
        tracker.delete(1);
        assertThat(tracker.findById(1)).isNull();
        assertThat(tracker.findAll()).isNotEmpty();
    }

    @Test
    public void whenFindByName() {
        SqlTracker tracker = new SqlTracker(connection);
        List<Item> itemsToFind = new ArrayList<>();
        Item item = new Item(1, "item");
        Item item2 = new Item(2, "item2");
        Item newItem = new Item(3, "new");
        itemsToFind.add(item);
        itemsToFind.add(item2);
        tracker.add(item);
        tracker.add(item2);
        tracker.add(newItem);
        assertThat(tracker.findAll().containsAll(itemsToFind));
    }

}