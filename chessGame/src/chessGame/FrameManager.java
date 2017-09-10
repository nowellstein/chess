package chessGame;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import pawns.*;
import pawns.GameRules.ToHighlight;
import gui.ChessBoard;
import gui.ChessboardSpot;
import gui.Window;
import net.miginfocom.swing.MigLayout;
import gui.Square;
import gui.GameLog;
import gui.MenuBar;
import gui.ChessboardSpot;
/**
 * Klasa {@link FrameManager} odpowiada za kontrol� menu, szachownicy oraz samej gry w szachy.
 *	Zawiera takie pola jak:<br>
 *	{@link Window} - odpowiada za kontrol� ca�ego okna w kt�rym uruchamia si� aplikacja <br>
 *	{@link ChessBoard} - odpowiada za kontrol� pola szachownicy <br>
 *	{@link GameRules} - kontrola zasad gry <br>
 *	{@link MenuBar} - zarz�dza menu gru <br>
 *	{@link GameLog} - zarz�da wy�wietlaniem informacji o aktualnej grze <br>
 *	{@link ChessboardSpot} - zawiera miejsce na pole szachownicy<br>
 *	options - mo�liwe ruchy danej bierki<br>
 */ 

public class FrameManager {
	
	public Window window;
	
	public Game game;
	private ChessBoard board;
	private GameRules gameRules;
	public MenuBar menuBar;
	public GameLog gameLog;
	private ChessboardSpot chessboardSpot;
	private ArrayList<ToHighlight> options;
	
	
	public boolean currentPlayer=true;
	
	/**
	 *	Konstruktor klasy FrameManager.

	 */
	public FrameManager()
	{
		
	}
	
	/**
	 *	Funkcja powo�uje do �ycia wszystkie elementy okna, menu, miejsca szachownicy oraz pola informacji.
	 *@param game po�redniczy pomi�dzy wywo�aniem w mainie a zarz�dzaniem aplikacj�
	 */
	public void prepareGui(Game game)
	{
		this.game=game;
		window=new Window();
		board=new ChessBoard(this);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setLayout(new MigLayout("", "[10%:12%:12.5%,growprio 25,grow,shrinkprio 12,fill][60%:65%:66.6%,growprio 25,grow,shrinkprio 66,fill][18%:20%:20.8%,growprio 25,grow,shrinkprio 20,fill]", "[100%:100%:100%,grow,fill]"));
		window.addJLayeredPane(layeredPane);
		menuBar=new MenuBar(this);
		gameLog=new GameLog();
		chessboardSpot= new ChessboardSpot();
		layeredPane.add(gameLog);
		layeredPane.add(chessboardSpot);
		layeredPane.add(menuBar);

	}
	
	/**
	 *	Funkcja dodaje na miejsce szachownicy przygotowane pola.
	 */
	
	public void prepareChessboard()
	{
		chessboardSpot.add(board);
	}
	
	/**
	 *	Funkcja de facto rozpoczynaj�ca gre, wywo�ywana za klikni�ciem "NOWA GRA". <br>
	 *	Ustawia gracza, zasady gry i bierki na miejscach a nast�pnie od�wie�a okno.
	 */
	public void startGame()
	{
		currentPlayer=true;
		this.gameRules=new GameRules();
		board.addPawns(new Player("white"),new Player("black"),gameRules);
		game.round=1;
		game.setGameStart(gameRules);
		board.revalidate();
		board.repaint();	
	}
	
