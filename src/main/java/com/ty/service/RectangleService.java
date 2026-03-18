package com.ty.service;

import com.ty.model.Rectangle;

public class RectangleService {

    /**
     * Determines whether the outer rectangle contains the inner rectangle
     *
     * @param outer the rectangle that may contain the other
     * @param inner the rectangle being tested for containment
     * @return true if the inner rectangle is contained in the outer rectangle else false
     */
    public boolean contains(Rectangle outer, Rectangle inner) {
        return outer.getLeft() <= inner.getLeft() &&
                outer.getBottom() <= inner.getBottom() &&
                outer.getRight() >= inner.getRight() &&
                outer.getTop() >= inner.getTop();
    }
}
