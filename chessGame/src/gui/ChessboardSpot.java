package gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
/**
 * Klasa {@link ChessboardSpot} zawiera miejsce na szachownic� w oknie aplikacji.
 * @see JPanel
 */ 
public class ChessboardSpot extends JPanel{
	
	/**
	 *	Konstruktor klasy ChessboardSpot ustawia layout panelu.

	 */
	public ChessboardSpot()
	{
		setLayout(new GridLayout(1,0));
	
	}

}
