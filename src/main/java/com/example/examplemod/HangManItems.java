package com.example.examplemod;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.example.examplemod.HangManCommon;

import java.util.function.Supplier;

public class HangManItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(HangManCommon.MODID);

    //public static final DeferredRegister<Item> NOOSE = ITEMS.register("noose", () -> new BlockItem(HangManBlocks.NOOSE.get(), new Item.Properties()));
    public static final Supplier<BlockItem> NOOSE = ITEMS.registerSimpleBlockItem("noose", HangManBlocks.NOOSE, new Item.Properties());
}
