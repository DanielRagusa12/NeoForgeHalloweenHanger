package com.example.examplemod;

import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.example.examplemod.HangManCommon;
import com.example.examplemod.NooseBlock;




public class HangManBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(HangManCommon.MODID);

    public static final DeferredBlock<Block> NOOSE = BLOCKS.register("noose", NooseBlock::new);
}
