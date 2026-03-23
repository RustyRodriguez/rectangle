package com.ty;

import com.ty.model.AdjacencyType;
import com.ty.model.Point;
import com.ty.model.Rectangle;
import com.ty.result.IntersectionResult;
import com.ty.service.RectangleService;

public class Main {

    public static void main(String[] args) {
        RectangleService service = new RectangleService();
        
        Rectangle i1 = new Rectangle(new Point(1, 1), new Point(4, 4));
        Rectangle i2 = new Rectangle(new Point(3, 2), new Point(6, 5));
        IntersectionResult intersection = service.computeIntersection(i1, i2);

        System.out.println("Intersection: ");
        System.out.printf("i1: (%d,%d) to (%d,%d)%n", i1.getLeft(), i1.getBottom(), i1.getRight(), i1.getTop());
        System.out.printf("i2: (%d,%d) to (%d,%d)%n", i2.getLeft(), i2.getBottom(), i2.getRight(), i2.getTop());
        System.out.println("Has intersection: " + intersection.hasIntersection());
        
        if (intersection.hasIntersection()) {
            System.out.println("Intersection points: " + intersection.points());
        }
        
        Rectangle outer = new Rectangle(new Point(1, 1), new Point(6, 6));
        Rectangle inner = new Rectangle(new Point(2, 2), new Point(5, 4));

        System.out.println("Containment:");
        System.out.printf("Outer: (%d,%d) to (%d,%d)%n", outer.getLeft(), outer.getBottom(), outer.getRight(), outer.getTop());
        System.out.printf("Inner: (%d,%d) to (%d,%d)%n", inner.getLeft(), inner.getBottom(), inner.getRight(), inner.getTop());
        System.out.println("Outer contains inner: " + service.contains(outer, inner));
        
        Rectangle a1 = new Rectangle(new Point(1, 1), new Point(4, 4));
        Rectangle a2 = new Rectangle(new Point(4, 1), new Point(7, 4));

        AdjacencyType adjacency = service.classifyAdjacency(a1, a2);

        System.out.println("Adjacency:");
        System.out.printf("i1: (%d,%d) to (%d,%d)%n", a1.getLeft(), a1.getBottom(), a1.getRight(), a1.getTop());
        System.out.printf("i2: (%d,%d) to (%d,%d)%n", a2.getLeft(), a2.getBottom(), a2.getRight(), a2.getTop());
        System.out.println("Adjacency type = " + adjacency);
    }
}
