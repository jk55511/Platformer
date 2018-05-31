import java.awt.*;
import javax.imageio.*;
import java.io.*;


public abstract class RealGObject extends GObject{
    
    private CollisonBox myCBox;
    public Image mySprite;
    
    public RealGObject(Game game, int y, int x, int l, int w){
        super(game, y , x);
        int[] temp = {y, x};
        myCBox = new CollisonBox(temp, l ,w);
    }
    
    public void render(Graphics g){
        g.drawImage(mySprite, myX, myY, myGame.getGameWindow().myCanvas);        
    }
    
    public abstract void update();
    
}