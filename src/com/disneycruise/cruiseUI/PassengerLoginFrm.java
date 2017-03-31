package com.disneycruise.cruiseUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PassengerLoginFrm extends JFrame {


	private JTextField userName_txtFiled;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PassengerLoginFrm frame = new PassengerLoginFrm();
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
	public PassengerLoginFrm() {
		JPanel contentPane;
		setTitle("Passger Login");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 611, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setFont(new Font("Arial", Font.PLAIN, 18));
		lblUserName.setBounds(125, 67, 101, 21);
		contentPane.add(lblUserName);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 18));
		lblPassword.setBounds(131, 132, 84, 21);
		contentPane.add(lblPassword);

		userName_txtFiled = new JTextField();
		userName_txtFiled.setBounds(225, 64, 224, 27);
		contentPane.add(userName_txtFiled);
		userName_txtFiled.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(225, 129, 224, 27);
		contentPane.add(passwordField);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// if find username and password in database then open: if(...)
				if(!psgOpen){
					psgOpen = true;
					p1 = new PassengerFrm();
					p1.setVisible(true);
					dispose();
				}else{
					psgOpen = false;
					p1.dispose();
				}
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

	private boolean psgOpen=false;
	private PassengerFrm p1;
}
