package com.qainfotech.tap.training.snl.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import static org.assertj.core.api.Assertions.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BoardTest {

	@Test
	public void registerPlayerTest() throws FileNotFoundException, UnsupportedEncodingException, IOException,
			PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption {
		Board board = new Board();
		JSONObject boardobject = new JSONObject(
				new String(Files.readAllBytes(Paths.get(board.getUUID().toString() + ".board"))));
		JSONArray jsonarray = boardobject.getJSONArray("players");
		jsonarray = board.registerPlayer("vidushi");
		jsonarray = board.registerPlayer("vids");
		jsonarray = board.registerPlayer("vidu");
		jsonarray = board.registerPlayer("vidz");
		assertThat(boardobject.length()).isNotEqualTo(0);
		assertThat(jsonarray.length()).isNotEqualTo(0);
		assertThat(jsonarray.length()).isEqualTo(4);
		assertThat(jsonarray.get(0).toString().equalsIgnoreCase("vidushi"));

	}

	@Test(expectedExceptions = MaxPlayersReachedExeption.class)
	public void registerPlayerTestForMaxPlayerException() throws FileNotFoundException, UnsupportedEncodingException,
			IOException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption {
		Board board = new Board();
		JSONObject boardobject = new JSONObject(
				new String(Files.readAllBytes(Paths.get(board.getUUID().toString() + ".board"))));
		JSONArray jsonarray = boardobject.getJSONArray("players");
		jsonarray = board.registerPlayer("vidushi");
		jsonarray = board.registerPlayer("vids");
		jsonarray = board.registerPlayer("vidu");
		jsonarray = board.registerPlayer("vidz");
		jsonarray = board.registerPlayer("abc");
	}

	@Test(expectedExceptions = PlayerExistsException.class)
	public void registerPlayerTestForPlayerExistException() throws FileNotFoundException, UnsupportedEncodingException,
			PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException {
		Board board = new Board();
		JSONObject boardobject = new JSONObject(
				new String(Files.readAllBytes(Paths.get(board.getUUID().toString() + ".board"))));
		JSONArray jsonarray = boardobject.getJSONArray("players");
		jsonarray = board.registerPlayer("vidushi");
		jsonarray = board.registerPlayer("vids");
		jsonarray = board.registerPlayer("vidu");
		jsonarray = board.registerPlayer("vids");

	}

	@Test(expectedExceptions = GameInProgressException.class)
	public void registerPlayerTestForGameInProgressException()
			throws FileNotFoundException, UnsupportedEncodingException, IOException, PlayerExistsException,
			GameInProgressException, MaxPlayersReachedExeption, InvalidTurnException {
		Board board = new Board();
		JSONArray jsonArray=board.registerPlayer("vidushi");
		jsonArray=board.registerPlayer("shivangi");
		jsonArray=board.registerPlayer("abc");
		System.out.println(jsonArray);
		/*UUID playeruuid=(UUID) jsonArray.getJSONObject(0).get("uuid");
		JSONObject uuid1=board.rollDice(playeruuid);*/
		System.out.println(board);
		jsonArray=board.registerPlayer("xyz");
		
		

	}

	@Test
	public void deletePlayerTest() throws FileNotFoundException, UnsupportedEncodingException, IOException,
			PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, NoUserWithSuchUUIDException {
		Board board = new Board();
		board.registerPlayer("vidushi");
		board.registerPlayer("shivangi");
		board.registerPlayer("abc");

		JSONObject boardData = board.getData();
		JSONArray boardarray = boardData.getJSONArray("players");
		JSONObject obj = boardarray.getJSONObject(1);
		UUID uid = (UUID) obj.get("uuid");
		JSONArray deletearray = board.deletePlayer(uid);
		assertThat(deletearray.length()).isNotEqualTo(3);
		assertThat(deletearray.get(1).toString().equalsIgnoreCase("abc"));

	}

	@Test(expectedExceptions = NoUserWithSuchUUIDException.class)
	public void NoUserWithSuchUUIDExceptionTest()
			throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException,
			MaxPlayersReachedExeption, IOException, NoUserWithSuchUUIDException {
		Board board = new Board();
		board.registerPlayer("vidushi");
		board.registerPlayer("shivangi");
		board.registerPlayer("abc");
		UUID uuid = UUID.randomUUID();
		board.deletePlayer(uuid);

	}

	@Test
	public void rollDiceTestForStartingTheGame()
			throws FileNotFoundException, UnsupportedEncodingException, IOException, PlayerExistsException,
			GameInProgressException, MaxPlayersReachedExeption, InvalidTurnException {
		Board board = new Board();
		board.registerPlayer("vidushi");
		board.registerPlayer("shivangi");
		board.registerPlayer("abc");

		JSONObject dataobject = board.getData();
		UUID uuid = (UUID) dataobject.getJSONArray("players").getJSONObject(0).get("uuid");
		 board.rollDice(uuid);
		assertThat(dataobject.getJSONArray("players").getJSONObject(0).getInt("position")).isNotEqualTo(0);
		
		
		 
		assertThat(dataobject.getInt("turn")).isNotEqualTo(0);
	}

	@Test(expectedExceptions = InvalidTurnException.class)
	public void rollDiceForInvalidTurnException()
			throws FileNotFoundException, UnsupportedEncodingException, IOException, PlayerExistsException,
			GameInProgressException, MaxPlayersReachedExeption, InvalidTurnException {
		Board board = new Board();
		board.registerPlayer("vidushi");
		board.registerPlayer("shivangi");
		board.registerPlayer("abc");

		board.rollDice(UUID.randomUUID());
	}
	
	@Test
	public void rollDiceTestForNotChangingThePosition() throws FileNotFoundException, UnsupportedEncodingException, IOException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, InvalidTurnException
	{
		Board board = new Board();
		board.registerPlayer("vidushi");
		board.registerPlayer("shivangi");
		board.registerPlayer("abc");
		JSONObject jsonObject=board.getData();
		jsonObject.getJSONArray("players").getJSONObject(0).put("position", 100);
		UUID uuid = (UUID) jsonObject.getJSONArray("players").getJSONObject(0).get("uuid");
		 board.rollDice(uuid);
		assertThat(jsonObject.getJSONArray("players").getJSONObject(0).getInt("position")).isEqualTo(100);
	    
		
	}
	
	@Test
	public void rollDiceForLadder() throws FileNotFoundException, UnsupportedEncodingException, IOException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, JSONException, InvalidTurnException
	{
		Board board=new Board();
		
		board.registerPlayer("vidushi");
		board.registerPlayer("shivangi");
		board.registerPlayer("abc");
		
		JSONObject jsonObject=board.getData();
		
		JSONArray stepsarray=(JSONArray) jsonObject.getJSONArray("steps");
		
		for(int i=1;i<7;i++)
		{
			stepsarray.getJSONObject(i).put("type", "2");
			stepsarray.getJSONObject(i).put("target", i*5);		
			
		}
		
		
		
		int currentposition=(int) board.getData().getJSONArray("players").getJSONObject(board.getData().getInt("turn")).get("position");

		JSONObject response=board.rollDice((UUID) board.getData().getJSONArray("players").getJSONObject(board.getData().getInt("turn")).get("uuid"));
		
		int dicevalue=response.getInt("dice");
		int target=dicevalue*5;
		
		int newposition=(int) board.getData().getJSONArray("players").getJSONObject((board.getData().getInt("turn"))-1).get("position");
		
		assertThat(target).isEqualTo(newposition);
		
		 
	}
	@Test
	public void rollDiceForSnake() throws FileNotFoundException, UnsupportedEncodingException, IOException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, JSONException, InvalidTurnException
	{
		Board board=new Board();
		
		board.registerPlayer("vidushi");
		board.registerPlayer("shivangi");
		board.registerPlayer("abc");
		
		JSONObject jsonObject=board.getData();
		
		
		
		JSONArray steps=jsonObject.getJSONArray("steps");
		
		
		
		for(int i=70;i<76;i++)
		{
			steps.getJSONObject(i).put("type", 1);
			steps.getJSONObject(i).put("target", i-10);
			
				
		}
		JSONArray players=jsonObject.getJSONArray("players");
		for(int i=0;i<players.length();i++)
		{
			players.getJSONObject(i).put("position", 70);
		}
		
		
		
		JSONObject response=board.rollDice((UUID) board.getData().getJSONArray("players").getJSONObject(board.getData().getInt("turn")).get("uuid"));
		
		int dicevalue=response.getInt("dice");
		int target=60+dicevalue;
		
		int newposition=(int) board.getData().getJSONArray("players").getJSONObject((board.getData().getInt("turn"))-1).get("position");
		
		assertThat(target).isEqualTo(newposition);
		
	}
		

}
