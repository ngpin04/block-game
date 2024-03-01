package persistence;

import model.GameState;
import org.json.JSONObject;


import java.io.*;

// Citation: JsonSerializationDemo project
// Represents a writer that writes JSON representation of workroom to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private final String destination;

    // Citation: JsonSerializationDemo project
    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // Citation: JsonSerializationDemo project
    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(destination);
    }

    // Citation: JsonSerializationDemo project
    // MODIFIES: this
    // EFFECTS: writes JSON representation of workroom to file
    public void write(GameState gs) {
        JSONObject json = gs.toJson();
        saveToFile(json.toString(TAB));
    }

    // Citation: JsonSerializationDemo project
    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // Citation: JsonSerializationDemo project
    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
