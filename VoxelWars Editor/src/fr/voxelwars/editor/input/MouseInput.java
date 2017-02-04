package fr.voxelwars.editor.input;

import org.lwjgl.input.Mouse;

public class MouseInput {

	private static final boolean[] MOUSE_PRESSED = new boolean[Mouse.getButtonCount()];
	
	public static boolean isButtonDown(int button){
		if(Mouse.isButtonDown(button) && !MOUSE_PRESSED[button]){
			MOUSE_PRESSED[button] = true;
			return true;
		}
		return false;
	}
	
	public static void updateMouseInput(){
		for(int i = 0; i < MOUSE_PRESSED.length; i++){
			if(MOUSE_PRESSED[i]) MOUSE_PRESSED[i] = Mouse.isButtonDown(i);
		}
	}
}
