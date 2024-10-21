package br.com.tommiranda;

import br.com.tommiranda.instructions.*;

import java.util.HashMap;
import java.util.Map;

public final class OpTable {

    private Map<Integer, Opcode> table = new HashMap<>();

    public OpTable() {
        // ## Load/Store Operations
        table.putAll(LDA.table);
        table.putAll(LDX.table);
        table.putAll(LDY.table);
        table.putAll(STA.table);
        table.putAll(STX.table);
        table.putAll(STY.table);
        table.putAll(Transfers.table);
        table.putAll(StackOps.table);
    }
}
