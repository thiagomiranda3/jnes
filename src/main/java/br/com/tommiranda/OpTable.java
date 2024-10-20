package br.com.tommiranda;

import java.util.HashMap;
import java.util.Map;

import static br.com.tommiranda.AddressMode.*;

public final class OpTable {

    private Map<Integer, Opcode> table = new HashMap<>();

    private final CPU cpu = Bus.cpu;

    public OpTable() {
        // ## Load/Store Operations

        // LDA - Load Accumulator -----------------------------------------------------------------
        table.put(0xA9, new Opcode(0xA9, "LDA Immediate", IMMEDIATE, 2, () -> {
            cpu.A = IMMEDIATE.fetch();
            return 2;
        }));
        table.put(0xA5, new Opcode(0xA5, "LDA Zero Page", ZERO_PAGE, 3, () -> {
            cpu.A = ZERO_PAGE.fetch();
            return 3;
        }));
        table.put(0xB5, new Opcode(0xB5, "LDA Zero Page X", ZERO_PAGE_X, 4, () -> {
            cpu.A = ZERO_PAGE_X.fetch();
            return 4;
        }));
        table.put(0xAD, new Opcode(0xAD, "LDA Absolute", ABSOLUTE, 4, () -> {
            cpu.A = ABSOLUTE.fetch();
            return 4;
        }));
        table.put(0xBD, new Opcode(0xBD, "LDA Absolute X", ABSOLUTE_X, 4, () -> {
            cpu.A = ABSOLUTE_X.fetch();
            if (cpu.pageCrossed) {
                cpu.pageCrossed = false;
                return 5;
            }
            return 4;
        }));
        table.put(0xB9, new Opcode(0xB9, "LDA Absolute Y", ABSOLUTE_Y, 4, () -> {
            cpu.A = ABSOLUTE_Y.fetch();
            if (cpu.pageCrossed) {
                cpu.pageCrossed = false;
                return 5;
            }
            return 4;
        }));
        table.put(0xA1, new Opcode(0xA1, "LDA Indirect X", INDIRECT_X, 6, () -> {
            cpu.A = INDIRECT_X.fetch();
            return 6;
        }));
        table.put(0xB1, new Opcode(0xB1, "LDA Indirect Y", INDIRECT_Y, 5, () -> {
            cpu.A = INDIRECT_Y.fetch();
            if (cpu.pageCrossed) {
                cpu.pageCrossed = false;
                return 6;
            }
            return 5;
        }));
    }
}
