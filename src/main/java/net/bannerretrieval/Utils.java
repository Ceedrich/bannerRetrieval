package net.bannerretrieval;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

import java.util.HashMap;
import java.util.Map;

public class Utils {
    public static final Map<Integer, String> COLOR_MAP = new HashMap<>();
    static {
        COLOR_MAP.put(0, "white");
        COLOR_MAP.put(1, "orange");
        COLOR_MAP.put(2, "magenta");
        COLOR_MAP.put(3, "light_blue");
        COLOR_MAP.put(4, "yellow");
        COLOR_MAP.put(5, "lime");
        COLOR_MAP.put(6, "pink");
        COLOR_MAP.put(7, "gray");
        COLOR_MAP.put(8, "light_gray");
        COLOR_MAP.put(9, "cyan");
        COLOR_MAP.put(10, "purple");
        COLOR_MAP.put(11, "blue");
        COLOR_MAP.put(12, "brown");
        COLOR_MAP.put(13, "green");
        COLOR_MAP.put(14, "red");
        COLOR_MAP.put(15, "black");
    }

    public static final String DECORATION_TAG_KEY = "DecorationTag";

    public static final double Y_OFFSET = 0.8;
    public static final double Y_VELOCITY = 0.2;

    public static NbtCompound getDecorationNbt(ItemStack stack) {
        return stack.getSubNbt(DECORATION_TAG_KEY);
    }
}
