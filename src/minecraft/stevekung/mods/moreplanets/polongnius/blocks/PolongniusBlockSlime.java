/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.polongnius.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.polongnius.core.ModulePolongnius;

public class PolongniusBlockSlime extends Block
{
	public PolongniusBlockSlime(int id)
	{
		super(id, Material.glass);
		this.setStepSound(Block.soundClothFootstep);
		this.blockHardness = 0.55F;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("polongnius:polongniusSlime");
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModulePolongnius.polongniusTab;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public int getRenderBlockPass()
	{
		return 1;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		float dy = 0.75F;
		int id = par1World.getBlockId(par2, par3 - 1, par4);
		AxisAlignedBB box = null;

		if (id != this.blockID && id != 0)
		{
			box = AxisAlignedBB.getBoundingBox(par2, par3, par4, par2 + 1, par3 + 1 - dy, par4 + 1);
		}
		return box;
	}

	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
	{
		this.onEntityJump(par1World, par2, par3, par4, par5Entity);
	}

	private void onEntityJump(World world, int i, int j, int k, Entity par5Entity)
	{
		float m = 1.05F;
		par5Entity.fallDistance = 0.0F;

		if (world.getBlockId(i + 1, j, k) == this.blockID && world.getBlockId(i - 1, j, k) == this.blockID && world.getBlockId(i, j, k + 1) == this.blockID && world.getBlockId(i, j, k - 1) == this.blockID)
		{
			m = 1.55F;
		}

		par5Entity.fallDistance = 0.0F;

		if (par5Entity.motionY < -0.15)
		{
			par5Entity.motionX *= m;
			par5Entity.motionY *= -m;
			par5Entity.motionZ *= m;
			par5Entity.playSound("mob.slime.big", 1f, 1f);
		}
		else
		{
			par5Entity.motionX *= 0.5D;
			par5Entity.motionY *= 0.5D;
			par5Entity.motionZ *= 0.5D;
		}
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
		return super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, 1 - par5);
	}
}