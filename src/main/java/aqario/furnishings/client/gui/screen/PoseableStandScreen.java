package aqario.furnishings.client.gui.screen;

import aqario.furnishings.client.gui.widget.ScrollbarWidget;
import aqario.furnishings.common.entity.PoseableStandEntity;
import aqario.furnishings.common.screen.PoseableStandScreenHandler;
import com.mojang.blaze3d.lighting.DiffuseLighting;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.EulerAngle;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Quaternion;
import net.minecraft.util.math.Vec3f;

import java.util.function.BiConsumer;
import java.util.function.Function;

public abstract class PoseableStandScreen extends HandledScreen<PoseableStandScreenHandler> {
	private static StandPart selectedPart = StandPart.HEAD;
	private final PoseableStandEntity poseableStand;
	private float mouseX;
	private float mouseY;
	private ScrollbarWidget xScroll;
	private ScrollbarWidget yScroll;
	private ScrollbarWidget zScroll;

	public PoseableStandScreen(PoseableStandScreenHandler handler, PlayerInventory inventory, PoseableStandEntity poseableStand) {
		super(handler, inventory, poseableStand.getDisplayName());
		this.poseableStand = poseableStand;
		this.backgroundHeight = 185;
		this.playerInventoryTitleY = this.backgroundHeight - 94;
	}

//	@Override
//	protected void init() {
//		super.init();
//
//		this.addDrawable(this.xScroll = new ScrollbarWidget(this.x + 136, this.y + 20, 8, 65, Text.literal("X")) {
//			@Override
//			public double getScrollRate() {
//				return 0;
//			}
//		});
//
//		this.addDrawable(this.yScroll = new ScrollbarWidget(this.x + 147, this.y + 20, 8, 65, Text.literal("Y")) {
//			@Override
//			public double getScrollRate() {
//				return 0;
//			}
//		});
//
//		this.addDrawable(this.zScroll = new ScrollbarWidget(this.x + 158, this.y + 20, 8, 65, Text.literal("Z")) {
//			@Override
//			public double getScrollRate() {
//				return 0;
//			}
//		});
//		this.updateScrollbar();
//	}

	private void updateScrollbar() {
		EulerAngle rotations = selectedPart.getRotation(this.poseableStand);
		float rotationX = MathHelper.wrapDegrees(rotations.getPitch()) + 180;
		float rotationY = MathHelper.wrapDegrees(rotations.getYaw()) + 180;
		float rotationZ = MathHelper.wrapDegrees(rotations.getRoll()) + 180;
		if (selectedPart == StandPart.LEFT_ARM) {
			rotationZ = 360 - rotationZ;
		}
		this.xScroll.setScrollAmount(rotationX % 360 / 360.0F * this.xScroll.getMaxScrollAmount());
		this.yScroll.setScrollAmount(rotationY % 360 / 360.0F * this.yScroll.getMaxScrollAmount());
		this.zScroll.setScrollAmount(rotationZ % 360 / 360.0F * this.zScroll.getMaxScrollAmount());
	}

//	@Override
//	public boolean mouseClicked(double mouseX, double mouseY, int button) {
//		for (PoseableStandPart part : PoseableStandPart.values()) {
//			if (selectedPart != part && part.isHovered(mouseX - this.x, mouseY - this.y)) {
//				selectedPart = part;
//				this.updateScrollbar();
//			}
//		}
//		return super.mouseClicked(mouseX, mouseY, button);
//	}

//	@Override
//	public boolean mouseReleased(double mouseX, double mouseY, int button) {
//		return super.mouseReleased(mouseX, mouseY, button);
//	}
//
//	@Override
//	protected void drawForeground(MatrixStack matrices, int mouseX, int mouseY) {
//		this.textRenderer.draw(matrices, this.title, (float)this.titleX, (float)this.titleY, 4210752);
//	}

	@Override
	protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, this.getTexture());
		this.drawTexture(matrices, this.x, this.y, 0, 0, this.backgroundWidth, this.backgroundHeight);
