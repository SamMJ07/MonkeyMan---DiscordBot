package org.example;



import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;

import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;

import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.entities.GuildVoiceState;

import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.audio.AudioModuleConfig;




public class Main extends ListenerAdapter {

    public boolean timerGoing=false;
    public boolean monkeySpeaking=false;
    public boolean alwaysTalk=false;
    public boolean monkeyman;
    public boolean inVC=false;
    public AudioPlayerManager playerManager;
    public GuildMusicManager musicManager;

    public SoundLoader soundMaker;
    public String personEaten="noOne";

    public String personMonkeyed="noOne";

    public AudioTrack sound1;
    public AudioTrack sound2;
    public AudioTrack sound3;

    public static void main(String[] args) throws Exception {
        //Dave stuff
        moe.kyokobot.libdave.DaveFactory daveFactory = new moe.kyokobot.libdave.NativeDaveFactory();
        net.dv8tion.jda.api.audio.dave.DaveSessionFactory daveSessionFactory = new moe.kyokobot.libdave.jda.LDJDADaveSessionFactory(daveFactory);
        //Playing Audio stuff
        Main mainInstance = new Main();
        mainInstance.playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerLocalSource(mainInstance.playerManager);
        mainInstance.playerManager.loadItem("ENTER mp3 PATH HERE", new SoundLoader(mainInstance.musicManager, mainInstance,1 ));
        mainInstance.playerManager.loadItem("ENTER mp3 PATH HERE", new SoundLoader(mainInstance.musicManager, mainInstance,2 ));
        mainInstance.playerManager.loadItem("ENTER mp3 PATH HERE", new SoundLoader(mainInstance.musicManager, mainInstance, 3));

        //d
        JDA bot = JDABuilder.createDefault("ENTER TOKEN HERE")
                .enableIntents( GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.MESSAGE_CONTENT,
                        GatewayIntent.GUILD_VOICE_STATES)
                .setAudioModuleConfig(new AudioModuleConfig()
                        .withDaveSessionFactory(daveSessionFactory))
                .setActivity(Activity.playing("BANANA EATER"))
                .setAutoReconnect(false)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .build();
        bot.awaitReady();
        bot.addEventListener(mainInstance);
        try {
            Class.forName("tomp2p.opuswrapper.Opus");
            System.out.println("Opus loaded successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("Opus NOT loaded - this is the problem!");
        }

    }//main

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String Author = event.getAuthor().getName();
        Message message = event.getMessage();
        String messageSent = event.getMessage().getContentDisplay();
        if(!message.getAuthor().isBot()){
             eatSomeone(message,messageSent);
             spitThemOut(message,messageSent);
            joinVC(message,messageSent);
            shutup(message, messageSent);
            startTimer(message,messageSent);
            interuptSomeone(message,messageSent);
            stopinterupting(message, messageSent);
        }//if
        if(!message.getAuthor().isBot())
        {
            digestSomeone(Author,message,messageSent);
            playSound(message,messageSent);
        }//if

        if(!Author.equals(personEaten)&&!message.getAuthor().isBot())
        {
            eatSomeone(message,messageSent);
            spitThemOut(message,messageSent);
            videoTester(message,messageSent);
            questionTester(message,messageSent);

        }//if








        }//onMessageRecieve
        public void digestSomeone(String person, Message words, String messageString){
            if(person.equals(personEaten)){

                words.delete().queue();
                words.getChannel().sendMessage("Monkey says: "+messageString).queue();
            }//if
        }//eatSomeone
    public void eatSomeone(Message words, String messageString){
            int eatIndex=0;
            if(messageString.toLowerCase().contains("monkey man")&&messageString.toLowerCase().contains("eat")){

                if(personEaten.equals("noOne")) {

                    eatIndex=messageString.indexOf("eat");
                    personEaten= messageString.substring(eatIndex+4);
                    if(personEaten.contains(" "))
                    {
                        eatIndex=personEaten.indexOf(" ");
                    }//if
                    else{
                        eatIndex= personEaten.length();
                    }

                    personEaten=personEaten.substring(0,eatIndex);
                    words.getChannel().sendMessage("Num Num Num").queue();
                }
                else{
                    words.getChannel().sendMessage("My stomach is already full of " +personEaten).queue();
                }//else
            }//if

    }//eatSomeone
    public void spitThemOut(Message words, String messageString){
        if(messageString.toLowerCase().equals("spit them out")){
            personEaten="noOne";
            words.getChannel().sendMessage("Hawk Tuah").queue();
        }//if
    }//spitThemOut


