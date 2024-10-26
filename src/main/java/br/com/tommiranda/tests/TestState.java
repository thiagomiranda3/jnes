package br.com.tommiranda.tests;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TestState {

    public String name;
    @SerializedName("initial")
    public State initialState;
    @SerializedName("final")
    public State finalState;
    public List<Object[]> cycles = new ArrayList<>();

    public static class State {
        public int pc;
        public int s;
        public int a;
        public int x;
        public int y;
        public int p;
        public List<int[]> ram = new ArrayList<>();
    }
}
