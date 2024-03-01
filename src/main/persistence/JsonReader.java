package persistence;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.GameState;
import model.Player;
import org.json.*;

// Citation: JsonSerializationDemo project
// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // Citation: JsonSerializationDemo project
    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // Citation: JsonSerializationDemo project
    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public GameState read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGameState(jsonObject);
    }

    // Citation: JsonSerializationDemo project
    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // Citation: JsonSerializationDemo project
    // EFFECTS: parses workroom from JSON object and returns it
    private GameState parseGameState(JSONObject jsonObject) {
        GameState g = new GameState();
        addPlayers(g, jsonObject);
        addBlocks(g, jsonObject);
        g.setCurrentTurn(jsonObject.getInt("currentTurn"));
        return g;
    }

    // EFFECTS: get the players of JSON object
    private void addPlayers(GameState g, JSONObject jsonObject) {
        JSONObject jsonP1 = jsonObject.getJSONObject("p1");
        JSONObject jsonP2 = jsonObject.getJSONObject("p2");
        Player p1 = new Player(true, jsonP1.getInt("numBlocksLeft"));
        p1.move(jsonP1.getInt("xpos"), jsonP1.getInt("ypos"));
        Player p2 = new Player(false, jsonP2.getInt("numBlocksLeft"));
        p2.move(jsonP2.getInt("xpos"), jsonP2.getInt("ypos"));
        g.setPlayers(p1, p2);
    }

    // EFFECTS: get the blocks list from JSON Object
    private void addBlocks(GameState g, JSONObject jsonObject) {
        JSONArray jsonBlocksList = jsonObject.getJSONArray("blockList");
        for (Object jo : jsonBlocksList) {
            JSONObject x = (JSONObject) jo;
            g.placeBlock(x.getInt("xpos"), x.getInt("ypos"));
        }
    }
}
