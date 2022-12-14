package org.gift.terminal.backoffice.core.manager;

import java.util.List;

public interface ItemManager<D> {
    D create(D dto);

    D edit(D dto);

    void delete(D dto);

    List<D> view();
}
