package com.waltenfiles.client;

import com.waltenfiles.lore.TapeLore;
import java.util.List;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.RandomSource;
import org.lwjgl.glfw.GLFW;

/** Tela de TV antiga com chiado, linhas de varredura e texto digitado. */
public class TapeScreen extends Screen {
    private final String[] pages;
    private int page = 0;
    private int age = 0;
    private final RandomSource noise = RandomSource.create();

    public TapeScreen(int tapeId) {
        super(Component.literal(TapeLore.title(tapeId)));
        this.pages = TapeLore.pages(tapeId);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void tick() {
        age++;
    }

    private void next() {
        if (page < pages.length - 1) {
            page++;
            age = 0;
        } else {
            this.onClose();
        }
    }

    private void previous() {
        if (page > 0) {
            page--;
            age = 0;
        }
    }

    @Override
    public boolean keyPressed(int key, int scanCode, int modifiers) {
        if (key == GLFW.GLFW_KEY_RIGHT || key == GLFW.GLFW_KEY_SPACE || key == GLFW.GLFW_KEY_ENTER) {
            next();
            return true;
        }
        if (key == GLFW.GLFW_KEY_LEFT) {
            previous();
            return true;
        }
        return super.keyPressed(key, scanCode, modifiers);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        next();
        return true;
    }

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float partialTick) {
        g.fill(0, 0, this.width, this.height, 0xFF010201);
        int pad = Math.max(16, this.width / 12);
        int x0 = pad;
        int y0 = pad;
        int x1 = this.width - pad;
        int y1 = this.height - pad;
        g.fill(x0, y0, x1, y1, 0xFF0B1A10);

        // indicador REC piscando
        boolean blink = (age / 10) % 2 == 0;
        g.fill(x0 + 12, y0 + 10, x0 + 18, y0 + 16, blink ? 0xFFFF3B3B : 0xFF6B1F1F);
        g.drawString(this.font, "REC", x0 + 24, y0 + 9, 0xFFFF6B6B, false);
        g.drawCenteredString(this.font, this.title, this.width / 2, y0 + 9, 0xFF9CFFB0);

        // texto "digitado"
        String full = pages[page];
        int shown = Math.min(full.length(), age * 2);
        List<FormattedCharSequence> lines = this.font.split(Component.literal(full.substring(0, shown)), x1 - x0 - 40);
        int y = y0 + 40;
        for (FormattedCharSequence line : lines) {
            g.drawString(this.font, line, x0 + 20, y, 0xFF9CFFB0, false);
            y += 12;
        }

        String hint = "PAG " + (page + 1) + "/" + pages.length + "   clique ou ESPACO: avancar   ESC: sair";
        g.drawString(this.font, hint, x0 + 12, y1 - 16, 0xFF4F8F62, false);

        // linhas de varredura
        for (int sy = y0; sy < y1; sy += 3) {
            g.fill(x0, sy, x1, sy + 1, 0x33000000);
        }
        // chiado (forte nos primeiros ticks, depois leve)
        int count = age < 12 ? 500 : 30;
        int w = Math.max(1, x1 - x0);
        int h = Math.max(1, y1 - y0);
        for (int i = 0; i < count; i++) {
            int nx = x0 + noise.nextInt(w);
            int ny = y0 + noise.nextInt(h);
            int alpha = 30 + noise.nextInt(60);
            g.fill(nx, ny, nx + 1 + noise.nextInt(3), ny + 1, (alpha << 24) | 0x00CFEFD8);
        }
        if (noise.nextInt(40) == 0) {
            int ty = y0 + noise.nextInt(h);
            g.fill(x0, ty, x1, ty + 2, 0x22FFFFFF);
        }
    }
}
