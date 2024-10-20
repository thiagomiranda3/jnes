package br.com.tommiranda;

import br.com.tommiranda.instructions.LDA;
import br.com.tommiranda.instructions.LDX;

import java.util.HashMap;
import java.util.Map;

public final class OpTable {

    private Map<Integer, Opcode> table = new HashMap<>();

    private final CPU cpu = Bus.cpu;

    public OpTable() {
        // ## Load/Store Operations
        table.putAll(LDA.table);
        table.putAll(LDX.table);
    }
}
