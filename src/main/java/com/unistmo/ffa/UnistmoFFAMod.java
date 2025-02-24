package com.unistmo.ffa;

import com.unistmo.ffa.commands.FFACommand;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class UnistmoFFAMod implements ModInitializer {

    @Override
    public void onInitialize() {
        // Registra los comandos
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            FFACommand.registerCommands(dispatcher);
        });

        System.out.println("UnistmoFFA cargado correctamente!");
    }
}