package br.com.tommiranda.cpu.instructions;

import br.com.tommiranda.Bus;
import br.com.tommiranda.cpu.CPU;
import br.com.tommiranda.cpu.Opcode;

import java.util.HashMap;
import java.util.Map;

import static br.com.tommiranda.cpu.AddressMode.*;

public class SystemOperations {

    private static final CPU cpu = Bus.cpu;

    public static Map<Integer, Opcode> getTable() {
        Map<Integer, Opcode> table = new HashMap<>();

        // BRK - Force Interrupt ====================================================================
        table.put(0x00, new Opcode(0x00, "BRK", IMPLIED, 7, () -> {
            Bus.pushSP((cpu.PC >> 8) & 0xFF);
            Bus.pushSP(cpu.PC & 0xFF);
            Bus.pushSP(cpu.status.toByte());
            cpu.PC = Bus.read(0xFFFE) | (Bus.read(0xFFFF) << 8);
            cpu.status.B = 1;
            return 7;
        }));

        // NOP - No Operation ====================================================================
        table.put(0xEA, new Opcode(0xEA, "NOP", IMPLIED, 2, () -> 2));

        // RTI - Return from Interrupt ====================================================================
        table.put(0x40, new Opcode(0x40, "RTI", IMPLIED, 6, () -> {
            int status = Bus.pullSP();
            cpu.status.fromByte(status);
            int lsb = Bus.pullSP();
            int msb = Bus.pullSP();
            cpu.PC = ((msb << 8) | lsb);
            return 6;
        }));

        return table;
    }
}
