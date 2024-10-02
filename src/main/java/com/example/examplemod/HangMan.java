package com.example.examplemod;

import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.example.examplemod.HangManBlocks;
import com.example.examplemod.HangManEntity;
import com.example.examplemod.HangManItems;
import com.example.examplemod.HangManCommon;
import org.slf4j.Logger;
import com.example.examplemod.HangManItems;


@Mod(HangManCommon.MODID)
public class HangMan extends HangManCommon {

    private static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    // Create a Deferred Register to hold Items which will all be registered under the "examplemod" namespace
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public HangMan(ModContainer container, IEventBus modBus) {
        HangManItems.ITEMS.register(modBus);
        HangManBlocks.BLOCKS.register(modBus);

        ModLoadingContext modLoadingContext = ModLoadingContext.get();
        container.registerConfig(ModConfig.Type.SERVER, Config.SERVER_SPEC);
        container.registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_SPEC);

        // Corrected: Register listeners to the mod event bus
        modBus.addListener(this::modConfig);
        modBus.addListener(this::addCreative);
    }


    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == (CreativeModeTabs.INGREDIENTS)) {
            event.accept((ItemLike) HangManItems.NOOSE);
        }
    }

    private void modConfig(ModConfigEvent event) {
        ModConfig config = event.getConfig();
        if (config.getSpec() == Config.CLIENT_SPEC)
            Config.refreshClient();
        else if (config.getSpec() == Config.SERVER_SPEC)
            Config.refreshServer();
    }
//    public HangMan(IEventBus modEventBus, ModContainer modContainer)
//    {
//        // Register the commonSetup method for modloading
//        //modEventBus.addListener(this::commonSetup);
//
//        // Register the Deferred Register to the mod event bus so blocks get registered
//        BLOCKS.register(modEventBus);
//        // Register the Deferred Register to the mod event bus so items get registered
//        ITEMS.register(modEventBus);
//        // Register the Deferred Register to the mod event bus so tabs get registered
//        CREATIVE_MODE_TABS.register(modEventBus);
//
//        // Register ourselves for server and other game events we are interested in.
//        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
//        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
//        NeoForge.EVENT_BUS.register(this);
//
//        // Register the item to a creative tab
//        modEventBus.addListener(this::addCreative);
//
//        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
//        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
//    }

   // private void commonSetup(final FMLCommonSetupEvent event)
   // {
        // Some common setup code
      //  LOGGER.info("HELLO FROM COMMON SETUP");

      //  if (Config.logDirtBlock)
        //    LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));

      //  LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

       // Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    //}
}