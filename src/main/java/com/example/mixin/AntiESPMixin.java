package com.example.mixin;

import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.network.protocol.game.ClientboundBlockUpdatePacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public class AntiESPMixin {

    @Inject(method = "handleBlockUpdate", at = @At("HEAD"), cancellable = true)
    private void onBlockUpdate(ClientboundBlockUpdatePacket packet, CallbackInfo ci) {
        if (packet.getBlockState().isAir()) {
            ci.cancel(); 
        }
    }

    @Inject(method = "handleBlockEntityData", at = @At("HEAD"))
    private void onBlockEntityData(ClientboundBlockEntityDataPacket packet, CallbackInfo ci) {
    }
}
