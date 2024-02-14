package net.bannerretrieval;

import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

import static net.bannerretrieval.Utils.*;

public interface CauldronBehaviorExtended {
    CauldronBehavior WASH_SHIELD = (state, world, pos, player, hand, stack) -> {
        Item item = stack.getItem();
        NbtCompound nbtData = stack.getNbt();
        if (
                nbtData == null ||
                nbtData.isEmpty() ||
                !nbtData.contains("BlockEntityTag")
        ) return ActionResult.PASS;

        NbtCompound shieldBannerNbt = nbtData.getCompound("BlockEntityTag").copy();
        stack.removeSubNbt("BlockEntityTag");

        String bannerColor = COLOR_MAP.get(shieldBannerNbt.getInt("Base"));
        shieldBannerNbt.remove("Base");

        NbtCompound bannerNBT = nbtData.contains(DECORATION_TAG_KEY) ? nbtData.getCompound(DECORATION_TAG_KEY).copy() : new NbtCompound();

        NbtCompound bannerItemNbt = new NbtCompound();
        bannerItemNbt.putString("id", "minecraft:" + bannerColor + "_banner");
        bannerItemNbt.putInt("Count", 1);
        bannerItemNbt.put("tag", bannerNBT);

        ItemStack banner = ItemStack.fromNbt(bannerItemNbt);
        BlockItem.setBlockEntityNbt(banner, BlockEntityType.BANNER, shieldBannerNbt);

        world.spawnEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY() + Y_OFFSET, pos.getZ() + 0.5, banner, 0, Y_VELOCITY, 0));

        LeveledCauldronBlock.decrementFluidLevel(state, world, pos);

        player.incrementStat(Stats.USE_CAULDRON);
        player.incrementStat(Stats.USED.getOrCreateStat(item));


        return ActionResult.SUCCESS;
    };
}
