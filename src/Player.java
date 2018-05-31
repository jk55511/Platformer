import javax.imageio.*;
import java.io.*;

public class Player extends RealGObject {
    
    public int myXV;
    public int myYV;
    
    public Player(Game game, int y, int x){
        super( game, y, x, 10, 10);
        try {mySprite = ImageIO.read(new File(myGame.filePath+"\\playerSprite.jpg"));}
        catch (IOException a) { System.out.println("No Sprite Found for"+this); System.exit(0);};
    }
    
    public void update(){
        
    }
    
    public String toString(){
        return " Player";
    }
}
