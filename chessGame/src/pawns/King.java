package pawns;

import java.util.ArrayList;

import pawns.GameRules.ToHighlight;

/**
 * Klasa {@link King} dziedzicz¹ca po Piece odpowiada za króla w szachach.
 *	@see Piece
 */ 

final public class King extends Piece{
	public boolean check=false;
	/**
	 *	Konstruktor klasy king który ustawia miejsce startowe króla na szachownicy.
	 *
	 * @param  color kolor króla wyra¿ony typem boolean
	 */
	public King(boolean color){
		super("king",color);
		this.file='e';
		if(color==true)
			this.rank=1;
		else if(color==false)
			this.rank=8;
	}
	
	/**
	 *	Funkcja która odpowiada za ruch królem. Przyjmnuje obiekt króla i stan szachownicy. <br>
	 *	Przy pomocy dodatkowych funkcji okreœla miejsca na które mo¿e udaæ siê król\..
	 *
	 * @param  king jest to obiekt króla którego mo¿liwe ruchy zostan¹ znalezione
	 * @param  pieces wyra¿a aktualny stan szachownicy
	 * @return zwraca listê mo¿liwych ruchów
	 */

	public ArrayList<GameRules.ToHighlight> moveKing(King king,Piece[][] pieces)
	{
		ArrayList<ToHighlight> moves = new ArrayList<ToHighlight>();
		int rank=king.rank;
		int file=Piece.fileNumber(king.file);
		
		
		if(Up(rank, file, pieces))
		{
			GameRules.ToHighlight toHighlight =new GameRules.ToHighlight();
			toHighlight.file=Piece.numberFile(file);
			toHighlight.rank=rank+1;
			moves.add(toHighlight);
		}
		if(Down(rank, file, pieces))
		{
			GameRules.ToHighlight toHighlight =new GameRules.ToHighlight();
			toHighlight.file=Piece.numberFile(file);
			toHighlight.rank=rank-1;
			moves.add(toHighlight);
		}
		if(Left(rank, file, pieces))
		{
			GameRules.ToHighlight toHighlight =new GameRules.ToHighlight();
			toHighlight.file=Piece.numberFile(file-1);
			toHighlight.rank=rank;
			moves.add(toHighlight);
		}
		if(Right(rank, file, pieces))
		{
			GameRules.ToHighlight toHighlight =new GameRules.ToHighlight();
			toHighlight.file=Piece.numberFile(file+1);
			toHighlight.rank=rank;
			moves.add(toHighlight);
		}
		if(UpRight(rank, file, pieces))
		{
			GameRules.ToHighlight toHighlight =new GameRules.ToHighlight();
			toHighlight.file=Piece.numberFile(file+1);
			toHighlight.rank=rank+1;
			moves.add(toHighlight);
		}
		if(UpLeft(rank, file, pieces))
		{
			GameRules.ToHighlight toHighlight =new GameRules.ToHighlight();
			toHighlight.file=Piece.numberFile(file-1);
			toHighlight.rank=rank+1;
			moves.add(toHighlight);
		}
		if(DownLeft(rank, file, pieces))
		{
			GameRules.ToHighlight toHighlight =new GameRules.ToHighlight();
			toHighlight.file=Piece.numberFile(file-1);
			toHighlight.rank=rank-1;
			moves.add(toHighlight);
		}
		if(DownRight(rank, file, pieces))
		{
			GameRules.ToHighlight toHighlight =new GameRules.ToHighlight();
			toHighlight.file=Piece.numberFile(file+1);
			toHighlight.rank=rank-1;
			moves.add(toHighlight);
		}
		
		return moves;
	}
	
	
	/**
	 *	Funkcja sprawdza czy król mo¿e iœæ w górê.
	 *
	 * @param  rank jest to numer wiersza piona 
	 * @param  file jest to numer kolumny piona 
	 * @param  pieces wyra¿a aktualny stan szachownicy
	 * @return prawdê jeœli ruch wykonalny
	 */
	
		public boolean Up(int rank,int file,Piece[][] pieces)
		{
			if(rank+1>8)
				return false;
			else if(pieces[rank+1][file]==null || pieces[rank+1][file].color!=this.color)
				return true;
			else
				return false;
		}
		/**
		 *	Funkcja sprawdza czy król mo¿e iœæ w dó³.
		 *
		 * @param  rank jest to numer wiersza piona 
		 * @param  file jest to numer kolumny piona 
		 * @param  pieces wyra¿a aktualny stan szachownicy
		 * @return prawdê jeœli ruch wykonalny
		 */
		public boolean Down(int rank,int file,Piece[][] pieces)
		{
			if(rank-1<1)
				return false;
			else if(pieces[rank-1][file]==null || pieces[rank-1][file].color!=this.color)
				return true;
			else
				return false;
		}
		
