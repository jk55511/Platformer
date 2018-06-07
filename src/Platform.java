import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class Platform extends RealGObject {
	
	public Platform(Game game, int y, int x) {
		super(game, y, x, 5, 5);
		try {
        	InputStream image = getClass().getResourceAsStream("/Bar.png");
        	mySprite = ImageIO.read(image); 
        	image.close();
        	myCBox.length = mySprite.getHeight();
        	myCBox.width = mySprite.getWidth();
        }
        catch (Exception a) { System.out.println("No Sprite Found for"+this); System.exit(0);}
	}

	@Override
	public void update() {

	}

}
