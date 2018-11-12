package com.infrastructure;

public class Game {
	private String playerName;
	private String gameId;
	private int playerScore;
	
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	public int getPlayerScore() {
		return playerScore;
	}
	public void setPlayerScore(int playerScore) {
		this.playerScore = playerScore;
	}
	@Override
	public String toString() {
		return "Game [playerName=" + playerName + ", gameId=" + gameId + ", playerScore=" + playerScore + "]";
	}
	
}
