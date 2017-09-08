package chessGame;

import java.awt.EventQueue;

import gui.Window;


public class Chess {

	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game game=new Game();
					game.startGame();
				
			} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	
		
	}
}
