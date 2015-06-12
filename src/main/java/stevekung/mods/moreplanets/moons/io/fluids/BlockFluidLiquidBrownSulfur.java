/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.io.fluids;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.blocks.BlockFluidLavaBaseMP;
import stevekung.mods.moreplanets.moons.io.blocks.BlockIo;
import stevekung.mods.moreplanets.moons.io.blocks.IoBlocks;

public class BlockFluidLiquidBrownSulfur extends BlockFluidLavaBaseMP
{
	public BlockFluidLiquidBrownSulfur(String name)
	{
		super(IoBlocks.liquid_brown_sulfur_fluid);
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
				world.spawnParticle(EnumParticleTypes.LAVA, d5, d6, d7, 0.0D, 0.0D, 0.0D);
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
			world.spawnParticle(EnumParticleTypes.DRIP_LAVA, d5, d6, d7, 0.0D, 0.0D, 0.0D);
		}
		super.randomDisplayTick(world, pos, state, rand);
	}

	@Override
	protected IBlockState getBlockFromWaterTo()
	{
		return IoBlocks.io_block.getDefaultState().withProperty(BlockIo.VARIANT, BlockIo.BlockType.io_rock);
	}

	@Override
	protected IBlockState getObsidianBlock()
	{
		return Blocks.obsidian.getDefaultState();
	}

	@Override
	protected IBlockState getCobblestoneBlock()
	{
		return IoBlocks.io_block.getDefaultState().withProperty(BlockIo.VARIANT, BlockIo.BlockType.io_cobblestone);
	}

	@Override
	protected IBlockState getFireBlock()
	{
		return Blocks.fire.getDefaultState();
	}
}