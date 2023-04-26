package aqario.furnishings.client.model;

import aqario.furnishings.common.entity.StatueEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import org.quiltmc.loader.api.minecraft.ClientOnly;

@ClientOnly
public class StatueArmorEntityModel extends BipedEntityModel<StatueEntity> {
	public StatueArmorEntityModel(ModelPart root) {
		super(root);
	}

	public static TexturedModelData getTexturedModelData(Dilation dilation) {
		ModelData modelData = BipedEntityModel.getModelData(dilation, 0.0F);
		ModelPartData modelPartData = modelData.getRoot();
		modelPartData.addChild(
			EntityModelPartNames.HAT,
			ModelPartBuilder.create().uv(32, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, dilation.add(0.5F)),
			ModelTransform.pivot(0.0F, 1.0F, 0.0F)
		);
		ModelPartData body = modelPartData.addChild(
			EntityModelPartNames.BODY,
			ModelPartBuilder.create().uv(16, 16).cuboid(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F, dilation.add(0.1F)),
			ModelTransform.pivot(0.0F, 10.0F, 0.0F)
		);
		modelPartData.addChild(
			EntityModelPartNames.HEAD,
			ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, dilation),
			ModelTransform.pivot(0.0F, 1.0F, 0.0F)
		);
		modelPartData.addChild(
			EntityModelPartNames.LEFT_LEG,
			ModelPartBuilder.create().uv(0, 16).mirrored().cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, dilation),
			ModelTransform.pivot(1.9F, 11.0F, 0.0F)
		);
		modelPartData.addChild(
			EntityModelPartNames.RIGHT_LEG,
			ModelPartBuilder.create().uv(0, 16).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, dilation),
			ModelTransform.pivot(-1.9F, 11.0F, 0.0F)
		);
		return TexturedModelData.of(modelData, 64, 32);
	}

	@Override
	public void setAngles(StatueEntity statueEntity, float f, float g, float h, float i, float j) {
		this.head.pitch = (float) (Math.PI / 180.0) * statueEntity.getHeadRotation().getPitch();
		this.head.yaw = (float) (Math.PI / 180.0) * statueEntity.getHeadRotation().getYaw();
		this.head.roll = (float) (Math.PI / 180.0) * statueEntity.getHeadRotation().getRoll();
		this.body.pitch = (float) (Math.PI / 180.0) * statueEntity.getBodyRotation().getPitch();
		this.body.yaw = (float) (Math.PI / 180.0) * statueEntity.getBodyRotation().getYaw();
		this.body.roll = (float) (Math.PI / 180.0) * statueEntity.getBodyRotation().getRoll();
		this.leftArm.pitch = (float) (Math.PI / 180.0) * statueEntity.getLeftArmRotation().getPitch();
		this.leftArm.yaw = (float) (Math.PI / 180.0) * statueEntity.getLeftArmRotation().getYaw();
		this.leftArm.roll = (float) (Math.PI / 180.0) * statueEntity.getLeftArmRotation().getRoll();
		this.rightArm.pitch = (float) (Math.PI / 180.0) * statueEntity.getRightArmRotation().getPitch();
		this.rightArm.yaw = (float) (Math.PI / 180.0) * statueEntity.getRightArmRotation().getYaw();
		this.rightArm.roll = (float) (Math.PI / 180.0) * statueEntity.getRightArmRotation().getRoll();
		this.leftLeg.pitch = (float) (Math.PI / 180.0) * statueEntity.getLeftLegRotation().getPitch();
		this.leftLeg.yaw = (float) (Math.PI / 180.0) * statueEntity.getLeftLegRotation().getYaw();
		this.leftLeg.roll = (float) (Math.PI / 180.0) * statueEntity.getLeftLegRotation().getRoll();
		this.rightLeg.pitch = (float) (Math.PI / 180.0) * statueEntity.getRightLegRotation().getPitch();
		this.rightLeg.yaw = (float) (Math.PI / 180.0) * statueEntity.getRightLegRotation().getYaw();
		this.rightLeg.roll = (float) (Math.PI / 180.0) * statueEntity.getRightLegRotation().getRoll();
		this.hat.copyTransform(this.head);
	}
}
