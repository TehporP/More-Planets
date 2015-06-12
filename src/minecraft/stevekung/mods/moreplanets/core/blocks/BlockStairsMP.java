/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.blocks;

import java.util.Arrays;
import java.util.List;

import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.mars.GalacticraftMars;
import micdoodle8.mods.galacticraft.moon.GalacticraftMoon;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;
import stevekung.mods.moreplanets.diona.core.ModuleDiona;
import stevekung.mods.moreplanets.fronos.core.ModuleFronos;
import stevekung.mods.moreplanets.kapteynb.core.ModuleKapteynB;
import stevekung.mods.moreplanets.koentus.core.ModuleKoentus;
import stevekung.mods.moreplanets.nibiru.core.ModuleNibiru;
import stevekung.mods.moreplanets.polongnius.core.ModulePolongnius;

public class BlockStairsMP extends BlockStairs
{
	public static enum StairsCategory
	{
		WHITE ("wood"),
		ORANGE ("wood"),
		COCONUT ("wood"),
		TIN ("stone"),
		TIN2 ("stone"),
		MOON ("stone"),
		MARS ("stone"),
		POLONGNIUS ("stone"),
		NIBIRU ("stone"),
		DIONA ("stone"),
		SMQUON ("stone"),
		BRQUON ("stone"),
		KOENTUS ("stone"),
		FRONOS ("stone"),
		KAPTEYNB ("stone");

		private List<String> values;
		private String type;

		private StairsCategory(String type)
		{
			this.type = type;
			this.values = Arrays.asList(type);
		}
	}

	public Icon[] textures;
	public StairsCategory category;

	public BlockStairsMP(int blockID, Block model, StairsCategory cat)
	{
		super(blockID, model, 0);
		this.category = cat;
		Block.setBurnProperties(this.blockID, 5, 20);
		this.setLightOpacity(0);
		this.setHardness(model.blockHardness);
		this.setResistance(model.blockResistance / 3.0F);
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		if (this.category == StairsCategory.WHITE || this.category == StairsCategory.NIBIRU || this.category == StairsCategory.ORANGE)
		{
			return ModuleNibiru.nibiruTab;
		}
		else if (this.category == StairsCategory.TIN || this.category == StairsCategory.TIN2)
		{
			return GalacticraftCore.galacticraftTab;
		}
		else if (this.category == StairsCategory.MOON)
		{
			return GalacticraftMoon.galacticraftMoonTab;
		}
		else if (this.category == StairsCategory.MARS)
		{
			return GalacticraftMars.galacticraftMarsTab;
		}
		else if (this.category == StairsCategory.POLONGNIUS)
		{
			return ModulePolongnius.polongniusTab;
		}
		else if (this.category == StairsCategory.KOENTUS)
		{
			return ModuleKoentus.koentusTab;
		}
		else if (this.category == StairsCategory.COCONUT || this.category == StairsCategory.FRONOS)
		{
			return ModuleFronos.fronosTab;
		}
		else if (this.category == StairsCategory.KAPTEYNB)
		{
			return ModuleKapteynB.kapteynB;
		}
		return ModuleDiona.dionaTab;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		if (this.category == StairsCategory.TIN)//Tin Decoration
		{
			this.blockIcon = par1IconRegister.registerIcon("galacticraftcore:deco_aluminium_4");
		}
		else if (this.category == StairsCategory.TIN2)//Tin Decoration
		{
			this.blockIcon = par1IconRegister.registerIcon("galacticraftcore:deco_aluminium_2");
		}
		else if (this.category == StairsCategory.BRQUON)//Quontonium Brick
		{
			this.blockIcon = par1IconRegister.registerIcon("diona:quontoniumBricks");
		}
		else if (this.category == StairsCategory.DIONA)//Diona Stone
		{
			this.blockIcon = par1IconRegister.registerIcon("diona:dionaStone");
		}
		else if (this.category == StairsCategory.KOENTUS)//Koentus Cobblestone
		{
			this.blockIcon = par1IconRegister.registerIcon("koentus:koentusCobblestone");
		}
		else if (this.category == StairsCategory.MARS)//Mars Cobblestone
		{
			this.blockIcon = par1IconRegister.registerIcon("galacticraftmars:cobblestone");
		}
		else if (this.category == StairsCategory.MOON)//Moon Stone
		{
			this.blockIcon = par1IconRegister.registerIcon("galacticraftmoon:bottom");
		}
		else if (this.category == StairsCategory.NIBIRU)//Nibiru Cobblestone
		{
			this.blockIcon = par1IconRegister.registerIcon("nibiru:nibiruCobblestone");
		}
		else if (this.category == StairsCategory.ORANGE)//Orange Wood Planks
		{
			this.blockIcon = par1IconRegister.registerIcon("nibiru:orangeWoodPlanks");
		}
		else if (this.category == StairsCategory.POLONGNIUS)//Polongnius Cobblestone
		{
			this.blockIcon = par1IconRegister.registerIcon("polongnius:polongniusCobblestone");
		}
		else if (this.category == StairsCategory.SMQUON)//Smooth Quontonium
		{
			this.blockIcon = par1IconRegister.registerIcon("diona:quontoniumSmooth");
		}
		else if (this.category == StairsCategory.WHITE)//White Wood
		{
			this.blockIcon = par1IconRegister.registerIcon("nibiru:whiteWoodPlanks");
		}
		else if (this.category == StairsCategory.COCONUT)//Coconut Wood
		{
			this.blockIcon = par1IconRegister.registerIcon("fronos:coconutWoodPlanks");
		}
		else if (this.category == StairsCategory.FRONOS)//Fronos Cobblestone
		{
			this.blockIcon = par1IconRegister.registerIcon("fronos:fronosCobblestone");
		}
		else if (this.category == StairsCategory.KAPTEYNB)//Kapteyn B Cobblestone
		{
			this.blockIcon = par1IconRegister.registerIcon("kapteynb:kapteynBCobblestone");
		}
	}

	public boolean isWoodCategory(String block)
	{
		String type = StairsCategory.valueOf(block).type;

		if (type == "wood")
		{
			return true;
		}
		return false;
	}

	public boolean isStoneCategory(String block)
	{
		String type = StairsCategory.valueOf(block).type;

		if (type == "stone")
		{
			return true;
		}
		return false;
	}

	public static int getWoodCategoryAmount()
	{
		int woodCatNo = 0;

		for (StairsCategory cat : StairsCategory.values())
		{
			if (cat.values.contains("wood"))
			{
				++woodCatNo;
			}
		}
		return woodCatNo;
	}

	public static int getStoneCategoryAmount()
	{
		int woodCatNo = 0;

		for (StairsCategory cat : StairsCategory.values())
		{
			if (cat.values.contains("stone"))
			{
				++woodCatNo;
			}
		}
		return woodCatNo;
	}

	@Override
	public Icon getIcon(int side, int meta)
	{
		return this.blockIcon;
	}
}