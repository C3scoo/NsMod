package cesco.nsmod;

import cesco.nsmod.block.ModBlocks;
import cesco.nsmod.command.NsModSelectDogCommand;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NsMod implements ModInitializer {
    public static final String MOD_ID = "nsmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Hello Fabric world!");
        ModBlocks.initialize();
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
        dispatcher.register(NsModSelectDogCommand.register());
    	});
    }
}
