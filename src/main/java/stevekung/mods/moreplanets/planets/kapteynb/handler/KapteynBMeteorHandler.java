/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import stevekung.mods.moreplanets.planets.kapteynb.dimension.IIceCrystalMeteor;
import stevekung.mods.moreplanets.planets.kapteynb.entities.EntityIceCrystalMeteor;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;

public class KapteynBMeteorHandler
{
	@SubscribeEvent
	public void meteorTickUpdate(LivingUpdateEvent event)
	{
		if (event.entityLiving instanceof EntityPlayerMP)
		{
			this.onPlayerUpdate((EntityPlayerMP) event.entityLiving);
		}
	}

	private void onPlayerUpdate(EntityPlayerMP player)
	{
		this.throwMeteors(player);
	}

	private void throwMeteors(EntityPlayerMP player)
	{
		World world = player.worldObj;

		if (world.provider instanceof IIceCrystalMeteor && FMLCommonHandler.instance().getEffectiveSide() != Side.CLIENT)
		{
			if (((IIceCrystalMeteor) world.provider).getIceCrystalMeteorFrequency() > 0)
			{
				int f = (int) (((IIceCrystalMeteor) world.provider).getIceCrystalMeteorFrequency() * 1000D);

				if (world.rand.nextInt(f) == 0)
				{
					EntityPlayer closestPlayer = world.getClosestPlayerToEntity(player, 100);

					if (closestPlayer == null || closestPlayer.getEntityId() <= player.getEntityId())
					{
						int x, y, z;
						double motX, motZ;
						x = world.rand.nextInt(20) - 10;
						y = world.rand.nextInt(20) + 200;
						z = world.rand.nextInt(20) - 10;
						motX = world.rand.nextDouble() * 5;
						motZ = world.rand.nextDouble() * 5;

						EntityIceCrystalMeteor meteor = new EntityIceCrystalMeteor(world, player.posX + x, player.posY + y, player.posZ + z, motX - 2.5D, 0, motZ - 2.5D, 1);

						if (!world.isRemote)
						{
							world.spawnEntityInWorld(meteor);
						}
					}
				}

				if (world.rand.nextInt(f * 3) == 0)
				{
					EntityPlayer closestPlayer = world.getClosestPlayerToEntity(player, 100);

					if (closestPlayer == null || closestPlayer.getEntityId() <= player.getEntityId())
					{
						int x, y, z;
						double motX, motZ;
						x = world.rand.nextInt(20) - 10;
						y = world.rand.nextInt(20) + 200;
						z = world.rand.nextInt(20) - 10;
						motX = world.rand.nextDouble() * 5;
						motZ = world.rand.nextDouble() * 5;

						EntityIceCrystalMeteor meteor = new EntityIceCrystalMeteor(world, player.posX + x, player.posY + y, player.posZ + z, motX - 2.5D, 0, motZ - 2.5D, 6);

						if (!world.isRemote)
						{
							world.spawnEntityInWorld(meteor);
						}
					}
				}
			}
		}
	}
}