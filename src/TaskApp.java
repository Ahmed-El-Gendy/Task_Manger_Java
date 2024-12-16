import java.time.LocalDate;
import java.util.Scanner;

public class TaskApp {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Task Manager App ===");
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Delete Task");
            System.out.println("4. Update Task");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    System.out.println("\n--- Add a New Task ---");
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter due date (YYYY-MM-DD): ");
                    String dateInput = scanner.nextLine();

                    try {
                        LocalDate dueDate = LocalDate.parse(dateInput);
                        taskManager.addTask(title, description, dueDate);
                        System.out.println("Task added successfully.");
                    } catch (Exception e) {
                        System.out.println("Invalid date format. Please try again.");
                    }
                }
                case 2 -> {
                    System.out.println("\n--- Task List ---");
                    taskManager.listTasks();
                }
                case 3 -> {
                    System.out.println("\n--- Delete a Task ---");
                    taskManager.listTasks();
                    System.out.print("Enter the task number to delete: ");

                    try {
                        int taskNumber = Integer.parseInt(scanner.nextLine());
                        taskManager.deleteTask(taskNumber - 1);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                    }
                }
                case 4 -> {
                    System.out.println("\n--- Update a Task ---");
                    taskManager.listTasks();
                    System.out.print("Enter the task number to update: ");

                    try {
                        int taskNumber = Integer.parseInt(scanner.nextLine()) - 1;
                        taskManager.updateTask(taskNumber);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid task number.");
                    } catch (Exception e) {
                        System.out.println("Error updating task: " + e.getMessage());
                    }
                }
                case 5 -> {
                    System.out.println("Exiting the app. Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
