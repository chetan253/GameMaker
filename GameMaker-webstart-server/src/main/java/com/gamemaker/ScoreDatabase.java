package com.gamemaker;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class ScoreDatabase {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer userId;

    private String userName;

	private String gameId;

	private Integer score;

	public ScoreDatabase() {
	}

	public ScoreDatabase(Integer userId, String userName, String gameId, Integer score) {
		this.userId = userId;
		this.userName = userName;
		this.gameId = gameId;
		this.score = score;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getUserId() {
		return userId;
	}
}

