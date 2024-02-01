package net.bannerretrieval.mixin;

import net.minecraft.block.AbstractCauldronBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.Map;

@Mixin(AbstractCauldronBlock.class)
abstract public class ShieldWash {

    @Unique
    private static final Map<Integer, String> COLOR_MAP = new HashMap<>();
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

    @Unique
    private static final double Y_OFFSET = 0.8;
    @Unique
    private static final double Y_VELOCITY = 0.2;
    @Shadow
    @Final
    protected CauldronBehavior.CauldronBehaviorMap behaviorMap;

    @Inject(method = "onUse", at = @At("HEAD"))
    public void onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.isOf(Items.SHIELD)) {
            NbtCompound nbtData = itemStack.getNbt();
            if (
                    nbtData != null &&
                            !nbtData.isEmpty() &&
                            nbtData.contains("BlockEntityTag"))
            {
                NbtCompound shieldBannerNbt = itemStack.getNbt().getCompound("BlockEntityTag").copy();
                itemStack.removeSubNbt("BlockEntityTag");

                String bannerColor = COLOR_MAP.get(shieldBannerNbt.getInt("Base"));
                shieldBannerNbt.remove("Base");

                NbtCompound bannerNBT = new NbtCompound();

                bannerNBT.putString("id", "minecraft:" + bannerColor + "_banner");
                bannerNBT.putInt("Count", 1);

                ItemStack banner = ItemStack.fromNbt(bannerNBT);
                BlockItem.setBlockEntityNbt(banner, BlockEntityType.BANNER, shieldBannerNbt);

                world.spawnEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 0.8, pos.getZ() + 0.5, banner, 0, 0.2, 0));
            }
        }
    }
}