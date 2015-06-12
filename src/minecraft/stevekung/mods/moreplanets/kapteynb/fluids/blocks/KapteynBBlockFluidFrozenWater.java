/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.kapteynb.fluids.blocks;

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
import stevekung.mods.moreplanets.kapteynb.core.ModuleKapteynB;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class KapteynBBlockFluidFrozenWater extends BlockFluidClassic
{
	public static Icon frozenWaterStillIcon;
	public static Icon frozenFlowingIcon;

	public KapteynBBlockFluidFrozenWater(int id, Fluid fluid, Material material)
	{
		super(id, fluid, material);
		this.stack = new FluidStack(fluid, FluidContainerRegistry.BUCKET_VOLUME);
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleKapteynB.kapteynB;
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

	/*@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		int meta = world.getBlockMetadata(x, y, z);

		if (entity instanceof EntityLivingBase)
		{
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.regeneration.id, 50, 1));
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 100, 1));
		}
	}
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
	{
		frozenWaterStillIcon = iconRegister.registerIcon("kapteynb:frozenWater_still");
		frozenFlowingIcon = iconRegister.registerIcon("kapteynb:frozenWater_flowing");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2)
	{
		return par1 != 0 && par1 != 1 ? frozenFlowingIcon : frozenWaterStillIcon;
	}
}