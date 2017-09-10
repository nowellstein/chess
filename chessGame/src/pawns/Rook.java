package pawns;

import java.util.ArrayList;

import pawns.GameRules.ToHighlight;

/**
 * Klasa {@link Rook} dziedzicz�ca po Piece odpowiada za wie�e w szachach.
 *	@see Piece
 */ 
public class Rook extends Piece{
	static int count=0;
	
	/**
	 *	Konstruktor klasy Rook kt�ry ustawia miejsce startowe wie�y na szachownicy.
	 *
	 * @param  color kolor wie�y wyra�ony typem boolean
	 */
	public Rook(boolean color){
		super("rook",color);
		if(count==2)
			count=0;
		this.file=(char)('a'+(count++)*7);
		if(color==true)
			this.rank=1;
		else if(color==false)
			this.rank=8;

	}
	/**
	 *	Funkcja kt�ra odpowiada za ruch wie��. Przyjmnuje obiekt wie�y i stan szachownicy. <br>
	 *	Przy pomocy dodatkowych funkcji okre�la miejsca na kt�re mo�e uda� si� wie�a.
	 *
	 * @param  rook jest to wie�a kt�rej mo�liwe ruchy zostan� znalezione
	 * @param  pieces wyra�a aktualny stan szachownicy
	 * @return zwraca list� mo�liwych ruch�w
	 */
	
	public ArrayList<GameRules.ToHighlight> moveRook(Rook rook,Piece[][] pieces)
	{
		ArrayList<ToHighlight> moves = new ArrayList<ToHighlight>();
		int rank=rook.rank;
		int file=Piece.fileNumber(rook.file);
		int countMoves=0;
		
		countMoves=moveUp(rank, file, pieces);
		for(int i=rank+1;i<rank+countMoves+1;++i)
		{
			GameRules.ToHighlight toHighlight =new GameRules.ToHighlight();
			toHighlight.file=Piece.numberFile(file);
			toHighlight.rank=i;
			moves.add(toHighlight);
		}
		countMoves=moveDown(rank, file, pieces);
		for(int i=rank-1;i>rank-countMoves-1;--i)
		{
			GameRules.ToHighlight toHighlight =new GameRules.ToHighlight();
			toHighlight.file=Piece.numberFile(file);
			toHighlight.rank=i;
			moves.add(toHighlight);
		}
		countMoves=moveRight(rank, file, pieces);
		for(int i=file+1;i<file+countMoves+1;++i)
		{
			GameRules.ToHighlight toHighlight =new GameRules.ToHighlight();
			toHighlight.file=Piece.numberFile(i);
			toHighlight.rank=rank;
			moves.add(toHighlight);
		}
		countMoves=moveLeft(rank, file, pieces);
		for(int i=file-1;i>file-countMoves-1;--i)
		{
			GameRules.ToHighlight toHighlight =new GameRules.ToHighlight();
			toHighlight.file=Piece.numberFile(i);
			toHighlight.rank=rank;
			moves.add(toHighlight);
		}

		return moves;
	}
	
	/**
	 *	Funkcja sprawdza jak daleko wie�a mo�e i�� w g�r�.
	 *
	 * @param  rank jest to numer wiersza wie�y
	 * @param  file jest to numer kolumny wie�y
	 * @param  pieces wyra�a aktualny stan szachownicy
	 * @return liczb� mo�liwych p�l do przej�cia
	 */
	int moveUp(int rank,int file,Piece[][] pieces)
	{
		int freeSquares=0;
		for(int i=rank+1;i<9;++i)
		{
			if(pieces[i][file]==null)
				++freeSquares;
			else if(pieces[i][file].color==this.color)
				break;
			else if(pieces[i][file].color!=this.color)
			{
				++freeSquares;
				break;
			}
		}
		return freeSquares;		
	}
	

	/**
	 *	Funkcja sprawdza jak daleko wie�a mo�e i�� w d�.
	 *
	 * @param  rank jest to numer wiersza wie�y
	 * @param  file jest to numer kolumny wie�y
	 * @param  pieces wyra�a aktualny stan szachownicy
	 * @return liczb� mo�liwych p�l do przej�cia
	 */
	int moveDown(int rank,int file,Piece[][] pieces)
	{
		int freeSquares=0;
		for(int i=rank-1;i>0;--i)
		{
			if(pieces[i][file]==null)
				++freeSquares;
			else if(pieces[i][file].color==this.color)
				break;
			else if(pieces[i][file].color!=this.color)
			{
				++freeSquares;
				break;
			}
		}
		return freeSquares;		
	}
	
	/**
	 *	Funkcja sprawdza jak daleko wie�a mo�e i�� w prawo.
	 *
	 * @param  rank jest to numer wiersza wie�y
	 * @param  file jest to numer kolumny wie�y
	 * @param  pieces wyra�a aktualny stan szachownicy
	 * @return liczb� mo�liwych p�l do przej�cia
	 */
	int moveRight(int rank,int file,Piece[][] pieces)
	{
		int freeSquares=0;
		for(int i=file+1;i<9;++i)
		{
			if(pieces[rank][i]==null)
				++freeSquares;
			else if(pieces[rank][i].color==this.color)
				break;
			else if(pieces[rank][i].color!=this.color)
			{
				++freeSquares;
				break;
			}
		}
		return freeSquares;		
	}
	
	/**
	 *	Funkcja sprawdza jak daleko wie�a mo�e i�� w lewo.
	 *
	 * @param  rank jest to numer wiersza wie�y
	 * @param  file jest to numer kolumny wie�y
	 * @param  pieces wyra�a aktualny stan szachownicy
	 * @return liczb� mo�liwych p�l do przej�cia
	 */
	
	int moveLeft(int rank,int file,Piece[][] pieces)
	{
		int freeSquares=0;
		for(int i=file-1;i>0;--i)
		{
			if(pieces[rank][i]==null)
				++freeSquares;
			else if(pieces[rank][i].color==this.color)
				break;
			else if(pieces[rank][i].color!=this.color)
			{
				++freeSquares;
				break;
			}
		}
		return freeSquares;		
	}

}