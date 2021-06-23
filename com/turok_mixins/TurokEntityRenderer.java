package com.turok_mixins;

import com.oldturok.turok.module.ModuleManager;
import com.oldturok.turok.module.modules.render.Brightness;
import com.oldturok.turok.module.modules.render.NoHurtEffect;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({EntityRenderer.class})
public class TurokEntityRenderer {
   private boolean nightVision = false;

   @Redirect(
      method = {"orientCamera"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/multiplayer/WorldClient;rayTraceBlocks(Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/math/Vec3d;)Lnet/minecraft/util/math/RayTraceResult;"
)
   )
   public RayTraceResult rayTraceBlocks(WorldClient world, Vec3d start, Vec3d end) {
      return ModuleManager.isModuleEnabled("BypassCamera") ? null : world.func_72933_a(start, end);
   }

   @Inject(
      method = {"hurtCameraEffect"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void hurtCameraEffect(float ticks, CallbackInfo info) {
      if (NoHurtEffect.shouldDisable()) {
         info.cancel();
      }

   }

   @Redirect(
      method = {"updateLightmap"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/entity/EntityPlayerSP;isPotionActive(Lnet/minecraft/potion/Potion;)Z"
)
   )
   public boolean isPotionActive(EntityPlayerSP player, Potion potion) {
      return (this.nightVision = Brightness.shouldBeActive()) || player.func_70644_a(potion);
   }

   @Redirect(
      method = {"updateLightmap"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/renderer/EntityRenderer;getNightVisionBrightness(Lnet/minecraft/entity/EntityLivingBase;F)F"
)
   )
   public float getNightVisionBrightnessMixin(EntityRenderer renderer, EntityLivingBase entity, float partialTicks) {
      return this.nightVision ? Brightness.getCurrentBrightness() : renderer.func_180438_a(entity, partialTicks);
   }
}
