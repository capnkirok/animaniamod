package com.animania.client.models.item;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.animania.client.render.item.RenderAnimatedEgg;
import com.mojang.math.Matrix4f;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms.TransformType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

public class AnimatedEggModelWrapper implements BakedModel
{

	private BakedModel old;

	public AnimatedEggModelWrapper(BakedModel old)
	{
		this.old = old;
	}

	@Override
	public List<BakedQuad> getQuads(BlockState state, Direction side, long rand)
	{
		return this.old.getQuads(state, side, rand);
	}

	@Override
	public boolean isAmbientOcclusion()
	{
		return this.old.isAmbientOcclusion();
	}

	@Override
	public boolean isGui3d()
	{
		return this.old.isGui3d();
	}

	@Override
	public boolean isBuiltInRenderer()
	{
		return true;
	}

	public BakedModel getInternal()
	{
		return this.old;
	}

	public void setInternal(BakedModel internal)
	{
		this.old = internal;
	}

	@Override
	public TextureAtlasSprite getParticleTexture()
	{
		return this.old.getParticleTexture();
	}

	@Override
	public ItemOverrides getOverrides()
	{
		return ItemOverrideList.NONE;
	}

	@Override
	public Pair<? extends IBakedModel, Matrix4f> handlePerspective(TransformType cameraTransformType)
	{
		RenderAnimatedEgg.transformType = cameraTransformType;
		return Pair.of(this, this.old.handlePerspective(cameraTransformType).getRight());
	}

}
