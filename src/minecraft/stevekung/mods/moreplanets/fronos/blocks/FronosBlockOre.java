/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.blocks;

import java.util.List;
import java.util.Random;

import micdoodle8.mods.galacticraft.api.block.IDetectableResource;
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
import stevekung.mods.moreplanets.fronos.items.FronosItems;

public class FronosBlockOre extends Block implements IDetectableResource
{
	private Icon[] fronosBlockIcon;

	public FronosBlockOre(int par1)
	{
		super(par1, Material.rock);
		this.setStepSound(Block.soundStoneFootstep);
		this.blockHardness = 3.0F;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.fronosBlockIcon = new Icon[7];
		this.fronosBlockIcon[0] = par1IconRegister.registerIcon("fronos:greenTopazOre");
		this.fronosBlockIcon[1] = par1IconRegister.registerIcon("fronos:jellyBerryOre");
		this.fronosBlockIcon[2] = par1IconRegister.registerIcon("fronos:jellyStrawberryOre");
		this.fronosBlockIcon[3] = par1IconRegister.registerIcon("fronos:jellyGrapeOre");
		this.fronosBlockIcon[4] = par1IconRegister.registerIcon("fronos:jellyRaspberryOre");
		this.fronosBlockIcon[5] = par1IconRegister.registerIcon("fronos:jellyLimeOre");
		this.fronosBlockIcon[6] = par1IconRegister.registerIcon("fronos:jellyOrangeOre");
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleFronos.fronosTab;
	}

	@Override
	public Icon getIcon(int side, int meta)
	{
		switch (meta)
		{
		case 0:
			return this.fronosBlockIcon[0];
		case 1:
			return this.fronosBlockIcon[1];
		case 2:
			return this.fronosBlockIcon[2];
		case 3:
			return this.fronosBlockIcon[3];
		case 4:
			return this.fronosBlockIcon[4];
		case 5:
			return this.fronosBlockIcon[5];
		case 6:
			return this.fronosBlockIcon[6];
			/*case 7:
            return this.koentusBlockIcon[7];
        case 8:
            return this.koentusBlockIcon[8];
        case 9:
            return this.koentusBlockIcon[9];
        case 10:
            return this.koentusBlockIcon[10];
        case 11:
            return this.koentusBlockIcon[11];
        case 12:
            return this.koentusBlockIcon[12];
        case 13:
            return this.koentusBlockIcon[13];
        case 14:
            return this.koentusBlockIcon[14];*/
		}
		return this.fronosBlockIcon[0];
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
		for (int i = 0; i < 7; ++i)
		{
			list.add(new ItemStack(blockID, 1, i));
		}
	}

	@Override
	public float getBlockHardness(World par1World, int par2, int par3, int par4)
	{
		int meta = par1World.getBlockMetadata(par2, par3, par4);

		if (meta <= 7)
		{
			return 5.0F;
		}
		return 1.0F;
	}

	@Override
	public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
	{
		int meta = world.getBlockMetadata(x, y, z);

		if (meta <= 7)
		{
			return 4.0F;
		}
		return 1.0F;
	}

	@Override
	public int idDropped(int meta, Random par2Random, int par3)
	{
		if (meta == 0)
		{
			return FronosItems.fronosGem.itemID;
		}
		if (meta >= 0 && meta <= 7)
		{
			return FronosItems.jelly.itemID;
		}
		return this.blockID;
	}

	@Override
	public int damageDropped(int meta)
	{
		if (meta == 0)
		{
			return 0;
		}
		if (meta == 1)
		{
			return 3;
		}
		if (meta == 2)
		{
			return 2;
		}
		if (meta == 3)
		{
			return 0;
		}
		if (meta == 4)
		{
			return 1;
		}
		if (meta == 5)
		{
			return 4;
		}
		if (meta == 6)
		{
			return 5;
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
	public boolean isValueable(int metadata)
	{
		return true;
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