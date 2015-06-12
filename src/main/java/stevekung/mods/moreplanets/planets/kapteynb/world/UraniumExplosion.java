/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.world;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.planets.kapteynb.blocks.KapteynBBlocks;
import stevekung.mods.moreplanets.planets.kapteynb.entities.EntityUraniumBomb;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class UraniumExplosion extends Explosion
{
	private boolean isFlaming;
	private boolean isSmoking;
	private Random explosionRNG;
	private World worldObj;
	private double explosionX;
	private double explosionY;
	private double explosionZ;
	private Entity exploder;
	private float explosionSize;
	private List affectedBlockPositions;
	private Map field_77288_k;
	private Vec3 position;

	public UraniumExplosion(World world, Entity entity, double x, double y, double z, float size, boolean flaming, boolean smoking)
	{
		super(world, entity, x, y, z, size, flaming, smoking);
		this.explosionRNG = new Random();
		this.affectedBlockPositions = Lists.newArrayList();
		this.field_77288_k = Maps.newHashMap();
		this.worldObj = world;
		this.exploder = entity;
		this.explosionSize = size;
		this.explosionX = x;
		this.explosionY = y;
		this.explosionZ = z;
		this.isFlaming = flaming;
		this.isSmoking = smoking;
		this.position = new Vec3(this.explosionX, this.explosionY, this.explosionZ);
	}

	@Override
	public void doExplosionA()
	{
		HashSet hashset = Sets.newHashSet();
		int j;
		int k;

		for (int i = 0; i < 16; ++i)
		{
			for (j = 0; j < 16; ++j)
			{
				for (k = 0; k < 16; ++k)
				{
					if (i == 0 || i == 15 || j == 0 || j == 15 || k == 0 || k == 15)
					{
						double d0 = i / 15.0F * 2.0F - 1.0F;
						double d1 = j / 15.0F * 2.0F - 1.0F;
						double d2 = k / 15.0F * 2.0F - 1.0F;
						double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
						d0 /= d3;
						d1 /= d3;
						d2 /= d3;
						float f = this.explosionSize * (0.7F + this.worldObj.rand.nextFloat() * 0.6F);
						double d4 = this.explosionX;
						double d6 = this.explosionY;
						double d8 = this.explosionZ;

						for (; f > 0.0F; f -= 0.22500001F)
						{
							BlockPos blockpos = new BlockPos(d4, d6, d8);
							IBlockState iblockstate = this.worldObj.getBlockState(blockpos);

							if (iblockstate.getBlock().getMaterial() != Material.air)
							{
								float f2 = this.exploder != null ? this.exploder.getExplosionResistance(this, this.worldObj, blockpos, iblockstate) : iblockstate.getBlock().getExplosionResistance(this.worldObj, blockpos, (Entity)null, this);
								f -= (f2 + 0.3F) * 0.3F;
							}

							if (f > 0.0F && (this.exploder == null || this.exploder.func_174816_a(this, this.worldObj, blockpos, iblockstate, f)))
							{
								hashset.add(blockpos);
							}

							d4 += d0 * 0.30000001192092896D;
							d6 += d1 * 0.30000001192092896D;
							d8 += d2 * 0.30000001192092896D;
						}
					}
				}
			}
		}

		this.affectedBlockPositions.addAll(hashset);
		float f3 = this.explosionSize * 2.0F;
		j = MathHelper.floor_double(this.explosionX - f3 - 1.0D);
		k = MathHelper.floor_double(this.explosionX + f3 + 1.0D);
		int j1 = MathHelper.floor_double(this.explosionY - f3 - 1.0D);
		int l = MathHelper.floor_double(this.explosionY + f3 + 1.0D);
		int k1 = MathHelper.floor_double(this.explosionZ - f3 - 1.0D);
		int i1 = MathHelper.floor_double(this.explosionZ + f3 + 1.0D);
		List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this.exploder, new AxisAlignedBB(j, j1, k1, k, l, i1));
		net.minecraftforge.event.ForgeEventFactory.onExplosionDetonate(this.worldObj, this, list, f3);
		Vec3 vec3 = new Vec3(this.explosionX, this.explosionY, this.explosionZ);

		for (int l1 = 0; l1 < list.size(); ++l1)
		{
			Entity entity = (Entity)list.get(l1);

			if (!entity.func_180427_aV())
			{
				double d12 = entity.getDistance(this.explosionX, this.explosionY, this.explosionZ) / f3;

				if (d12 <= 1.0D)
				{
					double d5 = entity.posX - this.explosionX;
					double d7 = entity.posY + entity.getEyeHeight() - this.explosionY;
					double d9 = entity.posZ - this.explosionZ;
					double d13 = MathHelper.sqrt_double(d5 * d5 + d7 * d7 + d9 * d9);

					if (d13 != 0.0D)
					{
						d5 /= d13;
						d7 /= d13;
						d9 /= d13;
						double d14 = this.worldObj.getBlockDensity(vec3, entity.getEntityBoundingBox());
						double d10 = (1.0D - d12) * d14;
						entity.attackEntityFrom(DamageSource.setExplosionSource(this), (int)((d10 * d10 + d10) / 2.0D * 8.0D * f3 + 1.0D));
						double d11 = EnchantmentProtection.func_92092_a(entity, d10);
						entity.motionX += d5 * d11;
						entity.motionY += d7 * d11;
						entity.motionZ += d9 * d11;

						if (entity instanceof EntityPlayer)
						{
							this.field_77288_k.put(entity, new Vec3(d5 * d10, d7 * d10, d9 * d10));
						}
					}
				}
			}
		}
	}

	@Override
	public void doExplosionB(boolean p_77279_1_)
	{
		this.worldObj.playSoundEffect(this.explosionX, this.explosionY, this.explosionZ, "random.explode", 4.0F, (1.0F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F) * 0.7F);

		if (this.explosionSize >= 2.0F && this.isSmoking)
		{
			this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.explosionX, this.explosionY, this.explosionZ, 1.0D, 0.0D, 0.0D, new int[0]);
		}
		else
		{
			this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.explosionX, this.explosionY, this.explosionZ, 1.0D, 0.0D, 0.0D, new int[0]);
		}

		Iterator iterator;
		BlockPos blockpos;

		if (this.isSmoking)
		{
			iterator = this.affectedBlockPositions.iterator();

			while (iterator.hasNext())
			{
				blockpos = (BlockPos)iterator.next();
				Block block = this.worldObj.getBlockState(blockpos).getBlock();

				if (p_77279_1_)
				{
					double d0 = blockpos.getX() + this.worldObj.rand.nextFloat();
					double d1 = blockpos.getY() + this.worldObj.rand.nextFloat();
					double d2 = blockpos.getZ() + this.worldObj.rand.nextFloat();
					double d3 = d0 - this.explosionX;
					double d4 = d1 - this.explosionY;
					double d5 = d2 - this.explosionZ;
					double d6 = MathHelper.sqrt_double(d3 * d3 + d4 * d4 + d5 * d5);
					d3 /= d6;
					d4 /= d6;
					d5 /= d6;
					double d7 = 0.5D / (d6 / this.explosionSize + 0.1D);
					d7 *= this.worldObj.rand.nextFloat() * this.worldObj.rand.nextFloat() + 0.3F;
					d3 *= d7;
					d4 *= d7;
					d5 *= d7;
					this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (d0 + this.explosionX * 1.0D) / 2.0D, (d1 + this.explosionY * 1.0D) / 2.0D, (d2 + this.explosionZ * 1.0D) / 2.0D, d3, d4, d5, new int[0]);
					this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, d3, d4, d5, new int[0]);
				}

				if (block.getMaterial() != Material.air)
				{
					if (block.canDropFromExplosion(this))
					{
						block.dropBlockAsItemWithChance(this.worldObj, blockpos, this.worldObj.getBlockState(blockpos), 1.0F / this.explosionSize, 0);
					}
					block.onBlockExploded(this.worldObj, blockpos, this);
				}
			}
		}

		if (this.isFlaming)
		{
			iterator = this.affectedBlockPositions.iterator();

			while (iterator.hasNext())
			{
				blockpos = (BlockPos)iterator.next();

				if (this.worldObj.getBlockState(blockpos).getBlock().getMaterial() == Material.air && this.worldObj.getBlockState(blockpos.down()).getBlock().isFullBlock() && this.explosionRNG.nextInt(3) == 0)
				{
					this.worldObj.setBlockState(blockpos, KapteynBBlocks.uranium_waste.getDefaultState());
				}
			}
		}
	}

	@Override
	public Map func_77277_b()
	{
		return this.field_77288_k;
	}

	@Override
	public EntityLivingBase getExplosivePlacedBy()
	{
		return this.exploder == null ? null : this.exploder instanceof EntityUraniumBomb ? ((EntityUraniumBomb)this.exploder).getTntPlacedBy() : this.exploder instanceof EntityLivingBase ? (EntityLivingBase)this.exploder : null;
	}

	@Override
	public void func_180342_d()
	{
		this.affectedBlockPositions.clear();
	}

	@Override
	public List func_180343_e()
	{
		return this.affectedBlockPositions;
	}

	@Override
	public Vec3 getPosition()
	{
		return this.position;
	}
}