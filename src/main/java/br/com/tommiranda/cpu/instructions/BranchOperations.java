package br.com.tommiranda.cpu.instructions;

import br.com.tommiranda.Bus;
import br.com.tommiranda.cpu.CPU;
import br.com.tommiranda.cpu.Opcode;

import java.util.HashMap;
import java.util.Map;

import static br.com.tommiranda.cpu.AddressMode.RELATIVE;

public class BranchOperations {

    private static final CPU cpu = Bus.cpu;

    public static Map<Integer, Opcode> getTable() {
        Map<Integer, Opcode> table = new HashMap<>();

        // BCC - Branch if Carry Clear ====================================================================
        table.put(0x90, new Opcode(0x90, "BCC", RELATIVE, 2, () -> {
            int offset = RELATIVE.fetchAddress();
            if (cpu.status.C == 0) {
                int oldPC = cpu.PC;
                cpu.PC += offset;

                if ((cpu.PC & 0xFF00) != (oldPC & 0xFF00)) {
                    return 4;
                }

                return 3;
            }

            return 2;
        }));

        // BCS - Branch if Carry Set ====================================================================
        table.put(0xB0, new Opcode(0xB0, "BCS", RELATIVE, 2, () -> {
            int offset = RELATIVE.fetchAddress();
            if (cpu.status.C == 1) {
                int oldPC = cpu.PC;
                cpu.PC += offset;

                if ((cpu.PC & 0xFF00) != (oldPC & 0xFF00)) {
                    return 4;
                }

                return 3;
            }

            return 2;
        }));

        // BEQ - Branch if Equal ====================================================================
        table.put(0xF0, new Opcode(0xF0, "BEQ", RELATIVE, 2, () -> {
            int offset = RELATIVE.fetchAddress();
            if (cpu.status.Z == 1) {
                int oldPC = cpu.PC;
                cpu.PC += offset;

                if ((cpu.PC & 0xFF00) != (oldPC & 0xFF00)) {
                    return 4;
                }

                return 3;
            }

            return 2;
        }));

        // BMI - Branch if Minus ====================================================================
        table.put(0x30, new Opcode(0x30, "BMI", RELATIVE, 2, () -> {
            int offset = RELATIVE.fetchAddress();
            if (cpu.status.N == 1) {
                int oldPC = cpu.PC;
                cpu.PC += offset;

                if ((cpu.PC & 0xFF00) != (oldPC & 0xFF00)) {
                    return 4;
                }

                return 3;
            }

            return 2;
        }));

        // BNE - Branch if Not Equal ====================================================================
        table.put(0xD0, new Opcode(0xD0, "BNE", RELATIVE, 2, () -> {
            int offset = RELATIVE.fetchAddress();
            if (cpu.status.Z == 0) {
                int oldPC = cpu.PC;
                cpu.PC += offset;

                if ((cpu.PC & 0xFF00) != (oldPC & 0xFF00)) {
                    return 4;
                }

                return 3;
            }

            return 2;
        }));

        // BPL - Branch if Positive ====================================================================
        table.put(0x10, new Opcode(0x10, "BPL", RELATIVE, 2, () -> {
            int offset = RELATIVE.fetchAddress();
            if (cpu.status.N == 0) {
                int oldPC = cpu.PC;
                cpu.PC += offset;

                if ((cpu.PC & 0xFF00) != (oldPC & 0xFF00)) {
                    return 4;
                }

                return 3;
            }

            return 2;
        }));

        // BVC - Branch if Overflow Clear ====================================================================
        table.put(0x50, new Opcode(0x50, "BVC", RELATIVE, 2, () -> {
            int offset = RELATIVE.fetchAddress();
            if (cpu.status.V == 0) {
                int oldPC = cpu.PC;
                cpu.PC += offset;

                if ((cpu.PC & 0xFF00) != (oldPC & 0xFF00)) {
                    return 4;
                }

                return 3;
            }

            return 2;
        }));

        // BVS - Branch if Overflow Set ====================================================================
        table.put(0x70, new Opcode(0x70, "BVS", RELATIVE, 2, () -> {
            int offset = RELATIVE.fetchAddress();
            if (cpu.status.V == 1) {
                int oldPC = cpu.PC;
                cpu.PC += offset;

                if ((cpu.PC & 0xFF00) != (oldPC & 0xFF00)) {
                    return 4;
                }

                return 3;
            }

            return 2;
        }));

        return table;
    }
}
