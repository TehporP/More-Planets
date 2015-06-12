/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.eventhandler;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import stevekung.mods.moreplanets.common.world.ILightningStorm;

public class LightningStormHandler
{
	@SubscribeEvent
	public void lightningStormTickUpdate(LivingUpdateEvent event)
	{
		if (event.entityLiving instanceof EntityPlayerMP)
		{
			this.onPlayerUpdate((EntityPlayerMP) event.entityLiving);
		}
	}

	private void onPlayerUpdate(EntityPlayerMP player)
	{
		this.spawnLightning(player);
	}

	private void spawnLightning(EntityPlayerMP player)
	{
		World world = player.worldObj;

		if (world.provider instanceof ILightningStorm && FMLCommonHandler.instance().getEffectiveSide() != Side.CLIENT)
		{
			if (((ILightningStorm)world.provider).getLightningStormFrequency() > 0)
			{
				int f = (int)(((ILightningStorm)world.provider).getLightningStormFrequency() * 1000D);

				if (world.rand.nextInt(f) == 0)
				{
					EntityPlayer closestPlayer = world.getClosestPlayerToEntity(player, 100);

					if (closestPlayer == null || closestPlayer.getEntityId() <= player.getEntityId())
					{
						double x = player.posX + world.rand.nextInt(10) - 5;
						double y = player.posY + world.rand.nextInt(10) + 10;
						double z = player.posZ + world.rand.nextInt(10) - 5;
						EntityLightningBolt lightning = new EntityLightningBolt(world, x, y, z);

						if (!world.isRemote)
						{
							world.spawnEntityInWorld(lightning);
						}
					}
				}

				if (world.rand.nextInt(f * 3) == 0)
				{
					EntityPlayer closestPlayer = world.getClosestPlayerToEntity(player, 100);

					if (closestPlayer == null || closestPlayer.getEntityId() <= player.getEntityId())
					{
						double x = player.posX + world.rand.nextInt(10) - 5;
						double y = player.posY + world.rand.nextInt(10) + 10;
						double z = player.posZ + world.rand.nextInt(10) - 5;
						EntityLightningBolt lightning = new EntityLightningBolt(world, x, y, z);

						if (!world.isRemote)
						{
							for (int i = 0; i < 3; i++)
							{
								world.spawnEntityInWorld(lightning);
							}
						}
					}
				}
			}
		}
	}
}