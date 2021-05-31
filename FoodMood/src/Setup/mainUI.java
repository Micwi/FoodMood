package Setup;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.*;





public class mainUI {

	public JFrame frame;
	private JButton submitSubmission;
	private JTextField submitSubmissionTF;
	private JComboBox<String> moodMenu;
	private ArrayList<String> moods = new ArrayList<String>();
	private String[] moodArray = {"Angry", "Happy", "Stressed", "Sad", "Nervous"};
	
	public mainUI() {
		
		initialize();
	}
	
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 695, 649);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		moodInitialize();
		createMoodCBox();
		createSubmissionTF();
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
		for (int i = 0; i < moods.size(); i++) {
			moodMenu.addItem(moods.get(i));
		}
		moodMenu.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		moodMenu.setBounds(320, 232, 232, 60);
		frame.getContentPane().add(moodMenu);
	}
	public void createSubmissionTF() {
		submitSubmissionTF = new JTextField();
		submitSubmissionTF.setHorizontalAlignment(SwingConstants.CENTER);
		submitSubmissionTF.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		submitSubmissionTF.setBounds(320, 335, 232, 60);
		submitSubmissionTF.setOpaque(false);
		submitSubmissionTF.setBackground(new java.awt.Color(0,0,0,1));
		frame.getContentPane().add(submitSubmissionTF);
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
