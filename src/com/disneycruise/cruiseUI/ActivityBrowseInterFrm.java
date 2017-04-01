package com.disneycruise.cruiseUI;

import com.disneycruise.cruise.PassengerTableViews;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ActivityBrowseInterFrm extends JFrame {
	private JTable act_table;
	private JTextField type_textField;
	private JTextField date_textField;
	private JTextField startTime_textField;
	private JTextField endTime_textField;
	private JTextField location_textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ActivityBrowseInterFrm frame = new ActivityBrowseInterFrm();
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
	public ActivityBrowseInterFrm() {

		setTitle("Activity Browser");
		setBounds(10, 10, 1165, 930);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 15, 901, 859);


		act_table = new JTable();
		act_table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Entertainment ID", "Type", "Name", "Location", "Seats", "Date", "Start", "End"
				}
		));
		act_table.getColumnModel().getColumn(0).setPreferredWidth(156);
		act_table.getColumnModel().getColumn(3).setPreferredWidth(105);
		act_table.getColumnModel().getColumn(5).setPreferredWidth(96);
		act_table.getColumnModel().getColumn(6).setPreferredWidth(139);
		act_table.getColumnModel().getColumn(7).setPreferredWidth(140);
		act_table.setFont(new Font("SimSun-ExtB", Font.PLAIN, 18));
		act_table.setBounds(697, 164, 34, 179);
		scrollPane.setViewportView(act_table);
		getContentPane().add(scrollPane);

		JLabel lblType = new JLabel("Type:");
		lblType.setFont(new Font("Arial", Font.PLAIN, 18));
		lblType.setBounds(955, 40, 56, 21);
		getContentPane().add(lblType);

		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Arial", Font.PLAIN, 18));
		lblDate.setBounds(955, 118, 56, 21);
		getContentPane().add(lblDate);

		JLabel lblStartTime = new JLabel("Start Time:");
		lblStartTime.setFont(new Font("Arial", Font.PLAIN, 18));
		lblStartTime.setBounds(955, 196, 88, 21);
		getContentPane().add(lblStartTime);

		JLabel lblLocation = new JLabel("Location:");
		lblLocation.setFont(new Font("Arial", Font.PLAIN, 18));
		lblLocation.setBounds(955, 348, 88, 21);
		getContentPane().add(lblLocation);

		JLabel lblEndTime = new JLabel("End Time:");
		lblEndTime.setFont(new Font("Arial", Font.PLAIN, 18));
		lblEndTime.setBounds(955, 274, 88, 21);
		getContentPane().add(lblEndTime);

		type_textField = new JTextField();
		type_textField.setBounds(945, 76, 183, 27);
		getContentPane().add(type_textField);
		type_textField.setColumns(10);

		date_textField = new JTextField();
		date_textField.setColumns(10);
		date_textField.setBounds(945, 154, 183, 27);
		getContentPane().add(date_textField);

		startTime_textField = new JTextField();
		startTime_textField.setColumns(10);
		startTime_textField.setBounds(945, 232, 183, 27);
		getContentPane().add(startTime_textField);

		endTime_textField = new JTextField();
		endTime_textField.setColumns(10);
		endTime_textField.setBounds(945, 297, 183, 27);
		getContentPane().add(endTime_textField);

		location_textField = new JTextField();
		location_textField.setColumns(10);
		location_textField.setBounds(945, 379, 183, 27);
		getContentPane().add(location_textField);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* replace this commented block
				 * with your query database code
				 * to search activity by give information, if no information then return all activity
				 */

				String startTime = startTime_textField.getText();
				String endTime = endTime_textField.getText();
				String location = location_textField.getText();
				String date = date_textField.getText();
				String type = type_textField.getText();


				fillTable(new Object(), false);
			}
		});
		btnSearch.setFont(new Font("Arial", Font.PLAIN, 18));
		btnSearch.setBounds(974, 448, 123, 29);
		getContentPane().add(btnSearch);

		JButton btnPopularActivity = new JButton("Popular Activity");
		btnPopularActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* replace this commented block
				 * with your query database code
				 * to find a/some activity(s) which is in every passenger's schedule
				 */
				String startTime = startTime_textField.getText();
				String endTime = endTime_textField.getText();
				String location = location_textField.getText();
				String date = date_textField.getText();
				String type = type_textField.getText();



				fillTable(new Object(), true);
			}
		});
		btnPopularActivity.setFont(new Font("Arial", Font.PLAIN, 18));
		btnPopularActivity.setBounds(945, 516, 183, 29);
		getContentPane().add(btnPopularActivity);
		this.fillTable(new Object(), false);


	}

	private void fillTable(Object o, boolean isPopularPressed) {
		DefaultTableModel dtm = (DefaultTableModel) act_table.getModel();
		dtm.setRowCount(0);
		/*
		 * replace this commented block
		 * with your query database code 
		 * to get all passenger data from database as variable rs
		*/
		String startTime = startTime_textField.getText();
		String endTime = endTime_textField.getText();
		String location = location_textField.getText();
		String date = date_textField.getText();
		String type = type_textField.getText();
		PassengerTableViews	pv = new PassengerTableViews();
		ResultSet rs;
		if (!isPopularPressed) {
			rs = pv.getBrowseActivityTableView(type, location, startTime, endTime, date);
		} else {
			rs = pv.getPopularActivityTables();
		}
		try {
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getString("eid"));
				v.add("-");
				v.add(rs.getString("ename"));
				v.add(rs.getString("eloc"));
				v.add("-");
				v.add(rs.getDate("edate"));
				v.add(rs.getObject("en_stime"));
				v.add(rs.getObject("en_etime"));

				dtm.addRow(v);
			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();

		}

	}

}
