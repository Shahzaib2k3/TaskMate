package Task;

public class Task {
    private int id;
    private String title;
    private String dueDate;
    private boolean completed;

    public Task(int id, String title, String dueDate, boolean completed) {
        this.id = id;
        this.title = title;
        this.dueDate = dueDate;
        this.completed = completed;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDueDate() { return dueDate; }
    public boolean isCompleted() { return completed; }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return id + " - " + title + " - " + dueDate + " - " + (completed ? "Completed" : "Pending");
    }
}
