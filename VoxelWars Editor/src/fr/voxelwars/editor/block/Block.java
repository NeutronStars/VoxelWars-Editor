package fr.voxelwars.editor.block;

import org.lwjgl.opengl.GL11;

import fr.voxelwars.editor.main.Editor;
import fr.voxelwars.editor.maths.Vector3f;
import fr.voxelwars.editor.maths.Vector4f;
import fr.voxelwars.editor.render.Rendering;

public class Block {

	private final Vector3f position;
	private final Vector4f color;
	
	public Block(Vector3f position, Vector4f color) {
		this.position = position;
		this.color = color;
	}
	
	public Vector3f getPosition() {
		return position;
	}
	
	public Vector4f getColor() {
		return color;
	}
	
	public void compile(){
		
		Block up = Editor.getEditor.getWorldCreator().getBlock((int)position.getX(), (int)position.getY()+1, (int)position.getZ());
		if(up == null || up instanceof AirBlock){			
			GL11.glColor4f(color.getX()*0.9f, color.getY()*0.9f, color.getZ()*0.9f, color.getW());
			Rendering.setUpData(position.getX(), position.getY(), position.getZ(), 1.0f, 1.0f, 1.0f);
		}
		
		Block down = Editor.getEditor.getWorldCreator().getBlock((int)position.getX(), (int)position.getY()-1, (int)position.getZ());
		if((down == null || down instanceof AirBlock) && position.getY() != 0.0f){
			GL11.glColor4f(color.getX()*0.9f, color.getY()*0.9f, color.getZ()*0.9f, color.getW());
			Rendering.setDownData(position.getX(), position.getY(), position.getZ(), 1.0f, 1.0f, 1.0f);
		}
		
		Block s1 = Editor.getEditor.getWorldCreator().getBlock((int)position.getX()-1, (int)position.getY(), (int)position.getZ());
		if(s1 == null || s1 instanceof AirBlock){
			GL11.glColor4f(color.getX()*0.8f, color.getY()*0.8f, color.getZ()*0.8f, color.getW());
			Rendering.setSideData(position.getX(), position.getY(), position.getZ(), position.getX(), position.getY()+1.0f, position.getZ()+1.0f);
		}
		
		Block s2 = Editor.getEditor.getWorldCreator().getBlock((int)position.getX(), (int)position.getY(), (int)position.getZ()-1);
		if(s2 == null || s2 instanceof AirBlock){
			GL11.glColor4f(color.getX()*0.7f, color.getY()*0.7f, color.getZ()*0.7f, color.getW());
			Rendering.setSideData(position.getX()+1.0f, position.getY(), position.getZ(), position.getX(), position.getY()+1.0f, position.getZ());
		}
		
		Block s3 = Editor.getEditor.getWorldCreator().getBlock((int)position.getX()+1, (int)position.getY(), (int)position.getZ());
		if(s3 == null || s3 instanceof AirBlock){
			GL11.glColor4f(color.getX()*0.8f, color.getY()*0.8f, color.getZ()*0.8f, color.getW());
			Rendering.setSideData(position.getX()+1.0f, position.getY(), position.getZ()+1.0f, position.getX()+1.0f, position.getY()+1.0f, position.getZ());
		}
		
		Block s4 = Editor.getEditor.getWorldCreator().getBlock((int)position.getX(), (int)position.getY(), (int)position.getZ()+1);
		if(s4 == null || s4 instanceof AirBlock){
			GL11.glColor4f(color.getX()*0.7f, color.getY()*0.7f, color.getZ()*0.7f, color.getW());
			Rendering.setSideData(position.getX(), position.getY(), position.getZ()+1.0f, position.getX()+1.0f, position.getY()+1.0f, position.getZ()+1.0f);
		}
	}
}
