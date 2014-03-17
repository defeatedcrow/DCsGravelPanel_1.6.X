package mods.gravelpanel.common;

import static net.minecraftforge.common.ForgeDirection.DOWN;
import static net.minecraftforge.common.ForgeDirection.UP;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.src.*;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import mods.gravelpanel.common.*;

public class BlockGravelPanel extends Block{
	
	public BlockGravelPanel (int blockid)
	{
		super(blockid, Material.ground);
		this.setHardness(0.5F);
		this.setResistance(2.0F);
		this.setStepSound(Block.soundGravelFootstep);
	}
	
	@Override
	public int idDropped(int metadata, Random rand, int fortune)
	{
		return this.blockID;
	}
	
	public boolean isOpaqueCube()
	{
		return false;
	}
 
	public boolean renderAsNormalBlock() 
	{
		return false;
	}
	
	public boolean getBlocksMovement(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return false;
    }
	
	public boolean canPlaceTorchOnTop(World par1World, int par2, int par3, int par4)
	{
		return true;
	}
	
	public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side)
    {
        int meta = world.getBlockMetadata(x, y, z);
        return ((meta & 4) == 0) ? false : ((side == UP || side == DOWN) ? true : false);
    }
	
	@Override
	public int getRenderType()
	{
		return DCsGravelPanel.modelGravelPanel;
	}
	
	public void addCollisionBoxesToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity)
    {
		boolean flag = (par1World.getBlockMetadata(par2, par3, par4) & 4) != 0;
		
		if (flag)
        {
			this.setBlockBounds(0.0F, 0.75F, 0.0F, 1.0F, 1.0F, 1.0F);
            super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
        }
		else
		{
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
            super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
		}
    }
	
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
		this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
        return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
    }
	
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
		boolean flag = (par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 4) != 0;
		if (flag) {
			this.setBlockBounds(0.0F, 0.75F, 0.0F, 1.0F, 1.0F, 1.0F);
		}
		else {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
		}
    }
	
	public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
    {
        return (par5 != 0 && (par5 == 1 || (double)par7 <= 0.5D) ? (par9 & 3) : (par9 | 4));
    }
	
	public int getDamageValue(World par1World, int par2, int par3, int par4)
    {
        return super.getDamageValue(par1World, par2, par3, par4) & 3;
    }
	
	public int damageDropped(int par1)
    {
        return par1 & 3;
    }
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
    }
	
	@SideOnly(Side.CLIENT)
    public Icon getIcon(int par1, int par2)
    {
		int i = par2 & 3;
		
		if (i == 0){ return Block.gravel.getBlockTextureFromSide(1);}
    	else if (i == 1){ return Block.sand.getBlockTextureFromSide(par1);}
    	else if (i == 2){ return Block.dirt.getBlockTextureFromSide(1);}
    	else { return Block.snow.getBlockTextureFromSide(1);}
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = Block.gravel.getBlockTextureFromSide(1);
		
	}

}
