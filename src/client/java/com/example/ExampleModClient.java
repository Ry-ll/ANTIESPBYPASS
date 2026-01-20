package com.example;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.core.BlockPos;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class ExampleModClient implements ClientModInitializer {
    
    public static final Map<BlockPos, Integer> DISCOVERED_CHESTS = new ConcurrentHashMap<>();

    @Override
    public void onInitializeClient() {
      
		WorldRenderEvents.END.register(context -> {
            if (DISCOVERED_CHESTS.isEmpty()) return;

            DISCOVERED_CHESTS.forEach((pos, type) -> {
    
            });
        });
    }
}
