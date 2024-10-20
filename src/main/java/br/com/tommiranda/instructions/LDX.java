package br.com.tommiranda.instructions;

import br.com.tommiranda.Bus;
import br.com.tommiranda.CPU;
import br.com.tommiranda.Opcode;

import java.util.HashMap;
import java.util.Map;

import static br.com.tommiranda.AddressMode.*;

public class LDX {

    private static final CPU cpu = Bus.cpu;
    public static final Map<Integer, Opcode> table = new HashMap<>();

    // LDX - Load X Register
    static {
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
    }
}
