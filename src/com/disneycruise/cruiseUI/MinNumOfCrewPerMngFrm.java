package com.disneycruise.cruiseUI;

import com.disneycruise.cruise.ManagerTableViews;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Vector;

public class MinNumOfCrewPerMngFrm extends JFrame {

	private JPanel contentPane;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MinNumOfCrewPerMngFrm frame = new MinNumOfCrewPerMngFrm();
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
	public MinNumOfCrewPerMngFrm() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Minimum number of crew per manager");
		setBounds(100, 100, 769, 648);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 747, 577);


		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Count", "Manager ID"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(76);
		table.getColumnModel().getColumn(1).setPreferredWidth(138);
		table.setFont(new Font("SimSun-ExtB", Font.PLAIN, 18));
		table.setBounds(565, 96, 33, 52);
		scrollPane.setViewportView(table);
		getContentPane().add(scrollPane);
		this.fillTable(new Object());
	}
	
	private void fillTable(Object o) {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		ManagerTableViews mtv = new ManagerTableViews();
		ResultSet rs = mtv.getMinNumOfCrewPerManager();
		try {
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getString("man_id"));
				v.add(rs.getString("numEmployees"));

				dtm.addRow(v);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
}

