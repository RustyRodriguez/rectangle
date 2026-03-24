package com.ty.service;

import com.ty.model.AdjacencyType;
import com.ty.model.Point;
import com.ty.model.Rectangle;
import com.ty.result.IntersectionResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangleAnalyzerServiceTest {

    private final RectangleAnalyzerService rectangleAnalyzerService = new RectangleAnalyzerService();

    @Test
    void shouldReturnTrueWhenOuterContainsInner() {
        Rectangle outer = new Rectangle(new Point(1, 1), new Point(5, 5));
        Rectangle inner = new Rectangle(new Point(2, 2), new Point(3, 4));

        assertTrue(rectangleAnalyzerService.contains(outer, inner));
    }

    @Test
    void shouldReturnFalseWhenOuterDoesNotContainInner() {
        Rectangle outer = new Rectangle(new Point(1, 1), new Point(5, 5));
        Rectangle inner = new Rectangle(new Point(0, 0), new Point(3, 4));

        assertFalse(rectangleAnalyzerService.contains(outer, inner));
    }

    @Test
    void shouldReturnTrueWhenRectanglesAreIdentical() {
        Rectangle outer = new Rectangle(new Point(1, 1), new Point(5, 5));
        Rectangle inner = new Rectangle(new Point(1, 1), new Point(5, 5));

        assertTrue(rectangleAnalyzerService.contains(outer, inner));
    }

    @Test
    void shouldReturnTrueWhenInnerTouchesOuterBoundary() {
        Rectangle outer = new Rectangle(new Point(1, 1), new Point(5, 5));
        Rectangle inner = new Rectangle(new Point(1, 2), new Point(3, 4));

        assertTrue(rectangleAnalyzerService.contains(outer, inner));
    }

    @Test
    void shouldReturnFalseWhenPartialOverlap() {
        Rectangle outer = new Rectangle(new Point(1, 1), new Point(5, 5));
        Rectangle inner = new Rectangle(new Point(3, 3), new Point(7, 7));

        assertFalse(rectangleAnalyzerService.contains(outer, inner));
    }

    @Test
    void shouldReturnNoneWhenNotAdjacent() {
        Rectangle r1 = new Rectangle(new Point(1, 1), new Point(3, 4));
        Rectangle r2 = new Rectangle(new Point(5, 1), new Point(7, 4));

        assertEquals(AdjacencyType.NONE, rectangleAnalyzerService.classifyAdjacency(r1, r2));
    }

    @Test
    void shouldReturnNoneWhenRectanglesTouchAtCornerOnly() {
        Rectangle r1 = new Rectangle(new Point(1, 1), new Point(3, 3));
        Rectangle r2 = new Rectangle(new Point(3, 3), new Point(5, 5));

        assertEquals(AdjacencyType.NONE, rectangleAnalyzerService.classifyAdjacency(r1, r2));
    }

    @Test
    void shouldReturnProperWhenRectanglesShareEntireSideVertical() {
        Rectangle r1 = new Rectangle(new Point(1, 1), new Point(3, 4));
        Rectangle r2 = new Rectangle(new Point(3, 1), new Point(5, 4));

        assertEquals(AdjacencyType.PROPER, rectangleAnalyzerService.classifyAdjacency(r1, r2));
    }

    @Test
    void shouldReturnSublineWhenOneTouchingSideIsContainedWithinTheOtherVertical() {
        Rectangle r1 = new Rectangle(new Point(1, 1), new Point(3, 6));
        Rectangle r2 = new Rectangle(new Point(3, 2), new Point(5, 4));

        assertEquals(AdjacencyType.SUBLINE, rectangleAnalyzerService.classifyAdjacency(r1, r2));
    }

    @Test
    void shouldReturnPartialWhenRectanglesShareOnlyPartOfASideVertical() {
        Rectangle r1 = new Rectangle(new Point(1, 1), new Point(3, 5));
        Rectangle r2 = new Rectangle(new Point(3, 4), new Point(5, 8));

        assertEquals(AdjacencyType.PARTIAL, rectangleAnalyzerService.classifyAdjacency(r1, r2));
    }

    @Test
    void shouldReturnProperWhenRectanglesShareEntireSideHorizontal() {
        Rectangle r1 = new Rectangle(new Point(1, 1), new Point(4, 3));
        Rectangle r2 = new Rectangle(new Point(1, 3), new Point(4, 5));

        assertEquals(AdjacencyType.PROPER, rectangleAnalyzerService.classifyAdjacency(r1, r2));
    }

    @Test
    void shouldReturnSublineWhenOneTouchingSideIsContainedWithinTheOtherHorizontal() {
        Rectangle r1 = new Rectangle(new Point(1, 1), new Point(6, 3));
        Rectangle r2 = new Rectangle(new Point(2, 3), new Point(4, 5));

        assertEquals(AdjacencyType.SUBLINE, rectangleAnalyzerService.classifyAdjacency(r1, r2));
    }

    @Test
    void shouldReturnPartialWhenRectanglesShareOnlyPartOfASideHorizontal() {
        Rectangle r1 = new Rectangle(new Point(1, 1), new Point(4, 3));
        Rectangle r2 = new Rectangle(new Point(3, 3), new Point(6, 5));

        assertEquals(AdjacencyType.PARTIAL, rectangleAnalyzerService.classifyAdjacency(r1, r2));
    }

    @Test
    void shouldReturnNoIntersectionWhenRectanglesDoNotOverlap() {
        Rectangle r1 = new Rectangle(new Point(1, 1), new Point(3, 3));
        Rectangle r2 = new Rectangle(new Point(3, 3), new Point(8, 8));

        IntersectionResult result = rectangleAnalyzerService.computeIntersection(r1, r2);

        assertFalse(result.hasIntersection());
        assertTrue(result.points().isEmpty());
    }

    @Test
    void shouldReturnNoIntersectionWhenRectanglesAreSeparatedByGap() {
        Rectangle r1 = new Rectangle(new Point(1, 1), new Point(3, 3));
        Rectangle r2 = new Rectangle(new Point(5, 5), new Point(8, 8));

        IntersectionResult result = rectangleAnalyzerService.computeIntersection(r1, r2);

        assertFalse(result.hasIntersection());
        assertTrue(result.points().isEmpty());
    }

    @Test
    void shouldReturnIntersectionPointsWhenRectanglesOverlap() {
        Rectangle r1 = new Rectangle(new Point(1, 1), new Point(4, 4));
        Rectangle r2 = new Rectangle(new Point(2, 2), new Point(6, 6));

        IntersectionResult result = rectangleAnalyzerService.computeIntersection(r1, r2);

        assertTrue(result.hasIntersection());
        assertEquals(4, result.points().size());
        assertTrue(result.points().contains(new Point(2, 2)));
        assertTrue(result.points().contains(new Point(2, 4)));
        assertTrue(result.points().contains(new Point(4, 2)));
        assertTrue(result.points().contains(new Point(4, 4)));
    }
}