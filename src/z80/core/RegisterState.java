package z80.core;

import java.util.BitSet;

import z80.memory.Memory;

public class RegisterState {
	
	/**
	 * Thease registers can be used in 8 bit or as 16 bit pairs
	 */
	private byte[] a = new byte[2];
	private byte[] f = new byte[2];
	private byte[] bc = new byte[2];
	private byte[] de = new byte[2];
	private byte[] hl = new byte[2];
	private byte currentOpcodeByte;
	/**
	 * always 16 bit registers
	 */
	private short pc, ix, iy, sp;
	
	private BitSet psr = new BitSet(8);
	
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

	public byte getCurrentWord8() {
		return currentOpcodeByte;
	}
	
	public byte fetchWord8() {
		return Memory.memory[pc++];
	}
	
	public byte[] fetchWord16() {
		return new byte[] {Memory.memory[pc++], Memory.memory[pc++]};
	}
	
	public short getIX() {
		return ix;
	}
	
	public void setIX(short value) {
		ix = value;
	}
	
	public short getIY() {
		return iy;
	}
	
	public void setIY(short value) {
		iy = value;
	}
	
		
}
