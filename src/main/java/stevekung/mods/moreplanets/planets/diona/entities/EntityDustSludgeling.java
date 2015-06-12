/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.entities;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.init.MPItems;

public class EntityDustSludgeling extends EntityMob /*implements IEntityBreathable*/
{
	public EntityDustSludgeling(World world)
	{
		super(world);
		this.setSize(0.2F, 0.2F);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, 0.25F, true));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		/*this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityEvolvedZombie.class, false, true));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityEvolvedSkeleton.class, false, true));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityEvolvedSpider.class, false, true));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityEvolvedCreeper.class, false, true));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntitySlimeling.class, false));*/
	}

	@Override
	public ItemStack getPickedResult(MovingObjectPosition moving)
	{
		return new ItemStack(MPItems.spawn_egg_mp, 1, 1002);
	}

	@Override
	public boolean canBreatheUnderwater()
	{
		return true;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(7.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.6000000238418579D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
	}

	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}

	@Override
	protected String getLivingSound()
	{
		return "mob.silverfish.say";
	}

	@Override
	protected String getHurtSound()
	{
		return "mob.silverfish.hit";
	}

	@Override
	protected String getDeathSound()
	{
		return "mob.silverfish.kill";
	}

	@Override
	public float getEyeHeight()
	{
		return 0.1F;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block block)
	{
		this.worldObj.playSoundAtEntity(this, "mob.silverfish.step", 1.0F, 1.0F);
	}

	@Override
	public void onUpdate()
	{
		this.renderYawOffset = this.rotationYaw;
		super.onUpdate();
	}

	@Override
	public void onLivingUpdate()
	{
		Block block = this.worldObj.getBlockState(this.getPosition().down()).getBlock();

		/*if (this.worldObj.provider instanceof WorldProviderDiona)
		{
			for (int i = 0; i < 1; i++)
			{
				if (this.worldObj.rand.nextInt(8) == 0)
				{
					if (block == DionaBlocks.diona_block.getDefaultState().withProperty(BlockDiona.VARIANT, BlockDiona.BlockType.diona_surface_rock))
					{
						this.worldObj.spawnParticle(EnumParticleTypes.BLOCK_CRACK, this.getPosition().getX() + this.worldObj.rand.nextFloat(), this.getPosition().getY() + 0.1F, this.getPosition().getZ() + this.worldObj.rand.nextFloat(), 0.0D, 0.0D, 0.0D, new int[] {Block.getStateId(DionaBlocks.diona_block.getDefaultState().withProperty(BlockDiona.VARIANT, BlockDiona.BlockType.diona_surface_rock))});
					}
					else if (block == DionaBlocks.diona_block.getDefaultState().withProperty(BlockDiona.VARIANT, BlockDiona.BlockType.diona_sub_surface_rock))
					{
						this.worldObj.spawnParticle(EnumParticleTypes.BLOCK_CRACK, this.getPosition().getX() + this.worldObj.rand.nextFloat(), this.getPosition().getY() + 0.1F, this.getPosition().getZ() + this.worldObj.rand.nextFloat(), 0.0D, 0.0D, 0.0D, new int[] {Block.getStateId(DionaBlocks.diona_block.getDefaultState().withProperty(BlockDiona.VARIANT, BlockDiona.BlockType.diona_sub_surface_rock))});
					}
				}
			}
		}*/
		super.onLivingUpdate();
	}

	@Override
	protected boolean isValidLightLevel()
	{
		return true;
	}

	@Override
	public boolean getCanSpawnHere()
	{
		if (super.getCanSpawnHere())
		{
			EntityPlayer player = this.worldObj.getClosestPlayerToEntity(this, 5.0D);
			return player == null;
		}
		else
		{
			return false;
		}
	}

	/*@Override
	public boolean canBreath()
	{
		return true;
	}*/

	@Override
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return EnumCreatureAttribute.ARTHROPOD;
	}
}