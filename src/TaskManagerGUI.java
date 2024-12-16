import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskManagerGUI {
    private JFrame frame;
    private JTable taskTable;
    private DefaultTableModel tableModel;
    private List<Task> tasks;

    public TaskManagerGUI() {
        tasks = new ArrayList<>();
        initialize();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TaskManagerGUI::new);
    }

    private void initialize() {
        frame = new JFrame("Task Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setLayout(new BorderLayout());

        // Task Table
        String[] columnNames = {"Title", "Description", "Due Date", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        taskTable = new JTable(tableModel);
        taskTable.setRowHeight(30);

        // Color rows based on task status
        taskTable.setDefaultRenderer(Object.class, new TaskTableCellRenderer());

        JScrollPane scrollPane = new JScrollPane(taskTable);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        JButton addTaskButton = new JButton("Add Task");
        JButton updateTaskButton = new JButton("Update Task");
        JButton deleteTaskButton = new JButton("Delete Task");

        addTaskButton.addActionListener(this::addTask);
        updateTaskButton.addActionListener(this::updateTask);
        deleteTaskButton.addActionListener(this::deleteTask);

        buttonPanel.add(addTaskButton);
        buttonPanel.add(updateTaskButton);
        buttonPanel.add(deleteTaskButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void addTask(ActionEvent e) {
        JTextField titleField = new JTextField();
        JTextField descriptionField = new JTextField();
        JTextField dueDateField = new JTextField("YYYY-MM-DD");
        JCheckBox statusCheckBox = new JCheckBox("Completed");

        Object[] fields = {
                "Title:", titleField,
                "Description:", descriptionField,
                "Due Date:", dueDateField,
                //"Completed:", statusCheckBox
        };

        int option = JOptionPane.showConfirmDialog(frame, fields, "Add Task", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String title = titleField.getText();
                String description = descriptionField.getText();
                LocalDate dueDate = LocalDate.parse(dueDateField.getText());
                //boolean isCompleted = statusCheckBox.isSelected();

                Task task = new Task(title, description, dueDate);
                tasks.add(task);
                updateTable();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please try again.");
            }
        }
    }

    private void updateTask(ActionEvent e) {
        int selectedRow = taskTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a task to update.");
            return;
        }

        Task task = tasks.get(selectedRow);

        JTextField titleField = new JTextField(task.getTitle());
        JTextField descriptionField = new JTextField(task.getDescription());
        JTextField dueDateField = new JTextField(task.getDueDate().toString());
        JCheckBox statusCheckBox = new JCheckBox("Completed", task.isCompleted());

        Object[] fields = {
                "Title:", titleField,
                "Description:", descriptionField,
                "Due Date:", dueDateField,
                "Completed:", statusCheckBox
        };

        int option = JOptionPane.showConfirmDialog(frame, fields, "Update Task", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                task.setTitle(titleField.getText());
                task.setDescription(descriptionField.getText());
                task.setDueDate(LocalDate.parse(dueDateField.getText()));
                task.setCompleted(statusCheckBox.isSelected());
                updateTable();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please try again.");
            }
        }
    }

    private void deleteTask(ActionEvent e) {
        int selectedRow = taskTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a task to delete.");
            return;
        }

        tasks.remove(selectedRow);
        updateTable();
    }

    private void updateTable() {
        tableModel.setRowCount(0); // Clear the table
        for (Task task : tasks) {
            tableModel.addRow(new Object[]{
                    task.getTitle(),
                    task.getDescription(),
                    task.getDueDate(),
                    task.isCompleted() ? "✔ Completed" : "☐ Pending"
            });
        }
    }

    static class TaskTableCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            String status = table.getValueAt(row, 3).toString();
            LocalDate dueDate = LocalDate.parse(table.getValueAt(row, 2).toString());
            boolean isCompleted = status.contains("✔");

            if (isCompleted) {
                c.setForeground(Color.GREEN);
            } else if (dueDate.isBefore(LocalDate.now())) {
                c.setForeground(Color.RED);
            } else {
                c.setForeground(Color.BLACK);
            }

            return c;
        }
    }
}
