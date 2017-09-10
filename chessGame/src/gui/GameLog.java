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
 * Klasa {@link GameLog} kontroluje pole zawieraj�ce informacje o grze. Zawiera wy�wietlane pole tekstowe i list� wykonanych ruch�w.
 * @see JPanel
 */ 

public class GameLog extends JPanel{
	
	private JTextArea gameLog;
	public ArrayList<String> gameCourse;
	
	/**
	 * Konstruktor klasy {@link GameLog} Ustawia scrollowany panel zawieraj�cy informacje o grze.
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
	 * @param fileFrom kolumna z kt�rej wychodzi bierka
	 * @param rankFrom wiersz s kt�rego wychodzi bierka
	 * @param fileTo kolumna na kt�re wchodzi bierka
	 * @param rankTo wiersz na kt�re wchodzi bierka
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
	 * Funkcja czy�ci log gry.
	 */
	public void clearLog()
	{
		gameLog.setText(null);
	}

}
