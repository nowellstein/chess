package pawns;

import java.util.ArrayList;

import pawns.GameRules.ToHighlight;

/**
 * Klasa {@link Knight} dziedzicz�ca po Piece odpowiada za skoczka w szachach.
 *	@see Piece
 */ 
public class Knight extends Piece{
	static int count=0;
	
	/**
	 *	Konstruktor klasy Knight kt�ry ustawia miejsce startowe szkoczka na szachownicy.
	 *
	 * @param  color kolor skoczka wyra�ony typem boolean
	 */
	public Knight(boolean color){
		super("knight",color);
		if(count==2)
			count=0;
		this.file=(char)('b'+(count++)*5);
		if(color==true)
			this.rank=1;
		else if(color==false)
			this.rank=8;
		
	}
	/**
	 *	Funkcja kt�ra odpowiada za ruch skoczkiem. Przyjmnuje obiekt skoczka i stan szachownicy. <br>
	 *	Przy pomocy dodatkowych funkcji okre�la miejsca na kt�re mo�e uda� si� kr�lowa.
	 *
	 * @param  knight jest to obiekt skoczka kt�rego mo�liwe ruchy zostan� znalezione
	 * @param  pieces wyra�a aktualny stan szachownicy
	 * @return zwraca list� mo�liwych ruch�w
	 */
	
	public ArrayList<GameRules.ToHighlight> moveKnight(Knight knight,Piece[][] pieces)
	{
		ArrayList<ToHighlight> moves = new ArrayList<ToHighlight>();
		int rank=knight.rank;
		int file=Piece.fileNumber(knight.file);
		
		if(moveUpRight(rank, file, pieces))
		{
			GameRules.ToHighlight toHighlight =new GameRules.ToHighlight();
			toHighlight.file=Piece.numberFile(file+1);
			toHighlight.rank=rank+2;
			moves.add(toHighlight);
		}
		if(moveUpLeft(rank, file, pieces))
		{
			GameRules.ToHighlight toHighlight =new GameRules.ToHighlight();
			toHighlight.file=Piece.numberFile(file-1);
			toHighlight.rank=rank+2;
			moves.add(toHighlight);
		}
		
		if(moveDownRight(rank, file, pieces))
		{
			GameRules.ToHighlight toHighlight =new GameRules.ToHighlight();
			toHighlight.file=Piece.numberFile(file+1);
			toHighlight.rank=rank-2;
			moves.add(toHighlight);
		}
		if(moveDownLeft(rank, file, pieces))
		{
			GameRules.ToHighlight toHighlight =new GameRules.ToHighlight();
			toHighlight.file=Piece.numberFile(file-1);
			toHighlight.rank=rank-2;
			moves.add(toHighlight);
		}
		
		if(moveRightUp(rank, file, pieces))
		{
			GameRules.ToHighlight toHighlight =new GameRules.ToHighlight();
			toHighlight.file=Piece.numberFile(file+2);
			toHighlight.rank=rank+1;
			moves.add(toHighlight);
		}
		if(moveRightDown(rank, file, pieces))
		{
			GameRules.ToHighlight toHighlight =new GameRules.ToHighlight();
			toHighlight.file=Piece.numberFile(file+2);
			toHighlight.rank=rank-1;
			moves.add(toHighlight);
		}
		
		if(moveLeftDown(rank, file, pieces))
		{
			GameRules.ToHighlight toHighlight =new GameRules.ToHighlight();
			toHighlight.file=Piece.numberFile(file-2);
			toHighlight.rank=rank-1;
			moves.add(toHighlight);
		}
		if(moveLeftUp(rank, file, pieces))
		{
			GameRules.ToHighlight toHighlight =new GameRules.ToHighlight();
			toHighlight.file=Piece.numberFile(file-2);
			toHighlight.rank=rank+1;
			moves.add(toHighlight);
		}
		return moves;
	}
	/**
	 *	Funkcja sprawdza czy skoczek mo�e poruszy� si� o dwa pola do g�ry i jedno w prawo.
	 *
	 * @param  rank jest to numer wiersza skoczka
	 * @param  file jest to numer kolumny skoczka
	 * @param  pieces wyra�a aktualny stan szachownicy
	 * @return prawd� je�li ruch wykonalny
	 */
	
	public boolean moveUpRight(int rank,int file,Piece[][] pieces)
	{
		if(rank+2>8 || file+1>8)
			return false;
		else if(pieces[rank+2][file+1]!=null && pieces[rank+2][file+1].color==this.color)
			return false;
		else
			return true;
			
	}
	/**
	 *	Funkcja sprawdza czy skoczek mo�e poruszy� si� o dwa pola do g�ry i jedno w lewo.
	 *
	 * @param  rank jest to numer wiersza skoczka
	 * @param  file jest to numer kolumny skoczka
	 * @param  pieces wyra�a aktualny stan szachownicy
	 * @return prawd� je�li ruch wykonalny
	 */
	
