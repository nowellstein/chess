package gui;

import java.awt.*;

import javax.swing.JPanel;

import javax.swing.ListSelectionModel;

import chessGame.FrameManager;

import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.io.*;
import javax.swing.JLabel;
import java.awt.event.*;

public class MenuBar extends JPanel{
	
	private JList list;
	private FrameManager frameManager;
	private boolean gameStarted=false;
	private String fileName=null;
	private int ifRead=1;
	
	public MenuBar(FrameManager frameManager){
		

		this.frameManager=frameManager;
		list=new JList<String>(readSavedGames());
		list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		
		setLayout(new GridBagLayout());
		GridBagConstraints c= new GridBagConstraints();

		
		JButton start=new JButton("NOWA GRA");
		c.insets=new Insets(5,5,5,5);
		c.ipady=20;
		c.weightx=0.1;
		c.fill=GridBagConstraints.HORIZONTAL;
		c.gridy=0;
		add(start,c);
		
		start.addMouseListener(new java.awt.event.MouseAdapter()
	    {
	        public void mouseClicked(java.awt.event.MouseEvent evt)
	        {
	        	if(!gameStarted)
	        	{
	        		frameManager.startGame();
	        		gameStarted=true;
	        	}
	        	else
	        	{
	        		frameManager.game.endGame();
	        		gameStarted=false;
	        		
	        	}
	        }
	    });
		
		
		JButton read=new JButton("WCZYTAJ");
		c.gridy=1;
		add(read,c);
		
		read.addMouseListener(new java.awt.event.MouseAdapter()
	    {
	        public void mouseClicked(java.awt.event.MouseEvent evt)
	        {
	        	if(frameManager.game.gameStart)
	        	{
	        		ifRead=JOptionPane.showConfirmDialog(null, new JLabel("Utracisz aktualn¹ grê. Chcesz Kontynuowaæ?"),"Wczytaj", JOptionPane.YES_NO_OPTION);
	        			
	        		if(ifRead==0)
	        		{
	        			if((String)list.getSelectedValue()!=null)
	        			{
	        				frameManager.game.readGame((String)list.getSelectedValue());
	        				ifRead=1;
	        			}
	        		}
	        		else
	        			return;
	        	}
	        	else if((String)list.getSelectedValue()!=null)
    			{
	        		frameManager.game.readGame((String)list.getSelectedValue());
    			}
	        }
	    });
		
		
		JButton save=new JButton("ZAPISZ");
		c.gridy=3;
		add(save,c);
		
		save.addMouseListener(new java.awt.event.MouseAdapter()
	    {
	        public void mouseClicked(java.awt.event.MouseEvent evt)
	        {
	        	if(!frameManager.game.gameStart)
	        		return;
	        	else
	        	{
	        		while(fileName==null)
	        		{
	        			fileName=JOptionPane.showInputDialog(null,new JLabel("Podaj nazwê pliku"),"Zapisz",JOptionPane.OK_CANCEL_OPTION);
	        		}	        		
	        		frameManager.game.saveGame(fileName);
	        		fileName=null;
	        	}
	        }
	    });
			
		c.gridy=2;
		c.ipady=200;
		add(listScroller,c);
		
	
		
	}
	
	public String[] readSavedGames()
	{		
		File file=new File("saves/");
	
		FilenameFilter textFilter = new FilenameFilter() {
	        public boolean accept(File dir, String name) {
	            return name.toLowerCase().endsWith(".txt");
	        }
	    };
	    File[] files = file.listFiles(textFilter);

	    int i=0;
	    for(File f : files)
	    {
	    		++i;
	    }
	    String[] returnFiles=new String[i];
	    i=0;
	    for(File f : files)
	    {
	    		returnFiles[i]=f.getName();
	    		++i;
	    }
		
		return returnFiles;
	}
	
	public void setGameStarted(boolean value)
	{
		gameStarted=value;
	}
	



}
