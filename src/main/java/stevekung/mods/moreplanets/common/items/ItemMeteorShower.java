/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.items;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.network.MeteorMessageToServer;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public class ItemMeteorShower extends ItemMorePlanets
{
	public ItemMeteorShower(String name)
	{
		super();
		this.setMaxStackSize(1);
		this.setUnlocalizedName(name);
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (!world.isRemote)
		{
			return true;
		}
		Vec3 targetLocation = new Vec3(pos.getX()+ 0.5, pos.getY() + 1.1, pos.getZ() + 0.5);
		this.callAirstrikeOnTarget(targetLocation);
		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		if (!world.isRemote)
		{
			return itemStack;
		}
		Vec3 playerLook = player.getLookVec();
		Vec3 playerFeetPosition = player.getPositionEyes(1.0F).subtract(0, player.getEyeHeight(), 0);
		Vec3 targetPosition = playerFeetPosition.addVector(playerLook.xCoord * 6.0D, 0.1D, playerLook.zCoord * 6.0D);
		this.callAirstrikeOnTarget(targetPosition);
		return itemStack;
	}

	public void callAirstrikeOnTarget(Vec3 targetPosition)
	{
		MeteorMessageToServer.EnumMeteorType [] choices = MeteorMessageToServer.EnumMeteorType.values();
		MeteorMessageToServer.EnumMeteorType projectile = choices[new Random().nextInt(choices.length)];
		MeteorMessageToServer airstrikeMessageToServer = new MeteorMessageToServer(projectile, targetPosition);
		MorePlanetsCore.simpleNetworkWrapper.sendToServer(airstrikeMessageToServer);
		return;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumAction getItemUseAction(ItemStack itemStack)
	{
		return EnumAction.BLOCK;
	}
}