package br.com.tommiranda.instructions;

import br.com.tommiranda.Bus;
import br.com.tommiranda.CPU;
import br.com.tommiranda.Opcode;

import java.util.HashMap;
import java.util.Map;

import static br.com.tommiranda.AddressMode.*;

public class STX {

    private static final CPU cpu = Bus.cpu;
    public static final Map<Integer, Opcode> table = new HashMap<>();

    // STX - Store X Register
    static {
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
    }
}
