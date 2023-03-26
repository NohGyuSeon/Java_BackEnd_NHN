package org.nhnacademy.tip;

import java.util.EventObject;

public class CollisionEvent extends EventObject {
    Bounds destination;

    public CollisionEvent(Bounds source, Bounds destination) {
        super(source);

        this.destination = destination;
    }

    public Bounds getDestination() {
        return destination;
    }
}
