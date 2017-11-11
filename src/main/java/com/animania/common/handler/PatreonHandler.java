package com.animania.common.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;

public class PatreonHandler
{

	public static List<String> patreonUUIDS;
	
	
	public static void initList()
	{
		patreonUUIDS = new ArrayList<String>();
		
		//DEV TEAM
		patreonUUIDS.add("3507ad5c-d868-453c-90a0-3b8092999d22"); //purplicious_cow
		patreonUUIDS.add("615d0847-0ddd-4bc9-a410-355a79cdd519"); //RazzleberryFox
		patreonUUIDS.add("04372b9e-4e31-4a69-9660-4ac1cc2dbdb4"); //ZeAmateis
		patreonUUIDS.add("bd1a8633-8ca7-4b5d-9ef7-5d1dfde310f3"); //A_villager
		patreonUUIDS.add("9f6e90b5-cd29-49fd-8858-87e3fa4ca150"); //Timmypote
		patreonUUIDS.add("9c8a434b-2adc-4385-bc58-a8f08db3ebb9"); //TheJurassicAlien
		patreonUUIDS.add("1fbb6b43-af8f-40a1-9c26-c5128ff513ce"); //vroulas
		patreonUUIDS.add("fdd172ed-dcb5-49ed-93ae-7847f6e88bef"); //Tschipp
		
		//PATREONS
		patreonUUIDS.add("b0bb3fff-ddbd-40c6-95d7-51dfcaece879"); //LeKoopa92
		patreonUUIDS.add("1f471396-84d9-41a0-ad9b-52a722c12a6a"); //stacyplays
		patreonUUIDS.add("6265251a-5dae-4eb4-a95e-dfd922ad8fd5"); //stacysays
		patreonUUIDS.add("407720e3-22a6-4a78-9e64-0f6e82e00609"); //Xorbah
		patreonUUIDS.add("f865dbb5-552c-4f62-8d73-2360634f6edd"); //Gold172
		patreonUUIDS.add("2ea31456-53d4-4eee-b034-5126fc45e1c7"); //akirakujo
		patreonUUIDS.add("eef69763-af9d-4ce6-bef7-cd9bc839004c"); //ScottRadish
		patreonUUIDS.add("de8fc838-7468-4c5f-8f48-012808e079e1"); //Volfster

	}
	
	
	public static boolean isPlayerPatreon(EntityPlayer player)
	{
		UUID id = player.getUniqueID();
		String uuid = id.toString();
		
		if(patreonUUIDS.contains(uuid))
			return true;
		
		return false;
	}
	
	
}
