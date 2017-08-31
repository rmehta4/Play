name := """play-java-seed"""
organization := "ncsu"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.2"

libraryDependencies += guice
libraryDependencies += javaJdbc
libraryDependencies += "org.jsr107.ri" % "cache-annotations-ri-guice" % "1.0.0"
libraryDependencies += "org.xerial" % "sqlite-jdbc" % "3.8.11.2"


