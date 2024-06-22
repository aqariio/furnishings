//package aqario.furnishings.client.gui.widget;
//
//import aqario.furnishings.common.Furnishings;
//import com.mojang.blaze3d.systems.RenderSystem;
//import net.minecraft.client.MinecraftClient;
//import net.minecraft.client.gui.Drawable;
//import net.minecraft.client.gui.Element;
//import net.minecraft.client.gui.GuiGraphics;
//import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
//import net.minecraft.client.gui.widget.ClickableWidget;
//import net.minecraft.client.render.GameRenderer;
//import net.minecraft.client.util.math.MatrixStack;
//import net.minecraft.text.Text;
//import net.minecraft.util.Identifier;
//import net.minecraft.util.math.MathHelper;
//
//public abstract class ScrollbarWidget extends ClickableWidget implements Drawable, Element {
//    public static final Identifier SCROLLBAR_TEXTURE = new Identifier(Furnishings.ID, "textures/gui/scrollbar.png");
//    public static final int SCROLLBAR_HEIGHT = 15;
//    private double scrollAmount;
//
//    public ScrollbarWidget(int x, int y, int width, int height, Text message) {
//        super(x, y, width, height, message);
//    }
//
//    @Override
//    public void drawWidget(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
//        MinecraftClient minecraftClient = MinecraftClient.getInstance();
//        RenderSystem.setShader(GameRenderer::getPositionTexShader);
//        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);
//        RenderSystem.setShaderTexture(0, SCROLLBAR_TEXTURE);
//        int i = this.getYImage(this.isHoveredOrFocused());
//        RenderSystem.enableBlend();
//        RenderSystem.defaultBlendFunc();
//        RenderSystem.enableDepthTest();
//        this.drawTexture(matrices, this.x, this.y, 0, 46 + i * 20, this.width / 2, this.height);
//        this.drawTexture(matrices, this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);
//        this.renderBackground(matrices, minecraftClient, mouseX, mouseY);
//    }
//
//    /** may be problematic */
//
//    @Override
//    public boolean mouseClicked(double mouseX, double mouseY, int button) {
//        boolean bl = this.isWithinBounds(mouseX, mouseY)
//                && mouseX >= (double)(this.x + this.width)
//                && mouseX <= (double)(this.x + this.width + 8)
//                && mouseY >= (double)this.y
//                && mouseY < (double)(this.y + this.height);
//        this.setFocused(bl);
//        return bl;
//    }
//
//    @Override
//    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
//        if (this.visible && this.isFocused()) {
//            this.setScrollAmount(this.scrollAmount - amount * this.getScrollRate());
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    @Override
//    protected void onDrag(double mouseX, double mouseY, double deltaX, double deltaY) {
//        if (mouseY < (double)this.y) {
//            this.setScrollAmount(0.0);
//        } else if (mouseY > (double)(this.y + this.height)) {
//            this.setScrollAmount(this.getMaxScrollAmount());
//        } else {
//            double d = Math.max(1, this.getMaxScrollAmount() / (this.height - SCROLLBAR_HEIGHT));
//            this.setScrollAmount(this.scrollAmount + deltaY * d);
//        }
//    }
//
//    @Override
//    public void appendNarrations(NarrationMessageBuilder builder) {
//    }
//
//    public abstract double getScrollRate();
//
//    protected double getScrollAmount() {
//        return this.scrollAmount;
//    }
//
//    public void setScrollAmount(double scrollAmount) {
//        this.scrollAmount = MathHelper.clamp(scrollAmount, 0.0, this.getMaxScrollAmount());
//    }
//
//    public int getMaxScrollAmount() {
//        return Math.max(0, this.height);
//    }
//
//    public void setMaxScrollAmount(int maxScroll) {
//        this.height = maxScroll;
//    }
//
//    public float getInterpolatedScrollPercentage(float partialTicks) {
//        return this.getMaxScrollAmount() > 0 ? (this.getInterpolatedScroll(partialTicks) / this.getMaxScrollAmount()) : 0;
//    }
//
//    public float getInterpolatedScroll(float partialTicks) {
//        return (float) MathHelper.lerp(partialTicks, this.scrollAmount, this.scrollAmount);
//    }
//
//    protected boolean isWithinBounds(double x, double y) {
//        return x >= (double)this.x && x < (double)(this.x + this.width) && y >= (double)this.y && y < (double)(this.y + this.height);
//    }
//}
