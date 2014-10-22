package de.kontext_e.spikes.trace_to_plantuml.application;

public class Controller {

    private Boundary boundary = new Boundary();

    public void processRequest() {
        final LoadEntityResult result = boundary.loadEntity(1);

        switch(result.getResults()) {
            case ONE_RESULT: break;
            case NO_RESULT: break;
            case MORE_THAN_ONE_RESULTS: break;
        }
    }
}
