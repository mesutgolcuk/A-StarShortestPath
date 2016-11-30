package com.mesutgolcuk.shortestPath;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.TextArea;
/**
 * Queue related user interface
 * @author mesut
 *
 */
public class QueueFrame extends JFrame {

	private JPanel contentPane;
	TextArea textArea;

	public QueueFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 100, 800, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		textArea = new TextArea();
		contentPane.add(textArea, BorderLayout.CENTER);
	}
	/**
	 * Add text to text area
	 * @param s
	 */
	public void addTextToJPane(String s){
		textArea.setText(textArea.getText()+s);
	}

}
