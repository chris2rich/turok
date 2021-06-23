package com.oldturok.turok.commands;

import com.oldturok.turok.TurokChat;
import com.oldturok.turok.TurokMessage;
import com.oldturok.turok.TurokMod;

public class TurokPrefix extends TurokChat {
   public TurokPrefix() {
      super("prefix", "-prefix new_prefix | For change prefix no?");
   }

   public boolean Get_Message(String[] message) {
      if (message.length > 1) {
         String prefix = message[1];
         TurokMod.get_instance().turok_chat_manager.set_prefix(prefix);
         TurokMod.TUROK_CHAT_PREFIX = prefix;
         TurokMessage.send_client_msg("The new character is " + TurokMod.get_instance().turok_chat_manager.get_prefix());
      } else {
         TurokMessage.send_client_msg("For change prefix. lol");
      }

      return true;
   }
}
