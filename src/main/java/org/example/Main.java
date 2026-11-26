package org.example;

import java.io.IOException;
import java.sql.SQLException;

import static java.lang.IO.println;
import static java.lang.IO.readln;

public class Main {
    static void main() throws SQLException {
        API api = new API();
        String s;
        Database db = new Database();

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

                        Pokemon pokemon = null;

                        if(db.isPokemonThere(nome)){
                            db.getPokemon(nome);
                        }else {
                            pokemon = api.getPokemonByName(nome);
                        }
                        println(pokemon);

                        println();
                        println();

                        break;
                    case "2":
                        println("Inserisci un id di un pokemon:");
                        String id = readln();
                        int Id = Integer.parseInt(id);

                        Pokemon pokemonById = null;

                        if(db.isPokemonThere(Id)){
                            db.getPokemon(id);
                        }else {
                            pokemonById = api.getPokemonById(Id);
                        }
                        println(pokemonById);

                        println();
                        println();

                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + s);
                }
            } while (!s.equalsIgnoreCase("x"));
        } catch (IOException | InterruptedException e) {
            System.err.println(e.getMessage());
        }

        println("Chiusura programma in corso ...");
    }
}