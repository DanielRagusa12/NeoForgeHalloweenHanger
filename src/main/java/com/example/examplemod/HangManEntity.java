package com.example.examplemod;


import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import com.example.examplemod.CameraPlayerOnNoose;
import com.example.examplemod.HangManCommon;
import net.neoforged.fml.common.EventBusSubscriber;


@EventBusSubscriber(modid = HangManCommon.MODID, bus = EventBusSubscriber.Bus.MOD)
public class HangManEntity {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(HangManCommon.MODID);
    public static final DeferredItem<Item> ENTITY_TYPES = ITEMS.registerSimpleItem(HangManCommon.MODID);
    public static final DeferredRegister<EntityType<?>> HANG_DUMMY = ENTITY_TYPES.register(HangManCommon.noose,
            () -> EntityType.Builder.of(ForgeNooseEntity::new, MobCategory.MISC)
                    .setTrackingRange(256).setUpdateInterval(20).noSummon().sized(0.001F, 0.001F).build(HangManCommon.noose));

    public static final DeferredRegister<EntityType<CameraPlayerOnNoose>> CAMERA = ENTITY_TYPES.register(HangManCommon.camera,
            () -> EntityType.Builder.<CameraPlayerOnNoose>of(CameraPlayerOnNoose::new, MobCategory.MISC)
                    .setTrackingRange(256).setUpdateInterval(20).noSave().noSummon().sized(0.001F, 0.001F).build(HangManCommon.camera));
}
