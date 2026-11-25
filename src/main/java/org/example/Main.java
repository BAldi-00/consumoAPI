package org.example;

import java.io.IOException;

import static java.lang.IO.println;
import static java.lang.IO.readln;

public class Main {
    static void main() {
        API api = new API();

        println("Inserisci un pokemon:");
        String nome = readln();

        try {
            Pokemon pokemon = api.getPokemonByName(nome);
            println(pokemon.name());
        } catch (IOException | InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
}
