package gui;

import pawns.*;
import chessGame.FrameManager;
import chessGame.Player;

import java.awt.Graphics;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.Highlighter.Highlight;

import org.omg.CORBA.INITIALIZE;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import pawns.GameRules;
import pawns.GameRules.ToHighlight;
import pawns.Piece;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Klasa {@link ChessBoard} odpowiada za rysowanie i interakcje z polem szachownicy oraz jest pierwsz¹ funkcj¹ przetwarzaj¹c¹ ruchy bierk¹.<br>
 * Implementuje {@link MouseListener} <br>
 * Zawiera pola takie jak :<br>
 * {@link FrameManager} - kotroler aplikacji<br>
 * {@link GameRules} - kontroler zasad gry<br>
 * highlited  - informacja czy podœwietlone jest mozliwy ruch bierki<br>
 * movingPiece - aktualnie wybrana bierka<br>
 * options - mo¿liwe do wykonania ruchy<br>
 * Squares - tablica pól szachownicy<br>
 * light - ustawiony kolor jasnych pól <br>
 * dark - ustawiony kolor ciemnych pól <br>
  * @see JPanel
 */ 
public class ChessBoard extends JPanel implements MouseListener{
	
	private FrameManager frameManager;
	private GameRules gameRules;
	
	private boolean highlited=false;
	public Piece movingPiece=null;
	private ArrayList<ToHighlight> options;

	
	
	public Square[][] Squares;
	Color light=new Color(153,160,0,200);
	Color dark=new Color(204,255,204,100);
	/**
	 *	Konstruktor klasy {@link ChessboardSpot} ustawia layout szachownicy oraz j¹ inicjalizuje.
	*	@param frameManager kontroler aplikacji	
	 */
	
	public ChessBoard(FrameManager frameManager)
	{
		this.frameManager=frameManager;
		options= new ArrayList<GameRules.ToHighlight>();
		setLayout(new GridLayout(9,9,3,3));
		Squares=new Square[9][9];
		initialize();
		
	}
	/**
	 *	Funkcja initialize, dodaje {@link MouseListener} do poszczególnych pól szachownicy i inicjalizuje je, ustawia kolor i opis planszy.
	 */
	
	private void initialize()
	{
		for(int i=8;i>=0;--i)
		{
			for(int j=0;j<9;++j)// tworzenie paneli i ustawianie layoutow dla wszystkich pol
			{
				Squares[i][j]=new Square();
				add(Squares[i][j]);
			}
		}
		
		for(int i=1;i<9;++i)
		{
			for(int j=1;j<9;++j)
			{
				Squares[i][j].file=(char)('a'+(j-1));
				Squares[i][j].addMouseListener(this);
				Squares[j][i].rank=j;
			}
		}
		for(int i=8;i>0;--i)
		{
			for(int j=i%2==0?2:1;j<9;j=j+2) //ustawianie pol do grania 
			{
				Squares[i][j].setColor(light);
			}
			for(int k=i%2==0?1:2;k<9;k=k+2)
			{
				Squares[i][k].setColor(dark);
			}       
	    }
		for(int i=8;i>0;--i)
		{
			Squares[i][0].setLayout(new GridBagLayout());
			JLabel jLabel=new JLabel(Integer.toString(i));
			jLabel.setFont(new Font("Serif", Font.BOLD,32));
			Squares[i][0].add(jLabel);
			
			String file=Character.toString((char)('a'-1+i));
			JLabel jLab=new JLabel(file);
			Squares[0][i].setLayout(new GridBagLayout());
			jLab.setFont(new Font("Serif", Font.BOLD,32));
			Squares[0][i].add(jLab);
			
			Squares[i][0].higlitedSquare();
			Squares[0][i].higlitedSquare();
		}
	}
	
	/**
	 *	Funkcja addPawns dodaje bierki na planszê ustawiaj¹c je w domyœlnej konfiguracji.
	 *@param playingWhite graj¹cy bia³ymi
	 *@param playingBlack graj¹cy czarnymi
	 *@param gameRules zasady gry
	 */
	
