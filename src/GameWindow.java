import java.awt.*;
import javax.swing.*;

public class GameWindow extends JFrame{
    
    private static final long serialVersionUID = 1;
    public GCanvas myCanvas = new GCanvas();
    
    public GameWindow(){
        myCanvas.setPreferredSize(new Dimension(1920, 1080));
        myCanvas.setLayout(new FlowLayout());
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
    
}
