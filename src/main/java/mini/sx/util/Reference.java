package mini.sx.util;


@SuppressWarnings("unused")
public class Reference {
    public static final String MODID = "sx";
    public static final String NAME = "Superheroes X";
    public static final String CLIENT = "mini.sx.proxy.ClientProxy";
    public static final String COMMON = "mini.sx.proxy.CommonProxy";
    public static final String PREFIX = MODID + ".";
    public static final String RESOURCE_PREFIX = MODID + ":";
    public static final String MC_VERSION = "[1.12.2]";
    public static final String DEPENDENCIES =
            "required-after:redstoneflux@[2.0.1,2.1.0);" +
                    "required-after:forge@[14.23.3.2655,15.0.0.0);" +
                    "required-after:minecraft;" +
                    "after:thermalexpansion;";
    public static final String VERSION = "0.2.3";

    public static final int ENTITY_KRYPTONIAN = 1;
    public static final int ENTITY_SHIELD = 2;

}
