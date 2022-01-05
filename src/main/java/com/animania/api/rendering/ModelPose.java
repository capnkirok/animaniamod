package com.animania.api.rendering;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.animania.client.models.render.ModelRendererAnimania;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

public class ModelPose
{
	private final Map<String, PartData> originalPose = new HashMap<>();
	private final Map<String, PartData> modelPose = new HashMap<>();

	private final Set<Field> originalFields = new HashSet<>();
	private final ModelBase originalModel;

	private float poseStartTime = 0;
	private float originalStartTime = 0;

	public ModelPose(ModelBase original, ModelBase pose)
	{
		this.originalModel = original;

		try
		{
			Field[] fields = original.getClass().getDeclaredFields();
			for (Field f : fields)
			{
				f.setAccessible(true);
				if (f.getType().isAssignableFrom(ModelRendererAnimania.class))
				{
					this.originalFields.add(f);

					ModelPart mr = (ModelPart) f.get(original);
					this.originalPose.put(f.getName(), new PartData(mr.x, mr.y, mr.z, mr.xRot, mr.yRot, mr.zRot, mr.visible, mr.offsetX, mr.offsetY, mr.offsetZ));
				}
			}

			fields = pose.getClass().getDeclaredFields();
			for (Field f : fields)
			{
				f.setAccessible(true);
				if (f.getType().isAssignableFrom(ModelRendererAnimania.class))
				{
					ModelPart mr = (ModelPart) f.get(pose);
					this.modelPose.put(f.getName(), new PartData(mr.x, mr.y, mr.z, mr.xRot, mr.yRot, mr.zRot, mr.visible, mr.offsetX, mr.offsetY, mr.offsetZ));
				}
			}

		}
		catch (Exception e)
		{
		}
	}

	public void transitionToPose(float totalAnimateTime, float passedTime)
	{
		if (this.originalStartTime != 0)
			this.originalStartTime = 0;

		if (this.poseStartTime == 0)
			this.poseStartTime = passedTime;

		float slide = totalAnimateTime <= 0 ? 1 : (passedTime - this.poseStartTime) / totalAnimateTime;

		if (slide >= 1)
			slide = 1;

		try
		{
			for (Field f : this.originalFields)
			{
				if (this.modelPose.containsKey(f.getName()))
				{
					ModelPart mr = (ModelPart) f.get(this.originalModel);
					PartData data = this.modelPose.get(f.getName());
					PartData orig = this.originalPose.get(f.getName());

					mr.x = Mth.clampedLerp(orig.rotationPointX, data.rotationPointX, slide);
					mr.y = Mth.clampedLerp(orig.rotationPointY, data.rotationPointY, slide);
					mr.z = Mth.clampedLerp(orig.rotationPointZ, data.rotationPointZ, slide);

					mr.xRot = this.lerpRotation(orig.rotateAngleX, data.rotateAngleX, slide);
					mr.yRot = this.lerpRotation(orig.rotateAngleY, data.rotateAngleY, slide);
					mr.zRot = this.lerpRotation(orig.rotateAngleZ, data.rotateAngleZ, slide);

					mr.visible = data.isHidden;

					mr.offsetX = (float) Mth.clampedLerp(orig.offsetX, data.offsetX, slide);
					mr.offsetY = (float) Mth.clampedLerp(orig.offsetY, data.offsetY, slide);
					mr.offsetZ = (float) Mth.clampedLerp(orig.offsetZ, data.offsetZ, slide);
				}
			}
		}
		catch (Exception e)
		{
		}

	}

	public void transitionToNormal(float totalAnimateTime, float passedTime)
	{
		if (this.poseStartTime != 0)
			this.poseStartTime = 0;

		if (this.originalStartTime == 0)
			this.originalStartTime = passedTime;

		float slide = totalAnimateTime <= 0 ? 1 : (passedTime - this.originalStartTime) / totalAnimateTime;

		if (slide >= 1)
			slide = 1;

		try
		{
			for (Field f : this.originalFields)
			{
				if (this.modelPose.containsKey(f.getName()))
				{
					ModelPart mr = (ModelPart) f.get(this.originalModel);
					PartData orig = this.modelPose.get(f.getName());
					PartData data = this.originalPose.get(f.getName());

					mr.x = Mth.clampedLerp(orig.rotationPointX, data.rotationPointX, slide);
					mr.y = Mth.clampedLerp(orig.rotationPointY, data.rotationPointY, slide);
					mr.z = Mth.clampedLerp(orig.rotationPointZ, data.rotationPointZ, slide);

					mr.xRot = this.lerpRotation(orig.rotateAngleX, data.rotateAngleX, slide);
					mr.yRot = this.lerpRotation(orig.rotateAngleY, data.rotateAngleY, slide);
					mr.zRot = this.lerpRotation(orig.rotateAngleZ, data.rotateAngleZ, slide);

					mr.visible = data.isHidden;

					mr.offsetX = (float) Mth.clampedLerp(orig.offsetX, data.offsetX, slide);
					mr.offsetY = (float) Mth.clampedLerp(orig.offsetY, data.offsetY, slide);
					mr.offsetZ = (float) Mth.clampedLerp(orig.offsetZ, data.offsetZ, slide);
				}
			}
		}
		catch (Exception e)
		{
		}
	}

	private float lerpRotation(float from, float to, float slide)
	{
		double mul = 180 / Math.PI;
		double fromdeg = from * mul % 180 + 180;
		double todeg = to * mul % 180 + 180;

		if (from == -6.079215658816017)
			System.out.println("s");

		double lerped = Mth.clampedLerp(fromdeg, todeg, slide) - 180;

		return (float) (lerped / mul);
	}

	private static class PartData
	{
		private final float rotationPointX;
		private final float rotationPointY;
		private final float rotationPointZ;
		private final float rotateAngleX;
		private final float rotateAngleY;
		private final float rotateAngleZ;
		private final boolean isHidden;
		private final float offsetX;
		private final float offsetY;
		private final float offsetZ;

		public PartData(float rotationPointX, float rotationPointY, float rotationPointZ, float rotateAngleX, float rotateAngleY, float rotateAngleZ, boolean isHidden, float offsetX, float offsetY, float offsetZ)
		{
			this.rotationPointX = rotationPointX;
			this.rotationPointY = rotationPointY;
			this.rotationPointZ = rotationPointZ;
			this.rotateAngleX = rotateAngleX;
			this.rotateAngleY = rotateAngleY;
			this.rotateAngleZ = rotateAngleZ;
			this.isHidden = isHidden;
			this.offsetX = offsetX;
			this.offsetY = offsetY;
			this.offsetZ = offsetZ;
		}
	}
}
