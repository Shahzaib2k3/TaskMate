package Task;

import java.util.ArrayList;
import java.util.List;

public class taskmanager {
    private List<Task> tasks = new ArrayList<>();
    private int idCounter = 1;

    public void addTask(String title, String dueDate) {
        Task task = new Task(idCounter++, title, dueDate, false);
        tasks.add(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void markCompleted(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setCompleted(true);
                break;
            }
        }
    }

    public void deleteTask(int id) {
        tasks.removeIf(task -> task.getId() == id);
    }

    public Task getTaskByRow(int row) {
        if (row >= 0 && row < tasks.size()) {
            return tasks.get(row);
        }
        return null;
    }
}
