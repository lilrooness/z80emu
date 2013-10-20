package z80.core;

import java.util.BitSet;

public class RegisterState {
	
	private byte[] a = new byte[2];
	private byte[] f = new byte[2];
	private byte[] bc = new byte[2];
	private byte[] de = new byte[2];
	private byte[] hl = new byte[2];
	private byte[] sp = new byte[2];
	private byte[] ir = new byte[4];
	
	private BitSet psr = new BitSet(8);
	
	byte[] idx = new byte[2];
	
	private static RegisterState _INSTANCE;

	/**
	 * @return RegisterState
	 */
	public static synchronized RegisterState getInstance() {
		if(_INSTANCE ==null) {
			_INSTANCE = new RegisterState();
		}
		return _INSTANCE;
	}
	
	/**
	 * 
	 */
	private RegisterState() {}

	public byte[] getA() {
		return a;
	}

	public void setA(byte[] a) {
		this.a = a;
	}

	public byte[] getF() {
		return f;
	}

	public void setF(byte[] f) {
		this.f = f;
	}

	public byte[] getBc() {
		return bc;
	}

	public void setBc(byte[] bc) {
		this.bc = bc;
	}

	public byte[] getDe() {
		return de;
	}

	public void setDe(byte[] de) {
		this.de = de;
	}

	public byte[] getHl() {
		return hl;
	}

	public void setHl(byte[] hl) {
		this.hl = hl;
	}

	public byte[] getIdx() {
		return idx;
	}

	public void setIdx(byte[] idx) {
		this.idx = idx;
	}
	
	public byte getOpcode() {
		return ir[0];
	}
	
	public byte getOperand() {
		return ir[1];
	}
	
	public byte[] getWholeInstruction() {
		return ir;
	}
	/**
	 * Sets the instruction register
	 * @param instruction
	 */
	public void setIr(byte[] instruction) {
		if(instruction.length >2) {
			throw new IllegalArgumentException("Instruction Too Long");
		} else if(instruction == null || instruction.length == 0) {
			throw new IllegalArgumentException("Instruction Not Provided");
		} else if(instruction.length == 1) {
			instruction = new byte[]{instruction[0],0};
		}
		ir = instruction;
	}
}
