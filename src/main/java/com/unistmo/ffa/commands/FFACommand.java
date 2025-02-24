package com.unistmo.ffa.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.unistmo.ffa.utils.FFAManager;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class FFACommand {

    public static void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("ffa")
            .then(CommandManager.literal("info")
                .executes(FFACommand::info))
            .then(CommandManager.literal("setspawn")
                .executes(FFACommand::setSpawn))
            .then(CommandManager.literal("join")
                .executes(FFACommand::join))
            .then(CommandManager.literal("leave")
                .executes(FFACommand::leave))
            .then(CommandManager.literal("setkit")
                .executes(FFACommand::setKit)));
    }

    private static int info(CommandContext<ServerCommandSource> context) {
        ServerCommandSource source = context.getSource();
        
        Text formattedMessage = Text.literal("")
                .append(Text.literal("=== Información del FFA ===\n"))
                    .formatted(Formatting.GOLD, Formatting.BOLD) // Color dorado y negrita
                .append(Text.literal("Este plugin fue desarrollado en la\n"))
                    .formatted(Formatting.YELLOW) // Color amarillo
                .append(Text.literal("Universidad del Istmo\n"))
                    .formatted(Formatting.GREEN, Formatting.ITALIC) // Color verde y cursiva
                .append(Text.literal("por JhoanKLZ")
                    .formatted(Formatting.AQUA, Formatting.BOLD)); // Color aqua y negrita

        source.sendFeedback(() -> formattedMessage, false);
        return 1;
    }

    private static int setSpawn(CommandContext<ServerCommandSource> context) {
        ServerCommandSource source = context.getSource();
        if (source.getPlayer() != null) {
            FFAManager.setSpawn(source.getPlayer().getBlockPos());
            source.sendFeedback(() -> Text.literal("Spawn del FFA establecido!"), false);
        }
        return 1;
    }

    private static int join(CommandContext<ServerCommandSource> context) {
        ServerCommandSource source = context.getSource();
        if (source.getPlayer() != null) {
            FFAManager.addPlayer(source.getPlayer());
        }
        return 1;
    }

    private static int leave(CommandContext<ServerCommandSource> context) {
        ServerCommandSource source = context.getSource();
        if (source.getPlayer() != null) {
            FFAManager.removePlayer(source.getPlayer());
        }
        return 1;
    }

    private static int setKit(CommandContext<ServerCommandSource> context) {
        ServerCommandSource source = context.getSource();
        if (source.getPlayer() != null) {
            FFAManager.setKit(source.getPlayer());
        }
        return 1;
    }
}