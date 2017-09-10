package pawns;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import gui.Square;

/**
 * Klasa {@link GameRules} odpowiada po czêœci za przebieg gry. W niej zawarte s¹ zasady na jakich piony mog¹ poruszaæ siê po szachownicy.<br>
 * Zawiera pole które przechowuje "wirtualny" stan przebiegu gry.
 */ 

public class GameRules {
	
	private Piece [][]Pieces;	
	
	/**
	 *	Konstruktor klasy GameRules, ustawia na wszystkich polach wartoœæ null aby pózniej odró¿niæ gdzie stoi a gdzie nie dana bierka.
	 *
	 */
	public GameRules()
	{
		Pieces=new Piece[9][9];
		for(int i=0;i<9;++i)
			for(int j=0;j<9;++j)
				Pieces[i][j]=null;	
		initialize();
	}
	
	
	/**
	 *	Inicjalizuje tablicê bierek za pomoc¹ startowego ustawienia szachownicy.
	 *
	 */
	public void initialize()
	{
		int count=0;
		for(int i=1;i<9;++i)
		{
			Pawn pawn=new Pawn(true);
			pawn.rank=2;
			pawn.file=(char)('a'+count++);
			Pieces[2][i]=pawn;
			Pawn pawn2=new Pawn(false);
			pawn2.rank=7;
			pawn2.file=(char)('a'+count++);
			Pieces[7][i]=pawn2;
		}
		Rook rook=new Rook(true);
		rook.rank=1;
		rook.file='a';
		Pieces[1][1]=rook;
		rook.file='h';
		Pieces[1][8]=rook;
		
		Rook rook2=new Rook(false);
		rook2.rank=8;
		rook2.file='a';
		Pieces[8][1]=rook2;
		rook2.file='h';
		Pieces[8][8]=rook2;
		
		Knight knight=new Knight(true);
		knight.rank=1;
		knight.file='b';
		Pieces[1][2]=knight;
		knight.file='g';
		Pieces[1][7]=knight;
		
		Knight knight2=new Knight(false);
		knight2.rank=8;
		knight2.file='b';
		Pieces[8][2]=knight2;
		knight2.file='g';
		Pieces[8][7]=knight2;
		
		Bishop bishop=new Bishop(true);
		bishop.rank=1;
		bishop.file='c';
		Pieces[1][3]=bishop;
		bishop.file='f';
		Pieces[1][6]=bishop;
		
		Bishop bishop2=new Bishop(false);
		bishop2.rank=8;
		bishop2.file='c';
		Pieces[8][3]=bishop2;
		bishop2.file='f';
		Pieces[8][6]=bishop2;
		
		King king=new King(true);
		king.rank=1;
		king.file='e';
		Pieces[1][5]=king;
		
		King king2=new King(false);
		king2.rank=8;
		king2.file='e';
		Pieces[8][5]=king2;
		
		Queen queen=new Queen(true);
		queen.rank=1;
		queen.file='d';
		Pieces[1][4]=queen;
		
		Queen queen2=new Queen(false);
		queen2.rank=8;
		queen2.file='d';
		Pieces[8][4]=queen2;
	
	}
	/**
	 *	Ustawia ponownie wszystkie elementy tablicy na null.
	 *
	 */
	public void clearGameRules()
	{
		for(int i=0;i<9;++i)
			for(int j=0;j<9;++j)
				Pieces[i][j]=null;	
	}
	
	/**
	 *	Klasa ToHighlight stanowi de facto kontener zawieraj¹cy informacje o polu które nale¿y podœwietliæ. 
	 * Sk³ada siê ze jednej zmiennej int i jednej zmiennej char.
	 *
	 */
	
	public static class ToHighlight
	{
		public int rank=0;
		public char file=0;

	}
	
