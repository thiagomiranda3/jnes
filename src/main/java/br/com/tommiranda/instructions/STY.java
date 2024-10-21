package br.com.tommiranda.instructions;

import br.com.tommiranda.Bus;
import br.com.tommiranda.CPU;
import br.com.tommiranda.Opcode;

import java.util.HashMap;
import java.util.Map;

import static br.com.tommiranda.AddressMode.*;

public class STY {

    private static final CPU cpu = Bus.cpu;
    public static final Map<Integer, Opcode> table = new HashMap<>();

    // STY - Store Y Register
    static {
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
    }
}
