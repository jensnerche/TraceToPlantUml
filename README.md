TraceToPlantUml
===============

Use AspectJ and PlantUML to produce UML sequence diagrams from runtime trace information.

Usage
=====
In the project dir call 'ant'. Then you should find an out.png.

Documentation
=============
The source folder 'aspectj' contains the aspect classes. The TracingAspect is called around every method call and traces the entry,
the exit, and exceptions.

The source folder 'java' contains a sample application.
In package de.kontext_e.spikes.trace_to_plantuml.application is a sample business logic.
These classes should be traced.

Package de.kontext_e.spikes.trace_to_plantuml.de.kontext_e.spikes.trace_to_plantuml.plantuml contains a simple PlantUML
sequence diagram renderer which produces a PNG in the working directory called out.png.

Package de.kontext_e.spikes.trace_to_plantuml.main contains the Main class which runs the application and then calls the 
PlantUML renderer.
