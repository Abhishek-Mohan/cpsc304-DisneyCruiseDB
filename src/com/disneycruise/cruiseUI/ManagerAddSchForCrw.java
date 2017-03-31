package com.disneycruise.cruiseUI;

import com.disneycruise.cruise.ManagerTableViews;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ManagerAddSchForCrw extends JFrame {

	private JPanel contentPane;
	private JTextField csid_textField;
	private JTextField esid_textField;
	private JTextField starttime_textField_1;
	private JTextField endtime_textField;
	private JTextField crewid_textField;
	private JButton btnCreate_1;
	private JButton btnReset;
	private JLabel lblManid;
	private JTextField manid_textField;
	private JLabel lblEid_1;
	private JTextField eid_textField_1;
	private JLabel lblYouHaveTo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerAddSchForCrw frame = new ManagerAddSchForCrw();
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
	public ManagerAddSchForCrw() {
		setAutoRequestFocus(false);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 561, 604);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setTitle("Create Schedule for Crew");
		contentPane.setLayout(null);
		
		JLabel lblSid = new JLabel("csid:");
		lblSid.setFont(new Font("Arial", Font.PLAIN, 18));
		
		lblSid.setBounds(85, 87, 43, 21);
		contentPane.add(lblSid);
		
		JLabel lblPid = new JLabel("esid:");
		lblPid.setFont(new Font("Arial", Font.PLAIN, 18));
		
		lblPid.setBounds(85, 143, 43, 21);
		contentPane.add(lblPid);
		
		JLabel lblEid = new JLabel("crew_id:");
		lblEid.setFont(new Font("Arial", Font.PLAIN, 18));
		
		lblEid.setBounds(51, 350, 85, 21);
		contentPane.add(lblEid);
		
		JLabel lblSstime = new JLabel("Start Time:");
		lblSstime.setFont(new Font("Arial", Font.PLAIN, 18));
		
		lblSstime.setBounds(32, 244, 96, 21);
		contentPane.add(lblSstime);
		
		JLabel lblSetime = new JLabel("End Time:");
		lblSetime.setFont(new Font("Arial", Font.PLAIN, 18));
		
		lblSetime.setBounds(38, 297, 96, 21);
		contentPane.add(lblSetime);
		
		csid_textField = new JTextField();
		csid_textField.setBounds(136, 83, 327, 27);
		contentPane.add(csid_textField);
		csid_textField.setColumns(10);
		
		esid_textField = new JTextField();
		esid_textField.setColumns(10);
		esid_textField.setBounds(136, 139, 327, 27);
		contentPane.add(esid_textField);
		
		starttime_textField_1 = new JTextField();
		starttime_textField_1.setColumns(10);
		starttime_textField_1.setBounds(136, 240, 327, 27);
		contentPane.add(starttime_textField_1);
		
		endtime_textField = new JTextField();
		endtime_textField.setColumns(10);
		endtime_textField.setBounds(136, 293, 327, 27);
		contentPane.add(endtime_textField);
		
		crewid_textField = new JTextField();
		crewid_textField.setColumns(10);
		crewid_textField.setBounds(136, 346, 327, 27);
		contentPane.add(crewid_textField);
		
		btnCreate_1 = new JButton("Create");
		btnCreate_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String csidt = csid_textField.getText(), esidt=esid_textField.getText();
				String eidt = eid_textField_1.getText(), startimet=starttime_textField_1.getText();
				String endtimet = endtime_textField.getText(), crewid = crewid_textField.getText();
				String manid = manid_textField.getText();
				if(StringUtil.isEmpty(csidt) && StringUtil.isEmpty(esidt)){
					JOptionPane.showMessageDialog(null, "csid and esid CANNOT both be empty!");
					return;
				}else if(StringUtil.isNotEmpty(csidt)&&StringUtil.isNotEmpty(esidt)){
					JOptionPane.showMessageDialog(null, "You CANNOT fill both csid and esid!");
					return;
				}else if(StringUtil.isNotEmpty(esidt)&&StringUtil.isEmpty(eidt)){
					JOptionPane.showMessageDialog(null, "eid CANNOT be empty!");
					return;
				}else if(StringUtil.isEmpty(startimet) || StringUtil.isEmpty(endtimet)){
					JOptionPane.showMessageDialog(null, "Start time and End time CANNOT be empty!");
					return;
				}else if(StringUtil.isEmpty(crewid)){
					JOptionPane.showMessageDialog(null, "crew_id CANNOT be empty!");
					return;
				}else if(StringUtil.isEmpty(manid)){
					JOptionPane.showMessageDialog(null, "man_id CANNOT be empty!");
					return;
				}

				ManagerTableViews mtv = new ManagerTableViews();
				mtv.createCrewSchedule(csidt, esidt, eidt, startimet, endtimet, crewid, manid);
				/* replace this commented block
				 * with your query database code 
				 * to insert values into schedule tables 
				 */
			}
		});
		btnCreate_1.setFont(new Font("Arial", Font.PLAIN, 18));
		btnCreate_1.setBounds(115, 481, 123, 29);
		contentPane.add(btnCreate_1);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValues();
			}
		});
		btnReset.setFont(new Font("Arial", Font.PLAIN, 18));
		btnReset.setBounds(327, 481, 123, 29);
		contentPane.add(btnReset);	
		
		lblManid = new JLabel("man_id:");
		lblManid.setFont(new Font("Arial", Font.PLAIN, 18));
		lblManid.setBounds(55, 405, 71, 21);
		contentPane.add(lblManid);
		
		manid_textField = new JTextField();
		manid_textField.setColumns(10);
		manid_textField.setBounds(136, 401, 327, 27);
		contentPane.add(manid_textField);
		
		lblEid_1 = new JLabel("eid:");
		lblEid_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblEid_1.setBounds(90, 196, 43, 21);
		contentPane.add(lblEid_1);
		
		eid_textField_1 = new JTextField();
		eid_textField_1.setColumns(10);
		eid_textField_1.setBounds(136, 192, 327, 27);
		contentPane.add(eid_textField_1);
		
		lblYouHaveTo = new JLabel("You have to fill all rows leave either csid or (esid,eid) empty");
		lblYouHaveTo.setFont(new Font("Arial", Font.PLAIN, 18));
		lblYouHaveTo.setBounds(36, 26, 482, 21);
		contentPane.add(lblYouHaveTo);
				
	}
	
	private void resetValues(){
		this.csid_textField.setText("");
		this.esid_textField.setText("");
		this.eid_textField_1.setText("");
		this.starttime_textField_1.setText("");
		this.endtime_textField.setText("");
		this.crewid_textField.setText("");
		this.manid_textField.setText("");
	}

}