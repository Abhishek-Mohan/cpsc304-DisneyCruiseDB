package GUI;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ActivityBrowseInterFrm extends JInternalFrame {
	private JTable act_table;
	private JTextField type_textField;
	private JTextField date_textField;
	private JTextField startTime_textField;
	private JTextField endTime_textField;
	private JTextField location_textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ActivityBrowseInterFrm frame = new ActivityBrowseInterFrm();
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
	public ActivityBrowseInterFrm() {
		setMaximizable(true);
		setTitle("Activity Browser");
		setBounds(10, 10, 903, 930);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 15, 649, 859);
		
		
		act_table = new JTable();
		act_table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"eid", "Type", "Name", "Location", "Seats", "Date", "Start", "End"
			}
		));
		act_table.getColumnModel().getColumn(3).setPreferredWidth(105);
		act_table.setFont(new Font("Arial", Font.PLAIN, 18));
		act_table.setBounds(697, 164, 34, 179);
		scrollPane.setViewportView(act_table);
		getContentPane().add(scrollPane);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setFont(new Font("Arial", Font.PLAIN, 18));
		lblType.setBounds(679, 32, 56, 21);
		getContentPane().add(lblType);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Arial", Font.PLAIN, 18));
		lblDate.setBounds(679, 109, 56, 21);
		getContentPane().add(lblDate);
		
		JLabel lblStartTime = new JLabel("Start Time:");
		lblStartTime.setFont(new Font("Arial", Font.PLAIN, 18));
		lblStartTime.setBounds(679, 178, 88, 21);
		getContentPane().add(lblStartTime);
		
		JLabel lblLocation = new JLabel("Location:");
		lblLocation.setFont(new Font("Arial", Font.PLAIN, 18));
		lblLocation.setBounds(679, 319, 88, 21);
		getContentPane().add(lblLocation);
		
		JLabel lblEndTime = new JLabel("End Time:");
		lblEndTime.setFont(new Font("Arial", Font.PLAIN, 18));
		lblEndTime.setBounds(679, 247, 88, 21);
		getContentPane().add(lblEndTime);
		
		type_textField = new JTextField();
		type_textField.setBounds(679, 68, 183, 27);
		getContentPane().add(type_textField);
		type_textField.setColumns(10);
		
		date_textField = new JTextField();
		date_textField.setColumns(10);
		date_textField.setBounds(679, 134, 183, 27);
		getContentPane().add(date_textField);
		
		startTime_textField = new JTextField();
		startTime_textField.setColumns(10);
		startTime_textField.setBounds(679, 201, 183, 27);
		getContentPane().add(startTime_textField);
		
		endTime_textField = new JTextField();
		endTime_textField.setColumns(10);
		endTime_textField.setBounds(679, 268, 183, 27);
		getContentPane().add(endTime_textField);
		
		location_textField = new JTextField();
		location_textField.setColumns(10);
		location_textField.setBounds(679, 344, 183, 27);
		getContentPane().add(location_textField);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* replace this commented block
				 * with your query database code 
				 * to search activity by give information, if no information then return all activity
				 */
				
				//fillTable();
			}
		});
		btnSearch.setFont(new Font("Arial", Font.PLAIN, 18));
		btnSearch.setBounds(715, 400, 123, 29);
		getContentPane().add(btnSearch);
		
		JButton btnPopularActivity = new JButton("Popular Activity");
		btnPopularActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* replace this commented block
				 * with your query database code 
				 * to find a/some activity(s) which is in every passenger's schedule
				 */
				
				//fillTable();
			}
		});
		btnPopularActivity.setFont(new Font("Arial", Font.PLAIN, 18));
		btnPopularActivity.setBounds(689, 499, 183, 29);
		getContentPane().add(btnPopularActivity);
		this.fillTable(new Object());

	}
	
	private void fillTable(Object o) {
		DefaultTableModel dtm = (DefaultTableModel) act_table.getModel();
		dtm.setRowCount(0);
		/*
		 * replace this commented block
		 * with your query database code 
		 * to get all passenger data from database as variable rs
			
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getInt("eid"));
				v.add(rs.getString("type"));		
				...
				dtm.addRow(v);
			}
		*/
	
	}

}
