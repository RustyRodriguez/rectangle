package com.ty.model;

import com.ty.exception.InvalidRectangleException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RectangleTest {

    @Test
    void shouldCreateRectangleSuccessfully() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(3, 5);

        Rectangle rectangle = new Rectangle(p1, p2);

        assertEquals(1, rectangle.getLeft());
        assertEquals(3, rectangle.getRight());
        assertEquals(1, rectangle.getBottom());
        assertEquals(5, rectangle.getTop());
    }

    @Test
    void shouldThrowExceptionWhenHeightIsZero() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(3, 1);

        assertThrows(InvalidRectangleException.class, () -> {
            new Rectangle(p1, p2);
        });
    }

    @Test
    void shouldThrowExceptionWhenWidthIsZero() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 3);

        assertThrows(InvalidRectangleException.class, () -> {
            new Rectangle(p1, p2);
        });
    }
}