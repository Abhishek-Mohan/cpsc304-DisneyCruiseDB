package com.disneycruise.cruiseUI;


import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddPsgScheduleFrm extends JFrame {

	private JPanel contentPane;
	private JTextField sid_textField;
	private JTextField pid_textField;
	private JTextField eid_textField;
	private JTextField sstime_textField;
	private JTextField setime_textField;
	private JButton btnCreate_1;
	private JButton btnReset;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPsgScheduleFrm frame = new AddPsgScheduleFrm();
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
	public AddPsgScheduleFrm() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 586, 518);
	
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setTitle("Create Schedule");
		setBounds(100, 100, 500, 488);
		contentPane.setLayout(null);
		
		JLabel lblSid = new JLabel("sid:");
		lblSid.setFont(new Font("Arial", Font.PLAIN, 18));
		
		lblSid.setBounds(49, 59, 43, 21);
		contentPane.add(lblSid);
		
		JLabel lblPid = new JLabel("pid:");
		lblPid.setFont(new Font("Arial", Font.PLAIN, 18));
		
		lblPid.setBounds(49, 112, 43, 21);
		contentPane.add(lblPid);
		
		JLabel lblEid = new JLabel("eid:");
		lblEid.setFont(new Font("Arial", Font.PLAIN, 18));
		
		lblEid.setBounds(49, 163, 43, 21);
		contentPane.add(lblEid);
		
		JLabel lblSstime = new JLabel("sstime:");
		lblSstime.setFont(new Font("Arial", Font.PLAIN, 18));
		
		lblSstime.setBounds(24, 219, 68, 21);
		contentPane.add(lblSstime);
		
		JLabel lblSetime = new JLabel("setime:");
		lblSetime.setFont(new Font("Arial", Font.PLAIN, 18));
		
		lblSetime.setBounds(24, 271, 68, 21);
		contentPane.add(lblSetime);
		
		sid_textField = new JTextField();
		sid_textField.setBounds(96, 55, 327, 27);
		contentPane.add(sid_textField);
		sid_textField.setColumns(10);
		
		pid_textField = new JTextField();
		pid_textField.setColumns(10);
		pid_textField.setBounds(96, 108, 327, 27);
		contentPane.add(pid_textField);
		
		eid_textField = new JTextField();
		eid_textField.setColumns(10);
		eid_textField.setBounds(96, 159, 327, 27);
		contentPane.add(eid_textField);
		
		sstime_textField = new JTextField();
		sstime_textField.setColumns(10);
		sstime_textField.setBounds(96, 215, 327, 27);
		contentPane.add(sstime_textField);
		
		setime_textField = new JTextField();
		setime_textField.setColumns(10);
		setime_textField.setBounds(96, 267, 327, 27);
		contentPane.add(setime_textField);
		
		btnCreate_1 = new JButton("Create");
		btnCreate_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sidt = sid_textField.getText(), pidt=pid_textField.getText();
				String eidt = eid_textField.getText(), sstt=sstime_textField.getText();
				String sett = setime_textField.getText();
				if(StringUtil.isEmpty(sidt)){
					JOptionPane.showMessageDialog(null, "sid cannot be null!");
					return;
				}else if(StringUtil.isEmpty(pidt)){
					JOptionPane.showMessageDialog(null, "pid cannot be null!");
					return;
				}else if(StringUtil.isEmpty(eidt)){
					JOptionPane.showMessageDialog(null, "eid cannot be null!");
					return;
				}else if(StringUtil.isEmpty(sstt)){
					JOptionPane.showMessageDialog(null, "sstime cannot be null!");
					return;
				}else if(StringUtil.isEmpty(sett)){
					JOptionPane.showMessageDialog(null, "setime cannot be null!");
					return;
				}
				
				/* replace this commented block
				 * with your query database code 
				 * to insert values into schedulecontent table and schedule table
				 */
			}
		});
		btnCreate_1.setFont(new Font("Arial", Font.PLAIN, 18));
		btnCreate_1.setBounds(91, 340, 123, 29);
		contentPane.add(btnCreate_1);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValues();
			}
		});
		btnReset.setFont(new Font("Arial", Font.PLAIN, 18));
		btnReset.setBounds(285, 340, 123, 29);
		contentPane.add(btnReset);	
				
	}
	
	private void resetValues(){
		this.sid_textField.setText("");
		this.pid_textField.setText("");
		this.eid_textField.setText("");
		this.sstime_textField.setText("");
		this.setime_textField.setText("");
	}

}
