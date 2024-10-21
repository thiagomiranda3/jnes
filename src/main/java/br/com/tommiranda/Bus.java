package br.com.tommiranda;

public class Bus {

    public static byte[] RAM = new byte[0xFFFF];
    public static final CPU cpu = new CPU();

    public static int read(int address) {
        return RAM[address] & 0xFF;
    }

    public static void write(int address, int data) {
        RAM[address] = (byte) (data & 0xFF);
    }
}
