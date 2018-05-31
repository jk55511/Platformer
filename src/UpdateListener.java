import java.awt.event.*;

public class UpdateListener implements ActionListener{
    private Game myGame;
    
    UpdateListener(Game game){
        myGame = game;
    }
    
    public void actionPerformed(ActionEvent event){
        myGame.update();
    }
    
}
