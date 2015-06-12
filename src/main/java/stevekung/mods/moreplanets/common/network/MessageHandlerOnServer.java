/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.network;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.moons.koentus.entities.EntityKoentusMeteor;
import stevekung.mods.moreplanets.planets.kapteynb.entities.EntityIceCrystalMeteor;
import stevekung.mods.moreplanets.planets.polongnius.entities.EntityPolongniusMeteor;

public class MessageHandlerOnServer implements IMessageHandler<MeteorMessageToServer, IMessage>
{
	@Override
	public IMessage onMessage(final MeteorMessageToServer message, MessageContext ctx)
	{
		final EntityPlayerMP sendingPlayer = ctx.getServerHandler().playerEntity;

		if (ctx.side != Side.SERVER)
		{
			System.err.println("MeteorMessageToServer received on wrong side:" + ctx.side);
			return null;
		}
		if (!message.isMessageValid())
		{
			System.err.println("MeteorMessageToServer was invalid" + message.toString());
			return null;
		}
		if (sendingPlayer == null)
		{
			System.err.println("EntityPlayerMP was null when MeteorMessageToServer was received");
			return null;
		}

		WorldServer playerWorldServer = sendingPlayer.getServerForPlayer();
		playerWorldServer.addScheduledTask(new Runnable()
		{
			@Override
			public void run()
			{
				MessageHandlerOnServer.this.processMessage(message, sendingPlayer);
			}
		});
		return null;
	}

	void processMessage(MeteorMessageToServer message, EntityPlayerMP sendingPlayer)
	{
		NetworkMessageToClient msg = new NetworkMessageToClient(message.getTargetCoordinates());
		MorePlanetsCore.simpleNetworkWrapper.sendToDimension(msg, sendingPlayer.dimension);
		Random rand = new Random();
		int numberOfProjectiles = 2 + rand.nextInt(20 - 2 + 1);

		for (int i = 0; i < numberOfProjectiles; ++i)
		{
			World world = sendingPlayer.worldObj;
			double xOffset = (rand.nextDouble() * 2 - 1) * 4.0D;
			double zOffset = (rand.nextDouble() * 2 - 1) * 4.0D;
			double yOffset = 80.0D + (rand.nextDouble() * 2 - 1) * 20.0D;
			Vec3 releasePoint = message.getTargetCoordinates().addVector(xOffset, yOffset, zOffset);
			Entity entity;

			switch (message.getProjectile())
			{
			case METEOR:
				entity = new EntityLightningBolt(world, releasePoint.xCoord, 0, releasePoint.zCoord);
				break;
			case KOENTUS_METEOR:
				entity = new EntityKoentusMeteor(world, releasePoint.xCoord, releasePoint.yCoord, releasePoint.zCoord, 0.0D, 0.0D, 0.0D, rand.nextInt(10));
				break;
			case POLONGNIUS_METEOR:
				entity = new EntityPolongniusMeteor(world, releasePoint.xCoord, releasePoint.yCoord, releasePoint.zCoord, 0.0D, 0.0D, 0.0D, rand.nextInt(10));
				break;
			case ICE_CRYSTAL_METEOR:
				entity = new EntityIceCrystalMeteor(world, releasePoint.xCoord, releasePoint.yCoord, releasePoint.zCoord, 0.0D, 0.0D, 0.0D, rand.nextInt(10));
				break;
			default:
				System.err.println("Invalid projectile type in " + String.valueOf(message.getProjectile()));
				return;
			}
			world.spawnEntityInWorld(entity);
			world.playSoundEffect(releasePoint.xCoord, releasePoint.yCoord, releasePoint.zCoord, "ambient.weather.thunder", 10000.0F, 0.8F + rand.nextFloat() * 0.2F);
		}
		return;
	}
}