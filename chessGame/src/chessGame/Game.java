package chessGame;
import pawns.*;

import java.awt.EventQueue;

import gui.Square;
import pawns.GameRules;
import java.io.*;


public class Game {
	
	public FrameManager frameManager;
	public Player playingWhite;
	public Player playingBlack;
	public GameRules gameRules;
	
	public int round;
	public boolean gameStart=false;
	public static int countMoves;

	
	public Game()
	{
		playingWhite=new Player("white");
		playingBlack=new Player("black");
		gameRules=new GameRules();
		frameManager=new FrameManager();	
	}

	
	public void startGame()
	{
		round=0;
		countMoves=0;
		frameManager.prepareGui(this);
		gameRules.initialize();
		frameManager.prepareChessboard();
	
	}
	public void endGame()
	{
		gameStart=false;
		gameRules.clearGameRules();
		frameManager.endGame();
		
	}
	
	public void setGameStart()
	{
		gameStart=true;
	}
	
	public void makeMove(Piece piece,int rank,char file)
	{
		gameRules.Pieces[rank][Piece.fileNumber(file)]=piece;
		gameRules.Pieces[piece.rank][Piece.fileNumber(piece.file)]=null;
		if(countMoves%2==0)
			++round;	
		++countMoves;
			
	}
	
	public void saveGame(String fileName)
	{
		try
		{
			File file =new File("saves/"+fileName+".txt");
			file.createNewFile();
			
			FileWriter writer=new FileWriter(file);
			for(int i=0;i<frameManager.gameLog.gameCourse.size();++i)
			{
				writer.write(frameManager.gameLog.gameCourse.get(i));
				writer.write(System.getProperty( "line.separator" ));
			}
			writer.flush();
			writer.close();
			
			System.out.print(fileName);
		}catch (Exception e){
		      System.err.println("Error: " + e.getMessage());
	    }
	}
	
	public void readGame(String fileName)
	{
		try
		{
			File file =new File("saves/"+fileName);
			FileReader reader=new FileReader(file);
			BufferedReader bufReader=new BufferedReader(reader);
			String temp=bufReader.readLine();
			
			if(!gameStart)
			{
				frameManager.startGame();
			}
			else
			{
				endGame();
				frameManager.startGame();
			}
			
			int rankFrom,rankTo;
			char fileFrom,fileTo;
			do{
				fileFrom=temp.charAt(0);
				String tempInt=temp.substring(1, 2);
				rankFrom=Integer.parseInt(tempInt);
				
				fileTo=temp.charAt(3);
				tempInt=temp.substring(4, 5);
				rankTo=Integer.parseInt(tempInt);
				frameManager.setGame(fileFrom, rankFrom, fileTo, rankTo);
				
				temp=bufReader.readLine();
			}while(temp!=null);
			
			bufReader.close();
			reader.close();
			
		}catch (Exception e){
		      System.err.println("Error: " + e.getMessage());
	    }
	}
		
	

				
}