	/**
	 *	Funkcja ko�cz�ca gr�, czy�ci pole szachowe i ustawia szachowinc� na now� parti�.
	 */
	public void endGame()
	{
		board.clearChessboard();
		//gameLog.clearLog();
		menuBar.setGameStarted(false);
		prepareChessboard();
		board.revalidate();
		board.repaint();
	}
	/**
	 *	Funkcja pobiera ruch z klasy Chessboard i przetwarza go na wy�wielanej szachownicy.<br>
	 *	Sprawdza czy wybrane pole jest w�r�d mo�liwych do wykonania, je�li tak to wykonuje ruch i zmienia stron�. <br>
	 *Dodatkowo sprawdza czy w przypadku wykonanego ruchu zosta� zaszachowany Kr�l przeciwnika. <br>
	 *Odpowiada r�wnie� za aktualizacj� informacji o grze.
	 *@param piece bierka wykonuj�ca ruch
	 *@param file numer kolumny gdzie na kt�r� wykonywany jest ruch 
	 *@param rank numer wiersza gdzie na kt�r� wykonywany jest ruch 
	 *@param options lista opcji mo�liwych ruch�w do wykonania
	 */
	public void getMove(Piece piece,char file,int rank, ArrayList<ToHighlight> options ){
		
		for(int i=0;i<options.size();++i)
		{
			if( options.get(i).file==file && options.get(i).rank==rank)
			{
				if(Game.countMoves%2==0)
					gameLog.updateLog("\n"+ game.round+". ");
				gameLog.updateLog("\n"+piece.file+""+piece.rank+" -> "+file+""+rank+" ");
				gameLog.updateLog(piece.file,piece.rank,file,rank);
				game.makeMove(piece,rank,file);
				board.Squares[piece.rank][Square.fileNumber(piece.file)].setOccupied(false);
				piece.rank=rank;
				piece.file=file;
				board.Squares[rank][Square.fileNumber(file)].add(piece);
				board.Squares[rank][Square.fileNumber(file)].setOccupied(true);
				if(checkCheck(piece))
				{
					if(piece.color)
						board.setKingCheck(!piece.color);
					else
						board.setKingCheck(piece.color);			
					
				}
				changeSide();				
			}
		}

		board.repaint();
		board.revalidate();
		
	}
	/**
	 *	Funkcja pobiera ruch z klasy Chessboard i przetwarza go na wy�wielanej szachownicy.<br>
	 *	Sprawdza czy wybrane pole jest w�r�d mo�liwych do wykonania, je�li tak to usuwa bierk� z wybranego pola.<br>
	 *Dodatkowo sprawdza czy nie zosta� pobit Kr�l. Je�li tak to ko�czy gr�.
	 *@param piece bierka wykonuj�ca ruch
	 *@param file numer kolumny gdzie na kt�r� wykonywane jest bicie
	 *@param rank numer wiersza gdzie na kt�r� wykonywany jest bicie
	 *@param options lista opcji mo�liwych ruch�w do wykonania
	 *@param toBeat bity pion kt�ry zostanie usuni�ty
	 *@return zwraca prawd� je�li wykonano bicie 
	 */
	public boolean beatPiece(Piece piece,char file,int rank, ArrayList<ToHighlight> options,Piece toBeat ){
		
		for(int i=0;i<options.size();++i)
		{
			if(options.get(i).file==file && (options.get(i).rank==rank))
			{
				//System.out.println("bity");
				gameRules.setPiece(rank, Piece.fileNumber(file), null);
				board.Squares[rank][Square.fileNumber(file)].remove(toBeat);
				if(toBeat instanceof King)
				{
					if(toBeat.color)
					{
						JLabel jLabel=new JLabel("Wygra�y czarne!");
						JOptionPane.showMessageDialog(null,jLabel,"Koniec Gry",JOptionPane.OK_OPTION);
					}
					else
					{
						JLabel jLabel=new JLabel("Wygra�y bia�e!");
						JOptionPane.showMessageDialog(null,jLabel,"Koniec Gry",JOptionPane.OK_OPTION);		
					}
				}
				return true;
			}
		}
		
		
		return false;
	}
	/**
	 *	Funkcja dostaje koordynaty wczytaj gry i na ich podstawie wykonuje ruch a je�li trzeba, bicie.
	 *@param fileFrom numer kolumny z kt�rej wychodzi bierka
	 *@param rankFrom numer wiersza z kt�rego wychodzi bierka
	 *@param fileTo numer kolumny na kt�r� wchodzi bierka
	 *@param rankTo  nr wierza na kt�ry wchodzi bierka
	 */
	
	public void setGame(char fileFrom,int rankFrom,char fileTo,int rankTo)
	{
		Piece piece=(Piece)board.Squares[rankFrom][Square.fileNumber(fileFrom)].getComponent(0);
		if(board.checkIfEmpty(fileTo, rankTo))
			getMove(piece,fileTo,rankTo,gameRules.returnLegalMoves(piece));
		else
		{
			Piece toBeat=(Piece)board.Squares[rankTo][Square.fileNumber(fileTo)].getComponent(0);
			this.options=gameRules.returnLegalMoves(piece);
			beatPiece(piece, fileTo, rankTo, gameRules.returnLegalMoves(piece), toBeat);
			getMove(piece,fileTo,rankTo,options);
			options=null;
		}
	}
	
	/**
	 *	Funkcja sprawdza czy ostatnio wykonany ruch na szachownicy nie sprawia, �e przeciwny Kr�l jest zaszachowany.
	 *@param piece bierka kt�ra mo�e szachowa� Kr�la
	 *@return zwraca prawd� je�li Kr�l w szachu.
	 */
	
	public boolean checkCheck(Piece piece)
	{
		ArrayList<ToHighlight> options=new ArrayList<ToHighlight>();
		options=gameRules.returnLegalMoves(piece);
		for(int i=0;i<options.size();++i)
		{
			ToHighlight check=options.get(i);
			Piece king=gameRules.getPiece(check.rank,Square.fileNumber(check.file));
			if(king instanceof King && piece.color!=king.color)
				return true;
		}
		
		return false;
	}
	
	
	/**
	 *	Funkcja zmienia stron� wykonuj�c� ruch.
	 */
	private void changeSide()
	{
		currentPlayer=!currentPlayer;
	}
	

}
