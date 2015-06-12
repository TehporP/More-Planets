/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.pluto.fluids;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.blocks.BlockFluidBaseMP;
import stevekung.mods.moreplanets.common.util.WorldUtilMP;
import stevekung.mods.moreplanets.planets.pluto.blocks.PlutoBlocks;

public class BlockFluidMethane extends BlockFluidBaseMP
{
	public BlockFluidMethane(String name)
	{
		super(PlutoBlocks.liquid_methane_fluid);
		this.setQuantaPerBlock(8);
		this.setRenderLayer(EnumWorldBlockLayer.TRANSLUCENT);
		this.setTickRandomly(true);
		this.setUnlocalizedName(name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		super.randomDisplayTick(world, pos, state, rand);
		int meta = (Integer)state.getValue(LEVEL);

		if (rand.nextInt(64) == 0)
		{
			if (meta > 0 && meta < 8)
			{
				world.playSound(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, "liquid.water", rand.nextFloat() * 0.25F + 0.75F, rand.nextFloat() * 1.0F + 0.5F, false);
			}
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, Entity entity)
	{
		if (entity instanceof EntityLivingBase)
		{
			if (WorldUtilMP.isPlutoWorld(world, pos))
			{
				((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 60));
			}
		}
	}
}