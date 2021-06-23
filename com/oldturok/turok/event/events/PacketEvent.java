package com.oldturok.turok.event.events;

import com.oldturok.turok.event.TurokEvent;
import net.minecraft.network.Packet;

public class PacketEvent extends TurokEvent {
   private final Packet packet;

   public PacketEvent(Packet packet) {
      this.packet = packet;
   }

   public Packet getPacket() {
      return this.packet;
   }

   public static class Send extends PacketEvent {
      public Send(Packet packet) {
         super(packet);
      }
   }

   public static class Receive extends PacketEvent {
      public Receive(Packet packet) {
         super(packet);
      }
   }
}
