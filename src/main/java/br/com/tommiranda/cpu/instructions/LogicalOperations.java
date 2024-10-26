package br.com.tommiranda.cpu.instructions;

import br.com.tommiranda.Bus;
import br.com.tommiranda.cpu.CPU;
import br.com.tommiranda.cpu.Opcode;

import java.util.HashMap;
import java.util.Map;

import static br.com.tommiranda.cpu.AddressMode.*;

public class LogicalOperations {

    private static final CPU cpu = Bus.cpu;

    public static Map<Integer, Opcode> getTable() {
        Map<Integer, Opcode> table = new HashMap<>();

        // AND - Logical AND ====================================================================
        table.put(0x29, new Opcode(0x29, "AND Immediate", IMMEDIATE, 2, () -> {
            cpu.A &= IMMEDIATE.fetch();
            cpu.status.Z = cpu.A == 0 ? 1 : 0;
            cpu.status.N = (cpu.A & 0x80) != 0 ? 1 : 0;
            return 2;
        }));

        table.put(0x25, new Opcode(0x25, "AND Zero Page", ZERO_PAGE, 3, () -> {
            cpu.A &= ZERO_PAGE.fetch();
            cpu.status.Z = cpu.A == 0 ? 1 : 0;
            cpu.status.N = (cpu.A & 0x80) != 0 ? 1 : 0;
            return 3;
        }));

        table.put(0x35, new Opcode(0x35, "AND Zero Page X", ZERO_PAGE_X, 4, () -> {
            cpu.A &= ZERO_PAGE_X.fetch();
            cpu.status.Z = cpu.A == 0 ? 1 : 0;
            cpu.status.N = (cpu.A & 0x80) != 0 ? 1 : 0;
            return 4;
        }));

        table.put(0x2D, new Opcode(0x2D, "AND Absolute", ABSOLUTE, 4, () -> {
            cpu.A &= ABSOLUTE.fetch();
            cpu.status.Z = cpu.A == 0 ? 1 : 0;
            cpu.status.N = (cpu.A & 0x80) != 0 ? 1 : 0;
            return 4;
        }));

        table.put(0x3D, new Opcode(0x3D, "AND Absolute X", ABSOLUTE_X, 4, () -> {
            cpu.A &= ABSOLUTE_X.fetch();
            cpu.status.Z = cpu.A == 0 ? 1 : 0;
            cpu.status.N = (cpu.A & 0x80) != 0 ? 1 : 0;

            if (cpu.pageCrossed) {
                cpu.pageCrossed = false;
                return 5;
            }
            return 4;
        }));

        table.put(0x39, new Opcode(0x39, "AND Absolute Y", ABSOLUTE_Y, 4, () -> {
            cpu.A &= ABSOLUTE_Y.fetch();
            cpu.status.Z = cpu.A == 0 ? 1 : 0;
            cpu.status.N = (cpu.A & 0x80) != 0 ? 1 : 0;

            if (cpu.pageCrossed) {
                cpu.pageCrossed = false;
                return 5;
            }
            return 4;
        }));

        table.put(0x21, new Opcode(0x21, "AND Indirect X", INDIRECT_X, 6, () -> {
            cpu.A &= INDIRECT_X.fetch();
            cpu.status.Z = cpu.A == 0 ? 1 : 0;
            cpu.status.N = (cpu.A & 0x80) != 0 ? 1 : 0;
            return 6;
        }));

        table.put(0x31, new Opcode(0x31, "AND Indirect Y", INDIRECT_Y, 5, () -> {
            cpu.A &= INDIRECT_Y.fetch();
            cpu.status.Z = cpu.A == 0 ? 1 : 0;
            cpu.status.N = (cpu.A & 0x80) != 0 ? 1 : 0;

            if (cpu.pageCrossed) {
                cpu.pageCrossed = false;
                return 6;
            }
            return 5;
        }));

        // EOR - Exclusive OR ====================================================================
        table.put(0x49, new Opcode(0x49, "EOR Immediate", IMMEDIATE, 2, () -> {
            cpu.A ^= IMMEDIATE.fetch();
            cpu.status.Z = cpu.A == 0 ? 1 : 0;
            cpu.status.N = (cpu.A & 0x80) != 0 ? 1 : 0;
            return 2;
        }));

        table.put(0x45, new Opcode(0x45, "EOR Zero Page", ZERO_PAGE, 3, () -> {
            cpu.A ^= ZERO_PAGE.fetch();
            cpu.status.Z = cpu.A == 0 ? 1 : 0;
            cpu.status.N = (cpu.A & 0x80) != 0 ? 1 : 0;
            return 3;
        }));

        table.put(0x55, new Opcode(0x55, "EOR Zero Page X", ZERO_PAGE_X, 4, () -> {
            cpu.A ^= ZERO_PAGE_X.fetch();
            cpu.status.Z = cpu.A == 0 ? 1 : 0;
            cpu.status.N = (cpu.A & 0x80) != 0 ? 1 : 0;
            return 4;
        }));

        table.put(0x4D, new Opcode(0x4D, "EOR Absolute", ABSOLUTE, 4, () -> {
            cpu.A ^= ABSOLUTE.fetch();
            cpu.status.Z = cpu.A == 0 ? 1 : 0;
            cpu.status.N = (cpu.A & 0x80) != 0 ? 1 : 0;
            return 4;
        }));

        table.put(0x5D, new Opcode(0x5D, "EOR Absolute X", ABSOLUTE_X, 4, () -> {
            cpu.A ^= ABSOLUTE_X.fetch();
            cpu.status.Z = cpu.A == 0 ? 1 : 0;
            cpu.status.N = (cpu.A & 0x80) != 0 ? 1 : 0;

            if (cpu.pageCrossed) {
                cpu.pageCrossed = false;
                return 5;
            }
            return 4;
        }));

        table.put(0x59, new Opcode(0x59, "EOR Absolute Y", ABSOLUTE_Y, 4, () -> {
            cpu.A ^= ABSOLUTE_Y.fetch();
            cpu.status.Z = cpu.A == 0 ? 1 : 0;
            cpu.status.N = (cpu.A & 0x80) != 0 ? 1 : 0;

            if (cpu.pageCrossed) {
                cpu.pageCrossed = false;
                return 5;
            }
            return 4;
        }));

        table.put(0x41, new Opcode(0x41, "EOR Indirect X", INDIRECT_X, 6, () -> {
            cpu.A ^= INDIRECT_X.fetch();
            cpu.status.Z = cpu.A == 0 ? 1 : 0;
            cpu.status.N = (cpu.A & 0x80) != 0 ? 1 : 0;
            return 6;
        }));

        table.put(0x51, new Opcode(0x51, "EOR Indirect Y", INDIRECT_Y, 5, () -> {
            cpu.A ^= INDIRECT_Y.fetch();
            cpu.status.Z = cpu.A == 0 ? 1 : 0;
            cpu.status.N = (cpu.A & 0x80) != 0 ? 1 : 0;

            if (cpu.pageCrossed) {
                cpu.pageCrossed = false;
                return 6;
            }
            return 5;
        }));

        // ORA - Logical Inclusive OR ============================================================
        table.put(0x09, new Opcode(0x09, "ORA Immediate", IMMEDIATE, 2, () -> {
            cpu.A |= IMMEDIATE.fetch();
            cpu.status.Z = cpu.A == 0 ? 1 : 0;
            cpu.status.N = (cpu.A & 0x80) != 0 ? 1 : 0;
            return 2;
        }));

        table.put(0x05, new Opcode(0x05, "ORA Zero Page", ZERO_PAGE, 3, () -> {
            cpu.A |= ZERO_PAGE.fetch();
            cpu.status.Z = cpu.A == 0 ? 1 : 0;
            cpu.status.N = (cpu.A & 0x80) != 0 ? 1 : 0;
            return 3;
        }));

        table.put(0x15, new Opcode(0x15, "ORA Zero Page X", ZERO_PAGE_X, 4, () -> {
            cpu.A |= ZERO_PAGE_X.fetch();
            cpu.status.Z = cpu.A == 0 ? 1 : 0;
            cpu.status.N = (cpu.A & 0x80) != 0 ? 1 : 0;
            return 4;
        }));

        table.put(0x0D, new Opcode(0x0D, "ORA Absolute", ABSOLUTE, 4, () -> {
            cpu.A |= ABSOLUTE.fetch();
            cpu.status.Z = cpu.A == 0 ? 1 : 0;
            cpu.status.N = (cpu.A & 0x80) != 0 ? 1 : 0;
            return 4;
        }));

        table.put(0x1D, new Opcode(0x1D, "ORA Absolute X", ABSOLUTE_X, 4, () -> {
            cpu.A |= ABSOLUTE_X.fetch();
            cpu.status.Z = cpu.A == 0 ? 1 : 0;
            cpu.status.N = (cpu.A & 0x80) != 0 ? 1 : 0;

            if (cpu.pageCrossed) {
                cpu.pageCrossed = false;
                return 5;
            }
            return 4;
        }));

        table.put(0x19, new Opcode(0x19, "ORA Absolute Y", ABSOLUTE_Y, 4, () -> {
            cpu.A |= ABSOLUTE_Y.fetch();
            cpu.status.Z = cpu.A == 0 ? 1 : 0;
            cpu.status.N = (cpu.A & 0x80) != 0 ? 1 : 0;

            if (cpu.pageCrossed) {
                cpu.pageCrossed = false;
                return 5;
            }
            return 4;
        }));

        table.put(0x01, new Opcode(0x01, "ORA Indirect X", INDIRECT_X, 6, () -> {
            cpu.A |= INDIRECT_X.fetch();
            cpu.status.Z = cpu.A == 0 ? 1 : 0;
            cpu.status.N = (cpu.A & 0x80) != 0 ? 1 : 0;
            return 6;
        }));

        table.put(0x11, new Opcode(0x11, "ORA Indirect Y", INDIRECT_Y, 5, () -> {
            cpu.A |= INDIRECT_Y.fetch();
            cpu.status.Z = cpu.A == 0 ? 1 : 0;
            cpu.status.N = (cpu.A & 0x80) != 0 ? 1 : 0;

            if (cpu.pageCrossed) {
                cpu.pageCrossed = false;
                return 6;
            }
            return 5;
        }));

        // BIT - Bit Test =========================================================================
        table.put(0x24, new Opcode(0x24, "BIT Zero Page", ZERO_PAGE, 3, () -> {
            int value = ZERO_PAGE.fetch();
            cpu.status.Z = (cpu.A & value) == 0 ? 1 : 0;
            cpu.status.N = (value & 0x80) != 0 ? 1 : 0;
            cpu.status.V = (value & 0x40) != 0 ? 1 : 0;
            return 3;
        }));

        table.put(0x2C, new Opcode(0x2C, "BIT Absolute", ABSOLUTE, 4, () -> {
            int value = ABSOLUTE.fetch();
            cpu.status.Z = (cpu.A & value) == 0 ? 1 : 0;
            cpu.status.N = (value & 0x80) != 0 ? 1 : 0;
            cpu.status.V = (value & 0x40) != 0 ? 1 : 0;
            return 4;
        }));

        return table;
    }
}
