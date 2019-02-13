package sx_dev.sx.util.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import sx_dev.sx.SuperheroesX;
import sx_dev.sx.util.Reference;
import sx_dev.sx.util.network.message.*;

public abstract class PacketHandler {

    public static final SimpleNetworkWrapper instance = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.NAME);

    public static void init() {
        SuperheroesX.LOGGER.info("Registering network messages");
        instance.registerMessage(MessageIronmanArmorSync.class, MessageIronmanArmorSync.class, 0, Side.CLIENT);
        instance.registerMessage(MessageConfigSync.class, MessageConfigSync.class, 1, Side.CLIENT);
        instance.registerMessage(MessageKeyboardSync.class, MessageKeyboardSync.class, 2, Side.SERVER);
        instance.registerMessage(MessageKeyBind.class, MessageKeyBind.class, 4, Side.SERVER);
        instance.registerMessage(MessageMouseSync.class, MessageMouseSync.class, 5, Side.SERVER);
    }

    public static EntityPlayer getPlayer(MessageContext context) {
        return SuperheroesX.PROXY.getPlayer(context);
    }
}
