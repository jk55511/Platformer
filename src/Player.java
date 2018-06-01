import javax.imageio.*;
import java.io.*;

public class Player extends RealGObject {
    
    public int myXV;
    public int myYV;
    
    public Player(Game game, int y, int x){
        super( game, y, x, 10, 10);
        try {
        	InputStream image = getClass().getResourceAsStream("/playerSprite.png");
        	mySprite = ImageIO.read(image); 
        	image.close();
        }
        catch (IOException a) { System.out.println("No Sprite Found for"+this); System.exit(0);}
    }
    
    public void update(){
        myX++;
    }
    
    public String toString(){
        return " Player";
    }
}
