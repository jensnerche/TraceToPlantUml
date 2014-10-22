package de.kontext_e.spikes.trace_to_plantuml.application;

import javax.swing.text.html.parser.Entity;

public class LoadEntityResult {
    public static enum Results {NO_RESULT, ONE_RESULT, MORE_THAN_ONE_RESULTS}
    private final Results results;
    private Entity entity;

    public LoadEntityResult(final Entity entity) {
        this.entity = entity;
        results = Results.NO_RESULT;
    }

    public LoadEntityResult(final Results results) {
        this.results = results;
    }

    public Results getResults() {
        return results;
    }

    public Entity getEntity() {
        return entity;
    }

    public static LoadEntityResult noResult() {
        return new LoadEntityResult(Results.NO_RESULT);
    }

    public static LoadEntityResult moreThanOneEntity() {
        return new LoadEntityResult(Results.MORE_THAN_ONE_RESULTS);
    }

    @Override
    public String toString() {
        return "LoadEntityResult{" +
               "results=" + results +
               '}';
    }
}