    public void interuptSomeone(Message words, String messageString){
        int eatIndex=0;
        if(messageString.toLowerCase().contains("monkey man")&&messageString.toLowerCase().contains("bonk")){

            if(personMonkeyed.equals("noOne")) {

                eatIndex=messageString.indexOf("bonk");
                personMonkeyed= messageString.substring(eatIndex+5);
                if(personMonkeyed.contains(" "))
                {
                    eatIndex=personMonkeyed.indexOf(" ");
                }//if
                else{
                    eatIndex= personMonkeyed.length();
                }

                personMonkeyed=personMonkeyed.substring(0,eatIndex);
                words.getChannel().sendMessage("BONK").queue();
            }
            else{
                words.getChannel().sendMessage("I am already attacking " +personMonkeyed).queue();
            }//else
        }//if

    }//interuptSomeone;

    public void stopinterupting(Message words, String messageString){
        if(messageString.toLowerCase().equals("stop attacking")){
            personMonkeyed="noOne";
            words.getChannel().sendMessage("Banana").queue();
        }//if
    }//spitThemOut



    public void videoTester(Message words, String messageString){

            if (messageString.contains("video") || messageString.contains("youtube") || messageString.contains("youtu")) {
                words.getChannel().sendMessage("Your video is lame, this is MY favorite video: https://www.youtube.com/watch?v=d9hAVBx3teA").queue();
            }//if

    }//videoTester
    public void questionTester(Message words, String messageString){
        int random = (int) (1 + Math.random() * 2);
        if (messageString.toLowerCase().contains("?")&&!messageString.toLowerCase().contains("eat")) {
            if (messageString.toLowerCase().contains("how")) {
                if(messageString.toLowerCase().contains("many"))
                {
                    if(random==1)
                    {
                        words.getChannel().sendMessage("ZERO").queue();
                    }//if
                    else{
                        words.getChannel().sendMessage("1 MILLION").queue();
                    }//else

                }//if
                else if(messageString.toLowerCase().contains(" i ")){
                    words.getChannel().sendMessage("you dont").queue();
                }//if
                else if(messageString.toLowerCase().contains("you")) {
                    words.getChannel().sendMessage("I dont").queue();
                }//else if
                else{
                    words.getChannel().sendMessage("it doesnt").queue();
                }//else

            }//if
            else if (messageString.toLowerCase().contains("can")) {
                if (messageString.toLowerCase().contains(" i ")) {
                    words.getChannel().sendMessage("you can't").queue();
                }//if
                else if (messageString.toLowerCase().contains("you")) {
                    if(random==1)
                    {
                        words.getChannel().sendMessage("I can but I won't").queue();
                    }//if
                    else{
                        words.getChannel().sendMessage("I can and I WILL").queue();
                    }//else


                }//else if
                else {
                    words.getChannel().sendMessage("Nope").queue();
                }//else
            }//else if
            else if (messageString.toLowerCase().contains("who")) {
                if(random==1)
                {
                    words.getChannel().sendMessage("nobody").queue();
                }//if
                else{
                    words.getChannel().sendMessage("ME").queue();
                }//else

            }//else if

            else if (messageString.toLowerCase().contains("what")) {
                if(random==1){
                    words.getChannel().sendMessage("nothing").queue();
                }//if
                else{
                    words.getChannel().sendMessage("Bananas").queue();
                }

            }

            else if (messageString.toLowerCase().contains("when")) {
                if(random==1)
                {
                    words.getChannel().sendMessage("never").queue();
                }//if
                else{
                    words.getChannel().sendMessage("In 1 MILLION years").queue();
                }//else


            }

            else if (messageString.toLowerCase().contains("where")) {
                words.getChannel().sendMessage("nowhere").queue();
            }

            else if (messageString.toLowerCase().contains("why")) {
                if (messageString.toLowerCase().contains(" i ")) {
                    words.getChannel().sendMessage("because you just do").queue();
                } else if (messageString.toLowerCase().contains("you")) {
                    words.getChannel().sendMessage("because I said so").queue();
                } else {
                    words.getChannel().sendMessage("because I said so").queue();
                }
            }


            else if (messageString.toLowerCase().contains("which")) {
                if (messageString.toLowerCase().contains(" i ")) {
                    words.getChannel().sendMessage("you should pick none").queue();
                }//if
                else {
                    words.getChannel().sendMessage("none of them").queue();
                }//else
            }//else if

            else if (messageString.toLowerCase().contains("whose")) {
                words.getChannel().sendMessage("its mine").queue();
            }//else if

            else{
                words.getChannel().sendMessage("No").queue();
            }//else

        }//if
    }//questionTester
    public void shutup(Message words, String messageString){

        if(messageString.toLowerCase().equals("speak up monkey man")){
            alwaysTalk=true;
        }//if
        if(messageString.toLowerCase().equals("shut up monkey man")){
            alwaysTalk=false;
        }//if
    }//shutup
    public void joinVC(Message words, String messageString) {
        if (messageString.toLowerCase().equals("join me")) {
            GuildVoiceState voiceState = words.getMember().getVoiceState();

            if (voiceState == null || !voiceState.inAudioChannel()) {
                words.getChannel().sendMessage("You need to be in a voice channel first!").queue();
                return;
            }//if

            AudioManager audioManager = words.getGuild().getAudioManager();
            musicManager = new GuildMusicManager(playerManager);
            audioManager.setSendingHandler(musicManager);
            audioManager.openAudioConnection(voiceState.getChannel());
            listenForSpeaking(audioManager); // ADD THIS
            soundMaker=new SoundLoader(musicManager,this, 4);
            System.out.println("Before connect - Status: " + audioManager.getConnectionStatus());


                //audioManager.openAudioConnection(voiceState.getChannel());

        }//if
        if(messageString.toLowerCase().equals("!leave")){
            AudioManager audioManager = words.getGuild().getAudioManager();
            audioManager.closeAudioConnection();
        }//if
    }//joinVC

