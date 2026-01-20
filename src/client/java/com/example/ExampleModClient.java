package com.example;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.debug.DebugRenderer;
import net.minecraft.core.BlockPos;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class ExampleModClient implements ClientModInitializer {
    
    public static final Map<BlockPos, Integer> CACHE = new ConcurrentHashMap<>();

    @Override
    public void onInitializeClient() {
        WorldRenderEvents.AFTER_ENTITIES.register(context -> {
            if (CACHE.isEmpty()) return;

            var matrices = context.matrixStack();
            var consumers = context.consumers();
            var cameraPos = context.camera().getPosition();
            
            for (Map.Entry<BlockPos, Integer> entry : CACHE.entrySet()) {
                BlockPos pos = entry.getKey();
                
                matrices.pushPose();
                
                double x = pos.getX() - cameraPos.x;
                double y = pos.getY() - cameraPos.y;
                double z = pos.getZ() - cameraPos.z;
                
                matrices.translate(x, y, z);

                float r = 1.0f;
                float g = 0.5f;
                float b = 0.0f;
                float a = 0.4f;

                DebugRenderer.drawFilledBox(matrices, consumers, 0, 0, 0, 1, 1, 1, r, g, b, a);
                
                matrices.popPose();
            }
        });
    }
}
