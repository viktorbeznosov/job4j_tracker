package ru.job4j.oop;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    public void when00to20then2() {
        double expected = 2;
        Point a = new Point(0, 0);
        Point b = new Point(2, 0);
        double out = a.distance(b);
        assertEquals(expected, out, 0.01);
    }

    @Test
    public void when00to00then0() {
        double expected = 0;
        Point a = new Point(0, 0);
        Point b = new Point(0, 0);
        double out = a.distance(b);
        assertEquals(expected, out, 0.01);
    }

    @Test
    public void when02to03then3dot6() {
        double expected = 3.6;
        Point a = new Point(0, 0);
        Point b = new Point(2, 3);
        double out = a.distance(b);
        assertEquals(expected, out, 0.01);
    }

    @Test
    public void when12to34then2dot828() {
        double expected = 2.828;
        Point a = new Point(1, 2);
        Point b = new Point(3, 4);
        double out = a.distance(b);
        assertEquals(expected, out, 0.01);
    }

    @Test
    void when000To100Then1() {
        double expected = 1;
        Point c = new Point(0, 0, 0);
        Point d = new Point(1, 0, 0);
        double out = c.distance3d(d);
        assertEquals(expected, out, 0.01);
    }

    @Test
    void when000To200Then2() {
        double expected = 2;
        Point c = new Point(0, 0, 0);
        Point d = new Point(2, 0, 0);
        double out = c.distance3d(d);
        assertEquals(expected, out, 0.01);
    }

    @Test
    void when000To111Then1dot732() {
        double expected = 1.732;
        Point c = new Point(0, 0, 0);
        Point d = new Point(1, 1, 1);
        double out = c.distance3d(d);
        assertEquals(expected, out, 0.01);
    }

    @Test
    void when000To222Then3dot464() {
        double expected = 3.464;
        Point c = new Point(0, 0, 0);
        Point d = new Point(2, 2, 2);
        double out = c.distance3d(d);
        assertEquals(expected, out, 0.01);
    }
}