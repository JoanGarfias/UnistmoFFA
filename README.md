# UnistmoFFA - Plugin de Free For All para Minecraft

![logo unistmo ffa](https://github.com/user-attachments/assets/62c17dce-dd24-4ade-a9a1-bc137c8dbedc)

Un plugin de Free For All (FFA) desarrollado para servidores de Minecraft usando Fabric. Este plugin permite a los jugadores unirse a un modo FFA, recibir un kit personalizado y competir entre ellos.

## Características

- **Comandos Fáciles de Usar**: `/ffa join`, `/ffa leave`, `/ffa setspawn`, `/ffa setkit`, `/ffa info`.
- **Kit Personalizable**: Los administradores pueden definir un kit que los jugadores recibirán al unirse al FFA.
- **Guardado de Posición**: Los jugadores regresan a su posición original al salir del FFA.
- **Limpieza de Inventario**: Al salir del FFA, el inventario y la armadura del jugador se limpian automáticamente.

## Requisitos

- **Minecraft**: Versión 1.21.4.
- **Fabric Loader**: Versión 0.15.11 o superior.
- **Fabric API**: Versión 0.96.0 o superior.

## Instalación

1. Descarga el archivo `.jar` del plugin desde la sección de [Releases](https://github.com/tu-usuario/unistmo-ffa/releases).
2. Coloca el archivo `.jar` en la carpeta `mods` de tu servidor Fabric.
3. Reinicia el servidor.

## Uso

### Comandos Disponibles

- **/ffa join**: Únete al FFA. Te teletransportará al spawn del FFA y recibirás el kit configurado.
- **/ffa leave**: Abandona el FFA. Regresarás a tu posición original y se limpiará tu inventario.
- **/ffa setspawn**: Establece el spawn del FFA en tu posición actual.
- **/ffa setkit**: Guarda tu inventario actual como el kit del FFA.
- **/ffa info**: Muestra información sobre el plugin.

### Ejemplos

1. **Configurar el Spawn**:
   ```bash
   /ffa setspawn
1. **Configurar el Kit:**:
   ```bash
   /ffa setkit
1. **Unirse al FFA:**:
   ```bash
   /ffa join
1. **Salir del FFA:**:
   ```bash
   /ffa leave
