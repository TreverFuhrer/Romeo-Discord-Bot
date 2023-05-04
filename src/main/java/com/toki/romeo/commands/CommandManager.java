package com.toki.romeo.commands;

import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.util.ArrayList;
import java.util.List;


public class CommandManager extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String command = event.getName();
        if (command.equals("hii")) {
            String userTag = event.getUser().getAsTag();
            if(userTag.equals("Toki#1111")) {
                event.reply("hello daddy trev  *Sits*\ni love you meow").queue();
            }
            else if(userTag.equals("rcmvnova#0777")) {
                event.reply("hi mommy natalie !! [*runs, jumps, and lays on lap*] i love you mommy meoow *purrs*").queue();
            }
            else {
                event.reply("leave me alone "
                        + userTag.toLowerCase().charAt(0)
                        + userTag.substring(1, userTag.indexOf("#"))
                        + "you little bitch.").queue();
            }
        }
    }

    @Override
    public void onGuildReady(GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("hii", "HEHE"));
        event.getGuild().updateCommands().addCommands(commandData).queue();
    }
}
