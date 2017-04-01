package com.disneycruise.cruiseUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class PassengerRemoveSch extends JFrame {

	private JPanel contentPane;
	private JTextField userName_txtFiled;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PassengerRemoveSch frame = new PassengerRemoveSch();
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
	public PassengerRemoveSch() {
		setTitle("Remove Schedule");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 611, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbleid = new JLabel("Entertainment ID:");
		lbleid.setFont(new Font("Arial", Font.PLAIN, 18));
		lbleid.setBounds(77, 67, 149, 21);
		contentPane.add(lbleid);
		
		JLabel lblsid = new JLabel("Schedule ID:");
		lblsid.setFont(new Font("Arial", Font.PLAIN, 18));
		lblsid.setBounds(112, 133, 111, 21);
		contentPane.add(lblsid);
		
		userName_txtFiled = new JTextField();
		userName_txtFiled.setBounds(225, 64, 224, 27);
		contentPane.add(userName_txtFiled);
		userName_txtFiled.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(225, 129, 224, 27);
		contentPane.add(passwordField);
		
		JButton btnLogin = new JButton("Remove");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// add remove sql 	
			}
		});
		btnLogin.setFont(new Font("Arial", Font.PLAIN, 18));
		btnLogin.setBounds(125, 207, 123, 29);
		contentPane.add(btnLogin);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userName_txtFiled.setText("");
				passwordField.setText("");
			}
		});
		btnReset.setFont(new Font("Arial", Font.PLAIN, 18));
		btnReset.setBounds(339, 207, 123, 29);
		contentPane.add(btnReset);
	}
	

}
