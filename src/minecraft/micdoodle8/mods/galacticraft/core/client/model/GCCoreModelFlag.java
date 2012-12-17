package micdoodle8.mods.galacticraft.core.client.model;

import micdoodle8.mods.galacticraft.core.entities.GCCoreEntityFlag;
import net.minecraft.src.Entity;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;
import net.minecraft.src.RenderEngine;
import net.minecraft.src.StringUtils;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

public class GCCoreModelFlag extends ModelBase
{
	ModelRenderer base;
	ModelRenderer pole;
	ModelRenderer flag;
	ModelRenderer picSide1;
	ModelRenderer picSide2;

	public GCCoreModelFlag()
	{
		this.textureWidth = 128;
		this.textureHeight = 64;
		this.base = new ModelRenderer(this, 4, 0);
		this.base.addBox(-1.5F, 0F, -1.5F, 3, 1, 3);
		this.base.setRotationPoint(0F, 23F, 0F);
		this.base.setTextureSize(128, 64);
		this.base.mirror = true;
		this.setRotation(this.base, 0F, 0F, 0F);
		this.pole = new ModelRenderer(this, 0, 0);
		this.pole.addBox(-0.5F, -40F, -0.5F, 1, 40, 1);
		this.pole.setRotationPoint(0F, 23F, 0F);
		this.pole.setTextureSize(128, 64);
		this.pole.mirror = true;
		this.setRotation(this.pole, 0F, 0F, 0F);
		this.flag = new ModelRenderer(this, 86, 0);
		this.flag.addBox(0F, 0F, 0F, 20, 12, 1);
		this.flag.setRotationPoint(0.5F, -16F, -0.5F);
		this.flag.setTextureSize(128, 64);
		this.flag.mirror = true;
		this.setRotation(this.flag, 0F, 0F, 0F);
		this.picSide1 = new ModelRenderer(this, 16, 16);
		this.picSide1.addBox(0F, 0F, 0F, 16, 16, 0);
		this.picSide1.setRotationPoint(29F, -28F, 1.1F);
		this.picSide1.setTextureSize(128, 64);
		this.picSide1.mirror = true;
	    this.setRotation(this.picSide1, 0F, 0F, 0F);
	    this.picSide2 = new ModelRenderer(this, 16, 16);
	    this.picSide2.addBox(0F, 0F, 0F, 16, 16, 0);
	    this.picSide2.setRotationPoint(13F, -28F, -1.1F);
	    this.picSide2.setTextureSize(128, 64);
	    this.picSide2.mirror = false;
	    this.setRotation(this.picSide2, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(entity, f, f1, f2, f3, f4, f5);
		this.base.render(f5);
		this.pole.render(f5);
		this.flag.render(f5);
		
		if (((GCCoreEntityFlag)entity).getType() != 0)
		{
			GL11.glScalef(0.5F, 0.5F, 0.5F);
			this.loadDownloadableImageTexture("http://skins.minecraft.net/MinecraftSkins/" + StringUtils.stripControlCodes(((GCCoreEntityFlag)entity).getOwner()) + ".png", FMLClientHandler.instance().getClient().thePlayer.getTexture());
			this.picSide1.render(f5);
			this.picSide2.render(f5);
		}
	}

	public void renderFlagOnly(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.setRotationAngles(entity, f, f1, f2, f3, f4, f5);
		this.flag.render(f5);
		
		if (((GCCoreEntityFlag)entity).getType() == 1)
		{
			GL11.glScalef(0.5F, 0.5F, 0.5F);
			this.loadDownloadableImageTexture("http://skins.minecraft.net/MinecraftSkins/" + StringUtils.stripControlCodes(((GCCoreEntityFlag)entity).getOwner()) + ".png", FMLClientHandler.instance().getClient().thePlayer.getTexture());
			this.picSide1.render(f5);
			this.picSide2.render(f5);
		}
	}
	
    protected boolean loadDownloadableImageTexture(String par1Str, String par2Str)
    {
        final RenderEngine var3 = FMLClientHandler.instance().getClient().renderEngine;
        final int var4 = var3.getTextureForDownloadableImage(par1Str, par2Str);

        if (var4 >= 0)
        {
            var3.bindTexture(var4);
            return true;
        }
        else
        {
            return false;
        }
    }

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.picSide1.rotateAngleY = (float) Math.PI;
	}
}
