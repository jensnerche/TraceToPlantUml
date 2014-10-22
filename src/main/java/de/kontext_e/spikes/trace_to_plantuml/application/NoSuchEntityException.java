package de.kontext_e.spikes.trace_to_plantuml.application;

public class NoSuchEntityException extends RuntimeException {
    private final int id;

    public NoSuchEntityException(final int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "NoSuchEntityException{" +
               "id=" + id +
               '}';
    }
}
