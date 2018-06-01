public class UI {

    public static Game game;

    public static void main(String[] args) {
	    // write your code here
	    game = new Game(60);
	    Game.GObjects.add(new Player(game, 100, 100));
	    game.gameStart();
    }
}
