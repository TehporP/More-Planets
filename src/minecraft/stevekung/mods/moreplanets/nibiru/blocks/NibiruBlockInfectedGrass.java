/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.nibiru.blocks;

import java.util.Random;

import micdoodle8.mods.galacticraft.api.block.ITerraformableBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;
import stevekung.mods.moreplanets.nibiru.core.ModuleNibiru;

public class NibiruBlockInfectedGrass extends Block implements ITerraformableBlock
{
	private Icon[] blockIcon;

	public NibiruBlockInfectedGrass(int par1)
	{
		super(par1, Material.grass);
		this.setTickRandomly(true);
		this.setStepSound(Block.soundGrassFootstep);
		this.setHardness(0.6F);
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleNibiru.nibiruTab;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = new Icon[8];
		this.blockIcon[0] = par1IconRegister.registerIcon("nibiru:nibiruInfectedDirt");
		this.blockIcon[1] = par1IconRegister.registerIcon("nibiru:nibiruInfectedGrassTop");
		this.blockIcon[2] = par1IconRegister.registerIcon("nibiru:nibiruInfectedGrassSide");
		this.blockIcon[3] = par1IconRegister.registerIcon("nibiru:nibiruInfectedGrassSide");
		this.blockIcon[4] = par1IconRegister.registerIcon("nibiru:nibiruInfectedGrassSide");
		this.blockIcon[5] = par1IconRegister.registerIcon("nibiru:nibiruInfectedGrassSide");
	}

	@Override
	public Icon getIcon(int par1, int par2)
	{
		if (par1 < 0 || par1 >= this.blockIcon.length) {
			par1 = 1;
		}

		return this.blockIcon[par1];
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		if (!world.isRemote)
		{
			if (world.getBlockLightValue(x, y + 1, z) < 4 && Block.lightOpacity[world.getBlockId(x, y + 1, z)] > 2)
			{
				world.setBlock(x, y, z, NibiruBlocks.infectedDirt.blockID);
			}
			else if (world.getBlockLightValue(x, y + 1, z) >= 9)
			{
				for (int var6 = 0; var6 < 4; ++var6)
				{
					int var7 = x + random.nextInt(3) - 1;
					int var8 = y + random.nextInt(5) - 3;
					int var9 = z + random.nextInt(3) - 1;
					int var10 = world.getBlockId(var7, var8 + 1, var9);

					if (world.getBlockId(var7, var8, var9) == NibiruBlocks.infectedDirt.blockID && world.getBlockLightValue(var7, var8 + 1, var9) >= 4 && Block.lightOpacity[var10] <= 2)
					{
						world.setBlock(var7, var8, var9, NibiruBlocks.infectedGrass.blockID);
					}
				}
			}
		}
	}

	@Override
	public boolean canSustainPlant(World world, int x, int y, int z, ForgeDirection direction, IPlantable plant)
	{
		return true;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		float f = 0.08F;
		return AxisAlignedBB.getAABBPool().getAABB(x, y, z, x + 1, y + 1 - f, z + 1);
	}

	@Override
	public int idDropped(int meta, Random par2Random, int par3)
	{
		return NibiruBlocks.infectedDirt.blockID;
	}

	@Override
	public boolean isTerraformable(World world, int x, int y, int z)
	{
		return true;
	}
}