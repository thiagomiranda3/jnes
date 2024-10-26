package br.com.tommiranda.cpu.instructions;

import br.com.tommiranda.Bus;
import br.com.tommiranda.cpu.CPU;
import br.com.tommiranda.cpu.Opcode;

import java.util.HashMap;
import java.util.Map;

import static br.com.tommiranda.cpu.AddressMode.*;

public class JumpCallOperations {

    private static final CPU cpu = Bus.cpu;

    public static Map<Integer, Opcode> getTable() {
        Map<Integer, Opcode> table = new HashMap<>();

        // JMP - Jump ====================================================================
        table.put(0x4C, new Opcode(0x4C, "JMP Absolute", ABSOLUTE, 3, () -> {
            cpu.PC = ABSOLUTE.fetchAddress();
            return 3;
        }));

        table.put(0x6C, new Opcode(0x6C, "JMP Indirect", INDIRECT, 5, () -> {
            int address = INDIRECT.fetchAddress();
            if ((address & 0xFF) == 0xFF) {
                int lsb = Bus.read(address);
                int msb = Bus.read(address & 0xFF00);
                address = (msb << 8) | lsb;
            } else {
                int lsb = Bus.read(address);
                int msb = Bus.read(address + 1);
                address = (msb << 8) | lsb;
            }

            cpu.PC = address;
            return 5;
        }));

        // JSR - Jump to Subroutine ====================================================================
        table.put(0x20, new Opcode(0x20, "JSR Absolute", ABSOLUTE, 6, () -> {
            int address = ABSOLUTE.fetchAddress();
            Bus.pushSP((cpu.PC >> 8) & 0xFF);
            Bus.pushSP(cpu.PC & 0xFF);
            cpu.PC = address;
            return 6;
        }));

        // RTS - Return from Subroutine ====================================================================
        table.put(0x60, new Opcode(0x60, "RTS", IMPLIED, 6, () -> {
            int lsb = Bus.pullSP();
            int msb = Bus.pullSP();
            cpu.PC = ((msb << 8) | lsb) + 1;
            return 6;
        }));

        return table;
    }
}