	/**
	 *	Funkcja sprawdza instancj¹ jakiej bierki jest przekazany do funkcji obiekt a nastêpnie przy pomocy klas danych bierek okreœla listê mo¿liwych ruchów.
	 * 
	 *@param piece bierka której mo¿liwe ruchy funkcja bêdzie zwracaæ
	 *
	 *@return Zwraca listê mo¿liwych ruchów do wykonania.
	 */
	
	public ArrayList<ToHighlight> returnLegalMoves(Piece piece)
	{	
		ArrayList<ToHighlight> moves = new ArrayList<ToHighlight>();
		if(piece instanceof Pawn)
		{
			piece=(Pawn)piece;
			moves=((Pawn) piece).movePawn((Pawn)piece,Pieces); //ruchy ktore moze zrobic pion
		}
		if(piece instanceof Knight)
		{
			piece=(Knight)piece;
			moves=((Knight) piece).moveKnight((Knight)piece,Pieces);
		}
		if(piece instanceof Rook)
		{
			piece=(Rook)piece;
			moves=((Rook) piece).moveRook((Rook)piece,Pieces);
		}
		if(piece instanceof Bishop)
		{
			piece=(Bishop)piece;
			moves=((Bishop) piece).moveBishop((Bishop)piece,Pieces);
		}
		if(piece instanceof Queen)
		{
			piece=(Queen)piece;
			moves=((Queen) piece).moveQueen((Queen)piece,Pieces);
		}
		if(piece instanceof King)
		{
			piece=(King)piece;
			moves=((King) piece).moveKing((King)piece,Pieces);
		}
		System.out.println(moves.size());
		for(int i=0;i<moves.size();++i)
		{
			King king=returnKing(piece.color);
			ToHighlight move=new ToHighlight();

			move=moves.get(i);
			/*if(king.checkUpRight(piece, move.rank, Piece.fileNumber(move.file), Pieces))
				moves.remove(i);*/
			/*else if(king.checkUpLeft(piece, move.rank, Piece.fileNumber(move.file), Pieces))
				moves.remove(i);
			else if(king.checkDownRight(piece, move.rank, Piece.fileNumber(move.file), Pieces))
				moves.remove(i);
			else if(king.checkDownLeft(piece, move.rank, Piece.fileNumber(move.file), Pieces))
				moves.remove(i);
			else if(king.checkLeft(piece, move.rank, Piece.fileNumber(move.file), Pieces))
				moves.remove(i);*/
		}
		System.out.println(moves.size());
		
		
		return moves;
	}
	/**
	 *	Funkcja znajduje i zwraca Króla o wybranym kolorze.
	 * 
	 *@param color kolor szukanego Króla
	 *
	 *@return zwraca wybranego Króla
	 */
	public King returnKing(boolean color)
	{
		for(int i=1;i<9;++i)
			for(int j=1;j<9;++j)
			{
				if(Pieces[i][j]!=null)
				{
					if(Pieces[i][j] instanceof King && Pieces[i][j].color==color)
						return (King)Pieces[i][j];
				}
			}
		return null;
	}
	
	/**
	 *	Funkcja znajduje i zwraca bierkê z danego miejsca.
	 * 
	 *@param rank numer wiersza szachownicy
	 *@param file numer kolumny szachowniy
	 *@return zwraca wybran¹ bierkê
	 */
	public Piece getPiece(int rank,int file)
	{
		return this.Pieces[rank][file];
	}
	/**
	 *	Funkcja ustawia bierkê na danym polu.
	 * 
	 *@param rank numer wiersza szachownicy
	 *@param file numer kolumny szachowniy
	 *@param piece bierka do ustawienia
	 */
	public void setPiece(int rank,int file,Piece piece)
	{
		this.Pieces[rank][file]=piece;
	}
	/**
	 *	Funkcja zwraca tablicê z ustawionymi bierkami.
	 *@return tablica {@link Piece}
	 */	
	public Piece[][] returnPieces()
	{
		return this.Pieces;
	}
}

