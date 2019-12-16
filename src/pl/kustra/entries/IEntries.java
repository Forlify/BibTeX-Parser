package pl.kustra.entries;

import java.util.List;

/**
 * Main part of Abstract Factory design pattern. This is an interface for Factories producing empty entries of given type.
 * Classes implementing this interface should have names like: Entrytype, e.g. Book, Article.
 * Methods:<br>
 * - create(): creates empty Entry object of type specified in Factory name<br>
 * - getRequiredAttributes(): makes list of required attributes for certain object type<br>
 * - getOptionalAttributes(): makes list of optional attributes for certain object type<br>
 */
public interface IEntries {
    Entry create();

    List<String> getRequiredAttributes();

    List<String> getOptionalAttributes();
}