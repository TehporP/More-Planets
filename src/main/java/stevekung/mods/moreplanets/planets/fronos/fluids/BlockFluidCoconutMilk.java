/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.fluids;

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
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.proxy.ClientProxyMP.ParticleTypesMP;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;

public class BlockFluidCoconutMilk extends BlockFluidBaseMP
{
	public BlockFluidCoconutMilk(String name)
	{
		super(FronosBlocks.coconut_milk_fluid);
		this.setRenderLayer(EnumWorldBlockLayer.TRANSLUCENT);
		this.setQuantaPerBlock(5);
		this.setUnlocalizedName(name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		super.randomDisplayTick(world, pos, state, rand);
		int meta = (Integer) state.getValue(LEVEL);

		if (rand.nextInt(1) == 0)
		{
			MorePlanetsCore.proxy.spawnParticle(ParticleTypesMP.COCONUT_MILK, pos.getX() + rand.nextFloat(), pos.getY() + 1, pos.getZ() + rand.nextFloat());
		}
		if (rand.nextInt(64) == 0)
		{
			if (meta > 0 && meta < 8)
			{
				world.playSound(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, "liquid.water", rand.nextFloat() * 0.25F + 0.75F, rand.nextFloat() * 1.0F + 0.5F, false);
			}
		}
		if (rand.nextInt(10) == 0 && World.doesBlockHaveSolidTopSurface(world, pos.down()) && !world.getBlockState(pos.down(2)).getBlock().getMaterial().blocksMovement())
		{
			double d5 = pos.getX() + rand.nextFloat();
			double d6 = pos.getY() - 1.05D;
			double d7 = pos.getZ() + rand.nextFloat();
			MorePlanetsCore.proxy.spawnParticle(ParticleTypesMP.COCONUT_MILK_DRIP, d5, d6, d7);
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
	{
		if (entity instanceof EntityLivingBase)
		{
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.regeneration.id, 50, 1));
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.jump.id, 100, 2));
		}
	}
}