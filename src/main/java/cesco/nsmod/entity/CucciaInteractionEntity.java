package cesco.nsmod.entity;

import net.minecraft.entity.decoration.InteractionEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CucciaInteractionEntity extends InteractionEntity {
    @Nullable
    private BlockPos ownerPos;

    public CucciaInteractionEntity(EntityType<? extends InteractionEntity> entityType, World world) {
        super(entityType, world);
    }

    public static CucciaInteractionEntity find(ServerWorld world, BlockPos pos) {
        return world.getEntitiesByType(ModEntities.CUCCIA_INTERACTION, entity -> 
            entity instanceof CucciaInteractionEntity cuccia && 
            cuccia.ownerPos != null && cuccia.ownerPos.equals(pos)
        ).stream().findFirst().orElse(null);
    }

    public void setOwnerPos(BlockPos pos) {
        this.ownerPos = pos;
    }

    public BlockPos getOwnerPos() {
        return ownerPos;
    }
}
