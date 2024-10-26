package br.com.tommiranda;

import br.com.tommiranda.cpu.CPU;

public class Bus {

    public static byte[] RAM = new byte[0xFFFF];
    public static final CPU cpu = new CPU();

    public static int read(int address) {
        return RAM[address] & 0xFF;
    }

    public static void write(int address, int data) {
        RAM[address] = (byte) (data & 0xFF);
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
