package com.disneycruise.cruiseUI;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.disneycruise.cruise.*;
import java.sql.*;
import java.util.Vector;

public class ManagerScheduleInterFrm extends JFrame {
	private JTable managerSch_table;
	private JTable managedCrewSch_table;
	private String manid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//ManagerScheduleInterFrm frame = new ManagerScheduleInterFrm();
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
	public ManagerScheduleInterFrm(String input, boolean isManID, boolean isCrewID) {
		if (isManID) {
			this.manid = input;
		}
		setTitle("Manager's Schedule");
		setBounds(10, 0, 1123, 961);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 51, 846, 457);


		managerSch_table = new JTable();
		managerSch_table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Manager Name", "Manager ID", "Clean Schedule ID", "Entertainment Schedule ID", "Start Time", "End Time"
				}
		));
		managerSch_table.getColumnModel().getColumn(0).setPreferredWidth(135);
		managerSch_table.getColumnModel().getColumn(1).setPreferredWidth(102);
		managerSch_table.getColumnModel().getColumn(2).setPreferredWidth(167);
		managerSch_table.getColumnModel().getColumn(3).setPreferredWidth(240);
		managerSch_table.getColumnModel().getColumn(4).setPreferredWidth(115);
		managerSch_table.getColumnModel().getColumn(5).setPreferredWidth(95);
		managerSch_table.setBounds(81, 512, 106, 35);
		scrollPane.setViewportView(managerSch_table);
		getContentPane().add(scrollPane);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(15, 563, 899, 342);


		managedCrewSch_table = new JTable();
		managedCrewSch_table.setModel(new DefaultTableModel(
				new Object[][] {
						{null, null, null, null, null, null, null},
				},
				new String[] {
						"Manager ID", "Crew Name", "Crew ID", "Clean Schedule ID", "Entertainment Schedule ID", "Start Time", "End Time"
				}
		));
		managedCrewSch_table.getColumnModel().getColumn(0).setPreferredWidth(104);
		managedCrewSch_table.getColumnModel().getColumn(1).setPreferredWidth(95);
		managedCrewSch_table.getColumnModel().getColumn(3).setPreferredWidth(163);
		managedCrewSch_table.getColumnModel().getColumn(4).setPreferredWidth(236);
		managedCrewSch_table.getColumnModel().getColumn(5).setPreferredWidth(115);
		managedCrewSch_table.getColumnModel().getColumn(6).setPreferredWidth(95);
		managedCrewSch_table.setBounds(66, 843, 108, 62);
		scrollPane_1.setViewportView(managedCrewSch_table);
		getContentPane().add(scrollPane_1);

		JLabel lblManagedCrewsSchedules = new JLabel("Manage Crew's Schedules:");
		lblManagedCrewsSchedules.setFont(new Font("Arial", Font.PLAIN, 18));
		lblManagedCrewsSchedules.setBounds(15, 538, 274, 21);
		getContentPane().add(lblManagedCrewsSchedules);

		JLabel lblManagersSchedule = new JLabel("Manager's Schedule:");
		lblManagersSchedule.setFont(new Font("Arial", Font.PLAIN, 18));
		lblManagersSchedule.setBounds(15, 25, 347, 21);
		getContentPane().add(lblManagersSchedule);

		JButton btnCreateScheduleFor = new JButton("<html><font color=black>Create Schedule</font> <br> <font color=black>for Crew</font></html>");
		btnCreateScheduleFor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!mngAddSchOpen){
					mngAddSchOpen=true;
					mngAddSchForCrw= new ManagerAddSchForCrw();
					mngAddSchForCrw.setVisible(true);
				}else{
					mngAddSchOpen=false;
					mngAddSchForCrw.dispose();

				}
			}
		});
		btnCreateScheduleFor.setFont(new Font("Arial", Font.PLAIN, 18));
		btnCreateScheduleFor.setBounds(898, 94, 172, 59);
		getContentPane().add(btnCreateScheduleFor);

		JButton updateCrwSch_button = new JButton("<html><font color=black>Edit Crew's</font> <br> <font color=black>Schedule</font></html>");
		updateCrwSch_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!mngUpdateSchOpen){
					mngUpdateSchOpen=true;
					mngUpdateSchFrm= new ManagerUpdateSchFrm();
					mngUpdateSchFrm.setVisible(true);
				}else{
					mngUpdateSchOpen=false;
					mngUpdateSchFrm.dispose();

				}
			}
		});
		updateCrwSch_button.setFont(new Font("Arial", Font.PLAIN, 18));
		updateCrwSch_button.setBounds(898, 199, 172, 59);
		getContentPane().add(updateCrwSch_button);

		

		this.fillMngSchTable(new Object());
		this.fillCrwSchTable(new Object(), input, isManID, isCrewID);
	}

	private void fillMngSchTable(Object o) {
		DefaultTableModel dtm = (DefaultTableModel) managerSch_table.getModel();
		dtm.setRowCount(0);
		System.out.println(manid);
		ManagerTableViews tv = new ManagerTableViews();
		ResultSet rs = tv.getManagerScheduleView(manid);

		try {
			if (tv.getIsEntertainmentManager() != false || tv.getIsCleaningManager() != false) {
				while (rs.next()) {
					Vector v = new Vector();

					if (tv.getIsCleaningManager()) {

						v.add(rs.getString("mname"));
						v.add(rs.getInt("man_id"));
						v.add(rs.getString("csid"));
						v.add("-");
						v.add(rs.getObject("cs_stime"));
						v.add(rs.getObject("cs_etime"));
						dtm.addRow(v);
					}

					if (tv.getIsEntertainmentManager()) {
						v.add(rs.getInt("man_id"));
						v.add(rs.getString("mname"));
						v.add("-");
						v.add(rs.getString("esid"));
						v.add("-");
						v.add("-");
						dtm.addRow(v);
					}
				}
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	private void fillCrwSchTable(Object o, String input, boolean isManID, boolean isCrewID) {
		DefaultTableModel dtm = (DefaultTableModel) managedCrewSch_table.getModel();
		dtm.setRowCount(0);

		ManagerTableViews tv = new ManagerTableViews();
		ResultSet rs = null;

		if (isCrewID) {
			rs = tv.getManagerCrewScheduleByCrewID(input);
		}

		try {
			if (tv.getIsEntertainmentManager() != false || tv.getIsCleaningManager() != false) {
				while (rs.next()) {
					Vector v = new Vector();

					if (tv.getIsCleaningManager()) {

						v.add(rs.getInt("man_id"));
						v.add(rs.getString("cname"));
						v.add(rs.getString("crew_id"));
						v.add(rs.getString("csid"));
						v.add("-");
						v.add(rs.getObject("cs_stime"));
						v.add(rs.getObject("cs_etime"));
						dtm.addRow(v);
					}

					if (tv.getIsEntertainmentManager()) {
						v.add(rs.getInt("man_id"));
						v.add(rs.getString("cname"));
						v.add(rs.getString("crew_id"));
						v.add("-");
						v.add(rs.getString("esid"));
						v.add(rs.getObject("es_stime"));
						v.add(rs.getObject("es_etime"));
						dtm.addRow(v);
					}
				}
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	private boolean mngAddSchOpen=false, mngUpdateSchOpen=false;
	private ManagerAddSchForCrw mngAddSchForCrw;
	private ManagerUpdateSchFrm mngUpdateSchFrm;
}
