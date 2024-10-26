package br.com.tommiranda.cpu;

import java.util.function.Supplier;

public record Opcode(int op, String label, AddressMode addressMode, int cycles, Supplier<Integer> instruction) {}