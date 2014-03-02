package mods.gravelpanel.common;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemGravelPanel extends ItemBlock{
	
	private static final String[] SlabType = new String[] {"_gravel", "_sand", "_wood", "_snow"};
	
	public ItemGravelPanel(int itemId)
	{
		super(itemId);
		setMaxDamage(0);
		setHasSubtypes(true);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		int i = par1ItemStack.getItemDamage() & 3;
		return super.getUnlocalizedName() + SlabType[i];
	}
	
	@Override
	public int getMetadata(int par1)
	{
		return par1 & 3;
	}

}
