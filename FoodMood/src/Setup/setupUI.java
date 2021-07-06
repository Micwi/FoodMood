package Setup;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class setupUI {
	public static JFrame frame; // this is the frame I wanted to use for every UI when switching to different views - Show this to robert
	
	public setupUI() {
		createMainFrame();
	}
	public static void cleanFrame() {
		frame.getContentPane().removeAll();
		frame.repaint();
	}
	public static void createMainFrame() {
		frame = new JFrame();
		frame.setBounds(100, 100, 695, 649);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(0, 0, 0, 0));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
	}
	public static void alert(String text) {
		JFrame alertFrame = new JFrame();
		alertFrame.setAlwaysOnTop(true);
		JOptionPane.showMessageDialog(alertFrame, text, "FoodMood", JOptionPane.INFORMATION_MESSAGE);
	}
}
