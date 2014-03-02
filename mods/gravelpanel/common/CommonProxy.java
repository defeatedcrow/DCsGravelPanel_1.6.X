package mods.gravelpanel.common;

import net.minecraft.world.World;


public class CommonProxy {
	
	public int getRenderID()
	{
		return -1;
	}
	
	public void registerRenderers(){}

	public World getClientWorld() {
		
		return null;
	}

}
