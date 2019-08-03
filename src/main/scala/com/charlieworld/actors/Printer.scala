package com.charlieworld.actors

import akka.actor.{Actor, ActorLogging, Props}
import com.charlieworld.actors.Printer.Greeting

object Printer {
  def props: Props = Props[Printer]

  final case class Greeting(greeting: String)
}

class Printer extends Actor with ActorLogging {

  def receive = {
    case Greeting(greeting) =>
      log.info("Greeting received (from " + sender() + "): " + greeting)
  }
}
