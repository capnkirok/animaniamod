package common.item;

import com.animania.common.items.ItemAnimaniaFood;
import com.animania.config.AnimaniaConfig;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.StatList;
import net.minecraft.util.InteractionResultHolderType;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class ItemTruffleSoup extends ItemAnimaniaFood
{
	public ItemTruffleSoup()
	{
		super(10, 0.6f, "truffle_soup", new MobEffectInstance(MobEffects.REGENERATION, 1200, 1, false, false));
		this.setMaxStackSize(1);
		this.setContainerItem(Items.BOWL);
	}

	@Override
	@Nullable
	public ItemStack onItemUseFinish(ItemStack stack, Level levelIn, LivingEntity LivingEntity)
	{

		if (LivingEntity instanceof Player Player)
		{
			if (AnimaniaConfig.gameRules.foodsGiveBonusEffects)
				Player.addMobEffectInstance(new MobEffectInstance(MobEffects.REGENERATION, 1200, 1, false, false));

			if (Player.getFoodStats() != null)
			{
				Player.getFoodStats().addStats(this, stack);
			}
			levelIn.playSound((Player) null, Player.getX(), Player.getY(), Player.getZ(), SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, levelIn.rand.nextFloat() * 0.1F + 0.9F);
			this.onFoodEaten(stack, levelIn, Player);
			Player.addStat(StatList.getObjectUseStats(this));

			if (Player instanceof ServerPlayer)
			{
				CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer) Player, stack);
			}

			if (!Player.capabilities.isCreativeMode)
			{
				stack.shrink(1);
			}
		}

		return stack.getCount() <= 0 ? new ItemStack(Items.BOWL) : stack;
	}

	@Override
	protected void onFoodEaten(ItemStack itemstack, Level levelObj, Player Player)
	{

	}

	public InteractionResultHolder<ItemStack> onItemRightClick(ItemStack itemStackIn, Level levelIn, Player playerIn, InteractionHand hand)
	{
		playerIn.setActiveHand(hand);
		return new InteractionResultHolder(InteractionResultHolderType.SUCCESS, itemStackIn);
	}
}
