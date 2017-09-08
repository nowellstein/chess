package pawns;

import java.util.ArrayList;

import pawns.GameRules.ToHighlight;

final public class King extends Piece{
	private int check=0;

	
	public King(boolean color){
		super("king",color);
		this.file='e';
		if(color==true)
			this.rank=1;
		else if(color==false)
			this.rank=8;
	}

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
	
	
	
	
		public boolean Up(int rank,int file,Piece[][] pieces)
		{
			if(rank+1>8)
				return false;
			else if(pieces[rank+1][file]==null || pieces[rank+1][file].color!=this.color)
				return true;
			else
				return false;
		}
		public boolean Down(int rank,int file,Piece[][] pieces)
		{
			if(rank-1<1)
				return false;
			else if(pieces[rank-1][file]==null || pieces[rank-1][file].color!=this.color)
				return true;
			else
				return false;
		}
		public boolean Left(int rank,int file,Piece[][] pieces)
		{
			if(file-1<1)
				return false;
			else if(pieces[rank][file-1]==null || pieces[rank][file-1].color!=this.color)
				return true;
			else
				return false;
		}
		public boolean Right(int rank,int file,Piece[][] pieces)
		{
			if(file+1>8)
				return false;
			else if(pieces[rank][file+1]==null || pieces[rank][file+1].color!=this.color)
				return true;
			else
				return false;
		}
		public boolean UpRight(int rank,int file,Piece[][] pieces)
		{
			if(rank+1>8 || file+1>8)
				return false;
			else if(pieces[rank+1][file+1]==null || pieces[rank+1][file+1].color!=this.color)
				return true;
			else
				return false;
		}
		public boolean DownRight(int rank,int file,Piece[][] pieces)
		{
			if(rank-1<1 || file+1>8)
				return false;
			else if(pieces[rank-1][file+1]==null || pieces[rank-1][file+1].color!=this.color)
				return true;
			else
				return false;
		}
		public boolean UpLeft(int rank,int file,Piece[][] pieces)
		{
			if(rank+1>8 || file-1<1)
				return false;
			else if(pieces[rank+1][file-1]==null || pieces[rank+1][file-1].color!=this.color)
				return true;
			else
				return false;
		}
		public boolean DownLeft(int rank,int file,Piece[][] pieces)
		{
			if(rank-1<1 || file-1<1)
				return false;
			else if(pieces[rank-1][file-1]==null || pieces[rank-1][file-1].color!=this.color)
				return true;
			else
				return false;
		}
}