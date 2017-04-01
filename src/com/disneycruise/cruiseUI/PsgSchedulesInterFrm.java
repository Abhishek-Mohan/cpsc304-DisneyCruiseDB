package com.disneycruise.cruiseUI;

import com.disneycruise.cruise.PassengerTableViews;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.Vector;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PsgSchedulesInterFrm extends JFrame {
	private JTable ps_table;
	private JTable rm_table;
	private JTextField pidSearch_textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PsgSchedulesInterFrm frame = new PsgSchedulesInterFrm();
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
	public PsgSchedulesInterFrm() {
//		setMaximizable(true);
		setTitle("Passenger Schedules");
		setBounds(10, 10, 1166, 921);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 50, 858, 370);


		ps_table = new JTable();
		ps_table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Schedule ID", "Passenger ID", "Entertainment ID", "Event Name", "Location", "Start Time", "End Time"
				}
		));
		ps_table.getColumnModel().getColumn(0).setPreferredWidth(118);
		ps_table.getColumnModel().getColumn(1).setPreferredWidth(122);
		ps_table.getColumnModel().getColumn(2).setPreferredWidth(159);
		ps_table.getColumnModel().getColumn(3).setPreferredWidth(125);
		ps_table.getColumnModel().getColumn(4).setPreferredWidth(105);
		ps_table.getColumnModel().getColumn(5).setPreferredWidth(125);
		ps_table.getColumnModel().getColumn(6).setPreferredWidth(129);
		ps_table.setBounds(73, 470, 524, 123);
		scrollPane.setViewportView(ps_table);
		getContentPane().add(scrollPane);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 481, 858, 370);
		getContentPane().add(scrollPane_1);

		rm_table = new JTable();
		rm_table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Schedule ID", "Passenger ID", "Entertainment ID", "Event Name", "Location", "Start Time", "End Time"
				}
		));
		rm_table.getColumnModel().getColumn(0).setPreferredWidth(115);
		rm_table.getColumnModel().getColumn(1).setPreferredWidth(128);
		rm_table.getColumnModel().getColumn(2).setPreferredWidth(161);
		rm_table.getColumnModel().getColumn(3).setPreferredWidth(115);
		rm_table.getColumnModel().getColumn(4).setPreferredWidth(105);
		rm_table.getColumnModel().getColumn(5).setPreferredWidth(105);
		rm_table.getColumnModel().getColumn(6).setPreferredWidth(105);
		scrollPane_1.setViewportView(rm_table);
		getContentPane().add(scrollPane_1);

		JLabel lblPassengersSchedule = new JLabel("Passenger's Schedule");
		lblPassengersSchedule.setFont(new Font("Arial", Font.PLAIN, 20));
		lblPassengersSchedule.setBounds(15, 14, 227, 21);
		getContentPane().add(lblPassengersSchedule);

		JLabel lblRoommatesSchedule = new JLabel("Roommate's Schedule");
		lblRoommatesSchedule.setFont(new Font("Arial", Font.PLAIN, 20));
		lblRoommatesSchedule.setBounds(15, 448, 227, 21);
		getContentPane().add(lblRoommatesSchedule);

		JLabel lblSearchScheduleBy = new JLabel("Search Schedule by pid:");
		lblSearchScheduleBy.setFont(new Font("Arial", Font.PLAIN, 18));
		lblSearchScheduleBy.setBounds(911, 60, 205, 21);
		getContentPane().add(lblSearchScheduleBy);

		pidSearch_textField = new JTextField();
		pidSearch_textField.setBounds(921, 91, 193, 27);
		getContentPane().add(pidSearch_textField);
		pidSearch_textField.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pid = pidSearch_textField.getText();
				/* replace this commented block
				 * with your query database code
				 * to find all schedules of specific pid which in variable pidSearch_textField
				 */

				fillPsgTable(new Object());
				fillRoommateTable(new Object(), pid);
			}
		});
		btnSearch.setFont(new Font("Arial", Font.PLAIN, 18));
		btnSearch.setBounds(954, 147, 123, 29);
		getContentPane().add(btnSearch);

		JButton btnCreateSchedule = new JButton("<html><font>Add Schedule</font><br><font> Content</font></html>");
		btnCreateSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!createSchOpen){
					createSchOpen=true;
					addPsgSchFrm= new AddPsgScheduleFrm();
					addPsgSchFrm.setVisible(true);
					addPsgSchFrm.setLocationRelativeTo(null);

				}else{
					createSchOpen=false;
					addPsgSchFrm.dispose();

				}
			}
		});
		btnCreateSchedule.setFont(new Font("Arial", Font.PLAIN, 18));
		btnCreateSchedule.setBounds(940, 328, 149, 61);
		getContentPane().add(btnCreateSchedule);

		JButton btnRemoveSchedule = new JButton("Remove");

		btnRemoveSchedule.setFont(new Font("Arial", Font.PLAIN, 18));
		btnRemoveSchedule.setBounds(954, 242, 123, 29);
		getContentPane().add(btnRemoveSchedule);

		btnRemoveSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!removeOpen){
					removeOpen=true;
					removeSch= new PassengerRemoveSch();
					removeSch.setVisible(true);
					removeSch.setLocationRelativeTo(null);

				}else{
					removeOpen=false;
					removeSch.dispose();

				}

				/* replace this commented block
				 * with your query database code
				 * to remove a schedule by sid which in variable rmSchedule_textField
				 */
			}
		});

		this.fillPsgTable(new Object());
	}

	private void fillPsgTable(Object o) {
		DefaultTableModel dtm = (DefaultTableModel) ps_table.getModel();
		dtm.setRowCount(0);
		String pid = pidSearch_textField.getText();

		PassengerTableViews ptv = new PassengerTableViews();
		ResultSet rs = ptv.getPassengerScheduleView(pid);
		try {

			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getString("sid"));
				v.add(rs.getString("pid"));
				v.add(rs.getString("eid"));
				v.add(rs.getString("ename"));
				v.add(rs.getString("eloc"));
				v.add(rs.getObject("sstime"));
				v.add(rs.getObject("setime"));
				dtm.addRow(v);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	private void fillRoommateTable(Object o, String roomMate) {
		DefaultTableModel dtm = (DefaultTableModel) rm_table.getModel();
		dtm.setRowCount(0);
		/*
		 * replace this commented block
		 * with your query database code
		 * to get all passenger data from database as variable rs
		*/
		PassengerTableViews ptv = new PassengerTableViews();
		ResultSet rs = ptv.getRoommateScheduleView(roomMate);
		try {

			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getString("sid"));
				v.add(rs.getString("pid"));
				v.add(rs.getString("eid"));
				v.add(rs.getString("ename"));
				v.add(rs.getString("eloc"));
				v.add(rs.getObject("sstime"));
				v.add(rs.getObject("setime"));
				dtm.addRow(v);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	private boolean createSchOpen=false, removeOpen=false;
	private AddPsgScheduleFrm addPsgSchFrm;
	private PassengerRemoveSch removeSch;
}