		/**
		 *	Funkcja sprawdza czy król mo¿e iœæ w lewo.
		 *
		 * @param  rank jest to numer wiersza piona 
		 * @param  file jest to numer kolumny piona 
		 * @param  pieces wyra¿a aktualny stan szachownicy
		 * @return prawdê jeœli ruch wykonalny
		 */
		public boolean Left(int rank,int file,Piece[][] pieces)
		{
			if(file-1<1)
				return false;
			else if(pieces[rank][file-1]==null || pieces[rank][file-1].color!=this.color)
				return true;
			else
				return false;
		}
		/**
		 *	Funkcja sprawdza czy król mo¿e iœæ w prawo.
		 *
		 * @param  rank jest to numer wiersza piona 
		 * @param  file jest to numer kolumny piona 
		 * @param  pieces wyra¿a aktualny stan szachownicy
		 * @return prawdê jeœli ruch wykonalny
		 */
		public boolean Right(int rank,int file,Piece[][] pieces)
		{
			if(file+1>8)
				return false;
			else if(pieces[rank][file+1]==null || pieces[rank][file+1].color!=this.color)
				return true;
			else
				return false;
		}
		
		/**
		 *	Funkcja sprawdza czy król mo¿e iœæ w prawo i do góry.
		 *
		 * @param  rank jest to numer wiersza piona 
		 * @param  file jest to numer kolumny piona 
		 * @param  pieces wyra¿a aktualny stan szachownicy
		 * @return prawdê jeœli ruch wykonalny
		 */
		public boolean UpRight(int rank,int file,Piece[][] pieces)
		{
			if(rank+1>8 || file+1>8)
				return false;
			else if(pieces[rank+1][file+1]==null || pieces[rank+1][file+1].color!=this.color)
				return true;
			else
				return false;
		}
		/**
		 *	Funkcja sprawdza czy król mo¿e iœæ w prawo i w dó³.
		 *
		 * @param  rank jest to numer wiersza piona 
		 * @param  file jest to numer kolumny piona 
		 * @param  pieces wyra¿a aktualny stan szachownicy
		 * @return prawdê jeœli ruch wykonalny
		 */
		public boolean DownRight(int rank,int file,Piece[][] pieces)
		{
			if(rank-1<1 || file+1>8)
				return false;
			else if(pieces[rank-1][file+1]==null || pieces[rank-1][file+1].color!=this.color)
				return true;
			else
				return false;
		}
		
		/**
		 *	Funkcja sprawdza czy król mo¿e iœæ w lewo i w górê.
		 *
		 * @param  rank jest to numer wiersza piona 
		 * @param  file jest to numer kolumny piona 
		 * @param  pieces wyra¿a aktualny stan szachownicy
		 * @return prawdê jeœli ruch wykonalny
		 */
		public boolean UpLeft(int rank,int file,Piece[][] pieces)
		{
			if(rank+1>8 || file-1<1)
				return false;
			else if(pieces[rank+1][file-1]==null || pieces[rank+1][file-1].color!=this.color)
				return true;
			else
				return false;
		}
		
		/**
		 *	Funkcja sprawdza czy król mo¿e iœæ w lewo i w dó³.
		 *
		 * @param  rank jest to numer wiersza piona 
		 * @param  file jest to numer kolumny piona 
		 * @param  pieces wyra¿a aktualny stan szachownicy
		 * @return prawdê jeœli ruch wykonalny
		 */
		public boolean DownLeft(int rank,int file,Piece[][] pieces)
		{
			if(rank-1<1 || file-1<1)
				return false;
			else if(pieces[rank-1][file-1]==null || pieces[rank-1][file-1].color!=this.color)
				return true;
			else
				return false;
		}
		
		/*
		public void setBoard(Piece[][] board)
		{
			for(int i=1;i<9;++i)
				for(int j=1;j<9;++j)
				{
					pieces[i][j]=null;
					if(board[i][j]!=null)
					{
						if(board[i][j] instanceof Pawn)
						{
							pieces[i][j]=new Pawn(board[i][j].color);
							pieces[i][j].file=board[i][j].file;
							pieces[i][j].rank=board[i][j].rank;
						}
						else if(board[i][j] instanceof Rook)
						{
							pieces[i][j]=new Rook(board[i][j].color);
							pieces[i][j].file=board[i][j].file;
							pieces[i][j].rank=board[i][j].rank;
						}
						else if(board[i][j] instanceof Bishop)
						{
							pieces[i][j]=new Bishop(board[i][j].color);
							pieces[i][j].file=board[i][j].file;
							pieces[i][j].rank=board[i][j].rank;
						}
						else if(board[i][j] instanceof Knight)
						{
							pieces[i][j]=new Knight(board[i][j].color);
							pieces[i][j].file=board[i][j].file;
							pieces[i][j].rank=board[i][j].rank;
						}
						else if(board[i][j] instanceof King)
						{
							pieces[i][j]=new King(board[i][j].color);
							pieces[i][j].file=board[i][j].file;
							pieces[i][j].rank=board[i][j].rank;
						}
						else if(board[i][j] instanceof Queen)
						{
							pieces[i][j]=new Queen(board[i][j].color);
							pieces[i][j].file=board[i][j].file;
							pieces[i][j].rank=board[i][j].rank;
						}
					}
				}
		}
		*/
		
		
		boolean checkUpRight(Piece piece,int rank,int file,Piece[][] pieces)
		{
			int i=this.rank+1;
			int j=Piece.fileNumber(this.file)+1;
			Piece temp=piece;
			while(i<9 && j<9)
			{
				if(i==piece.rank && j==Piece.fileNumber(piece.file))
				{
					++i;
					++j;
					continue;
				}
				else if(i==rank && j==file)
					break;
				if(pieces[i][j]!=null)
				{
					System.out.println("CHUUUUUUUUUUUUUUUUUUj");
					Piece checking=pieces[i][j];
					if(pieces[i][j].color==this.color)
						break;
					else if(checking instanceof Queen || checking instanceof Bishop)
						return true;
				}
				++i;
				++j;
			}
			return false;	
		}
		
