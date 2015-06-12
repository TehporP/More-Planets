/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.fluids;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.blocks.BlockFluidLavaBaseMP;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.proxy.ClientProxyMP.ParticleTypesMP;
import stevekung.mods.moreplanets.planets.siriusb.blocks.BlockSiriusB;
import stevekung.mods.moreplanets.planets.siriusb.blocks.SiriusBBlocks;

public class BlockFluidSiriusLava extends BlockFluidLavaBaseMP
{
	public BlockFluidSiriusLava(String name)
	{
		super(SiriusBBlocks.sirius_lava_fluid);
		this.setUnlocalizedName(name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		if (this.blockMaterial == Material.lava && world.getBlockState(pos.up()).getBlock().getMaterial() == Material.air && !world.getBlockState(pos.up()).getBlock().isOpaqueCube())
		{
			if (rand.nextInt(100) == 0)
			{
				double d5 = pos.getX() + rand.nextFloat();
				double d6 = pos.getY() + this.maxY;
				double d7 = pos.getZ() + rand.nextFloat();
				MorePlanetsCore.proxy.spawnParticle(ParticleTypesMP.SIRIUS_LAVA, d5, d6, d7);
				world.playSound(d5, d6, d7, "liquid.lavapop", 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
			}
			if (rand.nextInt(200) == 0)
			{
				world.playSound(pos.getX(), pos.getY(), pos.getZ(), "liquid.lava", 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
			}
		}
		if (rand.nextInt(10) == 0 && World.doesBlockHaveSolidTopSurface(world, pos.down()) && !world.getBlockState(pos.down(2)).getBlock().getMaterial().blocksMovement())
		{
			double d5 = pos.getX() + rand.nextFloat();
			double d6 = pos.getY() - 1.05D;
			double d7 = pos.getZ() + rand.nextFloat();
			MorePlanetsCore.proxy.spawnParticle(ParticleTypesMP.SIRIUS_LAVA_DRIP, d5, d6, d7);
		}
		super.randomDisplayTick(world, pos, state, rand);
	}

	@Override
	protected IBlockState getBlockFromWaterTo()
	{
		return SiriusBBlocks.sirius_b_block.getDefaultState().withProperty(BlockSiriusB.VARIANT, BlockSiriusB.BlockType.sirius_b_carbon_stone);
	}

	@Override
	protected IBlockState getObsidianBlock()
	{
		return SiriusBBlocks.sirius_obsidian.getDefaultState();
	}

	@Override
	protected IBlockState getCobblestoneBlock()
	{
		return SiriusBBlocks.sirius_b_block.getDefaultState().withProperty(BlockSiriusB.VARIANT, BlockSiriusB.BlockType.sirius_b_carbon_cobblestone);
	}

	@Override
	protected IBlockState getFireBlock()
	{
		return SiriusBBlocks.sirius_fire.getDefaultState();
	}
}