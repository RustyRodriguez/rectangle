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
- Intersection is defined as overlapping area only (shared edges or points are not considered intersections)

## Run Tests

```bash
./mvnw test
```

## Build

```bash
./mvnw package
```