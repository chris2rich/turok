package com.turok_mixins;

import com.oldturok.turok.TurokMod;
import com.oldturok.turok.event.events.PlayerMoveEvent;
import com.oldturok.turok.module.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.MoverType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({EntityPlayerSP.class})
public class TurokEntityPlayerSP {
   @Redirect(
      method = {"onLivingUpdate"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/entity/EntityPlayerSP;closeScreen()V"
)
   )
   public void closeScreen(EntityPlayerSP entityPlayerSP) {
      if (!ModuleManager.isModuleEnabled("PortalChat")) {
         ;
      }
   }

   @Redirect(
      method = {"onLivingUpdate"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/Minecraft;displayGuiScreen(Lnet/minecraft/client/gui/GuiScreen;)V"
)
   )
   public void closeScreen(Minecraft minecraft, GuiScreen screen) {
      if (!ModuleManager.isModuleEnabled("PortalChat")) {
         ;
      }
   }

   @Inject(
      method = {"move"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void move(MoverType type, double x, double y, double z, CallbackInfo info) {
      PlayerMoveEvent event = new PlayerMoveEvent(type, x, y, z);
      TurokMod.EVENT_BUS.post(event);
      if (event.isCancelled()) {
         info.cancel();
      }

   }
}
