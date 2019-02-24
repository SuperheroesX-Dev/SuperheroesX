package com.sx_dev.sx.util.handlers;

import com.sx_dev.sx.util.Reference;
import com.sx_dev.sx.util.network.message.MessageKeyBind;
import com.sx_dev.sx.util.network.message.MessageKeyboardSync;
import com.sx_dev.sx.util.network.message.MessageMouseSync;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;


public abstract class PacketHandler {

    private static final String PROTOCOL_VERSION = Integer.toString(1);

    public static final SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(Reference.MODID, "main_channel"))
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .simpleChannel();

    private static int ID = 0;

    private static int nextID() {
        return ID++;
    }

    public static void registerMessages() {

        INSTANCE.registerMessage(nextID(), MessageKeyboardSync.class,
                MessageKeyboardSync::toBytes,
                MessageKeyboardSync::new,
                MessageKeyboardSync::handle);

        INSTANCE.registerMessage(nextID(), MessageKeyBind.class,
                MessageKeyBind::toBytes,
                MessageKeyBind::new,
                MessageKeyBind::handle);

        INSTANCE.registerMessage(nextID(), MessageMouseSync.class,
                MessageMouseSync::toBytes,
                MessageMouseSync::new,
                MessageMouseSync::handle);

        /*INSTANCE.registerMessage(nextID(), MessageIronmanArmorSync.class,
                MessageIronmanArmorSync::toBytes,
                MessageIronmanArmorSync::new,
                MessageIronmanArmorSync::handle);*/

        /*INSTANCE.registerMessage(nextID(), MessageConfigSync.class,
                MessageConfigSync::toBytes,
                MessageConfigSync::new,
                MessageConfigSync::handle);*/
    }

    /*public static void init() {
        int id = 0;
        SuperheroesX.LOGGER.info("Registering network messages");
        INSTANCE.registerMessage(id++, MessageIronmanArmorSync.class, MessageIronmanArmorSync.class, 0, Side.CLIENT);
        INSTANCE.registerMessage(id++, MessageConfigSync.class, MessageConfigSync.class, 1, Side.CLIENT);
        INSTANCE.registerMessage(id++, MessageKeyboardSync.class, MessageKeyboardSync.class, 2, Side.SERVER);
        INSTANCE.registerMessage(id++, MessageKeyBind.class, MessageKeyBind.class, 4, Side.SERVER);
        INSTANCE.registerMessage(id++, MessageMouseSync.class, MessageMouseSync.class, 5, Side.SERVER);
    }*/
}
