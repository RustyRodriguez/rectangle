# Rectangle Analyzer

A Java application that analyzes relationships between rectangles, including containment, adjacency classification, and intersection detection.

## Prerequisites

- Java 21
- Maven 3.9+ (or use the maven wrapper)

## Features

- Determine whether one rectangle contains another
- Classify adjacency between rectangles (PROPER, PARTIAL, SUBLINE, NONE)
- Compute intersection region for overlapping rectangles

## Assumptions

- Rectangles are defined by two points with non-zero width and height
- Containment is inclusive (identical rectangles are considered contained)
- Adjacency requires a shared boundary with positive length
- Intersection is defined as the overlapping area between two rectangles. The four returned points are the corners of that overlap region. Shared edges or corners alone are not considered intersections.

## Run Tests

```bash
./mvnw test
```

## Build

```bash
./mvnw package
```

## Code Coverage

Run tests and generate a Jacoco coverage report

```bash
./mvnw test
```

Open the report in a browser at the following location

```
target/site/jacoco/index.html
```

## Visual Examples of Adjacency, Containment, Intersection

### Adjacency (Proper)
![adjacency_proper.png](adjacency_proper.png)

### Containment
![containment.png](containment.png)

### Intersection
![intersection.png](intersection.png)