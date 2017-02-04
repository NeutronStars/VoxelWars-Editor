package fr.voxelwars.editor.main;

import static org.lwjgl.opengl.GL11.*;

import java.io.File;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;

import fr.voxelwars.editor.entity.PlayerEntity;
import fr.voxelwars.editor.input.ConsoleSender;
import fr.voxelwars.editor.input.MouseInput;
import fr.voxelwars.editor.maths.Vector4f;
import fr.voxelwars.editor.maths.Vector5f;
import fr.voxelwars.editor.world.WorldCreator;

public class Editor {

	public static Editor getEditor;
	
	private int fps, tps, lfps, ltps;
	private PlayerEntity player = new PlayerEntity(new Vector5f(8.0f, 8.0f, 8.0f, 0.0f, 0.0f));
	private WorldCreator creator;
	
	public Editor() {
		if(getEditor != null) return;
		getEditor = this;
		displayInit();
		openGlInit();
		editorInit();
		run();
	}
	
	private void displayInit(){
		try{
			Display.setTitle("Editor Alpha");
			Display.setDisplayMode(new DisplayMode(720, 480));
			Display.setResizable(true);
			Display.create();
		}catch(LWJGLException e){
			e.printStackTrace();
		}
	}
	
	private void openGlInit(){
		glEnable(GL_DEPTH_TEST);
		glClearColor(0.2f, 0.7f, 0.7f, 1.0f);
	}
	
	private void editorInit(){
		creator = new WorldCreator();
		creator.updateWorld();
		player.setBlockPose(new Vector4f(0.0f, 0.0f, 0.0f, 1.0f));
		new ConsoleSender().start();
	}
	
	private void update(){
		tps++;
		if(Mouse.isButtonDown(0) && !Mouse.isGrabbed()){
			Mouse.setGrabbed(true);
			MouseInput.isButtonDown(0);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && Mouse.isGrabbed()) Mouse.setGrabbed(false);
		
		if(!Mouse.isGrabbed()) return;
		
		creator.update();
		player.update();
		MouseInput.updateMouseInput();
	}
		
	private void render(){
		if(Display.wasResized()) glViewport(0, 0, Display.getWidth(), Display.getHeight());
		
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT | GL_STENCIL_BUFFER_BIT);
		
		glLoadIdentity();
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glMatrixMode(GL_PROJECTION);
		GLU.gluPerspective(70.0f, (float) Display.getWidth() / (float) Display.getHeight(), 0.01f, 250.0f);
		
		
		glPopMatrix();
		glPushAttrib(GL_TRANSFORM_BIT);
			glRotatef(player.getCamera().getW(), 1, 0, 0);
			glRotatef(player.getCamera().getP(), 0, 1, 0);
			glTranslatef(-player.getCamera().getX(), -player.getCamera().getY(), -player.getCamera().getZ());
			
			creator.render();
			player.render();
		glPopAttrib();
		glPopMatrix();
		
		renderFace();
		Display.update();
		fps++;
	}
	
	public void renderFace(){
		glLoadIdentity();
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glMatrixMode(GL_PROJECTION);
		GLU.gluOrtho2D(0, Display.getWidth(), Display.getHeight(), 0);
		
		creator.renderFaceWorld();
	}
	
	public void run(){
		long lns = System.nanoTime();
		double ns = 1000000000.0/60.0;		
		long ls = System.currentTimeMillis();
		
		while (!Display.isCloseRequested()) {
			if(System.nanoTime() - lns > ns){
				lns+=ns;
				update();
			}else render();
			
			if(System.currentTimeMillis() - ls >= 1000){
				ls = System.currentTimeMillis();
				lfps = fps; ltps = tps;
				fps = 0; tps = 0;
				Display.setTitle("Editor Alpha | FPS : "+lfps+" | TPS : "+ltps);
			}
		}
		
		Display.destroy();
		System.exit(0);
	}
	
	public WorldCreator getWorldCreator() {
		return creator;
	}
	
	public PlayerEntity getPlayer(){
		return player;
	}
	
	public static void main(String[] args) {
		System.setProperty("org.lwjgl.librarypath", new File("native/"+(System.getProperties().getProperty("os.name").split(" ")[0]).toLowerCase()).getAbsolutePath());	
		new Editor();
	}
}
