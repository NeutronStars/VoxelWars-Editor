package fr.voxelwars.editor.world;

import fr.voxelwars.editor.block.AirBlock;
import fr.voxelwars.editor.block.Block;

public class Chunk {

	public final static int LENGTH_X = 16, LENGTH_Y = 16, LENGTH_Z = 16;
	
	private Block[][][] blocks = new Block[LENGTH_X][LENGTH_Y][LENGTH_Z];
	private final static Block AIR = new AirBlock();
	
	public Chunk(){
		generateChunk();
	}
	
	private void generateChunk(){
		for(int x = 0; x < LENGTH_X; x++){
			for(int y = 0; y < LENGTH_Y; y++){
				for(int z = 0; z < LENGTH_Z; z++){
					blocks[x][y][z] = AIR;
				}
			}
		}
	}
	
	public Block getBlock(int x, int y, int z){
		return (x < 0 || y < 0 || z < 0 || x >= LENGTH_X || y >= LENGTH_Y || z >= LENGTH_Z) ? null : blocks[x][y][z];
	}
	
	public void addBlock(int x, int y, int z, Block block){
		if (x < 0 || y < 0 || z < 0 || x >= LENGTH_X || y >= LENGTH_Y || z >= LENGTH_Z) return;
		blocks[x][y][z] = block;
	}
	
	public void removeBlock(int x, int y, int z){
		if (x < 0 || y < 0 || z < 0 || x >= LENGTH_X || y >= LENGTH_Y || z >= LENGTH_Z) return;
		blocks[x][y][z] = AIR;
	}
	
	public void updateRender(){
		for(int x = 0; x < LENGTH_X; x++){
			for(int y = 0; y < LENGTH_Y; y++){
				for(int z = 0; z < LENGTH_Z; z++){
					if(blocks[x][y][z] != null) blocks[x][y][z].compile();
				}
			}
		}
	}
}
