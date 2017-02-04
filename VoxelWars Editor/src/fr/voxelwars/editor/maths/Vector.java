package fr.voxelwars.editor.maths;

public abstract class Vector {

	public Vector() {}
	
	public abstract float length();
	public abstract Vector normalize();
	public abstract Vector rotation(int angle);
}
