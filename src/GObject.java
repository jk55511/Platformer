import java.awt.*;

public abstract class GObject {
    
    private static int nextId = 1;
    public CollisonBox myCBox  = null;
    public int myId;
    public Game myGame;
    public int myY = -1;
    public int myX = -1;    
    
    public GObject(Game myG){
        myGame = myG;       
        myId = nextId; 
        nextId++;
    }
    
    public GObject(Game myG, int y, int x){
        myGame = myG;       
        myId = nextId; 
        nextId++;
        myY = y;
        myX= x;
    }
    
    public void die(){
        //Figure out how to handle dynamic Ids
        myGame.getGObjects().remove(myId);
    }
    
    //If object has no position it will return -1
    public int getYPos(){
        return myY;
    }
    public int getXPos(){
        return myX;
    }
    
    public abstract void render(Graphics g);
    
    public abstract void update();
    
}
