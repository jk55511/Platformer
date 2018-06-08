import java.awt.Graphics;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Platform extends RealGObject {
	private int width;
	private int length;
	
	public Platform(Game game, int y, int x, int w, int l) {
		super(game, y, x, 5, 5);
		try {
        	InputStream image = getClass().getResourceAsStream("/Bar.png");
        	mySprite = ImageIO.read(image); 
        	image.close();
        	width = w;
        	length = l;
        	myCBox.length = l;
        	myCBox.width = w;
        }
        catch (Exception a) { System.out.println("No Sprite Found for"+this); System.exit(0);}
	}

	@Override
	public void render(Graphics g) {
	    g.drawImage(mySprite, myX, myY, width, length, myGame.getGameWindow().myCanvas);
	}

	@Override
	public void update() {
		
	}

}
