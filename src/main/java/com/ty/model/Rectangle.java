package com.ty.model;

import com.ty.exception.InvalidRectangleException;

public class Rectangle {
    private final int left;
    private final int right;
    private final int top;
    private final int bottom;

    public Rectangle(Point p1, Point p2) {
        if (p1.x() == p2.x() || p1.y() == p2.y()) {
            throw new InvalidRectangleException("Invalid Rectangle: the 2 points cannot share the same x or y coordinate");
        }

        this.left = Math.min(p1.x(), p2.x());
        this.right = Math.max(p1.x(), p2.x());
        this.top = Math.max(p1.y(), p2.y());
        this.bottom = Math.min(p1.y(), p2.y());
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int getTop() {
        return top;
    }

    public int getBottom() {
        return bottom;
    }
}
