import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class WinCoin extends InvisGObject {

	private BufferedImage mySprite;
	private BufferedImage endSprite;
	private CollisonBox myCBox;
	private boolean toEnd = false;
	
	public WinCoin(Game game, int y, int x, int l, int w) {
		super(game);
		try {
        	InputStream image = getClass().getResourceAsStream("/coin.png");
        	mySprite = ImageIO.read(image); 
        	image.close();
        	int[] temp = {y, x};
        	myCBox = new CollisonBox(temp, l, w);
        	myX = x;
        	myY = y;
        	//End Sprite
        	image = getClass().getResourceAsStream("/endSprite.png");
        	endSprite = ImageIO.read(image); 
        	image.close();
        }
        catch (IOException a) { System.out.println("No Sprite Found for"+this); System.exit(0);}
	}

	@Override
	public void render(Graphics g){
        if(!toEnd) {
        	g.drawImage(mySprite, myX, myY, 100, 100,  myGame.getGameWindow().myCanvas);        
        }
        else
        	g.drawImage(endSprite, 0, 0, 1920, 1080, myGame.getGameWindow().myCanvas);
    }
	
	@Override
	public void update() {
		if(toEnd) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.exit(0);
		}
		for(RealGObject a:myGame.colObjs) {	
			if(a.toString().equals(" Player") && myCBox.isColliding(a.myCBox, 0, 0)) {
				toEnd = true;
			}
		}
	}
	

}
