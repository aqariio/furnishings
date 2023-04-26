package aqario.furnishings.client.model;

import aqario.furnishings.common.entity.StatueEntity;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.util.math.MathHelper;
import org.quiltmc.loader.api.minecraft.ClientOnly;

@ClientOnly
public class StatueEntityModel extends StatueArmorEntityModel {
	private static final String BASE_PLATE = "base_plate";
	private final ModelPart basePlate;

	public StatueEntityModel(ModelPart root) {
		super(root);
		this.basePlate = root.getChild(BASE_PLATE);
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
		modelPartData.addChild(
			EntityModelPartNames.BODY,
			ModelPartBuilder.create().uv(16, 16).cuboid(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F)),
			ModelTransform.pivot(0.0F, 10.0F, 0.0F)
		);
		modelPartData.addChild(
			EntityModelPartNames.HEAD,
			ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)),
			ModelTransform.pivot(0.0F, -2.0F, 0.0F)
		);
		modelPartData.addChild(
			EntityModelPartNames.LEFT_ARM,
			ModelPartBuilder.create().uv(40, 16).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)),
			ModelTransform.pivot(5.0F, 0.0F, 0.0F)
		);
		modelPartData.addChild(
			EntityModelPartNames.RIGHT_ARM,
			ModelPartBuilder.create().uv(40, 16).mirrored().cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)).mirrored(false),
			ModelTransform.pivot(-5.0F, 0.0F, 0.0F)
		);
		modelPartData.addChild(
			BASE_PLATE,
			ModelPartBuilder.create().uv(0, 32).cuboid(-6.0F, -1.0F, -6.0F, 12.0F, 2.0F, 12.0F, new Dilation(0.0F)),
			ModelTransform.pivot(0.0F, 23.0F, 0.0F)
		);
		modelPartData.addChild(
			EntityModelPartNames.LEFT_LEG,
			ModelPartBuilder.create().uv(0, 16).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)),
			ModelTransform.pivot(2.0F, 10.0F, 0.0F)
		);
		modelPartData.addChild(
			EntityModelPartNames.RIGHT_LEG,
			ModelPartBuilder.create().uv(0, 16).mirrored().cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)).mirrored(false),
			ModelTransform.pivot(-2.0F, 10.0F, 0.0F)
		);
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	protected Iterable<ModelPart> getBodyParts() {
		return Iterables.concat(super.getBodyParts(), ImmutableList.of(this.basePlate));
	}

	@Override
	public void animateModel(StatueEntity statueEntity, float f, float g, float h) {
		this.basePlate.pitch = 0.0F;
		this.basePlate.yaw = (float) (Math.PI / 180.0) * -MathHelper.lerpAngleDegrees(h, statueEntity.prevYaw, statueEntity.getYaw());
		this.basePlate.roll = 0.0F;
	}

	@Override
	public void setAngles(StatueEntity statueEntity, float f, float g, float h, float i, float j) {
		super.setAngles(statueEntity, f, g, h, i, j);
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

//	@Override
//	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
//		body.render(matrices, vertices, light, overlay, red, green, blue, alpha);
//		leftLeg.render(matrices, vertices, light, overlay, red, green, blue, alpha);
//		rightLeg.render(matrices, vertices, light, overlay, red, green, blue, alpha);
//		basePlate.render(matrices, vertices, light, overlay, red, green, blue, alpha);
//		hat.render(matrices, vertices, light, overlay, red, green, blue, alpha);
//	}
}
