package com.mesutgolcuk.shortestPath;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.TextArea;
/**
 * Console frame for main screen 
 * @author mesut
 *
 */
public class ConsoleFrame extends JFrame {

	private JPanel contentPane;
	TextArea textArea;

	public ConsoleFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(50, 0, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		textArea = new TextArea();
		contentPane.add(textArea, BorderLayout.CENTER);
	}
	/**
	 * add text to console
	 * @param s 
	 */
	public void addTextToJPane(String s){
		textArea.setText(textArea.getText()+s);
	}

}
