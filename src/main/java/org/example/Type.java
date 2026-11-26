package org.example;

public record Type(String name, String url) {
    @Override
    public String toString() {
        return name;
    }
}