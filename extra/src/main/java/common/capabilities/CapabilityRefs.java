package common.capabilities;

import com.animania.Animania;
import net.minecraft.nbt.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

public class CapabilityRefs
{

	// TODO: Move this to RegisterCapabilitiesEvent?
	// @CapabilityInject(ICapabilityPlayer.class)
	public static final Capability<ICapabilityPlayer> CAPS = null;

	public static LazyOptional<ICapabilityPlayer> getPlayerCaps(Player player)
	{
		return player.getCapability(CapabilityRefs.CAPS, null);
	}

	public static StringTag toTagString(String s)
	{
		return StringTag.valueOf(s);
	}

	public static IntTag toTagInt(int i)
	{
		return IntTag.valueOf(i);
	}

	public static LongTag toTagLong(long i)
	{
		return LongTag.valueOf(i);
	}

	public static FloatTag toTagFloat(float f)
	{
		return FloatTag.valueOf(f);
	}

	public static ListTag toTagList(ListTag l)
	{
		return new ListTag();
	}

	public static ResourceLocation toResource(String path)
	{
		return new ResourceLocation(Animania.MODID, path);
	}
}