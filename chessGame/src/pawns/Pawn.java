package pawns;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import pawns.GameRules.ToHighlight;


/**
 * Klasa {@link Pawn} dziedzicz¹ca po Piece odpowiada za pionki w szachach.
 *	@see Piece
 */ 

final public class Pawn extends Piece{
	static int count=0;
	
	
	/**
	 *	Konstruktor klasy Pawn który ustawia miejsce startowe piona na szachownicy.
	 *
	 * @param  color kolor piona wyra¿ony typem boolean
	 */
	public Pawn(boolean color){
		
		super("pawn",color);
		if(count==8)
			count=0;
		this.file=(char)('a'+count++);
		if(color==true)
			this.rank=2;
		else 
			this.rank=7;
	
	}
	
	/**
	 *	Funkcja która odpowiada za ruch pionem. Przyjmnuje obiekt piona i stan szachownicy. <br>
	 *	Przy pomocy dodatkowych funkcji okreœla miejsca na które mo¿e udaæ siê pion.
	 *
	 * @param  pawn jest to pion którego mo¿liwe ruchy zostan¹ znalezione
	 * @param  pieces wyra¿a aktualny stan szachownicy
	 * @return zwraca listê mo¿liwych ruchów
	 */
	
	public ArrayList<GameRules.ToHighlight> movePawn(Pawn pawn,Piece[][] pieces)
	{
		ArrayList<ToHighlight> moves = new ArrayList<ToHighlight>();
		int rank=pawn.rank;
		int file=Piece.fileNumber(pawn.file);

		if(pawn.color)
		{
			if(moveForwardWhite(rank, file, pieces))
			{
				GameRules.ToHighlight toHighlight =new GameRules.ToHighlight();
				toHighlight.file=Piece.numberFile(file);
				toHighlight.rank=rank+1;
				moves.add(toHighlight);
			}
			if(beatRightWhite(rank, file, pieces))
			{
				GameRules.ToHighlight toHighlight =new GameRules.ToHighlight();
				toHighlight.file=Piece.numberFile(file+1);
				toHighlight.rank=rank+1;
				moves.add(toHighlight);
				toHighlight=null;
			}
			if(beatLeftWhite(rank, file, pieces))
			{
				GameRules.ToHighlight toHighlight =new GameRules.ToHighlight();
				toHighlight.file=Piece.numberFile(file-1);
				toHighlight.rank=rank+1;
				moves.add(toHighlight);
				toHighlight=null;
			}	
			
		}
		else
		{
			if(moveForwardBlack(rank, file, pieces))
			{
				GameRules.ToHighlight toHighlight =new GameRules.ToHighlight();
				toHighlight.file=Piece.numberFile(file);
				toHighlight.rank=rank-1;
				moves.add(toHighlight);
			}
			if(beatRightBlack(rank, file, pieces))
			{
				GameRules.ToHighlight toHighlight =new GameRules.ToHighlight();
				toHighlight.file=Piece.numberFile(file-1);
				toHighlight.rank=rank-1;
				moves.add(toHighlight);
			}
			if(beatLeftBlack(rank, file, pieces))
			{
				GameRules.ToHighlight toHighlight =new GameRules.ToHighlight();
				toHighlight.file=Piece.numberFile(file+1);
				toHighlight.rank=rank-1;
				moves.add(toHighlight);
			}	
			
		}	
		return moves;
	}
	
	/**
	 *	Funkcja sprawdza czy bia³y pion mo¿e iœæ do przodu.
	 *
	 * @param  rank jest to numer wiersza piona 
	 * @param  file jest to numer kolumny piona 
	 * @param  pieces wyra¿a aktualny stan szachownicy
	 * @return prawdê jeœli ruch wykonalny
	 */
	
	public boolean moveForwardWhite(int rank,int file,Piece[][] pieces)
	{
		if(rank+1>8)
			return false;
		else if(pieces[rank+1][file]==null)
			return true;
		else
			return false;
	}
	
	/**
	 *	Funkcja sprawdza czy bia³y pion mo¿e iœæ do przodu.
	 *
	 * @param  rank jest to numer wiersza piona 
	 * @param  file jest to numer kolumny piona 
	 * @param  pieces wyra¿a aktualny stan szachownicy
	 * @return prawdê jeœli ruch wykonalny
	 */
	public boolean moveForwardBlack(int rank,int file,Piece[][] pieces)
	{
		if(rank-1<1)
			return false;
		else if(pieces[rank-1][file]==null)
			return true;
		else
			return false;
	}
	
	/**
	 *	Funkcja sprawdza czy bia³y pion mo¿e biæ w prawo.
	 *
	 * @param  rank jest to numer wiersza piona 
	 * @param  file jest to numer kolumny piona 
	 * @param  pieces wyra¿a aktualny stan szachownicy
	 * @return prawdê jeœli ruch wykonalny
	 */
	public boolean beatRightWhite(int rank,int file,Piece[][] pieces)
	{
		if(rank+1>8 || file+1>8)
			return false;
		else if(pieces[rank+1][file+1]!=null && !pieces[rank+1][file+1].color)
			return true;
		else
			return false;
	}
	
	/**
	 *	Funkcja sprawdza czy czarny pion mo¿e biæ w prawo.
	 *
	 * @param  rank jest to numer wiersza piona 
	 * @param  file jest to numer kolumny piona 
	 * @param  pieces wyra¿a aktualny stan szachownicy
	 * @return prawdê jeœli ruch wykonalny
	 */
	public boolean beatRightBlack(int rank,int file,Piece[][] pieces)
	{
		if(rank-1<1 || file-1<1)
			return false;
		else if(pieces[rank-1][file-1]!=null && pieces[rank-1][file-1].color)
			return true;
		else
			return false;
	}
	
	/**
	 *	Funkcja sprawdza czy bia³y pion mo¿e biæ lewo.
	 *
	 * @param  rank jest to numer wiersza piona 
	 * @param  file jest to numer kolumny piona 
	 * @param  pieces wyra¿a aktualny stan szachownicy
	 * @return prawdê jeœli ruch wykonalny
	 */

	public boolean beatLeftWhite(int rank,int file,Piece[][] pieces)
	{
		if(rank+1>8 || file-1<1)
			return false;
		else if(pieces[rank+1][file-1]!=null && !pieces[rank+1][file-1].color)
			return true;
		else
			return false;
	}
	
	/**
	 *	Funkcja sprawdza czy czarny pion mo¿e biæ lewo.
	 *
	 * @param  rank jest to numer wiersza piona 
	 * @param  file jest to numer kolumny piona 
	 * @param  pieces wyra¿a aktualny stan szachownicy
	 * @return prawdê jeœli ruch wykonalny
	 */
	
	public boolean beatLeftBlack(int rank,int file,Piece[][] pieces)
	{
		if(rank-1<1 || file+1>8)
			return false;
		else if(pieces[rank-1][file+1]!=null && pieces[rank-1][file+1].color)
			return true;
		else
			return false;
	}
	
	
}



