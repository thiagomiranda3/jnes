package br.com.tommiranda.cpu.instructions;

import br.com.tommiranda.Bus;
import br.com.tommiranda.cpu.CPU;
import br.com.tommiranda.cpu.Opcode;

import java.util.HashMap;
import java.util.Map;

import static br.com.tommiranda.cpu.AddressMode.*;

public class LoadStoreOperations {

    private static final CPU cpu = Bus.cpu;

    public static Map<Integer, Opcode> getTable() {
        Map<Integer, Opcode> table = new HashMap<>();

        // LDA - Load Accumulator ====================================================================
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

        // LDX - Load X Register ======================================================================
        table.put(0xA2, new Opcode(0xA2, "LDX Immediate", IMMEDIATE, 2, () -> {
            cpu.X = IMMEDIATE.fetch();
            cpu.status.Z = cpu.X == 0 ? 1 : 0;
            cpu.status.N = (cpu.X & 0x80) != 0 ? 1 : 0;
            return 2;
        }));

        table.put(0xA6, new Opcode(0xA6, "LDX Zero Page", ZERO_PAGE, 3, () -> {
            cpu.X = ZERO_PAGE.fetch();
            cpu.status.Z = cpu.X == 0 ? 1 : 0;
            cpu.status.N = (cpu.X & 0x80) != 0 ? 1 : 0;
            return 3;
        }));

        table.put(0xB6, new Opcode(0xB6, "LDX Zero Page Y", ZERO_PAGE_Y, 4, () -> {
            cpu.X = ZERO_PAGE_Y.fetch();
            cpu.status.Z = cpu.X == 0 ? 1 : 0;
            cpu.status.N = (cpu.X & 0x80) != 0 ? 1 : 0;
            return 4;
        }));

        table.put(0xAE, new Opcode(0xAE, "LDX Absolute", ABSOLUTE, 4, () -> {
            cpu.X = ABSOLUTE.fetch();
            cpu.status.Z = cpu.X == 0 ? 1 : 0;
            cpu.status.N = (cpu.X & 0x80) != 0 ? 1 : 0;
            return 4;
        }));

        table.put(0xBE, new Opcode(0xBE, "LDX Absolute Y", ABSOLUTE_Y, 4, () -> {
            cpu.X = ABSOLUTE_Y.fetch();
            cpu.status.Z = cpu.X == 0 ? 1 : 0;
            cpu.status.N = (cpu.X & 0x80) != 0 ? 1 : 0;

            if (cpu.pageCrossed) {
                return 5;
            }
            return 4;
        }));

        // LDY - Load Y Register ======================================================================
        table.put(0xA0, new Opcode(0xA0, "LDY Immediate", IMMEDIATE, 2, () -> {
            cpu.Y = IMMEDIATE.fetch();
            cpu.status.Z = cpu.Y == 0 ? 1 : 0;
            cpu.status.N = (cpu.Y & 0x80) != 0 ? 1 : 0;
            return 2;
        }));

        table.put(0xA4, new Opcode(0xA4, "LDY Zero Page", ZERO_PAGE, 3, () -> {
            cpu.Y = ZERO_PAGE.fetch();
            cpu.status.Z = cpu.Y == 0 ? 1 : 0;
            cpu.status.N = (cpu.Y & 0x80) != 0 ? 1 : 0;
            return 3;
        }));

        table.put(0xB4, new Opcode(0xB4, "LDY Zero Page X", ZERO_PAGE_X, 4, () -> {
            cpu.Y = ZERO_PAGE_X.fetch();
            cpu.status.Z = cpu.Y == 0 ? 1 : 0;
            cpu.status.N = (cpu.Y & 0x80) != 0 ? 1 : 0;
            return 4;
        }));

        table.put(0xAC, new Opcode(0xAC, "LDY Absolute", ABSOLUTE, 4, () -> {
            cpu.Y = ABSOLUTE.fetch();
            cpu.status.Z = cpu.Y == 0 ? 1 : 0;
            cpu.status.N = (cpu.Y & 0x80) != 0 ? 1 : 0;
            return 4;
        }));

        table.put(0xBC, new Opcode(0xBC, "LDY Absolute Y", ABSOLUTE_X, 4, () -> {
            cpu.Y = ABSOLUTE_X.fetch();
            cpu.status.Z = cpu.Y == 0 ? 1 : 0;
            cpu.status.N = (cpu.Y & 0x80) != 0 ? 1 : 0;

            if (cpu.pageCrossed) {
                return 5;
            }
            return 4;
        }));

        // STA - Store Accumulator ====================================================================
        table.put(0x85, new Opcode(0x85, "STA Zero Page", ZERO_PAGE, 3, () -> {
            int addr = ZERO_PAGE.fetch();
            Bus.write(addr, cpu.A);
            return 3;
        }));

        table.put(0x95, new Opcode(0x95, "STA Zero Page X", ZERO_PAGE_X, 4, () -> {
            int addr = ZERO_PAGE_X.fetch();
            Bus.write(addr, cpu.A);
            return 4;
        }));

        table.put(0x8D, new Opcode(0x8D, "STA Absolute", ABSOLUTE, 4, () -> {
            int addr = ABSOLUTE.fetch();
            Bus.write(addr, cpu.A);
            return 4;
        }));

        table.put(0x9D, new Opcode(0x9D, "STA Absolute X", ABSOLUTE_X, 5, () -> {
            int addr = ABSOLUTE_X.fetch();
            cpu.pageCrossed = false;
            Bus.write(addr, cpu.A);
            return 5;
        }));

        table.put(0x99, new Opcode(0x99, "STA Absolute Y", ABSOLUTE_Y, 5, () -> {
            int addr = ABSOLUTE_Y.fetch();
            cpu.pageCrossed = false;
            Bus.write(addr, cpu.A);
            return 5;
        }));

        table.put(0x81, new Opcode(0x81, "STA Indirect X", INDIRECT_X, 6, () -> {
            int addr = INDIRECT_X.fetch();
            Bus.write(addr, cpu.A);
            return 6;
        }));

        table.put(0x91, new Opcode(0x91, "STA Indirect Y", INDIRECT_Y, 6, () -> {
            int addr = INDIRECT_Y.fetch();
            cpu.pageCrossed = false;
            Bus.write(addr, cpu.A);
            return 6;
        }));

        // STX - Store X Register ======================================================================
        table.put(0x86, new Opcode(0x86, "STX Zero Page", ZERO_PAGE, 3, () -> {
            int addr = ZERO_PAGE.fetch();
            Bus.write(addr, cpu.X);
            return 3;
        }));

        table.put(0x96, new Opcode(0x96, "STX Zero Page Y", ZERO_PAGE_Y, 4, () -> {
            int addr = ZERO_PAGE_Y.fetch();
            Bus.write(addr, cpu.X);
            return 4;
        }));

        table.put(0x8E, new Opcode(0x8E, "STX Absolute", ABSOLUTE, 4, () -> {
            int addr = ABSOLUTE.fetch();
            Bus.write(addr, cpu.X);
            return 4;
        }));

        // STY - Store Y Register ======================================================================
        table.put(0x84, new Opcode(0x84, "STY Zero Page", ZERO_PAGE, 3, () -> {
            int addr = ZERO_PAGE.fetch();
            Bus.write(addr, cpu.Y);
            return 3;
        }));

        table.put(0x94, new Opcode(0x94, "STY Zero Page X", ZERO_PAGE_X, 4, () -> {
            int addr = ZERO_PAGE_X.fetch();
            Bus.write(addr, cpu.Y);
            return 4;
        }));

        table.put(0x8C, new Opcode(0x8C, "STY Absolute", ABSOLUTE, 4, () -> {
            int addr = ABSOLUTE.fetch();
            Bus.write(addr, cpu.Y);
            return 4;
        }));

        return table;
    }
}