	public void addPawns(Player playingWhite,Player playingBlack,GameRules gameRules)
	{
		this.gameRules=gameRules;
		for(int i=1;i<9;++i)
		{
			Squares[2][i].add(playingWhite.CurrentPiece("whitePawn"+i));
			Squares[7][i].add(playingBlack.CurrentPiece("blackPawn"+i));
		}
		
		Squares[1][1].add(playingWhite.CurrentPiece("whiteRook1"));
		Squares[1][2].add(playingWhite.CurrentPiece("whiteKnight1"));
		Squares[1][3].add(playingWhite.CurrentPiece("whiteBishop1"));
		Squares[1][4].add(playingWhite.CurrentPiece("whiteQueen"));
		Squares[1][5].add(playingWhite.CurrentPiece("whiteKing"));
		Squares[1][6].add(playingWhite.CurrentPiece("whiteBishop2"));
		Squares[1][7].add(playingWhite.CurrentPiece("whiteKnight2"));
		Squares[1][8].add(playingWhite.CurrentPiece("whiteRook2"));
		
		
		Squares[8][1].add(playingBlack.CurrentPiece("blackRook1"));
		Squares[8][2].add(playingBlack.CurrentPiece("blackKnight1"));
		Squares[8][3].add(playingBlack.CurrentPiece("blackBishop1"));
		Squares[8][4].add(playingBlack.CurrentPiece("blackQueen"));
		Squares[8][5].add(playingBlack.CurrentPiece("blackKing"));
		Squares[8][6].add(playingBlack.CurrentPiece("blackBishop2"));
		Squares[8][7].add(playingBlack.CurrentPiece("blackKnight2"));
		Squares[8][8].add(playingBlack.CurrentPiece("blackRook2"));	
		
	
			for(int j=1;j<9;++j)
			{
				Squares[1][j].setOccupied(true);
				Squares[2][j].setOccupied(true);
				Squares[7][j].setOccupied(true);
				Squares[8][j].setOccupied(true);
			}
	}
	
	/**
	 *	Funkcja czyœci szachownicê z bierek przed ponown¹ rozgrywk¹.
	 */
	
	public void clearChessboard()
	{
		for(int i=8;i>=1;--i)
		{
			for(int j=1;j<9;++j)
			{
				if(!checkIfEmpty(Square.numberFile(j), i))
					Squares[i][j].remove((Piece)Squares[i][j].getComponent(0));
				Squares[i][j].unhiglitedSquare();
				
			}
		}
		movingPiece=null;
	}
	/**
	 *	Funkcja przetwarza wszystkie klikniêcia na szachownicy. Jeœli wybrano bierkê aktualnie graj¹cego gracza to wyœwietla jej mo¿liwe ruchy. <br>
	 * Jeœli wtedy klikniemy na inn¹ bierk¹ aktualnie graj¹cego to pokazujemy jej mo¿liwe ruchy. Jeœli po wyœwietleniu ruchu klikniemy przeciwn¹ bierkê, <br>
	 * to albo wykonane zostanie bicie (jeœli mo¿liwe) albo nic siê nie dzieje.
	 *@param square kliknête pole 
	 */
	
