package cesco.nsmod.entity;

import cesco.nsmod.NsMod;
import cesco.nsmod.entity.CucciaInteractionEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.decoration.InteractionEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<CucciaInteractionEntity> CUCCIA_INTERACTION = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(NsMod.MOD_ID, "cuccia_interaction"),
            EntityType.Builder.<CucciaInteractionEntity>create(CucciaInteractionEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 0.5f)
                    .build()
    );

    public static void registerModEntities() {
        // Puoi aggiungere log qui se vuoi sapere quando registra
    }
}
