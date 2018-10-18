package client;

import com.google.gson.Gson;
import models.Pokemon;
import models.Tournament;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class main {

    public static void main(String[] args) throws Exception {

        String configName = "estiam_pokemon.json";
        String content = new String(Files.readAllBytes(Paths.get(configName)));
        Pokemon monsters[] = new Gson().fromJson(content, Pokemon[].class);


        Tournament tournament = new Tournament("Estiam", 15, Arrays.asList(monsters));
        tournament.start();
    }
}
