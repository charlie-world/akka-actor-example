package com.charlieworld

import java.util.logging.Logger

import akka.actor.{ActorRef, ActorSystem, PoisonPill}
import com.charlieworld.actors.Greeter.{Greet, WhoToGreet}
import com.charlieworld.actors.{Greeter, Printer}

object AkkaQuickstart extends App {

  val system: ActorSystem = ActorSystem("helloAkka")
  val logger: Logger = Logger.getLogger("app")

  val printer: ActorRef = system.actorOf(Printer.props, "printerActor")

  val howdyGreeter: ActorRef =
    system.actorOf(Greeter.props("Howdy", printer), "howdyGreeter")
  val helloGreeter: ActorRef =
    system.actorOf(Greeter.props("Hello", printer), "helloGreeter")
  val goodDayGreeter: ActorRef =
    system.actorOf(Greeter.props("Good day", printer), "goodDayGreeter")

  howdyGreeter ! WhoToGreet("Akka")
  howdyGreeter ! Greet

  howdyGreeter ! WhoToGreet("Lightbend")
  howdyGreeter ! Greet

  helloGreeter ! WhoToGreet("Scala")
  helloGreeter ! Greet

  goodDayGreeter ! WhoToGreet("Play")
  goodDayGreeter ! Greet

  logger.info("Kill all actors by poison pill")

  howdyGreeter ! PoisonPill
  helloGreeter ! PoisonPill
  goodDayGreeter ! PoisonPill
  printer ! PoisonPill

  logger.info("Terminate actor system")
  system.terminate()
}
