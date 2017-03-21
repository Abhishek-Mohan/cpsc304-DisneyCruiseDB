package GUI;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;


import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JDesktopPane;

public class PassengerFrm extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnSchedules;
	private JButton btnBrowseActivity;
	private JButton btnNumberOfSchedules;
	private JDesktopPane desktopPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PassengerFrm frame = new PassengerFrm();
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
	public PassengerFrm() {
		setTitle("Passenger View");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 715, 500);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);	
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 15, 735, 956);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setShowVerticalLines(false);
		table.setFont(new Font("Arial", Font.PLAIN, 23));
		table.setModel(new DefaultTableModel(				
			new Object[][] {		
			},
			new String[] {
				"pid", "pname", "age", "password", "cid"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(3).setPreferredWidth(111);
		scrollPane.setViewportView(table);
		table.setBounds(15, 15, 763, 956);
		
		btnSchedules = new JButton("Schedules");
		
		btnSchedules.setFont(new Font("Arial", Font.PLAIN, 20));
		btnSchedules.setBounds(765, 90, 183, 50);
		contentPane.add(btnSchedules);
		
		btnBrowseActivity = new JButton("Browse Activity");
		btnBrowseActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!brwActOpen){
					brwActOpen=true;
					actBrwIF= new ActivityBrowseInterFrm();
					actBrwIF.setVisible(true);
					desktopPane.add(actBrwIF);
				}else{
					brwActOpen=false;					
					actBrwIF.dispose();
					
				}
			}
		});
		btnBrowseActivity.setFont(new Font("Arial", Font.PLAIN, 20));
		btnBrowseActivity.setBounds(765, 197, 183, 50);
		contentPane.add(btnBrowseActivity);
		
		btnNumberOfSchedules = new JButton("<html><font color=black>Total Number of </font> <br> <font color=black>Schedules of </font><br> <font color=black>each Passenger</font></html>");
		btnNumberOfSchedules.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNumberOfSchedules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!totalSchOpen){
					totalSchOpen=true;
					totalSchIF= new TotalNumScheduleOfEachPsg();
					totalSchIF.setVisible(true);
					desktopPane.add(totalSchIF);
				}else{
					totalSchOpen=false;					
					totalSchIF.dispose();
					
				}
			}
		});
		btnNumberOfSchedules.setBounds(765, 306, 183, 91);
		contentPane.add(btnNumberOfSchedules);
		
		desktopPane = new JDesktopPane();
		
		btnSchedules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!PsgSchOpen){
					PsgSchOpen=true;
					psgSI= new PsgSchedulesInterFrm();
					psgSI.setVisible(true);
					desktopPane.add(psgSI);
				}else{
					PsgSchOpen=false;					
					psgSI.dispose();
					
				}
			}
		});
		
		desktopPane.setBounds(963, 0, 949, 986);
		contentPane.add(desktopPane);		
		this.fillTable(new Object());
	}
	
	private void fillTable(Object o) {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		/*
		 * replace this commented block
		 * with your query database code 
		 * to get all passenger data from database as variable rs
			
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getInt("pid"));
				v.add(rs.getString("pname"));
				v.add(rs.getString("age"));
				...
				dtm.addRow(v);
			}
		*/
	
	}
	
	
	private boolean PsgSchOpen = false, brwActOpen=false, totalSchOpen=false;
	private PsgSchedulesInterFrm psgSI;
	private ActivityBrowseInterFrm actBrwIF;
	private TotalNumScheduleOfEachPsg totalSchIF;

}
