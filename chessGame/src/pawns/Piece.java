package pawns;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * Klasa {@link Piece} dziedzicz¹ca po JComponent jest klas¹ bazow¹ dla wszystkich
 *	bierek bior¹cych udzia³ w grze.<br> Przechowuje kolor pionka jak boolean, jego pozycje na szachownicy wyra¿on¹ w char i int
 *	oraz nazwê komponentu.
 *
 */


public class Piece extends JComponent{

	public boolean color;
	public String name;
	private Image image;
	public char file='z';
	public int rank=-1;
	
	/**
	 *	Konstruktor klasy Piece który nadaje obiektowi nazwê oraz ³aduje ikonê elementu.
	 *
	 * @param  piecePath zawiera nazwê elementu do za³adowania (np.Pawn)
	 * @param  color kolor piona wyra¿ony typem boolean
	 * @see        	JComponent
	 */

	public Piece(String piecePath,boolean color){
		super();
		
		this.color=color;
		File imageFile=new File("images/"+piecePath+"White.png");
		name=piecePath+"White";
		if(color==false)
		{
			name=piecePath+"Black";
			imageFile=new File("images/"+piecePath+"Black.png");
		}
	
		try{
			image=ImageIO.read(imageFile);
		}catch(IOException e){
			System.err.println("Error reading image");
			e.printStackTrace();
		}	
	}
	/**
	 *	Funkcja zwracaj¹ca wartoœæ numeryczn¹ kolumny szachownicy na której jest bierka.
	 *
	 * @param file element typu char do zamiany na int
	 * @return      zwraca wartoœæ numeryczn¹ kolumny szachownicy
	 */
	
	public static int fileNumber(char file)
	{
		for(int i=0;i<8;++i)
			if(file==(char)('a'+i))
			{
				return i+1;
			}
		return 0;
	}
	/**
	 *	Funkcja zwracaj¹ca wartoœæ symboliczn¹ kolumny szachownicy na której jest bierka.
	 *
	 * @param file element typu int do zamiany na char
	 * @return      zwraca wartoœæ symboliczn¹ kolumny szachownicy
	 */
	
	public static char numberFile(int file)
	{
		return (char)('a'-1+file);
	}
	/**
	 *	Nadpisana metoda pozwalaj¹ca na rysowanie bierki
	 *
	 * @param g informacje potrzebne do rysowania
	 * @see         Graphics
	 */
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(image,0,0,this.getWidth(),this.getHeight(),this);
		
	}
	

	
	
}