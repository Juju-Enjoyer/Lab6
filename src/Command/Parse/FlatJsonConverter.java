package Command.Parse;

import PossibleClassInCollection.Flat.Flat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Hashtable;

public class FlatJsonConverter implements Serializable {
    private Hashtable<Long, Flat> collection;
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter()).create();
    public FlatJsonConverter(Hashtable<Long, Flat> collection){
        this.collection=collection;
    }

    public String toJson(Flat flat){
        String json=null;
        try {
            json = String.valueOf(gson.toJson(flat));
        }catch (JsonSyntaxException e) {
            return null;
        }
        return json;
    }
    public Flat toFlat(String flatJson) {
        Flat flat = null;
        try {
            flat = gson.fromJson(flatJson, Flat.class);
        } catch (JsonSyntaxException e) {
            return null;
        }
        return flat;
    }
}


