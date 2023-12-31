package db;

import models.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {
    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/test-db",
                    "postgres",
                    "postgres"
            );
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM TASKS"
            );
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getLong("id"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setDeadlineDate(resultSet.getString("deadline_date"));
                tasks.add(task);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tasks;
    }

    /**
     * /** Потом Enter
     *
     * @param task
     */
    public static void addTask(Task task) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO TASKS(name, description, deadline_date) " +
                            "VALUES (?,?,?)"
            );
            statement.setString(1, task.getName());
            statement.setString(2, task.getDescription());
            statement.setString(3, task.getDeadlineDate());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Task getTaskById(Long id) {
        Task task = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM TASKS WHERE id = ?"
            );
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                task = new Task();
                task.setId(id);
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setDeadlineDate(resultSet.getString("deadline_date"));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return task;
    }

    public static void deleteTaskById(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM TASKS WHERE id = ?"
            );
            statement.setLong(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void editTask(Task task) {
        if(task == null)
            return;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE TASKS " +
                            "SET NAME = ?, DESCRIPTION = ?, deadline_date = ? WHERE id = ?"
            );
            statement.setString(1, task.getName());
            statement.setString(2, task.getDescription());
            statement.setString(3, task.getDeadlineDate());
            statement.setLong(4, task.getId());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
