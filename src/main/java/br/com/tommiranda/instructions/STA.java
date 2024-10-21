package br.com.tommiranda.instructions;

import br.com.tommiranda.Bus;
import br.com.tommiranda.CPU;
import br.com.tommiranda.Opcode;

import java.util.HashMap;
import java.util.Map;

import static br.com.tommiranda.AddressMode.*;
import static br.com.tommiranda.Bus.cpu;

public class STA {

    private static final CPU cpu = Bus.cpu;
    public static final Map<Integer, Opcode> table = new HashMap<>();

    // STA - Store Accumulator
    static {
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
    }
}
