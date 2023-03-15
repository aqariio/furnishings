package aqario.furnishings.client.model;

import aqario.furnishings.common.entity.StatueEntity;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.util.math.MatrixStack;

public class StatueEntityModel extends BipedEntityModel<StatueEntity> {
	private final ModelPart base;

	public StatueEntityModel(ModelPart root) {
		super(root);
		this.base = root.getChild("base");
		this.hat.visible = false;
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = BipedEntityModel.getModelData(Dilation.NONE, 0.0F);
		ModelPartData modelPartData = modelData.getRoot();
		modelPartData.addChild(
				EntityModelPartNames.HAT,
				ModelPartBuilder.create().uv(32, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.5F)),
				ModelTransform.pivot(0.0F, 1.0F, 0.0F)
		);

		ModelPartData body = modelPartData.addChild(
				"body", ModelPartBuilder.create().uv(16, 16).cuboid(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 10.0F, 0.0F));

		ModelPartData head = body.addChild(
				"head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -12.0F, 0.0F));

		ModelPartData leftArm = body.addChild(
				"leftArm", ModelPartBuilder.create().uv(40, 16).cuboid(0.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, -10.0F, 0.0F));

		ModelPartData rightArm = body.addChild(
				"rightArm", ModelPartBuilder.create().uv(40, 16).mirrored().cuboid(-4.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(-4.0F, -10.0F, 0.0F));

		ModelPartData base = modelPartData.addChild(
				"base", ModelPartBuilder.create().uv(0, 32).cuboid(-6.0F, -1.0F, -6.0F, 12.0F, 2.0F, 12.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 23.0F, 0.0F));

		ModelPartData leftLeg = base.addChild(
				"leftLeg", ModelPartBuilder.create().uv(0, 16).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, -13.0F, 0.0F));

		ModelPartData rightLeg = base.addChild(
				"rightLeg", ModelPartBuilder.create().uv(0, 16).mirrored().cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(-2.0F, -13.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	protected Iterable<ModelPart> getBodyParts() {
		return Iterables.concat(super.getBodyParts(), ImmutableList.of(this.base));
	}

	@Override
	public void setAngles(StatueEntity statueEntity, float f, float g, float h, float i, float j) {
		this.body.getChild("head").pitch = (float) (Math.PI / 180.0) * statueEntity.getHeadRotation().getPitch();
		this.body.getChild("head").yaw = (float) (Math.PI / 180.0) * statueEntity.getHeadRotation().getYaw();
		this.body.getChild("head").roll = (float) (Math.PI / 180.0) * statueEntity.getHeadRotation().getRoll();
		this.body.pitch = (float) (Math.PI / 180.0) * statueEntity.getBodyRotation().getPitch();
		this.body.yaw = (float) (Math.PI / 180.0) * statueEntity.getBodyRotation().getYaw();
		this.body.roll = (float) (Math.PI / 180.0) * statueEntity.getBodyRotation().getRoll();
		this.body.getChild("leftArm").pitch = (float) (Math.PI / 180.0) * statueEntity.getLeftArmRotation().getPitch();
		this.body.getChild("leftArm").yaw = (float) (Math.PI / 180.0) * statueEntity.getLeftArmRotation().getYaw();
		this.body.getChild("leftArm").roll = (float) (Math.PI / 180.0) * statueEntity.getLeftArmRotation().getRoll();
		this.body.getChild("rightArm").pitch = (float) (Math.PI / 180.0) * statueEntity.getRightArmRotation().getPitch();
		this.body.getChild("rightArm").yaw = (float) (Math.PI / 180.0) * statueEntity.getRightArmRotation().getYaw();
		this.body.getChild("rightArm").roll = (float) (Math.PI / 180.0) * statueEntity.getRightArmRotation().getRoll();
		this.base.getChild("leftLeg").pitch = (float) (Math.PI / 180.0) * statueEntity.getLeftLegRotation().getPitch();
		this.base.getChild("leftLeg").yaw = (float) (Math.PI / 180.0) * statueEntity.getLeftLegRotation().getYaw();
		this.base.getChild("leftLeg").roll = (float) (Math.PI / 180.0) * statueEntity.getLeftLegRotation().getRoll();
		this.base.getChild("rightLeg").pitch = (float) (Math.PI / 180.0) * statueEntity.getRightLegRotation().getPitch();
		this.base.getChild("rightLeg").yaw = (float) (Math.PI / 180.0) * statueEntity.getRightLegRotation().getYaw();
		this.base.getChild("rightLeg").roll = (float) (Math.PI / 180.0) * statueEntity.getRightLegRotation().getRoll();
		this.hat.copyTransform(this.head);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		body.render(matrices, vertices, light, overlay, red, green, blue, alpha);
		base.render(matrices, vertices, light, overlay, red, green, blue, alpha);
		hat.render(matrices, vertices, light, overlay, red, green, blue, alpha);
	}
}
