package com.animania.client.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.animania.Animania;
import com.animania.api.data.Pose;
import com.animania.api.rendering.ModelPose;
import com.google.common.base.Supplier;

import net.minecraft.world.level.block.LevelEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Dist.CLIENT)
@EventBusSubscriber(modid = Animania.MODID, value = Dist.CLIENT)
public class AnimationHandler
{
	private static final Map<Pair, ModelPose> handlers = new HashMap<>();

	@SubscribeEvent
	public static void onLevelLeave(LevelEvent.Unload event)
	{
		handlers.clear();
	}

	public static ModelPose getOrCreatePose(Entity entity, Pose pose, Supplier<ModelPose> poseSupplier)
	{
		Pair pair = new Pair(entity, pose);
		if (handlers.containsKey(pair))
			return handlers.get(pair);

		ModelPose p = poseSupplier.get();
		handlers.put(pair, p);
		return p;
	}

	private static class Pair
	{
		UUID e;
		Pose p;

		public Pair(Entity e, Pose p)
		{
			this.e = e.getUniqueID();
			this.p = p;
		}

		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + ((e == null) ? 0 : e.hashCode());
			result = prime * result + ((p == null) ? 0 : p.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj)
		{
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pair other = (Pair) obj;
			if (other.e.equals(this.e) && other.p == other.p)
				return true;
			return false;
		}
	}
}
