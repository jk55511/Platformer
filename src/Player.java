import javax.imageio.*;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.io.*;
import java.util.*;

public class Player extends RealGObject {
    
    public int myXV;
    public int myYV;
    
    public Player(Game game, int y, int x){
        super( game, y, x, 100, 100);
        try {
        	InputStream image = getClass().getResourceAsStream("/idle0.png");
        	mySprite = ImageIO.read(image); 
        	image.close();
        	myCBox.length = 99;
        	myCBox.width = 63;
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
    private double spriteTimer = 0;
    private String prevType = "idle";
    private int lastDir = 1;
    
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
            if(a != this && myCBox.isColliding(a.myCBox, 1, 1)){
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
        if(xSpeed != 0 || ySpeed != 0){
            int xSpeedChange = xSpeed;
            int ySpeedChange = ySpeed;
        	for(RealGObject a:myGame.colObjs) {
            	int xOffSet;
            	int yOffSet;
            	//Set offsets to fix collision
            	if(myY <= a.myY)
            		yOffSet = -1;
            	else
            		yOffSet = 1;
            	
            	if(myX <= a.myX)
            		xOffSet = 1;
            	else
            		xOffSet = -1;
        		
            	if(myX > a.myX) {
        			if(a != this && myCBox.isColliding(a.myCBox, xSpeed, yOffSet)) {
        				xSpeedChange = a.myX - myX + a.myCBox.width;
        			}
        		}else {
        			if(a != this && myCBox.isColliding(a.myCBox, xSpeed, yOffSet)) {
        				xSpeedChange = myX + myCBox.width - a.myX; 
        			}
        		}
           
            	if(a.myY >= myY) {	
        			if(a != this && myCBox.isColliding(a.myCBox, xOffSet, ySpeed)){
            			ySpeedChange = a.myY- myY - myCBox.length;
            		}
            	}
        		else
        			if(a != this && myCBox.isColliding(a.myCBox, xOffSet, ySpeed)){
            			ySpeedChange = a.myY + a.myCBox.length - myY;
        			}
            
        		}
            xSpeed = xSpeedChange;
            ySpeed = ySpeedChange;
        }
        if(ySpeed > 50) {
        	ySpeed = 50;
        }
        myX+=xSpeed;
        myY+=ySpeed;
        
        myCBox.origin[0] = myY;
        myCBox.origin[1] = myX;
        //Kill Player 
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        if(myY-150 > (int)screenSize.getHeight()) {
        	this.die();
        }
        //Sprite Handling
        String spriteType;
        if(inAir) {
        	spriteType = "midair";
        }
        else if(keyStates.get(0)) {
        	spriteType = "jump";
        }
        else if(keyStates.get(3) || keyStates.get(1)) {
        	spriteType = "run";
        }
        else 
        	spriteType = "idle";
      
        if(!spriteType.equals(prevType))
        	spriteTimer = 0;
        prevType = spriteType;
        try {
        	InputStream image = getClass().getResourceAsStream("/"+spriteType+(int)spriteTimer+".png");
        	mySprite = ImageIO.read(image); 
        	image.close();
        }
        catch (IOException a) { System.out.println("No Sprite Found for"+this); System.exit(0);}
        spriteTimer += 0.4;
        if(spriteTimer >= 8 && spriteType.equals("run") || spriteTimer >= 12 && spriteType.equals("idle") || spriteTimer >= 2.0 && spriteType.equals("midair"))
        	spriteTimer = 0;
    }
    
    @Override
    public void render(Graphics g) {
    	ArrayList<Boolean> keyStates = myGame.gameWin.keyStates;
    	if(keyStates.get(3) || lastDir == 0 && !keyStates.get(1)) {
    		g.drawImage(mySprite, myX, myY, 63, 99, myGame.getGameWindow().myCanvas);
    	}
    	else {
    		lastDir = 1;
    		g.drawImage(mySprite, myX + 63, myY, -63, 99, myGame.getGameWindow().myCanvas);        
    	}
    }
    
    public void die() {
    	super.die();
    	System.exit(0);
    }
    
    public String toString(){
        return " Player";
    }
}
