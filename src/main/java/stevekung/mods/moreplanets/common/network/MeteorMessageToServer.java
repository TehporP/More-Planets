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

public class MeteorMessageToServer implements IMessage
{
	private Vec3 targetCoordinates;
	private EnumMeteorType projectile;
	private boolean messageIsValid;

	public MeteorMessageToServer(EnumMeteorType projectile, Vec3 vec3)
	{
		this.projectile = projectile;
		this.targetCoordinates = vec3;
		this.messageIsValid = true;
	}

	public Vec3 getTargetCoordinates()
	{
		return this.targetCoordinates;
	}

	public EnumMeteorType getProjectile()
	{
		return this.projectile;
	}

	public boolean isMessageValid()
	{
		return this.messageIsValid;
	}

	public MeteorMessageToServer()
	{
		this.messageIsValid = false;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		try
		{
			this.projectile = EnumMeteorType.fromBytes(buf);
			double x = buf.readDouble();
			double y = buf.readDouble();
			double z = buf.readDouble();
			this.targetCoordinates = new Vec3(x, y, z);

		}
		catch (IndexOutOfBoundsException ioe)
		{
			System.err.println("Exception while reading AirStrikeMessageToServer: " + ioe);
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
		this.projectile.toBytes(buf);
		buf.writeDouble(this.targetCoordinates.xCoord);
		buf.writeDouble(this.targetCoordinates.yCoord);
		buf.writeDouble(this.targetCoordinates.zCoord);
	}

	public enum EnumMeteorType
	{
		METEOR(1, "METEOR"),
		KOENTUS_METEOR(2, "KOENTUS_METEOR"),
		POLONGNIUS_METEOR(3, "POLONGNIUS_METEOR"),
		ICE_CRYSTAL_METEOR(4, "ICE_CRYSTAL_METEOR");

		private byte projectileID;
		private String name;

		private EnumMeteorType(int id, String name)
		{
			this.projectileID = (byte)id;
			this.name = name;
		}

		public void toBytes(ByteBuf buffer)
		{
			buffer.writeByte(this.projectileID);
		}

		public static EnumMeteorType fromBytes(ByteBuf buffer)
		{
			byte ID = buffer.readByte();

			for (EnumMeteorType projectile : EnumMeteorType.values())
			{
				if (ID == projectile.projectileID)
				{
					return projectile;
				}
			}
			return null;
		}

		@Override
		public String toString()
		{
			return this.name;
		}
	}

	@Override
	public String toString()
	{
		return "AirstrikeMessageToServer[projectile=" + String.valueOf(this.projectile) + ", targetCoordinates=" + String.valueOf(this.targetCoordinates) + "]";
	}
}