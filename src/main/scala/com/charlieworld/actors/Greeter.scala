package com.charlieworld.actors

import akka.actor.{Actor, ActorRef, Props}
import com.charlieworld.actors.Greeter.{Greet, WhoToGreet}
import com.charlieworld.actors.Printer.Greeting

object Greeter {

  def props(message: String, printerActor: ActorRef): Props = Props(new Greeter(message, printerActor))

  final case class WhoToGreet(who: String)

  case object Greet
}

class Greeter(message: String, printerActor: ActorRef) extends Actor {

  var greeting = ""

  def receive = {
    case WhoToGreet(who) =>
      greeting = message + ", " + who
    case Greet           =>
      printerActor ! Greeting(greeting)
  }
}
