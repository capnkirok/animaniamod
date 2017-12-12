package com.animania.common.blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.common.ModSoundEvents;
import com.animania.common.entities.chickens.ChickenType;
import com.animania.common.entities.chickens.EntityChickBase;
import com.animania.common.entities.chickens.EntityRoosterBase;
import com.animania.common.entities.peacocks.EntityPeachickBase;
import com.animania.common.entities.peacocks.EntityPeacockBase;
import com.animania.common.entities.peacocks.PeacockType;
import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.ItemHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.tileentities.TileEntityNest;
import com.animania.common.tileentities.TileEntityTrough;
import com.animania.common.tileentities.TileEntityNest.NestContent;
import com.animania.compat.top.providers.TOPInfoProvider;
import com.animania.config.AnimaniaConfig;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockNest extends BlockContainer implements TOPInfoProvider
{
	private String name = "block_nest";

	protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.0D, 0.0D, 0.00D, 1.0D, 0.3D, 1.0D);

	public BlockNest()
	{
		super(Material.WOOD);
		this.setRegistryName(new ResourceLocation(Animania.MODID, this.name));
		GameRegistry.register(this);
		this.setUnlocalizedName(Animania.MODID + "_" + this.name);
		this.setCreativeTab(Animania.TabAnimaniaResources);
		this.setTickRandomly(true);

	}

	public int tickRate(World worldIn)
	{
		return 5;
	}

	@Override
	public String getLocalizedName()
	{
		return I18n.translateToLocal("tile.animania_block_nest.name");
	}

	/**
	 * Used to determine ambient occlusion and culling when rebuilding chunks
	 * for render
	 */
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{

		return BlockNest.AABB;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		TileEntityNest te = (TileEntityNest) worldIn.getTileEntity(pos);
		if (te != null && te.getNestContent() != NestContent.EMPTY)
		{
			List<EntityRoosterBase> roosters = AnimaniaHelper.getEntitiesInRange(EntityRoosterBase.class, 3, worldIn, pos);
			List<EntityPeacockBase> peacocks = AnimaniaHelper.getEntitiesInRange(EntityPeacockBase.class, 3, worldIn, pos);

			if (te.getBirdType() instanceof ChickenType)
			{
				for (EntityRoosterBase rooster : roosters)
				{
					if (rand.nextInt(AnimaniaConfig.careAndFeeding.eggHatchChance) < 1)
					{
						ChickenType chickType = ChickenType.breed(rooster.type, (ChickenType) te.getBirdType());
						EntityChickBase chick = chickType.getChild(worldIn);
						chick.setPosition(pos.getX() + .5, pos.getY() + .2, pos.getZ() + .5);
						worldIn.spawnEntity(chick);
						chick.playSound(ModSoundEvents.chickenCluck1, 0.50F, 1.4F);
						te.removeItem();
						te.markDirty();
						break;
					}
				}
			}
			if (te.getBirdType() instanceof PeacockType)
			{
				for (EntityPeacockBase peacock : peacocks)
				{
					if (rand.nextInt(AnimaniaConfig.careAndFeeding.eggHatchChance) < 1)
					{
						PeacockType chickType = PeacockType.breed(peacock.type, (PeacockType) te.getBirdType());
						EntityPeachickBase chick = chickType.getChild(worldIn);
						chick.setPosition(pos.getX() + .5, pos.getY() + .2, pos.getZ() + .5);
						worldIn.spawnEntity(chick);
						chick.playSound(ModSoundEvents.peacock1, 0.50F, 1.4F);
						te.removeItem();
						te.markDirty();
						break;
					}
				}
			}

		}

	}

	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{

		return this.getDefaultState();
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityNest();
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
	{
		return new ItemStack(BlockHandler.blockNest, 1);
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{

		TileEntityNest te = (TileEntityNest) worldIn.getTileEntity(pos);

		if (te != null)
			InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), te.itemHandler.getStackInSlot(0));

		super.breakBlock(worldIn, pos, state);
	}

	@Override
	@Nullable
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(BlockHandler.blockNest);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{

		ItemStack heldItem = playerIn.getHeldItem(hand);
		TileEntityNest te = (TileEntityNest) worldIn.getTileEntity(pos);

		if (te != null && heldItem.isEmpty() && !playerIn.isSneaking())
		{
			ItemStack stack = te.itemHandler.extractItem(0, 1, false);
			AnimaniaHelper.addItem(playerIn, stack);
			return true;
		}

		return false;

	}

	public String getName()
	{
		return this.name;
	}

	@Override
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data)
	{
		TileEntity te = world.getTileEntity(data.getPos());
		if (te instanceof TileEntityNest)
		{
			TileEntityNest nest = (TileEntityNest) te;
			ItemStack stack = nest.itemHandler.getStackInSlot(0);

			if (mode == ProbeMode.NORMAL)
			{
				if (!stack.isEmpty())
				{
					probeInfo.horizontal();
					probeInfo.item(stack);
					if (nest.birdType != null)
						probeInfo.text("From: " + nest.birdType.toString().substring(0, 1).toUpperCase() + nest.birdType.toString().substring(1).toLowerCase());
				}
			}

		}

	}

}