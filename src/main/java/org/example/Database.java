package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.IO.println;

public class Database {
    private Connection connection = null;
    private static Database instance;

    Database()  throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:pokemon");
    }

    public static Database getInstance()  throws SQLException {
        if(instance == null){
            instance = new Database();
        }
        return instance;
    }

    public boolean isPokemonThere(int id){
        String query = "SELECT 1 FROM pokemon WHERE id = ? LIMIT 1";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isPokemonThere(String name){
        String query = "SELECT 1 FROM pokemon WHERE name = ? LIMIT 1";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Pokemon getPokemon(int id){
        String query = "SELECT P.id, P.name, P.type1, P.type2 FROM pokemon P WHERE P.id = ? LIMIT 1";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }

                int pokemonId = rs.getInt("id");
                String name = rs.getString("name");
                String type1 = rs.getString("type1");
                String type2 = rs.getString("type2");

                List<Types> types = new ArrayList<>();
                int slot = 1;
                if (type1 != null && !type1.isBlank()) {
                    types.add(new Types(slot++, new Type(type1, null)));
                }
                if (type2 != null && !type2.isBlank()) {
                    types.add(new Types(slot++, new Type(type2, null)));
                }

                return new Pokemon(pokemonId, name, types);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Pokemon getPokemon(String name){
        String query = "SELECT P.id, P.name, P.type1, P.type2 FROM pokemon P WHERE P.name = ? LIMIT 1";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, name);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }

                int pokemonId = rs.getInt("id");
                String nome = rs.getString("name");
                String type1 = rs.getString("type1");
                String type2 = rs.getString("type2");

                List<Types> types = new ArrayList<>();
                int slot = 1;
                if (type1 != null && !type1.isBlank()) {
                    types.add(new Types(slot++, new Type(type1, null)));
                }
                if (type2 != null && !type2.isBlank()) {
                    types.add(new Types(slot++, new Type(type2, null)));
                }

                return new Pokemon(pokemonId, nome, types);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void insertPokemon(int id, String nome, String tipo1, String tipo2) {
        //test connessione
        if (!testConnection()) {
            throw new RuntimeException("Impossibile connettersi");
        }

        //preparazione query
        String query = """
        INSERT INTO pokemon (id, name, type1, type2)
        SELECT ?, ?, ?, ?
        WHERE NOT EXISTS (
            SELECT 1 FROM pokemon WHERE id = ?
        )
        """;


        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.setString(2, nome);
            ps.setString(3, tipo1);
            ps.setString(4, tipo2); // se è null lo passa come NULL
            ps.setInt(5, id);

            //esecuzione query
            ps.execute();

            println("Inserito il pokemon "+ nome +" nel DB");

        } catch (SQLException e) {
            throw new RuntimeException("Inserimento fallito: " + e.getMessage());
        }
    }


    private boolean testConnection(){
        try{
            return connection.isValid(5);
        } catch (SQLException e) {
            return false;
        }
    }
}
