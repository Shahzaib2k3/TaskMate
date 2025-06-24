package Task;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class taskgui {

	private JFrame frame;
	private JTextField titleField;
	private JTextField dueDateField;
	private JTable table;
	private DefaultTableModel tableModel;
	private taskmanager manager = new taskmanager();

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				taskgui window = new taskgui();
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public taskgui() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame("🌟 Task Manager");
		frame.setBounds(100, 100, 600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(10, 10));

		// Top Panel: Input Form
		JPanel inputPanel = new JPanel();
		inputPanel.setBorder(new TitledBorder("Add New Task"));
		inputPanel.setLayout(new GridLayout(3, 2, 10, 10));
		inputPanel.setBackground(new Color(245, 245, 245));

		JLabel titleLabel = new JLabel("Task Title:");
		titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		inputPanel.add(titleLabel);

		titleField = new JTextField();
		inputPanel.add(titleField);

		JLabel dateLabel = new JLabel("Due Date:");
		dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		inputPanel.add(dateLabel);

		dueDateField = new JTextField();
		inputPanel.add(dueDateField);

		JButton addBtn = new JButton("➕ Add Task");
		JButton completeBtn = new JButton("✅ Mark Completed");
		JButton deleteBtn = new JButton("🗑️ Delete Task");

		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.setBackground(new Color(245, 245, 245));
		buttonPanel.add(addBtn);
		buttonPanel.add(completeBtn);
		buttonPanel.add(deleteBtn);

		JPanel topWrapper = new JPanel(new BorderLayout());
		topWrapper.add(inputPanel, BorderLayout.CENTER);
		topWrapper.add(buttonPanel, BorderLayout.SOUTH);

		frame.getContentPane().add(topWrapper, BorderLayout.NORTH);

	
		tableModel = new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Title", "Due Date", "Completed" });
		table = new JTable(tableModel);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		table.setRowHeight(22);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new TitledBorder("📋 Task List"));
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);


		addBtn.addActionListener(e -> {
			String title = titleField.getText().trim();
			String dueDate = dueDateField.getText().trim();
			if (title.isEmpty() || dueDate.isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Please enter both title and due date.");
				return;
			}
			manager.addTask(title, dueDate);
			refreshTable();
			titleField.setText("");
			dueDateField.setText("");
		});

		completeBtn.addActionListener(e -> {
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1) {
				Task task = manager.getTaskByRow(selectedRow);
				if (task != null) {
					manager.markCompleted(task.getId());
					refreshTable();
				}
			} else {
				JOptionPane.showMessageDialog(frame, "Please select a task.");
			}
		});

		deleteBtn.addActionListener(e -> {
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1) {
				Task task = manager.getTaskByRow(selectedRow);
				if (task != null) {
					manager.deleteTask(task.getId());
					refreshTable();
				}
			} else {
				JOptionPane.showMessageDialog(frame, "Please select a task.");
			}
		});
	}

	private void refreshTable() {
		tableModel.setRowCount(0);
		for (Task task : manager.getTasks()) {
			tableModel.addRow(new Object[] {
				task.getId(),
				task.getTitle(),
				task.getDueDate(), 
				task.isCompleted() ? "Yes" : "No"
			});
		}
	}
}
