package com.sx_dev.sx.objects.armor.armorModels;

/*
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelPlayer - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 *
public class IronManArmorModel_NOTUSING extends ModelBiped {
    public ModelRenderer LeftArm2;
    public ModelRenderer RightLeg2;
    public ModelRenderer RightArm2;
    public ModelRenderer Head2;
    public ModelRenderer LeftLeg2;
    public ModelRenderer RightArm1;
    public ModelRenderer RightLeg1;
    public ModelRenderer Head1;
    public ModelRenderer Body1;
    public ModelRenderer LeftArm1;
    public ModelRenderer LeftLeg1;
    public ModelRenderer Body2;

    public IronManArmorModel_NOTUSING(float f) {
        super(f, 0, 64, 64);
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Body2 = new ModelRenderer(this, 16, 32);
        this.Body2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body2.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.25F);
        this.bipedBody.addChild(Body2);
        this.LeftArm2 = new ModelRenderer(this, 48, 48);
        this.LeftArm2.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.LeftArm2.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.25F);
        this.bipedLeftArm.addChild(LeftArm2);
        this.Head2 = new ModelRenderer(this, 32, 0);
        this.Head2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Head2.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
        this.bipedHead.addChild(Head2);
        this.Body1 = new ModelRenderer(this, 16, 16);
        this.Body1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body1.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.bipedBody.addChild(Body1);
        this.LeftArm1 = new ModelRenderer(this, 32, 48);
        this.LeftArm1.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.LeftArm1.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.bipedLeftArm.addChild(LeftArm1);
        this.RightLeg2 = new ModelRenderer(this, 0, 32);
        this.RightLeg2.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.RightLeg2.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
        this.bipedRightLeg.addChild(RightLeg2);
        this.LeftLeg1 = new ModelRenderer(this, 16, 48);
        this.LeftLeg1.setRotationPoint(1.9F, 12.0F, 0.0F);
        this.LeftLeg1.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.bipedLeftLeg.addChild(LeftLeg1);
        this.RightLeg1 = new ModelRenderer(this, 0, 16);
        this.RightLeg1.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.RightLeg1.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.bipedRightLeg.addChild(RightLeg1);
        this.LeftLeg2 = new ModelRenderer(this, 0, 48);
        this.LeftLeg2.setRotationPoint(1.9F, 12.0F, 0.0F);
        this.LeftLeg2.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
        this.bipedLeftLeg.addChild(LeftLeg2);
        this.RightArm2 = new ModelRenderer(this, 40, 32);
        this.RightArm2.setRotationPoint(-5.0F, 2.0F, 10.0F);
        this.RightArm2.addBox(-3.0F, -2.0F, -12.0F, 4, 12, 4, 0.25F);
        this.bipedRightArm.addChild(RightArm2);
        this.RightArm1 = new ModelRenderer(this, 40, 16);
        this.RightArm1.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.RightArm1.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.bipedRightArm.addChild(RightArm1);
        this.Head1 = new ModelRenderer(this, 0, 0);
        this.Head1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Head1.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.bipedHead.addChild(Head1);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.Body2.render(f5);
        this.LeftArm2.render(f5);
        this.Head2.render(f5);
        this.Body1.render(f5);
        this.LeftArm1.render(f5);
        this.RightLeg2.render(f5);
        this.LeftLeg1.render(f5);
        this.RightLeg1.render(f5);
        this.LeftLeg2.render(f5);
        this.RightArm2.render(f5);
        this.RightArm1.render(f5);
        this.Head1.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     *
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }
}*/
