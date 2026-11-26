package org.example;

import java.util.List;

public record Pokemon(int id, String name, List<Types> types) {

    @Override
    public String toString(){
        String stringTypes = "";

        if (types != null) {
            for (int i = 0; i < types.size(); i++) {
                Types t = types.get(i);
                if (t != null && t.type() != null && t.type().name() != null) {
                    if (!stringTypes.isEmpty()) {
                        stringTypes += ", ";
                    }
                    stringTypes += t.type().name();
                }
            }
        }

        return "ID = " + this.id + ", " + "Nome = " + this.name + ", TIPI: " + stringTypes;
    }

    public String getTipo1(){
        return types.getFirst().toString();
    }

    public String getTipo2(){
        return types.get(1).toString();
    }
}

