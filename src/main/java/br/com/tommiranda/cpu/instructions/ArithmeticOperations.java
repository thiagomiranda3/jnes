package br.com.tommiranda.cpu.instructions;

import br.com.tommiranda.Bus;
import br.com.tommiranda.cpu.CPU;
import br.com.tommiranda.cpu.Opcode;

import java.util.HashMap;
import java.util.Map;

import static br.com.tommiranda.cpu.AddressMode.*;

public class ArithmeticOperations {

    private static final CPU cpu = Bus.cpu;

    public static Map<Integer, Opcode> getTable() {
        Map<Integer, Opcode> table = new HashMap<>();

        // ADC - Add with Carry ====================================================================
        table.put(0x69, new Opcode(0x69, "ADC Immediate", IMMEDIATE, 2, () -> {
            int value = IMMEDIATE.fetch();
            int result = cpu.A + value + cpu.status.C;
            cpu.status.C = result > 0xFF ? 1 : 0;
            cpu.status.Z = (result & 0xFF) == 0 ? 1 : 0;
            cpu.status.N = (result & 0x80) != 0 ? 1 : 0;

            // https://stackoverflow.com/questions/16845912/determining-carry-and-overflow-flag-in-6502-emulation-in-java
            cpu.status.V = ((~(cpu.A ^ value)) & (cpu.A ^ result) & 0x80) != 0 ? 1 : 0;

            cpu.A = result & 0xFF;
            return 2;
        }));

        table.put(0x65, new Opcode(0x65, "ADC Zero Page", ZERO_PAGE, 3, () -> {
            int value = ZERO_PAGE.fetch();
            int result = cpu.A + value + cpu.status.C;
            cpu.status.C = result > 0xFF ? 1 : 0;
            cpu.status.Z = (result & 0xFF) == 0 ? 1 : 0;
            cpu.status.N = (result & 0x80) != 0 ? 1 : 0;
            cpu.status.V = ((~(cpu.A ^ value)) & (cpu.A ^ result) & 0x80) != 0 ? 1 : 0;
            cpu.A = result & 0xFF;
            return 3;
        }));

        table.put(0x75, new Opcode(0x75, "ADC Zero Page X", ZERO_PAGE_X, 4, () -> {
            int value = ZERO_PAGE_X.fetch();
            int result = cpu.A + value + cpu.status.C;
            cpu.status.C = result > 0xFF ? 1 : 0;
            cpu.status.Z = (result & 0xFF) == 0 ? 1 : 0;
            cpu.status.N = (result & 0x80) != 0 ? 1 : 0;
            cpu.status.V = ((~(cpu.A ^ value)) & (cpu.A ^ result) & 0x80) != 0 ? 1 : 0;
            cpu.A = result & 0xFF;
            return 4;
        }));

        table.put(0x6D, new Opcode(0x6D, "ADC Absolute", ABSOLUTE, 4, () -> {
            int value = ABSOLUTE.fetch();
            int result = cpu.A + value + cpu.status.C;
            cpu.status.C = result > 0xFF ? 1 : 0;
            cpu.status.Z = (result & 0xFF) == 0 ? 1 : 0;
            cpu.status.N = (result & 0x80) != 0 ? 1 : 0;
            cpu.status.V = ((~(cpu.A ^ value)) & (cpu.A ^ result) & 0x80) != 0 ? 1 : 0;
            cpu.A = result & 0xFF;
            return 4;
        }));

        table.put(0x7D, new Opcode(0x7D, "ADC Absolute X", ABSOLUTE_X, 4, () -> {
            int value = ABSOLUTE_X.fetch();
            int result = cpu.A + value + cpu.status.C;
            cpu.status.C = result > 0xFF ? 1 : 0;
            cpu.status.Z = (result & 0xFF) == 0 ? 1 : 0;
            cpu.status.N = (result & 0x80) != 0 ? 1 : 0;
            cpu.status.V = ((~(cpu.A ^ value)) & (cpu.A ^ result) & 0x80) != 0 ? 1 : 0;
            cpu.A = result & 0xFF;

            if (cpu.pageCrossed) {
                cpu.pageCrossed = false;
                return 5;
            }
            return 4;
        }));

        table.put(0x79, new Opcode(0x79, "ADC Absolute Y", ABSOLUTE_Y, 4, () -> {
            int value = ABSOLUTE_Y.fetch();
            int result = cpu.A + value + cpu.status.C;
            cpu.status.C = result > 0xFF ? 1 : 0;
            cpu.status.Z = (result & 0xFF) == 0 ? 1 : 0;
            cpu.status.N = (result & 0x80) != 0 ? 1 : 0;
            cpu.status.V = ((~(cpu.A ^ value)) & (cpu.A ^ result) & 0x80) != 0 ? 1 : 0;
            cpu.A = result & 0xFF;

            if (cpu.pageCrossed) {
                cpu.pageCrossed = false;
                return 5;
            }
            return 4;
        }));

        table.put(0x61, new Opcode(0x61, "ADC Indirect X", INDIRECT_X, 6, () -> {
            int value = INDIRECT_X.fetch();
            int result = cpu.A + value + cpu.status.C;
            cpu.status.C = result > 0xFF ? 1 : 0;
            cpu.status.Z = (result & 0xFF) == 0 ? 1 : 0;
            cpu.status.N = (result & 0x80) != 0 ? 1 : 0;
            cpu.status.V = ((~(cpu.A ^ value)) & (cpu.A ^ result) & 0x80) != 0 ? 1 : 0;
            cpu.A = result & 0xFF;
            return 6;
        }));

        table.put(0x71, new Opcode(0x71, "ADC Indirect Y", INDIRECT_Y, 5, () -> {
            int value = INDIRECT_Y.fetch();
            int result = cpu.A + value + cpu.status.C;
            cpu.status.C = result > 0xFF ? 1 : 0;
            cpu.status.Z = (result & 0xFF) == 0 ? 1 : 0;
            cpu.status.N = (result & 0x80) != 0 ? 1 : 0;
            cpu.status.V = ((~(cpu.A ^ value)) & (cpu.A ^ result) & 0x80) != 0 ? 1 : 0;
            cpu.A = result & 0xFF;

            if (cpu.pageCrossed) {
                cpu.pageCrossed = false;
                return 6;
            }
            return 5;
        }));

        // SBC - Subtract with Carry ===============================================================
        table.put(0xE9, new Opcode(0xE9, "SBC Immediate", IMMEDIATE, 2, () -> {
            int value = IMMEDIATE.fetch();
            int result = cpu.A - value - (1 - cpu.status.C);
            cpu.status.C = result >= 0 ? 1 : 0;
            cpu.status.Z = (result & 0xFF) == 0 ? 1 : 0;
            cpu.status.N = (result & 0x80) != 0 ? 1 : 0;
            cpu.status.V = (((cpu.A ^ value) & (cpu.A ^ result)) & 0x80) != 0 ? 1 : 0;
            cpu.A = result & 0xFF;
            return 2;
        }));

        table.put(0xE5, new Opcode(0xE5, "SBC Zero Page", ZERO_PAGE, 3, () -> {
            int value = ZERO_PAGE.fetch();
            int result = cpu.A - value - (1 - cpu.status.C);
            cpu.status.C = result >= 0 ? 1 : 0;
            cpu.status.Z = (result & 0xFF) == 0 ? 1 : 0;
            cpu.status.N = (result & 0x80) != 0 ? 1 : 0;
            cpu.status.V = (((cpu.A ^ value) & (cpu.A ^ result)) & 0x80) != 0 ? 1 : 0;
            cpu.A = result & 0xFF;
            return 3;
        }));

        table.put(0xF5, new Opcode(0xF5, "SBC Zero Page X", ZERO_PAGE_X, 4, () -> {
            int value = ZERO_PAGE_X.fetch();
            int result = cpu.A - value - (1 - cpu.status.C);
            cpu.status.C = result >= 0 ? 1 : 0;
            cpu.status.Z = (result & 0xFF) == 0 ? 1 : 0;
            cpu.status.N = (result & 0x80) != 0 ? 1 : 0;
            cpu.status.V = (((cpu.A ^ value) & (cpu.A ^ result)) & 0x80) != 0 ? 1 : 0;
            cpu.A = result & 0xFF;
            return 4;
        }));

        table.put(0xED, new Opcode(0xED, "SBC Absolute", ABSOLUTE, 4, () -> {
            int value = ABSOLUTE.fetch();
            int result = cpu.A - value - (1 - cpu.status.C);
            cpu.status.C = result >= 0 ? 1 : 0;
            cpu.status.Z = (result & 0xFF) == 0 ? 1 : 0;
            cpu.status.N = (result & 0x80) != 0 ? 1 : 0;
            cpu.status.V = (((cpu.A ^ value) & (cpu.A ^ result)) & 0x80) != 0 ? 1 : 0;
            cpu.A = result & 0xFF;
            return 4;
        }));

        table.put(0xFD, new Opcode(0xFD, "SBC Absolute X", ABSOLUTE_X, 4, () -> {
            int value = ABSOLUTE_X.fetch();
            int result = cpu.A - value - (1 - cpu.status.C);
            cpu.status.C = result >= 0 ? 1 : 0;
            cpu.status.Z = (result & 0xFF) == 0 ? 1 : 0;
            cpu.status.N = (result & 0x80) != 0 ? 1 : 0;
            cpu.status.V = (((cpu.A ^ value) & (cpu.A ^ result)) & 0x80) != 0 ? 1 : 0;
            cpu.A = result & 0xFF;

            if (cpu.pageCrossed) {
                cpu.pageCrossed = false;
                return 5;
            }
            return 4;
        }));

        table.put(0xF9, new Opcode(0xF9, "SBC Absolute Y", ABSOLUTE_Y, 4, () -> {
            int value = ABSOLUTE_Y.fetch();
            int result = cpu.A - value - (1 - cpu.status.C);
            cpu.status.C = result >= 0 ? 1 : 0;
            cpu.status.Z = (result & 0xFF) == 0 ? 1 : 0;
            cpu.status.N = (result & 0x80) != 0 ? 1 : 0;
            cpu.status.V = (((cpu.A ^ value) & (cpu.A ^ result)) & 0x80) != 0 ? 1 : 0;
            cpu.A = result & 0xFF;

            if (cpu.pageCrossed) {
                cpu.pageCrossed = false;
                return 5;
            }
            return 4;
        }));

        table.put(0xE1, new Opcode(0xE1, "SBC Indirect X", INDIRECT_X, 6, () -> {
            int value = INDIRECT_X.fetch();
            int result = cpu.A - value - (1 - cpu.status.C);
            cpu.status.C = result >= 0 ? 1 : 0;
            cpu.status.Z = (result & 0xFF) == 0 ? 1 : 0;
            cpu.status.N = (result & 0x80) != 0 ? 1 : 0;
            cpu.status.V = (((cpu.A ^ value) & (cpu.A ^ result)) & 0x80) != 0 ? 1 : 0;
            cpu.A = result & 0xFF;
            return 6;
        }));

        table.put(0xF1, new Opcode(0xF1, "SBC Indirect Y", INDIRECT_Y, 5, () -> {
            int value = INDIRECT_Y.fetch();
            int result = cpu.A - value - (1 - cpu.status.C);
            cpu.status.C = result >= 0 ? 1 : 0;
            cpu.status.Z = (result & 0xFF) == 0 ? 1 : 0;
            cpu.status.N = (result & 0x80) != 0 ? 1 : 0;
            cpu.status.V = (((cpu.A ^ value) & (cpu.A ^ result)) & 0x80) != 0 ? 1 : 0;
            cpu.A = result & 0xFF;

            if (cpu.pageCrossed) {
                cpu.pageCrossed = false;
                return 6;
            }
            return 5;
        }));

        // CMP - Compare ==============================================================================
        table.put(0xC9, new Opcode(0xC9, "CMP Immediate", IMMEDIATE, 2, () -> {
            int value = IMMEDIATE.fetch();
            int result = cpu.A - value;
            cpu.status.C = result >= 0 ? 1 : 0;
            cpu.status.Z = (result & 0xFF) == 0 ? 1 : 0;
            cpu.status.N = (result & 0x80) != 0 ? 1 : 0;
            return 2;
        }));

        table.put(0xC5, new Opcode(0xC5, "CMP Zero Page", ZERO_PAGE, 3, () -> {
            int value = ZERO_PAGE.fetch();
            int result = cpu.A - value;
            cpu.status.C = result >= 0 ? 1 : 0;
            cpu.status.Z = (result & 0xFF) == 0 ? 1 : 0;
            cpu.status.N = (result & 0x80) != 0 ? 1 : 0;
            return 3;
        }));

        table.put(0xD5, new Opcode(0xD5, "CMP Zero Page X", ZERO_PAGE_X, 4, () -> {
            int value = ZERO_PAGE_X.fetch();
            int result = cpu.A - value;
            cpu.status.C = result >= 0 ? 1 : 0;
            cpu.status.Z = (result & 0xFF) == 0 ? 1 : 0;
            cpu.status.N = (result & 0x80) != 0 ? 1 : 0;
            return 4;
        }));

        table.put(0xCD, new Opcode(0xCD, "CMP Absolute", ABSOLUTE, 4, () -> {
            int value = ABSOLUTE.fetch();
            int result = cpu.A - value;
            cpu.status.C = result >= 0 ? 1 : 0;
            cpu.status.Z = (result & 0xFF) == 0 ? 1 : 0;
            cpu.status.N = (result & 0x80) != 0 ? 1 : 0;
            return 4;
        }));

        table.put(0xDD, new Opcode(0xDD, "CMP Absolute X", ABSOLUTE_X, 4, () -> {
            int value = ABSOLUTE_X.fetch();
            int result = cpu.A - value;
            cpu.status.C = result >= 0 ? 1 : 0;
            cpu.status.Z = (result & 0xFF) == 0 ? 1 : 0;
            cpu.status.N = (result & 0x80) != 0 ? 1 : 0;

            if (cpu.pageCrossed) {
                cpu.pageCrossed = false;
                return 5;
            }
            return 4;
        }));

        table.put(0xD9, new Opcode(0xD9, "CMP Absolute Y", ABSOLUTE_Y, 4, () -> {
            int value = ABSOLUTE_Y.fetch();
            int result = cpu.A - value;
            cpu.status.C = result >= 0 ? 1 : 0;
            cpu.status.Z = (result & 0xFF) == 0 ? 1 : 0;
            cpu.status.N = (result & 0x80) != 0 ? 1 : 0;

            if (cpu.pageCrossed) {
                cpu.pageCrossed = false;
                return 5;
            }
            return 4;
        }));

        table.put(0xC1, new Opcode(0xC1, "CMP Indirect X", INDIRECT_X, 6, () -> {
            int value = INDIRECT_X.fetch();
            int result = cpu.A - value;
            cpu.status.C = result >= 0 ? 1 : 0;
            cpu.status.Z = (result & 0xFF) == 0 ? 1 : 0;
            cpu.status.N = (result & 0x80) != 0 ? 1 : 0;
            return 6;
        }));

        table.put(0xD1, new Opcode(0xD1, "CMP Indirect Y", INDIRECT_Y, 5, () -> {
            int value = INDIRECT_Y.fetch();
            int result = cpu.A - value;
            cpu.status.C = result >= 0 ? 1 : 0;
            cpu.status.Z = (result & 0xFF) == 0 ? 1 : 0;
            cpu.status.N = (result & 0x80) != 0 ? 1 : 0;

            if (cpu.pageCrossed) {
                cpu.pageCrossed = false;
                return 6;
            }
            return 5;
        }));

        // CPX - Compare X ===========================================================================
        table.put(0xE0, new Opcode(0xE0, "CPX Immediate", IMMEDIATE, 2, () -> {
            int value = IMMEDIATE.fetch();
            int result = cpu.X - value;
            cpu.status.C = result >= 0 ? 1 : 0;
            cpu.status.Z = (result & 0xFF) == 0 ? 1 : 0;
            cpu.status.N = (result & 0x80) != 0 ? 1 : 0;
            return 2;
        }));

        table.put(0xE4, new Opcode(0xE4, "CPX Zero Page", ZERO_PAGE, 3, () -> {
            int value = ZERO_PAGE.fetch();
            int result = cpu.X - value;
            cpu.status.C = result >= 0 ? 1 : 0;
            cpu.status.Z = (result & 0xFF) == 0 ? 1 : 0;
            cpu.status.N = (result & 0x80) != 0 ? 1 : 0;
            return 3;
        }));

        table.put(0xEC, new Opcode(0xEC, "CPX Absolute", ABSOLUTE, 4, () -> {
            int value = ABSOLUTE.fetch();
            int result = cpu.X - value;
            cpu.status.C = result >= 0 ? 1 : 0;
            cpu.status.Z = (result & 0xFF) == 0 ? 1 : 0;
            cpu.status.N = (result & 0x80) != 0 ? 1 : 0;
            return 4;
        }));

        // CPY - Compare Y ===========================================================================
        table.put(0xC0, new Opcode(0xC0, "CPY Immediate", IMMEDIATE, 2, () -> {
            int value = IMMEDIATE.fetch();
            int result = cpu.Y - value;
            cpu.status.C = result >= 0 ? 1 : 0;
            cpu.status.Z = (result & 0xFF) == 0 ? 1 : 0;
            cpu.status.N = (result & 0x80) != 0 ? 1 : 0;
            return 2;
        }));

        table.put(0xC4, new Opcode(0xC4, "CPY Zero Page", ZERO_PAGE, 3, () -> {
            int value = ZERO_PAGE.fetch();
            int result = cpu.Y - value;
            cpu.status.C = result >= 0 ? 1 : 0;
            cpu.status.Z = (result & 0xFF) == 0 ? 1 : 0;
            cpu.status.N = (result & 0x80) != 0 ? 1 : 0;
            return 3;
        }));

        table.put(0xCC, new Opcode(0xCC, "CPY Absolute", ABSOLUTE, 4, () -> {
            int value = ABSOLUTE.fetch();
            int result = cpu.Y - value;
            cpu.status.C = result >= 0 ? 1 : 0;
            cpu.status.Z = (result & 0xFF) == 0 ? 1 : 0;
            cpu.status.N = (result & 0x80) != 0 ? 1 : 0;
            return 4;
        }));

        return table;
    }
}
