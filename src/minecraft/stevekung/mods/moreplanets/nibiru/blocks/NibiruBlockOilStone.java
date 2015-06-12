/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.nibiru.blocks;

import java.util.List;
import java.util.Random;

import micdoodle8.mods.galacticraft.api.block.IDetectableResource;
import micdoodle8.mods.galacticraft.core.blocks.GCCoreBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.nibiru.core.ModuleNibiru;

public class NibiruBlockOilStone extends Block implements IDetectableResource
{
	private Icon[] nibiruBlockIcon;

	public NibiruBlockOilStone(int par1)
	{
		super(par1, Material.rock);
		this.setStepSound(Block.soundStoneFootstep);
		this.setHardness(3.25F);
		this.setResistance(3.5F);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		float f = 0.125F;
		return AxisAlignedBB.getAABBPool().getAABB(x, y, z, x + 1, y + 1 - f, z + 1);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.nibiruBlockIcon = new Icon[4];
		this.nibiruBlockIcon[0] = par1IconRegister.registerIcon("nibiru:nibiruOilStone");
		this.nibiruBlockIcon[1] = par1IconRegister.registerIcon("nibiru:nibiruOilOre");
		this.nibiruBlockIcon[2] = par1IconRegister.registerIcon("nibiru:nibiruGrass");
		this.nibiruBlockIcon[3] = par1IconRegister.registerIcon("nibiru:nibiruDirt");
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleNibiru.nibiruTab;
	}

	@Override
	public Icon getIcon(int side, int meta)
	{
		switch (meta)
		{
		case 0:
			switch (side)
			{
			case 0:
				return this.nibiruBlockIcon[3]; //BOTTOM
			case 1:
				return this.nibiruBlockIcon[0]; //TOP
			case 2:
				return this.nibiruBlockIcon[2]; //Z-
			case 3:
				return this.nibiruBlockIcon[2]; //Z+
			case 4:
				return this.nibiruBlockIcon[2]; //X-
			case 5:
				return this.nibiruBlockIcon[2]; //X+
			default:
				return this.nibiruBlockIcon[0];
			}
		case 1:
			return this.nibiruBlockIcon[1];
		}
		return this.nibiruBlockIcon[0];
	}

	@Override
	public int idDropped(int meta, Random random, int par3)
	{
		return this.blockID;
	}

	@Override
	public int quantityDropped(Random par1Random)
	{
		return 0;
	}

	@Override
	public int getMobilityFlag()
	{
		return 0;
	}

	@Override
	public boolean canHarvestBlock(EntityPlayer player, int meta)
	{
		return super.canHarvestBlock(player, meta);
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
		for (int i = 0; i < 2; ++i)
		{
			list.add(new ItemStack(blockID, 1, i));
		}
	}

	@Override
	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata)
	{
		return true;
	}

	@Override
	public boolean isValueable(int metadata)
	{
		return true;
	}

	@Override
	public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6)
	{
		par2EntityPlayer.addStat(StatList.mineBlockStatArray[this.blockID], 1);
		par2EntityPlayer.addExhaustion(0.025F);

		if (this.canSilkHarvest() && EnchantmentHelper.getSilkTouchModifier(par2EntityPlayer))
		{
			ItemStack itemstack = this.createStackedBlock(par6);

			if (itemstack != null)
			{
				this.dropBlockAsItem_do(par1World, par3, par4, par5, itemstack);
			}
		}
		else
		{
			int i1 = EnchantmentHelper.getFortuneModifier(par2EntityPlayer);
			this.dropBlockAsItem(par1World, par3, par4, par5, par6, i1);
			Material material = par1World.getBlockMaterial(par3, par4 - 1, par5);

			if (material.blocksMovement() || material.isLiquid())
			{
				par1World.setBlock(par3, par4, par5, GCCoreBlocks.crudeOilStill.blockID, 0, 3);
			}
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
	{
		par5Entity.motionX *= 0.4D;
		par5Entity.motionZ *= 0.4D;
	}
}