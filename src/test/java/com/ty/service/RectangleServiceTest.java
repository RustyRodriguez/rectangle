package com.ty.service;

import com.ty.model.AdjacencyType;
import com.ty.model.Point;
import com.ty.model.Rectangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangleServiceTest {

    private final RectangleService rectangleService = new RectangleService();

    @Test
    void shouldReturnTrueWhenOuterContainsInner() {
        Rectangle outer = new Rectangle(new Point(1, 1), new Point(5, 5));
        Rectangle inner = new Rectangle(new Point(2, 2), new Point(3, 4));

        assertTrue(rectangleService.contains(outer, inner));
    }

    @Test
    void shouldReturnFalseWhenOuterDoesNotContainInner() {
        Rectangle outer = new Rectangle(new Point(1, 1), new Point(5, 5));
        Rectangle inner = new Rectangle(new Point(0, 0), new Point(3, 4));

        assertFalse(rectangleService.contains(outer, inner));
    }

    @Test
    void shouldReturnTrueWhenRectanglesAreIdentical() {
        Rectangle outer = new Rectangle(new Point(1, 1), new Point(5, 5));
        Rectangle inner = new Rectangle(new Point(1, 1), new Point(5, 5));

        assertTrue(rectangleService.contains(outer, inner));
    }

    @Test
    void shouldReturnTrueWhenInnerTouchesOuterBoundary() {
        Rectangle outer = new Rectangle(new Point(1, 1), new Point(5, 5));
        Rectangle inner = new Rectangle(new Point(1, 2), new Point(3, 4));

        assertTrue(rectangleService.contains(outer, inner));
    }

    @Test
    void shouldReturnFalseWhenPartialOverlap() {
        Rectangle outer = new Rectangle(new Point(1, 1), new Point(5, 5));
        Rectangle inner = new Rectangle(new Point(3, 3), new Point(7, 7));

        assertFalse(rectangleService.contains(outer, inner));
    }

    @Test
    void shouldReturnNoneWhenNotAdjacent() {
        Rectangle r1 = new Rectangle(new Point(1, 1), new Point(3, 4));
        Rectangle r2 = new Rectangle(new Point(5, 1), new Point(7, 4));

        assertEquals(AdjacencyType.NONE, rectangleService.getAdjacencyType(r1, r2));
    }

    @Test
    void shouldReturnProperWhenRectanglesShareEntireSide() {
        Rectangle r1 = new Rectangle(new Point(1, 1), new Point(3, 4));
        Rectangle r2 = new Rectangle(new Point(3, 1), new Point(5, 4));

        assertEquals(AdjacencyType.PROPER, rectangleService.getAdjacencyType(r1, r2));
    }

    @Test
    void shouldReturnSublineWhenOneTouchingSideIsContainedWithinTheOther() {
        Rectangle r1 = new Rectangle(new Point(1, 1), new Point(3, 6));
        Rectangle r2 = new Rectangle(new Point(3, 2), new Point(5, 4));

        assertEquals(AdjacencyType.SUBLINE, rectangleService.getAdjacencyType(r1, r2));
    }

    @Test
    void shouldReturnPartialWhenRectanglesShareOnlyPartOfASide() {
        Rectangle r1 = new Rectangle(new Point(1, 1), new Point(3, 5));
        Rectangle r2 = new Rectangle(new Point(3, 4), new Point(5, 8));

        assertEquals(AdjacencyType.PARTIAL, rectangleService.getAdjacencyType(r1, r2));
    }
}