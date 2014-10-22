package de.kontext_e.spikes.trace_to_plantuml.application;

import javax.swing.text.html.parser.Entity;

public class Repository {
    public Entity readEntity(final int id) {
        throw new NoSuchEntityException(id);
    }
}
