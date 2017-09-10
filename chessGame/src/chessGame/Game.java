package chessGame;
import pawns.*;

import java.awt.EventQueue;

import gui.ChessBoard;
import gui.ChessboardSpot;
import gui.GameLog;
import gui.MenuBar;
import gui.Square;
import gui.Window;
import pawns.GameRules;
import java.io.*;

/**
 * Klasa {@link Game} odpowiada za kontrolê menu, szachownicy oraz samej gry w szachy.
 *	Zawiera takie pola jak:<br>
 *	{@link FrameManager} - kontroler aplikacji <br>
 *	{@link GameRules} - kontrola zasad gry <br>
*	round - licznik rund
*	gameStart - informacja o stanie gry
*	counMoves - licznik ruchów
 *	options - mo¿liwe ruchy danej bierki<br>
 */ 
public class Game {
	
	public FrameManager frameManager;
	private GameRules gameRules;
	public int round;
	public boolean gameStart=false;
	public static int countMoves;

	/**
	 *	Konstruktor klasy {@link GameLog} tworzy obiekt klast {@link FrameManager}.

	 */
	public Game()
	{
		frameManager=new FrameManager();	
	}

	/**
	 *	Funkcja ustawia liczbê rund i ruchów oraz przygotowuje okno oraz szachownicê za pomoc¹ obiektu {@link FrameManager}.

	 */
	public void startGame()
	{
		round=0;
		countMoves=0;
		frameManager.prepareGui(this);
		frameManager.prepareChessboard();
	
	}
	/**
	 *	Funkcja ustawia stan gry na zakoñczony i koñczy grê pomoc¹ funkcji obiektu {@link FrameManager}.

	 */
	public void endGame()
	{
		gameStart=false;
		frameManager.endGame();
		
	}
	
	/**
	 *	Funkcja ustawia stan gry.
	 *@param gameRules zasady gry

	 */
	public void setGameStart(GameRules gameRules)
	{
		this.gameRules=gameRules;
		gameStart=true;
	}
	/**
	 *	Funkcja wykonuje ruch w "wirtualnej" tablicy {@link GameRules}. Zarz¹dza równie¿ liczbami ruchów i rund.
	 *
	 *@param piece pbierka wykonuj¹ca ruch
	 *@param rank wiersz na który ustawiana jest bierka
	 *@param file kolumna na któr¹ ustawiana jest bierka
	 */
	public void makeMove(Piece piece,int rank,char file)
	{
		System.out.println(rank);
		gameRules.setPiece(rank, Piece.fileNumber(file), piece);
		gameRules.setPiece(piece.rank, Piece.fileNumber(piece.file), null);
		if(countMoves%2==0)
			++round;	
		++countMoves;
			
	}
	/**
	 *	Funkcja zapisuj¹ca do pliku o nazwie podanej w parametrze, aktualny stan gry.
	 *
	 *@param fileName nazwa nowego zapisu gry
	 */
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
			frameManager.menuBar.addJlistElement(fileName+".txt");
			
		}catch (Exception e){
		      System.err.println("Error: " + e.getMessage());
	    }
	}
	
	/**
	 *	Funkcja wczytuj¹ca zapisan¹ grê z pliku o nazwie podanej w parametrze.
	 *
	 *@param fileName nazwa zapisu gry do wczytania
	 */
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

