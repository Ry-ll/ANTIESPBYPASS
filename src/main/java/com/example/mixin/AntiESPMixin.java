package com.example.mixin;

import com.example.ExampleModClient;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.network.protocol.game.ClientboundBlockUpdatePacket;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public class AntiESPMixin {

    @Inject(method = "handleBlockUpdate", at = @At("HEAD"), cancellable = true)
    private void onBlockUpdate(ClientboundBlockUpdatePacket packet, CallbackInfo ci) {
        if (ExampleModClient.CACHE.containsKey(packet.getPos()) && packet.getBlockState().isAir()) {
            ci.cancel();
        }
    }

    @Inject(method = "handleBlockEntityData", at = @At("HEAD"))
    private void onBlockEntityData(ClientboundBlockEntityDataPacket packet, CallbackInfo ci) {
        if (packet.getType() == BlockEntityType.CHEST) {
            ExampleModClient.CACHE.put(packet.getPos(), 1);
        }
    }
}
