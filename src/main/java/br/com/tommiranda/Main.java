package br.com.tommiranda;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws IOException {
        byte[] cartridge = Files.readAllBytes(Path.of("Tetris.nes"));

        for (int i = 0; i < cartridge.length; i++) {
            Bus.Global[i + 0x8000] = cartridge[i];
        }

        System.out.println("NES ROM loaded!");
    }
}