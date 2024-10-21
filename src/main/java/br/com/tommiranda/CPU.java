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

        public int toByte() {
            return (N << 7) | (V << 6) | (B << 4) | (D << 3) | (I << 2) | (Z << 1) | C;
        }

        public void fromByte(int b) {
            N = (b >> 7) & 1;
            V = (b >> 6) & 1;
            B = (b >> 4) & 1;
            D = (b >> 3) & 1;
            I = (b >> 2) & 1;
            Z = (b >> 1) & 1;
            C = b & 1;
        }
    }
}
