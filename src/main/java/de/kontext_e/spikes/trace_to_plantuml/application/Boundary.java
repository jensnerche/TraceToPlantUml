package de.kontext_e.spikes.trace_to_plantuml.application;

public class Boundary {

    private Repository repository = new Repository();

    public LoadEntityResult loadEntity(final int id) {
        try {
            return new LoadEntityResult(repository.readEntity(id));
        } catch (NoSuchEntityException e) {
            return LoadEntityResult.noResult();
        } catch (MoreThanOneEntityException e) {
            return LoadEntityResult.moreThanOneEntity();
        }
    }
}
