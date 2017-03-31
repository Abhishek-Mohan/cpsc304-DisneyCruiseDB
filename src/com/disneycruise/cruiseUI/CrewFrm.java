package com.disneycruise.cruiseUI;

import com.disneycruise.cruise.CrewTableViews;
import java.sql.*;
import java.util.Vector;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class CrewFrm extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel lblFindScheduleBy;
	private JTextField workplace_textField;
	private JTextField crewId_textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrewFrm frame = new CrewFrm();
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
	public CrewFrm() {
		setTitle("Crew View");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 715, 500);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);	
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 15, 663, 956);
		
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"crew_id", "Name", "Department", "cid", "csid", "esid"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(85);
		table.getColumnModel().getColumn(2).setPreferredWidth(105);
		table.setFont(new Font("SimSun-ExtB", Font.PLAIN, 18));
		table.setBounds(738, 184, 120, 86);
		scrollPane.setViewportView(table);
		contentPane.add(scrollPane);
		
		lblFindScheduleBy = new JLabel("Browse Schedule by crew_id:");
		lblFindScheduleBy.setFont(new Font("Arial", Font.PLAIN, 18));
		lblFindScheduleBy.setBounds(699, 70, 247, 21);
		contentPane.add(lblFindScheduleBy);
		
		JCheckBox isManager_CheckBox = new JCheckBox("Is Manager");
		isManager_CheckBox.setFont(new Font("Arial", Font.PLAIN, 17));
		isManager_CheckBox.setBounds(709, 316, 129, 29);
		contentPane.add(isManager_CheckBox);
		
		JLabel lblWorkPlace = new JLabel("Work Place:");
		lblWorkPlace.setFont(new Font("Arial", Font.PLAIN, 17));
		lblWorkPlace.setBounds(709, 250, 115, 21);
		contentPane.add(lblWorkPlace);
		
		workplace_textField = new JTextField();
		workplace_textField.setBounds(709, 277, 215, 27);
		contentPane.add(workplace_textField);
		workplace_textField.setColumns(10);
		
		crewId_textField = new JTextField();
		crewId_textField.setBounds(709, 106, 215, 27);
		contentPane.add(crewId_textField);
		crewId_textField.setColumns(10);
		
		JButton btnBrowse_schedule = new JButton("Browse");
		
		btnBrowse_schedule.setFont(new Font("Arial", Font.PLAIN, 18));
		btnBrowse_schedule.setBounds(752, 148, 123, 29);
		contentPane.add(btnBrowse_schedule);
		
		JLabel lblFindCrewBy = new JLabel("Find crew by work place:");
		lblFindCrewBy.setFont(new Font("Arial", Font.PLAIN, 18));
		lblFindCrewBy.setBounds(699, 217, 231, 21);
		contentPane.add(lblFindCrewBy);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(962, 0, 950, 986);
		
		JButton btnFindCrew = new JButton("Find");
		btnFindCrew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String workplace  = workplace_textField.getText();
				if(StringUtil.isEmpty(workplace)){
					JOptionPane.showMessageDialog(null, "crew_id CANNOT be empty��");
					return;
				}
				if(!crwSchOpen){
					crwSchOpen=true;
					crwSchIF= new CrewScheduleInterFrm(workplace, true, false);
					crwSchIF.setVisible(true);
					desktopPane.add(crwSchIF);
				}else{
					crwSchOpen=false;
					crwSchIF.dispose();

				}
			}

		});
		btnFindCrew.setFont(new Font("Arial", Font.PLAIN, 18));
		btnFindCrew.setBounds(752, 368, 123, 29);
		contentPane.add(btnFindCrew);
		contentPane.add(desktopPane);


		btnBrowse_schedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String crwid = crewId_textField.getText();
				if(StringUtil.isEmpty(crwid)){
					JOptionPane.showMessageDialog(null, "crew_id CANNOT be empty��");
					return;
				}
				if(!crwSchOpen){
					crwSchOpen=true;
					crwSchIF= new CrewScheduleInterFrm(crwid, false, true);
					crwSchIF.setVisible(true);
					desktopPane.add(crwSchIF);
				}else{
					crwSchOpen=false;					
					crwSchIF.dispose();
					
				}
			}
		});
		contentPane.add(desktopPane);
		this.fillTable(new Object());
	}
	
	private void fillTable(Object o) {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);

		CrewTableViews ctv = new CrewTableViews();
		ResultSet rs = ctv.getCrewTableViews();
		try {
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getString("crew_id"));
				v.add(rs.getString("cname"));
				v.add(rs.getString("department"));
				v.add(rs.getString("cid"));
				v.add(rs.getString("csid"));
				v.add(rs.getString("esid"));

			//	v.add(rs.getString("man_id"));

				dtm.addRow(v);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	
	private boolean crwSchOpen=false;
	private CrewScheduleInterFrm crwSchIF;
}
