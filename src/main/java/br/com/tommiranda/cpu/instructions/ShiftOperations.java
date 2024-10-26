package br.com.tommiranda.cpu.instructions;

import br.com.tommiranda.Bus;
import br.com.tommiranda.cpu.CPU;
import br.com.tommiranda.cpu.Opcode;

import java.util.HashMap;
import java.util.Map;

import static br.com.tommiranda.cpu.AddressMode.*;

public class ShiftOperations {

    private static final CPU cpu = Bus.cpu;

    public static Map<Integer, Opcode> getTable() {
        Map<Integer, Opcode> table = new HashMap<>();

        // ASL - Arithmetic Shift Left ====================================================================
        table.put(0x0A, new Opcode(0x0A, "ASL Accumulator", ACCUMULATOR, 2, () -> {
            int result = (cpu.A << 1) & 0xFF;
            cpu.status.C = (cpu.A & 0x80) != 0 ? 1 : 0;
            cpu.status.Z = result == 0 ? 1 : 0;
            cpu.status.N = (result & 0x80) != 0 ? 1 : 0;
            cpu.A = result;
            return 2;
        }));

        table.put(0x06, new Opcode(0x06, "ASL Zero Page", ZERO_PAGE, 5, () -> {
            int address = ZERO_PAGE.fetchAddress();
            int value = Bus.read(address);
            int result = (value << 1) & 0xFF;
            Bus.write(address, result);
            cpu.status.C = (value & 0x80) != 0 ? 1 : 0;
            cpu.status.Z = result == 0 ? 1 : 0;
            cpu.status.N = (result & 0x80) != 0 ? 1 : 0;
            return 5;
        }));

        table.put(0x16, new Opcode(0x16, "ASL Zero Page X", ZERO_PAGE_X, 6, () -> {
            int address = ZERO_PAGE_X.fetchAddress();
            int value = Bus.read(address);
            int result = (value << 1) & 0xFF;
            Bus.write(address, result);
            cpu.status.C = (value & 0x80) != 0 ? 1 : 0;
            cpu.status.Z = result == 0 ? 1 : 0;
            cpu.status.N = (result & 0x80) != 0 ? 1 : 0;
            return 6;
        }));

        table.put(0x0E, new Opcode(0x0E, "ASL Absolute", ABSOLUTE, 6, () -> {
            int address = ABSOLUTE.fetchAddress();
            int value = Bus.read(address);
            int result = (value << 1) & 0xFF;
            Bus.write(address, result);
            cpu.status.C = (value & 0x80) != 0 ? 1 : 0;
            cpu.status.Z = result == 0 ? 1 : 0;
            cpu.status.N = (result & 0x80) != 0 ? 1 : 0;
            return 6;
        }));

        table.put(0x1E, new Opcode(0x1E, "ASL Absolute X", ABSOLUTE_X, 7, () -> {
            int address = ABSOLUTE_X.fetchAddress();
            int value = Bus.read(address);
            int result = (value << 1) & 0xFF;
            Bus.write(address, result);
            cpu.status.C = (value & 0x80) != 0 ? 1 : 0;
            cpu.status.Z = result == 0 ? 1 : 0;
            cpu.status.N = (result & 0x80) != 0 ? 1 : 0;
            return 7;
        }));

        // LSR - Logical Shift Right ====================================================================
        table.put(0x4A, new Opcode(0x4A, "LSR Accumulator", ACCUMULATOR, 2, () -> {
            int result = (cpu.A >> 1) & 0xFF;
            cpu.status.C = cpu.A & 1;
            cpu.status.Z = result == 0 ? 1 : 0;
            cpu.status.N = 0;
            cpu.A = result;
            return 2;
        }));

        table.put(0x46, new Opcode(0x46, "LSR Zero Page", ZERO_PAGE, 5, () -> {
            int address = ZERO_PAGE.fetchAddress();
            int value = Bus.read(address);
            int result = value >> 1;
            Bus.write(address, result);
            cpu.status.C = value & 1;
            cpu.status.Z = result == 0 ? 1 : 0;
            cpu.status.N = 0;
            return 5;
        }));

        table.put(0x56, new Opcode(0x56, "LSR Zero Page X", ZERO_PAGE_X, 6, () -> {
            int address = ZERO_PAGE_X.fetchAddress();
            int value = Bus.read(address);
            int result = value >> 1;
            Bus.write(address, result);
            cpu.status.C = value & 1;
            cpu.status.Z = result == 0 ? 1 : 0;
            cpu.status.N = 0;
            return 6;
        }));

        table.put(0x4E, new Opcode(0x4E, "LSR Absolute", ABSOLUTE, 6, () -> {
            int address = ABSOLUTE.fetchAddress();
            int value = Bus.read(address);
            int result = value >> 1;
            Bus.write(address, result);
            cpu.status.C = value & 1;
            cpu.status.Z = result == 0 ? 1 : 0;
            cpu.status.N = 0;
            return 6;
        }));

        table.put(0x5E, new Opcode(0x5E, "LSR Absolute X", ABSOLUTE_X, 7, () -> {
            int address = ABSOLUTE_X.fetchAddress();
            int value = Bus.read(address);
            int result = value >> 1;
            Bus.write(address, result);
            cpu.status.C = value & 1;
            cpu.status.Z = result == 0 ? 1 : 0;
            cpu.status.N = 0;
            return 7;
        }));

        // ROL - Rotate Left ====================================================================
        table.put(0x2A, new Opcode(0x2A, "ROL Accumulator", ACCUMULATOR, 2, () -> {
            int result = ((cpu.A << 1) | cpu.status.C) & 0xFF;
            cpu.status.C = (cpu.A & 0x80) != 0 ? 1 : 0;
            cpu.status.Z = result == 0 ? 1 : 0;
            cpu.status.N = (result & 0x80) != 0 ? 1 : 0;
            cpu.A = result;
            return 2;
        }));

        table.put(0x26, new Opcode(0x26, "ROL Zero Page", ZERO_PAGE, 5, () -> {
            int address = ZERO_PAGE.fetchAddress();
            int value = Bus.read(address);
            int result = (value << 1) | cpu.status.C;
            Bus.write(address, result);
            cpu.status.C = (value & 0x80) != 0 ? 1 : 0;
            cpu.status.Z = result == 0 ? 1 : 0;
            cpu.status.N = (result & 0x80) != 0 ? 1 : 0;
            return 5;
        }));

        table.put(0x36, new Opcode(0x36, "ROL Zero Page X", ZERO_PAGE_X, 6, () -> {
            int address = ZERO_PAGE_X.fetchAddress();
            int value = Bus.read(address);
            int result = (value << 1) | cpu.status.C;
            Bus.write(address, result);
            cpu.status.C = (value & 0x80) != 0 ? 1 : 0;
            cpu.status.Z = result == 0 ? 1 : 0;
            cpu.status.N = (result & 0x80) != 0 ? 1 : 0;
            return 6;
        }));

        table.put(0x2E, new Opcode(0x2E, "ROL Absolute", ABSOLUTE, 6, () -> {
            int address = ABSOLUTE.fetchAddress();
            int value = Bus.read(address);
            int result = (value << 1) | cpu.status.C;
            Bus.write(address, result);
            cpu.status.C = (value & 0x80) != 0 ? 1 : 0;
            cpu.status.Z = result == 0 ? 1 : 0;
            cpu.status.N = (result & 0x80) != 0 ? 1 : 0;
            return 6;
        }));

        table.put(0x3E, new Opcode(0x3E, "ROL Absolute X", ABSOLUTE_X, 7, () -> {
            int address = ABSOLUTE_X.fetchAddress();
            int value = Bus.read(address);
            int result = (value << 1) | cpu.status.C;
            Bus.write(address, result);
            cpu.status.C = (value & 0x80) != 0 ? 1 : 0;
            cpu.status.Z = result == 0 ? 1 : 0;
            cpu.status.N = (result & 0x80) != 0 ? 1 : 0;
            return 7;
        }));

        // ROR - Rotate Right ====================================================================
        table.put(0x6A, new Opcode(0x6A, "ROR Accumulator", ACCUMULATOR, 2, () -> {
            int result = ((cpu.A >> 1) | (cpu.status.C << 7)) & 0xFF;
            cpu.status.C = cpu.A & 1;
            cpu.status.Z = result == 0 ? 1 : 0;
            cpu.status.N = 0;
            cpu.A = result;
            return 2;
        }));

        table.put(0x66, new Opcode(0x66, "ROR Zero Page", ZERO_PAGE, 5, () -> {
            int address = ZERO_PAGE.fetchAddress();
            int value = Bus.read(address);
            int result = (value >> 1) | (cpu.status.C << 7);
            Bus.write(address, result);
            cpu.status.C = value & 1;
            cpu.status.Z = result == 0 ? 1 : 0;
            cpu.status.N = 0;
            return 5;
        }));

        table.put(0x76, new Opcode(0x76, "ROR Zero Page X", ZERO_PAGE_X, 6, () -> {
            int address = ZERO_PAGE_X.fetchAddress();
            int value = Bus.read(address);
            int result = (value >> 1) | (cpu.status.C << 7);
            Bus.write(address, result);
            cpu.status.C = value & 1;
            cpu.status.Z = result == 0 ? 1 : 0;
            cpu.status.N = 0;
            return 6;
        }));

        table.put(0x6E, new Opcode(0x6E, "ROR Absolute", ABSOLUTE, 6, () -> {
            int address = ABSOLUTE.fetchAddress();
            int value = Bus.read(address);
            int result = (value >> 1) | (cpu.status.C << 7);
            Bus.write(address, result);
            cpu.status.C = value & 1;
            cpu.status.Z = result == 0 ? 1 : 0;
            cpu.status.N = 0;
            return 6;
        }));

        table.put(0x7E, new Opcode(0x7E, "ROR Absolute X", ABSOLUTE_X, 7, () -> {
            int address = ABSOLUTE_X.fetchAddress();
            int value = Bus.read(address);
            int result = (value >> 1) | (cpu.status.C << 7);
            Bus.write(address, result);
            cpu.status.C = value & 1;
            cpu.status.Z = result == 0 ? 1 : 0;
            cpu.status.N = 0;
            return 7;
        }));

        return table;
    }
}
