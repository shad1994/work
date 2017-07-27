package com.qainfotech.tap.training.snl.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import static org.assertj.core.api.Assertions.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class BoardModelTest {

	@Test
	public void initTest() throws JSONException, IOException {
		UUID boardid = UUID.randomUUID();
		BoardModel boardModel = new BoardModel();
		boardModel.init(boardid);
		JSONObject fileObj = new JSONObject(new String(Files.readAllBytes(Paths.get(boardid.toString() + ".board"))));
		assertThat(fileObj).isNotEqualTo(null);
		JSONArray playerarray = fileObj.getJSONArray("players");
		assertThat(playerarray.length()).isEqualTo(0);
		assertThat(fileObj.get("turn")).isEqualTo(0);
		assertThat(fileObj.getJSONArray("steps").length()).isEqualTo(101);
	}

	@Test
	public void saveTest() throws JSONException, IOException {
		UUID boarduuid = UUID.randomUUID();
		BoardModel boardmodel = new BoardModel();
		boardmodel.init(boarduuid);
		JSONObject fileObj = new JSONObject(new String(Files.readAllBytes(Paths.get(boarduuid.toString() + ".board"))));
		JSONArray playersarray = fileObj.getJSONArray("players");
		playersarray.put("vidushi");
		playersarray.put("vids");
		boardmodel.save(boarduuid, fileObj);
		Board board = new Board();
		board.getData();
		JSONObject dataobject = new JSONObject(
				new String(Files.readAllBytes(Paths.get(boarduuid.toString() + ".board"))));
		JSONArray players = dataobject.getJSONArray("players");
		assertThat(players.length()).isNotEqualTo(0);
		assertThat(players.get(0).equals("vidushi"));

	}

	@Test
	public void dataTest() throws JSONException, IOException {
		Board board = new Board();
		BoardModel boardModel = new BoardModel();
		assertThat(boardModel.data(board.getUUID()).length()).isNotEqualTo(0);

	}

}
