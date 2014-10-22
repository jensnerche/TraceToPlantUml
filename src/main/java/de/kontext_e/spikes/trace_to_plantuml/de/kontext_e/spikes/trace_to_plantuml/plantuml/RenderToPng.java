package de.kontext_e.spikes.trace_to_plantuml.de.kontext_e.spikes.trace_to_plantuml.plantuml;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import de.kontext_e.spikes.trace_to_plantuml.TracingEvent;
import net.sourceforge.plantuml.SourceStringReader;

public class RenderToPng {

    public void render(final List<TracingEvent> messages) {
        try {
            final OutputStream png = new FileOutputStream("out.png");
            String source = "@startuml\n";
            for (final TracingEvent message : messages) {
                // TODO use renderers and remove asXXXMessage methods
                switch (message.getTraceType()) {
                    case ENTRY:
                        source += message.asCallMessage()+"\n";
                        break;
                    case EXIT:
                        source += message.asExitMessage()+"\n";
                        break;
                    case EXCEPTION:
                        source += message.getTarget().getName() + " -> " + message.getSource().getName() + " : <color:red>throws" + "(" + Arrays.deepToString(message.getArgs()) + ")</color>"+"\n";
                        break;
                }
            }

            source += "@enduml\n";

            final SourceStringReader reader = new SourceStringReader(source);
            reader.generateImage(png);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
