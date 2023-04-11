package org.nhnacademy.tip;

import java.util.EventListener;

public interface CollisionEventListener extends EventListener {
    public void collisionEvent(CollisionEvent event);
}
