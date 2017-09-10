package gui;


import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import java.util.ArrayList;
/**
 * Klasa {@link GameLog} kontroluje pole zawieraj¹ce informacje o grze. Zawiera wyœwietlane pole tekstowe i listê wykonanych ruchów.
 * @see JPanel
 */ 

public class GameLog extends JPanel{
	
	private JTextArea gameLog;
	public ArrayList<String> gameCourse;
	
	/**
	 * Konstruktor klasy {@link GameLog} Ustawia scrollowany panel zawieraj¹cy informacje o grze.
	 */
	public GameLog(){
		
		gameCourse=new ArrayList<String>();
		gameLog=new JTextArea();
		gameLog.setLineWrap(true);

		gameLog.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				gameLog.setEditable(false);
				
			}			
			@Override
			public void focusGained(FocusEvent e) {
				gameLog.setEditable(false);
				
			}
		});
		
		JScrollPane areaScrollPane = new JScrollPane(gameLog);
		areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		setLayout(new GridLayout(1,0,0,0));
		add(areaScrollPane);
	}
	/**
	 * Funkcja aktualizuje string o przekazane informacje o ruchu.
	 * @param fileFrom kolumna z której wychodzi bierka
	 * @param rankFrom wiersz s którego wychodzi bierka
	 * @param fileTo kolumna na które wchodzi bierka
	 * @param rankTo wiersz na które wchodzi bierka
	 */
	public void updateLog(char fileFrom,int rankFrom,char fileTo,int rankTo)
	{
		String update=Character.toString(fileFrom)+rankFrom+" "+Character.toString(fileTo)+rankTo;
		gameCourse.add(update);
		
	}
	/**
	 * Funkcja dodaje string do logu gry.
	 * @param value dodawany string
	 */
	
	public void updateLog(String value)
	{
		gameLog.append(value);
	}
	/**
	 * Funkcja czyœci log gry.
	 */
	public void clearLog()
	{
		gameLog.setText(null);
	}

}
