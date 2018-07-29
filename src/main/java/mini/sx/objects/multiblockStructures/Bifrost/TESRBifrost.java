package mini.sx.objects.multiblockStructures.Bifrost;

import mini.sx.util.Reference;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.obj.OBJLoader;
import org.lwjgl.opengl.GL11;

public class TESRBifrost extends TileEntitySpecialRenderer {

    public static final ResourceLocation texture = new ResourceLocation(Reference.RESOURCE_PREFIX + "textures/multiblock/bifrost.png");
    private IModel model;

    public TESRBifrost() {
        try {
            model = OBJLoader.INSTANCE.loadModel(new ResourceLocation(Reference.RESOURCE_PREFIX + "models/block/obj/bifrost.obj"));
        } catch (Exception ignored) {
        }
    }

    @Override
    public void renderTileEntityFast(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer) {
        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);
        this.bindTexture(texture);
        GL11.glPopMatrix();
    }
}
