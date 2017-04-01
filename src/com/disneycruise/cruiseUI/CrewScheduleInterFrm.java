package com.disneycruise.cruiseUI;

import com.disneycruise.cruise.CrewTableViews;
import com.disneycruise.cruise.ManagerTableViews;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Vector;

public class CrewScheduleInterFrm extends JFrame {
	private JTable cleanSch_table;
	private JTable entertainmentSch_table;
	private JTable passengerName_table;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JLabel lblEntertainmentSchedule;
	private JLabel lblPassengerName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//CrewScheduleInterFrm frame = new CrewScheduleInterFrm();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CrewScheduleInterFrm(String input, boolean isWorkPlaceQuery, boolean isCrewIDQuery) {
//		setMaximizable(true);
		setTitle("Crew Schedule");
		setBounds(10, 0, 949, 980);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 38, 897, 265);


		cleanSch_table = new JTable();
		cleanSch_table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Crew Name", "Clean Schedule ID", "Start Time", "End Time", "Manager ID"
				}
		));
		cleanSch_table.getColumnModel().getColumn(0).setPreferredWidth(115);
		cleanSch_table.getColumnModel().getColumn(1).setPreferredWidth(179);
		cleanSch_table.getColumnModel().getColumn(2).setPreferredWidth(139);
		cleanSch_table.getColumnModel().getColumn(3).setPreferredWidth(142);
		cleanSch_table.getColumnModel().getColumn(4).setPreferredWidth(108);
		cleanSch_table.setFont(new Font("SimSun-ExtB", Font.PLAIN, 18));
		cleanSch_table.setBounds(77, 500, 114, 29);
		scrollPane.setViewportView(cleanSch_table);
		getContentPane().add(scrollPane);

		JLabel lblCleanSchedule = new JLabel("Clean Schedule:");
		lblCleanSchedule.setFont(new Font("Arial", Font.PLAIN, 18));
		lblCleanSchedule.setBounds(30, 15, 187, 21);
		getContentPane().add(lblCleanSchedule);

		entertainmentSch_table = new JTable();
		entertainmentSch_table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Crew Name", "Entertainment Schedule ID", "Entertainment ID", "Start Time", "End Time", "Manager ID"
				}
		));
		entertainmentSch_table.getColumnModel().getColumn(0).setPreferredWidth(115);
		entertainmentSch_table.getColumnModel().getColumn(1).setPreferredWidth(236);
		entertainmentSch_table.getColumnModel().getColumn(2).setPreferredWidth(154);
		entertainmentSch_table.getColumnModel().getColumn(3).setPreferredWidth(133);
		entertainmentSch_table.getColumnModel().getColumn(4).setPreferredWidth(127);
		entertainmentSch_table.getColumnModel().getColumn(5).setPreferredWidth(104);
		entertainmentSch_table.setFont(new Font("SimSun-ExtB", Font.PLAIN, 18));
		entertainmentSch_table.setBounds(52, 116, 161, 21);
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(15, 364, 897, 265);
		scrollPane_1.setViewportView(entertainmentSch_table);
		getContentPane().add(scrollPane_1);


		passengerName_table = new JTable();
		passengerName_table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Passenger Name", "Cabin ID"
				}
		));
		passengerName_table.getColumnModel().getColumn(0).setPreferredWidth(165);
		passengerName_table.getColumnModel().getColumn(1).setPreferredWidth(89);
		passengerName_table.setFont(new Font("SimSun-ExtB", Font.PLAIN, 18));
		passengerName_table.setBounds(752, 387, 16, 21);
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(15, 695, 897, 229);
		scrollPane_2.setViewportView(passengerName_table);
		getContentPane().add(scrollPane_2);

		lblEntertainmentSchedule = new JLabel("Entertainment Schedule:");
		lblEntertainmentSchedule.setFont(new Font("Arial", Font.PLAIN, 18));
		lblEntertainmentSchedule.setBounds(30, 338, 229, 21);
		getContentPane().add(lblEntertainmentSchedule);

		lblPassengerName = new JLabel("Passenger Name in the cabin that crew works in:");
		lblPassengerName.setFont(new Font("Arial", Font.PLAIN, 18));
		lblPassengerName.setBounds(30, 671, 399, 21);
		getContentPane().add(lblPassengerName);

		this.fillCleanTable(new Object(), input, isWorkPlaceQuery, isCrewIDQuery);
		this.fillEntertainmentTable(new Object(), input, isWorkPlaceQuery, isCrewIDQuery);
		this.fillPsgTable(new Object());
	}

	private void fillCleanTable(Object o, String input, boolean isWorkPlaceQuery, boolean isCrewIDQuery) {
		DefaultTableModel dtm = (DefaultTableModel) cleanSch_table.getModel();
		dtm.setRowCount(0);

		CrewTableViews ctv = new CrewTableViews();
		ManagerTableViews mtv = new ManagerTableViews();

		ResultSet rs = null;

		if (isWorkPlaceQuery) {
			rs = ctv.getCrewCleanScheduleByDepartment(input);
		}
		if (isCrewIDQuery) {
			rs = ctv.getCrewCleanScheduleByCrewID(input);
		}

		try {
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getString("cname"));
				v.add(rs.getString("csid"));
				v.add(rs.getObject("cs_stime"));
				v.add(rs.getObject("cs_etime"));
				v.add(rs.getString("man_id"));

				dtm.addRow(v);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}

	}

	private void fillEntertainmentTable(Object o, String input, boolean isWorkPlaceQuery, boolean isCrewIDQuery) {
		DefaultTableModel dtm = (DefaultTableModel) entertainmentSch_table.getModel();
		dtm.setRowCount(0);

		CrewTableViews ctv = new CrewTableViews();
		ResultSet rs = null;
		if (isWorkPlaceQuery) {
			rs = ctv.getCrewEntertainmentScheduleByDepartment(input);
		}
		if (isCrewIDQuery) {
			rs = ctv.getCrewEntertainmentScheduleByCrewID(input);
		}

		try {
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getString("cname"));
				v.add(rs.getString("esid"));
				v.add(rs.getString("eid"));
				v.add(rs.getObject("es_stime"));
				v.add(rs.getObject("es_etime"));
				v.add(rs.getString("man_id"));

				dtm.addRow(v);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	private void fillPsgTable(Object o) {
		DefaultTableModel dtm = (DefaultTableModel) passengerName_table.getModel();
		dtm.setRowCount(0);
		/*
		 * replace this commented block
		 * with your query database code 
		 * to get passenger name if the crewid work in cabin from database as variable rs
			
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getString("passengerName"));
				v.add(rs.getString("cid"));			
				...
				dtm.addRow(v);
			}
		*/

	}

}
