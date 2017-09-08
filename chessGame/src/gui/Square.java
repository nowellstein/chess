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



public class Square extends JPanel {
	
	public char file='z';
	public int rank=-1;
	boolean highlight=false;
	private Color color;
	
	public Square()
	{
		setLayout(new GridLayout(1, 0, 0, 0));
	}
	
	public void higlitedSquare() //dopisz
	{
		highlight=true;
		setBackground(color);

	}
	public void unhiglitedSquare() //dopisz
	{
		highlight=false;
		setBackground(color);

	}
	
	public void setColor(Color color)
	{
		this.color=color;
		setBackground(color);
	}
	public Color getColor()
	{
		return this.color;
	}
	
	public static int fileNumber(char file)
	{
		for(int i=0;i<8;++i)
			if(file==(char)('a'+i))
			{
				return i+1;
			}
		return 0;
	}
	public static char numberFile(int file)
	{
		
		return (char)('a'-1+file);
	}
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

