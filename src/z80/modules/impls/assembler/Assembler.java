package z80.modules.impls.assembler;

import z80.core.RegisterCodes;
import z80.util.RadixOperations;

import java.math.BigInteger;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Joe on 08/02/2014.
 */
public class Assembler {

    Pattern ld8bitDirect = Pattern.compile("([ABCDEHL]{1}),([0-9a-f]+h)"); // LD A, 54
    Pattern ld8bit = Pattern.compile("([ABCDEHL]{1}),([ABCDEHL]{1})"); // LD A, B
    Pattern ld8bitIndirect = Pattern.compile("([ABCDEHL]{1}),(\\([ABCDEHLIXIY]{2}\\))"); // LD A, (HL)
    Pattern ld8bitMemoryDirect = Pattern.compile("([ABCDEHL]{1}),(\\([0-9a-f]+h\\))");
    Pattern ld8bitIndirectOffset = Pattern.compile("([ABCDEHL]{1}),(\\([ABCDEHLIXIY]{2}\\+[0-9]+h\\))"); // LD A, (IX + 5)

    Pattern ld8bitRegToMemory = Pattern.compile("(\\([ABCDEHL]{2}\\)),([ABCDEHLIXIY]{1})");
    Pattern ld8bitRegToMemoryOffset = Pattern.compile("(\\([ABCDEHLIXIY]{2}\\+[0-9]+h\\)),([ABCDEHL]{1})");
    Pattern ld8bitToMemoryDirect = Pattern.compile("(\\([ABCDEHL]{2}\\)),([0-9]+h)");
    Pattern ld8bitToMemoryOffsetDirect = Pattern.compile("(\\([ABCDEHLIXIY]{2}\\)),([0-9]+h)");
    Pattern ld8bitregToMemoryLocation = Pattern.compile("(\\([0-9a-f]+h\\)),([ABCDEHLIXIY]{1})");

    Pattern ld16bitDirect = Pattern.compile("([ABCDEHL]{2}),([0-9a-f]+h)"); // LD HL, 54
    Pattern ld16bit = Pattern.compile("([ABCDEHL]{2}),([ABCDEHL]{2})"); // LD HL, BC
    Pattern ld16bitIndirect = Pattern.compile("([ABCDEHL]{2}),(\\([ABCDEHLIXIY]{2}\\))"); // LD BC, (HL)
    Pattern ld16bitIndirectOffset = Pattern.compile("([ABCDEHL]{2}),(\\([ABCDEHLIXIY]{2}\\+[0-9a-f]+h\\))"); // LD BC, (ID + 5)
    Pattern ld16bitMemoryDirect = Pattern.compile("([ABCDEHL]{2}),(\\([0-9a-f]+h\\))");

    public Assembler() {

    }

    public String assemble(String assembly) {

        StringBuilder binary = new StringBuilder();
        String[] instructions = assembly.split("\n");

        for(int i=0; i<instructions.length; i++) {
            binary.append(compileInstruction(instructions[i]));
        }

        return binary.toString();
    }

    private String compileInstruction(String instruction) {

        String opcode = "";
        Matcher matcher;

        String type = String.valueOf(instruction.subSequence(0, 3)).trim();
        String remaining = instruction.substring(3).trim().replaceAll(" ", "");

        switch (InstructionType.valueOf(type)) {
            case LD: {
                if((matcher = ld8bit.matcher(remaining)).matches()) {
                    String from = RegisterCodes.valueOf(matcher.group(2)).getBitString();
                    String to = RegisterCodes.valueOf(matcher.group(1)).getBitString();

                    opcode = "01"+to+from;

                } else if((matcher = ld8bitDirect.matcher(remaining)).matches()) {
                    String reg = RegisterCodes.valueOf(matcher.group(1)).getBitString();
                    String binNumber = hexToBin(matcher.group(2));

                    opcode = "00"+reg+"110"+binNumber;

                } else if((matcher = ld8bitIndirect.matcher(remaining)).matches()) {//LD r, (HL)
                    String reg = RegisterCodes.valueOf(matcher.group(1)).getBitString();

                    opcode = "01"+reg+"110";

                } else if((matcher = ld8bitMemoryDirect.matcher(remaining)).matches()) {//LD A, (nn)
                    String add = hexToBinAddress(matcher.group(2));

                    opcode = "00111010"+add;

                } else if((matcher = ld8bitIndirectOffset.matcher(remaining)).matches()) {

                    String reg = RegisterCodes.valueOf(matcher.group(1)).getBitString();
                    String indexReg = RegisterCodes.valueOf(matcher.group(2).substring(0, 2)).getName();
                    String d = hexToBin(matcher.group(2).substring(remaining.indexOf("+")+1));
                    if(indexReg.equals("IY")) {
                        opcode = "11011101"+"01"+reg+"110"+d;
                    } else if(indexReg.equals("IX")) {
                        opcode = "11111111"+"01"+reg+"110"+d;
                    }
                } else if((matcher = ld8bitRegToMemory.matcher(remaining)).matches()) {

                    String reg = RegisterCodes.valueOf(matcher.group(2)).getBitString();
                    opcode = "01110" + reg;

                }else if((matcher = ld8bitregToMemoryLocation.matcher(remaining)).matches()) {

                    String location = hexToBinAddress(matcher.group(1));
                    opcode = "00110010"+location;

                } else if(ld16bit.matcher(remaining).matches()) {

                } else if(ld16bitDirect.matcher(remaining).matches()) {

                } else if(ld16bitIndirect.matcher(remaining).matches()) {

                } else if(ld16bitIndirectOffset.matcher(remaining).matches()) {

                } else if(ld16bitMemoryDirect.matcher(remaining).matches()) {

                }

            }break;
            case ADC: {

            }break;
            case ADD: {

            }
        }

        return opcode;
    }

    private String[] getToFrom(String instruction) {
        String[] registers = new String[2];

        if(ld8bit.matcher(instruction).matches()) {
            registers[0] = RegisterCodes.valueOf(ld8bit.matcher(instruction).group(0)).getBitString();
            registers[1] = RegisterCodes.valueOf(ld8bit.matcher(instruction).group(1)).getBitString();
        }

        if(ld16bit.matcher(instruction).matches()) {

        }
        return null;
    }

    private boolean checkBracketing(String code) {
        Stack<Character> bstack = new Stack<Character>();

        for(int i=0; i<code.length(); i++) {
            if(code.charAt(i) == '(') {
                bstack.push('(');
            } else if(code.charAt(i) == ')' && bstack.peek() == '(') {
                bstack.pop();
            } else if(code.charAt(i) == ')' && bstack.peek() != '(') {
                return false;
            }
        }

        if(!bstack.empty()) {
            return false;
        }

        return true;
    }

    private String hexToBin(String hex) {
        if(hex.contains("h")) {
            hex = hex.substring(0, hex.length()-1);
        }
        BigInteger bi = new BigInteger(hex, 16);
        return RadixOperations.prependZeros(Integer.toBinaryString((int) bi.longValue()));
    }

    private String hexToBinAddress(String hex) {
        if(hex.contains("(")) {
            hex = hex.replaceAll("\\(", "");
            hex = hex.replaceAll("\\)", "");
        }
        if(hex.contains("h")) {
            hex = hex.replaceAll("h", "");
        }
        BigInteger bi = new BigInteger(hex, 16);
        return RadixOperations.prependZeros(Integer.toBinaryString((int) bi.longValue()), 16);
    }

}
