import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameDriver {

	public static void main(String[] args) throws IOException {
		Game game = Game.getInstance();
		
		game.startGame();
		
		while(game.inProgress)
		{
			
			game.playGame();
			game.endGame();
			game.replay();
		}
			
			
			

	}

}
