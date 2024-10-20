package br.com.tommiranda;

import java.util.function.Supplier;

import static br.com.tommiranda.Bus.*;
import static br.com.tommiranda.Bus.cpu;

public enum AddressMode {

    IMMEDIATE(1, () -> read(cpu.PC++)),
    ZERO_PAGE(1, () -> {
        int address = read(cpu.PC++);
        return read(address);
    }),
    ZERO_PAGE_X(1, () -> {
        int address = (read(cpu.PC++) + cpu.X) & 0xFF;
        return read(address);
    }),
    ZERO_PAGE_Y(1, () -> {
        int address = (read(cpu.PC++) + cpu.Y) & 0xFF;
        return read(address);
    }),
    ABSOLUTE(2, () -> {
        int lsb = read(cpu.PC++);
        int msb = read(cpu.PC++) << 8;
        int address = lsb | msb;
        return read(address);
    }),
    ABSOLUTE_X(2, () -> {
        int lsb = read(cpu.PC++);
        int msb = read(cpu.PC++) << 8;
        int address = (msb | lsb) + cpu.X;

        if ((address & 0xFF00) != (msb & 0xFF00)) {
            cpu.pageCrossed = true;
        }

        return read(address);
    }),
    ABSOLUTE_Y(2, () -> {
        int lsb = read(cpu.PC++);
        int msb = read(cpu.PC++) << 8;
        int address = (msb | lsb) + cpu.Y;

        if ((address & 0xFF00) != (msb & 0xFF00)) {
            cpu.pageCrossed = true;
        }

        return read(address);
    }),
    INDIRECT(2, () -> {
        int lsb_ptr = read(cpu.PC++);
        int msb_ptr = read(cpu.PC++) << 8;
        int addr_ptr = msb_ptr | lsb_ptr;
        int lsb = read(addr_ptr);
        int msb = read(addr_ptr + 1) << 8;
        int address = msb | lsb;
        return read(address);
    }),
    INDIRECT_X(1, () -> {
        int ptr = (read(cpu.PC++) + cpu.X) & 0xFF;
        int lsb = read(ptr);
        int msb = read((ptr + 1) & 0xFF) << 8;
        int address = msb | lsb;
        return read(address);
    }),
    INDIRECT_Y(1, () -> {
        int ptr = read(cpu.PC++);
        int lsb = read(ptr);
        int msb = read((ptr + 1) & 0xFF) << 8;
        int address = (msb | lsb) + cpu.Y;

        if ((address & 0xFF00) != (msb & 0xFF00)) {
            cpu.pageCrossed = true;
        }

        return read(address);
    }),
    IMPLIED(0, () -> 0),
    ACCUMULATOR(0, () -> 0),
    RELATIVE(1, () -> {
        int offset = read(cpu.PC++);
        return offset < 0x80 ? offset : offset - 0x100;
    }),
    ;

    private final int bytes;

    AddressMode(int bytes, Supplier<Integer> fn) {
        this.bytes = bytes;
    }

    public int getBytes() {
        return bytes;
    }
}
