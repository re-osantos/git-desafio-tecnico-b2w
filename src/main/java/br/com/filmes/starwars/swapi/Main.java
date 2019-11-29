package br.com.filmes.starwars.swapi;

public class Main {

    public static void main(String[] args) {

        ArgumentSwitcher argumentSwitcher = new ArgumentSwitcher();
        int qtd = argumentSwitcher.getQtdAparicoesEmFilmes("planets", "Alderaan");
        System.out.println("Qtd = "+qtd);
    }
}
