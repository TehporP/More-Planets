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
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.fronos.core.ModuleFronos;

public class FronosBlockFrostedCake extends Block
{
	public static final String[] types = new String[] {"cakeBlock",
		"cakeBlockTop",
	"cakeBlockPinkTop"};
	private Icon[] textures;
	private Icon[] cakeIcon;
	private Icon[] cakePinkIcon;

	public FronosBlockFrostedCake(int par1)
	{
		super(par1, Material.cake);
		this.setStepSound(Block.soundClothFootstep);
		this.blockHardness = 1.5F;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.textures = new Icon[types.length];

		for (int i = 0; i < types.length; ++i)
		{
			this.textures[i] = par1IconRegister.registerIcon("fronos:"+types[i]);
		}

		this.cakeIcon = new Icon[3];
		this.cakeIcon[0] = par1IconRegister.registerIcon("fronos:cakeBlock");
		this.cakeIcon[1] = par1IconRegister.registerIcon("fronos:cakeBlockSide");
		this.cakeIcon[2] = par1IconRegister.registerIcon("fronos:cakeBlockTop");

		this.cakePinkIcon = new Icon[2];
		this.cakePinkIcon[0] = par1IconRegister.registerIcon("fronos:cakeBlockPinkTop");
		this.cakePinkIcon[1] = par1IconRegister.registerIcon("fronos:cakeBlockPinkSide");
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleFronos.fronosTab;
	}

	@Override
	public Icon getIcon(int side, int meta)
	{
		if (meta < 0 || meta >= this.textures.length)
		{
			meta = 0;
		}

		switch (meta)
		{
		case 1:
			switch (side)
			{
			case 0:
				return this.cakeIcon[0]; //BOTTOM
			case 1:
				return this.cakeIcon[2]; //TOP
			case 2:
				return this.cakeIcon[1]; //Z-
			case 3:
				return this.cakeIcon[1]; //Z+
			case 4:
				return this.cakeIcon[1]; //X-
			case 5:
				return this.cakeIcon[1]; //X+
			default:
				return this.cakeIcon[0];
			}
		case 2:
			switch (side)
			{
			case 0:
				return this.cakeIcon[0]; //BOTTOM
			case 1:
				return this.cakePinkIcon[0]; //TOP
			case 2:
				return this.cakePinkIcon[1]; //Z-
			case 3:
				return this.cakePinkIcon[1]; //Z+
			case 4:
				return this.cakePinkIcon[1]; //X-
			case 5:
				return this.cakePinkIcon[1]; //X+
			default:
				return this.cakePinkIcon[0];
			}
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
	public float getBlockHardness(World par1World, int par2, int par3, int par4)
	{
		int meta = par1World.getBlockMetadata(par2, par3, par4);

		if (meta == 0 || meta == 1 || meta == 2)
		{
			return 0.55F;
		}
		return 1.0F;
	}

	@Override
	public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
	{
		int meta = world.getBlockMetadata(x, y, z);

		if (meta == 0 || meta == 1 || meta == 2)
		{
			return 0.55F;
		}
		return 1.0F;
	}

	@Override
	public int idDropped(int meta, Random par2Random, int par3)
	{
		if (meta == 1)
		{
			return FronosBlocks.frostedCakeBlock.blockID;
		}
		if (meta == 2)
		{
			return FronosBlocks.frostedCakeBlock.blockID;
		}
		return this.blockID;
	}

	@Override
	public int damageDropped(int meta)
	{
		if (meta == 1)
		{
			return 0;
		}
		if (meta == 2)
		{
			return 0;
		}

		return meta;
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		if (fortune > 0 && this.blockID != this.idDropped(meta, random, fortune))
		{
			int j = random.nextInt(fortune + 2) - 1;

			if (j < 0)
			{
				j = 0;
			}

			return this.quantityDropped(random) * (j + 1);
		}
		else
		{
			return this.quantityDropped(random);
		}
	}

	@Override
	public void dropBlockAsItemWithChance(World world, int par2, int par3, int par4, int par5, float par6, int par7)
	{
		super.dropBlockAsItemWithChance(world, par2, par3, par4, par5, par6, par7);

		if (this.idDropped(par5, world.rand, par7) != this.blockID)
		{
			int var8 =  MathHelper.getRandomIntegerInRange(world.rand, 3, 5);
			this.dropXpOnBlockBreak(world, par2, par3, par4, var8);
		}
	}
}