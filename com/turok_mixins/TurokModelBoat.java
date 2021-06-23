package com.turok_mixins;

import com.oldturok.turok.module.ModuleManager;
import com.oldturok.turok.module.modules.movement.EntitySpeed;
import com.oldturok.turok.util.Wrapper;
import net.minecraft.client.model.ModelBoat;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ModelBoat.class})
public class TurokModelBoat {
   @Inject(
      method = {"render"},
      at = {@At("HEAD")}
   )
   public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, CallbackInfo info) {
      if (Wrapper.getPlayer().func_184187_bx() == entityIn && ModuleManager.isModuleEnabled("EntitySpeed")) {
         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, EntitySpeed.getOpacity());
         GlStateManager.func_179147_l();
      }

   }
}
