package com.disneycruise.cruiseUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class MaxNumEmployeePerDeptFrm extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MaxNumEmployeePerDeptFrm frame = new MaxNumEmployeePerDeptFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MaxNumEmployeePerDeptFrm() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Max Number of Employees Per Department");
		setBounds(100, 100, 769, 648);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 747, 577);


		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Department Name", "Max Number"
				}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(216);
		table.getColumnModel().getColumn(1).setPreferredWidth(173);
		table.setFont(new Font("SimSun-ExtB", Font.PLAIN, 18));
		table.setBounds(565, 96, 33, 52);
		scrollPane.setViewportView(table);
		getContentPane().add(scrollPane);
		this.fillTable(new Object());
	}

	private void fillTable(Object o) {
	}
}