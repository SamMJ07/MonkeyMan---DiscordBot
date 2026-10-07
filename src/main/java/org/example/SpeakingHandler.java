package org.example;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.audio.AudioReceiveHandler;
import net.dv8tion.jda.api.audio.UserAudio;

public class SpeakingHandler implements AudioReceiveHandler {
    private final Main main;


    public SpeakingHandler(Main main, SoundLoader soundMaker) {
        this.main = main;

    }

    @Override
    public boolean canReceiveUser() {
        return true;
    }


    @Override
    public void handleUserAudio(UserAudio userAudio) {

        if (userAudio.getUser().getName().equals(main.personMonkeyed)) {

            if (!main.monkeySpeaking) {
                main.monkeySpeaking = true;
                int random = (int) (1 + Math.random() * 2);
                main.musicManager.player.addListener(new TrackEndListener(main));
                if (random == 1) {
                    main.musicManager.player.playTrack(main.sound1.makeClone());
                    final long duration1= main.sound1.getDuration() - 700; // stop 200ms early
                    new Thread(() -> {
                        try {
                            Thread.sleep(duration1);
                            main.musicManager.player.stopTrack();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }//catch
                    }).start();
                }//if

                if (random == 2) {
                    main.musicManager.player.playTrack(main.sound3.makeClone());//using sound3
                    final long duration2= main.sound3.getDuration() - 700; // stop 200ms early
                    new Thread(() -> {
                        try {
                            Thread.sleep(duration2);
                            main.musicManager.player.stopTrack();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }//catch
                    }).start();

                }//if
                if (random == 3) {
                    main.musicManager.player.playTrack(main.sound3.makeClone());
                    final long duration3 = main.sound3.getDuration() - 800; // stop 200ms early
                    new Thread(() -> {
                        try {
                            Thread.sleep(duration3);
                            main.musicManager.player.stopTrack();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }//catch
                    }).start();
                }//if
            }//if
        }//if
    }//handleUser
}