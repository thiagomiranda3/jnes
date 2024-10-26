package br.com.tommiranda;

import br.com.tommiranda.cpu.CPU;

import java.util.Arrays;

public class Bus {

    public static byte[] RAM = new byte[0x0800]; // from 0x0000 to 0x07FF
    public static byte[] ppuRegisters = new byte[0x0008]; // from 0x2000 to 0x2007
    public static byte[] apuIO = new byte[0x0018]; // from 0x4000 to 0x4017
    public static byte[] apuIODisabled = new byte[0x0008]; // from 0x4018 to 0x401F
    public static byte[] cartridge = new byte[0xBFE0]; // from 0x4020 to 0xFFFF

    public static byte[] global = new byte[0xFFFF];
    public static final CPU cpu = new CPU();

    public static void reset() {
        cpu.PC = (Bus.read(0xFFFD) << 8) | Bus.read(0xFFFC);
        cpu.SP = 0xFD;
        cpu.A = 0;
        cpu.X = 0;
        cpu.Y = 0;
        cpu.status.N = 0;
        cpu.status.V = 0;
        cpu.status.D = 0;
        cpu.status.I = 1;
        cpu.status.Z = 0;
        cpu.status.C = 0;

        Arrays.fill(RAM, (byte) 0);
        Arrays.fill(ppuRegisters, (byte) 0);
        Arrays.fill(apuIO, (byte) 0);
        Arrays.fill(apuIODisabled, (byte) 0);
        Arrays.fill(cartridge, (byte) 0);
        Arrays.fill(global, (byte) 0);
    }

    public static void cpuTest(long maxCycles) {
        while (cpu.executedCycles < maxCycles) {
            cpu.clock();
        }
    }

    public static void start() {
        final long nanoFrame = 16666667; // For NTSC 60Hz
        long previousTime = System.nanoTime(), currentTime = 0, masterCycle = 0, residualTime = 0;

        while (true) {
            currentTime = System.nanoTime();
            long timeElapsed = currentTime - previousTime;
            if (timeElapsed >= nanoFrame) {
                masterCycle++;

                if (masterCycle % 3 == 0) {
                    cpu.clock();
                }

                residualTime = timeElapsed - nanoFrame;
                previousTime = currentTime - residualTime;
            }
        }
    }

    public static int read(int address) {
        return global[address] & 0xFF;
    }

    public static void write(int address, int data) {
        global[address] = (byte) (data & 0xFF);
    }

    public static int readTestDisable(int address) {
        if (address >= 0x0000 && address <= 0x07FF) {
            return RAM[address] & 0xFF;
        } else if (address >= 0x0800 && address <= 0x1FFF) {
            return RAM[address & 0x07FF] & 0xFF;
        } else if (address >= 0x2000 && address <= 0x2007) {
            return ppuRegisters[address & 0x0007] & 0xFF;
        } else if (address >= 0x2008 && address <= 0x3FFF) {
            return ppuRegisters[address & 0x0007] & 0xFF;
        } else if (address >= 0x4000 && address <= 0x4017) {
            return apuIO[address & 0x0017] & 0xFF;
        } else if (address >= 0x4018 && address <= 0x401F) {
            return apuIODisabled[address & 0x0007] & 0xFF;
        } else if (address >= 0x4020 && address <= 0xFFFF) {
            return cartridge[address & 0xBFE0] & 0xFF;
        }

        return 0;
    }

    public static void writeTestDisable(int address, int data) {
        byte value = (byte) (data & 0xFF);

        if (address >= 0x0000 && address <= 0x07FF) {
            RAM[address] = value;
        } else if (address >= 0x0800 && address <= 0x1FFF) {
            RAM[address & 0x07FF] = value;
        } else if (address >= 0x2000 && address <= 0x2007) {
            ppuRegisters[address & 0x0007] = value;
        } else if (address >= 0x2008 && address <= 0x3FFF) {
            ppuRegisters[address & 0x0007] = value;
        } else if (address >= 0x4000 && address <= 0x4017) {
            apuIO[address & 0x0017] = value;
        } else if (address >= 0x4018 && address <= 0x401F) {
            apuIODisabled[address & 0x0007] = value;
        } else if (address >= 0x4020 && address <= 0xFFFF) {
            cartridge[address & 0xBFE0] = value;
        }
    }

    public static void pushSP(int data) {
        write(0x100 + cpu.SP, data);
        cpu.SP = (cpu.SP - 1) & 0xFF;
    }

    public static int pullSP() {
        cpu.SP = (cpu.SP + 1) & 0xFF;
        return read(0x100 + cpu.SP);
    }
}
