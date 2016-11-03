import java.util.HashMap;
import java.util.Random;
import java.util.HashSet;

/** The server of RandomCoin. Gives users coins, and stores 
 *  a log of the number of coins they have.
 *  @author Martin Guo 
 */
public class RandomCoin {

	/** The current number of coins a user has. */
	private int _numbercoins;

	/** The random number that the user has to guess. */
	private int _number;

	/** Stores the user base. */
	private static HashMap<String, Integer> _log;

	/** Random used to generate random numbers. */
	private Random _random;

	/** Logs current guesses made by user. */
	private HashSet<Integer> _guesses = new HashSet<>();

	/** Constructs the state of the RandomCoin server. */
	public RandomCoin() {
		_random = new Random();
		_number = _random.nextInt(50);
		_log = new HashMap<>();
		System.out.println("Please use HandleGuess to start guessing.");
		System.out.println("If you want your total number of coins, use GetCoins.");
		System.out.println("If you want to see your attempted guesses, check guesses method.");
	}

	/** Determines whether or not a guess is correct. If so, user
	 * gets a coin. */
	public boolean HandleGuess(String userID, int guess) {
		int temp = _number;
		_guesses.add(guess);
		if (_log.containsKey(userID)) {
			_numbercoins = GetCoins(userID);
		}
		if (guess == _number) {
			_number = _random.nextInt(50);
			_numbercoins += 1;
			_guesses.clear();
		}
		_log.put(userID, _numbercoins);
		_numbercoins = 0;
		return guess == temp;
	}

	/** Returns the number of coins a user has. */
	public int GetCoins(String userID) {
		return _log.get(userID);
	}

	/** Simulates a user. The simulation stops once
	 * the target has been reached. */
	public void StartGuessing(String userID) {
		_log.put(userID, _numbercoins);
		HashSet<Integer> guessed = new HashSet<>();
		int target = _random.nextInt(25) * _random.nextInt(5) + 1;
		while (GetCoins(userID) <= target) {
			int guess = _random.nextInt(50);
			while (guessed.contains(guess)) {
				guess = _random.nextInt(50);
			}
			guessed.add(guess);
			boolean works = HandleGuess(userID, guess);
			if (works) {
				guessed.clear();
			}
		}
	}

	/** Returns the total number of coins in the server. */
	int totalValue() {
		int result = 0;
		java.util.Collection<Integer> values = _log.values();
		for (int value : values) {
			result += value;
		}
		return result;
	}

	/** Returns a representation of the current number of guesses. */
	private String guesses() {
		return _guesses.toString();
	}

}