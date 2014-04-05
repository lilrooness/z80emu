/**
 * <project>
 * <name>, ICP-3099
 * 2013/14
 * Supervisor: <name>
 */
package z80.core;

import z80.instructions.set.*;

/**
 * <description>
 *
 * @author Frangoudes, Joseph (eeue5c)
 * @since 01, 1976
 * @version 05, 2005
 */
public class Control {

    //implemented instructions up to page 110
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

            processUnPrefixed(opcode, registerState);
            processedPrefixed(opcode, registerState);

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
            case (byte) 0xC5:
            case (byte) 0xD5:
            case (byte) 0xE5:
            case (byte) 0xF5: {
                LD16.pushqq();
            }break;
            case (byte) 0xC1:
            case (byte) 0xD1:
            case (byte) 0xE1:
            case (byte) 0xF1: {
                LD16.popqq();
            }break;
            case (byte) 0x87:
            case (byte) 0x80:
            case (byte) 0x81:
            case (byte) 0x82:
            case (byte) 0x83:
            case (byte) 0x84:
            case (byte) 0x85: {
                Arith8Bit.AddAr();
            }break;
            case (byte) 0xC6: {
                Arith8Bit.AddAn();
            }break;
            case (byte) 0x86: {
                Arith8Bit.addAHL();
            }break;
            case (byte) 0x88:
            case (byte) 0x89:
            case (byte) 0x8A:
            case (byte) 0x8B:
            case (byte) 0x8C:
            case (byte) 0x8D:
            case (byte) 0x8F: {
                Arith8Bit.AddAr();
            }break;
            case (byte) 0xCE: {
                Arith8Bit.AddAn();
            }break;
            case (byte) 0x8E: {
                Arith8Bit.addAHL();
            }break;
            case (byte) 0x90:
            case (byte) 0x91:
            case (byte) 0x92:
            case (byte) 0x93:
            case (byte) 0x94:
            case (byte) 0x95:
            case (byte) 0x97: {
                Arith8Bit.subr();
            }break;
            case (byte) 0xD6: {
                Arith8Bit.subn();
            }break;
            case (byte) 0x96: {
                Arith8Bit.subHL();
            }break;
            case (byte) 0x98:
            case (byte) 0x99:
            case (byte) 0x9A:
            case (byte) 0x9B:
            case (byte) 0x9C:
            case (byte) 0x9D:
            case (byte) 0x9F: {
                Arith8Bit.subr();
            }break;
            case (byte) 0xDE: {
                Arith8Bit.subn();
            }break;
            case (byte) 0x9E: {
                Arith8Bit.subHL();
            }break;
            case (byte) 0xA0:
            case (byte) 0xA1:
            case (byte) 0xA2:
            case (byte) 0xA3:
            case (byte) 0xA4:
            case (byte) 0xA5:
            case (byte) 0xA7: {
                Arith8Bit.andr();
            }break;
            case (byte) 0xE6: {
                Arith8Bit.andn();
            }break;
            case (byte) 0xA6: {
                Arith8Bit.andHL();
            }break;
            case (byte) 0xB0:
            case (byte) 0xB1:
            case (byte) 0xB2:
            case (byte) 0xB3:
            case (byte) 0xB4:
            case (byte) 0xB5:
            case (byte) 0xB7: {
                Arith8Bit.orr();
            }break;
            case (byte) 0xF6: {
                Arith8Bit.orn();
            }break;
            case (byte) 0xB6: {
                Arith8Bit.orHL();
            }break;
            case 0x32:{
                LD8Bit.LDnnA(registerState);
            }break;
            case (byte)0xc3: {
                JmpGroup.JPnn();
            }
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
                    }break;
                    case 0x2A: {
                        LD16.LDIX_nn_();
                    }break;
                    case 0x22: {
                        LD16.LD_nn_IX();
                    }break;
                    case 0xF9: {
                        LD16.LDSPIX();
                    }break;
                    case 0xE5: {
                        LD16.pushIX();
                    }break;
                    case 0xE1: {
                        LD16.popIX();
                    }break;
                    case 0xFD: {
                        LD16.popIY();
                    }break;
                    case 0xE3: {
                        ExTransSearchGroup.exSpIX();
                    }break;
                    case 0x86: {
                        Arith8Bit.addIXd();
                    }break;
                    case 0x8E: {
                        Arith8Bit.addIXd();
                    }break;
                    case 0x96: {
                        Arith8Bit.subIXd();
                    }break;
                    case 0x9E: {
                        Arith8Bit.subIXd();
                    }break;
                    case 0xA6: {
                        Arith8Bit.andIXd();
                    }break;
                    case 0xB6: {
                        Arith8Bit.orIXd();
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
                    case 0x21: {
                        LD16.LDIYnn();
                    }break;
                    case 0x2A: {
                        LD16.LDIY_nn_();
                    }break;
                    case 0x22: {
                        LD16.LD_nn_IY();
                    }break;
                    case 0xF9: {
                        LD16.LDSPIY();
                    }break;
                    case 0xE5: {
                        LD16.pushIY();
                    }break;
                    case 0xE3: {
                        ExTransSearchGroup.exSpIY();
                    }break;
                    case 0x86: {
                        Arith8Bit.addIYd();
                    }break;
                    case 0x8E: {
                        Arith8Bit.addIYd();
                    }break;
                    case 0x96: {
                        Arith8Bit.subIYd();
                    }break;
                    case 0x9E: {
                        Arith8Bit.subIYd();
                    }break;
                    case 0xA6: {
                        Arith8Bit.andIYd();
                    }break;
                    case 0xB6: {
                        Arith8Bit.orIYd();
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
                    case 0x4B:
                    case 0x5B:
                    case 0x6B:
                    case 0x7B: {
                        LD16.LDdd_nn_();
                    }break;
                    case 0x43:
                    case 0x53:
                    case 0x63:
                    case 0x73: {
                        LD16.LD_nn_dd();
                    }break;
                    case (byte) 0xA0: {
                        ExTransSearchGroup.ldi();
                    }break;
                    case (byte) 0xB0: {
                        ExTransSearchGroup.ldir();
                    }break;
                    case (byte) 0xA8: {
                        ExTransSearchGroup.ldd();
                    }break;
                    case (byte) 0xB8: {
                        ExTransSearchGroup.lddr();
                    }break;
                    case (byte) 0xA1: {
                        ExTransSearchGroup.cpi();
                    }break;
                    case (byte) 0xB1: {
                        ExTransSearchGroup.cpir();
                    }break;
                    case (byte) 0xA9: {
                        ExTransSearchGroup.cpd();
                    }break;
                    case (byte) 0xB9: {
                        ExTransSearchGroup.cpdr();
                    }
                }
            }break;
            case 0x2A: {
                LD16.HL_nn_();
            }break;
            case 0x22: {
                LD16.LD_nn_hl();
            }break;
            case 0xF9: {
                LD16.LDSPHL();
            }break;
            case 0xEB: {
                ExTransSearchGroup.exDeHl();
            }break;
            case 0x08: {
                ExTransSearchGroup.exAfAf();
            }break;
            case 0xD9: {
                ExTransSearchGroup.exx();
            }break;
            case 0xE3: {
                ExTransSearchGroup.exSpHl();
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
