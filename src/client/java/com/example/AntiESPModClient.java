package com.example;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.core.BlockPos;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class AntiESPModClient implements ClientModInitializer {
    public static final Map<BlockPos, Integer> CACHE = new ConcurrentHashMap<>();

    @Override
    public void onInitializeClient() {
       
    }
}
