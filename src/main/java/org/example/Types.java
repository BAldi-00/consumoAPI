package org.example;

public record Types(int slot, Type type) {
    @Override
    public String toString() {
        return type != null ? type.toString() : "";
    }
}