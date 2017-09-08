package gui;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import java.awt.CardLayout;
import javax.swing.JLayeredPane;
import javax.swing.border.BevelBorder;


import net.miginfocom.swing.MigLayout;

import javax.swing.JDesktopPane;
import javax.swing.SwingUtilities;
import java.awt.EventQueue;

import java.lang.Exception;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Window extends JFrame{
	

	public Window()
	{
		setBackground(Color.DARK_GRAY);
		initialize();
	}
	
	private void initialize() {
		setBounds(100, 100, 900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		setVisible(true);
	}
	
	public void addJLayeredPane(JLayeredPane pane)
	{
		getContentPane().add(pane);
		
	}
}
	