	public void processClick(Square square)
	{
		
		
		if(checkIfEmpty(square.file, square.rank))
		{
			if(highlited && movingPiece!=null)
			{
				Squares[movingPiece.rank][Square.fileNumber(movingPiece.file)].unhiglitedSquare();
				frameManager.getMove(movingPiece,square.file,square.rank, options);
				highlited=false;
				unhighlightLegalMoves();
				movingPiece=null;
			}
			else 
				return;
		}
		else
		{
			Piece piece=(Piece)square.getComponent(0);
			if(movingPiece!=null) //bicie piona
			{
				if(frameManager.beatPiece(movingPiece,square.file,square.rank, options,piece))
				{
					Squares[movingPiece.rank][Square.fileNumber(movingPiece.file)].unhiglitedSquare();
					frameManager.getMove(movingPiece,square.file,square.rank, options);
					highlited=false;
					unhighlightLegalMoves();
					movingPiece=null;
					return;
				}
			}
			
			if(frameManager.currentPlayer && !piece.color) //gdy kliknac w pion przeciwny jesli nie mozna bic
				return;
			else if(!frameManager.currentPlayer && piece.color)
				return;
			
			
			if(movingPiece!=null && movingPiece.color==frameManager.currentPlayer) //gdy kliknac w inny pionek tego samego koloru
			{
				Squares[movingPiece.rank][Square.fileNumber(movingPiece.file)].unhiglitedSquare();
				unhighlightLegalMoves();
				revalidate();
				repaint();
			}		
			highlited=true; //podswietlenie mozliwosci
			Squares[square.rank][Square.fileNumber(square.file)].higlitedSquare();
			movingPiece=piece;
			highlightLegalMoves();
			revalidate();
			repaint();
		}
	}
	/**
	 *	Funkcja pdœwietla wszyskie mo¿liwe ruchy wybranej bierki.
	 */
	
	public void highlightLegalMoves()
	{
		options=gameRules.returnLegalMoves(movingPiece);
		GameRules.ToHighlight toHighlight=new GameRules.ToHighlight();
		int rank;
		char file;
		for(int i=0;i<options.size();++i)
		{
			toHighlight=options.get(i);
			rank=toHighlight.rank;
			file=toHighlight.file;		
			Squares[rank][Square.fileNumber(file)].higlitedSquare();
		}	
	}
	/**
	 *	Funkcja ustawia szach na wybranym Królu.
	 *@param color kolor Króla który zostaje zaszachowny.
	 */

	public void setKingCheck(boolean color)
	{
		for(int i=1;i<9;++i)
			for(int j=1;j<9;++j)
			{
				if(Squares[i][j].getOccupied())
				{
					Piece piece=(Piece)Squares[i][j].getComponent(0);
					if(piece instanceof King)
					{
						if(color==piece.color)
							continue;
						else
						{
							King king=(King)piece;
							king.check=true;
						}
					}
				}
			}
	}
	/**
	 *	Funkcja odznacza mo¿liwe ruchy bierki.
	 */
	
	
	public void unhighlightLegalMoves()
	{
		GameRules.ToHighlight toHighlight=new GameRules.ToHighlight();
		int rank;
		char file;
		for(int i=0;i<options.size();++i)
		{
			toHighlight=options.get(i);
			rank=toHighlight.rank;
			file=toHighlight.file;
			Squares[rank][Square.fileNumber(file)].unhiglitedSquare();
		}
	}
	
	/**
	 *	Funkcja sprawdza czy dane pole jest puste.
	 *@param file kolumna pola
	 *@param rank wiersz pola
	 *@return zwraca prawdê jeœli pole puste
	 */

	public boolean checkIfEmpty(char file,int rank)
	{
		
		if(Squares[rank][Square.fileNumber(file)].getComponentCount()==0)
			return true;
		else 
			return false;
	}
	/**
	 *	Nadpisana metoda pozwalaj¹ca na rysowanie pola
	 *
	 * @param g informacje potrzebne do rysowania
	 * @see         Graphics
	 */

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	public void mousePressed(MouseEvent e) {
	    }
	    public void mouseReleased(MouseEvent e) {
	    }

	    public void mouseEntered(MouseEvent e) {
	    }

	    public void mouseExited(MouseEvent e) {
	    }
		/**
		 *	Funkcja procesuj¹ca klikniêcie.
		 * Wywo³uje processClick który kontroluje ruch bierki na szachownicy.
		 */
	    
	    public void mouseClicked(MouseEvent e) {
	    	if(!frameManager.game.gameStart)
	    		return;
	    	Square square=(Square)e.getComponent();
	    	//System.out.println(square.file+" "+square.rank);
	    	processClick(square);
	    }
	
}
