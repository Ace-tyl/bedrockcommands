package sodiumcl10.bedrockcommands;

import net.fabricmc.api.ModInitializer;
import sodiumcl10.bedrockcommands.command.*;

public class Bedrockcommands implements ModInitializer {
    @Override
    public void onInitialize() {
        DaylockCommand.RegisterCommand("daylock");
        DaylockCommand.RegisterCommand("alwaysday");
    }
}
