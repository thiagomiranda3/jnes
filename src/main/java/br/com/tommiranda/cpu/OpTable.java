package br.com.tommiranda.cpu;

import br.com.tommiranda.cpu.instructions.*;

import java.util.HashMap;
import java.util.Map;

public final class OpTable {

    private Map<Integer, Opcode> table = new HashMap<>();

    public OpTable() {
        table.putAll(LoadStoreOperations.getTable());
        table.putAll(TransfersOperations.getTable());
        table.putAll(StackOperations.getTable());
        table.putAll(LogicalOperations.getTable());
        table.putAll(ArithmeticOperations.getTable());
        table.putAll(IncDecOperations.getTable());
        table.putAll(ShiftOperations.getTable());
        table.putAll(JumpCallOperations.getTable());
        table.putAll(BranchOperations.getTable());
        table.putAll(StatusChangeOperations.getTable());
        table.putAll(SystemOperations.getTable());
    }
}