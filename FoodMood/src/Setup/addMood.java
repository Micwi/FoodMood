package Setup;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class addMood {

	private JTextField addMoodTF;
	private JTextField addFoodTF;
	private JTable table;
	private DefaultTableModel tableModel;
	private File file;
	private String addedMood;
	private String addedFood;
	
	
	public void initialize() {
		setupUI.cleanFrame();
		
		addMenuComponents();
	}

	public void addMenuComponents() {
		JLabel addMoodUILabel = new JLabel("Add Mood");
		addMoodUILabel.setBounds(254, 43, 194, 35);
		addMoodUILabel.setHorizontalAlignment(SwingConstants.CENTER);
		addMoodUILabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		setupUI.frame.getContentPane().add(addMoodUILabel);

		addStuffToFile();
		finishAdding();
	}

	public void addStuffToFile() {
		JLabel moodLabel = new JLabel("Mood Name:");
		moodLabel.setBounds(52, 124, 126, 35);
		moodLabel.setHorizontalAlignment(SwingConstants.CENTER);
		moodLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		setupUI.frame.getContentPane().add(moodLabel);

		 // array for new added food
		// table
		table = new JTable() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};
		try {
			Object[] column_headers = { "Foods" }; // Column names
			tableModel = new DefaultTableModel();
			tableModel.setColumnIdentifiers(column_headers);

			table.setModel(tableModel);
			table.doLayout();
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			table.setGridColor(Color.black);
			table.setFont(new Font("Tahoma", Font.PLAIN, 17));
			table.setRowHeight(30);
			table.setAutoCreateRowSorter(true);

			JScrollPane gPane = new JScrollPane(table);
			gPane.setBounds(203, 196, 296, 173);
			setupUI.frame.getContentPane().add(gPane);
			gPane.setViewportView(table);
		} catch (Exception e) {
			System.out.println(e);
		}
		// food adding
		addFoodTF = new JTextField();
		addFoodTF.setHorizontalAlignment(SwingConstants.CENTER);
		addFoodTF.setFont(new Font("Tahoma", Font.BOLD, 20));
		addFoodTF.setColumns(10);
		addFoodTF.setBounds(203, 399, 211, 60);
		setupUI.frame.getContentPane().add(addFoodTF);

		addMoodTF = new JTextField();
		addMoodTF.setFont(new Font("Tahoma", Font.BOLD, 20));
		addMoodTF.setHorizontalAlignment(SwingConstants.CENTER); 
		addMoodTF.setBounds(203, 110, 296, 60);
		setupUI.frame.getContentPane().add(addMoodTF);
		addMoodTF.setColumns(10);
		JButton addFoodButton = new JButton(" Add Food");
		addFoodButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		addFoodButton.setBounds(440, 410, 126, 42);
		setupUI.frame.getContentPane().add(addFoodButton);
		
		
		
		addFoodButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
			
				addedFood = addFoodTF.getText(); // add to txt file for added mood
				addFoodTF.setText("");
				System.out.println("New Food: "+ addedFood);
				
				
				tableModel.addRow(new String[] {addedFood});
				
				System.out.println("Food Array Contents: " + tableModel.getDataVector());
				for(Vector currentElement : tableModel.getDataVector()) {
					System.out.println("s value: " + currentElement.get(0));
				}
			}
		});
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		backButton.setBounds(20, 43, 100, 35);
		setupUI.frame.getContentPane().add(backButton);
		backButton.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				mainUI MUI = new mainUI();
				MUI.initialize();
				setupUI.frame.setVisible(true);
				
			}
			
		});
	}

	public void finishAdding() {
		JButton addMood_FinishBtn = new JButton("Finish");
		addMood_FinishBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		addMood_FinishBtn.setBounds(288, 504, 126, 42);
		setupUI.frame.getContentPane().add(addMood_FinishBtn);
		addMood_FinishBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// new file creation
				addedMood = addMoodTF.getText();// set name of new text file to this name .txt
				file = new File("FoodMood/src/TextFiles/" + addedMood + ".txt");

				try {
					FileWriter writer = new FileWriter(file);
					for(Vector currentElement : tableModel.getDataVector()) {
						writer.write(currentElement.get(0).toString());
						writer.write("\n");
					}
					
					writer.close();
					
					mainUI MUI = new mainUI();
					MUI.initialize();
					setupUI.frame.setVisible(true);
					
					if(addMoodTF.getText().isEmpty()) {
						setupUI.alert("Mood field left blank!");
					}
					
				} catch (FileNotFoundException ex) {
					System.out.println("The file was not found on this system.");
				} catch (IOException exc) {
					exc.printStackTrace();
				}

			}
		});
	}
	
}
