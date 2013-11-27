/**
 * <project>
 * <name>, ICP-3099
 * 2013/14
 * Supervisor: <name>
 */
package z80.core;

import z80.instructions.set.LD8bit.*;

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
        registerState.setPc(startAddress);
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
            //HALT
            case 0x76: {
                isRunning = false;
            }break;
            //LD r, r'
            case 0x78:
            case 0x79:
            case 0x7A:
            case 0x7B:
            case 0x7C:
            case 0x7D:
            case 0x47:
            case 0x41:
            case 0x42:
            case 0x43:
            case 0x44:
            case 0x45:
            case 0x4F:
            case 0x48:
            case 0x4A:
            case 0x4B:
            case 0x4C:
            case 0x4D:
            case 0x57:
            case 0x50:
            case 0x51:
            case 0x53:
            case 0x54:
            case 0x55:
            case 0x5F:
            case 0x58:
            case 0x59:
            case 0x5A:
            case 0x5C:
            case 0x5D:
            case 0x67:
            case 0x60:
            case 0x61:
            case 0x62:
            case 0x63:
            case 0x65: {
                LDrr instruction = new LDrr();
                instruction.execute(registerState);
            }break;
            //LD r, n
            case 0x3E:
            case 0x6:
            case 0xE:
            case 0x16:
            case 0x1E:
            case 0x26:
            case 0x2E: {
                LDrn instruction = new LDrn();
                instruction.execute(registerState);
            }break;
            case 0x7E:
            case 0x46:
            case 0x4E:
            case 0x56:
            case 0x5E:
            case 0x66:
            case 0x6E: {
                LDrHL instruction = new LDrHL();
                instruction.execute(registerState);
            }break;
            case 0x77:
            case 0x70:
            case 0x71:
            case 0x72:
            case 0x73:
            case 0x74:
            case 0x75: {
                LDHLr instruction = new LDHLr();
                instruction.execute(registerState);
            }break;

        }
    }

    private void processedPrefixed(byte opcode, RegisterState registerState) {
        switch (opcode & 0xFF) {
            case 0xDD:{// DD PREFIX
                opcode = registerState.fetchWord8();
                switch(opcode & 0xFF) {
                    case 0x7E:
                    case 0x46:
                    case 0x4E:
                    case 0x56:
                    case 0x5E:
                    case 0x66:
                    case 0x6E: {
                        LDrIXd instruction = new LDrIXd();
                        instruction.execute(registerState);
                    }break;
                    case 0x77:
                    case 0x70:
                    case 0x71:
                    case 0x72:
                    case 0x73:
                    case 0x74:
                    case 0x75: {
                        LDIXdr instruction = new LDIXdr();
                        instruction.execute(registerState);
                    }break;
                }
            }break;
            case 0xFD:{// FD PREFIX
                opcode = registerState.fetchWord8();
                switch(opcode & 0xFF) {
                    case 0x7E:
                    case 0x46:
                    case 0x4E:
                    case 0x56:
                    case 0x5E:
                    case 0x66:
                    case 0x6E: {
                        LDrIYd instruction = new LDrIYd();
                        instruction.execute(registerState);
                    }break;
                    case 0x77:
                    case 0x70:
                    case 0x71:
                    case 0x72:
                    case 0x73:
                    case 0x74:
                    case 0x75: {
                        LDIYdr instruction = new LDIYdr();
                        instruction.execute(registerState);
                    }break;
                }
            }break;
            case 0x36:{}break;
            case 0x0A:{}break;
            case 0x1A:{}break;
            case 0x3A:{}break;
            case 0x02:{}break;
            case 0x12:{}break;
            case 0x32:{}break;
            case 0xED:{}break;
        }
    }

    private boolean isPrefix(byte opcode) {
        return false;
    }
}
