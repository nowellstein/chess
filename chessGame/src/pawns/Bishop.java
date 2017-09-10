package pawns;

import java.util.ArrayList;

import pawns.GameRules.ToHighlight;

/**
 * Klasa {@link Bishop} dziedzicz¹ca po Piece odpowiada za goñca w szachach.
 *	@see Piece
 */ 


public class Bishop extends Piece{
	static int count=0;
	
	/**
	 *	Konstruktor klasy {@link Bishop} który ustawia miejsce startowe goñca na szachownicy.
	 *
	 * @param  color kolor goñca wyra¿ony typem boolean
	 */
	public Bishop(boolean color){
		super("bishop",color);
		if(count==2)
			count=0;
		this.file=(char)('c'+(count++)*3);
		if(color==true)
			this.rank=1;
		else if(color==false)
			this.rank=8;
	}
	/**
	 *	Funkcja która odpowiada za ruch goñcem. Przyjmnuje obiekt goñca i stan szachownicy. <br>
	 *	Przy pomocy dodatkowych funkcji okreœla miejsca na które mo¿e udaæ siê goniec.
	 *
	 * @param  bishop jest to goniec którego mo¿liwe ruchy zostan¹ znalezione
	 * @param  pieces wyra¿a aktualny stan szachownicy
	 * @return zwraca listê mo¿liwych ruchów
	 */
	
	public ArrayList<GameRules.ToHighlight> moveBishop(Bishop bishop,Piece[][] pieces)
	{
		ArrayList<ToHighlight> moves = new ArrayList<ToHighlight>();
		int rank=bishop.rank;
		int file=Piece.fileNumber(bishop.file);
		int countMoves=0;
		
		countMoves=moveUpRight(rank, file, pieces);
		for(int i=1;i<countMoves+1;++i)
		{
			GameRules.ToHighlight toHighlight =new GameRules.ToHighlight();
			toHighlight.file=Piece.numberFile(file+i);
			toHighlight.rank=rank+i;
			moves.add(toHighlight);
		}
		
		countMoves=moveUpLeft(rank, file, pieces);
		for(int i=1;i<countMoves+1;++i)
		{
			GameRules.ToHighlight toHighlight =new GameRules.ToHighlight();
			toHighlight.file=Piece.numberFile(file-i);
			toHighlight.rank=rank+i;
			moves.add(toHighlight);
		}
		
		countMoves=moveDownRight(rank, file, pieces);
		for(int i=1;i<countMoves+1;++i)
		{
			GameRules.ToHighlight toHighlight =new GameRules.ToHighlight();
			toHighlight.file=Piece.numberFile(file+i);
			toHighlight.rank=rank-i;
			moves.add(toHighlight);
		}
		
		countMoves=moveDownLeft(rank, file, pieces);
		for(int i=1;i<countMoves+1;++i)
		{
			GameRules.ToHighlight toHighlight =new GameRules.ToHighlight();
			toHighlight.file=Piece.numberFile(file-i);
			toHighlight.rank=rank-i;
			moves.add(toHighlight);
		}
		
		return moves;
	}
	
	/**
	 *	Funkcja sprawdza jak daleko królowa mo¿e iœæ na ukos w górê w prawo.
	 *
	 * @param  rank jest to numer wiersza goñca 
	 * @param  file jest to numer kolumny goñca
	 * @param  pieces wyra¿a aktualny stan szachownicy
	 * @return liczbê mo¿liwych pól do przejœcia
	 */
	
	int moveUpRight(int rank,int file,Piece[][] pieces)
	{
		int freeSquares=0;
		
		for(int i=1;i<8;++i)
		{
			if(rank+i>8 || file+i>8)
				break;
			else if(pieces[rank+i][file+i]==null)
				++freeSquares;
			else if(pieces[rank+i][file+i].color==this.color)
				break;
			else if(pieces[rank+i][file+i].color!=this.color)
			{
				++freeSquares;
				break;
			}
			
		}				
		return freeSquares;
	}
	
	/**
	 *	Funkcja sprawdza jak daleko królowa mo¿e iœæ na ukos w górê w lewo.
	 *
	 * @param  rank jest to numer wiersza goñca 
	 * @param  file jest to numer kolumny goñca
	 * @param  pieces wyra¿a aktualny stan szachownicy
	 * @return liczbê mo¿liwych pól do przejœcia
	 */
	int moveUpLeft(int rank,int file,Piece[][] pieces)
	{
		int freeSquares=0;
		
		for(int i=1;i<8;++i)
		{
			if(rank+i>8 || file-i<1)
				break;
			else if(pieces[rank+i][file-i]==null)
				++freeSquares;
			else if(pieces[rank+i][file-i].color==this.color)
				break;
			else if(pieces[rank+i][file-i].color!=this.color)
			{
				++freeSquares;
				break;
			}
			
		}				
		return freeSquares;
	}
	
	/**
	 *	Funkcja sprawdza jak daleko królowa mo¿e iœæ na ukos w dó³ w prawo.
	 *
	 * @param  rank jest to numer wiersza goñca 
	 * @param  file jest to numer kolumny goñca
	 * @param  pieces wyra¿a aktualny stan szachownicy
	 * @return liczbê mo¿liwych pól do przejœcia
	 */
	int moveDownRight(int rank,int file,Piece[][] pieces)
	{
		int freeSquares=0;
		
		for(int i=1;i<8;++i)
		{
			if(rank-i<1 || file+i>8)
				break;
			else if(pieces[rank-i][file+i]==null)
				++freeSquares;
			else if(pieces[rank-i][file+i].color==this.color)
				break;
			else if(pieces[rank-i][file+i].color!=this.color)
			{
				++freeSquares;
				break;
			}
			
		}				
		return freeSquares;
	}
	
	/**
	 *	Funkcja sprawdza jak daleko królowa mo¿e iœæ na ukos w dó³ w lewo.
	 *
	 * @param  rank jest to numer wiersza goñca 
	 * @param  file jest to numer kolumny goñca
	 * @param  pieces wyra¿a aktualny stan szachownicy
	 * @return liczbê mo¿liwych pól do przejœcia
	 */
	
	int moveDownLeft(int rank,int file,Piece[][] pieces)
	{
		int freeSquares=0;
		
		for(int i=1;i<8;++i)
		{
			if(rank-i<1 || file-i<1)
				break;
			else if(pieces[rank-i][file-i]==null)
				++freeSquares;
			else if(pieces[rank-i][file-i].color==this.color)
				break;
			else if(pieces[rank-i][file-i].color!=this.color)
			{
				++freeSquares;
				break;
			}
			
		}				
		return freeSquares;
	}

}
