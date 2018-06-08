import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Background extends InvisGObject {

	private BufferedImage myRec;
	
	public Background(Game game) {
		super(game);
		try {
        	InputStream image = getClass().getResourceAsStream("/Background.jpg");
        	myRec = ImageIO.read(image); 
        	image.close();
        }
        catch (IOException a) { System.out.println("No Sprite Found for"+this); System.exit(0);}
	}
	
	public void render(Graphics g){
        g.drawImage(myRec, 0, 0, myGame.getGameWindow().myCanvas);        
    }

}
