package br.com.tommiranda.cpu;

import java.util.function.Supplier;

import static br.com.tommiranda.Bus.cpu;
import static br.com.tommiranda.Bus.read;

public enum AddressMode {

    IMMEDIATE(1, () -> cpu.PC++),
    ZERO_PAGE(1, () -> read(cpu.PC++)),
    ZERO_PAGE_X(1, () -> (read(cpu.PC++) + cpu.X) & 0xFF),
    ZERO_PAGE_Y(1, () -> (read(cpu.PC++) + cpu.Y) & 0xFF),
    ABSOLUTE(2, () -> {
        int lsb = read(cpu.PC++);
        int msb = read(cpu.PC++) << 8;
        return lsb | msb;
    }),
    ABSOLUTE_X(2, () -> {
        int lsb = read(cpu.PC++);
        int msb = read(cpu.PC++) << 8;
        int address = (msb | lsb) + cpu.X;

        if ((address & 0xFF00) != (msb & 0xFF00)) {
            cpu.pageCrossed = true;
        }

        return address;
    }),
    ABSOLUTE_Y(2, () -> {
        int lsb = read(cpu.PC++);
        int msb = read(cpu.PC++) << 8;
        int address = (msb | lsb) + cpu.Y;

        if ((address & 0xFF00) != (msb & 0xFF00)) {
            cpu.pageCrossed = true;
        }

        return address;
    }),
    INDIRECT(2, () -> {
        int lsb_ptr = read(cpu.PC++);
        int msb_ptr = read(cpu.PC++) << 8;
        int addr_ptr = msb_ptr | lsb_ptr;
        int lsb = read(addr_ptr);
        int msb = read(addr_ptr + 1) << 8;
        return msb | lsb;
    }),
    INDIRECT_X(1, () -> {
        int ptr = (read(cpu.PC++) + cpu.X) & 0xFF;
        int lsb = read(ptr);
        int msb = read((ptr + 1) & 0xFF) << 8;
        return msb | lsb;
    }),
    INDIRECT_Y(1, () -> {
        int ptr = read(cpu.PC++);
        int lsb = read(ptr);
        int msb = read((ptr + 1) & 0xFF) << 8;
        int address = (msb | lsb) + cpu.Y;

        if ((address & 0xFF00) != (msb & 0xFF00)) {
            cpu.pageCrossed = true;
        }

        return address;
    }),
    IMPLIED(0, () -> 0),
    ACCUMULATOR(0, () -> 0),
    RELATIVE(1, () -> {
        int offset = read(cpu.PC++);
        return offset < 0x80 ? offset : offset - 0x100;
    });

    private final int bytes;
    private final Supplier<Integer> fn;

    AddressMode(int bytes, Supplier<Integer> fn) {
        this.bytes = bytes;
        this.fn = fn;
    }

    public int getBytes() {
        return bytes;
    }

    public int fetch() {
        if (this == RELATIVE) {
            throw new RuntimeException("Cannot fetch value for relative mode");
        }

        int address = fn.get();
        return read(address);
    }

    public int fetchAddress() {
        return fn.get();
    }
}
