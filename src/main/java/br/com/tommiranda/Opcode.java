package br.com.tommiranda;

import java.util.function.Consumer;
import java.util.function.Supplier;

public record Opcode(int op, String label, AddressMode addressMode, int cycles, Supplier<Integer> proc) {}