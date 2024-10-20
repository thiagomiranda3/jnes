package br.com.tommiranda;

import java.util.HashMap;
import java.util.Map;

public final class OpTable {

    private Map<Integer, Opcode> table = new HashMap<>();

    private final CPU cpu = Bus.cpu;

    public OpTable() {
        // Load/Store Operations
        table.put(0xA9, new Opcode(0xA9, "LDA Immediate", AddressMode.IMMEDIATE, 2, () -> {
            cpu.A = Bus.read(cpu.PC++);

            return 2;
        }));
    }
}
