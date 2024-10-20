package br.com.tommiranda.instructions;

import br.com.tommiranda.Bus;
import br.com.tommiranda.CPU;
import br.com.tommiranda.Opcode;

import java.util.HashMap;
import java.util.Map;

import static br.com.tommiranda.AddressMode.*;

public class LDY {

    private static final CPU cpu = Bus.cpu;
    public static final Map<Integer, Opcode> table = new HashMap<>();

    // LDY - Load Y Register
    static {
        table.put(0xA0, new Opcode(0xA0, "LDY Immediate", IMMEDIATE, 2, () -> {
            cpu.Y = IMMEDIATE.fetch();
            cpu.status.Z = cpu.Y == 0 ? 1 : 0;
            cpu.status.N = (cpu.Y & 0x80) != 0 ? 1 : 0;
            return 2;
        }));

        table.put(0xA4, new Opcode(0xA4, "LDY Zero Page", ZERO_PAGE, 3, () -> {
            cpu.Y = ZERO_PAGE.fetch();
            cpu.status.Z = cpu.Y == 0 ? 1 : 0;
            cpu.status.N = (cpu.Y & 0x80) != 0 ? 1 : 0;
            return 3;
        }));

        table.put(0xB4, new Opcode(0xB4, "LDY Zero Page X", ZERO_PAGE_X, 4, () -> {
            cpu.Y = ZERO_PAGE_X.fetch();
            cpu.status.Z = cpu.Y == 0 ? 1 : 0;
            cpu.status.N = (cpu.Y & 0x80) != 0 ? 1 : 0;
            return 4;
        }));

        table.put(0xAC, new Opcode(0xAC, "LDY Absolute", ABSOLUTE, 4, () -> {
            cpu.Y = ABSOLUTE.fetch();
            cpu.status.Z = cpu.Y == 0 ? 1 : 0;
            cpu.status.N = (cpu.Y & 0x80) != 0 ? 1 : 0;
            return 4;
        }));

        table.put(0xBC, new Opcode(0xBC, "LDY Absolute Y", ABSOLUTE_X, 4, () -> {
            cpu.Y = ABSOLUTE_X.fetch();
            cpu.status.Z = cpu.Y == 0 ? 1 : 0;
            cpu.status.N = (cpu.Y & 0x80) != 0 ? 1 : 0;

            if (cpu.pageCrossed) {
                return 5;
            }
            return 4;
        }));
    }
}