//		this.drawTexture(matrices, this.x + 59, this.y + 20, 0, this.backgroundHeight + (this.handler.getSlot(0).hasStack() ? 0 : 16), 110, 16);
//		if ((this.handler.getSlot(0).hasStack() || this.handler.getSlot(1).hasStack()) && !this.handler.getSlot(2).hasStack()) {
//			this.drawTexture(matrices, this.x + 99, this.y + 45, this.backgroundWidth, 0, 28, 21);
//		}
		InventoryScreen.drawEntity(this.x + 51, this.y + 84, 20, (float)(this.x + 51) - this.mouseX, (float)(this.y + 75 - 50) - this.mouseY, this.poseableStand);
	}

	public static void drawPoseableStand(int x, int y, int size, float mouseX, float mouseY, LivingEntity entity) {
		float f = (float)Math.atan(mouseX / 40.0F);
		float g = (float)Math.atan(mouseY / 40.0F);
		MatrixStack matrixStack = RenderSystem.getModelViewStack();
		matrixStack.push();
		matrixStack.translate((double)x, (double)y, 1050.0);
		matrixStack.scale(1.0F, 1.0F, -1.0F);
		RenderSystem.applyModelViewMatrix();
		MatrixStack matrixStack2 = new MatrixStack();
		matrixStack2.translate(0.0, 0.0, 1000.0);
		matrixStack2.scale((float)size, (float)size, (float)size);
		Quaternion quaternion = Vec3f.POSITIVE_Z.getDegreesQuaternion(180.0F);
		Quaternion quaternion2 = Vec3f.POSITIVE_X.getDegreesQuaternion(g * 20.0F);
		quaternion.hamiltonProduct(quaternion2);
		matrixStack2.multiply(quaternion);
		float h = entity.bodyYaw;
		float i = entity.getYaw();
		float j = entity.getPitch();
		float k = entity.prevHeadYaw;
		float l = entity.headYaw;
		entity.bodyYaw = 180.0F + f * 20.0F;
		entity.setYaw(180.0F + f * 40.0F);
		entity.setPitch(-g * 20.0F);
		entity.headYaw = entity.getYaw();
		entity.prevHeadYaw = entity.getYaw();
		DiffuseLighting.setupInventoryEntityLighting();
		EntityRenderDispatcher entityRenderDispatcher = MinecraftClient.getInstance().getEntityRenderDispatcher();
		quaternion2.conjugate();
		entityRenderDispatcher.setRotation(quaternion2);
		entityRenderDispatcher.setRenderShadows(false);
		VertexConsumerProvider.Immediate immediate = MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers();
		RenderSystem.runAsFancy(() -> entityRenderDispatcher.render(entity, 0.0, 0.0, 0.0, 0.0F, 1.0F, matrixStack2, immediate, 15728880));
		immediate.draw();
		entityRenderDispatcher.setRenderShadows(true);
		entity.bodyYaw = h;
		entity.setYaw(i);
		entity.setPitch(j);
		entity.prevHeadYaw = k;
		entity.headYaw = l;
		matrixStack.pop();
		RenderSystem.applyModelViewMatrix();
		DiffuseLighting.setup3DGuiLighting();
	}

	@Override
	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
		this.renderBackground(matrices);
		super.render(matrices, mouseX, mouseY, delta);
		RenderSystem.disableBlend();
		this.drawMouseoverTooltip(matrices, mouseX, mouseY);

//		EulerAngle rotations = selectedPart.getRotation(this.poseableStand);
//		float x = this.xScroll.getInterpolatedScrollPercentage(delta) * 360.0F - 180.0F;
//		float y = this.yScroll.getInterpolatedScrollPercentage(delta) * 360.0F - 180.0F;
//		float z = this.zScroll.getInterpolatedScrollPercentage(delta) * 360.0F - 180.0F;
//		if (selectedPart == PoseableStandPart.LEFT_ARM)
//			z *= -1;
//		if (rotations.getPitch() != x || rotations.getRoll() != y || rotations.getYaw() != z)
//			selectedPart.setRotation(this.poseableStand, new EulerAngle(x, y, z));
		this.mouseX = (float)mouseX;
		this.mouseY = (float)mouseY;
	}

	@Override
	public void closeScreen() {
		handler.setPose(StandPart.HEAD.getRotation(this.poseableStand), StandPart.BODY.getRotation(this.poseableStand), StandPart.LEFT_ARM.getRotation(this.poseableStand), StandPart.RIGHT_ARM.getRotation(this.poseableStand));
		super.closeScreen();
	}

	public abstract Identifier getTexture();

	enum StandPart {
		HEAD(PoseableStandEntity::setHeadRotation, PoseableStandEntity::getHeadRotation, 98, 21, 176, 59, 16, 16),
		BODY(PoseableStandEntity::setBodyRotation, PoseableStandEntity::getBodyRotation, 98, 37, 176, 39, 16, 20),
		LEFT_ARM(PoseableStandEntity::setLeftArmRotation, PoseableStandEntity::getLeftArmRotation, 114, 37, 176, 15, 8, 24),
		RIGHT_ARM(PoseableStandEntity::setRightArmRotation, PoseableStandEntity::getRightArmRotation, 90, 37, 176, 15, 8, 24);

		private final BiConsumer<PoseableStandEntity, EulerAngle> rotationSetter;
		private final Function<PoseableStandEntity, EulerAngle> rotationGetter;
		private final int xOffset;
		private final int yOffset;
		private final int buttonU;
		private final int buttonV;
		private final int buttonWidth;
		private final int buttonHeight;

		StandPart(BiConsumer<PoseableStandEntity, EulerAngle> rotationSetter, Function<PoseableStandEntity, EulerAngle> rotationGetter, int xOffset, int yOffset, int buttonU, int buttonV, int buttonWidth, int buttonHeight) {
			this.rotationSetter = rotationSetter;
			this.rotationGetter = rotationGetter;
			this.xOffset = xOffset;
			this.yOffset = yOffset;
			this.buttonU = buttonU;
			this.buttonV = buttonV;
			this.buttonWidth = buttonWidth;
			this.buttonHeight = buttonHeight;
		}

		public boolean isHovered(double mouseX, double mouseY) {
			return mouseX >= this.xOffset && mouseX < this.xOffset + this.buttonWidth && mouseY >= this.yOffset && mouseY < this.yOffset + this.buttonHeight;
		}

		public EulerAngle getRotation(PoseableStandEntity poseableStand) {
			return this.rotationGetter.apply(poseableStand);
		}

		public void setRotation(PoseableStandEntity poseableStand, EulerAngle rot) {
			this.rotationSetter.accept(poseableStand, rot);
		}
	}
}
