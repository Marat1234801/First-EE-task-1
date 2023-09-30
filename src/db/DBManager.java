package db;

import models.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DBManager {
    private static List<Task> tasks = new ArrayList<>();

    private static Long id = 1L;

    static {
        DBManager.addTask( new Task("Do homework",
                "I need to do my BITLAB homework",
                "2023-09-14"));
        DBManager.addTask( new Task("Go to University",
                "I will have exams",
                "2023-09-30"));
        DBManager.addTask( new Task("Diploma preparation",
                "I need to be ready for diploma",
                "2023-10-11"));
    }

    public static void addTask(Task task){
        task.setId(id);
        tasks.add(task);
        id++;
    }

    public static Task getTaskById(Long id){
        return tasks.stream()
                .filter(task -> Objects.equals(id, task.getId()))
                .findFirst()
                .orElse(null);
    }

    public static List<Task> getTasks(){
        return tasks;
    }

    public static void deleteTaskById(Long id){
        tasks.removeIf(task -> Objects.equals(id , task.getId()));
    }
}
