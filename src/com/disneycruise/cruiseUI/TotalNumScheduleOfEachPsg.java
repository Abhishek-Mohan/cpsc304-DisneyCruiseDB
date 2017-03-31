package com.disneycruise.cruiseUI;

import com.disneycruise.cruise.PassengerTableViews;

import java.awt.EventQueue;
import java.sql.*;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;

public class TotalNumScheduleOfEachPsg extends JFrame {
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TotalNumScheduleOfEachPsg frame = new TotalNumScheduleOfEachPsg();
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
	public TotalNumScheduleOfEachPsg() {
		setTitle("Total Number of Schedules of Each Passenger");
		setBounds(100, 100, 557, 555);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 15, 517, 484);


		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][]{
				},
				new String[]{
						"pid", "Passenger Name", "Total Number of Schedules"
				}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(165);
		table.getColumnModel().getColumn(2).setPreferredWidth(255);
		table.setFont(new Font("SimSun-ExtB", Font.PLAIN, 18));
		table.setBounds(565, 96, 33, 52);
		scrollPane.setViewportView(table);
		getContentPane().add(scrollPane);
		this.fillTable(new Object());
	}

	private void fillTable(Object o) {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);

		PassengerTableViews ptv = new PassengerTableViews();
		ResultSet rs = ptv.getTotalNumberofSchedulesView();

		try {
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getString("pid"));
				v.add(rs.getString("pname"));
				v.add(rs.getString("scheduleCount"));
				dtm.addRow(v);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
}
