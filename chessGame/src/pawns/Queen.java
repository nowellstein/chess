package pawns;

import java.util.ArrayList;

import pawns.GameRules.ToHighlight;


/**
 * Klasa {@link Queen} dziedzicz¹ca po Piece odpowiada za królowe w szachach.
 *	@see Piece
 */ 
public class Queen extends Piece{
	
	/**
	 *	Konstruktor klasy Queen który ustawia miejsce startowe królowej na szachownicy.
	 *
	 * @param  color kolor królowej wyra¿ony typem boolean
	 */
	public Queen(boolean color){
		super("queen",color);
		this.file='d';
		if(color==true)
			this.rank=1;
		else if(color==false)
			this.rank=8;
			
	}
	/**
	 *	Funkcja która odpowiada za ruch królow¹. Przyjmnuje obiekt królowej i stan szachownicy. <br>
	 *	Przy pomocy dodatkowych funkcji okreœla miejsca na które mo¿e udaæ siê królowa.
	 *
	 * @param  queen jest to królowa której mo¿liwe ruchy zostan¹ znalezione
	 * @param  pieces wyra¿a aktualny stan szachownicy
	 * @return zwraca listê mo¿liwych ruchów
	 */
	
	public ArrayList<GameRules.ToHighlight> moveQueen(Queen queen,Piece[][] pieces)
	{
		ArrayList<ToHighlight> moves = new ArrayList<ToHighlight>();
		int rank=queen.rank;
		int file=Piece.fileNumber(queen.file);
		int countMoves=0;
		
		System.out.println(queen.color);
		
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
	 *	Funkcja sprawdza jak daleko królowa mo¿e iœæ na ukos w górê w prawo.
	 *
	 * @param  rank jest to numer wiersza królowej 
	 * @param  file jest to numer kolumny królowej
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
	 * @param  rank jest to numer wiersza królowej 
	 * @param  file jest to numer kolumny królowej
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
	 * @param  rank jest to numer wiersza królowej 
	 * @param  file jest to numer kolumny królowej
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
	 * @param  rank jest to numer wiersza królowej 
	 * @param  file jest to numer kolumny królowej
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
	
	/**
	 *	Funkcja sprawdza jak daleko królowa mo¿e iœæ w górê.
	 *
	 * @param  rank jest to numer wiersza królowej 
	 * @param  file jest to numer kolumny królowej
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
	 *	Funkcja sprawdza jak daleko królowa mo¿e iœæ w dó³.
	 *
	 * @param  rank jest to numer wiersza królowej 
	 * @param  file jest to numer kolumny królowej
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
	 *	Funkcja sprawdza jak daleko królowa mo¿e iœæ w prawo.
	 *
	 * @param  rank jest to numer wiersza królowej 
	 * @param  file jest to numer kolumny królowej
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
	 *	Funkcja sprawdza jak daleko królowa mo¿e iœæ w lewo.
	 *
	 * @param  rank jest to numer wiersza królowej 
	 * @param  file jest to numer kolumny królowej
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
