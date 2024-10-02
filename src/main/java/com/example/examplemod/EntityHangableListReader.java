package com.example.examplemod;


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

public class EntityHangableListReader extends SimplePreparableReloadListener<ArrayList<JsonObject>> {

    private static final HashMap<ResourceLocation, EntityHangable> mappedEntities = new HashMap<>();

    public static EntityHangable get(EntityType<?> entityType) {

        ResourceLocation resLoc = BuiltInRegistries.ENTITY_TYPE.getKey(entityType);
        if (mappedEntities.containsKey(resLoc))
            return mappedEntities.get(resLoc);

        return null;
    }

    public static boolean has(EntityType<?> entityType) {
        ResourceLocation resLoc = BuiltInRegistries.ENTITY_TYPE.getKey(entityType);
        return mappedEntities.containsKey(resLoc);
    }

    @Override
    protected ArrayList<JsonObject> prepare(ResourceManager resourceManager, ProfilerFiller profilerFiller) {

        ArrayList<JsonObject> theJsonFiles = Lists.newArrayList();

        Collection<ResourceLocation> jsonfiles = resourceManager.listResources("noose_entities", (filename) -> filename.getPath().endsWith(".json")).keySet();

        List<Resource> jsons = new ArrayList<>();

        for (ResourceLocation resLoc : jsonfiles) {
            jsons.addAll(resourceManager.getResourceStack(resLoc));
        }

        Gson gson = new GsonBuilder().create();

        for (Resource res : jsons) {
            try {
                BufferedReader reader = res.openAsReader(); ;
                JsonElement je = gson.fromJson(reader, JsonElement.class);
                JsonObject json = je.getAsJsonObject();

                if (json.has("hangable")) {
                    theJsonFiles.add(json);
                }
            } catch (IOException e) {
                HangManCommon.LOG.warn("************************************");
                HangManCommon.LOG.warn("!*!*!*!*!");
                HangManCommon.LOG.warn("resource {} couldn't be loaded", res);
                HangManCommon.LOG.warn(e.getMessage());
            }
        }
        return theJsonFiles;
    }

    @Override
    protected void apply(ArrayList<JsonObject> jsonFiles, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        ConfigReader.reloadServer();
        if (!jsonFiles.isEmpty()) {

            Runnable run = () -> {
                for (JsonObject the_file : jsonFiles) {
                    JsonArray array = the_file.getAsJsonArray("hangable");
                    for (int i = 0; i < array.size(); i++) {
                        JsonObject jsonObject = array.get(i).getAsJsonObject();

                        String entity = jsonObject.get("entity").getAsString();
                        double offset = 0.35d;
                        boolean dmg = true;

                        if (jsonObject.has("offset")) {
                            offset = jsonObject.get("offset").getAsDouble();
                        }
                        if (jsonObject.has("takesDamage")) {
                            dmg = jsonObject.get("takesDamage").getAsBoolean();
                        }

                        // Use tryCreate to create the ResourceLocation
                        ResourceLocation entityLocation = ResourceLocation.parse(entity);
                        if (entityLocation != null) { // Check if the ResourceLocation was created successfully
                            // Get the EntityType from the ResourceLocation
                            EntityType<?> entityType = (EntityType<?>) NeoForgeRegistries.ENTITY_DATA_SERIALIZERS.get(entityLocation);

                            if (entityType != null) { // Check if the EntityType exists
                                ResourceLocation resourceLocation = EntityType.getKey(entityType);
                                mappedEntities.put(resourceLocation, new EntityHangable(entityType.getDescriptionId(), offset, dmg));
                            } else {
                                // Handle the case where the EntityType is not found
                                System.err.println("EntityType not found for: " + entity);
                            }
                        } else {
                            // Handle the case where ResourceLocation creation failed
                            System.err.println("Failed to create ResourceLocation for: " + entity);
                        }


                    }
                }
            };
            run.run();
        }
    }
}
