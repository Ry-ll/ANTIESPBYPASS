package com.example;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.core.BlockPos;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class ExampleModClient implements ClientModInitializer {
    
    // Lista global de cofres detectados
    public static final Map<BlockPos, Integer> CACHE = new ConcurrentHashMap<>();

    @Override
    public void onInitializeClient() {
        WorldRenderEvents.END.register(context -> {
            if (CACHE.isEmpty()) return;
            
            // El renderizado se activa aqui. Por ahora el bypass 
            // ya estara guardando los datos gracias al Mixin.
        });
    }
}
