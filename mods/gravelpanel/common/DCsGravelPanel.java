package mods.gravelpanel.common;

import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(
		modid = "DCsGravelPanel",
		name = "GravelPanels",
		version = "1.6.2_1.0a",
		dependencies = "required-after:Forge@[9.10,);required-after:FML@[6.2.0,)"
		)
@NetworkMod(
		clientSideRequired = true,
		serverSideRequired = false
		)

public class DCsGravelPanel{
	
	@SidedProxy(clientSide = "mods.gravelpanel.client.ClientProxy", serverSide = "mods.gravelpanel.common.CommonProxy")
	public static CommonProxy proxy;
	
	@Instance("DCsGravelPanel")
    public static DCsGravelPanel instance;
	
	public static Block  gravelPanel;
	
	public int blockIdGPanel = 670;
	public static int modelGravelPanel;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		try
		{
			cfg.load();
			Property blockGPanel = cfg.getBlock("GravelPanel", blockIdGPanel);
			
			blockIdGPanel = blockGPanel.getInt();

		}
		catch (Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "Error Message");

		}
		finally
		{
			cfg.save();
		}
		
		gravelPanel = (new BlockGravelPanel(blockIdGPanel)).
				setUnlocalizedName("defeatedcrow.gravelPanel").
				setCreativeTab(CreativeTabs.tabDecorations);
		
		
		GameRegistry.registerBlock(gravelPanel, ItemGravelPanel.class, "defeatedcrow.gravelPanel");
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		this.modelGravelPanel = proxy.getRenderID();
		proxy.registerRenderers();
		
		//Registering new recipe
		GameRegistry.addRecipe(
	    		  new ItemStack(this.gravelPanel, 16, 0),
	    		  new Object[]{"XXX","YYY",
	    			  Character.valueOf('X'), Block.gravel,
	    			  Character.valueOf('Y'), Block.cobblestone
	    			  });
		
		GameRegistry.addRecipe(
	    		  new ItemStack(this.gravelPanel, 16, 1),
	    		  new Object[]{"XXX","YYY",
	    			  Character.valueOf('X'), Block.sand,
	    			  Character.valueOf('Y'), Block.cobblestone
	    			  });
		
		GameRegistry.addRecipe(
	    		  new ItemStack(this.gravelPanel, 16, 2),
	    		  new Object[]{"XXX","YYY",
	    			  Character.valueOf('X'), Block.dirt,
	    			  Character.valueOf('Y'), Block.cobblestone
	    			  });
		
		GameRegistry.addRecipe(
	    		  new ItemStack(this.gravelPanel, 16, 3),
	    		  new Object[]{"XXX","YYY",
	    			  Character.valueOf('X'), Block.blockSnow,
	    			  Character.valueOf('Y'), Block.cobblestone
	    			  });
		
	    //Registering language
		LanguageRegistry.addName(new ItemStack(this.gravelPanel, 1, 0), "Gravel Panel");
		LanguageRegistry.instance().addNameForObject(new ItemStack(this.gravelPanel, 1, 0), "ja_JP", "敷砂利パネル");
		LanguageRegistry.addName(new ItemStack(this.gravelPanel, 1, 1), "Sand Panel");
		LanguageRegistry.instance().addNameForObject(new ItemStack(this.gravelPanel, 1, 1), "ja_JP", "敷砂パネル");
		LanguageRegistry.addName(new ItemStack(this.gravelPanel, 1, 2), "Dirt Panel");
		LanguageRegistry.instance().addNameForObject(new ItemStack(this.gravelPanel, 1, 2), "ja_JP", "敷土パネル");
		LanguageRegistry.addName(new ItemStack(this.gravelPanel, 1, 3), "Snow Panel");
		LanguageRegistry.instance().addNameForObject(new ItemStack(this.gravelPanel, 1, 3), "ja_JP", "敷雪パネル");
	}
	
}
