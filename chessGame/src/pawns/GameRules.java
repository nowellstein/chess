package pawns;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import gui.Square;

public class GameRules {
	
	public Piece [][]Pieces;
	
	public GameRules()
	{
		Pieces=new Piece[9][9];
		for(int i=0;i<9;++i)
			for(int j=0;j<9;++j)
				Pieces[i][j]=null;	
	}
	
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
	public void clearGameRules()
	{
		for(int i=0;i<9;++i)
			for(int j=0;j<9;++j)
				Pieces[i][j]=null;	
	}
	
	public static class ToHighlight
	{
		public int rank=0;
		public char file=0;

		public ToHighlight()
		{
			
		}
	}
	
	public ArrayList<ToHighlight> returnLegalMoves(Piece piece)
	{	
		ArrayList<ToHighlight> moves = new ArrayList<ToHighlight>();
		ArrayList<ToHighlight> illegalmoves = new ArrayList<ToHighlight>();
		if(piece instanceof Pawn)
		{
			piece=(Pawn)piece;
			moves=((Pawn) piece).movePawn((Pawn)piece,Pieces); //ruchy ktore moze zrobic pion
			//illegalmoves=returnIllegalMoves((Pawn)piece, moves);
		/*	for(int i=0;i<illegalmoves.size();++i)
			{
				System.out.println("co do chuj");
				ToHighlight deleteMove=new ToHighlight();
				deleteMove=illegalmoves.get(i);
				for(int j=0;j<moves.size();++j)
				{
					ToHighlight checkMove=new ToHighlight();
					if(checkMove.file==deleteMove.file && checkMove.rank==deleteMove.rank)
						moves.remove(j);
				}
			}*/
			
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

		
		
		return moves;
	}
	
	public void deleteMoves(ArrayList<ToHighlight> moves, ArrayList<ToHighlight> illegalMoves)
	{
		
		
	}
	
	public ArrayList<ToHighlight> returnIllegalMoves(Piece piece,ArrayList<ToHighlight> moves)
	{
		ArrayList<ToHighlight> illegamlMoves = new ArrayList<ToHighlight>();
		ArrayList<ToHighlight> possibleFutureMoves = new ArrayList<ToHighlight>();
		
		int size=moves.size();
		Piece[][] possibleMoves=new Piece[9][9];
		possibleMoves=Pieces;
		if(piece instanceof Pawn)
		{
			for(int k=0;k<size;++k)
			{
				ToHighlight futureMove=new ToHighlight();
				futureMove=moves.get(k);
				possibleMoves[futureMove.rank][Piece.fileNumber(futureMove.file)]=(Pawn)piece;
				for(int i=1;i<9;++i)
					for(int j=1;j<9;++j)
					{
						if(possibleMoves[i][j]!=null && possibleMoves[i][j].color!=piece.color)
						{
							possibleFutureMoves=returnLegalMoves(possibleMoves[i][j]);
							for(int l=0;l<possibleFutureMoves.size();++l)
							{
								ToHighlight check=new ToHighlight();
								check=possibleFutureMoves.get(l);
								if(possibleMoves[check.rank][Piece.fileNumber(check.file)] instanceof King && possibleMoves[check.rank][Piece.fileNumber(check.file)].color==piece.color)
									System.out.println("nie");
									//illegamlMoves.add(check);
							}
						}
					}
				
			}
			
		}
		/*if(piece instanceof Knight)
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
		}*/
		return illegamlMoves;
	}
	
}
