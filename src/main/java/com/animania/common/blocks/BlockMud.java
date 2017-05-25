package com.animania.common.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.common.entities.pigs.EntityHogDuroc;
import com.animania.common.entities.pigs.EntityHogLargeBlack;
import com.animania.common.entities.pigs.EntityHogLargeWhite;
import com.animania.common.entities.pigs.EntityHogOldSpot;
import com.animania.common.entities.pigs.EntityHogYorkshire;
import com.animania.common.entities.pigs.EntityPigletDuroc;
import com.animania.common.entities.pigs.EntityPigletLargeBlack;
import com.animania.common.entities.pigs.EntityPigletLargeWhite;
import com.animania.common.entities.pigs.EntityPigletOldSpot;
import com.animania.common.entities.pigs.EntityPigletYorkshire;
import com.animania.common.entities.pigs.EntitySowDuroc;
import com.animania.common.entities.pigs.EntitySowLargeBlack;
import com.animania.common.entities.pigs.EntitySowLargeWhite;
import com.animania.common.entities.pigs.EntitySowOldSpot;
import com.animania.common.entities.pigs.EntitySowYorkshire;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMud extends Block
{

    protected static final AxisAlignedBB MUD_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.88D, 1.0D);

    private String                       name     = "block_mud";

    public BlockMud() {
        super(Material.SAND, MapColor.BROWN);
        this.setCreativeTab(Animania.TabAnimaniaResources);
        this.setRegistryName(new ResourceLocation(Animania.MODID, this.name));
        GameRegistry.register(this);
        this.setUnlocalizedName(Animania.MODID + "_" + this.name);
        this.slipperiness = 0.6F;
        this.setSoundType(SoundType.SLIME);
        this.setTickRandomly(true);
    }

    @Override
    public int tickRate(World worldIn) {
        return 20;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    public int damageDropped(int par1) {
        return par1;
    }

    @Override
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return BlockMud.MUD_AABB;
    }

    @Override
    public int quantityDropped(Random random) {
        return 1;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        entityIn.motionX *= 0.2;
        entityIn.motionZ *= 0.2;
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        entityIn.motionX *= 0.2D;
        entityIn.motionZ *= 0.2D;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {

        int esize = worldIn.loadedEntityList.size();

        for (int k = 0; k <= esize - 1; k++) {

            Entity entity = worldIn.loadedEntityList.get(k);

            if (entity != null && (entity instanceof EntitySowYorkshire || entity instanceof EntitySowDuroc || entity instanceof EntitySowLargeBlack
                    || entity instanceof EntitySowLargeWhite || entity instanceof EntitySowOldSpot || entity instanceof EntityPigletYorkshire
                    || entity instanceof EntityPigletDuroc || entity instanceof EntityPigletLargeBlack || entity instanceof EntityPigletLargeWhite
                    || entity instanceof EntityPigletOldSpot || entity instanceof EntityHogYorkshire || entity instanceof EntityHogDuroc
                    || entity instanceof EntityHogLargeBlack || entity instanceof EntityHogLargeWhite || entity instanceof EntityHogOldSpot)) {
                // Add Hogs and Piglets
                boolean isMuddy = false;
                Float splashTimer = 0.0F;

                if (entity instanceof EntitySowYorkshire) {
                    EntitySowYorkshire entitysow = (EntitySowYorkshire) entity;
                    splashTimer = entitysow.getSplashTimer();
                    isMuddy = entitysow.getMuddy();
                }
                else if (entity instanceof EntitySowDuroc) {
                    EntitySowDuroc entitysow = (EntitySowDuroc) entity;
                    splashTimer = entitysow.getSplashTimer();
                    isMuddy = entitysow.getMuddy();
                }
                else if (entity instanceof EntitySowLargeBlack) {
                    EntitySowLargeBlack entitysow = (EntitySowLargeBlack) entity;
                    splashTimer = entitysow.getSplashTimer();
                    isMuddy = entitysow.getMuddy();
                }
                else if (entity instanceof EntitySowLargeWhite) {
                    EntitySowLargeWhite entitysow = (EntitySowLargeWhite) entity;
                    splashTimer = entitysow.getSplashTimer();
                    isMuddy = entitysow.getMuddy();
                }
                else if (entity instanceof EntitySowDuroc) {
                    EntitySowOldSpot entitysow = (EntitySowOldSpot) entity;
                    splashTimer = entitysow.getSplashTimer();
                    isMuddy = entitysow.getMuddy();
                }
                else if (entity instanceof EntityPigletYorkshire) {
                    EntityPigletYorkshire entityPiglet = (EntityPigletYorkshire) entity;
                    splashTimer = entityPiglet.getSplashTimer();
                    isMuddy = entityPiglet.getMuddy();
                }
                else if (entity instanceof EntityPigletDuroc) {
                    EntityPigletDuroc entityPiglet = (EntityPigletDuroc) entity;
                    splashTimer = entityPiglet.getSplashTimer();
                    isMuddy = entityPiglet.getMuddy();
                }
                else if (entity instanceof EntityPigletLargeBlack) {
                    EntityPigletLargeBlack entityPiglet = (EntityPigletLargeBlack) entity;
                    splashTimer = entityPiglet.getSplashTimer();
                    isMuddy = entityPiglet.getMuddy();
                }
                else if (entity instanceof EntityPigletLargeWhite) {
                    EntityPigletLargeWhite entityPiglet = (EntityPigletLargeWhite) entity;
                    splashTimer = entityPiglet.getSplashTimer();
                    isMuddy = entityPiglet.getMuddy();
                }
                else if (entity instanceof EntityPigletDuroc) {
                    EntityPigletOldSpot entityPiglet = (EntityPigletOldSpot) entity;
                    splashTimer = entityPiglet.getSplashTimer();
                    isMuddy = entityPiglet.getMuddy();
                }
                else if (entity instanceof EntityHogYorkshire) {
                    EntityHogYorkshire entityHog = (EntityHogYorkshire) entity;
                    splashTimer = entityHog.getSplashTimer();
                    isMuddy = entityHog.getMuddy();
                }
                else if (entity instanceof EntityHogDuroc) {
                    EntityHogDuroc entityHog = (EntityHogDuroc) entity;
                    splashTimer = entityHog.getSplashTimer();
                    isMuddy = entityHog.getMuddy();
                }
                else if (entity instanceof EntityHogLargeBlack) {
                    EntityHogLargeBlack entityHog = (EntityHogLargeBlack) entity;
                    splashTimer = entityHog.getSplashTimer();
                    isMuddy = entityHog.getMuddy();
                }
                else if (entity instanceof EntityHogLargeWhite) {
                    EntityHogLargeWhite entityHog = (EntityHogLargeWhite) entity;
                    splashTimer = entityHog.getSplashTimer();
                    isMuddy = entityHog.getMuddy();
                }
                else if (entity instanceof EntityHogDuroc) {
                    EntityHogOldSpot entityHog = (EntityHogOldSpot) entity;
                    splashTimer = entityHog.getSplashTimer();
                    isMuddy = entityHog.getMuddy();
                }

                if (isMuddy && splashTimer > 0.0F || entity instanceof EntityPlayer) {

                    double xt = entity.posX;
                    double yt = entity.posY;
                    double zt = entity.posZ;
                    int x1 = pos.getX();
                    int y1 = pos.getY();
                    int z1 = pos.getZ();
                    double x2 = xt - x1;
                    double y2 = yt - y1;
                    double z2 = zt - z1;

                    if (MathHelper.abs((int) x2) < 1 && MathHelper.abs((int) z2) < 1 && MathHelper.abs((int) y2) < 1)
                        for (int kk = 0; kk < 8; kk++)
                            worldIn.spawnParticle(EnumParticleTypes.BLOCK_CRACK, entity.posX + (rand.nextFloat() - 0.5D) * entity.width,
                                    entity.getEntityBoundingBox().minY + 0.5D, entity.posZ + (rand.nextFloat() - 0.5D) * entity.width,
                                    4.0D * (rand.nextFloat() - 0.5D), 0.5D, (rand.nextFloat() - 0.5D) * 4.0D,
                                    new int[] { Block.getStateId(stateIn) });

                }

            }

        }

    }

}
