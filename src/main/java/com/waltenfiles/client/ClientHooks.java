package com.waltenfiles.client;

import net.minecraft.client.Minecraft;

/** Classe so carregada no cliente (chamada via DistExecutor). */
public final class ClientHooks {
    private ClientHooks() {}

    public static void openTape(int tapeId) {
        Minecraft.getInstance().setScreen(new TapeScreen(tapeId));
    }
}
