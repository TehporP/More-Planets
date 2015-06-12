/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.world.gen.village;

public class StructureFronosVillagePieceWeight
{
	public Class<? extends ComponentFronosVillage> villagePieceClass;
	public final int villagePieceWeight;
	public int villagePiecesSpawned;
	public int villagePiecesLimit;

	public StructureFronosVillagePieceWeight(Class<? extends ComponentFronosVillage> component, int weight, int limit)
	{
		this.villagePieceClass = component;
		this.villagePieceWeight = weight;
		this.villagePiecesLimit = (int) (limit / 1.5D);
	}

	public boolean canSpawnMoreVillagePiecesOfType(int par1)
	{
		return this.villagePiecesLimit == 0 || this.villagePiecesSpawned < this.villagePiecesLimit;
	}

	public boolean canSpawnMoreVillagePieces()
	{
		return this.villagePiecesLimit == 0 || this.villagePiecesSpawned < this.villagePiecesLimit;
	}
}