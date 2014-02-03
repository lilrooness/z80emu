/**
 * <project>
 * <name>, ICP-3099
 * 2013/14
 * Supervisor: <name>
 */
package z80.core;

import z80.instructions.set.LD16;
import z80.instructions.set.LD8Bit;

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
    private boolean isSetup = false; // for use when stepping through code

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

    public void stepProgram(short startAddress) {
        if(!isSetup) {
            registerState = RegisterState.getInstance();
            registerState.setPc(startAddress);
            isSetup = true;
        }

        byte opcode = registerState.fetchWord8();
        if(!isPrefix(opcode)) {
            processUnPrefixed(opcode, registerState);
        } else {
            processedPrefixed(opcode, registerState);
        }
    }

    private void processUnPrefixed(byte opcode, RegisterState registerState) {
        switch (opcode) {
            case 0x01:
            case 0x17:
            case 0x33:
            case 0x49: {
                LD16.LDddnn();
            }

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
                LD8Bit.LDrr(registerState);
            }break;
            //LD r, n
            case 0x3E:
            case 0x6:
            case 0xE:
            case 0x16:
            case 0x1E:
            case 0x26:
            case 0x2E: {
                LD8Bit.LDrn(registerState);
            }break;
            case 0x7E:
            case 0x46:
            case 0x4E:
            case 0x56:
            case 0x5E:
            case 0x66:
            case 0x6E: {
                LD8Bit.DLrHL(registerState);
            }break;
            case 0x77:
            case 0x70:
            case 0x71:
            case 0x72:
            case 0x73:
            case 0x74:
            case 0x75: {
                LD8Bit.LDHLr(registerState);
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
                        LD8Bit.LDrIXd(registerState);
                    }break;
                    case 0x77:
                    case 0x70:
                    case 0x71:
                    case 0x72:
                    case 0x73:
                    case 0x74:
                    case 0x75: {
                        LD8Bit.LDIXdr(registerState);
                    }break;
                    case 0x36: {
                        LD8Bit.LDIXdn(registerState);
                    }break;
                    case 0x21: {
                        LD16.LDIXnn();
                    }
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
                        LD8Bit.LDrIYd(registerState);
                    }break;
                    case 0x77:
                    case 0x70:
                    case 0x71:
                    case 0x72:
                    case 0x73:
                    case 0x74:
                    case 0x75: {
                        LD8Bit.LDIYdr(registerState);
                    }break;
                    case 0x36: {
                        LD8Bit.LDIYdn(registerState);
                    }break;
                }
            }break;
            case 0x36:{
                opcode = registerState.fetchWord8();
                LD8Bit.LDHLn(registerState);
            }break;
            case 0x0A:{
                LD8Bit.LDABC(registerState);
            }break;
            case 0x1A:{
                LD8Bit.LDADE(registerState);
            }break;
            case 0x3A:{
                LD8Bit.LDAnn(registerState);
            }break;
            case 0x02:{
                LD8Bit.LDBCA(registerState);
            }break;
            case 0x12:{
                LD8Bit.LDDEA(registerState);
            }break;
            case 0x32:{
                LD8Bit.LDnnA(registerState);
            }break;
            case 0xED:{
                opcode = registerState.fetchWord8();
                switch (opcode) {
                    case 0x57: {
                        LD8Bit.LDAI(registerState);
                    }break;
                    case 0x5f: {
                        LD8Bit.LDAR(registerState);
                    }break;
                    case 0x47: {
                        LD8Bit.LDIA(registerState);
                    }break;
                    case 0x4f: {
                        LD8Bit.LDRA(registerState);
                    }
                }
            }break;
        }
    }
    private boolean isPrefix(byte opcode) {
        return false;
    }

    public boolean isSetup() {
        return isSetup;
    }

    public void setIsSetup(boolean isSetup) {
        this.isSetup = isSetup;
    }
}
