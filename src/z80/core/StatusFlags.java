package z80.core;

public enum StatusFlags {
	C(0),N(1),PV(2),H(3),Z(4),S(5),X(6),UN(7);

    private int position;

    private StatusFlags(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
