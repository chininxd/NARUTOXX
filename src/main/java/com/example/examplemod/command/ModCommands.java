package com.example.examplemod.command;

import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = "examplemod"
)
public class ModCommands {

    @SubscribeEvent
    public static void registerCommands(
            RegisterCommandsEvent event
    ) {

        // /pt
        PTCommand.register(
                event.getDispatcher()
        );

        // /narutox
        NarutoXCommand.register(
                event.getDispatcher()
        );
    }
}