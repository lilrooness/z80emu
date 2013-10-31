package z80.core;

import z80.util.RadixOperations;

import java.util.BitSet;

public enum RegisterCodes {

	A("111"),B("000"),C("001"),D("010"),E("011"),H("100"),L("101"),BC("00"),DE("01"),HL("10"),SP("11");

	private BitSet bitSet;

	private RegisterCodes(String code) {
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
			if (RadixOperations.checkEquals(c.getCodeAsBitSet(),code)) {
                return c;
            }
		}

		return null;
	}
}
