/**
 * <project>
 * <name>, ICP-3099
 * 2013/14
 * Supervisor: <name>
 */
package z80.core;

import z80.instructions.set.LD8bit.LDrr;

/**
 * <description>
 *
 * @author Frangoudes, Joseph (eeue5c)
 * @since 01, 1976
 * @version 05, 2005
 */
public class Control {

    private boolean isRunning = true;
    RegisterState registerState;
    public void runProgram(short startAddress) {
        registerState = RegisterState.getInstance();

        while(isRunning) {
            byte opcode = registerState.fetchWord8();
            if(!isPrefix(opcode)) {
                processUnPrefixed(opcode, registerState);
            } else {
                processedPrefixed(opcode, registerState);
            }
        }
    }

    private void processUnPrefixed(byte opcode, RegisterState registerState) {
        switch (opcode) {
            //LD r, r'
            case (byte)0x78:
            case (byte)0x79:
            case (byte)0x7A:
            case (byte)0x7B:
            case (byte)0x7C:
            case (byte)0x7D:
            case (byte)0x47:
            case (byte)0x41:
            case (byte)0x42:
            case (byte)0x43:
            case (byte)0x44:
            case (byte)0x45:
            case (byte)0x4F:
            case (byte)0x48:
            case (byte)0x4A:
            case (byte)0x4B:
            case (byte)0x4C:
            case (byte)0x4D:
            case (byte)0x57:
            case (byte)0x50:
            case (byte)0x51:
            case (byte)0x53:
            case (byte)0x54:
            case (byte)0x55:
            case (byte)0x5F:
            case (byte)0x58:
            case (byte)0x59:
            case (byte)0x5A:
            case (byte)0x5C:
            case (byte)0x5D:
            case (byte)0x67:
            case (byte)0x60:
            case (byte)0x61:
            case (byte)0x62:
            case (byte)0x63:
            case (byte)0x65:{
                LDrr instruction = new LDrr();
                instruction.execute(registerState);
            }break;
        }
    }

    private void processedPrefixed(byte opcode, RegisterState registerState) {
        switch (opcode) {
            case (byte)0xDD:{}break;
            case (byte)0xFD:{}break;
            case (byte)0x36:{}break;
            case (byte)0x0A:{}break;
            case (byte)0x1A:{}break;
            case (byte)0x3A:{}break;
            case (byte)0x02:{}break;
            case (byte)0x12:{}break;
            case (byte)0x32:{}break;
            case (byte)0xED:{}break;
        }
    }

    private boolean isPrefix(byte opcode) {
        return true;
    }
}
