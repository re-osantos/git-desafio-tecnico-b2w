package br.com.filmes.starwars.swapi;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Printer {

    public Printer() {
    }

    API api = new API();
    GetRequestRepository repository = new GetRequestRepository(api);


    public int printQtdAparicoesFilmes(JsonArray planetresults) {

        if (planetresults.size() != 0){
            for (int i = 0; i < planetresults.size(); i++) {
                JsonObject planet = planetresults.get(i).getAsJsonObject();
                JsonArray films = planet.getAsJsonArray("films");
                return getQtdFilmes("title", films);
            }
        }    
        return 0;
    }
    
    private int getQtdFilmes(String entity, JsonArray jsonArray) {
    	int qtd = 0;
        if (jsonArray.size() != 0) {
            for (int j = 0; j < jsonArray.size(); j++) {
                qtd++;
            }
        }
        return qtd;
    }
}
