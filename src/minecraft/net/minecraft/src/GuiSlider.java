package net.minecraft.src;

import com.oldschoolminecraft.client.Client;
import com.oldschoolminecraft.client.util.RenderUtils;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

public class GuiSlider extends GuiButton {
    public float sliderValue = 1.0F;
    public boolean dragging = false;
    private EnumOptions idFloat = null;

    public GuiSlider(int var1, int var2, int var3, EnumOptions var4, String var5, float var6) {
        super(var1, var2, var3, 150, 20, var5);
        this.idFloat = var4;
        this.sliderValue = var6;
    }

    protected int getHoverState(boolean var1) {
        return 0;
    }

    protected void mouseDragged(Minecraft var1, int var2, int var3) {
        if (this.enabled2) {
            if (this.dragging) {
                this.sliderValue = (float)(var2 - (this.xPosition + 4)) / (float)(this.width - 8);
                if (this.sliderValue < 0.0F) {
                    this.sliderValue = 0.0F;
                }

                if (this.sliderValue > 1.0F) {
                    this.sliderValue = 1.0F;
                }

                var1.gameSettings.setOptionFloatValue(this.idFloat, this.sliderValue);
                this.displayString = var1.gameSettings.getKeyBinding(this.idFloat);
            }

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            RenderUtils.drawRoundedRect(this.xPosition + (int)(this.sliderValue * (float)(this.width - 8)), this.yPosition, 5, 20, 5, Client.getMainColor(40));
            RenderUtils.drawRoundedOutline(this.xPosition + (int)(this.sliderValue * (this.width - 8)), this.yPosition, this.xPosition + (int)(this.sliderValue * (this.width - 8)) + 8, this.yPosition + 20, 2.0f, 2.0f, Client.getMainColor(255));
            RenderUtils.drawRoundedOutline(this.xPosition, this.yPosition, this.xPosition + (int)(this.sliderValue * (this.width - 8)) + 8, this.yPosition + 20, 2.0f, 2.0f, Client.getMainColor(255));
        }
    }

    public boolean mousePressed(Minecraft var1, int var2, int var3) {
        if (super.mousePressed(var1, var2, var3)) {
            this.sliderValue = (float)(var2 - (this.xPosition + 4)) / (float)(this.width - 8);
            if (this.sliderValue < 0.0F) {
                this.sliderValue = 0.0F;
            }

            if (this.sliderValue > 1.0F) {
                this.sliderValue = 1.0F;
            }

            var1.gameSettings.setOptionFloatValue(this.idFloat, this.sliderValue);
            this.displayString = var1.gameSettings.getKeyBinding(this.idFloat);
            this.dragging = true;
            return true;
        } else {
            return false;
        }
    }

    public void mouseReleased(int var1, int var2) {
        this.dragging = false;
    }
}
