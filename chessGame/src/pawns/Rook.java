package pawns;

import java.util.ArrayList;

import pawns.GameRules.ToHighlight;

/**
 * Klasa {@link Rook} dziedzicz¹ca po Piece odpowiada za wie¿e w szachach.
 *	@see Piece
 */ 
public class Rook extends Piece{
	static int count=0;
	
	/**
	 *	Konstruktor klasy Rook który ustawia miejsce startowe wie¿y na szachownicy.
	 *
	 * @param  color kolor wie¿y wyra¿ony typem boolean
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
	 *	Funkcja która odpowiada za ruch wie¿¹. Przyjmnuje obiekt wie¿y i stan szachownicy. <br>
	 *	Przy pomocy dodatkowych funkcji okreœla miejsca na które mo¿e udaæ siê wie¿a.
	 *
	 * @param  rook jest to wie¿a której mo¿liwe ruchy zostan¹ znalezione
	 * @param  pieces wyra¿a aktualny stan szachownicy
	 * @return zwraca listê mo¿liwych ruchów
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
	 *	Funkcja sprawdza jak daleko wie¿a mo¿e iœæ w górê.
	 *
	 * @param  rank jest to numer wiersza wie¿y
	 * @param  file jest to numer kolumny wie¿y
	 * @param  pieces wyra¿a aktualny stan szachownicy
	 * @return liczbê mo¿liwych pól do przejœcia
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
	 *	Funkcja sprawdza jak daleko wie¿a mo¿e iœæ w dó³.
	 *
	 * @param  rank jest to numer wiersza wie¿y
	 * @param  file jest to numer kolumny wie¿y
	 * @param  pieces wyra¿a aktualny stan szachownicy
	 * @return liczbê mo¿liwych pól do przejœcia
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
	 *	Funkcja sprawdza jak daleko wie¿a mo¿e iœæ w prawo.
	 *
	 * @param  rank jest to numer wiersza wie¿y
	 * @param  file jest to numer kolumny wie¿y
	 * @param  pieces wyra¿a aktualny stan szachownicy
	 * @return liczbê mo¿liwych pól do przejœcia
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
	 *	Funkcja sprawdza jak daleko wie¿a mo¿e iœæ w lewo.
	 *
	 * @param  rank jest to numer wiersza wie¿y
	 * @param  file jest to numer kolumny wie¿y
	 * @param  pieces wyra¿a aktualny stan szachownicy
	 * @return liczbê mo¿liwych pól do przejœcia
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