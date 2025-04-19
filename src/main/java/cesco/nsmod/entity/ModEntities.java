package cesco.nsmod.entity;

import cesco.nsmod.NsMod;
import cesco.nsmod.entity.custom.CucciaInteractionEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<CucciaInteractionEntity> CUCCIA_INTERACTION = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(NsMod.MOD_ID, "cuccia_interaction"),
            EntityType.Builder.<CucciaInteractionEntity>create(CucciaInteractionEntity::new, SpawnGroup.MISC)
                    .dimensions(1.0f, 1.0f)
                    .build()
    );

    public static void registerModEntities() {
        NsMod.LOGGER.info("Registering ModEntities for " + NsMod.MOD_ID);
    }
}
