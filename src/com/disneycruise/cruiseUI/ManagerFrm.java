package com.disneycruise.cruiseUI;

import java.sql.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;
import javax.swing.table.DefaultTableModel;
import com.disneycruise.cruise.*;

public class ManagerFrm extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel lblFindScheduleBy;
	private JTextField crewid_textField;
	private JTextField manid_textField;
	private boolean createMaxEmployeePerDeptOpen=false;
	private MaxNumEmployeePerDeptFrm maxNumOfEmplPerDept;
	private boolean createMinNumOpen=false;
	private MinNumOfCrewPerMngFrm minNumOfCrewPerMng;
	private boolean crwSchOpen=false, mngSchOpen=false;
	private CrewScheduleInterFrm crwSchIF;
	private ManagerScheduleInterFrm mngSchIF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerFrm frame = new ManagerFrm();
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
	public ManagerFrm() {
		setTitle("Manager View");
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
				"man_id ", "Manager Name", "Department", "cid"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(85);
		table.getColumnModel().getColumn(1).setPreferredWidth(135);
		table.getColumnModel().getColumn(2).setPreferredWidth(105);
		table.setFont(new Font("SimSun-ExtB", Font.PLAIN, 18));
		table.setBounds(738, 184, 120, 86);
		scrollPane.setViewportView(table);
		contentPane.add(scrollPane);
		
		lblFindScheduleBy = new JLabel("Browse Schedule by man_id:");
		lblFindScheduleBy.setFont(new Font("Arial", Font.PLAIN, 18));
		lblFindScheduleBy.setBounds(699, 70, 247, 21);
		contentPane.add(lblFindScheduleBy);
		
		JLabel lblWorkPlace = new JLabel("crew_id:");
		lblWorkPlace.setFont(new Font("Arial", Font.PLAIN, 17));
		lblWorkPlace.setBounds(709, 263, 115, 21);
		contentPane.add(lblWorkPlace);
		
		crewid_textField = new JTextField();
		crewid_textField.setBounds(709, 288, 215, 27);
		contentPane.add(crewid_textField);
		crewid_textField.setColumns(10);
		
		manid_textField = new JTextField();
		manid_textField.setBounds(709, 106, 215, 27);
		contentPane.add(manid_textField);
		manid_textField.setColumns(10);
		
		JButton btnBrowse_schedule = new JButton("Browse");
		
		btnBrowse_schedule.setFont(new Font("Arial", Font.PLAIN, 18));
		btnBrowse_schedule.setBounds(752, 148, 123, 29);
		contentPane.add(btnBrowse_schedule);
		
		JLabel lblFindCrewBy = new JLabel("Find a crew's schedule :");
		lblFindCrewBy.setFont(new Font("Arial", Font.PLAIN, 18));
		lblFindCrewBy.setBounds(699, 238, 231, 21);
		contentPane.add(lblFindCrewBy);
		
		JButton btnFindCrewSch = new JButton("Find");
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(962, 0, 950, 986);
		btnFindCrewSch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String crwid = crewid_textField.getText();
				if(StringUtil.isEmpty(crwid)){
					JOptionPane.showMessageDialog(null, "crew_id CANNOT be empty!");
					return;
				}
				if(!mngSchOpen){
					mngSchOpen=true;
					mngSchIF= new ManagerScheduleInterFrm(crwid, false, true);
					mngSchIF.setVisible(true);
					desktopPane.add(mngSchIF);
				}else{
					mngSchOpen=false;
					mngSchIF.dispose();
					
				}
				/*
				 * replace this commented block
				 * with your query database code 
				 * to search crew's schedules by crew_id from database
				 * crewid info. is in variable crewid_textField		
				 */				
				
			}
		});
		btnFindCrewSch.setFont(new Font("Arial", Font.PLAIN, 18));
		btnFindCrewSch.setBounds(752, 330, 123, 29);
		contentPane.add(btnFindCrewSch);
		
		
		btnBrowse_schedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String manid = manid_textField.getText();
				if(StringUtil.isEmpty(manid)){
					JOptionPane.showMessageDialog(null, "man_id CANNOT be empty");
					return;
				}
				if(!mngSchOpen){
					mngSchOpen=true;
					mngSchIF= new ManagerScheduleInterFrm(manid, true, false);
					mngSchIF.setVisible(true);
					desktopPane.add(mngSchIF);
				}else{
					mngSchOpen=false;					
					mngSchIF.dispose();
				}

				/*
				 * replace this commented block
				 * with your query database code 
				 * to search crew's schedules by crew_id from database
				 * crewid info. is in variable crewid_textField		
				 */
			}
		});
		contentPane.add(desktopPane);
		
		JButton btnNewButton = new JButton("<html><font color=black>Find minimum number </font> <br> <font color=black>of crew per manager</font></html>");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!createMinNumOpen){
					createMinNumOpen=true;
					minNumOfCrewPerMng= new MinNumOfCrewPerMngFrm();
					minNumOfCrewPerMng.setVisible(true);
					minNumOfCrewPerMng.setLocationRelativeTo(null);
					
				}else{
					createMinNumOpen=false;					
					minNumOfCrewPerMng.dispose();
					
				}
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 18));
		btnNewButton.setBounds(709, 440, 215, 63);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("<html><font color=black>Find the max number</font><br><font color=black> of employees per</font><br><font color=black> department</font></html>");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!createMaxEmployeePerDeptOpen){
					createMaxEmployeePerDeptOpen=true;
					maxNumOfEmplPerDept= new MaxNumEmployeePerDeptFrm();
					maxNumOfEmplPerDept.setVisible(true);
					maxNumOfEmplPerDept.setLocationRelativeTo(null);
					
				}else{
					createMaxEmployeePerDeptOpen=false;					
					maxNumOfEmplPerDept.dispose();
					
				}
			}
		});
		btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 18));
		
		btnNewButton_1.setBounds(709, 560, 215, 81);
		contentPane.add(btnNewButton_1);
		this.fillTable(new Object());
	}
	
	private void fillTable(Object o) {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		/*
		 * replace this commented block
		 * with your query database code 
		 * to get all manager's data from database as variable rs
			*/
		ManagerTableViews tv = new ManagerTableViews();
		 ResultSet rs = tv.getManagerTableView();

		 try {
			 while (rs.next()) {
				 Vector v = new Vector();
				 v.add(rs.getInt("man_id"));
				 v.add(rs.getString("mname"));
				v.add(rs.getString("department"));
				v.add(rs.getInt("cid"));
				 dtm.addRow(v);
			 }
			 rs.close();
		 } catch (SQLException se) {
		 	se.printStackTrace();

		 }
	}

}
