package z80.memory;

public class Memory {
	private static Memory _INSTANCE;
	
	byte[] memory = new byte[65000];
	
	public static Memory getInstance() {
		if(_INSTANCE != null) {
			_INSTANCE = new Memory();
		}
		return _INSTANCE;
	}
	
	private Memory(){}
}
