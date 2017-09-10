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
/**
 * Klasa {@link Window} ustawia okno aplikacji.
  * @see JPanel
 */ 
public class Window extends JFrame{
	/**
	 * Kontruktor klasy {@link Window} inicjalizuje okno.
	 */ 
	
	public Window()
	{
		initialize();
	}
	
	/**
	 * Funkcja ustawia granice, przycisk zamkniêcia i layout okna.
	 */ 
	
	private void initialize() {
		setBounds(100, 100, 900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		setVisible(true);
	}
	/**
	 * Funkcja dodaje {@link JLayeredPane} do okna.
	 * @param pane dodawany sektor
	 */ 
	public void addJLayeredPane(JLayeredPane pane)
	{
		getContentPane().add(pane);
		
	}
}
	
