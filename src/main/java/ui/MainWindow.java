package main.java.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import main.java.fileProcessor.RenameFile;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTextField path;

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setResizable(false);
		setTitle("Rename Songs");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		path = new JTextField();
		path.setBounds(120, 68, 203, 20);
		contentPane.add(path);
		path.setColumns(10);
		
		JLabel lblPathToFolder = new JLabel("Path to folder");
		lblPathToFolder.setBounds(43, 71, 77, 14);
		contentPane.add(lblPathToFolder);
		
		JTextArea log = new JTextArea();
		contentPane.add(log);
		log.setEditable(false);
		log.setBounds(107, 125, 307, 311);
		log.setBorder(BorderFactory.createEmptyBorder(5,10,3,0));
		
		JScrollPane log1 = new JScrollPane(log);
		log1.setSize(511, 258);
		log1.setLocation(120, 143);
		contentPane.add(log1);
		
		JButton btnConvert = new JButton("Convert");
		btnConvert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inputPath = path.getText();
				RenameFile renamer = new RenameFile(inputPath);
				renamer.rename(log);				
			}
		});
		btnConvert.setBounds(399, 67, 89, 23);
		contentPane.add(btnConvert);
		
		
	}
}
