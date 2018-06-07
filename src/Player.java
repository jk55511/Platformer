import javax.imageio.*;
import java.io.*;
import java.util.*;

public class Player extends RealGObject {
    
    public int myXV;
    public int myYV;
    
    public Player(Game game, int y, int x){
        super( game, y, x, 600, 100);
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
    private final int GRAVITYACCEL = 3;
    
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
      //Y Movement Handling
        
        //JUMP
        boolean inAir = true;
            //Check for in Air
        for(RealGObject a:myGame.colObjs){
            if(a != this && myCBox.isColliding(a.myCBox, 1, 0)){
                inAir = false;
                System.out.println("whyza");
            }
        }
        if(keyStates.get(0) && !inAir) {
        	ySpeed-=jumpAccel;
        }
        if(inAir) {
        	ySpeed+=GRAVITYACCEL;
        }
       
        // DOWNWARDS MOTION
        if(keyStates.get(2)) {
        	ySpeed+=1;
        }
        
        //COLLSION CHECK SOMETHING WRONG
        System.out.println(ySpeed);
        if(xSpeed != 0 || ySpeed != 0){
            int xSpeedChange = xSpeed;
            int ySpeedChange = ySpeed;
        	for(RealGObject a:myGame.colObjs) {
            	
            	if(a != this && myCBox.isColliding(a.myCBox, xSpeed, 0)) {
            		xSpeedChange = a.myX - myX;
            		System.out.println("1678");
            	}
            	if(a != this && myCBox.isColliding(a.myCBox, 0, ySpeed)){
            		ySpeedChange = a.myY - myY+1;
            		System.out.println("279876");
            	}
            	if(a != this && myCBox.isColliding(a.myCBox, xSpeed, ySpeed)){
            		xSpeedChange = a.myX - myX;
            	    ySpeedChange = a.myY - myY+1;
            	    System.out.println("374658");
            	}
            	System.out.println("endtg");
            }
            xSpeed = xSpeedChange;
            ySpeed = ySpeedChange;
        }
        if(ySpeed > 15) {
        	ySpeed = 15;
        }
        myX+=xSpeed;
        myY+=ySpeed;
        
        System.out.println(ySpeed+"\nPOSITIOIN       "+myY+"   "+myX);
        myCBox.origin[0] = myY;
        myCBox.origin[1] = myX;
        
        
    }
    
    public String toString(){
        return " Player";
    }
}
