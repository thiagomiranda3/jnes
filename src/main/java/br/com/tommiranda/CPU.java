package br.com.tommiranda;

public class CPU {

    public int PC; // Program Counter
    public int SP; // Stack Pointer
    public int A; // Accumulator
    public int X; // Index Register X
    public int Y; // Index Register Y
    public final Status status = new Status();

    public boolean pageCrossed;

    class Status {
        public byte C; // Carry
        public byte Z; // Zero
        public byte I; // Interrupt Disable
        public byte D; // Decimal Mode
        public byte B; // Break Command
        public byte V; // Overflow
        public byte N; // Negative
    }
}
