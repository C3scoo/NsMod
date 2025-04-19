package cesco.nsmod.entity.custom;

import net.minecraft.entity.decoration.InteractionEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CucciaInteractionEntity extends InteractionEntity {
    private BlockPos ownerPos;

    public CucciaInteractionEntity(EntityType<? extends InteractionEntity> entityType, World world) {
        super(entityType, world);
    }

    public void setOwnerPos(BlockPos pos) {
        this.ownerPos = pos;
    }

    public BlockPos getOwnerPos() {
        return ownerPos;
    }

    public static @Nullable CucciaInteractionEntity find(ServerWorld world, BlockPos pos) {
        List<CucciaInteractionEntity> list = world.getEntitiesByClass(
                CucciaInteractionEntity.class,
                new net.minecraft.util.math.Box(pos).expand(1),
                e -> pos.equals(e.getOwnerPos())
        );
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public ActionResult interact(PlayerEntity player, Hand hand) {
        if (this.getWorld().isClient) return ActionResult.SUCCESS;
        player.sendMessage(Text.literal("Hai cliccato sulla cuccia!"), false);
        return ActionResult.SUCCESS;
    }
}
