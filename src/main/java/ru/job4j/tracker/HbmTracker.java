package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class HbmTracker implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Item add(Item item) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
            return item;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        } finally {
            close();
        }
    }

    @Override
    public boolean replace(int id, Item item) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(
                    "UPDATE Item SET name = :fName WHERE id = :fId")
                    .setParameter("fName", "new name")
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return false;
        } finally {
            close();
        }
    }

    @Override
    public void delete(int id) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(
                    "DELETE Item WHERE id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            close();
        }
    }

    @Override
    public List<Item> findAll() {
        Session session = sf.openSession();
        try {
            Query<Item> query = session.createQuery("FROM Item", Item.class);
            return query.getResultList().stream().toList();
        } catch (Exception e) {
            return null;
        } finally {
            close();
        }
    }

    @Override
    public List<Item> findByName(String key) {
        Session session = sf.openSession();
        try {
            Query<Item> query = session.createQuery("FROM Item WHERE name = :name")
                    .setParameter("name", key);
            return query.getResultList().stream().toList();
        } catch (Exception e) {
            return null;
        } finally {
            close();
        }
    }

    @Override
    public Item findById(int id) {
        Session session = sf.openSession();
        try {
            Query<Item> query = session.createQuery("FROM Item WHERE id = :id")
                    .setParameter("id", id);
            return query.uniqueResult();
        } catch (Exception e) {
            return null;
        } finally {
            close();
        }
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}