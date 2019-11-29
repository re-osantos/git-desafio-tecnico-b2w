package br.com.filmes.starwars.swapi;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ArgumentSwitcher {

    private static final API apiCalls = new API();
    private GetRequestRepository repository = new GetRequestRepository(apiCalls);
    Printer printer = new Printer();

    public int getQtdAparicoesEmFilmes(String command, String searchquery) {
        JsonObject jsonObject = repository.getAll("planets", searchquery);
        JsonArray planetresults = jsonObject.getAsJsonArray("results");
        return printer.printQtdAparicoesFilmes(planetresults);
    }
}
