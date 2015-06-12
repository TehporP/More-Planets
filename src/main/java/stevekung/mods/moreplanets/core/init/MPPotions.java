/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.init;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import stevekung.mods.moreplanets.planets.diona.potion.EMPEffect;
import stevekung.mods.moreplanets.planets.kapteynb.potion.ChemicalEffect;
import stevekung.mods.moreplanets.planets.kapteynb.potion.IceCrystalEffect;
import stevekung.mods.moreplanets.planets.nibiru.potion.InfectedGasEffect;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.relauncher.ReflectionHelper;

public class MPPotions
{
	public static Potion infected_gas;
	public static Potion chemical;
	public static Potion electro_magnetic_pulse;
	public static Potion icy_poison;

	public static int potionOffset;
	private static int nextPotionID = 8;

	public static void init()
	{
		MPPotions.extendPotionsArray();
		MPPotions.intializePotions();
	}

	private static void extendPotionsArray()
	{
		MPPotions.potionOffset = Potion.potionTypes.length;
		Potion[] potionTypes = new Potion[MPPotions.potionOffset + MPPotions.nextPotionID];
		System.arraycopy(Potion.potionTypes, 0, potionTypes, 0, MPPotions.potionOffset);
		MPPotions.setPrivateFinalValue(Potion.class, null, potionTypes, "potionTypes", "field_76425_a");
	}

	private static void intializePotions()
	{
		MPPotions.infected_gas = new InfectedGasEffect(true, -4502242).setPotionName("potion.infected.gas");
		MPPotions.chemical = new ChemicalEffect(true, -16718336).setPotionName("potion.chemical");
		MPPotions.electro_magnetic_pulse = new EMPEffect(true, -14258727).setPotionName("potion.emp").func_111184_a(SharedMonsterAttributes.movementSpeed, "7107DE5E-7CE8-4030-940E-514C1F160890", -2.5D, 2);
		MPPotions.icy_poison = new IceCrystalEffect(true, -6564921).setPotionName("potion.icy_poison").func_111184_a(SharedMonsterAttributes.movementSpeed, "7107DE5E-7CE8-4030-940E-514C1F160890", -0.20000000596046448D, 2);
	}

	public static int getNextID()
	{
		return MPPotions.potionOffset++ - 1;
	}

	static <T, E> void setPrivateFinalValue(Class <? super T > classToAccess, T instance, E value, String... fieldNames)
	{
		Field field = ReflectionHelper.findField(classToAccess, ObfuscationReflectionHelper.remapFieldNames(classToAccess.getName(), fieldNames));

		try
		{
			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
			field.set(instance, value);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}