package com.example;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.renderer.debug.DebugRenderer;
import net.minecraft.core.BlockPos;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class AntiESPModClient implements ClientModInitializer {
    
    public static final Map<BlockPos, Integer> CACHE = new ConcurrentHashMap<>();

    @Override
    public void onInitializeClient() {
        WorldRenderEvents.AFTER_ENTITIES.register(context -> {
            if (CACHE.isEmpty()) return;

            var matrices = context.matrixStack();
            var consumers = context.consumers();
            var cameraPos = context.camera().getPosition();
            
            CACHE.forEach((pos, type) -> {
                matrices.pushPose();
                double x = pos.getX() - cameraPos.x;
                double y = pos.getY() - cameraPos.y;
                double z = pos.getZ() - cameraPos.z;
                matrices.translate(x, y, z);

                float r = 1f, g = 1f, b = 1f;
                if (type == 1) { r = 1.0f; g = 0.6f; b = 0.0f; }
                else if (type == 2) { r = 1.0f; g = 0.0f; b = 0.8f; }
                else if (type == 3) { r = 0.6f; g = 0.0f; b = 1.0f; }

                DebugRenderer.drawFilledBox(matrices, consumers, 0, 0, 0, 1, 1, 1, r, g, b, 0.4f);
                matrices.popPose();
            });
        });
    }
}
