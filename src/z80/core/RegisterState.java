package z80.core;

import java.util.BitSet;

import z80.memory.Memory;

public class RegisterState {

    private boolean runningProgram;
	
	/**
	 * Thease registers can be used in 8 bit or as 16 bit pairs
	 */
	private byte[] a = new byte[2];
	private byte[] f = new byte[2];
	private byte[] bc = new byte[2];
	private byte[] de = new byte[2];
	private byte[] hl = new byte[2];
	private byte currentOpcodeByte;
    private byte i, r;
    private boolean IFF2, IFF1;

	/**
	 * always 16 bit registers
	 */
	private short pc, ix, iy, sp;
	
	public static BitSet psr = new BitSet(8);
	
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

    public void dump() {
        pc = 0;
        ix = 0;
        iy = 0;
        sp = 0;

        i = 0;
        r = 0;

        IFF2 = false;
        IFF1 = false;

        a = new byte[] {0, 0};
        f = new byte[] {0, 0};
        hl = new byte[] {0, 0};
        de = new byte[] {0, 0};
        bc = new byte[] {0, 0};
    }

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
        this.currentOpcodeByte = Memory.memory[pc];
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

    public byte getI() {
        return i;
    }

    public byte getR() {
        return r;
    }

    public void setI(byte i) {
        this.i = i;
    }

    public void setR(byte r) {
        this.r = r;
    }

    public boolean isIFF2() {
        return IFF2;
    }

    public void setIFF2(boolean IFF2) {
        this.IFF2 = IFF2;
    }

    public boolean isIFF1() {
        return IFF1;
    }

    public void setIFF1(boolean IFF1) {
        this.IFF1 = IFF1;
    }

    public short getPc() {
        return pc;
    }

    public void setPc(short pc) {
        this.pc = pc;
    }

    public boolean isRunningProgram() {
        return runningProgram;
    }

    public void setRunningProgram(boolean runningProgram) {
        this.runningProgram = runningProgram;
    }

    public short getSp() {
        return sp;
    }

    public void setSp(short sp) {
        this.sp = sp;
    }
}
