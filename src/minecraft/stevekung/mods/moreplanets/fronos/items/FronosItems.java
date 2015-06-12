/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import stevekung.mods.moreplanets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.fronos.itemblocks.FronosItemBlockCake;
import stevekung.mods.moreplanets.fronos.itemblocks.FronosItemBlockPinkCake;
import stevekung.mods.moreplanets.fronos.util.FronosConfigManager;
import cpw.mods.fml.common.registry.GameRegistry;

public class FronosItems
{
	public static Item fronosFood;
	public static Item strawberrySeed;
	public static Item bearryEgg;
	public static Item fronosDisc;
	public static Item creamBall;
	public static Item pearl;
	public static Item fronosDungeonKey;
	public static Item candyCane;
	public static Item greenTopazBucket;
	public static Item jelly;
	public static Item fronosGem;
	public static Item fronosFood2;
	public static Item pinkCakeBlock;
	public static Item cakeBlock;
	public static Item fruits;
	public static Item candyBow;
	public static Item poisonArrow;
	public static Item goldenSeed;
	public static Item glassGemCorn;
	public static Item cup;

	public static void initItems()
	{
		FronosItems.fronosFood = new FronosItemFood(FronosConfigManager.idFronosItemFood, 2).setUnlocalizedName("fronosFood");
		FronosItems.strawberrySeed = new FronosItemStrawberrySeed(FronosConfigManager.idStrawberrySeed).setUnlocalizedName("strawberrySeed");
		FronosItems.bearryEgg = new FronosItemBearryEgg(FronosConfigManager.idBearryEgg).setUnlocalizedName("bearryEgg");
		FronosItems.fronosDisc = new FronosItemRecord(FronosConfigManager.idItemDisc, "fronosDisc").setUnlocalizedName("fronosDisc");
		FronosItems.creamBall = new FronosItemCreamBall(FronosConfigManager.idItemCreamBall).setUnlocalizedName("creamBall");
		FronosItems.pearl = new FronosItemPearl(FronosConfigManager.idItemPearl).setUnlocalizedName("pearl");
		FronosItems.fronosDungeonKey = new FronosItemDungeonKey(FronosConfigManager.idItemFronosDungeonKey).setUnlocalizedName("fronosDungeonKey");
		FronosItems.candyCane = new FronosItemCandyCane(FronosConfigManager.idItemCandyCane, 2).setUnlocalizedName("candyCane");
		FronosItems.greenTopazBucket = new FronosItemBucket(FronosConfigManager.idItemGreenTopazBucket).setUnlocalizedName("gtb");
		FronosItems.jelly = new FronosItemJelly(FronosConfigManager.idItemJelly, 4).setUnlocalizedName("jellys");
		FronosItems.fronosGem = new FronosItemGem(FronosConfigManager.idItemGem).setUnlocalizedName("fronosGem");
		FronosItems.fronosFood2 = new FronosItemFood2(FronosConfigManager.idItemFronosFood, 2).setUnlocalizedName("fronosFood2");
		FronosItems.pinkCakeBlock = new FronosItemBlockPinkCake(FronosConfigManager.idItemPinkCake, FronosBlocks.pinkCakeBlock).setUnlocalizedName("pinkCakeItem");
		FronosItems.cakeBlock = new FronosItemBlockCake(FronosConfigManager.idItemCake, FronosBlocks.cakeBlock).setUnlocalizedName("cakeItem");
		FronosItems.fruits = new FronosItemFruits(FronosConfigManager.idItemFruits, 4).setUnlocalizedName("fruits");
		FronosItems.candyBow = new FronosItemCandyBow(FronosConfigManager.idItemCandyBow).setUnlocalizedName("candyBow");
		FronosItems.poisonArrow = new FronosItemPoisonArrow(FronosConfigManager.idItemPoisonArrow).setUnlocalizedName("poisonArrow");
		FronosItems.goldenSeed = new FronosItemGoldenSeed(FronosConfigManager.idItemGoldenSeeds).setUnlocalizedName("goldenSeeds");
		FronosItems.glassGemCorn = new FronosItemGlassGemCornSeed(FronosConfigManager.idItemGlassGemCorn, 4).setUnlocalizedName("glassGemCornItem");
		FronosItems.cup = new FronosItemCup(FronosConfigManager.idItemOvantineCup, 6).setUnlocalizedName("cup");

		fluidContainerRegister();
		registerItems();
	}

	public static void registerItems()
	{
		registerItem(fronosFood);
		registerItem(strawberrySeed);
		registerItem(bearryEgg);
		registerItem(fronosDisc);
		registerItem(creamBall);
		registerItem(pearl);
		registerItem(fronosDungeonKey);
		registerItem(candyCane);
		registerItem(greenTopazBucket);
		registerItem(jelly);
		registerItem(fronosGem);
		registerItem(fronosFood2);
		registerItem(pinkCakeBlock);
		registerItem(cakeBlock);
		registerItem(fruits);
		registerItem(candyBow);
		registerItem(poisonArrow);
		registerItem(goldenSeed);
		registerItem(glassGemCorn);
		registerItem(cup);
	}

	public static void fluidContainerRegister()
	{
		FluidContainerRegistry.registerFluidContainer(FronosBlocks.coconutMilkFluid, new ItemStack(greenTopazBucket, 1, 1), new ItemStack(greenTopazBucket, 1, 0));
		FluidContainerRegistry.registerFluidContainer(FronosBlocks.mineralWaterFluid, new ItemStack(greenTopazBucket, 1, 2), new ItemStack(greenTopazBucket, 1, 0));
		FluidContainerRegistry.registerFluidContainer(FronosBlocks.ovantineFluid, new ItemStack(greenTopazBucket, 1, 3), new ItemStack(greenTopazBucket, 1, 0));
	}

	public static void registerItem(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName().replace("item.", ""));
	}
}