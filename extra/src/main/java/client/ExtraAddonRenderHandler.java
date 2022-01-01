package client;

import client.render.amphibians.RenderDartFrogs;
import client.render.amphibians.RenderFrogs;
import client.render.amphibians.RenderToad;
import client.render.peafowl.RenderPeachickBase;
import client.render.peafowl.RenderPeacockBase;
import client.render.peafowl.RenderPeafowlBase;
import client.render.rabbits.*;
import client.render.rodents.*;
import com.animania.Animania;
import com.animania.addons.extra.client.render.tileentity.BlockEntityHamsterWheelRenderer;
import com.animania.addons.extra.common.entity.peafowl.PeafowlBlue.EntityPeachickBlue;
import com.animania.addons.extra.common.entity.peafowl.PeafowlBlue.EntityPeacockBlue;
import com.animania.addons.extra.common.entity.peafowl.PeafowlBlue.EntityPeafowlBlue;
import com.animania.addons.extra.common.entity.peafowl.PeafowlCharcoal.EntityPeachickCharcoal;
import com.animania.addons.extra.common.entity.peafowl.PeafowlCharcoal.EntityPeacockCharcoal;
import com.animania.addons.extra.common.entity.peafowl.PeafowlCharcoal.EntityPeafowlCharcoal;
import com.animania.addons.extra.common.entity.peafowl.PeafowlOpal.EntityPeachickOpal;
import com.animania.addons.extra.common.entity.peafowl.PeafowlOpal.EntityPeacockOpal;
import com.animania.addons.extra.common.entity.peafowl.PeafowlOpal.EntityPeafowlOpal;
import com.animania.addons.extra.common.entity.peafowl.PeafowlPeach.EntityPeachickPeach;
import com.animania.addons.extra.common.entity.peafowl.PeafowlPeach.EntityPeacockPeach;
import com.animania.addons.extra.common.entity.peafowl.PeafowlPeach.EntityPeafowlPeach;
import com.animania.addons.extra.common.entity.peafowl.PeafowlPurple.EntityPeachickPurple;
import com.animania.addons.extra.common.entity.peafowl.PeafowlPurple.EntityPeacockPurple;
import com.animania.addons.extra.common.entity.peafowl.PeafowlPurple.EntityPeafowlPurple;
import com.animania.addons.extra.common.entity.peafowl.PeafowlTaupe.EntityPeachickTaupe;
import com.animania.addons.extra.common.entity.peafowl.PeafowlTaupe.EntityPeacockTaupe;
import com.animania.addons.extra.common.entity.peafowl.PeafowlTaupe.EntityPeafowlTaupe;
import com.animania.addons.extra.common.entity.peafowl.PeafowlWhite.EntityPeachickWhite;
import com.animania.addons.extra.common.entity.peafowl.PeafowlWhite.EntityPeacockWhite;
import com.animania.addons.extra.common.entity.peafowl.PeafowlWhite.EntityPeafowlWhite;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitChinchilla.RabbitEntityBuckChinchilla;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitChinchilla.RabbitEntityDoeChinchilla;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitChinchilla.RabbitEntityKitChinchilla;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitCottonail.RabbitEntityBuckCottontail;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitCottonail.RabbitEntityDoeCottontail;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitCottonail.RabbitEntityKitCottontail;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitDutch.RabbitEntityBuckDutch;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitDutch.RabbitEntityDoeDutch;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitDutch.RabbitEntityKitDutch;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitHavana.RabbitEntityBuckHavana;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitHavana.RabbitEntityDoeHavana;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitHavana.RabbitEntityKitHavana;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitJack.RabbitEntityBuckJack;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitJack.RabbitEntityDoeJack;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitJack.RabbitEntityKitJack;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitLop.RabbitEntityBuckLop;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitLop.RabbitEntityDoeLop;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitLop.RabbitEntityKitLop;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitNewZealand.RabbitEntityBuckNewZealand;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitNewZealand.RabbitEntityDoeNewZealand;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitNewZealand.RabbitEntityKitNewZealand;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitRex.RabbitEntityBuckRex;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitRex.RabbitEntityDoeRex;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitRex.RabbitEntityKitRex;
import com.leviathanstudio.craftstudio.client.registry.CSRegistryHelper;
import com.leviathanstudio.craftstudio.client.registry.CraftStudioLoader;
import com.leviathanstudio.craftstudio.client.util.EnumRenderType;
import com.leviathanstudio.craftstudio.client.util.EnumResourceType;
import common.blockentity.BlockEntityHamsterWheel;
import common.entity.amphibians.EntityDartFrogs;
import common.entity.amphibians.EntityFrogs;
import common.entity.amphibians.EntityToad;
import common.entity.rodents.*;
import common.handler.ExtraAddonBlockHandler;
import common.handler.ExtraAddonItemHandler;
import net.minecraft.client.renderer.BlockEntity.BlockEntitySpecialRenderer;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ExtraAddonRenderHandler
{

	/**
	 * Render Entities <br>
	 * Render Items
	 */
	public static void preInit()
	{
		// Items
		register(ExtraAddonItemHandler.hamsterFood);
		register(ExtraAddonItemHandler.peacockEggBlue);
		register(ExtraAddonItemHandler.peacockEggWhite);
		register(ExtraAddonItemHandler.hamsterBallClear);
		register(ExtraAddonItemHandler.peacockFeatherBlue);
		register(ExtraAddonItemHandler.peacockFeatherWhite);
		register(ExtraAddonItemHandler.peacockFeatherCharcoal);
		register(ExtraAddonItemHandler.peacockFeatherOpal);
		register(ExtraAddonItemHandler.peacockFeatherPeach);
		register(ExtraAddonItemHandler.peacockFeatherPurple);
		register(ExtraAddonItemHandler.peacockFeatherTaupe);
		registerColored(ExtraAddonItemHandler.hamsterBallColored, "hamster_ball");

		// Frogs
		register(ExtraAddonItemHandler.rawFrogLegs);
		register(ExtraAddonItemHandler.cookedFrogLegs);

		// Goats
		register(ExtraAddonItemHandler.rawPeacock);
		register(ExtraAddonItemHandler.cookedPeacock);
		register(ExtraAddonItemHandler.rawPrimePeacock);
		register(ExtraAddonItemHandler.cookedPrimePeacock);

		// Rabbit
		register(ExtraAddonItemHandler.rawPrimeRabbit);
		register(ExtraAddonItemHandler.cookedPrimeRabbit);

		register(ExtraAddonItemHandler.entityeggrandompeacock);
		register(ExtraAddonItemHandler.entityeggdartfrog);
		register(ExtraAddonItemHandler.entityeggrandomrabbit);

		register(Item.byBlock(ExtraAddonBlockHandler.blockHamsterWheel));

		// Rabbits
		registerEntityRender(RabbitEntityBuckCottontail.class, RenderBuckCottontail.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(RabbitEntityDoeCottontail.class, RenderDoeCottontail.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(RabbitEntityKitCottontail.class, RenderKitCottontail.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(RabbitEntityBuckChinchilla.class, RenderBuckChinchilla.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(RabbitEntityDoeChinchilla.class, RenderDoeChinchilla.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(RabbitEntityKitChinchilla.class, RenderKitChinchilla.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(RabbitEntityBuckDutch.class, RenderBuckDutch.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(RabbitEntityDoeDutch.class, RenderDoeDutch.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(RabbitEntityKitDutch.class, RenderKitDutch.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(RabbitEntityBuckHavana.class, RenderBuckHavana.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(RabbitEntityDoeHavana.class, RenderDoeHavana.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(RabbitEntityKitHavana.class, RenderKitHavana.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(RabbitEntityBuckJack.class, RenderBuckJack.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(RabbitEntityDoeJack.class, RenderDoeJack.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(RabbitEntityKitJack.class, RenderKitJack.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(RabbitEntityBuckNewZealand.class, RenderBuckNewZealand.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(RabbitEntityDoeNewZealand.class, RenderDoeNewZealand.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(RabbitEntityKitNewZealand.class, RenderKitNewZealand.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(RabbitEntityBuckRex.class, RenderBuckRex.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(RabbitEntityDoeRex.class, RenderDoeRex.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(RabbitEntityKitRex.class, RenderKitRex.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(RabbitEntityBuckLop.class, RenderBuckLop.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(RabbitEntityDoeLop.class, RenderDoeLop.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(RabbitEntityKitLop.class, RenderKitLop.FACTORY);

		// Frogs
		RenderingRegistry.registerEntityRenderingHandler(EntityFrogs.class, RenderFrogs.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityDartFrogs.class, RenderDartFrogs.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityToad.class, RenderToad.FACTORY);

		// Rodents
		RenderingRegistry.registerEntityRenderingHandler(EntityHamster.class, RenderHamster.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityHedgehog.class, RenderHedgehog.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityHedgehogAlbino.class, RenderHedgehogAlbino.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityFerretGrey.class, RenderFerretGrey.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityFerretWhite.class, RenderFerretWhite.FACTORY);

		// Peacocks
		RenderingRegistry.registerEntityRenderingHandler(EntityPeacockBlue.class, RenderPeacockBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlBlue.class, RenderPeafowlBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeachickBlue.class, RenderPeachickBase.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityPeacockWhite.class, RenderPeacockBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlWhite.class, RenderPeafowlBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeachickWhite.class, RenderPeachickBase.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityPeacockCharcoal.class, RenderPeacockBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlCharcoal.class, RenderPeafowlBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeachickCharcoal.class, RenderPeachickBase.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityPeacockOpal.class, RenderPeacockBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlOpal.class, RenderPeafowlBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeachickOpal.class, RenderPeachickBase.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityPeacockPeach.class, RenderPeacockBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlPeach.class, RenderPeafowlBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeachickPeach.class, RenderPeachickBase.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityPeacockPurple.class, RenderPeacockBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlPurple.class, RenderPeafowlBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeachickPurple.class, RenderPeachickBase.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityPeacockTaupe.class, RenderPeacockBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlTaupe.class, RenderPeafowlBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeachickTaupe.class, RenderPeachickBase.FACTORY);
	}

	@CraftStudioLoader
	public static void registerCraftStudioAssets()
	{
		CSRegistryHelper csRegistry = new CSRegistryHelper(Animania.MODID);

		csRegistry.register(EnumResourceType.MODEL, EnumRenderType.BLOCK, "model_hamster_wheel");
		csRegistry.register(EnumResourceType.MODEL, EnumRenderType.ENTITY, "hamster");

		csRegistry.register(EnumResourceType.ANIM, EnumRenderType.BLOCK, "anim_hamster_wheel");
		csRegistry.register(EnumResourceType.ANIM, EnumRenderType.ENTITY, "hamster_run");

	}

	/**
	 * Render TileEntities
	 */
	public static void init()
	{
		ClientRegistry.bindBlockEntitySpecialRenderer(BlockEntityHamsterWheel.class, new BlockEntityHamsterWheelRenderer());
	}

	@SideOnly(Dist.CLIENT)
	private static void register(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

	@SideOnly(Dist.CLIENT)
	private static void register(Item item, String name, int meta)
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Animania.MODID + ":" + name, "inventory"));
	}

	@SideOnly(Dist.CLIENT)

	private static void registerColored(Item item, String name)
	{
		for (int meta = 0; meta < 16; meta++)
			ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Animania.MODID + ":" + name + "_" + EnumDyeColor.byDyeDamage(meta).getName(), "inventory"));
	}

	@SideOnly(Dist.CLIENT)
	private static <T extends Entity> void registerEntityRender(Class<T> entityClass, IRenderFactory<? super T> renderFactory)
	{
		RenderingRegistry.registerEntityRenderingHandler(entityClass, renderFactory);
	}

	@SideOnly(Dist.CLIENT)
	private static <T extends BlockEntity> void registerBlockEntityRender(Class<T> BlockEntityClass, BlockEntitySpecialRenderer<? super T> specialRenderer)
	{
		ClientRegistry.bindBlockEntitySpecialRenderer(BlockEntityClass, specialRenderer);
	}

}
