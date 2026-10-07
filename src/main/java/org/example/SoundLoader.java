package org.example;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

public class SoundLoader implements AudioLoadResultHandler {
    private final GuildMusicManager musicManager;
    private final Main main;

    private final int soundIndex;

    public SoundLoader(GuildMusicManager musicManager, Main main, int soundIndex) {
        this.musicManager = musicManager;
        this.main = main;
        this.soundIndex = soundIndex;

    }

    @Override
    public void trackLoaded(AudioTrack track) {
        if (soundIndex == 1)
        {
            main.sound1 = track;
        }//if
        else if(soundIndex == 2){
            main.sound2 = track;
        }//else if
        else if (soundIndex == 3){
            main.sound3 = track;
        }//else if
        else {
            musicManager.player.addListener(new TrackEndListener(main));
            musicManager.player.playTrack(track);
        }
    }
    // rest of methods stay the same...


    @Override
    public void playlistLoaded(AudioPlaylist playlist) {}

    @Override
    public void noMatches() {
        System.out.println("No audio file found!");
    }

    @Override
    public void loadFailed(FriendlyException exception) {
        System.out.println("Failed to load: " + exception.getMessage());
    }
}