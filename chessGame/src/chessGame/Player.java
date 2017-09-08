package chessGame;
import pawns.*;
import java.util.HashMap;
	

public class Player {
	
	public boolean color;
	public HashMap<String,Piece> player;
	
	public Player(String stringColor)
	{
		if(stringColor=="white")
			color=true;
		else if(stringColor=="black")
			color=false;
		
		player=new  HashMap<String,Piece>();
		for(int i=1;i<9;++i)
		{
			if(color)
				player.put("whitePawn"+i, new Pawn(true));
			else
				player.put("blackPawn"+i, new Pawn(false));
		}
		if(color)
		{
			player.put("whiteRook1", new Rook(true));
			player.put("whiteKnight1", new Knight(true));
			player.put("whiteBishop1", new Bishop(true));
			player.put("whiteQueen", new Queen(true));
			player.put("whiteKing", new King(true));
			player.put("whiteBishop2", new Bishop(true));
			player.put("whiteKnight2", new Knight(true));
			player.put("whiteRook2", new Rook(true));
		}
		else
		{
			player.put("blackRook1", new Rook(false));
			player.put("blackKnight1", new Knight(false));
			player.put("blackBishop1", new Bishop(false));
			player.put("blackQueen", new Queen(false));
			player.put("blackKing", new King(false));
			player.put("blackBishop2", new Bishop(false));
			player.put("blackKnight2", new Knight(false));
			player.put("blackRook2", new Rook(false));
		}
		
		
	}


	public Piece CurrentPiece(String name)
	{
		return this.player.get(name);
	}
}