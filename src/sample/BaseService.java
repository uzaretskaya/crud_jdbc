package sample;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class BaseService {

    private static Connection connection;
    private static Statement stmt;

    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:crud.db");
            stmt = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static User getFioAndDate(String fio) {
        String sql = String.format("SELECT fio, birthDate FROM Users\n" +
                "WHERE fio = '%s'", fio);
        try {
            ResultSet rs = stmt.executeQuery(sql);

            if(rs.next()) {
                String fio_db = rs.getString(1);
                Date date_db = rs.getDate(2);
                User user = new User(fio_db, date_db);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean deleteRecord(String fio){
        String sql = String.format("DELETE FROM Users\n" +
                "WHERE fio = '%s'", fio);
        try {
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean createRecord(String fio, Date date){
        String sql = String.format("INSERT INTO Users (\n" +
                "                      fio,\n" +
                "                      birthDate\n" +
                "                  )\n" +
                "                  VALUES (\n" +
                "                      '%s',\n" +
                "                      '%s'\n" +
                "                  );", fio, date);
        try {
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean updateRecord(String fio, String newFio){
        String sql = String.format("UPDATE Users\n" +
                "   SET fio = '%s'\n" +
                " WHERE fio = '%s';", newFio, fio);
        try {
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        String sql = String.format("SELECT fio, birthDate FROM Users");
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                String fio = rs.getString("fio");
                java.util.Date date = new java.util.Date();
                try {
                    date = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("birthDate"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                User user = new User(fio, date);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
