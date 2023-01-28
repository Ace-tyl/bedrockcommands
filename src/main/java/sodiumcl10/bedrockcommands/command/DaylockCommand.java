package sodiumcl10.bedrockcommands.command;

import com.mojang.brigadier.Command;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.GameRules;
import net.minecraft.world.GameRules.*;
import static net.minecraft.server.command.CommandManager.literal;
import static net.minecraft.text.Text.translatable;

public class DaylockCommand {
    static Command<ServerCommandSource> daylock_true = ctx -> {
        MinecraftServer server = ctx.getSource().getServer();
        BooleanRule rule = server.getGameRules().get(GameRules.DO_DAYLIGHT_CYCLE);
        rule.set(false, server);
        for (ServerWorld world : server.getWorlds()) {
            world.setTimeOfDay(5000L);
        }
        ctx.getSource().sendMessage(translatable("commands.daylock.true.success"));
        return 1;
    };

    static Command<ServerCommandSource> daylock_false = ctx -> {
        MinecraftServer server = ctx.getSource().getServer();
        BooleanRule rule = server.getGameRules().get(GameRules.DO_DAYLIGHT_CYCLE);
        rule.set(true, server);
        ctx.getSource().sendMessage(translatable("commands.daylock.false.success"));
        return 1;
    };

    public static void RegisterCommand(String cmd) {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(
                literal(cmd).requires(ctx -> ctx.hasPermissionLevel(2))
                        .then(literal("true").executes(DaylockCommand.daylock_true))
                        .then(literal("false").executes(DaylockCommand.daylock_false))
        ));
    }
}
