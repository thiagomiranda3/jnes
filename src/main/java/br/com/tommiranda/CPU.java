package br.com.tommiranda;

public class CPU {

    public int PC; // Program Counter
    public int SP; // Stack Pointer
    public int A; // Accumulator
    public int X; // Index Register X
    public int Y; // Index Register Y
    public final Status status = new Status();

    public boolean pageCrossed;

    public static class Status {
        public int C; // Carry
        public int Z; // Zero
        public int I; // Interrupt Disable
        public int D; // Decimal Mode
        public int B; // Break Command
        public int V; // Overflow
        public int N; // Negative
    }
}
