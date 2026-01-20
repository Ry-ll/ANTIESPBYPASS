package com.example.mixin;

import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.network.protocol.game.ClientboundBlockUpdatePacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public class ExampleMixin {

    @Inject(method = "handleBlockUpdate", at = @At("HEAD"), cancellable = true)
    private void onBlockUpdate(ClientboundBlockUpdatePacket packet, CallbackInfo ci) {
        // Lógica: Si el server intenta ocultar el cofre (aire) y estás lejos, cancelamos el paquete.
        // Esto mantiene el cofre renderizado en tu pantalla.
        if (packet.getBlockState().isAir()) {
            ci.cancel(); 
        }
    }

    @Inject(method = "handleBlockEntityData", at = @At("HEAD"))
    private void onBlockEntityData(ClientboundBlockEntityDataPacket packet, CallbackInfo ci) {
        // Aquí podrías guardar la posición en una lista para el ESP persistente
    }
}
