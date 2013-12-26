package z80.core;

/**
 * Created by Joe on 26/12/2013.
 */
public enum StatusFlagTests {
    NZ("000", StatusFlags.Z), Z("001", StatusFlags.Z), NC("010", StatusFlags.C), C("011", StatusFlags.C), PO("100", StatusFlags.PV), PE("101", StatusFlags.PV), P("110", StatusFlags.S), M("111", StatusFlags.S);

    private String code;

    private StatusFlagTests(String code, StatusFlags flag) {
        this.code = code;
    }
}
