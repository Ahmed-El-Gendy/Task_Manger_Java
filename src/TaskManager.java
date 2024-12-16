import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        tasks = FileManager.loadTasks();
    }

    public void addTask(String title, String description, LocalDate dueDate) {
        tasks.add(new Task(title, description, dueDate));
        FileManager.saveTasks(tasks);
    }

    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            FileManager.saveTasks(tasks);
        }
    }

    public void updateTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            System.out.println("Invalid task number.");
            return;
        }

        Task task = tasks.get(index);
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--- Update Task ---");
        System.out.println("1. Update Title");
        System.out.println("2. Update Description");
        System.out.println("3. Update Due Date");
        System.out.println("4. Update Status");
        System.out.print("Choose what to update (1-4): ");

        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }

        switch (choice) {
            case 1 -> {
                System.out.print("Enter new title: ");
                String newTitle = scanner.nextLine();
                task.setTitle(newTitle);
                System.out.println("Task title updated successfully.");
            }
            case 2 -> {
                System.out.print("Enter new description: ");
                String newDescription = scanner.nextLine();
                task.setDescription(newDescription);
                System.out.println("Task description updated successfully.");
            }
            case 3 -> {
                System.out.print("Enter new due date (YYYY-MM-DD): ");
                String dateInput = scanner.nextLine();
                try {
                    task.setDueDate(LocalDate.parse(dateInput));
                    System.out.println("Task due date updated successfully.");
                } catch (Exception e) {
                    System.out.println("Invalid date format. Task due date not updated.");
                }
            }
            case 4 -> {
                System.out.print("Is the task completed? (true/false): ");
                try {
                    boolean isCompleted = Boolean.parseBoolean(scanner.nextLine());
                    task.setCompleted(isCompleted);
                    System.out.println("Task status updated successfully.");
                } catch (Exception e) {
                    System.out.println("Invalid input. Task status not updated.");
                }
            }
            default -> System.out.println("Invalid choice. No changes made.");
        }

        // Save changes to file after update
        FileManager.saveTasks(tasks);
    }


    public void listTasks() {
        // Sort tasks: overdue tasks first, then pending tasks, then completed tasks.
        tasks.sort(Comparator.comparing(Task::isCompleted)
                .thenComparing(task -> task.getDueDate().isBefore(LocalDate.now()) ? 0 : 1)
                .thenComparing(Task::getDueDate));

        System.out.println("\n--- Task List ---");
        System.out.printf("%-5s %-20s %-30s %-15s %-10s%n", "No.", "Title", "Description", "Due Date", "Status");
        System.out.println("---------------------------------------------------------------------------");

        int counter = 1;
        for (Task task : tasks) {
            String statusSymbol;
            String dueDateColor = "\033[0m"; // Default color
            String statusColor = "\033[0m"; // Default color

            // Determine the task's status and colors
            if (task.isCompleted()) {
                statusSymbol = "✔"; // Completed task
                statusColor = "\033[32m"; // Green
            } else if (task.getDueDate().isBefore(LocalDate.now())) {
                statusSymbol = "☐"; // Overdue and incomplete
                dueDateColor = "\033[31m"; // Red for overdue
            } else {
                statusSymbol = "☐"; // Pending and on time
            }

            // Print task details with appropriate formatting
            System.out.printf("%-5d %-20s %-30s %s%-15s%s %s%-10s%s%n",
                    counter,
                    task.getTitle(),
                    task.getDescription(),
                    dueDateColor, task.getDueDate(), "\033[0m", // Due date color reset
                    statusColor, statusSymbol, "\033[0m" // Status color reset
            );
            counter++;
        }
    }
}
