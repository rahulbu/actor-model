package org.example;


import akka.actor.typed.ActorSystem;

public class Main {
  public static void main(String[] args) {
    ActorSystem<HelloWorldActor.Command> actorRef = ActorSystem.create(HelloWorldActor.Create(), "myActor");
    actorRef.tell(HelloWorldActor.SayHello.INSTANCE);
    actorRef.tell(new HelloWorldActor.ChangeMessage(" this is new message "));
    actorRef.tell(HelloWorldActor.SayHello.INSTANCE);
    actorRef.tell(new HelloWorldActor.Command() {
    });
    actorRef.tell(HelloWorldActor.SayHello.INSTANCE);
  }
}