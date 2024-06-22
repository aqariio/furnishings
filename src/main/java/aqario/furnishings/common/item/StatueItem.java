package aqario.furnishings.common.item;

import aqario.furnishings.common.entity.FurnishingsEntityType;
import aqario.furnishings.common.entity.StatueEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class StatueItem extends Item {
    public StatueItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        Direction direction = context.getSide();
        if (direction == Direction.DOWN) {
            return ActionResult.FAIL;
        }
        else {
            World world = context.getWorld();
            ItemPlacementContext itemPlacementContext = new ItemPlacementContext(context);
            BlockPos blockPos = itemPlacementContext.getBlockPos();
            ItemStack itemStack = context.getStack();
            Vec3d vec3d = Vec3d.ofBottomCenter(blockPos);
            Box box = FurnishingsEntityType.STATUE.getDimensions().getBoxAt(vec3d.getX(), vec3d.getY(), vec3d.getZ());
            if (world.isSpaceEmpty(null, box) && world.getOtherEntities(null, box).isEmpty()) {
                if (world instanceof ServerWorld serverWorld) {
                    StatueEntity statueEntity = FurnishingsEntityType.STATUE
                        .create(serverWorld, itemStack.getNbt(), null, blockPos, SpawnReason.SPAWN_EGG, true, true);
                    if (statueEntity == null) {
                        return ActionResult.FAIL;
                    }

                    float f = (float) MathHelper.floor((MathHelper.wrapDegrees(context.getPlayerYaw() - 180.0F) + 22.5F) / 45.0F) * 45.0F;
                    statueEntity.refreshPositionAndAngles(statueEntity.getX(), statueEntity.getY(), statueEntity.getZ(), f, 0.0F);
                    serverWorld.spawnEntityAndPassengers(statueEntity);
                    world.playSound(
                        null, statueEntity.getX(), statueEntity.getY(), statueEntity.getZ(), statueEntity.getPlaceSound(), SoundCategory.BLOCKS, 0.75F, 0.8F
                    );
                    statueEntity.emitGameEvent(GameEvent.ENTITY_PLACE, context.getPlayer());
                }

                itemStack.decrement(1);
                return ActionResult.success(world.isClient);
            }
            else {
                return ActionResult.FAIL;
            }
        }
    }
}
