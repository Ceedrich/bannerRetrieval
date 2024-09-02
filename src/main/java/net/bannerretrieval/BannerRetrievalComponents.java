package net.bannerretrieval;

import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BannerRetrievalComponents {
    public static final DataComponentType<ComponentMap> BANNER_COMPONENTS = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of("bannerretrieval", "banner_components"),
            DataComponentType.<ComponentMap>builder().codec(ComponentMap.CODEC).build()
    );

    public static void initialize() {}
}
