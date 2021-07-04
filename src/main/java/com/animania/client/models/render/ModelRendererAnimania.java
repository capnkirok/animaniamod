package com.animania.client.models.render;

import java.nio.FloatBuffer;

import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;

import com.leviathanstudio.craftstudio.client.util.MathHelper;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModelRendererAnimania extends ModelRenderer
{

	private boolean compiled;
	private int displayList;

	
	public ModelRendererAnimania(ModelBase model, int texOffX, int texOffY)
	{
		super(model, texOffX, texOffY);
	}

	//Credit: CraftStudioAPI
	@SideOnly(Dist.CLIENT)
	public void render(float scale)
	{
		if (!this.isHidden)
			if (this.showModel)
			{
				if (!this.compiled)
					this.compileDisplayList(scale);

				GlStateManager.pushMatrix();

				GlStateManager.translate(this.rotationPointX * scale, this.rotationPointY * scale, this.rotationPointZ * scale);
				
				Matrix4f mat = new Matrix4f();
				Quat4f quat = MathHelper.quatFromEuler(this.rotateAngleX * (180F / (float) Math.PI), this.rotateAngleY * (180F / (float) Math.PI), this.rotateAngleZ * (180F / (float) Math.PI));
				mat.set(quat);
				mat.transpose();
								
				FloatBuffer buf = MathHelper.makeFloatBuffer(mat);				
				GlStateManager.multMatrix(buf);
				GlStateManager.translate(this.offsetX * scale, this.offsetY * scale, this.offsetZ * scale);

				GlStateManager.pushMatrix();
				GlStateManager.callList(this.displayList);
				GlStateManager.popMatrix();

				if (this.childModels != null)
					for (int i = 0; i < this.childModels.size(); ++i)
						this.childModels.get(i).render(scale);

				GlStateManager.popMatrix();

			}
	}

	@SideOnly(Dist.CLIENT)
	private void compileDisplayList(float scale)
	{
		this.displayList = GLAllocation.generateDisplayLists(1);
		GlStateManager.glNewList(this.displayList, 4864);
		BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();

		for (int i = 0; i < this.cubeList.size(); ++i)
		{
			((ModelBox) this.cubeList.get(i)).render(bufferbuilder, scale);
		}

		GlStateManager.glEndList();
		this.compiled = true;
	}
	
	public void setOffset(float offsetX, float offsetY, float offsetZ)
	{
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.offsetZ = offsetZ;
	}
	
	
}
