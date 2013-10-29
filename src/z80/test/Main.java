package z80.test;

import z80.core.Control;
import z80.memory.Memory;

import java.util.BitSet;

public class Main {

    public static void main(String[] args) {
//        String code = "011111100000001101110110";
//        BitSet processedCode = new BitSet(code.length());
//        for(int i=0; i<code.length(); i++) {
//            if(code.charAt(i) == '1') {
//                processedCode.set(code.length() - i, true); // read in the bits reversed, because bitset uses little endian
//            } else if(code.charAt(i) == '0') {
//                processedCode.set(code.length() - i, false);
//            } else {
//                throw new IllegalArgumentException("An illegal character "+code.charAt(i)+ "was found at position: "+i);
//            }
//        }
//        System.out.println(processedCode.toString());
//        byte[] program = processedCode.toByteArray();
//        short codeSegmentOffset = 0;
//        for(int i=0; i<program.length; i++) {
//            Memory.memory[i+codeSegmentOffset] = program[i];
//        }
//
//        Control control = new Control();
//        control.runProgram(codeSegmentOffset);

        byte b = 0x76;
        int i = b & 0xff;
        System.out.println(i == 0x76);

    }
}
