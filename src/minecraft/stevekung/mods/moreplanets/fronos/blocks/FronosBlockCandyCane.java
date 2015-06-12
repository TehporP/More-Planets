/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.fronos.core.ModuleFronos;

public class FronosBlockCandyCane extends Block
{
	public static enum CandyCategory
	{
		CAT1, CAT2, CAT3, CAT4;
	}

	private static final String[] types = new String[] {"pink", "orange", "green", "yellow", "lightBlue", "blue", "lightPurple", "purple"/*, "redwood", "willow", "dead", "bigflowerstem", "pine", "hellbark", "jacaranda"*/};
	private Icon[] textures;
	private Icon[] logHearts;

	private final CandyCategory category;

	public FronosBlockCandyCane(int blockID, CandyCategory cat)
	{
		super(blockID, Material.cake);
		this.category = cat;
		this.setHardness(1.0F);
		this.setResistance(3.0F);
		this.setStepSound(Block.soundClothFootstep);
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		this.textures = new Icon[types.length];
		this.logHearts = new Icon[types.length];

		for (int i = 0; i < types.length; ++i)
		{
			if (i != 11)
			{
				this.textures[i] = iconRegister.registerIcon("fronos:candyCane_"+types[i]+"_side");
				this.logHearts[i] = iconRegister.registerIcon("fronos:candyCane_"+types[i]+"_heart");
			}
		}
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleFronos.fronosTab;
	}

	@Override
	public Icon getIcon(int side, int meta)
	{
		int pos = meta & 12;

		if (pos == 0 && (side == 1 || side == 0) || pos == 4 && (side == 5 || side == 4) || pos == 8 && (side == 2 || side == 3))
		{
			return this.logHearts[getTypeFromMeta(meta) + this.category.ordinal() * 4];
		}
		else
		{
			return this.textures[getTypeFromMeta(meta) + this.category.ordinal() * 4];
		}
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list)
	{
		if (this.category != CandyCategory.CAT4)
		{
			for (int i = 0; i < 4; ++i)
			{
				list.add(new ItemStack(this, 1, i));
			}
		}
		else
		{
			for (int i = 0; i < 3; ++i)
			{
				list.add(new ItemStack(this, 1, i));
			}
		}
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
	{
		int type = getTypeFromMeta(meta);
		byte orientation = 0;

		switch (side)
		{
		case 0:
		case 1:
			orientation = 0;
			break;

		case 2:
		case 3:
			orientation = 8;
			break;

		case 4:
		case 5:
			orientation = 4;
		}

		return type | orientation;
	}

	@Override
	public int damageDropped(int meta)
	{
		return getTypeFromMeta(meta);
	}

	@Override
	protected ItemStack createStackedBlock(int meta)
	{
		return new ItemStack(this.blockID, 1, getTypeFromMeta(meta));
	}

	@Override
	public int getRenderType()
	{
		return 31;
	}

	public String getWoodType(int meta)
	{
		return types[getTypeFromMeta(meta) + this.category.ordinal() * 4];
	}

	public static int getTypeFromMeta(int meta)
	{
		return meta & 3;
	}
}