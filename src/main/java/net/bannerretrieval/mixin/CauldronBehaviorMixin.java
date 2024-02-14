package net.bannerretrieval.mixin;

import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.bannerretrieval.CauldronBehaviorExtended;

@Mixin(CauldronBehavior.class)
public interface CauldronBehaviorMixin {
    @Inject(method = "registerBehavior", at=@At("TAIL"))
    private static void bannerretrieval$injectRegisterBehavior(CallbackInfo ci) {
        CauldronBehavior.WATER_CAULDRON_BEHAVIOR.map().put(Items.SHIELD, CauldronBehaviorExtended.WASH_SHIELD);
    }
}
