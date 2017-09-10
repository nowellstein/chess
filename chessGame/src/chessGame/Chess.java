package chessGame;

import java.awt.EventQueue;

import gui.Window;
import pawns.Piece;
import sun.applet.Main;


/**
 * Klasa {@link Chess} zawiera static void main i odpowiada za wykonanie ca³ego kodu programu.
 *	
 */ 
public class Chess {

	/**
	 *	Funkcja wykonuj¹ca kod programu.
	 *
	 * @param  args argumenty wywo³ania funkcji main.
	 */
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
