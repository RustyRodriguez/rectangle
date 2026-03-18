package com.ty.result;

import com.ty.model.Point;

import java.util.List;

public record IntersectionResult(
        boolean hasIntersection,
        List<Point> points
) {}
