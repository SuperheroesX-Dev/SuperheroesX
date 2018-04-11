package mini.SuperheroesX.entity.model;

import mini.SuperheroesX.entity.EntityKryptonian;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.math.MathHelper;

/**
 * ModelZombie - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
public class ModelKryptonian extends ModelBase
{
    public ModelRenderer Arm_Right;
    public ModelRenderer Leg_Right;
    public ModelRenderer Head;
    public ModelRenderer Body;
    public ModelRenderer Arm_Left;
    public ModelRenderer Leg_Left;
    public ModelRenderer Head_2;

    public ModelKryptonian() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Body = new ModelRenderer(this, 16, 16);
        this.Body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.Arm_Right = new ModelRenderer(this, 40, 16);
        this.Arm_Right.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Arm_Right.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.Arm_Left = new ModelRenderer(this, 40, 16);
        this.Arm_Left.mirror = true;
        this.Arm_Left.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Arm_Left.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.Leg_Left = new ModelRenderer(this, 0, 16);
        this.Leg_Left.mirror = true;
        this.Leg_Left.setRotationPoint(1.9F, 12.0F, 0.1F);
        this.Leg_Left.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.Head_2 = new ModelRenderer(this, 32, 0);
        this.Head_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Head_2.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
        this.Leg_Right = new ModelRenderer(this, 0, 16);
        this.Leg_Right.setRotationPoint(-1.9F, 12.0F, 0.1F);
        this.Leg_Right.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.Body.render(f5);
        this.Arm_Right.render(f5);
        this.Arm_Left.render(f5);
        this.Leg_Left.render(f5);
        this.Head.render(f5);
        this.Head_2.render(f5);
        this.Leg_Right.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    
    public void setRotationAngles(float limbSwing, float limbSwingAmount,float ageInTicks, float netHeadYaw,float headPitch, float scaleFactor, Entity entityIn)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        boolean flag = entityIn instanceof EntityKryptonian && ((EntityKryptonian)entityIn).isArmsRaised();
        float f = MathHelper.sin(this.swingProgress * (float)Math.PI);
        float f1 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float)Math.PI);
        this.Arm_Right.rotateAngleZ = 0.0F;
        this.Arm_Left.rotateAngleZ = 0.0F;
        this.Arm_Right.rotateAngleY = -(0.1F - f * 0.6F);
        this.Arm_Left.rotateAngleY = 0.1F - f * 0.6F;
        float f2 = -(float)Math.PI / (flag ? 1.5F : 2.25F);
        this.Arm_Right.rotateAngleX = f2;
        this.Arm_Left.rotateAngleX = f2;
        this.Arm_Right.rotateAngleX += f * 1.2F - f1 * 0.4F;
        this.Arm_Left.rotateAngleX += f * 1.2F - f1 * 0.4F;
        this.Arm_Right.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.Arm_Left.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.Arm_Right.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.Arm_Left.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        
        this.Head.rotateAngleY = netHeadYaw * 0.017453292F;
        this.Head.rotateAngleX = headPitch * 0.017453292F;

        this.Head_2.rotateAngleY = netHeadYaw * 0.017453292F;
        this.Head_2.rotateAngleX = headPitch * 0.017453292F;

    }
}