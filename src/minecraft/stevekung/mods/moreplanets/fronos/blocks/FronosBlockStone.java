/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.fronos.core.ModuleFronos;

public class FronosBlockStone extends Block
{
	public static final String[] types = new String[] {
		"fronosStone",
		"fronosCobblestone",
	"fronosDungeonBricks"};
	private Icon[] textures;

	public FronosBlockStone(int par1, Material par2Material)
	{
		super(par1, par2Material);
		this.setStepSound(Block.soundStoneFootstep);
		this.blockHardness = 2.0F;
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleFronos.fronosTab;
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		this.textures = new Icon[types.length];

		for (int i = 0; i < types.length; ++i)
		{
			this.textures[i] = iconRegister.registerIcon("fronos:"+types[i]);
		}
	}

	@Override
	public Icon getIcon(int side, int meta)
	{
		if (meta < 0 || meta >= this.textures.length)
		{
			meta = 0;
		}
		return this.textures[meta];
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z)
	{
		return world.getBlockMetadata(x, y, z);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < types.length; ++i)
		{
			list.add(new ItemStack(blockID, 1, i));
		}
	}

	@Override
	public float getBlockHardness(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		if (meta == 0)
		{
			return 1.5F;
		}
		if (meta == 1)
		{
			return 1.0F;
		}
		if (meta == 2)
		{
			return -1.0F;
		}
		return this.blockHardness;
	}

	@Override
	public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
	{
		int meta = world.getBlockMetadata(x, y, z);

		if (meta == 0)
		{
			return 1.5F;
		}
		if (meta == 1)
		{
			return 1.0F;
		}
		if (meta == 2)
		{
			return 10000.0F;
		}
		return super.getExplosionResistance(par1Entity, world, x, y, z, explosionX, explosionY, explosionZ);
	}

	@Override
	public int idDropped(int meta, Random par2Random, int par3)
	{
		if (meta == 0)
		{
			return FronosBlocks.fronosStone.blockID;
		}
		if (meta == 2)
		{
			return 0;
		}
		return this.blockID;
	}

	@Override
	public int damageDropped(int meta)
	{
		if (meta == 0)
		{
			return 1;
		}
		if (meta == 2)
		{
			return 0;
		}
		return meta;
	}
}