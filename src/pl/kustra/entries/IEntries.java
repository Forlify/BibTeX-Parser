package pl.kustra.entries;

import java.util.List;

public interface IEntries {
    Entry create();

    List<String> getRequiredAttributes();
    List<String> getOptionalAttributes();
}