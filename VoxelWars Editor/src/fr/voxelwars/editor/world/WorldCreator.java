package fr.voxelwars.editor.world;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.Display;

import fr.voxelwars.editor.block.AirBlock;
import fr.voxelwars.editor.block.Block;
import fr.voxelwars.editor.render.Rendering;

public class WorldCreator {

	private Chunk chunk;
	private int listChunk, listWorld;
	private boolean isCompile, needCompile = true;
	
	public WorldCreator() {
		listWorld = glGenLists(1);
		glNewList(listWorld, GL_COMPILE);
			glBegin(GL_QUADS);
			glColor4f(0.3f, 0.4f, 0.5f, 1.0f);
			Rendering.setUpData(-10.0f, -0.01f, -10.0f, 36.0f, -0.01f, 36.0f);
			glEnd();
			
			glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
			glLineWidth(1);
			glBegin(GL_QUADS);
			glColor4f(1.0f, 0.0f, 0.0f, 1.0f);
			Rendering.setUpData(0.0f, 0.0f, 0.0f, Chunk.LENGTH_X, Chunk.LENGTH_Y, Chunk.LENGTH_Z);
			Rendering.setSideData(0.0f, 0.0f, 0.0f, 0.0f, 0.0f+Chunk.LENGTH_Y, 0.0f+Chunk.LENGTH_Z);
			Rendering.setSideData(0.0f+Chunk.LENGTH_X, 0.0f, 0.0f, 0.0f, 0.0f+Chunk.LENGTH_Y, 0.0f);
			Rendering.setSideData(0.0f+Chunk.LENGTH_X, 0.0f, 0.0f+Chunk.LENGTH_Z, 0.0f+Chunk.LENGTH_X, 0.0f+Chunk.LENGTH_Y, 0.0f);
			Rendering.setSideData(0.0f, 0.0f, 0.0f+Chunk.LENGTH_Z, 0.0f+Chunk.LENGTH_X, 0.0f+Chunk.LENGTH_Y, 0.0f+Chunk.LENGTH_Z);
			
			
			for(int x = 0; x < (int)Chunk.LENGTH_X; x++){
				for(int z = 0; z < (int)Chunk.LENGTH_Z; z++){
					Rendering.setUpData(x, 0.0f, z, 1.0f, 0.0f, 1.0f);
				}
			}
			glEnd();
			glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);			
		glEndList();
		
	}
	
	public void updateWorld(){
		if(needCompile){
			if(isCompile) glDeleteLists(listChunk, 1);
			else{
				chunk = new Chunk();
				listChunk = glGenLists(1);
				isCompile = true;
			}
		
			glNewList(listChunk, GL_COMPILE);
				glBegin(GL_QUADS);
					glEnable(GL_CULL_FACE);
					glCullFace(GL_BACK);
						chunk.updateRender();
				glEnd();
			glEndList();
		}
		needCompile = false;
	}
	
	public void update(){
		updateWorld();
	}
	
	public void render(){
		glCallList(listWorld);
		glCallList(listChunk);
	}
	
	public void renderFaceWorld(){
		glColor3d(0.0d, 0.0d, 0.0d);
		glRectf(Display.getWidth()/2-4, Display.getHeight()/2-1, Display.getWidth()/2+4, Display.getHeight()/2+1);
		glRectf(Display.getWidth()/2-1, Display.getHeight()/2-4, Display.getWidth()/2+1, Display.getHeight()/2+4);
	}
	
	public boolean hasBlockSolid(int x, int y, int z){
		return chunk.getBlock(x, y, z) != null && !(chunk.getBlock(x, y, z) instanceof AirBlock);
	}
	
	public Block getBlock(int x, int y, int z){
		return chunk.getBlock(x, y, z);
	}
	
	public void addBlock(int x, int y, int z, Block block){
		chunk.addBlock(x, y, z, block);
		needCompile = true;
	}
	
	public void removeBlock(int x, int y, int z){
		chunk.removeBlock(x, y, z);
		needCompile = true;
	}
}
