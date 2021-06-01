package Setup;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;







public class mainUI {

	public JFrame frame;
	private JButton submitSubmission;
	private JTextField submitSubmissionTF;
	private JComboBox<String> moodMenu;
	private ArrayList<String> moods = new ArrayList<String>();
	private String[] moodArray = {"Angry", "Happy", "Stressed", "Sad", "Nervous"};
	private JTable table;
	
	public mainUI() {
		
		initialize();
	}
	
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 695, 649);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		moodInitialize();
		createMoodCBox();
		mainUIComponents();
		createMainUITable();
		//createSubmissionTF();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		setupView();

	}
	//adds moods to arraylist from array. So new moods can be added to existing list using moodAdd()
	public void moodInitialize() {
		for (int i = 0; i < moodArray.length; i++) {
			moods.add(moodArray[i]);
		}
	}
	public void moodAdd(String mood) {
		moods.add(mood);
	}
	public void createMoodCBox() {
		moodMenu = new JComboBox();
		moodMenu.setBounds(203, 110, 232, 60);
		for (int i = 0; i < moods.size(); i++) {
			moodMenu.addItem(moods.get(i));
		}
		frame.getContentPane().setLayout(null);
		moodMenu.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		frame.getContentPane().add(moodMenu);
		
	}
	public void mainUIComponents() {
		JLabel moodLabel = new JLabel("Mood:");
		moodLabel.setBounds(78, 124, 100, 35);
		moodLabel.setHorizontalAlignment(SwingConstants.CENTER);
		moodLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		frame.getContentPane().add(moodLabel);
		
		JButton generateFoodBtn = new JButton("Generate");
		generateFoodBtn.setBounds(203, 469, 232, 49);
		generateFoodBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		frame.getContentPane().add(generateFoodBtn);
		
		JLabel mainUILabel = new JLabel("FoodMood");
		mainUILabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		mainUILabel.setBounds(254, 11, 194, 35);
		mainUILabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(mainUILabel);
	}
	public void createMainUITable() {
		table = new JTable() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};
		
		Object[] column_headers = {"Foods"}; // Column names
		DefaultTableModel tableModel = new DefaultTableModel(); // ADD foods associated with mood here
		tableModel.setColumnIdentifiers(column_headers);
		table.setModel(tableModel);
		table.doLayout();
		
		table.setGridColor(Color.black);
		table.setFont(new Font("Tahoma", Font.PLAIN, 17));
		table.setRowHeight(30);
		table.setAutoCreateRowSorter(true);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setBounds(78, 212, 535, 183);
		JScrollPane gPane = new JScrollPane(table);
		gPane.setBounds(203, 196, 232, 245);
		frame.getContentPane().add(gPane);
	}
	
	public void createSubmissionTF() {
		submitSubmissionTF = new JTextField();
		submitSubmissionTF.setHorizontalAlignment(SwingConstants.CENTER);
		submitSubmissionTF.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		submitSubmissionTF.setBounds(423, 514, 232, 60);
		submitSubmissionTF.setOpaque(false);
		submitSubmissionTF.setBackground(new java.awt.Color(0,0,0,1));
		frame.getContentPane().add(submitSubmissionTF);
		
		JLabel moodLabel = new JLabel("Mood:");
		moodLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		moodLabel.setBounds(70, 126, 77, 31);
		frame.getContentPane().add(moodLabel);
	}
	public static void setupView() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainUI window = new mainUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
