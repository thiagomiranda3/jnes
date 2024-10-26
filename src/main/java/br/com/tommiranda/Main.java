package br.com.tommiranda;

import br.com.tommiranda.tests.TestState;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();

        String jsonFile = Files.readString(Path.of("/Users/thiago/git/ProcessorTests/nes6502/v1/0a.json"));

        List<TestState> tests = gson.fromJson(jsonFile, new TypeToken<List<TestState>>() {}.getType());

        for (TestState testState : tests) {
            System.out.println("Running test " + testState.name + "...");
            Bus.reset();

            TestState.State initialState = testState.initialState;
            TestState.State finalState = testState.finalState;

            initialState.ram.forEach(data -> {
                Bus.write(data[0], data[1]);
            });

            Bus.cpu.A = initialState.a;
            Bus.cpu.X = initialState.x;
            Bus.cpu.Y = initialState.y;
            Bus.cpu.PC = initialState.pc;
            Bus.cpu.SP = initialState.s;
            Bus.cpu.status.fromByte(initialState.p);

            Bus.cpuTest(testState.cycles.size());

            finalState.ram.forEach(data -> {
                int actualValue = Bus.read(data[0]);
                int expectedValue = data[1];
                if (actualValue != expectedValue) {
                    System.out.println("RAM mismatch at address " + data[0] + ": expected " + expectedValue + ", got " + actualValue);
                }
            });

            if (Bus.cpu.A != finalState.a) {
                System.out.println("A mismatch: expected " + finalState.a + ", got " + Bus.cpu.A);
            }

            if (Bus.cpu.X != finalState.x) {
                System.out.println("X mismatch: expected " + finalState.x + ", got " + Bus.cpu.X);
            }

            if (Bus.cpu.Y != finalState.y) {
                System.out.println("Y mismatch: expected " + finalState.y + ", got " + Bus.cpu.Y);
            }

            if (Bus.cpu.PC != finalState.pc) {
                System.out.println("PC mismatch: expected " + finalState.pc + ", got " + Bus.cpu.PC);
            }

            if (Bus.cpu.SP != finalState.s) {
                System.out.println("SP mismatch: expected " + finalState.s + ", got " + Bus.cpu.SP);
            }

            if (Bus.cpu.status.toByte() != finalState.p) {
                System.out.println("Status mismatch: expected " + finalState.p + ", got " + Bus.cpu.status.toByte());
            }

            System.out.println("Test " + testState.name + " finished!");
        }
    }

    public static void main2(String[] args) throws IOException {
        byte[] cartridge = Files.readAllBytes(Path.of("Tetris.nes"));

        for (int i = 0; i < cartridge.length; i++) {
            Bus.global[i + 0x8000] = cartridge[i];
        }

        System.out.println("NES ROM loaded!");
    }
}