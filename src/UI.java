public class UI {

    public static Game game;

    public static void main(String[] args) {
	    // write your code here
	    game = new Game(30);
	    Game.GObjects.add(new Background(game));
	    Game.GObjects.add(new Player(game, 100, 100));
	    Game.GObjects.add(new Platform(game, 900, 0, 400, 50));
	    Game.GObjects.add(new Platform(game, 600, 400, 400, 50));
	    Game.GObjects.add(new Platform(game, 800, 800, 400, 50));
	    Game.GObjects.add(new Platform(game, 300, 800, 400, 50));
	    Game.GObjects.add(new WinCoin(game, 190, 1000, 100, 100));
	    game.gameStart();
    }
}
