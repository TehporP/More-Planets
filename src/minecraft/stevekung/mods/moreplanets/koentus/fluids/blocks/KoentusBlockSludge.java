/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.koentus.fluids.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import stevekung.mods.moreplanets.core.MorePlanetCore;
import stevekung.mods.moreplanets.koentus.blocks.KoentusBlocks;
import stevekung.mods.moreplanets.koentus.core.ModuleKoentus;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class KoentusBlockSludge extends BlockFluidClassic
{
	public static Icon koentusSludgeStillIcon;
	public static Icon koentusSludgeFlowingIcon;

	public KoentusBlockSludge(int par1, Fluid fluid, Material par2Material)
	{
		super(par1, KoentusBlocks.koentusSludge, par2Material);
		this.setQuantaPerBlock(4);
		this.setRenderPass(1);
		this.setLightValue(1.0F);
		this.needsRandomTick = true;
		this.stack = new FluidStack(fluid, FluidContainerRegistry.BUCKET_VOLUME);
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleKoentus.koentusTab;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2)
	{
		return par1 != 0 && par1 != 1 ? koentusSludgeStillIcon : koentusSludgeFlowingIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		koentusSludgeStillIcon = par1IconRegister.registerIcon("koentus:koentusSludge_flowing");
		koentusSludgeFlowingIcon = par1IconRegister.registerIcon("koentus:koentusSludge_still");
	}

	@Override
	public boolean canDisplace(IBlockAccess world, int x, int y, int z)
	{
		if (world.getBlockMaterial(x, y, z).isLiquid())
		{
			return false;
		}
		return super.canDisplace(world, x, y, z);
	}

	@Override
	public boolean displaceIfPossible(World world, int x, int y, int z)
	{
		if (world.getBlockMaterial(x, y, z).isLiquid())
		{
			return false;
		}
		return super.displaceIfPossible(world, x, y, z);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand)
	{
		super.randomDisplayTick(world, x, y, z, rand);

		if (rand.nextInt(1200) == 0)
		{
			world.playSound(x + 0.5F, y + 0.5F, z + 0.5F, "liquid.lava", rand.nextFloat() * 0.25F + 0.75F, 0.00001F + rand.nextFloat() * 0.5F, false);
		}

		if (rand.nextInt(3) == 0)
		{
			MorePlanetCore.proxy.spawnParticle("koentusSludgeSmoke", x + rand.nextFloat(), y + 1.0F, z + rand.nextFloat());
		}
	}

	/*@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if (!world.isRemote)
		{
			if (entity instanceof EntityPlayer && ((EntityPlayer) entity).capabilities.isFlying || entity instanceof GCMarsEntitySludgeling)
			{
				return;
			}

			int range = 5;
			List<?> l = world.getEntitiesWithinAABB(GCMarsEntitySludgeling.class, AxisAlignedBB.getBoundingBox(x - range, y - range, z - range, x + range, y + range, z + range));

			if (l.size() < 3)
			{
				GCMarsEntitySludgeling sludgeling = new GCMarsEntitySludgeling(world);
				sludgeling.setPosition(x + world.rand.nextInt(5) - 2, y, z + world.rand.nextInt(5) - 2);
				world.spawnEntityInWorld(sludgeling);
			}
		}

		super.onEntityCollidedWithBlock(world, x, y, z, entity);
	}*/
}