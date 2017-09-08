package chessGame;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLayeredPane;


import pawns.*;
import pawns.GameRules.ToHighlight;
import gui.ChessBoard;
import gui.ChessboardSpot;
import gui.Window;
import net.miginfocom.swing.MigLayout;
import gui.Square;
import gui.GameLog;
import gui.MenuBar;
import gui.ChessboardSpot;

public class FrameManager {
	
	public Window window;
	
	public Game game;
	private ChessBoard board;
	public MenuBar menuBar;
	public GameLog gameLog;
	private ChessboardSpot chessboardSpot;
	private ArrayList<ToHighlight> options;
	
	
	public boolean currentPlayer=true;
	public FrameManager()
	{
		
	}
	
	public void prepareGui(Game game)
	{
		this.game=game;
		window=new Window();
		board=new ChessBoard(this);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setLayout(new MigLayout("", "[10%:12%:12.5%,growprio 25,grow,shrinkprio 12,fill][60%:65%:66.6%,growprio 25,grow,shrinkprio 66,fill][18%:20%:20.8%,growprio 25,grow,shrinkprio 20,fill]", "[100%:100%:100%,grow,fill]"));
		window.addJLayeredPane(layeredPane);
		menuBar=new MenuBar(this);
		gameLog=new GameLog();
		chessboardSpot= new ChessboardSpot();
		layeredPane.add(gameLog);
		layeredPane.add(chessboardSpot);
		layeredPane.add(menuBar);

	}
	
	public void prepareChessboard()
	{
		chessboardSpot.add(board);
	}
	
	public void startGame()
	{
		currentPlayer=true;
		board.addPawns();
		game.round=1;
		game.setGameStart();
		board.revalidate();
		board.repaint();	
	}
	
	public void endGame()
	{
		board.clearChessboard();
		gameLog.clearLog();
		menuBar.setGameStarted(false);
		prepareChessboard();
		board.revalidate();
		board.repaint();
	}

	public void getMove(Piece piece,char file,int rank, ArrayList<ToHighlight> options ){
		
		for(int i=0;i<options.size();++i)
		{
			if( options.get(i).file==file && options.get(i).rank==rank)
			{
				if(Game.countMoves%2==0)
					gameLog.updateLog("\n"+ game.round+". ");
				gameLog.updateLog("\n"+piece.file+""+piece.rank+" -> "+file+""+rank+" ");
				gameLog.updateLog(piece.file,piece.rank,file,rank);
				game.makeMove(piece,rank,file);
				piece.rank=rank;
				piece.file=file;
				board.Squares[rank][Square.fileNumber(file)].add(piece);
				changeSide();				
			}
		}

		board.repaint();
		board.revalidate();
		
	}
	public boolean beatPiece(Piece piece,char file,int rank, ArrayList<ToHighlight> options,Piece toBeat ){
		
		for(int i=0;i<options.size();++i)
		{
			if(options.get(i).file==file && (options.get(i).rank==rank))
			{
				System.out.println("bity");
				game.gameRules.Pieces[rank][Square.fileNumber(file)]=null;
				board.Squares[rank][Square.fileNumber(file)].remove(toBeat);
				return true;
			}
		}
		
		
		return false;
	}
	
	public void setGame(char fileFrom,int rankFrom,char fileTo,int rankTo)
	{
		Piece piece=(Piece)board.Squares[rankFrom][Square.fileNumber(fileFrom)].getComponent(0);
		if(board.checkIfEmpty(fileTo, rankTo))
			getMove(piece,fileTo,rankTo,game.gameRules.returnLegalMoves(piece));
		else
		{
			Piece toBeat=(Piece)board.Squares[rankTo][Square.fileNumber(fileTo)].getComponent(0);
			this.options=game.gameRules.returnLegalMoves(piece);
			beatPiece(piece, fileTo, rankTo, game.gameRules.returnLegalMoves(piece), toBeat);
			getMove(piece,fileTo,rankTo,options);
			options=null;
			System.out.println("tak");
		}
	}
	
	

	private void changeSide()
	{
		currentPlayer=!currentPlayer;
	}
	

}
