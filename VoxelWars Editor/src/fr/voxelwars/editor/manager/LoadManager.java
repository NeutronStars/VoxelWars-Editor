package fr.voxelwars.editor.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import fr.voxelwars.editor.block.Block;
import fr.voxelwars.editor.main.Editor;
import fr.voxelwars.editor.maths.Vector3f;
import fr.voxelwars.editor.maths.Vector4f;
import fr.voxelwars.editor.world.Chunk;

public class LoadManager {

	public static void Load(String name){
		if(!new File(name+".ns").exists()) return;
		
		String save = null;
		try(FileInputStream is = new FileInputStream(name+".ns");
			ObjectInputStream ois = new ObjectInputStream(is)){
			save = (String) ois.readObject();
		}catch(Exception e){e.printStackTrace();}
	
		if(save == null) return;
		
		for(int x = 0; x < Chunk.LENGTH_X; x++){
			for(int y = 0; y < Chunk.LENGTH_X; y++){
				for(int z = 0; z < Chunk.LENGTH_X; z++){
					Editor.getEditor.getWorldCreator().removeBlock(x, y, z);
				}
			}
		}
		
		String[] blocksData = save.split("=");
		for(String data : blocksData){
			String[] parsData = data.split(" ");
		
			if(!parsData[0].equalsIgnoreCase("AIR")){
				int px = Integer.parseInt(parsData[1]);
				int py = Integer.parseInt(parsData[2]);
				int pz = Integer.parseInt(parsData[3]);
				
				float cx = Float.parseFloat(parsData[4]);
				float cy = Float.parseFloat(parsData[5]);
				float cz = Float.parseFloat(parsData[6]);
				Editor.getEditor.getWorldCreator().addBlock(px, py, pz, new Block(new Vector3f(px, py, pz), new Vector4f(cx, cy, cz, 1.0f)));
			}
		}
	}
}
