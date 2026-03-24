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
        IntersectionResult intersection1 = service.computeIntersection(i1, i2);

        System.out.println("\nIntersection: ");
        System.out.printf("i1: (%d,%d) to (%d,%d)%n", i1.getLeft(), i1.getBottom(), i1.getRight(), i1.getTop());
        System.out.printf("i2: (%d,%d) to (%d,%d)%n", i2.getLeft(), i2.getBottom(), i2.getRight(), i2.getTop());
        System.out.println("Has intersection: " + intersection1.hasIntersection());
        
        if (intersection1.hasIntersection()) {
            System.out.println("Intersection points: " + intersection1.points());
        }

        Rectangle i3 = new Rectangle(new Point(1, 1), new Point(4, 4));
        Rectangle i4 = new Rectangle(new Point(4, 4), new Point(6, 8));
        IntersectionResult intersection2 = service.computeIntersection(i3, i4);

        System.out.println("\nIntersection: ");
        System.out.printf("i3: (%d,%d) to (%d,%d)%n", i3.getLeft(), i3.getBottom(), i3.getRight(), i3.getTop());
        System.out.printf("i4: (%d,%d) to (%d,%d)%n", i4.getLeft(), i4.getBottom(), i4.getRight(), i4.getTop());
        System.out.println("Has intersection: " + intersection2.hasIntersection());

        if (intersection2.hasIntersection()) {
            System.out.println("Intersection points: " + intersection1.points());
        }
        
        Rectangle outer1 = new Rectangle(new Point(1, 1), new Point(6, 6));
        Rectangle inner1 = new Rectangle(new Point(2, 2), new Point(5, 4));

        System.out.println("\nContainment:");
        System.out.printf("Outer: (%d,%d) to (%d,%d)%n", outer1.getLeft(), outer1.getBottom(), outer1.getRight(), outer1.getTop());
        System.out.printf("Inner: (%d,%d) to (%d,%d)%n", inner1.getLeft(), inner1.getBottom(), inner1.getRight(), inner1.getTop());
        System.out.println("Outer contains inner: " + service.contains(outer1, inner1) + "\n");

        Rectangle outer2 = new Rectangle(new Point(1, 1), new Point(6, 6));
        Rectangle inner2 = new Rectangle(new Point(2, 2), new Point(7, 7));

        System.out.printf("Outer: (%d,%d) to (%d,%d)%n", outer2.getLeft(), outer2.getBottom(), outer2.getRight(), outer2.getTop());
        System.out.printf("Inner: (%d,%d) to (%d,%d)%n", inner2.getLeft(), inner2.getBottom(), inner2.getRight(), inner2.getTop());
        System.out.println("Outer contains inner: " + service.contains(outer2, inner2));
        
        Rectangle a1 = new Rectangle(new Point(1, 1), new Point(4, 4));
        Rectangle a2 = new Rectangle(new Point(4, 1), new Point(7, 4));

        AdjacencyType adjacency1 = service.classifyAdjacency(a1, a2);

        System.out.println("\nAdjacency:");
        System.out.printf("a1: (%d,%d) to (%d,%d)%n", a1.getLeft(), a1.getBottom(), a1.getRight(), a1.getTop());
        System.out.printf("a2: (%d,%d) to (%d,%d)%n", a2.getLeft(), a2.getBottom(), a2.getRight(), a2.getTop());
        System.out.println("Adjacency type = " + adjacency1 + "\n");

        Rectangle a3 = new Rectangle(new Point(1, 1), new Point(4, 4));
        Rectangle a4 = new Rectangle(new Point(4, 2), new Point(6, 3));

        AdjacencyType adjacency2 = service.classifyAdjacency(a3, a4);

        System.out.printf("a3: (%d,%d) to (%d,%d)%n", a3.getLeft(), a3.getBottom(), a3.getRight(), a3.getTop());
        System.out.printf("a4: (%d,%d) to (%d,%d)%n", a4.getLeft(), a4.getBottom(), a4.getRight(), a4.getTop());
        System.out.println("Adjacency type = " + adjacency2 + "\n");


        Rectangle a5 = new Rectangle(new Point(1, 1), new Point(4, 4));
        Rectangle a6 = new Rectangle(new Point(4, 0), new Point(6, 3));

        AdjacencyType adjacency3 = service.classifyAdjacency(a5, a6);

        System.out.printf("a5: (%d,%d) to (%d,%d)%n", a5.getLeft(), a5.getBottom(), a5.getRight(), a5.getTop());
        System.out.printf("a6: (%d,%d) to (%d,%d)%n", a6.getLeft(), a6.getBottom(), a6.getRight(), a6.getTop());
        System.out.println("Adjacency type = " + adjacency3);
    }
}
