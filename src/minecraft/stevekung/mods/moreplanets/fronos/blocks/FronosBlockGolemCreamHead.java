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
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetCore;
import stevekung.mods.moreplanets.fronos.core.ModuleFronos;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityChocolateGolem;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityOrangeGolem;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityStrawberryGolem;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityVanillaGolem;

public class FronosBlockGolemCreamHead extends Block
{
	private Icon[] creamBlockIcon;

	protected FronosBlockGolemCreamHead(int par1)
	{
		super(par1, Material.plants);
		this.setHardness(0.4F);
		this.setTickRandomly(true);
		this.setBlockBounds(0.2F, 0.2F, 0.2F, 0.8F, 0.8F, 0.8F);
		this.setStepSound(Block.soundSnowFootstep);
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public int idDropped(int meta, Random par2Random, int par3)
	{
		return this.blockID;
	}

	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z)
	{
		return world.getBlockMetadata(x, y, z);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubBlocks(int block, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < 4; ++i)
		{
			list.add(new ItemStack(block, 1, i));
		}
	}

	@Override
	public Icon getIcon(int side, int meta)
	{
		switch (meta)
		{
		case 0:
			return this.creamBlockIcon[0];
		case 1:
			return this.creamBlockIcon[1];
		case 2:
			return this.creamBlockIcon[2];
		case 3:
			return this.creamBlockIcon[3];
		}
		return this.creamBlockIcon[0];
	}

	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4)
	{
		super.onBlockAdded(par1World, par2, par3, par4);
		int meta = par1World.getBlockMetadata(par2, par3, par4);

		if (par1World.getBlockId(par2, par3 - 1, par4) == FronosBlocks.vanillaCream.blockID && par1World.getBlockId(par2, par3 - 2, par4) == FronosBlocks.vanillaCream.blockID && meta == 0)
		{
			if (!par1World.isRemote)
			{
				par1World.setBlock(par2, par3, par4, 0, 0, 2);
				par1World.setBlock(par2, par3 - 1, par4, 0, 0, 2);
				par1World.setBlock(par2, par3 - 2, par4, 0, 0, 2);
				FronosEntityVanillaGolem entitysnowman = new FronosEntityVanillaGolem(par1World);
				entitysnowman.setLocationAndAngles(par2 + 0.5D, par3 - 1.95D, par4 + 0.5D, 0.0F, 0.0F);
				par1World.spawnEntityInWorld(entitysnowman);
				par1World.notifyBlockChange(par2, par3, par4, 0);
				par1World.notifyBlockChange(par2, par3 - 1, par4, 0);
				par1World.notifyBlockChange(par2, par3 - 2, par4, 0);
			}

			for (int l = 0; l < 120; ++l)
			{
				MorePlanetCore.proxy.spawnParticle("vanillaBall", par2 + par1World.rand.nextDouble(), par3 - 2 + par1World.rand.nextDouble() * 2.5D, par4 + par1World.rand.nextDouble());
			}
		}
		else if (par1World.getBlockId(par2, par3 - 1, par4) == FronosBlocks.chocolateCream.blockID && par1World.getBlockId(par2, par3 - 2, par4) == FronosBlocks.chocolateCream.blockID && meta == 1)
		{
			if (!par1World.isRemote)
			{
				par1World.setBlock(par2, par3, par4, 0, 0, 2);
				par1World.setBlock(par2, par3 - 1, par4, 0, 0, 2);
				par1World.setBlock(par2, par3 - 2, par4, 0, 0, 2);
				FronosEntityChocolateGolem entitysnowman = new FronosEntityChocolateGolem(par1World);
				entitysnowman.setLocationAndAngles(par2 + 0.5D, par3 - 1.95D, par4 + 0.5D, 0.0F, 0.0F);
				par1World.spawnEntityInWorld(entitysnowman);
				par1World.notifyBlockChange(par2, par3, par4, 0);
				par1World.notifyBlockChange(par2, par3 - 1, par4, 0);
				par1World.notifyBlockChange(par2, par3 - 2, par4, 0);
			}

			for (int l = 0; l < 120; ++l)
			{
				MorePlanetCore.proxy.spawnParticle("chocolateBall", par2 + par1World.rand.nextDouble(), par3 - 2 + par1World.rand.nextDouble() * 2.5D, par4 + par1World.rand.nextDouble());
			}
		}
		else if (par1World.getBlockId(par2, par3 - 1, par4) == FronosBlocks.strawberryCream.blockID && par1World.getBlockId(par2, par3 - 2, par4) == FronosBlocks.strawberryCream.blockID && meta == 2)
		{
			if (!par1World.isRemote)
			{
				par1World.setBlock(par2, par3, par4, 0, 0, 2);
				par1World.setBlock(par2, par3 - 1, par4, 0, 0, 2);
				par1World.setBlock(par2, par3 - 2, par4, 0, 0, 2);
				FronosEntityStrawberryGolem entitysnowman = new FronosEntityStrawberryGolem(par1World);
				entitysnowman.setLocationAndAngles(par2 + 0.5D, par3 - 1.95D, par4 + 0.5D, 0.0F, 0.0F);
				par1World.spawnEntityInWorld(entitysnowman);
				par1World.notifyBlockChange(par2, par3, par4, 0);
				par1World.notifyBlockChange(par2, par3 - 1, par4, 0);
				par1World.notifyBlockChange(par2, par3 - 2, par4, 0);
			}

			for (int l = 0; l < 120; ++l)
			{
				MorePlanetCore.proxy.spawnParticle("strawberryBall", par2 + par1World.rand.nextDouble(), par3 - 2 + par1World.rand.nextDouble() * 2.5D, par4 + par1World.rand.nextDouble());
			}
		}
		else if (par1World.getBlockId(par2, par3 - 1, par4) == FronosBlocks.orangeCream.blockID && par1World.getBlockId(par2, par3 - 2, par4) == FronosBlocks.orangeCream.blockID && meta == 3)
		{
			if (!par1World.isRemote)
			{
				par1World.setBlock(par2, par3, par4, 0, 0, 2);
				par1World.setBlock(par2, par3 - 1, par4, 0, 0, 2);
				par1World.setBlock(par2, par3 - 2, par4, 0, 0, 2);
				FronosEntityOrangeGolem entitysnowman = new FronosEntityOrangeGolem(par1World);
				entitysnowman.setLocationAndAngles(par2 + 0.5D, par3 - 1.95D, par4 + 0.5D, 0.0F, 0.0F);
				par1World.spawnEntityInWorld(entitysnowman);
				par1World.notifyBlockChange(par2, par3, par4, 0);
				par1World.notifyBlockChange(par2, par3 - 1, par4, 0);
				par1World.notifyBlockChange(par2, par3 - 2, par4, 0);
			}

			for (int l = 0; l < 120; ++l)
			{
				MorePlanetCore.proxy.spawnParticle("orangeBall", par2 + par1World.rand.nextDouble(), par3 - 2 + par1World.rand.nextDouble() * 2.5D, par4 + par1World.rand.nextDouble());
			}
		}
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleFronos.fronosTab;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.creamBlockIcon = new Icon[4];
		this.creamBlockIcon[0] = par1IconRegister.registerIcon("fronos:vanillaCream");
		this.creamBlockIcon[1] = par1IconRegister.registerIcon("fronos:chocolateCream");
		this.creamBlockIcon[2] = par1IconRegister.registerIcon("fronos:strawberryCream");
		this.creamBlockIcon[3] = par1IconRegister.registerIcon("fronos:orangeCream");
	}
}