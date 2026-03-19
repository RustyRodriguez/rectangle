package com.ty.service;

import com.ty.model.AdjacencyType;
import com.ty.model.Point;
import com.ty.model.Rectangle;
import com.ty.result.IntersectionResult;

import java.util.Collections;
import java.util.List;

import static com.ty.model.AdjacencyType.*;

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

    public AdjacencyType getAdjacencyType(Rectangle r1, Rectangle r2) {
        boolean vertical = r1.getRight() == r2.getLeft() || r1.getLeft() == r2.getRight();
        boolean horizontal = r1.getTop() == r2.getBottom() || r1.getBottom() == r2.getTop();

        if (vertical) {
            int overlapStart = Math.max(r1.getBottom(), r2.getBottom());
            int overlapEnd = Math.min(r1.getTop(), r2.getTop());

            if (overlapStart >= overlapEnd) {
                return NONE;
            }

            int r1Height = r1.getTop() - r1.getBottom();
            int r2Height = r2.getTop() - r2.getBottom();
            int overlap = overlapEnd - overlapStart;

            if (r1Height == overlap && r2Height == overlap) {
                return PROPER;
            } else if (r1Height == overlap || r2Height == overlap) {
                return SUBLINE;
            } else {
                return PARTIAL;
            }
        }

        if (horizontal) {
            int overlapStart = Math.max(r1.getLeft(), r2.getLeft());
            int overlapEnd = Math.min(r1.getRight(), r2.getRight());

            if (overlapStart >= overlapEnd) {
                return NONE;
            }

            int r1Width = r1.getRight() - r1.getLeft();
            int r2Width = r2.getRight() - r2.getLeft();
            int overlap = overlapEnd - overlapStart;

            if (r1Width == overlap && r2Width == overlap) {
                return PROPER;
            } else if (r1Width == overlap || r2Width == overlap) {
                return SUBLINE;
            } else {
                return PARTIAL;
            }
        }

        return NONE;
    }

    public IntersectionResult computeIntersection(Rectangle r1, Rectangle r2) {
        int overlapLeft = Math.max(r1.getLeft(), r2.getLeft());
        int overlapRight = Math.min(r1.getRight(), r2.getRight());
        int overlapBottom = Math.max(r1.getBottom(), r2.getBottom());
        int overlapTop = Math.min(r1.getTop(), r2.getTop());

        int area = (overlapRight - overlapLeft) * (overlapTop - overlapBottom);

        if (area == 0) {
            return new IntersectionResult(false, Collections.emptyList());
        }

        List<Point> overlappingPoints = List.of(
                new Point(overlapLeft, overlapBottom),
                new Point(overlapRight, overlapBottom),
                new Point(overlapLeft, overlapTop),
                new Point(overlapRight, overlapTop)
        );

        return new IntersectionResult(true, overlappingPoints);
    }
}
