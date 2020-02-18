package model.data_structures;
import model.logic.*;
import model.data_structures.*;

import com.google.gson.*;

import java.io.*;
import java.net.MalformedURLException;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

public class GsonParser {

    public static void main(String[] args) throws MalformedURLException, IOException {
        Gson gson = new Gson();
        String json = "./data/comparendos_dei_2018_BIG.geojson";
        BufferedReader br = new BufferedReader(new FileReader(json));
        Comparendos comparendos = gson.fromJson(br, Comparendos.class);
        Modelo<Features> mdl = new Modelo(comparendos.darListaFeatures());
        br.close();
    }
}

