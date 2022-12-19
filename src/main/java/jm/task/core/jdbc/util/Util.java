package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    public static Connection getDbConnectionJDBC() throws ClassNotFoundException, SQLException {
        Connection dbConnection;
        String connectionString = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "forexample";
        dbConnection = DriverManager.getConnection(connectionString, "root", "Bsps1900/.");
        return dbConnection;
    }
    public static SessionFactory getDbConnectionHibernate() {
        Configuration configuration = new Configuration()
                .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/forexample?useSSL=false&allowMultiQueries=true&serverTimezone=UTC")
                .setProperty("hibernate.connection.username", "root")
                .setProperty("hibernate.connection.password", "Bsps1900/.")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                .addAnnotatedClass(User.class);
        try {
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            return sessionFactory;
        } catch (HibernateException e) {
            System.out.println("Не удалось установить соединение с базой данных");
            return null;
        }

    }
}
