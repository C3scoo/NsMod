package cesco.nsmod.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CucciaInteractionEntity extends Entity {
    private BlockPos ownerPos;

    public CucciaInteractionEntity(EntityType<? extends CucciaInteractionEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void tick() {
        // Aggiungi la logica del tick, se necessario
    }

    public BlockPos getOwnerPos() {
        return this.ownerPos;
    }

    public void setOwnerPos(BlockPos pos) {
        this.ownerPos = pos;
    }

    @Override
    protected void initDataTracker() {
        // Inizializza il tracker dei dati se necessario
    }

    public static CucciaInteractionEntity create(ServerWorld world, BlockPos pos) {
        // Esegui l'inizializzazione per la creazione dell'entità
        return new CucciaInteractionEntity(EntityType.create("cuccia_interaction", CucciaInteractionEntity::new), world);
    }
}
