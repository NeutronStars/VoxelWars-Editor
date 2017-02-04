package fr.voxelwars.editor.input;

import java.util.Scanner;

import fr.voxelwars.editor.main.Editor;
import fr.voxelwars.editor.manager.LoadManager;
import fr.voxelwars.editor.manager.SaveManager;
import fr.voxelwars.editor.maths.Vector4f;

public class ConsoleSender extends Thread{

	private final Scanner scan = new Scanner(System.in);
	
	public void run() {
		while(true){
			String msg = scan.nextLine();
			
			String[] cmd = msg.split(" ");
			if(cmd.length == 2){
				if(cmd[0].equalsIgnoreCase("save")){
					SaveManager.save(cmd[1]);
				}
				if(cmd[0].equalsIgnoreCase("load")){
					LoadManager.Load(cmd[1]);
				}
			}
			
			if(cmd.length == 5){
				if(cmd[0].equalsIgnoreCase("color")){
					try{
						int r = Integer.parseInt(cmd[1]);
						int g = Integer.parseInt(cmd[2]);
						int b = Integer.parseInt(cmd[3]);
						int a = Integer.parseInt(cmd[4]);
						
						Editor.getEditor.getPlayer().setBlockPose(new Vector4f((float)r/255.0f, (float)g/255.0f, (float)b/255.0f, (float)a/255.0f));
					}catch(NumberFormatException e){}
				}
			}
		}
	}
}
