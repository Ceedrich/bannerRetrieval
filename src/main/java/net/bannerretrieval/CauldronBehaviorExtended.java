package net.bannerretrieval;

import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.entity.BannerPatternLayers;

public interface CauldronBehaviorExtended {
    CauldronInteraction WASH_SHIELD = (state, world, pos, player, hand, stack) -> {
        BannerPatternLayers bannerPatternsComponent = stack.getOrDefault(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY);
        DyeColor bannerColor = stack.get(DataComponents.BASE_COLOR);
        assert bannerColor != null;

        if (bannerPatternsComponent.layers().isEmpty()) {
            return InteractionResult.TRY_WITH_EMPTY_HAND;
        }
        if (world.isClientSide()) return InteractionResult.SUCCESS;

        ItemStack shieldItemStack = stack.copy();

        shieldItemStack.remove(DataComponents.BASE_COLOR);
        shieldItemStack.set(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY);

        ItemStack bannerItemStack = new ItemStack(bannerItemFromColor(bannerColor));

        bannerItemStack.set(DataComponents.BANNER_PATTERNS, bannerPatternsComponent);

        stack.consume(1, player);
        if (stack.isEmpty()) {
            player.setItemInHand(hand, shieldItemStack);
        } else if (player.getInventory().add(shieldItemStack)) {
            player.inventoryMenu.sendAllDataToRemote();
        } else {
            player.drop(shieldItemStack, false);
        }
        if (player.getInventory().add(bannerItemStack)) {
            player.inventoryMenu.sendAllDataToRemote();
        } else {
            player.drop(bannerItemStack, false);
        }

        player.awardStat(Stats.USE_CAULDRON);
        LayeredCauldronBlock.lowerFillLevel(state, world, pos);
        return InteractionResult.SUCCESS;
    };

    private static Item bannerItemFromColor(DyeColor color) {
        switch (color) {
            case RED -> {
                return Items.RED_BANNER;
            }
            case ORANGE -> {
                return Items.ORANGE_BANNER;
            }
            case YELLOW -> {
                return Items.YELLOW_BANNER;
            }
            case LIME -> {
                return Items.LIME_BANNER;
            }
            case GREEN -> {
                return Items.GREEN_BANNER;
            }
            case CYAN -> {
                return Items.CYAN_BANNER;
            }
            case LIGHT_BLUE -> {
                return Items.LIGHT_BLUE_BANNER;
            }
            case BLUE -> {
               return Items.BLUE_BANNER;
            }
            case MAGENTA -> {
                return Items.MAGENTA_BANNER;
            }
            case PURPLE -> {
                return Items.PURPLE_BANNER;
            }
            case PINK -> {
                return Items.PINK_BANNER;
            }
            case BROWN -> {
                return Items.BROWN_BANNER;
            }
            case WHITE -> {
                return Items.WHITE_BANNER;
            }
            case LIGHT_GRAY -> {
                return Items.LIGHT_GRAY_BANNER;
            }
            case GRAY -> {
                return Items.GRAY_BANNER;
            }
            case BLACK -> {
                return Items.BLACK_BANNER;
            }
        }
        return Items.PURPLE_BANNER;
    }
}
