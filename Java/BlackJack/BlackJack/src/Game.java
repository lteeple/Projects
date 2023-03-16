
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game {
	Player player;
	Deck deck;
	Dealer dealer;
	boolean playerWins = false;
	boolean inProgress = true;
	private static Game instance;
	
	
	private Game()
	{
		deck = new Deck();
		dealer = new Dealer();
	}
	
	public static Game getInstance()
	{
		if (instance == null)
			instance = new Game();
		return instance;
	}
	/**	public void addPlayer(String name)
	{
		Player player = new Player(name);
		players.add(player);
		System.out.println(name + " has taken a seat at the table");
	}
	*/
	
	public void startGame() throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Welcome to BlackJack, what is your name?");
		String name = br.readLine();
		player = new Player(name);
		
		System.out.println("How much would you like to deposit?");
		int deposit = Integer.parseInt(br.readLine());
		player.addToBalance(deposit);
		System.out.println("Your current balance is " + player.getBalance());
		
		System.out.println("How much would you like to wager?");
		int bet = Integer.parseInt(br.readLine());
		player.setBet(bet);
		player.bet();
	}
	
	public void playGame() throws IOException
	{
		deal();
		hitOrStand();
	}
	
	public void endGame()
	{
		if (dealer.getScore() > 21)
		{
			System.out.println("TOO MANY!");
			dealer.bust();
		}
		
		if (player.getScore() >= dealer.getScore())
			playerWins = true;
		
		if (playerWins || dealer.bust())
		{
			player.playerWins();
		} else 
			player.playerLoses();
	}
	
	public void deal()
	{
		playerDeal();
		dealerDeal();
		playerDeal();
	}
	
	public void hitOrStand() throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Would you like to hit or stand?");
		String answer = br.readLine().replaceAll("[^a-zA-Z]+", "").toUpperCase();
		if (answer.contains("H"))
			hit();
		stand();
	}
	
	public void playerDeal()
	{
		player.recieveCard(deck.giveCard());
		player.showHand();
		player.showScore();
		System.out.println();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void dealerDeal()
	{
		dealer.recieveCard(deck.giveCard());
		dealer.showFirstCard();
		dealer.showScore();
		System.out.println();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	 /* @throws IOException */
	
	public void hit() throws IOException
	{
		playerDeal();
		
		if (player.bust()) 
		{
			System.out.println("BUST! GAME OVER");
			player.showScore();
			return;
		}
		player.showScore();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Would you like to hit again");
		String answer = br.readLine();
		answer.replaceAll("[^a-zA-Z]+", "").toUpperCase();
		if (answer.contains("Y"))
			hit();
	}
	
	public void stand()
	{
		dealerDeal();
		
		if (dealer.getScore() < 17)
		{
			stand();
		}	
	}
	
	public void replay() throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Would you like to play again?");
		String answer = br.readLine();
		answer.replaceAll("[^a-zA-Z]+", "").toUpperCase();
		if (answer.contains("N"))
			inProgress = false;
		player.hand.clear();
		if (inProgress)
		{
			System.out.println("Would you like to change your bet?");
			answer = br.readLine();
			answer.replaceAll("[^a-zA-Z]+", "").toUpperCase();
			if (answer.contains("Y"))
			{
				System.out.println("How much would you like to bet?");
				player.setBet(Integer.parseInt(answer));
			}
		}
	}
}
