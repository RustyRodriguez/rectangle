package com.ty.service;

import com.ty.model.AdjacencyType;
import com.ty.model.Point;
import com.ty.model.Rectangle;
import com.ty.result.IntersectionResult;

import java.util.Collections;
import java.util.List;

import static com.ty.model.AdjacencyType.NONE;
import static com.ty.model.AdjacencyType.PARTIAL;
import static com.ty.model.AdjacencyType.PROPER;
import static com.ty.model.AdjacencyType.SUBLINE;

public class RectangleAnalyzerService {

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

    /**
     * Determines whether two rectangles are adjacent and classifies the type of adjacency
     *
     * @param r1 first rectangle
     * @param r2 second rectangle
     * @return AdjacencyType an enum representing the adjacency classification
     */
    public AdjacencyType classifyAdjacency(Rectangle r1, Rectangle r2) {
        boolean vertical = r1.getRight() == r2.getLeft() || r1.getLeft() == r2.getRight();
        boolean horizontal = r1.getTop() == r2.getBottom() || r1.getBottom() == r2.getTop();

        if (vertical) {
            int overlapStart = Math.max(r1.getBottom(), r2.getBottom());
            int overlapEnd = Math.min(r1.getTop(), r2.getTop());

            return classifyOverlap(overlapStart, overlapEnd, r1.height(), r2.height());
        }

        if (horizontal) {
            int overlapStart = Math.max(r1.getLeft(), r2.getLeft());
            int overlapEnd = Math.min(r1.getRight(), r2.getRight());

            return classifyOverlap(overlapStart, overlapEnd, r1.width(), r2.width());
        }

        return NONE;
    }

    /**
     * Determines the intersection region between two rectangles, if any.
     * Intersection is defined as the overlapping area. The four returned points
     * are the corners of that overlap region.
     *
     * @param r1 first rectangle
     * @param r2 second rectangle
     * @return IntersectionResult containing a boolean indicating overlap and the
     *         four corner points of the overlapping region
     */
    public IntersectionResult computeIntersection(Rectangle r1, Rectangle r2) {
        int left = Math.max(r1.getLeft(), r2.getLeft());
        int right = Math.min(r1.getRight(), r2.getRight());
        int bottom = Math.max(r1.getBottom(), r2.getBottom());
        int top = Math.min(r1.getTop(), r2.getTop());

        int width = right - left;
        int height = top - bottom;

        if (width <= 0 || height <= 0) {
            return new IntersectionResult(false, Collections.emptyList());
        }

        List<Point> intersectionPoints = createIntersectionPoints(left, right, top, bottom);

        return new IntersectionResult(true, intersectionPoints);
    }

    private List<Point> createIntersectionPoints(int left, int right, int top, int bottom) {
        return List.of(new Point(left, bottom), new Point(right, bottom), new Point(left, top), new Point(right, top));
    }

    private AdjacencyType classifyOverlap(int overlapStart, int overlapEnd, int firstLength, int secondLength) {
        if (overlapStart >= overlapEnd) {
            return NONE;
        }

        int overlap = overlapEnd - overlapStart;

        if (firstLength == overlap && secondLength == overlap) {
            return PROPER;
        } else if (firstLength == overlap || secondLength == overlap) {
            return SUBLINE;
        } else {
            return PARTIAL;
        }
    }
}
