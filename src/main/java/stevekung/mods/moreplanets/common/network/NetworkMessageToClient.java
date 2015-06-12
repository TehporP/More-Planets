/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class NetworkMessageToClient implements IMessage
{
	private Vec3 targetCoordinates;
	private boolean messageIsValid;

	public NetworkMessageToClient(Vec3 vec3)
	{
		this.targetCoordinates = vec3;
		this.messageIsValid = true;
	}

	public Vec3 getTargetCoordinates()
	{
		return this.targetCoordinates;
	}

	public boolean isMessageValid()
	{
		return this.messageIsValid;
	}

	public NetworkMessageToClient()
	{
		this.messageIsValid = false;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		try
		{
			double x = buf.readDouble();
			double y = buf.readDouble();
			double z = buf.readDouble();
			this.targetCoordinates = new Vec3(x, y, z);

		}
		catch (IndexOutOfBoundsException ioe)
		{
			System.err.println("Exception while reading NetworkMessageToClient: " + ioe);
			return;
		}
		this.messageIsValid = true;
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		if (!this.messageIsValid)
		{
			return;
		}
		buf.writeDouble(this.targetCoordinates.xCoord);
		buf.writeDouble(this.targetCoordinates.yCoord);
		buf.writeDouble(this.targetCoordinates.zCoord);
	}

	@Override
	public String toString()
	{
		return "NetworkMessageToClient[targetCoordinates=" + String.valueOf(this.targetCoordinates) + "]";
	}
}