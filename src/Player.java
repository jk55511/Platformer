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
    private int ySpeed = 1;
    private int jumpAccel = 15;
    private final int GRAVITYACCEL = 2;
    
    public void update(){
     //X Movement Handling 
       ArrayList<Boolean> keyStates = myGame.gameWin.keyStates;
        if(keyStates.get(1) && xSpeed > -1*maxXSpeed) {
        	xSpeed-=xAccel;
        }
        if(keyStates.get(3) && xSpeed < maxXSpeed) {
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
        //JUMP
        boolean inAir = false;
            //Check for in Air
        for(RealGObject a:myGame.colObjs){
            if(a != this && myCBox.isColliding(a.myCBox, -1, 0)){
                inAir = true;
            }
        }
        if(keyStates.get(0) && !inAir) {
        	ySpeed-=jumpAccel;
        }
        if(keyStates.get(2)) {
        	ySpeed+=1;
        }
        
        //COLLSION CHECK
        if(xSpeed != 0){
            for(RealGObject a:myGame.colObjs) {
            	if(a != this && myCBox.isColliding(a.myCBox, xSpeed, 0)) {
            		xSpeed = a.myX - myX;
            	}
            	else if(a != this && myCBox.isColliding(a.myCBox, 0, ySpeed)){
            		ySpeed = a.myY - myY+1;
            	}else if(a != this && myCBox.isColliding(a.myCBox, xSpeed, ySpeed)){
            	    xSpeed = a.myX - myX;
            	    ySpeed = a.myY - myY+1;
            	}
            }
            	
        }
        myX+=xSpeed;
        
        
        
        
    }
    
    public String toString(){
        return " Player";
    }
}
