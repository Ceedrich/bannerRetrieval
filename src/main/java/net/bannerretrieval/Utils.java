package net.bannerretrieval;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.DyeColor;

public class Utils {

    public static final String DECORATION_TAG_KEY = "DecorationTag";

    public static final double Y_OFFSET = 0.8;
    public static final double Y_VELOCITY = 0.2;

    public static Item getBannerItem(DyeColor color) {
        if (color.equals(DyeColor.WHITE)) return Items.WHITE_BANNER;
        if (color.equals(DyeColor.LIGHT_GRAY)) return Items.LIGHT_GRAY_BANNER;
        if (color.equals(DyeColor.GRAY)) return Items.GRAY_BANNER;
        if (color.equals(DyeColor.BLACK)) return Items.BLACK_BANNER;
        if (color.equals(DyeColor.BROWN)) return Items.BROWN_BANNER;
        if (color.equals(DyeColor.RED)) return Items.RED_BANNER;
        if (color.equals(DyeColor.ORANGE)) return Items.ORANGE_BANNER;
        if (color.equals(DyeColor.YELLOW)) return Items.YELLOW_BANNER;
        if (color.equals(DyeColor.LIME)) return Items.LIME_BANNER;
        if (color.equals(DyeColor.GREEN)) return Items.GREEN_BANNER;
        if (color.equals(DyeColor.CYAN)) return Items.CYAN_BANNER;
        if (color.equals(DyeColor.LIGHT_BLUE)) return Items.LIGHT_BLUE_BANNER;
        if (color.equals(DyeColor.BLUE)) return Items.BLUE_BANNER;
        if (color.equals(DyeColor.PURPLE)) return Items.PURPLE_BANNER;
        if (color.equals(DyeColor.MAGENTA)) return Items.MAGENTA_BANNER;
        if (color.equals(DyeColor.PINK)) return Items.PINK_BANNER;

        throw new IllegalStateException("Unexpected value: " + color);
    }

    /*
    public static NbtCompound getDecorationNbt(ItemStack stack) {
        return stack.getSubNbt(DECORATION_TAG_KEY);
    }

     */
}
