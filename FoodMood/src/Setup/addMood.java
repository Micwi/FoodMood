package Setup;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class addMood {

	public JFrame frame;
	private JTextField addMoodTF;
	private JTextField addFoodTF;
	private JTable table;
	private DefaultTableModel tableModel;
	private File file;
	private String addedMood;
	private String addedFood;

	public addMood() {
		frame = new JFrame();
		frame.setBounds(100, 100, 695, 649);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		addMenuComponents();
	}

	public void addMenuComponents() {
		frame.getContentPane().setLayout(null);
		JLabel addMoodUILabel = new JLabel("Add Mood");
		addMoodUILabel.setBounds(254, 43, 194, 35);
		addMoodUILabel.setHorizontalAlignment(SwingConstants.CENTER);
		addMoodUILabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		frame.getContentPane().add(addMoodUILabel);

		addStuffToFile();

	}

	public void addStuffToFile() {
		JLabel moodLabel = new JLabel("Mood Name:");
		moodLabel.setBounds(52, 124, 126, 35);
		moodLabel.setHorizontalAlignment(SwingConstants.CENTER);
		moodLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		frame.getContentPane().add(moodLabel);

		ArrayList<String> newFoodArray = new ArrayList<String>(); // array for new added food
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
			frame.getContentPane().add(gPane);
			gPane.setViewportView(table);
		} catch (Exception e) {
			System.out.println(e);
		}
		// food adding
		addFoodTF = new JTextField();
		addFoodTF.setHorizontalAlignment(SwingConstants.CENTER);
		addFoodTF.setFont(new Font("Tahoma", Font.BOLD, 20));
		addFoodTF.setColumns(10);
		addFoodTF.setBounds(254, 409, 211, 60);
		frame.getContentPane().add(addFoodTF);
		addedFood = addFoodTF.getText(); // add to txt file for added mood

		addMoodTF = new JTextField();
		addMoodTF.setFont(new Font("Tahoma", Font.BOLD, 20));
		addMoodTF.setHorizontalAlignment(SwingConstants.CENTER); //Figure out why addedMood TF doesnt display name and why file button isnt putting data in table. And why file isnt saved in right place 
		//(textfiles)
		addMoodTF.setBounds(203, 110, 296, 60);
		frame.getContentPane().add(addMoodTF);
		addMoodTF.setColumns(10);
		addedMood = "String " + addMoodTF.getText(); // set name of new text file to this name .txt
		System.out.println(addedMood);
		JButton addFoodButton = new JButton("Add");
		addFoodButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		addFoodButton.setBounds(296, 493, 126, 42);
		frame.getContentPane().add(addFoodButton);
		addFoodButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// new file creation
				file = new File(addedMood + ".txt");
				System.out.println("Path: " + file.getAbsolutePath());
				
				try {
					BufferedWriter writer = new BufferedWriter(new FileWriter(file));
					writer.write(addedFood);
					System.out.println("Hello");
					writer.close();
				} catch (FileNotFoundException ex) {
					System.out.println("The file was not found on this system.");
				} catch (IOException exc) {
					exc.printStackTrace();
				}
				tableModel.insertRow(0, new Object[] { addedFood });
			}
		});

	}
}
