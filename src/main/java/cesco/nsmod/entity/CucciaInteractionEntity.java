package cesco.nsmod.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.InteractionEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CucciaInteractionEntity extends InteractionEntity {
    private @Nullable BlockPos ownerPos;

    public CucciaInteractionEntity(EntityType<? extends InteractionEntity> type, World world) {
        super(type, world);
        this.setInvisible(true);
        this.setNoGravity(true);
    }

    @Override
    protected void initDataTracker(net.minecraft.entity.data.DataTracker.Builder builder) {
        super.initDataTracker(builder);
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("OwnerX") && nbt.contains("OwnerY") && nbt.contains("OwnerZ")) {
            this.ownerPos = new BlockPos(nbt.getInt("OwnerX"), nbt.getInt("OwnerY"), nbt.getInt("OwnerZ"));
        }
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        if (ownerPos != null) {
            nbt.putInt("OwnerX", ownerPos.getX());
            nbt.putInt("OwnerY", ownerPos.getY());
            nbt.putInt("OwnerZ", ownerPos.getZ());
        }
    }

    public @Nullable BlockPos getOwnerPos() {
        return ownerPos;
    }

    public void setOwnerPos(BlockPos pos) {
        this.ownerPos = pos;
    }

    @Override
    public void kill() {
        if (!this.getWorld().isClient() && this.getWorld() instanceof ServerWorld serverWorld) {
            this.discard(); // Elimina l'entità senza effetti sonori
        }
    }
}
