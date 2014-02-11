package z80.core;

import z80.util.RadixOperations;

import java.util.BitSet;

public enum RegisterCodes {

	A("111", "A"),B("000", "B"),C("001", "C"),D("010", "D"),E("011", "E"),H("100", "H"),L("101", "L"),BC("00", "BC"),DE("01", "DE"),HL("10", "HL"),SP("11", "SP");

	private BitSet bitSet;
    private String bitString;
    private String name;

	private RegisterCodes(String code, String name) {
        this.name = name;
        this.bitString = code;
		this.bitSet = new BitSet(3);
		String[] split = code.split("");

		for(int i = 0;i < code.length(); i++) {
            if(code.charAt(i) == '1') {
                bitSet.set(i);
            }
		}
	}

	public BitSet getCodeAsBitSet() {
		return this.bitSet;
	}

	public static RegisterCodes getByCode(BitSet code) {
		for (RegisterCodes c : values()) {
			if (RadixOperations.checkEquals(c.bitSet, code)) {
                return c;
            }
		}

		return null;
	}

    public static RegisterCodes getByCode(String code) {
        for (RegisterCodes c : values()) {
            if (c.bitString.equals(code)) {
                return c;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public String getBitString() {
        return bitString;
    }

    public static String getByName(String name) {
        return RegisterCodes.valueOf(name.toUpperCase()).getBitString();
    }
}