		boolean checkUpLeft(Piece piece,int rank,int file,Piece[][] pieces)
		{
			int i=this.rank+1;
			int j=Piece.fileNumber(this.file)-1;
			Piece temp=piece;
			pieces[piece.rank][Piece.fileNumber(piece.file)]=null;
			//pieces[rank][file]=piece;
			System.out.print(" "+this.rank+" "+this.file+" ");
			/*while(i<9 && j>0)
			{
				if(pieces[i][j]!=null)
				{
					Piece checking=pieces[i][j];
					if(pieces[i][j].color==this.color)
						break;
					else if(checking instanceof Queen || checking instanceof Bishop)
					{
						pieces[temp.rank][Piece.fileNumber(temp.file)]=temp;
						pieces[rank][file]=null;
						System.out.print(this.rank+" "+this.file);
						return true;
					}
				}
				++i;
				--j;
			}*/
			
			pieces[temp.rank][Piece.fileNumber(temp.file)]=temp;
			pieces[rank][file]=null;
			return false;	
		}
		
		
		boolean checkDownRight(Piece piece,int rank,int file,Piece[][] pieces)
		{
			int i=this.rank-1;
			int j=Piece.fileNumber(this.file)+1;
			Piece temp=piece;
			pieces[piece.rank][Piece.fileNumber(piece.file)]=null;
			pieces[rank][file]=piece;
			/*while(i>0 && j<9)
			{
				if(pieces[i][j]!=null)
				{
					Piece checking=pieces[i][j];
					if(pieces[i][j].color==this.color)
						break;
					else if(checking instanceof Queen || checking instanceof Bishop)
					{
						pieces[temp.rank][Piece.fileNumber(temp.file)]=temp;
						pieces[rank][file]=null;
						return true;
					}
				}
				--i;
				++j;
			}*/
			pieces[temp.rank][Piece.fileNumber(temp.file)]=temp;
			pieces[rank][file]=null;
			return false;	
		}
		
		boolean checkDownLeft(Piece piece,int rank,int file,Piece[][] pieces)
		{
			int i=this.rank-1;
			int j=Piece.fileNumber(this.file)-1;
			Piece temp=piece;
			pieces[piece.rank][Piece.fileNumber(piece.file)]=null;
			pieces[rank][file]=piece;
			/*while(i>0 && j>0)
			{
				if(pieces[i][j]!=null)
				{
					Piece checking=pieces[i][j];
					if(pieces[i][j].color==this.color)
						break;
					else if(checking instanceof Queen || checking instanceof Bishop)
					{
						pieces[temp.rank][Piece.fileNumber(temp.file)]=temp;
						pieces[rank][file]=null;
						return true;
					}
				}
				--i;
				--j;
			}*/
			pieces[temp.rank][Piece.fileNumber(temp.file)]=temp;
			pieces[rank][file]=null;
			return false;	
		}
		
		boolean checkLeft(Piece piece,int rank,int file,Piece[][] pieces)
		{
			int i=this.rank;
			int j=Piece.fileNumber(this.file)-1;
			Piece temp=piece;
			pieces[piece.rank][Piece.fileNumber(piece.file)]=null;
			pieces[rank][file]=piece;
			while(j>0)
			{
				if(pieces[i][j]!=null)
				{
					Piece checking=pieces[i][j];
					if(pieces[i][j].color==this.color)
						break;
					else if(checking instanceof Queen || checking instanceof Bishop)
					{
						pieces[temp.rank][Piece.fileNumber(temp.file)]=temp;
						pieces[rank][file]=null;
						return true;
					}
				}
				--j;
			}
			pieces[temp.rank][Piece.fileNumber(temp.file)]=temp;
			pieces[rank][file]=null;
			return false;	
		}
		

}