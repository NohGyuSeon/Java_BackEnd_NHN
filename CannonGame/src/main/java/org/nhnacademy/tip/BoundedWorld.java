package org.nhnacademy.tip;

public class BoundedWorld extends MovableWorld {
    public BoundedWorld(int width, int height) {
        super(width, height);
    }

    @Override
    public void add(Bounds bounds) {
        synchronized (boundsList) {

            for (Bounds other : boundsList) {
                if (other instanceof Bounded) {
                    ((Bounded) other).addExcludedBounds(bounds);
                }
            }
        }

        if (bounds instanceof Bounded) {
            ((Bounded) bounds).setBounds(0, 0, getWidth(), getHeight());

            synchronized (boundsList) {

                for (Bounds other : boundsList) {
                    ((Bounded) bounds).addExcludedBounds(other);
                }
            }
        }

        super.add(bounds);
    }
}
