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
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.fronos.core.ModuleFronos;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FronosBlockJelly extends Block
{
	public static final String[] types = new String[] {"jellyBlockGrape",
		"jellyBlockRaspberry",
		"jellyBlockStrawberry",
		"jellyBlockBerry",
		"jellyBlockLime",
	"jellyBlockOrange"};
	private Icon[] textures;

	public FronosBlockJelly(int par1)
	{
		super(par1, Material.glass);
		this.setStepSound(Block.soundClothFootstep);
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@SideOnly(Side.CLIENT)
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

	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
		return super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, 1 - par5);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.textures = new Icon[types.length];

		for (int i = 0; i < types.length; ++i)
		{
			this.textures[i] = par1IconRegister.registerIcon("fronos:"+types[i]);
		}
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
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
		return 1.0F;
	}

	@Override
	public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
	{
		return 1.0F;
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
}