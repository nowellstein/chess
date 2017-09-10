package gui;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JLabel;
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
 * Klasa {@link Square} jest jednym z podstawowych element�w szachownicy. Stanowi o jej konkretnym polu. Zawiera takie pola jak:<br>
 * file - kolumna pola na szachownicy<br>
 * rank - wiersz pola na szachownicy<br>
 * 	highlight - informacj� o stanie pod�wietlenia gry<br>
 * 	color - informacj� o kolorze pola <br>
 * occupied - informacje o tym czy pole jest zaj�te
 * @see JPanel
 */


public class Square extends JPanel {
	
	public char file='z';
	public int rank=-1;
	boolean highlight=false;
	private Color color;
	private boolean occupied=false;
	/**
	 * Konstruktor {@link Square} ustawia layout pola.
	 *
	 */

	public Square()
	{
		setLayout(new GridLayout(1, 0, 0, 0));
	}
	
	/**
	 * Funkcja ustawia czy pole jest zaj�te.
	 * @param o stan do ustawienia 
	 */
	public void setOccupied(boolean o)
	{
		this.occupied=o;
	}
	/**
	 * Funkcja zwraca czy pole jest zaj�te.
	 * @return prawd� je�li zaj�te
	 *
	 */
	public boolean getOccupied()
	{
		return this.occupied;
	}
	
	/**
	 * Funkcja ustawia pod�wietlenie pola.
	 *
	 */
	public void higlitedSquare() 
	{
		highlight=true;
		setBackground(color);

	}
	/**
	 * Funkcja odznacza pod�wietlenie pola.
	 */
	public void unhiglitedSquare() 
	{
		highlight=false;
		setBackground(color);

	}
	/**
	 * Funkcja ustawia kolor.
	 * @param color warto�� pola kolor
	 */
	public void setColor(Color color)
	{
		this.color=color;
		setBackground(color);
	}
	/**
	 * Funkcja zwraca pole koloru.
	 * @return {@link Color}
	 */
	public Color getColor()
	{
		return this.color;
	}
	/**
	 *	Funkcja zwracaj�ca warto�� numeryczn� kolumny szachownicy.
	 *
	 * @param file element typu char do zamiany na int
	 * @return      zwraca warto�� numeryczn� kolumny szachownicy
	 */
	
	public static int fileNumber(char file)
	{
		for(int i=0;i<8;++i)
			if(file==(char)('a'+i))
			{
				return i+1;
			}
		return 0;
	}
	/**
	 *	Funkcja zwracaj�ca warto�� symboliczn� kolumny szachownicy.
	 *
	 * @param file element typu int do zamiany na char
	 * @return      zwraca warto�� symboliczn� kolumny szachownicy
	 */
	public static char numberFile(int file)
	{
		
		return (char)('a'-1+file);
	}
	/**
	 *	Nadpisana metoda pozwalaj�ca na rysowanie i pod�wietlanie pola.
	 *
	 * @param g informacje potrzebne do rysowania
	 * @see         Graphics
	 */
	    @Override
	    public void paintComponent(Graphics g) { //dokonczyc
			super.paintComponent(g);
			if(highlight)
			{
				g.setColor(Color.BLACK);
				g.draw3DRect(0,0,getWidth()-1,getHeight()-1,false);
				g.drawRect(1,1,getWidth()-2,getHeight()-2);
				g.drawRect(2,2,getWidth()-4,getHeight()-4);	
			}
	
			

		}
	    


}

