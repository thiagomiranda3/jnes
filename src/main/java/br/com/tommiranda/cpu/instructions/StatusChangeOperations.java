package br.com.tommiranda.cpu.instructions;

import br.com.tommiranda.Bus;
import br.com.tommiranda.cpu.CPU;
import br.com.tommiranda.cpu.Opcode;

import java.util.HashMap;
import java.util.Map;

import static br.com.tommiranda.cpu.AddressMode.*;

public class StatusChangeOperations {

    private static final CPU cpu = Bus.cpu;

    public static Map<Integer, Opcode> getTable() {
        Map<Integer, Opcode> table = new HashMap<>();

        // CLC - Clear Carry Flag ====================================================================
        table.put(0x18, new Opcode(0x18, "CLC", IMPLIED, 2, () -> {
            cpu.status.C = 0;
            return 2;
        }));

        // CLD - Clear Decimal Mode ====================================================================
        table.put(0xD8, new Opcode(0xD8, "CLD", IMPLIED, 2, () -> {
            cpu.status.D = 0;
            return 2;
        }));

        // CLI - Clear Interrupt Disable ====================================================================
        table.put(0x58, new Opcode(0x58, "CLI", IMPLIED, 2, () -> {
            cpu.status.I = 0;
            return 2;
        }));

        // CLV - Clear Overflow Flag ====================================================================
        table.put(0xB8, new Opcode(0xB8, "CLV", IMPLIED, 2, () -> {
            cpu.status.V = 0;
            return 2;
        }));

        // SEC - Set Carry Flag ====================================================================
        table.put(0x38, new Opcode(0x38, "SEC", IMPLIED, 2, () -> {
            cpu.status.C = 1;
            return 2;
        }));

        // SED - Set Decimal Flag ====================================================================
        table.put(0xF8, new Opcode(0xF8, "SED", IMPLIED, 2, () -> {
            cpu.status.D = 1;
            return 2;
        }));

        // SEI - Set Interrupt Disable ====================================================================
        table.put(0x78, new Opcode(0x78, "SEI", IMPLIED, 2, () -> {
            cpu.status.I = 1;
            return 2;
        }));

        return table;
    }
}
