package Bot;

import discord4j.core.object.entity.Message;

public interface IJavaDocBotCommand {
    Message getMessageToPost();
}
