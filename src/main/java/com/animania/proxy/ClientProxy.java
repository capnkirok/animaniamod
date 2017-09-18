package com.animania.proxy;

import com.animania.Animania;
import com.animania.client.AnimaniaTextures;
import com.animania.client.handler.RenderHandler;
import com.animania.common.handler.ItemHandler;
import com.animania.common.items.ItemEntityEgg;
import com.leviathanstudio.craftstudio.CSRegistryHelper;
import com.leviathanstudio.craftstudio.client.json.EnumRenderType;
import com.leviathanstudio.craftstudio.client.json.EnumResourceType;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ClientProxy extends CommonProxy
{

    private CSRegistryHelper csRegistry = new CSRegistryHelper(Animania.MODID);

    @Override
    public void preInit()
    {
        super.preInit();
        RenderHandler.preInit();
        AnimaniaTextures.registerTextures();

    }

    @Override
    public void init()
    {
        super.init();
        RenderHandler.init();
        ItemHandler.regItemEggColors();
        
        for (Item item : ForgeRegistries.ITEMS.getValues())
        {
            if (item instanceof ItemEntityEgg)
            {
                FMLClientHandler.instance().getClient().getItemColors().registerItemColorHandler(new ItemEntityEgg.Color(), item);
            }
        }
    }

    @Override
    public void registerFluidBlockRendering(Block block, String name)
    {
        name = name.toLowerCase();
        super.registerFluidBlockRendering(block, name);
        FluidStateMapper mapper = new FluidStateMapper(name);

        Item item = Item.getItemFromBlock(block);
        ModelBakery.registerItemVariants(item);
        ModelLoader.setCustomMeshDefinition(item, mapper);
        ModelLoader.setCustomStateMapper(block, mapper);
    }

    public static class FluidStateMapper extends StateMapperBase implements ItemMeshDefinition
    {
        public final ModelResourceLocation location;

        public FluidStateMapper(String name)
        {
            this.location = new ModelResourceLocation(Animania.MODID + ":fluids", name);
        }

        @Override
        protected ModelResourceLocation getModelResourceLocation(IBlockState state)
        {
            return this.location;
        }

        @Override
        public ModelResourceLocation getModelLocation(ItemStack stack)
        {
            return this.location;
        }
    }

    @Override
    public void registerCraftStudioModels()
    {
        csRegistry.register(EnumResourceType.MODEL, EnumRenderType.BLOCK, "model_hamster_wheel");
        csRegistry.register(EnumResourceType.MODEL, EnumRenderType.ENTITY, "hamster");
        csRegistry.register(EnumResourceType.MODEL, EnumRenderType.ENTITY, "wagon");
    }

    @Override
    public void registerCraftStudioAnimations()
    {
        csRegistry.register(EnumResourceType.ANIM, EnumRenderType.BLOCK, "anim_hamster_wheel");
        csRegistry.register(EnumResourceType.ANIM, EnumRenderType.ENTITY, "hamster_run");
    }
}