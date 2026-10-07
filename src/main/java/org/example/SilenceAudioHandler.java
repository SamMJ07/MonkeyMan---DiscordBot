package org.example;
import net.dv8tion.jda.api.audio.AudioSendHandler;
import java.nio.ByteBuffer;

public class SilenceAudioHandler implements AudioSendHandler {
    private final byte[] silence = new byte[]{(byte)0xF8, (byte)0xFF, (byte)0xFE};

    @Override
    public boolean canProvide() {
        return true;
    }

    @Override
    public ByteBuffer provide20MsAudio() {
        return ByteBuffer.wrap(silence);
    }

    @Override
    public boolean isOpus() {
        return true;
    }
}