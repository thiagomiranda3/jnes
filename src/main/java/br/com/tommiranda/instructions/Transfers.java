package br.com.tommiranda.instructions;

import br.com.tommiranda.Bus;
import br.com.tommiranda.CPU;
import br.com.tommiranda.Opcode;

import java.util.HashMap;
import java.util.Map;

import static br.com.tommiranda.AddressMode.IMPLIED;

public class Transfers {

    private static final CPU cpu = Bus.cpu;
    public static final Map<Integer, Opcode> table = new HashMap<>();

    static {
        // TAX - Transfer Accumulator to X
        table.put(0xAA, new Opcode(0xAA, "TAX", IMPLIED, 2, () -> {
            cpu.X = cpu.A;
            cpu.status.Z = cpu.X == 0 ? 1 : 0;
            cpu.status.N = (cpu.X & 0x80) != 0 ? 1 : 0;

            return 2;
        }));

        // TAY - Transfer Accumulator to Y
        table.put(0xA8, new Opcode(0xA8, "TAY", IMPLIED, 2, () -> {
            cpu.Y = cpu.A;
            cpu.status.Z = cpu.Y == 0 ? 1 : 0;
            cpu.status.N = (cpu.Y & 0x80) != 0 ? 1 : 0;

            return 2;
        }));

        // TSX - Transfer Stack Pointer to X
        table.put(0xBA, new Opcode(0xBA, "TSX", IMPLIED, 2, () -> {
            cpu.X = cpu.SP;
            cpu.status.Z = cpu.X == 0 ? 1 : 0;
            cpu.status.N = (cpu.X & 0x80) != 0 ? 1 : 0;

            return 2;
        }));

        // TXA - Transfer X to Accumulator
        table.put(0x8A, new Opcode(0x8A, "TXA", IMPLIED, 2, () -> {
            cpu.A = cpu.X;
            cpu.status.Z = cpu.A == 0 ? 1 : 0;
            cpu.status.N = (cpu.A & 0x80) != 0 ? 1 : 0;

            return 2;
        }));

        // TXS - Transfer X to Stack Pointer
        table.put(0x9A, new Opcode(0x9A, "TXS", IMPLIED, 2, () -> {
            cpu.SP = cpu.X;
            return 2;
        }));

        // TYA - Transfer Y to Accumulator
        table.put(0x98, new Opcode(0x98, "TYA", IMPLIED, 2, () -> {
            cpu.A = cpu.Y;
            cpu.status.Z = cpu.A == 0 ? 1 : 0;
            cpu.status.N = (cpu.A & 0x80) != 0 ? 1 : 0;

            return 2;
        }));
    }
}
