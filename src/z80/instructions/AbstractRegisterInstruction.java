package z80.instructions;

import java.util.BitSet;

import z80.core.RegisterCodes;
import z80.core.RegisterState;
import z80.core.StatusFlagTests;

public abstract class AbstractRegisterInstruction implements Instruction {

	protected static BitSet getFirstRegisterBits(BitSet opcode) {
		return opcode.get(2, 4);
	}
	
	protected static BitSet getSecondRegisterBits(BitSet opcode) {
		return opcode.get(5, 8);
	}
	
	public static byte[] getRegisterValueByCode(BitSet code, RegisterState state) {

		RegisterCodes c = RegisterCodes.getByCode(code);
		if (c != null) {
			switch (c) {
			case A:
				return new byte[] { state.getA()[0] };
			case B:
				return new byte[] { state.getBc()[0] };
			case C:
				return new byte[] { state.getBc()[1] };
			case D:
				return new byte[] { state.getDe()[0] };
			case E:
				return new byte[] { state.getDe()[1] };
			case H:
				return new byte[] { state.getHl()[0] };
			case L:
				return new byte[] { state.getHl()[1] };
			}
		}
		throw new IllegalArgumentException("Register Does Not Exist");
	}

    public static byte[] getRegisterValueByCode(String code, RegisterState state) {

        RegisterCodes c = RegisterCodes.getByCode(code);
        if (c != null) {
            switch (c) {
                case A:
                    return new byte[] { state.getA()[0] };
                case B:
                    return new byte[] { state.getBc()[0] };
                case C:
                    return new byte[] { state.getBc()[1] };
                case D:
                    return new byte[] { state.getDe()[0] };
                case E:
                    return new byte[] { state.getDe()[1] };
                case H:
                    return new byte[] { state.getHl()[0] };
                case L:
                    return new byte[] { state.getHl()[1] };
            }
        }
        throw new IllegalArgumentException("Register Does Not Exist");
    }
	
	public static void setRegisterValue(RegisterState registerState, RegisterCodes c, byte[] value) {
		if(c != null) {
			switch(c) {
			case A:{
				registerState.setA(value);
			}break;
			case B:{
				registerState.setBc(new byte[]{value[0], registerState.getBc()[1]});
			}break;
			case C:{
				registerState.setBc(new byte[]{registerState.getBc()[0], value[0]});
			}break;
			case D:{
				registerState.setDe(new byte[]{value[0], registerState.getDe()[1]});
			}break;
			case E:{
				registerState.setDe(new byte[]{registerState.getDe()[0], value[0]});
			}break;
			case H:{
				registerState.setHl(new byte[]{value[0], registerState.getHl()[1]});
			}break;
			case L:{
				registerState.setHl(new byte[]{registerState.getHl()[0], value[0]});
			}
			default:{
				throw new IllegalArgumentException("Register Does Not Exist");
			}
			}
		}
	}

    public static byte getRegisterValue(RegisterState registerState, RegisterCodes c) {
        if(c != null) {
            switch(c) {
                case A:{
//                    registerState.setA(value);
                    return registerState.getA()[0];
                }
                case B:{
//                    registerState.setBc(new byte[]{value[0], registerState.getBc()[1]});
                    return registerState.getBc()[0];
                }
                case C:{
//                    registerState.setBc(new byte[]{registerState.getBc()[0], value[0]});
                    return registerState.getBc()[1];
                }
                case D:{
//                    registerState.setDe(new byte[]{value[0], registerState.getDe()[1]});
                    return registerState.getDe()[0];
                }
                case E:{
//                    registerState.setDe(new byte[]{registerState.getDe()[0], value[0]});
                    return registerState.getDe()[1];
                }
                case H:{
//                    registerState.setHl(new byte[]{value[0], registerState.getHl()[1]});
                    return registerState.getHl()[0];
                }
                case L:{
//                    registerState.setHl(new byte[]{registerState.getHl()[0], value[0]});
                    return registerState.getHl()[1];
                }
            }
        }
        return -1;
    }


    public static byte[] get16BitRegisterValue(RegisterState registerState, RegisterCodes c) {
        if(c !=  null) {
            switch (c) {
                case HL: {
                    return registerState.getHl();
                }
                case DE: {
                    return registerState.getDe();
                }
                case BC: {
                    return registerState.getBc();
                }
            }
        }
        return null;
    }

    public static void set16BitRegisterValue(RegisterState registerState, RegisterCodes c, byte[] value) {
        if(c !=  null) {
            switch (c) {
                case HL: {
                    registerState.setHl(value);
                }
                case DE: {
                    registerState.setDe(value);
                }
                case BC: {
                    registerState.setBc(value);
                }
            }
        }
    }

    public static boolean isSPCode(RegisterCodes c) {
        if(c == RegisterCodes.SP) {
            return true;
        } else {
            return false;
        }
    }

    public static StatusFlagTests getFlagTest(String code) {
        if(code.equals(StatusFlagTests.C.getCode())) {
            return StatusFlagTests.C;
        } else if(code.equals(StatusFlagTests.M.getCode())) {
            return StatusFlagTests.M;
        } else if(code.equals(StatusFlagTests.NC.getCode())) {
            return StatusFlagTests.NC;
        } else if(code.equals(StatusFlagTests.NZ.getCode())) {
            return StatusFlagTests.NZ;
        } else if(code.equals(StatusFlagTests.P.getCode())) {
            return StatusFlagTests.P;
        } else if(code.equals(StatusFlagTests.PE.getCode())) {
            return StatusFlagTests.PE;
        } else if(code.equals(StatusFlagTests.PO)) {
            return StatusFlagTests.PO;
        } else if(code.equals(StatusFlagTests.Z)) {
            return StatusFlagTests.Z;
        }else {
            return null;
        }
    }

}
