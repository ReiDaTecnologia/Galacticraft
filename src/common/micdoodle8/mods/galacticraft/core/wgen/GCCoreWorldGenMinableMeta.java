package micdoodle8.mods.galacticraft.core.wgen;

import java.util.Random;

import net.minecraft.src.MathHelper;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenMinable;

/**
 * Copyright 2012, micdoodle8
 * 
 *  All rights reserved.
 *
 */
public class GCCoreWorldGenMinableMeta extends WorldGenMinable 
{
	private final int minableBlockId;

	private final int numberOfBlocks;

	private final int metadata;

	private boolean usingMetadata = false;
	
	private final int fillerID;

	public GCCoreWorldGenMinableMeta(int par1, int par2, int par3, boolean par4, int id)
	{
		super(par1, par2);
		this.minableBlockId = par1;
		this.numberOfBlocks = par2;
		this.metadata = par3;
		this.usingMetadata = par4;
		this.fillerID = id;
	}

	@Override
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) 
	{
		final float var6 = par2Random.nextFloat() * (float) Math.PI;
		final double var7 = par3 + 8 + MathHelper.sin(var6) * this.numberOfBlocks / 8.0F;
		final double var9 = par3 + 8 - MathHelper.sin(var6) * this.numberOfBlocks	/ 8.0F;
		final double var11 = par5 + 8 + MathHelper.cos(var6) * this.numberOfBlocks / 8.0F;
		final double var13 = par5 + 8 - MathHelper.cos(var6) * this.numberOfBlocks / 8.0F;
		final double var15 = par4 + par2Random.nextInt(3) - 2;
		final double var17 = par4 + par2Random.nextInt(3) - 2;

		for (int var19 = 0; var19 <= this.numberOfBlocks; ++var19) 
		{
			final double var20 = var7 + (var9 - var7) * var19 / this.numberOfBlocks;
			final double var22 = var15 + (var17 - var15) * var19 / this.numberOfBlocks;
			final double var24 = var11 + (var13 - var11) * var19 / this.numberOfBlocks;
			final double var26 = par2Random.nextDouble() * this.numberOfBlocks / 16.0D;
			final double var28 = (MathHelper.sin(var19 * (float) Math.PI / this.numberOfBlocks) + 1.0F) * var26 + 1.0D;
			final double var30 = (MathHelper.sin(var19 * (float) Math.PI / this.numberOfBlocks) + 1.0F) * var26 + 1.0D;
			final int var32 = MathHelper.floor_double(var20 - var28 / 2.0D);
			final int var33 = MathHelper.floor_double(var22 - var30 / 2.0D);
			final int var34 = MathHelper.floor_double(var24 - var28 / 2.0D);
			final int var35 = MathHelper.floor_double(var20 + var28 / 2.0D);
			final int var36 = MathHelper.floor_double(var22 + var30 / 2.0D);
			final int var37 = MathHelper.floor_double(var24 + var28 / 2.0D);

			for (int var38 = var32; var38 <= var35; ++var38)
			{
				final double var39 = (var38 + 0.5D - var20) / (var28 / 2.0D);

				if (var39 * var39 < 1.0D) 
				{
					for (int var41 = var33; var41 <= var36; ++var41)
					{
						final double var42 = (var41 + 0.5D - var22) / (var30 / 2.0D);

						if (var39 * var39 + var42 * var42 < 1.0D) 
						{
							for (int var44 = var34; var44 <= var37; ++var44) 
							{
								final double var45 = (var44 + 0.5D - var24) / (var28 / 2.0D);

								if (var39 * var39 + var42 * var42 + var45 * var45 < 1.0D && par1World.getBlockId(var38, var41, var44) == this.fillerID && this.usingMetadata == false) 
								{
									par1World.setBlock(var38, var41, var44, this.minableBlockId);
								} 
								else if (var39 * var39 + var42 * var42 + var45 * var45 < 1.0D && par1World.getBlockId(var38, var41, var44) == this.fillerID && this.usingMetadata == true) 
								{
									par1World.setBlockAndMetadata(var38, var41,	var44, this.minableBlockId, this.metadata);
								}
							}
						}
					}
				}
			}
		}

		return true;
	}
}
