package net.bannerretrieval.mixin;

import net.bannerretrieval.Bannerretrieval;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.bannerretrieval.CauldronBehaviorExtended;

import java.lang.reflect.Field;

@Mixin(CauldronBehavior.class)
public interface CauldronBehaviorMixin {
    @Inject(method = "registerBehavior", at=@At("TAIL"))
    private static void bannerretrieval$injectRegisterBehavior(CallbackInfo ci) {
        CauldronBehavior.WATER_CAULDRON_BEHAVIOR.map().put(Items.SHIELD, CauldronBehaviorExtended.WASH_SHIELD);

        if (!FabricLoader.getInstance().isModLoaded("lolmsv")) { return; }
        try {
            Class<?> MoreShieldVariantItems = Class.forName("de.pnku.lolmsv.item.MoreShieldVariantItems");
            Field ACACIA_SHIELD = MoreShieldVariantItems.getField("ACACIA_SHIELD");
            Field BAMBOO_SHIELD = MoreShieldVariantItems.getField("BAMBOO_SHIELD");
            Field BIRCH_SHIELD = MoreShieldVariantItems.getField("BIRCH_SHIELD");
            Field CHERRY_SHIELD = MoreShieldVariantItems.getField("CHERRY_SHIELD");
            Field CRIMSON_SHIELD = MoreShieldVariantItems.getField("CRIMSON_SHIELD");
            Field DARK_OAK_SHIELD = MoreShieldVariantItems.getField("DARK_OAK_SHIELD");
            Field JUNGLE_SHIELD = MoreShieldVariantItems.getField("JUNGLE_SHIELD");
            Field MANGROVE_SHIELD = MoreShieldVariantItems.getField("MANGROVE_SHIELD");
            Field OAK_SHIELD = MoreShieldVariantItems.getField("OAK_SHIELD");
            Field WARPED_SHIELD = MoreShieldVariantItems.getField("WARPED_SHIELD");
            Field PALE_OAK_SHIELD = MoreShieldVariantItems.getField("PALE_OAK_SHIELD");

            CauldronBehavior.WATER_CAULDRON_BEHAVIOR.map().put((Item) ACACIA_SHIELD.get(null), CauldronBehaviorExtended.WASH_SHIELD);
            CauldronBehavior.WATER_CAULDRON_BEHAVIOR.map().put((Item) BAMBOO_SHIELD.get(null), CauldronBehaviorExtended.WASH_SHIELD);
            CauldronBehavior.WATER_CAULDRON_BEHAVIOR.map().put((Item) BIRCH_SHIELD.get(null), CauldronBehaviorExtended.WASH_SHIELD);
            CauldronBehavior.WATER_CAULDRON_BEHAVIOR.map().put((Item) CHERRY_SHIELD.get(null), CauldronBehaviorExtended.WASH_SHIELD);
            CauldronBehavior.WATER_CAULDRON_BEHAVIOR.map().put((Item) CRIMSON_SHIELD.get(null), CauldronBehaviorExtended.WASH_SHIELD);
            CauldronBehavior.WATER_CAULDRON_BEHAVIOR.map().put((Item) DARK_OAK_SHIELD.get(null), CauldronBehaviorExtended.WASH_SHIELD);
            CauldronBehavior.WATER_CAULDRON_BEHAVIOR.map().put((Item) JUNGLE_SHIELD.get(null), CauldronBehaviorExtended.WASH_SHIELD);
            CauldronBehavior.WATER_CAULDRON_BEHAVIOR.map().put((Item) MANGROVE_SHIELD.get(null), CauldronBehaviorExtended.WASH_SHIELD);
            CauldronBehavior.WATER_CAULDRON_BEHAVIOR.map().put((Item) OAK_SHIELD.get(null), CauldronBehaviorExtended.WASH_SHIELD);
            CauldronBehavior.WATER_CAULDRON_BEHAVIOR.map().put((Item) WARPED_SHIELD.get(null), CauldronBehaviorExtended.WASH_SHIELD);
            CauldronBehavior.WATER_CAULDRON_BEHAVIOR.map().put((Item) PALE_OAK_SHIELD.get(null), CauldronBehaviorExtended.WASH_SHIELD);

        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            Bannerretrieval.LOGGER.error("Failed to access third-party mod class or method: {}", e.getMessage());
        }
    }
}
