package fr.leviathanstudio.player_anim.proxy;

import com.leviathanstudio.craftstudio.CSRegistryHelper;
import com.leviathanstudio.craftstudio.client.json.EnumRenderType;
import com.leviathanstudio.craftstudio.client.json.EnumResourceType;

import fr.leviathanstudio.player_anim.common.PlayerAnimation;

public class PAClientProxy extends PACommonProxy
{
    @Override
    public void registerModels()
    {
        super.registerModels();
        CSRegistryHelper registry = new CSRegistryHelper(PlayerAnimation.MODID);
        registry.register(EnumResourceType.MODEL, EnumRenderType.ENTITY, "player_sit");
        registry.register(EnumResourceType.MODEL, EnumRenderType.ENTITY, "player");
    }

    @Override
    public void registerAnims()
    {
        super.registerAnims();
        CSRegistryHelper registry = new CSRegistryHelper(PlayerAnimation.MODID);
    }
}
