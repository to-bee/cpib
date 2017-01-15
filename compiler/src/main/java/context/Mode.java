package context;

import scanner.datatypes.Terminal;

public class Mode {
	public static Terminal getCurrentMode() {
		return currentMode;
	}

	public static void setCurrentMode(Terminal currentMode) {
		Mode.currentMode = currentMode;
	}

	private static Terminal currentMode;

}
