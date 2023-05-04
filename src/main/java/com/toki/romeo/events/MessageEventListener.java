package com.toki.romeo.events;

import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class MessageEventListener extends ListenerAdapter {
    boolean romeoAskedHowAreU = false;
    int romeoAskedHowAreUCount = 0;
    String[] greetings = {"hello", "whats up", "helo", "honk", "greetings", "hi", "howdy", "sup", "hey"};
    String[] goodWords = {"good", "well", "wel", "happy", "amazing", "wonderful", "incredible", "okay"};
    String[] badWords = {"fine", "bad", "sad", "mad", "unhappy", "not well", "not happy", "not good"};
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        // Variables
        String message = event.getMessage().getContentDisplay().toLowerCase();
        boolean iAm=false, good=false, bad=false;

        // Checks nature of message if after romeoAskedHowAreU
        if(romeoAskedHowAreU) {
            if(message.contains("i am ") || message.contains("i'm ") || message.contains("im ") || message.contains("i’m ")) {
                iAm = true;
            }
            for(int index = 0; index < goodWords.length; index++) {
                if(message.contains(goodWords[index])) {
                    good = true;
                    break;
                }
            }
            for(int index = 0; index < badWords.length; index++) {
                if (message.contains(badWords[index])) {
                    bad = true;
                    break;
                }
            }
        }
        // Sends Messages
        if(event.getAuthor().isBot())
            return; // Checks if message is from bot
        else if(iAm && (good || bad)) {
            if(good)
                event.getChannel().sendMessage("im glad !! i am good too hehe").queue();
            else
                event.getChannel().sendMessage("oh, im sorry. i hope you feel better soon").queue();
        }
        else if(message.contains("romeo"))
        {
            // Checks if message contains greeting from array
            for(int index = 0; index < greetings.length; index++){
                if(message.contains(greetings[index])) {
                    int mrMax = 100, mrMin = 1;
                    int messageRand = (int)(Math.random()*((mrMax-mrMin)+1))+mrMin; // Max 100, Min 1
                    System.out.println("Romeo greeting response = " + messageRand);

                    // 5 Different romeo replies of varying probability
                    if(messageRand > 65)
                        event.getChannel().sendMessage("hello :3").queue();
                    else if(messageRand > 40)
                        event.getChannel().sendMessage("what? *purrs*").queue();
                    else if(messageRand > 15){
                        event.getChannel().sendMessage("hi, how are you? hehe").queue();
                        romeoAskedHowAreU = true;
                        romeoAskedHowAreUCount = 0;
                    }
                    else if(messageRand > 5)
                        event.getChannel().sendMessage("**hisses**").queue();
                    else
                        event.getChannel().sendMessage("What bitch?").queue();
                }
            }
            if(message.contains("i love you") || message.contains("i luv you") || message.contains("i wuv you"))
                event.getChannel().sendMessage("i love you too :3").queue();
        }
        // Lets next run be checked if romeoAskedHowAreU is true then next run sets romeoAskedHowAreU false
        romeoAskedHowAreUCount += 1;
        if(romeoAskedHowAreUCount == 2) {
            romeoAskedHowAreU = false;
            System.out.println("romeoAskedHowAreU was set false.");
        }
    }
}
