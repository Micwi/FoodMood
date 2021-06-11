package Setup;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;







public class mainUI {

	public JFrame frame;
	private JComboBox<String> moodMenu;
	private ArrayList<String> moods = new ArrayList<String>();
	private String[] moodArray = {"Happy", "Stressed", "Sad"};
	private JTable table;
	private ArrayList<Object> obj = new ArrayList<Object>();
	private DefaultTableModel tableModel;
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
		addMoodButton();
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
		moodMenu.setBounds(203, 110, 296, 60);
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
		
		JLabel mainUILabel = new JLabel("FoodMood");
		mainUILabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		mainUILabel.setBounds(254, 43, 194, 35);
		mainUILabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(mainUILabel);
	}
	public void addMoodButton() {
		JButton addMoodBtn = new JButton("Add Mood");
		addMoodBtn.setBounds(426, 528, 232, 49);
		addMoodBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		frame.getContentPane().add(addMoodBtn);
		addMoodBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addMood addM = new addMood();
				addM.frame.setVisible(true);
			}
			
		});
	}
	public void createMainUITable() {
		table = new JTable() {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};
		try {
			Object[] column_headers = {"Foods"}; // Column names
			tableModel = new DefaultTableModel();
			tableModel.setColumnIdentifiers(column_headers);
			
			changeFoodList(moodMenu);//calls method that changes food displayed when clicking mood
			
			table.setModel(tableModel);
			table.doLayout();
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			table.setGridColor(Color.black);
			table.setFont(new Font("Tahoma", Font.PLAIN, 17));
			table.setRowHeight(30);
			table.setAutoCreateRowSorter(true);
			
			JScrollPane gPane = new JScrollPane();
			gPane.setBounds(203, 196, 296, 173);
			frame.getContentPane().add(gPane);
			gPane.setViewportView(table);
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}
	public void changeFoodList(JComboBox cBox) {
		cBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedMood = cBox.getSelectedItem().toString();
				System.out.println("Selected Mood = " + selectedMood);
				moodSelect(selectedMood);
			}
		});

			
					
		
	}
	public void moodSelect(String mood) {
		switch(mood) {
		
		case "Happy":
			happyMoodFile(tableModel);
			break;
		case "Sad":
			sadMoodFile(tableModel);
			break;
		case "Stressed":
			stressedMoodFile(tableModel);
			break;
		}
	}
		
	public void happyMoodFile(DefaultTableModel model) {
		try {
			String str = null;
			URL url = getClass().getResource("/TextFiles/Happy.txt"); // gets file path for File constructor
			File file = new File(url.getPath());
			BufferedReader br = new BufferedReader(new FileReader(file)); //reads from file
			Object[] obj = br.lines().toArray(); //takes values from file and puts into object array
			model.setRowCount(0); //clears the table before adding new data
			for(int i = 0; i < obj.length; i++) {
				str = obj[i].toString().trim();
				String[] data = str.split("\n");// String array containing all data without excess values
				
				model.addRow(data); // adds data to table model
			}
		}catch(Exception e) {
			System.out.println("Error: " + e);
		}
		
	}
	public void sadMoodFile(DefaultTableModel model) {
		try {
			String str = null;
			URL url = getClass().getResource("/TextFiles/Sad.txt"); // gets file path for File constructor
			File file = new File(url.getPath());
			BufferedReader br = new BufferedReader(new FileReader(file)); //reads from file
			Object[] obj = br.lines().toArray(); //takes values from file and puts into object array
			model.setRowCount(0); //clears the table before adding new data
			for(int i = 0; i < obj.length; i++) {
				str = obj[i].toString().trim();
				String[] data = str.split("\n");// String array containing all data without excess values
				model.addRow(data); // adds data to table model
			}
		}catch(Exception e) {
			System.out.println("Error: " + e);
		}
	}
	public void stressedMoodFile (DefaultTableModel model) {
		try {
			String str = null;
			URL url = getClass().getResource("/TextFiles/Stressed.txt"); // gets file path for File constructor
			File file = new File(url.getPath());
			BufferedReader br = new BufferedReader(new FileReader(file)); //reads from file
			Object[] obj = br.lines().toArray(); //takes values from file and puts into object array
			model.setRowCount(0); //clears the table before adding new data
			for(int i = 0; i < obj.length; i++) {
				str = obj[i].toString().trim();
				String[] data = str.split("\n");// String array containing all data without excess values
				model.addRow(data); // adds data to table model
			}
		}catch(Exception e) {
			System.out.println("Error: " + e);
		}
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
