/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.europa.entities;

import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.MovingSound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EuropaGuardianSound extends MovingSound
{
	private EntityEuropaGuardian guardian;

	public EuropaGuardianSound(EntityEuropaGuardian guardian)
	{
		super(new ResourceLocation("minecraft:mob.guardian.attack"));
		this.guardian = guardian;
		this.attenuationType = ISound.AttenuationType.NONE;
		this.repeat = true;
		this.repeatDelay = 0;
	}

	@Override
	public void update()
	{
		if (!this.guardian.isDead && this.guardian.func_175474_cn())
		{
			this.xPosF = (float)this.guardian.posX;
			this.yPosF = (float)this.guardian.posY;
			this.zPosF = (float)this.guardian.posZ;
			float f = this.guardian.func_175477_p(0.0F);
			this.volume = 0.0F + 1.0F * f * f;
			this.pitch = 0.7F + 0.5F * f;
		}
		else
		{
			this.donePlaying = true;
		}
	}
}