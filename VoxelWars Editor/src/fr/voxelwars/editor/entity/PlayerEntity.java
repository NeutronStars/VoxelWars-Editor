package fr.voxelwars.editor.entity;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import fr.voxelwars.editor.block.Block;
import fr.voxelwars.editor.input.MouseInput;
import fr.voxelwars.editor.main.Editor;
import fr.voxelwars.editor.maths.Vector3f;
import fr.voxelwars.editor.maths.Vector4f;
import fr.voxelwars.editor.maths.Vector5f;
import fr.voxelwars.editor.render.Rendering;

public class PlayerEntity {

	private Vector5f cam;
	private float xDir, yDir, zDir, speed = 0.05f, xa, ya, za;
	private boolean sprint;
	
	private Block selectBlock;
	private Vector4f colorBlock;
	private Vector3f lastSelectBlockPose;
	
	public PlayerEntity(Vector5f cam) {
		this.cam = cam;
	}
	
	public Vector5f getCamera() {
		return cam;
	}
	
	public void update(){
		cameraUpdate();
		checkLookBlock();
		if(MouseInput.isButtonDown(0) && selectBlock != null)
			Editor.getEditor.getWorldCreator().removeBlock((int)selectBlock.getPosition().getX(), (int)selectBlock.getPosition().getY(), (int)selectBlock.getPosition().getZ());
		
		if(MouseInput.isButtonDown(1) && selectBlock != null && colorBlock != null){
			if(selectBlock.getPosition().getY() == 0.0f && !Editor.getEditor.getWorldCreator().hasBlockSolid((int)selectBlock.getPosition().getX(), (int)selectBlock.getPosition().getY(), (int)selectBlock.getPosition().getZ())){
				Editor.getEditor.getWorldCreator().addBlock((int)selectBlock.getPosition().getX(), (int)selectBlock.getPosition().getY(), (int)selectBlock.getPosition().getZ(), new Block(selectBlock.getPosition(), colorBlock));	
				lastSelectBlockPose = null;
			}else{
				if(lastSelectBlockPose != null){
					Editor.getEditor.getWorldCreator().addBlock((int)lastSelectBlockPose.getX(), (int)lastSelectBlockPose.getY(), (int)lastSelectBlockPose.getZ(), new Block(lastSelectBlockPose, colorBlock));
					lastSelectBlockPose = null;
				}
			}
		}
		
		if(MouseInput.isButtonDown(2) && selectBlock != null && Editor.getEditor.getWorldCreator().hasBlockSolid((int)selectBlock.getPosition().getX(), (int)selectBlock.getPosition().getY(), (int)selectBlock.getPosition().getZ())){
			setBlockPose(new Vector4f(Editor.getEditor.getWorldCreator().getBlock((int)selectBlock.getPosition().getX(), (int)selectBlock.getPosition().getY(), (int)selectBlock.getPosition().getZ()).getColor()));
		}
	}
	
	public Vector4f getColorBlockSelect(){
		return colorBlock;
	}
	
	public void setBlockPose(Vector4f color){
		colorBlock = color;
	}
	
	public void render(){
		if(selectBlock != null) renderSelectBlock();
	}
	
	private void cameraUpdate(){
		cam.removeW(Mouse.getDY()* 0.05F);
		cam.addP(Mouse.getDX()* 0.05F);
		if(cam.getW() < -90.0f) cam.setW(-90.0f);
		if(cam.getW() > 90.0f) cam.setW(90.0f);
		
		if(Keyboard.isKeyDown(Keyboard.KEY_R)){
			if(Mouse.isButtonDown(2) && !sprint){
				speed = 0.15f;
				sprint = true;
			}
			zDir = -speed;
		}else{
			if(sprint){
				speed = 0.05f;
				sprint = false;
			}
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_F)){
			zDir = speed;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			xDir = -speed;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_G)){
			xDir = speed;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			yDir = speed+0.02f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			yDir = -speed;
		}
	
		double rad = Math.toRadians((cam.getP()));
		xa += xDir * Math.cos(rad) - zDir * Math.sin(rad);
		ya += yDir;
		za += zDir * Math.cos(rad) + xDir * Math.sin(rad);
	
		move(xa, ya, za);
	
		xa *= 0.5f;
		ya *= 0.5f;
		za *= 0.5f;
		xDir = 0; yDir = 0; zDir = 0;
	}

	public void move(float x, float y, float z){
		cam.addX(x);			
		cam.addY(y);
		cam.addZ(z);
	}
	
	private Vector3f getDirection(){
		float cy = (float) Math.cos(Math.toRadians(cam.getP() - 90));
		float sy = (float) Math.sin(Math.toRadians(cam.getP() - 90));
		float cp = (float) Math.cos(Math.toRadians(-cam.getW()));
		float sp = (float) Math.sin(Math.toRadians(-cam.getW()));
		return new Vector3f(cy*cp, sp, sy*cp).normalize();
	}
	
	private void checkLookBlock(){
		for(int i = 1; i < 8*10; i++){
			Vector3f dir = getDirection();
			dir.mul(i/10.0f);
			if(Editor.getEditor.getWorldCreator().hasBlockSolid((int)(getCamera().getX()+dir.getX()), (int)(getCamera().getY()+dir.getY()), (int)(getCamera().getZ()+dir.getZ()))){
				selectBlock = new Block(new Vector3f((int)(getCamera().getX()+dir.getX()), (int)(getCamera().getY()+dir.getY()), (int)(getCamera().getZ()+dir.getZ())), new Vector4f());
				return;
			}
			if(getCamera().getY()+dir.getY() <= 0.0f && Editor.getEditor.getWorldCreator().getBlock((int)(getCamera().getX()+dir.getX()), (int)(getCamera().getY()+dir.getY()), (int)(getCamera().getZ()+dir.getZ())) != null){
				selectBlock = new Block(new Vector3f((int)(getCamera().getX()+dir.getX()), 0, (int)(getCamera().getZ()+dir.getZ())), new Vector4f());
				return;
			}
			lastSelectBlockPose = new Vector3f((int)(getCamera().getX()+dir.getX()), (int)(getCamera().getY()+dir.getY()), (int)(getCamera().getZ()+dir.getZ()));
		}
		selectBlock = null;
		lastSelectBlockPose = null;
	}
	
	private void renderSelectBlock(){
		glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
			glLineWidth(2);
			glBegin(GL_QUADS);
			glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
				float x = selectBlock.getPosition().getX();
				float y = selectBlock.getPosition().getY();
				float z = selectBlock.getPosition().getZ();
				Rendering.setUpData(x, y, z, 1.0f, 1.0f, 1.0f);
				Rendering.setDownData(x, y, z, 1.0f, 1.0f, 1.0f);
				Rendering.setSideData(x, y, z, x, y+1.0f, z+1.0f);
				Rendering.setSideData(x+1.0f, y, z, x, y+1.0f, z);
				Rendering.setSideData(x+1.0f, y, z+1.0f, x+1.0f, y+1.0f, z);
				Rendering.setSideData(x, y, z+1.0f, x+1.0f, y+1.0f, z+1.0f);
			glEnd();
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
	}
}
