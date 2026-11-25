package org.example;

import java.io.IOException;

import static java.lang.IO.println;
import static java.lang.IO.readln;

public class Main {
    static void main() {
        API api = new API();
        String s;

        try {
            do {
                println("""
                        Scegli:
                        1 - se vuoi cercare per nome
                        2 - se vuoi cercare per id
                        x - esci
                        """);
                s = readln("Inserisci un carattere: ");

                switch (s) {
                    case "1":
                        println("Inserisci un nome di un pokemon:");
                        String nome = readln();
                        Pokemon pokemon = api.getPokemonByName(nome);
                        println(pokemon.name());
                        break;
                    case "2":
                        println("Inserisci un id di un pokemon:");
                        String id = readln();
                        int Id = Integer.parseInt(id);
                        Pokemon pokemonById = api.getPokemonById(Id);
                        println(pokemonById.name());
                        break;
                }
            } while (!s.equalsIgnoreCase("x"));
        } catch (IOException | InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
}