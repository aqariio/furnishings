package aqario.furnishings.client.model;

import aqario.furnishings.common.entity.ScarecrowEntity;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;

public class ScarecrowEntityModel extends ScarecrowArmorEntityModel {
    private static final String STAND = "stand";
    private final ModelPart stand;

    public ScarecrowEntityModel(ModelPart root) {
        super(root);
        this.stand = root.getChild(STAND);
        this.hat.visible = false;
        this.leftLeg.visible = false;
        this.rightLeg.visible = false;
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = BipedEntityModel.getModelData(Dilation.NONE, 0.0F);
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild(
            EntityModelPartNames.HAT,
            ModelPartBuilder.create().uv(32, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.5F)),
            ModelTransform.pivot(0.0F, 1.0F, 0.0F)
        );
        modelPartData.addChild(
            EntityModelPartNames.BODY,
            ModelPartBuilder.create().uv(8, 16).cuboid(-4.0F, -12.0F, -2.0F, 8.0F, 10.0F, 4.0F, new Dilation(0.0F)),
            ModelTransform.pivot(0.0F, 10.0F, 0.0F)
        );
        modelPartData.addChild(
            EntityModelPartNames.HEAD,
            ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)),
            ModelTransform.pivot(0.0F, -2.0F, 0.0F)
        );
        modelPartData.addChild(
            EntityModelPartNames.LEFT_ARM,
            ModelPartBuilder.create().uv(32, 16).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)),
            ModelTransform.pivot(5.0F, 0.0F, 0.0F)
        );
        modelPartData.addChild(
            EntityModelPartNames.RIGHT_ARM,
            ModelPartBuilder.create().uv(32, 16).mirrored().cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)).mirrored(false),
            ModelTransform.pivot(-5.0F, 0.0F, 0.0F)
        );
        modelPartData.addChild(
            STAND,
            ModelPartBuilder.create().uv(0, 16).cuboid(-3.0F, -2.0F, -1.0F, 2.0F, 16.0F, 2.0F, new Dilation(0.0F)),
            ModelTransform.pivot(2.0F, 10.0F, 0.0F)
        );
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    protected Iterable<ModelPart> getBodyParts() {
        return Iterables.concat(super.getBodyParts(), ImmutableList.of(this.stand));
    }

    @Override
    public void setAngles(ScarecrowEntity scarecrowEntity, float f, float g, float h, float i, float j) {
        this.head.pitch = (float) (Math.PI / 180.0) * scarecrowEntity.getHeadRotation().getPitch();
        this.head.yaw = (float) (Math.PI / 180.0) * scarecrowEntity.getHeadRotation().getYaw();
        this.head.roll = (float) (Math.PI / 180.0) * scarecrowEntity.getHeadRotation().getRoll();
        this.body.pitch = (float) (Math.PI / 180.0) * scarecrowEntity.getBodyRotation().getPitch();
        this.body.yaw = (float) (Math.PI / 180.0) * scarecrowEntity.getBodyRotation().getYaw();
        this.body.roll = (float) (Math.PI / 180.0) * scarecrowEntity.getBodyRotation().getRoll();
        this.leftArm.pitch = (float) (Math.PI / 180.0) * scarecrowEntity.getLeftArmRotation().getPitch();
        this.leftArm.yaw = (float) (Math.PI / 180.0) * scarecrowEntity.getLeftArmRotation().getYaw();
        this.leftArm.roll = (float) (Math.PI / 180.0) * scarecrowEntity.getLeftArmRotation().getRoll();
        this.rightArm.pitch = (float) (Math.PI / 180.0) * scarecrowEntity.getRightArmRotation().getPitch();
        this.rightArm.yaw = (float) (Math.PI / 180.0) * scarecrowEntity.getRightArmRotation().getYaw();
        this.rightArm.roll = (float) (Math.PI / 180.0) * scarecrowEntity.getRightArmRotation().getRoll();
        this.hat.copyTransform(this.head);
    }

//    @Override
//    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
//        body.render(matrices, vertices, light, overlay, red, green, blue, alpha);
//        stand.render(matrices, vertices, light, overlay, red, green, blue, alpha);
//        hat.render(matrices, vertices, light, overlay, red, green, blue, alpha);
//    }
}
