package org.example;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class HelloWorldActor extends AbstractBehavior<HelloWorldActor.Command> {

  interface Command {}

  public static Behavior<Command> Create() {
    return Behaviors.setup(HelloWorldActor::new);
  }

  private HelloWorldActor(ActorContext<Command> context) {
    super(context);
  }

  public enum SayHello implements Command {
    INSTANCE
  }

  public static class ChangeMessage implements Command {
    public final String message;

    public ChangeMessage(String message) {
      this.message = message;
    }
  }

  private String message = "first message";

  @Override
  public Receive<Command> createReceive() {
    return newReceiveBuilder()
               .onMessageEquals(SayHello.INSTANCE, this::OnSayHello)
               .onMessage(ChangeMessage.class, this::OnChangeMessage)
               .onAnyMessage(this::RandomMessage)
               .build();
  }

  private Behavior<Command> RandomMessage(Command command) {
    System.out.println("some random message");
    return this;
  }

  private Behavior<Command> OnChangeMessage(ChangeMessage changeMessage) {
    message = changeMessage.message;
    return this;
  }

  private Behavior<Command> OnSayHello() {
    System.out.println(message);
    return this;
  }
}
