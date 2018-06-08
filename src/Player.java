import javax.imageio.*;
import java.io.*;
import java.util.*;

public class Player extends RealGObject {
    
    public int myXV;
    public int myYV;
    
    public Player(Game game, int y, int x){
        super( game, y, x, 100, 100);
        try {
        	InputStream image = getClass().getResourceAsStream("/playerSprite.png");
        	mySprite = ImageIO.read(image); 
        	image.close();
        	myCBox.length = mySprite.getHeight();
        	myCBox.width = mySprite.getWidth();
        }
        catch (IOException a) { System.out.println("No Sprite Found for"+this); System.exit(0);}
    }
    
    private int maxXSpeed = 15;
    private int xSpeedDecay = 2;
    private int xAccel = 1;
    private int xSpeed = 1;
    private int ySpeed = 1;
    private int jumpAccel = -50;
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
            }
        }
        if(keyStates.get(0) && !inAir) {
        	ySpeed = jumpAccel;
        }
        if(inAir) {
        	ySpeed+=GRAVITYACCEL;
        }
       
        // DOWNWARDS MOTION
        if(keyStates.get(2)) {
        	ySpeed+=1;
        }
        
        //COLLSION CHECK SOMETHING WRONG
        System.out.println("BEFORE COLL    "+ySpeed);
        if(xSpeed != 0 || ySpeed != 0){
            int xSpeedChange = xSpeed;
            int ySpeedChange = ySpeed;
        	for(RealGObject a:myGame.colObjs) {
            	
        		if(a != this && myCBox.isColliding(a.myCBox, xSpeed, -1)) {
            		xSpeedChange = a.myX - myX + a.myCBox.width;
            	}
           
            	if(a != this && myCBox.isColliding(a.myCBox, 1, ySpeed)){
            		ySpeedChange = a.myY- myY - myCBox.length;
            		System.out.println("COLLISON OCCUREED IN Y\nDistance   "+ySpeedChange);
            	}
            
            }
            xSpeed = xSpeedChange;
            ySpeed = ySpeedChange;
            System.out.println("AFTER COLLISON CHECK        "+ySpeed);
        }
        if(ySpeed > 50) {
        	ySpeed = 50;
        }
        myX+=xSpeed;
        myY+=ySpeed;
        
        System.out.println(ySpeed+"\nPOSITIOIN       "+myY+"   "+myX);
        System.out.println("YSPEED   "+ySpeed);
        myCBox.origin[0] = myY;
        myCBox.origin[1] = myX;
        
        
    }
    
    
    public String toString(){
        return " Player";
    }
}
