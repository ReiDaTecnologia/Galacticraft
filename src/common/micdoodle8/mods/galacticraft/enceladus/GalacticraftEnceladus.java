package micdoodle8.mods.galacticraft.enceladus;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import micdoodle8.mods.galacticraft.API.IGalacticraftSubMod;
import micdoodle8.mods.galacticraft.core.GCCoreUtil;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.enceladus.blocks.GCEnceladusBlocks;
import micdoodle8.mods.galacticraft.enceladus.dimension.GCEnceladusWorldProvider;
import micdoodle8.mods.galacticraft.saturn.GalacticraftSaturn;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.INetworkManager;
import net.minecraft.src.Packet250CustomPayload;
import net.minecraftforge.common.DimensionManager;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * Copyright 2012, micdoodle8
 * 
 *  All rights reserved.
 *
 */
public class GalacticraftEnceladus implements IGalacticraftSubMod
{
	public static List enceladusPlayers = new ArrayList();
	public static List gcEnceladusPlayers = new ArrayList();

	public void preInit(FMLPreInitializationEvent event)
	{
		GalacticraftCore.registerSubMod(this);

		new GCEnceladusConfigManager(new File(event.getModConfigurationDirectory(), "Galacticraft/enceladus.conf"));
		
		GCEnceladusBlocks.initBlocks();
		GCEnceladusBlocks.registerBlocks();
		GCEnceladusBlocks.setHarvestLevels();
		GCEnceladusBlocks.addNames();
//		
//		GCMarsItems.initItems();
//		GCMarsItems.addNames(); TODO
	}
	
	public void init(FMLInitializationEvent event)
	{
		DimensionManager.registerProviderType(GCEnceladusConfigManager.dimensionIDEnceladus, GCEnceladusWorldProvider.class, false);
		DimensionManager.registerDimension(GCEnceladusConfigManager.dimensionIDEnceladus, GCEnceladusConfigManager.dimensionIDEnceladus);
//		MinecraftForge.EVENT_BUS.register(new GCMarsEvents());
//		GameRegistry.registerPlayerTracker(new GCMarsPlayerHandler());
		this.registerTileEntities();
		this.registerCreatures();
		this.registerOtherEntities();
	}
	
	public void registerTileEntities()
	{
		
	}
	
	public void registerCreatures()
	{
	}
	
	public void registerOtherEntities()
	{
	}
	
	public void postInit(FMLPostInitializationEvent event)
	{
//		GCMarsAchievementList.initAchievs();
	}
	
	public void serverInit(FMLServerStartedEvent event)
	{
        NetworkRegistry.instance().registerChannel(new ServerPacketHandler(), "GcEn", Side.SERVER);
	}

    public void registerGalacticraftCreature(Class var0, String var1, int id, int back, int fore)
    {
    	EntityRegistry.registerGlobalEntityID(var0, var1, id, back, fore);
        EntityRegistry.registerModEntity(var0, var1, id, GalacticraftSaturn.moonEnceladus, 80, 3, true);
		LanguageRegistry.instance().addStringLocalization("entity." + var1 + ".name", "en_US", var1);
    }
    
    public void registerGalacticraftNonMobEntity(Class var0, String var1, int id, int trackingDistance, int updateFreq, boolean sendVel)
    {
        EntityRegistry.registerModEntity(var0, var1, id, this, trackingDistance, updateFreq, sendVel);
    }
	
    public class ServerPacketHandler implements IPacketHandler
    {
        @Override
        public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player p)
        {
            final DataInputStream data = new DataInputStream(new ByteArrayInputStream(packet.data));
            final int packetType = GCCoreUtil.readPacketID(data);
            final EntityPlayerMP player = (EntityPlayerMP)p;
            
            if (packetType == 0)
            {
            	
            }
        }
    }

	@Override
	public String getDimensionName() 
	{
		return "Enceladus";
	}

	@Override
	public boolean reachableDestination() 
	{
		return true;
	}
}
