# Task Manager GUI

A **Task Manager** application built in **Java** using **Swing** for the graphical user interface. This application allows users to manage tasks by adding, updating, deleting, and displaying them in a table view with colored indicators for task statuses.

## Team Members
- **Ahmed El Gendy**
- **Saged Rayan**
- **Eslam Kamel**

---

## Project Features

### 1. Task Management
- Add new tasks with a title, description, due date, and status (completed or not).
- Update individual fields of an existing task (e.g., title, description, due date, or status).
- Delete tasks.

### 2. Task Status Highlighting
- **Green:** Completed tasks.
- **Red:** Overdue tasks.
- **Default (Black):** Pending tasks that are not overdue.

### 3. Table View
- Displays tasks in a structured table format using `JTable`.
- Columns include:
  - **Title**
  - **Description**
  - **Due Date**
  - **Status**

### 4. Persistent Data Storage
- Tasks are saved in memory while the application is running.
- Optionally, future iterations can include file storage or database integration for persistence.

---

## Technologies Used

1. **Java**: Programming language used to build the application.
2. **Swing**: For creating the graphical user interface (GUI).
3. **Maven (Optional)**: For managing dependencies (if extended).

---

## Prerequisites

Before running the project, ensure the following:

1. **Java Development Kit (JDK):** Installed JDK 8 or higher.
   - [Download JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
2. **IDE:** Use an IDE like IntelliJ IDEA, Eclipse, or NetBeans for easier development and execution.

---

## Steps to Run the Project

### 1. Clone the Repository
```bash
git clone https://github.com/your-username/task-manager-gui.git
cd task-manager-gui
```

### 2. Open the Project in Your IDE
1. Open your preferred IDE (e.g., IntelliJ IDEA, Eclipse).
2. Import the project as a Java project.
3. Make sure the JDK is properly configured.

### 3. Run the Project (Two Ways)

#### **Option 1: Run the GUI**
- Locate the `TaskManagerGUI.java` file in your IDE.
- Run the `TaskManagerGUI` class to open the GUI interface.

#### **Option 2: Run in Terminal**
- Locate the `TaskApp.java` file, which provides a terminal-based interface.
- Compile the project from the terminal:
  ```bash
  javac src/TaskApp.java
  java -cp src TaskApp
  ```
- Use the terminal-based menu to add, update, and delete tasks.

---

## Project Structure

```
project-directory/
|
|-- src/
|   |-- Task.java              # Task class for task objects
|   |-- TaskManagerGUI.java    # Main GUI class
|   |-- TaskApp.java           # Terminal-based interface class
|
|-- README.md                  # Documentation (this file)
|-- .gitignore                 # Git ignore file
```

---

## Screenshots

### Main Interface
A clean, user-friendly interface displaying tasks in a table:
![Task Manager Main Interface](https://via.placeholder.com/800x400?text=Task+Manager+GUI+Main+Interface)


---

## Future Enhancements
- **Data Persistence:** Save tasks to a file or database to retain data between sessions.
- **Advanced Filters:** Allow users to filter tasks by status (e.g., show only overdue tasks).
- **Search Feature:** Add a search bar to quickly locate specific tasks.
- **Export/Import:** Enable users to export tasks to CSV or import tasks from a file.

---

## Contributors
- **Ahmed El Gendy**
- **Saged Rayan**
- **Eslam Kamel**
