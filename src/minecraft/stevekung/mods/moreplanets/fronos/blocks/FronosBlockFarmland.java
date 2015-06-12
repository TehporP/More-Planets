/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FronosBlockFarmland extends Block
{
	private Icon wet;
	private Icon dry;

	public FronosBlockFarmland(int par1)
	{
		super(par1, Material.ground);
		this.setTickRandomly(true);
		this.setStepSound(Block.soundGravelFootstep);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.9375F, 1.0F);
		this.blockHardness = 0.55F;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int par2, int par3, int par4)
	{
		float f = 0.0F;
		return AxisAlignedBB.getAABBPool().getAABB(par2, par3, par4, par2 + 1, par3 + 1 - f, par4 + 1);
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
	public boolean canSustainPlant(World world, int x, int y, int z, ForgeDirection direction, IPlantable plant)
	{
		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(int par1, int par2)
	{
		return par1 == 1 ? par2 > 0 ? this.wet : this.dry : FronosBlocks.fronosDirt.getBlockTextureFromSide(par1);
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		if (!this.isWaterNearby(par1World, par2, par3, par4) && !par1World.canLightningStrikeAt(par2, par3 + 1, par4))
		{
			int meta = par1World.getBlockMetadata(par2, par3, par4);

			if (meta > 0)
			{
				par1World.setBlockMetadataWithNotify(par2, par3, par4, meta - 1, 2);
			}
			else if (!this.isCropsNearby(par1World, par2, par3, par4))
			{
				par1World.setBlock(par2, par3, par4, FronosBlocks.fronosDirt.blockID);
			}
		}
		else
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 7, 2);
		}
	}

	@Override
	public void onFallenUpon(World par1World, int par2, int par3, int par4, Entity par5Entity, float par6)
	{
		if (!par1World.isRemote && par1World.rand.nextFloat() < par6 - 0.5F)
		{
			if (!(par5Entity instanceof EntityPlayer) && !par1World.getGameRules().getGameRuleBooleanValue("mobGriefing"))
			{
				return;
			}
			par1World.setBlock(par2, par3, par4, FronosBlocks.fronosDirt.blockID);
		}
	}

	private boolean isCropsNearby(World par1World, int par2, int par3, int par4)
	{
		byte b0 = 0;

		for (int l = par2 - b0; l <= par2 + b0; ++l)
		{
			for (int i1 = par4 - b0; i1 <= par4 + b0; ++i1)
			{
				int j1 = par1World.getBlockId(l, par3 + 1, i1);

				Block plant = blocksList[j1];

				if (plant instanceof IPlantable && this.canSustainPlant(par1World, par2, par3, par4, ForgeDirection.UP, (IPlantable)plant))
				{
					return true;
				}
			}
		}
		return false;
	}

	private boolean isWaterNearby(World par1World, int par2, int par3, int par4)
	{
		for (int l = par2 - 4; l <= par2 + 4; ++l)
		{
			for (int i1 = par3; i1 <= par3 + 1; ++i1)
			{
				for (int j1 = par4 - 4; j1 <= par4 + 4; ++j1)
				{
					if (par1World.getBlockMaterial(l, i1, j1) == Material.water)
					{
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
	{
		super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
		Material material = par1World.getBlockMaterial(par2, par3 + 1, par4);

		if (material.isSolid())
		{
			par1World.setBlock(par2, par3, par4, FronosBlocks.fronosDirt.blockID);
		}
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return FronosBlocks.fronosDirt.idDropped(0, par2Random, par3);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int idPicked(World par1World, int par2, int par3, int par4)
	{
		return FronosBlocks.fronosDirt.blockID;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.wet = par1IconRegister.registerIcon("fronos:fronosFarmlandWet");
		this.dry = par1IconRegister.registerIcon("fronos:fronosFarmlandDry");
	}
}