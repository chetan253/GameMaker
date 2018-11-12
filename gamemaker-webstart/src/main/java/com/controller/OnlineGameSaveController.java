package com.controller;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infrastructure.Game;


public class OnlineGameSaveController {
	private OnlineGameSaveController instance = null;
	private Game game = null;
	
	private OnlineGameSaveController() {
		this.game = new Game();
	}
	
	public static class OnlineGameSaveControllerSingleton {
		
		private static final OnlineGameSaveController _INSTANCE = new OnlineGameSaveController();
	}
	
	public static OnlineGameSaveController getInstance() {
		return OnlineGameSaveControllerSingleton._INSTANCE;
	}
	
	public boolean saveGame(int playerScore) throws ClientProtocolException, IOException {
		game.setPlayerScore(playerScore);
		HttpClient client = HttpClientBuilder.create().build();
		ObjectMapper mapper = new ObjectMapper();
		StringEntity entity = new StringEntity(mapper.writeValueAsString(game));
		entity.setContentType("application/json");
		
		HttpPost postRequest = new HttpPost("http://localhost:8082/add?userName="+ game.getPlayerName() +"&gameId="+ game.getGameId() +"&score="+ playerScore);
		postRequest.setEntity(entity);
		
		HttpResponse response = client.execute(postRequest);
		return false;
	}
	
	public void initSession(String gameId, String playerName) {
		game.setGameId(gameId);
		game.setPlayerName(playerName);
	}
	
}
