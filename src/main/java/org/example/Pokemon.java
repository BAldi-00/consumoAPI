package org.example;

import java.util.List;

import static java.lang.IO.println;

public record Pokemon(int id, String name, List<Types> types) {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (types != null) {
            for (Types t : types) {
                if (t != null && t.type() != null && t.type().name() != null && !t.type().name().isBlank()) {
                    if (!sb.isEmpty()) {
                        sb.append(", ");
                    }
                    sb.append(t.type().name());
                    println(t.type().name());
                }
            }
        }
        return "ID = " + id + ", Nome = " + name + ", TIPI: " + sb;
    }

    public String getTipo1() {
        if (types == null || types.isEmpty() || types.getFirst().type() == null) {
            return null;
        }
        return types.getFirst().type().name();
    }

    public String getTipo2() {
        if (types == null || types.size() < 2 || types.get(1).type() == null) {
            return null;
        }
        return types.get(1).type().name();
    }
}