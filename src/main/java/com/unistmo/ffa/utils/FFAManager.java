package com.unistmo.ffa.utils;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class FFAManager {

    private static BlockPos spawnPoint;
    private static Map<PlayerEntity, ItemStack[]> kits = new HashMap<>();
    private static Map<PlayerEntity, Vec3d> playerOriginalPositions = new HashMap<>();
    private static Map<PlayerEntity, World> playerOriginalWorlds = new HashMap<>();

    public static void setSpawn(BlockPos pos) {
        spawnPoint = pos;
    }

    public static BlockPos getSpawn() {
        return spawnPoint;
    }

    public static void addPlayer(PlayerEntity player) {
        if (spawnPoint == null) {
            player.sendMessage(Text.literal("¡El spawn del FFA no está configurado!"), false);
            return;
        }

        if (playerOriginalPositions.containsKey(player)) {
            player.sendMessage(Text.literal("¡Ya estás en el FFA! Usa /ffa leave para salir."), false);
            return;
        }

        // Guarda la posición y el mundo actual del jugador
        playerOriginalPositions.put(player, player.getPos());
        playerOriginalWorlds.put(player, player.getWorld());

        // Teletransporta al jugador al spawn del FFA
        if (player.getWorld() instanceof ServerWorld) {
            double x = spawnPoint.getX() + 0.5;
            double y = spawnPoint.getY();
            double z = spawnPoint.getZ() + 0.5;
            player.teleport(x, y, z, false);
        }

        // Dar el kit al jugador
        ItemStack[] kit = kits.get(player);
        if (kit != null) {
            PlayerInventory inventory = player.getInventory();
            for (int i = 0; i < kit.length; i++) {
                inventory.setStack(i, kit[i].copy()); // Copia cada ItemStack del kit
            }
            player.sendMessage(Text.literal("¡Has recibido el kit del FFA!"), false);
        } else {
            player.sendMessage(Text.literal("¡No hay un kit configurado para el FFA!"), false);
        }
    }

    public static void removePlayer(PlayerEntity player) {
        if (!playerOriginalPositions.containsKey(player)) {
            player.sendMessage(Text.literal("¡No estás en el FFA! Usa /ffa join para unirte."), false);
            return;
        }

        // Restaura la posición y el mundo original del jugador
        Vec3d originalPos = playerOriginalPositions.get(player);
        World originalWorld = playerOriginalWorlds.get(player);

        if (originalWorld instanceof ServerWorld) {
        	player.teleport(originalPos.getX(), originalPos.getY(), originalPos.getZ(), false);
        }

        // Limpia el inventario y la armadura del jugador
        PlayerInventory inventory = player.getInventory();
        inventory.clear(); // Limpia el inventario principal

        // Limpia la armadura
        for (int i = 0; i < inventory.armor.size(); i++) {
            inventory.armor.set(i, ItemStack.EMPTY); // Elimina cada pieza de armadura
        }

        // Limpia los datos del jugador
        playerOriginalPositions.remove(player);
        playerOriginalWorlds.remove(player);

        // Mensaje de confirmación
        player.sendMessage(Text.literal("¡Has abandonado el FFA y tu inventario ha sido limpiado!"), false);
    }

    public static void setKit(PlayerEntity player) {
        PlayerInventory inventory = player.getInventory();
        ItemStack[] kit = new ItemStack[inventory.size()];
        for (int i = 0; i < inventory.size(); i++) {
            kit[i] = inventory.getStack(i).copy(); // Copia cada ItemStack
        }
        kits.put(player, kit);
        player.sendMessage(Text.literal("¡Kit del FFA establecido!"), false);
    }
}