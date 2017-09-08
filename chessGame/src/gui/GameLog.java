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


public class GameLog extends JPanel{
	
	private JTextArea gameLog;
	public ArrayList<String> gameCourse;
	
	public GameLog(){
		
		gameCourse=new ArrayList<String>();
		gameLog=new JTextArea();
		gameLog.setLineWrap(true);
		//gameLog ;
		
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
	public void updateLog(char fileFrom,int rankFrom,char fileTo,int rankTo)
	{
		String update=Character.toString(fileFrom)+rankFrom+" "+Character.toString(fileTo)+rankTo;
		System.out.println(update);
		gameCourse.add(update);
		
	}
	
	public void updateLog(String value)
	{
		gameLog.append(value);
	}
	public void clearLog()
	{
		gameLog.setText(null);
	}

}
