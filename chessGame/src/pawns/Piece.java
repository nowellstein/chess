package pawns;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JPanel;


public class Piece extends JComponent{

	public boolean color;
	public String name;
	private Image image;
	public char file='z';
	public int rank=-1;

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
	public static int fileNumber(char file)
	{
		for(int i=0;i<8;++i)
			if(file==(char)('a'+i))
			{
				return i+1;
			}
		return 0;
	}
	public static char numberFile(int file)
	{
		return (char)('a'-1+file);
	}
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(image,0,0,this.getWidth(),this.getHeight(),this);
		
	}
	
	
}