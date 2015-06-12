/*******************************************************************************
 * Copyright 2015 SteveKunG - Steve's Core
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.stevecore;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RegisterHelper
{
	static int id = 0;

	public static void registerBlock(Block block)
	{
		if (Loader.isModLoaded("GalacticraftCore"))
		{
			if (Loader.isModLoaded("MorePlanets"))
			{
				try
				{
					Class<?> clazz = Class.forName("micdoodle8.mods.galacticraft.core.items.ItemBlockGC");
					GameRegistry.registerBlock(block, (Class)clazz, block.getUnlocalizedName().substring(5));
				}
				catch (ClassNotFoundException e)
				{
					GameRegistry.registerBlock(block, ItemBlock.class, block.getUnlocalizedName().substring(5));
				}
			}
			else
			{
				GameRegistry.registerBlock(block, ItemBlock.class, block.getUnlocalizedName().substring(5));
			}
		}
		else
		{
			GameRegistry.registerBlock(block, ItemBlock.class, block.getUnlocalizedName().substring(5));
		}
	}

	public static void registerBlock(Block block, Class<? extends ItemBlock> itemBlock)
	{
		GameRegistry.registerBlock(block, itemBlock, block.getUnlocalizedName().substring(5));
	}

	public static void registerBlock(Block block, Class<? extends ItemBlock> itemBlock, Object... objectFromItemBlock)
	{
		GameRegistry.registerBlock(block, itemBlock, block.getUnlocalizedName().substring(5), objectFromItemBlock);
	}

	public static void registerFluid(Fluid fluid)
	{
		FluidRegistry.registerFluid(fluid);
	}

	public static void registerItem(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
	}

	public static void registerFluidContainer(Fluid fluid, ItemStack filledContainer, ItemStack emptyContainer)
	{
		FluidContainerRegistry.registerFluidContainer(new FluidContainerData(new FluidStack(fluid, FluidContainerRegistry.BUCKET_VOLUME), filledContainer, emptyContainer));
	}

	public static void setFireBurn(Block block, int encouragement, int flammibility)
	{
		Blocks.fire.setFireInfo(block, encouragement, flammibility);
	}

	public static void registerNonMobEntity(Class<? extends Entity> entity, String name, Object mod, boolean sendVelocityUpdate)
	{
		EntityRegistry.registerModEntity(entity, name, id++, mod, 256, 1, sendVelocityUpdate);
	}

	public static void registerVariantName(Item item, String[] variant)
	{
		ModelBakery.addVariantName(item, variant);
	}

	public static void registerVariantName(Block block, String[] variant)
	{
		RegisterHelper.registerVariantName(Item.getItemFromBlock(block), variant);
	}

	public static void registerVariantNameWithDyeColor(Block block, String folder)
	{
		RegisterHelper.registerVariantName(block, "white_" + getBlockName(block), folder);
		RegisterHelper.registerVariantName(block, "orange_" + getBlockName(block), folder);
		RegisterHelper.registerVariantName(block, "magenta_" + getBlockName(block), folder);
		RegisterHelper.registerVariantName(block, "light_blue_" + getBlockName(block), folder);
		RegisterHelper.registerVariantName(block, "yellow_" + getBlockName(block), folder);
		RegisterHelper.registerVariantName(block, "lime_" + getBlockName(block), folder);
		RegisterHelper.registerVariantName(block, "pink_" + getBlockName(block), folder);
		RegisterHelper.registerVariantName(block, "gray_" + getBlockName(block), folder);
		RegisterHelper.registerVariantName(block, "silver_" + getBlockName(block), folder);
		RegisterHelper.registerVariantName(block, "cyan_" + getBlockName(block), folder);
		RegisterHelper.registerVariantName(block, "purple_" + getBlockName(block), folder);
		RegisterHelper.registerVariantName(block, "blue_" + getBlockName(block), folder);
		RegisterHelper.registerVariantName(block, "brown_" + getBlockName(block), folder);
		RegisterHelper.registerVariantName(block, "green_" + getBlockName(block), folder);
		RegisterHelper.registerVariantName(block, "red_" + getBlockName(block), folder);
		RegisterHelper.registerVariantName(block, "black_" + getBlockName(block), folder);
	}

	public static void registerVariantNameWithDyeColor(Item item, String folder)
	{
		RegisterHelper.registerVariantName(item, "white_" + getItemName(item), folder);
		RegisterHelper.registerVariantName(item, "orange_" + getItemName(item), folder);
		RegisterHelper.registerVariantName(item, "magenta_" + getItemName(item), folder);
		RegisterHelper.registerVariantName(item, "light_blue_" + getItemName(item), folder);
		RegisterHelper.registerVariantName(item, "yellow_" + getItemName(item), folder);
		RegisterHelper.registerVariantName(item, "lime_" + getItemName(item), folder);
		RegisterHelper.registerVariantName(item, "pink_" + getItemName(item), folder);
		RegisterHelper.registerVariantName(item, "gray_" + getItemName(item), folder);
		RegisterHelper.registerVariantName(item, "silver_" + getItemName(item), folder);
		RegisterHelper.registerVariantName(item, "cyan_" + getItemName(item), folder);
		RegisterHelper.registerVariantName(item, "purple_" + getItemName(item), folder);
		RegisterHelper.registerVariantName(item, "blue_" + getItemName(item), folder);
		RegisterHelper.registerVariantName(item, "brown_" + getItemName(item), folder);
		RegisterHelper.registerVariantName(item, "green_" + getItemName(item), folder);
		RegisterHelper.registerVariantName(item, "red_" + getItemName(item), folder);
		RegisterHelper.registerVariantName(item, "black_" + getItemName(item), folder);
	}

	public static void registerModelRender(Block block, int meta, String variantName, String folder)
	{
		RegisterHelper.registerModelRender(Item.getItemFromBlock(block), meta, variantName, folder);
	}

	public static void registerModelRender(Block block, String variantName, String folder)
	{
		RegisterHelper.registerModelRender(Item.getItemFromBlock(block), 0, variantName, folder);
	}

	public static void registerModelRender(Item item, int meta, String variantName, String folder)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation(folder + ":" + variantName, "inventory"));
	}

	public static void registerModelRender(Item item, String variantName, String folder)
	{
		RegisterHelper.registerModelRender(item, 0, variantName, folder);
	}

	public static void registerModelRenderWithDyeColor(Block block, String folder)
	{
		RegisterHelper.registerModelRender(block, 0, "white_" + getBlockName(block), folder);
		RegisterHelper.registerModelRender(block, 1, "orange_" + getBlockName(block), folder);
		RegisterHelper.registerModelRender(block, 2, "magenta_" + getBlockName(block), folder);
		RegisterHelper.registerModelRender(block, 3, "light_blue_" + getBlockName(block), folder);
		RegisterHelper.registerModelRender(block, 4, "yellow_" + getBlockName(block), folder);
		RegisterHelper.registerModelRender(block, 5, "lime_" + getBlockName(block), folder);
		RegisterHelper.registerModelRender(block, 6, "pink_" + getBlockName(block), folder);
		RegisterHelper.registerModelRender(block, 7, "gray_" + getBlockName(block), folder);
		RegisterHelper.registerModelRender(block, 8, "silver_" + getBlockName(block), folder);
		RegisterHelper.registerModelRender(block, 9, "cyan_" + getBlockName(block), folder);
		RegisterHelper.registerModelRender(block, 10, "purple_" + getBlockName(block), folder);
		RegisterHelper.registerModelRender(block, 11, "blue_" + getBlockName(block), folder);
		RegisterHelper.registerModelRender(block, 12, "brown_" + getBlockName(block), folder);
		RegisterHelper.registerModelRender(block, 13, "green_" + getBlockName(block), folder);
		RegisterHelper.registerModelRender(block, 14, "red_" + getBlockName(block), folder);
		RegisterHelper.registerModelRender(block, 15, "black_" + getBlockName(block), folder);
	}

	public static void registerModelRenderWithDyeColor(Item item, String folder)
	{
		RegisterHelper.registerModelRender(item, 0, "white_" + getItemName(item), folder);
		RegisterHelper.registerModelRender(item, 1, "orange_" + getItemName(item), folder);
		RegisterHelper.registerModelRender(item, 2, "magenta_" + getItemName(item), folder);
		RegisterHelper.registerModelRender(item, 3, "light_blue_" + getItemName(item), folder);
		RegisterHelper.registerModelRender(item, 4, "yellow_" + getItemName(item), folder);
		RegisterHelper.registerModelRender(item, 5, "lime_" + getItemName(item), folder);
		RegisterHelper.registerModelRender(item, 6, "pink_" + getItemName(item), folder);
		RegisterHelper.registerModelRender(item, 7, "gray_" + getItemName(item), folder);
		RegisterHelper.registerModelRender(item, 8, "silver_" + getItemName(item), folder);
		RegisterHelper.registerModelRender(item, 9, "cyan_" + getItemName(item), folder);
		RegisterHelper.registerModelRender(item, 10, "purple_" + getItemName(item), folder);
		RegisterHelper.registerModelRender(item, 11, "blue_" + getItemName(item), folder);
		RegisterHelper.registerModelRender(item, 12, "brown_" + getItemName(item), folder);
		RegisterHelper.registerModelRender(item, 13, "green_" + getItemName(item), folder);
		RegisterHelper.registerModelRender(item, 14, "red_" + getItemName(item), folder);
		RegisterHelper.registerModelRender(item, 15, "black_" + getItemName(item), folder);
	}

	public static void registerBlockWithStateMapper(Block block, IStateMapper mapper)
	{
		Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().registerBlockWithStateMapper(block, mapper);
	}

	public static void registerBuiltInBlocks(Block... block)
	{
		Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().registerBuiltInBlocks(block);
	}

	private static String getBlockName(Block block)
	{
		return block.getUnlocalizedName().substring(5);
	}

	private static String getItemName(Item item)
	{
		return item.getUnlocalizedName().substring(5);
	}

	private static void registerVariantName(Block block, String variant, String folder)
	{
		RegisterHelper.registerVariantName(Item.getItemFromBlock(block), variant, folder);
	}

	private static void registerVariantName(Item item, String variant, String folder)
	{
		ModelBakery.addVariantName(item, folder + ":" + variant);
	}
}