	public boolean moveUpLeft(int rank,int file,Piece[][] pieces)
	{
		if(rank+2>8 || file-1<1)
			return false;
		else if(pieces[rank+2][file-1]!=null && pieces[rank+2][file-1].color==this.color)
			return false;
		else
			return true;
			
	}
	
	/**
	 *	Funkcja sprawdza czy skoczek mo�e poruszy� si� o dwa pola w d�l i jedno w prawo.
	 *
	 * @param  rank jest to numer wiersza skoczka
	 * @param  file jest to numer kolumny skoczka
	 * @param  pieces wyra�a aktualny stan szachownicy
	 * @return prawd� je�li ruch wykonalny
	 */

	public boolean moveDownRight(int rank,int file,Piece[][] pieces)
	{
		if(rank-2<1 || file+1>8)
			return false;
		else if(pieces[rank-2][file+1]!=null && pieces[rank-2][file+1].color==this.color)
			return false;
		else
			return true;
			
	}
	
	/**
	 *	Funkcja sprawdza czy skoczek mo�e poruszy� si� o dwa pola w d�l i jedno w lewo.
	 *
	 * @param  rank jest to numer wiersza skoczka
	 * @param  file jest to numer kolumny skoczka
	 * @param  pieces wyra�a aktualny stan szachownicy
	 * @return prawd� je�li ruch wykonalny
	 */
	
	public boolean moveDownLeft(int rank,int file,Piece[][] pieces)
	{
		if(rank-2<1 || file-1<1)
			return false;
		else if(pieces[rank-2][file-1]!=null && pieces[rank-2][file-1].color==this.color)
			return false;
		else
			return true;
			
	}
	
	/**
	 *	Funkcja sprawdza czy skoczek mo�e poruszy� si� o dwa pola w prawo i jedno w d�.
	 *
	 * @param  rank jest to numer wiersza skoczka
	 * @param  file jest to numer kolumny skoczka
	 * @param  pieces wyra�a aktualny stan szachownicy
	 * @return prawd� je�li ruch wykonalny
	 */
	
	public boolean moveRightDown(int rank,int file,Piece[][] pieces)
	{
		if(rank-1<1 || file+2>8)
			return false;
		else if(pieces[rank-1][file+2]!=null && pieces[rank-1][file+2].color==this.color)
			return false;
		else
			return true;
	}
	
	/**
	 *	Funkcja sprawdza czy skoczek mo�e poruszy� si� o dwa pola w prawo i jedno w g�r�.
	 *
	 * @param  rank jest to numer wiersza skoczka
	 * @param  file jest to numer kolumny skoczka
	 * @param  pieces wyra�a aktualny stan szachownicy
	 * @return prawd� je�li ruch wykonalny
	 */
	
	
	public boolean moveRightUp(int rank,int file,Piece[][] pieces)
	{
		if(rank+1>8 || file+2>8)
			return false;
		else if(pieces[rank+1][file+2]!=null && pieces[rank+1][file+2].color==this.color)
			return false;
		else
			return true;
	}
	
	/**
	 *	Funkcja sprawdza czy skoczek mo�e poruszy� si� o dwa pola w lewo i jedno w g�r�.
	 *
	 * @param  rank jest to numer wiersza skoczka
	 * @param  file jest to numer kolumny skoczka
	 * @param  pieces wyra�a aktualny stan szachownicy
	 * @return prawd� je�li ruch wykonalny
	 */
	
	public boolean moveLeftUp(int rank,int file,Piece[][] pieces)
	{
		if(rank+1>8 || file-2<1)
			return false;
		else if(pieces[rank+1][file-2]!=null && pieces[rank+1][file-2].color==this.color)
			return false;
		else
			return true;
	}
	
	/**
	 *	Funkcja sprawdza czy skoczek mo�e poruszy� si� o dwa pola w lewo i jedno w d�.
	 *
	 * @param  rank jest to numer wiersza skoczka
	 * @param  file jest to numer kolumny skoczka
	 * @param  pieces wyra�a aktualny stan szachownicy
	 * @return prawd� je�li ruch wykonalny
	 */
	
	public boolean moveLeftDown(int rank,int file,Piece[][] pieces)
	{
		if(rank-1<1 || file-2<1)
			return false;
		else if(pieces[rank-1][file-2]!=null && pieces[rank-1][file-2].color==this.color)
			return false;
		else
			return true;
	}

}
