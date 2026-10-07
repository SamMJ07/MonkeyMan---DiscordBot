# Monkey Man Discord Bot

A chaotic joke Discord bot written in Java. Monkey Man answers questions with nonsense, "eats" people (deleting their messages and reposting them as a monkey), joins voice channels, and plays monkey sound effects on command or at random intervals.

Built with [JDA](https://github.com/discord-jda/JDA) and [LavaPlayer](https://github.com/sedmelluq/lavaplayer) for voice audio.

## Features

- **Question answering:** replies to any message containing `?` with a silly answer ("nobody", "1 MILLION", "Bananas", and so on)
- **Eating people:** "eats" a user so their messages are deleted and reposted as `Monkey says: ...`
- **Voice channel audio:** joins your voice channel and plays sound effects on command or on a random timer
- **Interrupting people:** targets a user and interrupts them with a sound whenever they talk in voice chat
- **Video roasting:** reacts to YouTube links and the word "video"

## Commands

Anyone in the server can use these commands. Type them as normal chat messages.

| Message | What it does |
|---|---|
| `monkey man eat <name>` | Eats the named user. Their messages get deleted and reposted as "Monkey says: ..." |
| `spit them out` | Releases the eaten user |
| `monkey man bonk <name>` | Targets a user. While the bot is in a voice channel, it interrupts them with a sound whenever they speak |
| `stop attacking` | Stops interrupting the targeted user |
| `join me` | Bot joins the voice channel you are in |
| `!leave` | Bot leaves the voice channel |
| `play sound` | Plays a sound (bot must already be in a voice channel) |
| `start timer` | Plays a sound at random intervals (every 15 to 50 seconds) |
| `stop timer` | Stops the random sound timer |
| `speak up monkey man` | Turns on the bot's always-talk setting |
| `shut up monkey man` | Turns it off |
| Any message with `?` | Monkey Man answers with a random joke reply |
| Message containing `video`, `youtube` or `youtu` | Monkey Man shares his favorite video |

Note: `<name>` is the user's Discord username, written as a single word.

## Requirements

- [Java JDK 17](https://adoptium.net/) or newer
- [Maven](https://maven.apache.org/) (or an IDE like IntelliJ IDEA, which includes it)
- A Discord bot token (see below)
- Your own `.mp3` sound files

## Setup

### 1. Create a Discord bot

1. Go to the [Discord Developer Portal](https://discord.com/developers/applications) and create a new application.
2. Open the **Bot** tab and click **Reset Token** to get your token. Keep it secret and never share it.
3. On the same tab, enable these **Privileged Gateway Intents**:
   - Server Members Intent
   - Message Content Intent
4. Under **OAuth2 > URL Generator**, select the `bot` scope and give it permission to read and send messages, manage messages (needed to delete messages), connect, and speak. Open the generated link to invite the bot to your server.

### 2. Download the project

```bash
git clone https://github.com/SamMJ07/MonkeyMan---DiscordBot
.git
cd MonkeyMan---DiscordBot
```

### 3. Add your bot token

Open `src/main/java/org/example/Main.java` and find this line:

```java
JDA bot = JDABuilder.createDefault("ENTER TOKEN HERE")
```

Replace `ENTER TOKEN HERE` with your bot's token.

**Important:** never upload your real token to GitHub. If you plan to commit changes, put the placeholder back first.

### 4. Add your sound files

Put your `.mp3` files in a folder you can find, then replace each placeholder in `Main.java` with the path to one of your files. Search the file for `ENTER mp3 PATH HERE`. There are four of them:

- Three in the `main` method (they preload your sounds)
- One in the `playSound` method (the sound played by `play sound`)

There is also a file path inside the `startTimer` method (the sound played by `start timer`). Replace that one too.

Example (Windows paths need double backslashes):

```java
"C:\\Users\\you\\Music\\monkey.mp3"
```

A path relative to the project folder also works, for example `"sounds/monkey.mp3"`, as long as you run the bot from the project folder.

### 5. Build and run

In IntelliJ, open the project folder (the one containing `pom.xml`), let Maven load the dependencies, and run `Main`.

Or from the command line:

```bash
mvn clean package
java -cp target/classes org.example.Main
```

If the bot started correctly, it appears online in your server and the console prints `Opus loaded successfully!`.

## Troubleshooting

- **`NoClassDefFoundError`:** the project was run without Maven. Open the folder containing `pom.xml` as a Maven project in your IDE instead of running a single file.
- **Bot is online but does not respond:** check that the Message Content Intent is enabled in the Developer Portal.
- **Bot joins voice but plays no audio:** check that the console says `Opus loaded successfully!` and that your sound file paths are correct.
- **Messages are not being deleted:** give the bot the Manage Messages permission.
- **Invalid token error:** make sure you pasted the full token with no extra spaces, and that you used the bot token (not the application ID or client secret).

## Disclaimer

This is a hobby project made for fun. Use it only in servers where everyone is okay with it, since it deletes and reposts other people's messages.




