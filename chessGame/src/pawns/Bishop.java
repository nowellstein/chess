package pawns;

import java.util.ArrayList;

import pawns.GameRules.ToHighlight;

/**
 * Klasa {@link Bishop} dziedzicz�ca po Piece odpowiada za go�ca w szachach.
 *	@see Piece
 */ 


public class Bishop extends Piece{
	static int count=0;
	
	/**
	 *	Konstruktor klasy {@link Bishop} kt�ry ustawia miejsce startowe go�ca na szachownicy.
	 *
	 * @param  color kolor go�ca wyra�ony typem boolean
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
	 *	Funkcja kt�ra odpowiada za ruch go�cem. Przyjmnuje obiekt go�ca i stan szachownicy. <br>
	 *	Przy pomocy dodatkowych funkcji okre�la miejsca na kt�re mo�e uda� si� goniec.
	 *
	 * @param  bishop jest to goniec kt�rego mo�liwe ruchy zostan� znalezione
	 * @param  pieces wyra�a aktualny stan szachownicy
	 * @return zwraca list� mo�liwych ruch�w
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
	 *	Funkcja sprawdza jak daleko kr�lowa mo�e i�� na ukos w g�r� w prawo.
	 *
	 * @param  rank jest to numer wiersza go�ca 
	 * @param  file jest to numer kolumny go�ca
	 * @param  pieces wyra�a aktualny stan szachownicy
	 * @return liczb� mo�liwych p�l do przej�cia
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
	 *	Funkcja sprawdza jak daleko kr�lowa mo�e i�� na ukos w g�r� w lewo.
	 *
	 * @param  rank jest to numer wiersza go�ca 
	 * @param  file jest to numer kolumny go�ca
	 * @param  pieces wyra�a aktualny stan szachownicy
	 * @return liczb� mo�liwych p�l do przej�cia
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
	 *	Funkcja sprawdza jak daleko kr�lowa mo�e i�� na ukos w d� w prawo.
	 *
	 * @param  rank jest to numer wiersza go�ca 
	 * @param  file jest to numer kolumny go�ca
	 * @param  pieces wyra�a aktualny stan szachownicy
	 * @return liczb� mo�liwych p�l do przej�cia
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
	 *	Funkcja sprawdza jak daleko kr�lowa mo�e i�� na ukos w d� w lewo.
	 *
	 * @param  rank jest to numer wiersza go�ca 
	 * @param  file jest to numer kolumny go�ca
	 * @param  pieces wyra�a aktualny stan szachownicy
	 * @return liczb� mo�liwych p�l do przej�cia
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
