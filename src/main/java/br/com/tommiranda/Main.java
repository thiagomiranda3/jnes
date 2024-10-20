package br.com.tommiranda;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws IOException {
        Bus.ROM = Files.readAllBytes(Path.of("Tetris.nes"));

        System.out.println("NES ROM loaded!");
    }
}