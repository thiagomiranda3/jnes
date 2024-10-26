package br.com.tommiranda.cpu.instructions;

import br.com.tommiranda.Bus;
import br.com.tommiranda.cpu.CPU;
import br.com.tommiranda.cpu.Opcode;

import java.util.HashMap;
import java.util.Map;

import static br.com.tommiranda.cpu.AddressMode.*;

public class IncDecOperations {

    private static final CPU cpu = Bus.cpu;

    public static Map<Integer, Opcode> getTable() {
        Map<Integer, Opcode> table = new HashMap<>();

        // INC - Increment Memory ====================================================================
        table.put(0xE6, new Opcode(0xE6, "INC Zero Page", ZERO_PAGE, 5, () -> {
            int address = ZERO_PAGE.fetchAddress();
            int value = (Bus.read(address) + 1) & 0xFF;
            Bus.write(address, value);
            cpu.status.Z = value == 0 ? 1 : 0;
            cpu.status.N = (value & 0x80) != 0 ? 1 : 0;
            return 5;
        }));

        table.put(0xF6, new Opcode(0xF6, "INC Zero Page X", ZERO_PAGE_X, 6, () -> {
            int address = ZERO_PAGE_X.fetchAddress();
            int value = (Bus.read(address) + 1) & 0xFF;
            Bus.write(address, value);
            cpu.status.Z = value == 0 ? 1 : 0;
            cpu.status.N = (value & 0x80) != 0 ? 1 : 0;
            return 6;
        }));

        table.put(0xEE, new Opcode(0xEE, "INC Absolute", ABSOLUTE, 6, () -> {
            int address = ABSOLUTE.fetchAddress();
            int value = (Bus.read(address) + 1) & 0xFF;
            Bus.write(address, value);
            cpu.status.Z = value == 0 ? 1 : 0;
            cpu.status.N = (value & 0x80) != 0 ? 1 : 0;
            return 6;
        }));

        table.put(0xFE, new Opcode(0xFE, "INC Absolute X", ABSOLUTE_X, 7, () -> {
            int address = ABSOLUTE_X.fetchAddress();
            int value = (Bus.read(address) + 1) & 0xFF;
            Bus.write(address, value);
            cpu.status.Z = value == 0 ? 1 : 0;
            cpu.status.N = (value & 0x80) != 0 ? 1 : 0;
            return 7;
        }));

        // INX - Increment X Register =================================================================
        table.put(0xE8, new Opcode(0xE8, "INX", IMPLIED, 2, () -> {
            cpu.X = (cpu.X + 1) & 0xFF;
            cpu.status.Z = cpu.X == 0 ? 1 : 0;
            cpu.status.N = (cpu.X & 0x80) != 0 ? 1 : 0;
            return 2;
        }));

        // INY - Increment Y Register =================================================================
        table.put(0xC8, new Opcode(0xC8, "INY", IMPLIED, 2, () -> {
            cpu.Y = (cpu.Y + 1) & 0xFF;
            cpu.status.Z = cpu.Y == 0 ? 1 : 0;
            cpu.status.N = (cpu.Y & 0x80) != 0 ? 1 : 0;
            return 2;
        }));

        // DEC - Decrement Memory ====================================================================
        table.put(0xC6, new Opcode(0xC6, "DEC Zero Page", ZERO_PAGE, 5, () -> {
            int address = ZERO_PAGE.fetchAddress();
            int value = (Bus.read(address) - 1) & 0xFF;
            Bus.write(address, value);
            cpu.status.Z = value == 0 ? 1 : 0;
            cpu.status.N = (value & 0x80) != 0 ? 1 : 0;
            return 5;
        }));

        table.put(0xD6, new Opcode(0xD6, "DEC Zero Page X", ZERO_PAGE_X, 6, () -> {
            int address = ZERO_PAGE_X.fetchAddress();
            int value = (Bus.read(address) - 1) & 0xFF;
            Bus.write(address, value);
            cpu.status.Z = value == 0 ? 1 : 0;
            cpu.status.N = (value & 0x80) != 0 ? 1 : 0;
            return 6;
        }));

        table.put(0xCE, new Opcode(0xCE, "DEC Absolute", ABSOLUTE, 6, () -> {
            int address = ABSOLUTE.fetchAddress();
            int value = (Bus.read(address) - 1) & 0xFF;
            Bus.write(address, value);
            cpu.status.Z = value == 0 ? 1 : 0;
            cpu.status.N = (value & 0x80) != 0 ? 1 : 0;
            return 6;
        }));

        table.put(0xDE, new Opcode(0xDE, "DEC Absolute X", ABSOLUTE_X, 7, () -> {
            int address = ABSOLUTE_X.fetchAddress();
            int value = (Bus.read(address) - 1) & 0xFF;
            Bus.write(address, value);
            cpu.status.Z = value == 0 ? 1 : 0;
            cpu.status.N = (value & 0x80) != 0 ? 1 : 0;
            return 7;
        }));

        // DEX - Decrement X Register =================================================================
        table.put(0xCA, new Opcode(0xCA, "DEX", IMPLIED, 2, () -> {
            cpu.X = (cpu.X - 1) & 0xFF;
            cpu.status.Z = cpu.X == 0 ? 1 : 0;
            cpu.status.N = (cpu.X & 0x80) != 0 ? 1 : 0;
            return 2;
        }));

        // DEY - Decrement Y Register =================================================================
        table.put(0x88, new Opcode(0x88, "DEY", IMPLIED, 2, () -> {
            cpu.Y = (cpu.Y - 1) & 0xFF;
            cpu.status.Z = cpu.Y == 0 ? 1 : 0;
            cpu.status.N = (cpu.Y & 0x80) != 0 ? 1 : 0;
            return 2;
        }));

        return table;
    }
}
