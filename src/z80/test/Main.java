package z80.test;

import z80.core.Control;
import z80.core.RegisterState;
import z80.memory.Memory;
import z80.util.RadixOperations;

import java.util.BitSet;

public class Main {

    public static void main(String[] args) {
        String code = "00111110 00000011 01110110";
        BitSet processedCode = new BitSet(code.length());
        for(int i=0; i<code.length(); i++) {
            if(code.charAt(i) == '1') {
                processedCode.set(code.length() - i, true); // read in the bits reversed, because bitset uses little endian
            } else if(code.charAt(i) == '0') {
                processedCode.set(code.length() - i, false);
            } else {
                throw new IllegalArgumentException("An illegal character "+code.charAt(i)+ "was found at position: "+i);
            }
        }
      System.out.println(processedCode.toString());
        byte[] program = RadixOperations.toByteArray(code);
        short codeSegmentOffset = 0;
        for(int i=0; i<program.length; i++) {
            Memory.memory[codeSegmentOffset+i] = program[i];
        }

        Control control = new Control();
        control.runProgram(codeSegmentOffset);

        RegisterState registerState = RegisterState.getInstance();
        System.out.println("reg A: "+ registerState.getA()[0]);
    }
}
