import javax.imageio.*;
import java.io.*;
import java.util.*;

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
    
    private int maxXSpeed = 15;
    private int xSpeedDecay = 3;
    private int xAccel = 1;
    private int xSpeed = 1;
    
    public void update(){
     //X Movement Handling 
       ArrayList<Boolean> keyStates = myGame.gameWin.keyStates;
        if(keyStates.get(1) && xSpeed >= -1*maxXSpeed) {
        	xSpeed-=xAccel;
        }
        if(keyStates.get(3) && xSpeed <= maxXSpeed) {
        	xSpeed+=xAccel;
        }
        
        //Speed Decay for x direction
        if(xSpeed != 0){
            if(xSpeed < 0 && !keyStates.get(1)){
                if(xSpeed+xSpeedDecay > 0)
                    xSpeed = 0;
                else 
                    xSpeed+=xSpeedDecay;
            }
            else
            if(xSpeed > 0 && !keyStates.get(3)){
                if(xSpeed-xSpeedDecay < 0)
                    xSpeed = 0;
                else 
                    xSpeed-=xSpeedDecay;                
            }
        }
        //COLLSION X CHECK
        if(xSpeed != 0){
            for(RealGObject a:myGame.colObjs) {
            	if(myCBox.isColliding(a.myCBox, xSpeed, 0)) {
            		xSpeed = a.myX - myX;
            	}
            	else {
            		
            	}
            }
            	
        }
        
        myX+=xSpeed;
        if(keyStates.get(0)) {
        	myY-=5;
        }
        if(keyStates.get(2)) {
        	myY+=5;
        }
        
        
        
    }
    
    public String toString(){
        return " Player";
    }
}
