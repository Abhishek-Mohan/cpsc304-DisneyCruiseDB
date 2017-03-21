package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewsRunner extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewsRunner frame = new ViewsRunner();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewsRunner() {
		setTitle("DisneyCruiseDB Views");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 413, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDisneycruisedbViews = new JLabel("DisneyCruiseDB Views:");
		lblDisneycruisedbViews.setFont(new Font("Arial", Font.PLAIN, 23));
		lblDisneycruisedbViews.setBounds(62, 47, 251, 36);
		contentPane.add(lblDisneycruisedbViews);
		
		JButton btnNewButton = new JButton("Passenger View");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!psgOpen){
					psgOpen = true;
					p1 = new PassengerFrm();
					p1.setVisible(true);
				}else{
					psgOpen = false;
					p1.dispose();
				}				
			}
		});
		
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNewButton.setBounds(101, 139, 185, 59);
		contentPane.add(btnNewButton);
		
		JButton btnManagerView = new JButton("Manager View");
		btnManagerView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!mngOpen){
					mngOpen = true;
					m1 = new ManagerFrm();
					m1.setVisible(true);
				}else{
					mngOpen = false;
					m1.dispose();
				}		
			}
		});
		btnManagerView.setFont(new Font("Arial", Font.PLAIN, 20));
		btnManagerView.setBounds(101, 250, 185, 59);
		contentPane.add(btnManagerView);
		
		JButton btnCrewView = new JButton("Crew View");
		btnCrewView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!crwOpen){
					crwOpen = true;
					c1 = new CrewFrm();
					c1.setVisible(true);
				}else{
					crwOpen = false;
					c1.dispose();
				}		
			}
		});
		btnCrewView.setFont(new Font("Arial", Font.PLAIN, 20));
		btnCrewView.setBounds(101, 361, 185, 59);
		contentPane.add(btnCrewView);
	}
	
	 private boolean psgOpen=false, mngOpen=false, crwOpen=false;
	 private PassengerFrm p1;
	 private ManagerFrm m1;
	 private CrewFrm c1;


}
