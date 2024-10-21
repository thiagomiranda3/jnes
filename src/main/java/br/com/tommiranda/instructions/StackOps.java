package br.com.tommiranda.instructions;

import br.com.tommiranda.Bus;
import br.com.tommiranda.CPU;
import br.com.tommiranda.Opcode;

import java.util.HashMap;
import java.util.Map;

import static br.com.tommiranda.AddressMode.IMPLIED;

public class StackOps {

    private static final CPU cpu = Bus.cpu;
    public static final Map<Integer, Opcode> table = new HashMap<>();

    static {
        // PHA - Push Accumulator
        table.put(0x48, new Opcode(0x48, "PHA", IMPLIED, 3, () -> {
            Bus.write(0x100 + cpu.SP, cpu.A);
            cpu.SP = (cpu.SP - 1) & 0xFF;
            return 3;
        }));

        // PHP - Push Processor Status
        table.put(0x08, new Opcode(0x08, "PHP", IMPLIED, 3, () -> {
            Bus.write(0x100 + cpu.SP, cpu.status.toByte());
            cpu.SP = (cpu.SP - 1) & 0xFF;
            return 3;
        }));

        // PLA - Pull Accumulator
        table.put(0x68, new Opcode(0x68, "PLA", IMPLIED, 4, () -> {
            cpu.SP = (cpu.SP + 1) & 0xFF;
            cpu.A = Bus.read(0x100 + cpu.SP);
            cpu.status.Z = cpu.A == 0 ? 1 : 0;
            cpu.status.N = (cpu.A & 0x80) != 0 ? 1 : 0;
            return 4;
        }));

        // PLP - Pull Processor Status
        table.put(0x28, new Opcode(0x28, "PLP", IMPLIED, 4, () -> {
            cpu.SP = (cpu.SP + 1) & 0xFF;
            cpu.status.fromByte(Bus.read(0x100 + cpu.SP));
            return 4;
        }));
    }
}
