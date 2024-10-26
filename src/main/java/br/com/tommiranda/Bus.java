package br.com.tommiranda;

import br.com.tommiranda.cpu.CPU;

public class Bus {

    public static byte[] RAM = new byte[0x0800]; // from 0x0000 to 0x07FF
    public static byte[] ppuRegisters = new byte[0x0008]; // from 0x2000 to 0x2007
    public static byte[] apuIO = new byte[0x0018]; // from 0x4000 to 0x4017
    public static byte[] apuIODisabled = new byte[0x0008]; // from 0x4018 to 0x401F
    public static byte[] cartridge = new byte[0xBFE0]; // from 0x4020 to 0xFFFF

    public static byte[] Global = new byte[0xFFFF];
    public static final CPU cpu = new CPU();

    public static int read(int address) {
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

    public static void write(int address, int data) {
        byte value = (byte) (data & 0xFF);

        if(address >= 0x0000 && address <= 0x07FF) {
            RAM[address] = value;
        } else if(address >= 0x0800 && address <= 0x1FFF) {
            RAM[address & 0x07FF] = value;
        } else if(address >= 0x2000 && address <= 0x2007) {
            ppuRegisters[address & 0x0007] = value;
        } else if(address >= 0x2008 && address <= 0x3FFF) {
            ppuRegisters[address & 0x0007] = value;
        } else if(address >= 0x4000 && address <= 0x4017) {
            apuIO[address & 0x0017] = value;
        } else if(address >= 0x4018 && address <= 0x401F) {
            apuIODisabled[address & 0x0007] = value;
        } else if(address >= 0x4020 && address <= 0xFFFF) {
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
