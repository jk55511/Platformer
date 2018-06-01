import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;

public class GameWindow extends JFrame implements KeyListener{
   
	//Current Key States
    //w = 0, a = 1, s = 2, d = 3
    public ArrayList<Boolean> keyStates = new ArrayList<Boolean>();
    //Change depending on number of used keys
    private final int keysUsed = 4;
    
    private static final long serialVersionUID = 1;
    public GCanvas myCanvas = new GCanvas();
    
    public GameWindow(){
    	//Initialize keyStates
    	for(int count = 0; count < keysUsed; count++)
    		keyStates.add(false);
    	
    	//Setup Canvas and Window
    	myCanvas.setPreferredSize(new Dimension(1920, 1080));
        myCanvas.setLayout(new FlowLayout());
        myCanvas.addKeyListener(this);
        myCanvas.setFocusable(true);
        this.setContentPane(myCanvas);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setTitle("Platformer");
        this.setVisible(true);
        
    }
   
   private class GCanvas extends JPanel{
		private static final long serialVersionUID = 1;
		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			for(GObject obj: Game.GObjects){
			    obj.render(g);
			}
		}
	}

@Override
public void keyPressed(KeyEvent e) {
	// TODO Auto-generated method stub
	System.out.println(e);
	if(e.getKeyChar() == 'w') {
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
	// TODO Auto-generated method stub
	if(e.getKeyChar() == 'w') {
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
	// TODO Auto-generated method stub
	
}
   
    
}
