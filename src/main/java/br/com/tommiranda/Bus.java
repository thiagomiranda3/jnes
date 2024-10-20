package br.com.tommiranda;

public class Bus {

    public static byte[] ROM;
    public static final CPU cpu = new CPU();

    public static int read(int address) {
        return ROM[address] & 0xFF;
    }
}
