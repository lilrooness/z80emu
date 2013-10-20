package z80.instructions;

import java.util.BitSet;

import z80.core.RegisterCodes;
import z80.core.RegisterState;

public abstract class AbstractRegisterInstruction implements Instruction {

	protected static BitSet getFirstRegisterBits(BitSet opcode) {
		return opcode.get(2, 4);
	}
	
	protected static BitSet getSecondRegisterBits(BitSet opcode) {
		return opcode.get(5, 8);
	}
	
	protected byte[] getRegisterValueByCode(BitSet code, RegisterState state) {

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
	
	protected void setRegisterValue(RegisterState registerState, RegisterCodes c, byte[] value) {
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
}
