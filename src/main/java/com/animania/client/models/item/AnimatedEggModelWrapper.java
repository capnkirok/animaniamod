package com.animania.client.models.item;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.animania.client.render.item.RenderAnimatedEgg;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Matrix4f;

public class AnimatedEggModelWrapper implements IBakedModel
{

	private IBakedModel old;
	
	
	public AnimatedEggModelWrapper(IBakedModel old)
	{
		this.old = old;
	}

	@Override
	public List<BakedQuad> getQuads(BlockState state, Direction side, long rand)
	{
		return old.getQuads(state, side, rand);
	}

	@Override
	public boolean isAmbientOcclusion()
	{
		return old.isAmbientOcclusion();
	}

	@Override
	public boolean isGui3d()
	{
		return old.isGui3d();
	}

	@Override
	public boolean isBuiltInRenderer()
	{
		return true;
	}

	public IBakedModel getInternal()
	{
		return old;
	}

	public void setInternal(IBakedModel internal)
	{
		this.old = internal;
	}

	@Override
	public TextureAtlasSprite getParticleTexture()
	{
		return old.getParticleTexture();
	}

	@Override
	public ItemOverrideList getOverrides()
	{
		return ItemOverrideList.NONE;
	}
	
	@Override
	public Pair<? extends IBakedModel, Matrix4f> handlePerspective(TransformType cameraTransformType)
	{
		RenderAnimatedEgg.transformType = cameraTransformType;
		return Pair.of(this, old.handlePerspective(cameraTransformType).getRight());
	}

}
