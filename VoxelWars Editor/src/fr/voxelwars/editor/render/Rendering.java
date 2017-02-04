package fr.voxelwars.editor.render;

import static org.lwjgl.opengl.GL11.glVertex3f;

public final class Rendering{
	
	public static void setUpData(float x, float y, float z, float lx, float ly, float lz){
		glVertex3f(x+lx, y+ly, z);
		glVertex3f(x, y+ly, z);
		glVertex3f(x, y+ly, z+lz);
		glVertex3f(x+lx, y+ly, z+lz);
	}
	
	public static void setDownData(float x, float y, float z, float lx, float ly, float lz){
		glVertex3f(x, y, z);
		glVertex3f(x+lx, y, z);
		glVertex3f(x+lx, y, z+lz);
		glVertex3f(x, y, z+lz);
	}
	
	public static void setSideData(float x0, float y0, float z0, float x1, float y1, float z1){
		glVertex3f(x0, y0, z0);
		glVertex3f(x1, y0, z1);
		glVertex3f(x1, y1, z1);
		glVertex3f(x0, y1, z0);
	}
}
