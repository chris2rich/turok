package com.turok_mixins;

import com.oldturok.turok.TurokMod;
import com.oldturok.turok.event.events.PacketEvent;
import com.oldturok.turok.module.modules.misc.NoPackKick;
import io.netty.channel.ChannelHandlerContext;
import java.io.IOException;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({NetworkManager.class})
public class TurokNetworkManager {
   @Inject(
      method = {"sendPacket(Lnet/minecraft/network/Packet;)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onSendPacket(Packet<?> packet, CallbackInfo callbackInfo) {
      PacketEvent event = new PacketEvent.Send(packet);
      TurokMod.EVENT_BUS.post(event);
      if (event.isCancelled()) {
         callbackInfo.cancel();
      }

   }

   @Inject(
      method = {"channelRead0"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onChannelRead(ChannelHandlerContext context, Packet<?> packet, CallbackInfo callbackInfo) {
      PacketEvent event = new PacketEvent.Receive(packet);
      TurokMod.EVENT_BUS.post(event);
      if (event.isCancelled()) {
         callbackInfo.cancel();
      }

   }

   @Inject(
      method = {"exceptionCaught"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void exceptionCaught(ChannelHandlerContext p_exceptionCaught_1_, Throwable p_exceptionCaught_2_, CallbackInfo info) {
      if (p_exceptionCaught_2_ instanceof IOException && NoPackKick.isEnabled()) {
         info.cancel();
      }

   }
}
