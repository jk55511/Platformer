import java.util.*;
import javax.swing.Timer;

public class Game {
    
	public static ArrayList<GObject> GObjects = new ArrayList<GObject>();
    private int tickRate;
    public String filePath;
    private GameWindow gameWin = new GameWindow();;
    
    public Game(int tick, String path){
        tickRate = tick;
        filePath = path;
    }
    
    public ArrayList<GObject> getGObjects(){
        return GObjects;
    }
    
    public GameWindow getGameWindow(){
        return gameWin;
    }
    
    private UpdateListener updatePerformer = new UpdateListener(this);
        
        
    
    private Timer timer = new Timer(tickRate, updatePerformer);
    
    public void update(){
        //Run code for all objects
        for(GObject obj: GObjects){
            obj.update();
        }
        //Render new objects
        gameWin.repaint();         
    }
    
    public void gameStart(){
        timer.start();
    }
    
    

}

