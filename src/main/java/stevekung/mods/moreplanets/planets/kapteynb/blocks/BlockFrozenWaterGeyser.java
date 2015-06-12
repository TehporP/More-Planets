/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.blocks.base.BlockBaseMP;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFrozenWaterGeyser extends BlockBaseMP
{
	private IIcon[] geyserBlockIcon;

	public BlockFrozenWaterGeyser(String name)
	{
		super(Material.rock);
		this.setHardness(2.0F);
		this.setResistance(5.0F);
		this.setBlockName(name);
	}

	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.geyserBlockIcon = new IIcon[14];
		this.geyserBlockIcon[0] = par1IconRegister.registerIcon("kapteynb:kapteyn_b_frozen_water_geyser");
		this.geyserBlockIcon[1] = par1IconRegister.registerIcon("kapteynb:kapteyn_b_surface_ice");
		this.geyserBlockIcon[2] = par1IconRegister.registerIcon("kapteynb:kapteyn_b_surface_ice");
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		switch (side)
		{
		case 0:
			return this.geyserBlockIcon[2]; //BOTTOM
		case 1:
			return this.geyserBlockIcon[0]; //TOP
		case 2:
			return this.geyserBlockIcon[1]; //Z-
		case 3:
			return this.geyserBlockIcon[1]; //Z+
		case 4:
			return this.geyserBlockIcon[1]; //X-
		case 5:
			return this.geyserBlockIcon[1]; //X+
		default:
			return this.geyserBlockIcon[0];
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if (entity instanceof EntityPlayer)
		{
			entity.motionY = 2.0F;
			entity.fallDistance = 0.0F;
			((EntityPlayer)entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 120, 1));

			if (((EntityPlayer)entity).capabilities.isFlying)
			{
				return;
			}
		}
		if (entity instanceof EntityLivingBase)
		{
			entity.motionY = 2.0F;
			entity.fallDistance = 0.0F;
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 120, 1));
		}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		float f = 0.100F;
		return AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1 - f, z + 1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand)
	{
		for (int i = 0; i < 5; i++)
		{
			if (!World.doesBlockHaveSolidTopSurface(world, x, y + 1, z))
			{
				MorePlanetsCore.proxy.spawnMotionParticle("waterGeyser", x + 0.5F, y + 0.5F + rand.nextFloat(), z + 0.5F, 0.0D, 0.0D + rand.nextFloat(), 0.0D);
			}
		}
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune)
	{
		return Item.getItemFromBlock(KapteynBBlocks.kapteyn_b_block);
	}

	@Override
	public int damageDropped(int meta)
	{
		return 0;
	}

	@Override
	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata)
	{
		return true;
	}
}