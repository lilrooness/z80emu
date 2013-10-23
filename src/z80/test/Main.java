package z80.test;

import z80.core.Control;

public class Main {
	public static void main(String[] args) {
        Control control = new Control();
        control.runProgram((short)1);
	}
}
