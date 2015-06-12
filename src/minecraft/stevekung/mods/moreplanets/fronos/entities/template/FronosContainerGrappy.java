/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.entities.template;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import stevekung.mods.moreplanets.fronos.entities.FronosEntityGrappy;

public class FronosContainerGrappy extends Container
{
	public final FronosEntityGrappy field_90034_a;

	public FronosContainerGrappy(FronosEntityGrappy par1EntitySheep)
	{
		this.field_90034_a = par1EntitySheep;
	}

	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer)
	{
		return false;
	}
}