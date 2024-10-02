package com.example.examplemod;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.example.examplemod.DamageSourceHang;
import com.google.common.collect.Lists;
import com.google.gson.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.EntityType;
import com.example.examplemod.ConfigReader;
import com.example.examplemod.HangManCommon;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public abstract class HangManCommon {
    public static final String MODID = "hangman";
    public static final Logger LOG = LogManager.getLogger();
    public static final DamageSource HANGING = new DamageSourceHang("hanging");

    public static final String noose = "hang_dummy";
    public static final String camera = "camera";

    public static final ResourceLocation NOOSE = ResourceLocation.fromNamespaceAndPath(MODID, noose);
    public static final ResourceLocation CAMERA = ResourceLocation.fromNamespaceAndPath(MODID, camera);


}