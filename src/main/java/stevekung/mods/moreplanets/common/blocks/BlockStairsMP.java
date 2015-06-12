/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.blocks;

import java.util.Arrays;
import java.util.List;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public class BlockStairsMP extends BlockStairs
{
	private StairsCategory category;

	public static enum StairsCategory
	{
		ancient_dark_wood ("wood"),
		orange_wood ("wood"),
		coconut_wood ("wood"),
		maple_wood ("wood"),
		crystal_wood ("wood"),

		diona_cobblestone ("stone"),
		diona_dungeon_brick ("stone"),
		chiseled_quontonium ("stone"),
		quontonium_brick ("stone"),
		polongnius_cobblestone ("stone"),
		polongnius_dungeon_brick ("stone"),
		nibiru_cobblestone ("stone"),
		nibiru_dungeon_brick ("stone"),
		koentus_cobblestone ("stone"),
		koentus_dungeon_brick ("stone"),
		koentus_ancient_stone ("stone"),
		koentus_ancient_stone_brick ("stone"),
		fronos_cobblestone ("stone"),
		fronos_stone_brick ("stone"),
		cracked_fronos_stone_brick ("stone"),
		fronos_dungeon_brick ("stone"),
		kapteyn_b_cracked_ice ("stone"),
		kapteyn_b_dungeon_brick ("stone"),
		sirius_carbon_cobblestone ("stone"),
		sirius_dungeon_brick ("stone"),
		mercury_cobblestone ("stone"),
		mercury_dungeon_brick ("stone");

		private List<String> values;
		private String type;

		private StairsCategory(String type)
		{
			this.type = type;
			this.values = Arrays.asList(type);
		}
	}

	public BlockStairsMP(String name, float hardness, StairsCategory cat, IBlockState material)
	{
		super(material);
		this.category = cat;
		this.setUnlocalizedName(name);
		this.setHardness(hardness);

		if (this.isWoodCategory(this.category.toString()))
		{
			this.setStepSound(soundTypeWood);
		}
		if (this.category == StairsCategory.sirius_carbon_cobblestone || this.category == StairsCategory.sirius_dungeon_brick)
		{
			this.setLightLevel(1.0F);
		}
		this.useNeighborBrightness = true;
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return MorePlanetsCore.mpBlocksTab;
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
}