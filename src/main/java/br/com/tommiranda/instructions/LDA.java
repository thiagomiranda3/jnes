package br.com.tommiranda.instructions;

import br.com.tommiranda.Bus;
import br.com.tommiranda.CPU;
import br.com.tommiranda.Opcode;

import java.util.HashMap;
import java.util.Map;

import static br.com.tommiranda.AddressMode.*;
import static br.com.tommiranda.AddressMode.INDIRECT_Y;

public class LDA {

    private static final CPU cpu = Bus.cpu;
    public static final Map<Integer, Opcode> table = new HashMap<>();

    // LDA - Load Accumulator
    static {
        table.put(0xA9, new Opcode(0xA9, "LDA Immediate", IMMEDIATE, 2, () -> {
            cpu.A = IMMEDIATE.fetch();
            cpu.status.Z = cpu.A == 0 ? 1 : 0;
            cpu.status.N = (cpu.A & 0x80) != 0 ? 1 : 0;
            return 2;
        }));

        table.put(0xA5, new Opcode(0xA5, "LDA Zero Page", ZERO_PAGE, 3, () -> {
            cpu.A = ZERO_PAGE.fetch();
            cpu.status.Z = cpu.A == 0 ? 1 : 0;
            cpu.status.N = (cpu.A & 0x80) != 0 ? 1 : 0;
            return 3;
        }));

        table.put(0xB5, new Opcode(0xB5, "LDA Zero Page X", ZERO_PAGE_X, 4, () -> {
            cpu.A = ZERO_PAGE_X.fetch();
            cpu.status.Z = cpu.A == 0 ? 1 : 0;
            cpu.status.N = (cpu.A & 0x80) != 0 ? 1 : 0;
            return 4;
        }));

        table.put(0xAD, new Opcode(0xAD, "LDA Absolute", ABSOLUTE, 4, () -> {
            cpu.A = ABSOLUTE.fetch();
            cpu.status.Z = cpu.A == 0 ? 1 : 0;
            cpu.status.N = (cpu.A & 0x80) != 0 ? 1 : 0;
            return 4;
        }));

        table.put(0xBD, new Opcode(0xBD, "LDA Absolute X", ABSOLUTE_X, 4, () -> {
            cpu.A = ABSOLUTE_X.fetch();
            cpu.status.Z = cpu.A == 0 ? 1 : 0;
            cpu.status.N = (cpu.A & 0x80) != 0 ? 1 : 0;

            if (cpu.pageCrossed) {
                cpu.pageCrossed = false;
                return 5;
            }
            return 4;
        }));

        table.put(0xB9, new Opcode(0xB9, "LDA Absolute Y", ABSOLUTE_Y, 4, () -> {
            cpu.A = ABSOLUTE_Y.fetch();
            cpu.status.Z = cpu.A == 0 ? 1 : 0;
            cpu.status.N = (cpu.A & 0x80) != 0 ? 1 : 0;

            if (cpu.pageCrossed) {
                cpu.pageCrossed = false;
                return 5;
            }
            return 4;
        }));

        table.put(0xA1, new Opcode(0xA1, "LDA Indirect X", INDIRECT_X, 6, () -> {
            cpu.A = INDIRECT_X.fetch();
            cpu.status.Z = cpu.A == 0 ? 1 : 0;
            cpu.status.N = (cpu.A & 0x80) != 0 ? 1 : 0;
            return 6;
        }));

        table.put(0xB1, new Opcode(0xB1, "LDA Indirect Y", INDIRECT_Y, 5, () -> {
            cpu.A = INDIRECT_Y.fetch();
            cpu.status.Z = cpu.A == 0 ? 1 : 0;
            cpu.status.N = (cpu.A & 0x80) != 0 ? 1 : 0;

            if (cpu.pageCrossed) {
                cpu.pageCrossed = false;
                return 6;
            }
            return 5;
        }));
    }
}
