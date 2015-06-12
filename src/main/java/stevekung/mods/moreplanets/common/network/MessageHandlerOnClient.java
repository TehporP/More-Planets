/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.network;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MessageHandlerOnClient implements IMessageHandler<NetworkMessageToClient, IMessage>
{
	@Override
	public IMessage onMessage(final NetworkMessageToClient message, MessageContext ctx)
	{
		if (ctx.side != Side.CLIENT)
		{
			System.err.println("NetworkMessageToClient received on wrong side:" + ctx.side);
			return null;
		}
		if (!message.isMessageValid())
		{
			System.err.println("NetworkMessageToClient was invalid" + message.toString());
			return null;
		}

		Minecraft.getMinecraft().addScheduledTask(new Runnable()
		{
			@Override
			public void run()
			{
				MessageHandlerOnClient.this.processMessage(Minecraft.getMinecraft().theWorld, message);
			}
		});
		return null;
	}

	void processMessage(WorldClient worldClient, NetworkMessageToClient message)
	{
		Random rand = new Random();

		for (int i = 0; i < 100; ++i)
		{
			Vec3 targetCoordinates = message.getTargetCoordinates();
			double spawnXpos = targetCoordinates.xCoord + (2 * rand.nextDouble() - 1) * 1.5D;
			double spawnYpos = targetCoordinates.yCoord;
			double spawnZpos = targetCoordinates.zCoord + (2 * rand.nextDouble() - 1) * 1.5D;
			worldClient.spawnParticle(EnumParticleTypes.SPELL_INSTANT, spawnXpos, spawnYpos, spawnZpos, 0, 0, 0);
		}
		return;
	}
}