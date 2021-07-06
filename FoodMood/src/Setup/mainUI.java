package Setup;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;



public class mainUI {

	private JComboBox<String> moodMenu;
	private ArrayList<String> moods = new ArrayList<String>();
	private String[] moodArray; //get all the files that exist in proper directory (Stored) to fill combobox values with proper names of moods
	private JTable table;
	private ArrayList<Object> obj = new ArrayList<Object>();
	private DefaultTableModel tableModel;
	
	public void initialize() {
		setupUI.cleanFrame();
		moodInitialize();
		createMoodCBox();
		mainUIComponents();
		createMainUITable();
		addMoodButton();
		setupUI.frame.setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		setupUI.createMainFrame();
		mainUI MUI = new mainUI();
		MUI.initialize();
	}
	//adds moods to arraylist from array. So new moods can be added to existing list using moodAdd()
	public void moodInitialize() {
		
		File moodDirectory = new File("FoodMood/src/TextFiles/");
		moodArray = moodDirectory.list();
		String [] names = new String[moodArray.length];
		for(int i = 0; i < moodArray.length;i++) {
			names[i] = moodArray[i].replace(".txt", "");
		}
		for (int i = 0; i < moodArray.length; i++) {
			moods.add(names[i]);
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
		
		setupUI.frame.getContentPane().setLayout(null);
		
		moodMenu.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		moodMenu.setSelectedItem(null);
		setupUI.frame.getContentPane().add(moodMenu);
		
	}
	public void mainUIComponents() {
		JLabel moodLabel = new JLabel("Mood:");
		moodLabel.setBounds(78, 124, 100, 35);
		moodLabel.setHorizontalAlignment(SwingConstants.CENTER);
		moodLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		setupUI.frame.getContentPane().add(moodLabel);
		
		JLabel mainUILabel = new JLabel("FoodMood");
		mainUILabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		mainUILabel.setBounds(254, 43, 194, 35);
		mainUILabel.setHorizontalAlignment(SwingConstants.CENTER);
		setupUI.frame.getContentPane().add(mainUILabel);
	}
	public void addMoodButton() {
		JButton addMoodBtn = new JButton("Add Mood");
		addMoodBtn.setBounds(426, 528, 232, 49);
		addMoodBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		setupUI.frame.getContentPane().add(addMoodBtn);
		addMoodBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addMood addM = new addMood();
				addM.initialize();
				setupUI.frame.setVisible(true);
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
			setupUI.frame.getContentPane().add(gPane);
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
		
		loadMoodFile(tableModel, mood);
	
	}
	public void loadMoodFile(DefaultTableModel model, String filename) {
		try {
			String str = null;
			 // gets file path for File constructor
			File file = new File("FoodMood/src/TextFiles/" + filename + ".txt");
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
	
	
	/*public static void setupView() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setupUI.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
}
