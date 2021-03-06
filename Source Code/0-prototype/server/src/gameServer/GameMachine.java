package gameServer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;

public class GameMachine {
	private static GameMachine theMachine;
	
	HashMap <String, Integer> playerNameLevel; // <playerName, playerLevel>
	HashMap <String, Integer> playerNameScore; // <playername, score>
	TreeMap <Integer, String> highScore; // act as database for high score, user TreeMap for sorting
	String ack = "false";
	
	public HashMap<String, Integer> getPlayerNameLevel() {
		return playerNameLevel;
	}

	public void setPlayerNameLevel(HashMap<String, Integer> playerNameLevel) {
		this.playerNameLevel = playerNameLevel;
	}

	public HashMap<String, Integer> getPlayerNameScore() {
		return playerNameScore;
	}

	public void setPlayerNameScore(HashMap<String, Integer> playerNameScore) {
		this.playerNameScore = playerNameScore;
	}
	
	public TreeMap <Integer, String> getHigScore () {
		return highScore;
	}

	public void setHighScore(TreeMap<Integer, String> highScore) {
		this.highScore = highScore;
	}
	
	public String getAck() {
		return ack;
	}

	public void setAck(String ack) {
		this.ack = ack;
	}

	public static GameMachine getInstance() {
		if (theMachine == null) {
			theMachine = new GameMachine() ;
			theMachine.init( ) ;
			return theMachine ;
		}
		else {
			return theMachine ;
		}
	}

	private void init( ) {
		playerNameLevel = new HashMap<> ();
		playerNameScore = new HashMap<> ();
		highScore = new TreeMap<> ();
		
		FileReader in = null;
				
		try {
			in = new FileReader("highScore.txt");
			BufferedReader br = new BufferedReader(in);
			String line = null;
			while ((line = br.readLine()) != null){
				String[] tokens = line.split("|");
				
				String playerName = tokens[0];
				int score = Integer.parseInt(tokens[1]);

				this.highScore.put(score, playerName);
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException");
		} catch (IOException e) {
			System.out.println("IOException");
		} 
	}

	public void registerPlayer(String playerName) {
		if (this.playerNameLevel.get(playerName) != null ) {
			this.ack = "false";
		} else {
			this.playerNameLevel.put(playerName, 0);
		}
	}

	public void setLevel(String playerName, int level) {
		if (this.playerNameLevel.get(playerName) != null ) {
			this.ack = "false";
		} else {
			this.playerNameLevel.put(playerName, level);
		}
		
	}

	public void setScore(String playerName, int score) {
		if (this.playerNameLevel.get(playerName) != null ) {
			this.ack = "false";
		} else {
			this.playerNameScore.put(playerName, score);
			this.highScore.put(score, playerName);
		}
	}

	public int calculateScore(int correctScore, int time) {
		return correctScore + time;
	}

}
