package com.disneycruise.cruiseUI;


import com.disneycruise.cruise.PassengerTableViews;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;


import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JDesktopPane;
import java.sql.*;

public class PassengerFrm extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnSchedules;
	private JButton btnBrowseActivity;
	private JButton btnNumberOfSchedules;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PassengerFrm frame = new PassengerFrm();
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
	public PassengerFrm() {
		setTitle("Passenger View");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 715, 500);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 15, 1185, 956);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setShowVerticalLines(false);
		table.setFont(new Font("SimSun-ExtB", Font.PLAIN, 18));
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Passenger ID", "Passenger Name", "Age", "Password", "Cabin ID"
				}
		) {
			boolean[] columnEditables = new boolean[] {
					false, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(131);
		table.getColumnModel().getColumn(1).setPreferredWidth(145);
		table.getColumnModel().getColumn(3).setPreferredWidth(111);
		table.getColumnModel().getColumn(4).setPreferredWidth(102);
		scrollPane.setViewportView(table);
		table.setBounds(15, 15, 763, 956);

		btnSchedules = new JButton("Schedules");

		btnSchedules.setFont(new Font("Arial", Font.PLAIN, 20));
		btnSchedules.setBounds(1406, 101, 183, 50);
		contentPane.add(btnSchedules);

		btnBrowseActivity = new JButton("Browse Activity");
		btnBrowseActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!brwActOpen){
					brwActOpen=true;
					actBrwIF= new ActivityBrowseInterFrm();
					actBrwIF.setVisible(true);
//					desktopPane.add(actBrwIF);
				}else{
					brwActOpen=false;
					actBrwIF.dispose();

				}
			}
		});
		btnBrowseActivity.setFont(new Font("Arial", Font.PLAIN, 20));
		btnBrowseActivity.setBounds(1406, 251, 183, 50);
		contentPane.add(btnBrowseActivity);

		btnNumberOfSchedules = new JButton("<html><font color=black>Total Number of </font> <br> <font color=black>Schedules of </font><br> <font color=black>each Passenger</font></html>");
		btnNumberOfSchedules.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNumberOfSchedules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!totalSchOpen){
					totalSchOpen=true;
					totalSchIF= new TotalNumScheduleOfEachPsg();
					totalSchIF.setVisible(true);
//					desktopPane.add(totalSchIF);
				}else{
					totalSchOpen=false;
					totalSchIF.dispose();

				}
			}
		});
		btnNumberOfSchedules.setBounds(1406, 389, 183, 91);
		contentPane.add(btnNumberOfSchedules);

		btnSchedules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!PsgSchOpen){
					PsgSchOpen=true;
					psgSI= new PsgSchedulesInterFrm();
					psgSI.setVisible(true);
//					desktopPane.add(psgSI);
				}else{
					PsgSchOpen=false;
					psgSI.dispose();

				}
			}
		});
		this.fillTable(new Object());
	}

	private void fillTable(Object o) {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);

		PassengerTableViews pv = new PassengerTableViews();
		ResultSet rs = pv.getPassengerTableView();

		try {
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getString("pid"));
				v.add(rs.getString("pname"));
				v.add(rs.getInt("age"));
				v.add(rs.getString("password"));
				v.add(rs.getInt("cid"));
				dtm.addRow(v);
			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();

		}
	}


	private boolean PsgSchOpen = false, brwActOpen=false, totalSchOpen=false;
	private PsgSchedulesInterFrm psgSI;
	private ActivityBrowseInterFrm actBrwIF;
	private TotalNumScheduleOfEachPsg totalSchIF;

}
