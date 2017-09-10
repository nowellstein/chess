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
 * Klasa {@link FrameManager} odpowiada za kontrolê menu, szachownicy oraz samej gry w szachy.
 *	Zawiera takie pola jak:<br>
 *	{@link Window} - odpowiada za kontrolê ca³ego okna w którym uruchamia siê aplikacja <br>
 *	{@link ChessBoard} - odpowiada za kontrolê pola szachownicy <br>
 *	{@link GameRules} - kontrola zasad gry <br>
 *	{@link MenuBar} - zarz¹dza menu gru <br>
 *	{@link GameLog} - zarz¹da wyœwietlaniem informacji o aktualnej grze <br>
 *	{@link ChessboardSpot} - zawiera miejsce na pole szachownicy<br>
 *	options - mo¿liwe ruchy danej bierki<br>
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
	 *	Funkcja powo³uje do ¿ycia wszystkie elementy okna, menu, miejsca szachownicy oraz pola informacji.
	 *@param game poœredniczy pomiêdzy wywo³aniem w mainie a zarz¹dzaniem aplikacj¹
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
	 *	Funkcja de facto rozpoczynaj¹ca gre, wywo³ywana za klikniêciem "NOWA GRA". <br>
	 *	Ustawia gracza, zasady gry i bierki na miejscach a nastêpnie odœwie¿a okno.
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
	 *	Funkcja koñcz¹ca grê, czyœci pole szachowe i ustawia szachowincê na now¹ partiê.
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
	 *	Funkcja pobiera ruch z klasy Chessboard i przetwarza go na wyœwielanej szachownicy.<br>
	 *	Sprawdza czy wybrane pole jest wœród mo¿liwych do wykonania, jeœli tak to wykonuje ruch i zmienia stronê. <br>
	 *Dodatkowo sprawdza czy w przypadku wykonanego ruchu zosta³ zaszachowany Król przeciwnika. <br>
	 *Odpowiada równie¿ za aktualizacjê informacji o grze.
	 *@param piece bierka wykonuj¹ca ruch
	 *@param file numer kolumny gdzie na któr¹ wykonywany jest ruch 
	 *@param rank numer wiersza gdzie na któr¹ wykonywany jest ruch 
	 *@param options lista opcji mo¿liwych ruchów do wykonania
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
	 *	Funkcja pobiera ruch z klasy Chessboard i przetwarza go na wyœwielanej szachownicy.<br>
	 *	Sprawdza czy wybrane pole jest wœród mo¿liwych do wykonania, jeœli tak to usuwa bierkê z wybranego pola.<br>
	 *Dodatkowo sprawdza czy nie zosta³ pobit Król. Jeœli tak to koñczy grê.
	 *@param piece bierka wykonuj¹ca ruch
	 *@param file numer kolumny gdzie na któr¹ wykonywane jest bicie
	 *@param rank numer wiersza gdzie na któr¹ wykonywany jest bicie
	 *@param options lista opcji mo¿liwych ruchów do wykonania
	 *@param toBeat bity pion który zostanie usuniêty
	 *@return zwraca prawdê jeœli wykonano bicie 
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
						JLabel jLabel=new JLabel("Wygra³y czarne!");
						JOptionPane.showMessageDialog(null,jLabel,"Koniec Gry",JOptionPane.OK_OPTION);
					}
					else
					{
						JLabel jLabel=new JLabel("Wygra³y bia³e!");
						JOptionPane.showMessageDialog(null,jLabel,"Koniec Gry",JOptionPane.OK_OPTION);		
					}
				}
				return true;
			}
		}
		
		
		return false;
	}
	/**
	 *	Funkcja dostaje koordynaty wczytaj gry i na ich podstawie wykonuje ruch a jeœli trzeba, bicie.
	 *@param fileFrom numer kolumny z której wychodzi bierka
	 *@param rankFrom numer wiersza z którego wychodzi bierka
	 *@param fileTo numer kolumny na któr¹ wchodzi bierka
	 *@param rankTo  nr wierza na który wchodzi bierka
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
	 *	Funkcja sprawdza czy ostatnio wykonany ruch na szachownicy nie sprawia, ¿e przeciwny Król jest zaszachowany.
	 *@param piece bierka która mo¿e szachowaæ Króla
	 *@return zwraca prawdê jeœli Król w szachu.
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
	 *	Funkcja zmienia stronê wykonuj¹c¹ ruch.
	 */
	private void changeSide()
	{
		currentPlayer=!currentPlayer;
	}
	

}
