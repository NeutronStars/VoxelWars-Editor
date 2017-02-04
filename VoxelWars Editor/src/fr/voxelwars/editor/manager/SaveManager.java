package fr.voxelwars.editor.manager;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import fr.voxelwars.editor.block.AirBlock;
import fr.voxelwars.editor.block.Block;
import fr.voxelwars.editor.main.Editor;
import fr.voxelwars.editor.world.Chunk;

public class SaveManager {
	
	public static void save(String name){
		String save = "";
	
		for(int x = 0; x < Chunk.LENGTH_X; x++){
			for(int y = 0; y < Chunk.LENGTH_X; y++){
				for(int z = 0; z < Chunk.LENGTH_X; z++){
					Block b = Editor.getEditor.getWorldCreator().getBlock(x, y, z);
					if(b instanceof AirBlock)
						save+="AIR "+x+" "+y+" "+z+"=";
					else
						save+="SOLIDE "+x+" "+y+" "+z+" "+b.getColor().getX()+" "+b.getColor().getY()+" "+b.getColor().getZ()+"=";
				}
			}
		}
		try(OutputStream os = new FileOutputStream(name+".ns");
			ObjectOutputStream oos = new ObjectOutputStream(os)){
			oos.writeObject(save);
		}catch(Exception e){ e.printStackTrace();}
	}
}