    public void playSound(Message words, String messageString) {
        if (messageString.toLowerCase().equals("play sound")) {
            AudioManager audioManager = words.getGuild().getAudioManager();

            if (!audioManager.isConnected()) {
                words.getChannel().sendMessage("I need to be in a voice channel first!").queue();
                return;
            }

            playerManager.loadItem("ENTER mp3 PATH HERE",soundMaker);

            words.getChannel().sendMessage("Playing sound!").queue();
        }//if
    }//playAudio

    public void listenForSpeaking(AudioManager audioManager) {
        audioManager.setReceivingHandler(new SpeakingHandler(this, soundMaker));
    }//listenForSpeaking
    public void startTimer(Message words, String messageString) {
        if (messageString.toLowerCase().equals("start timer")) {
            timerGoing=true;

            words.getChannel().sendMessage("Timer started!").queue();
            new Thread(() -> {
                while (timerGoing) {
                    try {
                        playerManager.loadItem("ENTER .mp3 PATH HERE", soundMaker);
                        Thread.sleep((int) (15000 + Math.random() * 35001)); // wait 5 seconds
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }).start();
        }//if
        else if(messageString.toLowerCase().equals("stop timer")){
            timerGoing=false;
        }//else if
    }//startTimer
    @Override
    public void onGuildVoiceUpdate(GuildVoiceUpdateEvent event) {
        if (event.getMember().getUser().equals(event.getJDA().getSelfUser())) {
            System.out.println("Bot voice update!");
            System.out.println("Joined: " + event.getChannelJoined());
            System.out.println("Left: " + event.getChannelLeft());
            System.out.println("Audio manager connected: " + event.getGuild().getAudioManager().isConnected());
            System.out.println("Connection status: " + event.getGuild().getAudioManager().getConnectionStatus());
        }
    }//onGuildVoiceUpdate




}//Main





