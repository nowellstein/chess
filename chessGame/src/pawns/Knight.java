package pawns;

import java.util.ArrayList;

import pawns.GameRules.ToHighlight;

public class Knight extends Piece{
	static int count=0;
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
	
	public boolean moveUpRight(int rank,int file,Piece[][] pieces)
	{
		if(rank+2>8 || file+1>8)
			return false;
		else if(pieces[rank+2][file+1]!=null && pieces[rank+2][file+1].color==this.color)
			return false;
		else
			return true;
			
	}
	
	public boolean moveUpLeft(int rank,int file,Piece[][] pieces)
	{
		if(rank+2>8 || file-1<1)
			return false;
		else if(pieces[rank+2][file-1]!=null && pieces[rank+2][file-1].color==this.color)
			return false;
		else
			return true;
			
	}

	public boolean moveDownRight(int rank,int file,Piece[][] pieces)
	{
		if(rank-2<1 || file+1>8)
			return false;
		else if(pieces[rank-2][file+1]!=null && pieces[rank-2][file+1].color==this.color)
			return false;
		else
			return true;
			
	}
	
	public boolean moveDownLeft(int rank,int file,Piece[][] pieces)
	{
		if(rank-2<1 || file-1<1)
			return false;
		else if(pieces[rank-2][file-1]!=null && pieces[rank-2][file-1].color==this.color)
			return false;
		else
			return true;
			
	}
	
	public boolean moveRightDown(int rank,int file,Piece[][] pieces)
	{
		if(rank-1<1 || file+2>8)
			return false;
		else if(pieces[rank-1][file+2]!=null && pieces[rank-1][file+2].color==this.color)
			return false;
		else
			return true;
	}
	
	public boolean moveRightUp(int rank,int file,Piece[][] pieces)
	{
		if(rank+1>8 || file+2>8)
			return false;
		else if(pieces[rank+1][file+2]!=null && pieces[rank+1][file+2].color==this.color)
			return false;
		else
			return true;
	}
	
	public boolean moveLeftUp(int rank,int file,Piece[][] pieces)
	{
		if(rank+1>8 || file-2<1)
			return false;
		else if(pieces[rank+1][file-2]!=null && pieces[rank+1][file-2].color==this.color)
			return false;
		else
			return true;
	}
	
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
