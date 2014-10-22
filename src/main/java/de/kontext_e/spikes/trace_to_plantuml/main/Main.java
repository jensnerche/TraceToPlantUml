package de.kontext_e.spikes.trace_to_plantuml.main;

import de.kontext_e.spikes.trace_to_plantuml.TracingAspect;
import de.kontext_e.spikes.trace_to_plantuml.application.Controller;
import de.kontext_e.spikes.trace_to_plantuml.de.kontext_e.spikes.trace_to_plantuml.plantuml.RenderToPng;

public class Main {
    public static void main(String[] args) {
        new Controller().processRequest();

        new RenderToPng().render(TracingAspect.getMessages());
    }
}
