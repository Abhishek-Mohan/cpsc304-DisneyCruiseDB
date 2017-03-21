package GUI;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManagerScheduleInterFrm extends JInternalFrame {
	private JTable managerSch_table;
	private JTable managedCrewSch_table;
	private JTextField esidcsidSchRm_textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//ManagerScheduleInterFrm frame = new ManagerScheduleInterFrm();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ManagerScheduleInterFrm(String manid) {
		setTitle("Manager's Schedule");
		setBounds(10, 0, 930, 961);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 51, 637, 457);
		
		
		managerSch_table = new JTable();
		managerSch_table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Manager Name", "man_id", "csid", "esid", "Start Time", "End Time"
			}
		));
		managerSch_table.getColumnModel().getColumn(0).setPreferredWidth(135);
		managerSch_table.getColumnModel().getColumn(4).setPreferredWidth(115);
		managerSch_table.getColumnModel().getColumn(5).setPreferredWidth(95);
		managerSch_table.setBounds(81, 512, 106, 35);
		scrollPane.setViewportView(managerSch_table);
		getContentPane().add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(15, 563, 637, 342);
		
		
		managedCrewSch_table = new JTable();
		managedCrewSch_table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"man_id", "Crew Name", "crew_id", "csid", "esid", "Start Time", "End Time"
			}
		));
		managedCrewSch_table.getColumnModel().getColumn(1).setPreferredWidth(115);
		managedCrewSch_table.getColumnModel().getColumn(5).setPreferredWidth(115);
		managedCrewSch_table.getColumnModel().getColumn(6).setPreferredWidth(95);
		managedCrewSch_table.setBounds(66, 843, 108, 62);
		scrollPane_1.setViewportView(managedCrewSch_table);
		getContentPane().add(scrollPane_1);
		
		JLabel lblManagedCrewsSchedules = new JLabel("Managed Crew's Schedules:");
		lblManagedCrewsSchedules.setFont(new Font("Arial", Font.PLAIN, 18));
		lblManagedCrewsSchedules.setBounds(15, 538, 274, 21);
		getContentPane().add(lblManagedCrewsSchedules);
		
		JLabel lblManagersSchedule = new JLabel("Manager's Schedule:");
		lblManagersSchedule.setFont(new Font("Arial", Font.PLAIN, 18));
		lblManagersSchedule.setBounds(15, 25, 347, 21);
		getContentPane().add(lblManagersSchedule);
		
		JButton btnCreateScheduleFor = new JButton("<html><font color=black>Create Schedule</font> <br> <font color=black>for Crew</font></html>");
		btnCreateScheduleFor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if(!mngAddSchOpen){
					mngAddSchOpen=true;
					mngAddSchForCrw= new ManagerAddSchForCrw();
					mngAddSchForCrw.setVisible(true);					
				}else{
					mngAddSchOpen=false;					
					mngAddSchForCrw.dispose();
					
				}			
			}
		});
		btnCreateScheduleFor.setFont(new Font("Arial", Font.PLAIN, 18));
		btnCreateScheduleFor.setBounds(697, 86, 172, 59);
		getContentPane().add(btnCreateScheduleFor);
		
		JButton updateCrwSch_button = new JButton("<html><font color=black>Update Crew's</font> <br> <font color=black>Schedule</font></html>");
		updateCrwSch_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!mngUpdateSchOpen){
					mngUpdateSchOpen=true;
					mngUpdateSchFrm= new ManagerUpdateSchFrm();
					mngUpdateSchFrm.setVisible(true);					
				}else{
					mngUpdateSchOpen=false;					
					mngUpdateSchFrm.dispose();
					
				}	
			}
		});
		updateCrwSch_button.setFont(new Font("Arial", Font.PLAIN, 18));
		updateCrwSch_button.setBounds(697, 189, 172, 59);
		getContentPane().add(updateCrwSch_button);
		
		JLabel lblRemoveCrewsSchedule = new JLabel("Remove Crew's Schedule:");
		lblRemoveCrewsSchedule.setFont(new Font("Arial", Font.PLAIN, 18));
		lblRemoveCrewsSchedule.setBounds(678, 303, 210, 21);
		getContentPane().add(lblRemoveCrewsSchedule);
		
		JLabel lblEsidcsid = new JLabel("esid/csid:");
		lblEsidcsid.setFont(new Font("Arial", Font.PLAIN, 17));
		lblEsidcsid.setBounds(688, 328, 107, 21);
		getContentPane().add(lblEsidcsid);
		
		esidcsidSchRm_textField = new JTextField();
		esidcsidSchRm_textField.setBounds(688, 350, 201, 27);
		getContentPane().add(esidcsidSchRm_textField);
		esidcsidSchRm_textField.setColumns(10);
		
		JButton removeCrwSch_Button = new JButton("Remove");
		removeCrwSch_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String rm = esidcsidSchRm_textField.getText();
				if(StringUtil.isEmpty(rm)){
					JOptionPane.showMessageDialog(null, "esid/csid CANNOT be empty!");
					return;
				}
				/* replace this commented block
				 * with your query database code 
				 * to remove a schedule by esid/csid which in variable esidcsidSchRm_textField
				 */
				
				//fillCrwSchTable(new Object());
			}
		});
		removeCrwSch_Button.setFont(new Font("Arial", Font.PLAIN, 18));
		removeCrwSch_Button.setBounds(697, 400, 172, 29);
		getContentPane().add(removeCrwSch_Button);
		this.fillMngSchTable(new Object());
	}
	
	private void fillMngSchTable(Object o) {
		DefaultTableModel dtm = (DefaultTableModel) managerSch_table.getModel();
		dtm.setRowCount(0);
		/*
		 * replace this commented block
		 * with your query database code 
		 * to get manager's schedule from database by variable manid 
			
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getInt("man_id"));
				v.add(rs.getString("csid"));
				...
				dtm.addRow(v);
			}
		*/	
	}
	
	private void fillCrwSchTable(Object o) {
		DefaultTableModel dtm = (DefaultTableModel) managedCrewSch_table.getModel();
		dtm.setRowCount(0);
		/*
		 * replace this commented block
		 * with your query database code 
		 * to get managed crew's schedules from database as variable rs
			
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getInt("crew_id"));
				v.add(rs.getString("csid"));
				...
				dtm.addRow(v);
			}
		*/
	
	}
	
	private boolean mngAddSchOpen=false, mngUpdateSchOpen=false;
	private ManagerAddSchForCrw mngAddSchForCrw;
	private ManagerUpdateSchFrm mngUpdateSchFrm;
}
