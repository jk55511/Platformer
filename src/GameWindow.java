import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.*;

public class GameWindow extends JFrame implements KeyListener{
   
	//Current Key States
    //SPACE = 0, a = 1, s = 2, d = 3
    public ArrayList<Boolean> keyStates = new ArrayList<Boolean>();
    //Change depending on number of used keys
    private final int KEYSUSED = 4;
    
    private static final long serialVersionUID = 1;
    public GCanvas myCanvas = new GCanvas();
    
    public GameWindow(){
    	//Initialize keyStates
    	for(int count = 0; count < KEYSUSED; count++)
    		keyStates.add(false);
    	
    	//Setup Canvas and Window
    	myCanvas.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
    	myCanvas.setLayout(new FlowLayout());
        myCanvas.addKeyListener(this);
        myCanvas.setFocusable(true);
        this.setContentPane(myCanvas);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setSize(this.getPreferredSize());
        this.setTitle("Platformer");
        this.setVisible(true);
        
    }
   
   private class GCanvas extends JPanel{
		private static final long serialVersionUID = 1;
		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			BufferedImage frame = new BufferedImage(1920 ,1080 ,7);
			Graphics gra = frame.createGraphics();
			for(GObject obj: Game.GObjects){
				obj.render(gra);
			}
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			g.drawImage(frame, 0, 0, (int)(screenSize.getHeight()*(16.0/9.0)), (int)screenSize.getHeight(), myCanvas); 
		}
	}

@Override
public void keyPressed(KeyEvent e) {
	// Set State of pressed keys to true
	System.out.println(e);
	if(e.getKeyChar() == ' ') {
		keyStates.set(0, true);
	}
	if(e.getKeyChar() == 'a') {
		keyStates.set(1, true);
	}
	if(e.getKeyChar() == 's') {
		keyStates.set(2, true);
	}
	if(e.getKeyChar() == 'd') {
		keyStates.set(3, true);
	}
}

@Override
public void keyReleased(KeyEvent e) {
	// Set State of pressed keys to false
	if(e.getKeyChar() == ' ') {
		keyStates.set(0, false);
	}
	if(e.getKeyChar() == 'a') {
		keyStates.set(1, false);
	}
	if(e.getKeyChar() == 's') {
		keyStates.set(2, false);
	}
	if(e.getKeyChar() == 'd') {
		keyStates.set(3, false);
	}
}

@Override
public void keyTyped(KeyEvent e) {
	// TODO ATTACKS
	
}
   
    
}
