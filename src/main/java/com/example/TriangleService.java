package com.example;

public class TriangleService {

    public enum LengthType { EQUILATERAL, ISOSCELES, SCALENE }

    public enum AngleType { RIGHT_ANGLED, ACUTE_ANGLED, OBTUSE_ANGLED }

    public boolean validateInput(String a, String b, String c) {
        return isNaturalNumber(a) && isNaturalNumber(b) && isNaturalNumber(c);
    }

    public boolean isTriangle(long a, long b, long c) {
        return a + b > c && a + c > b && b + c > a;
    }

    public LengthType classifyByLength(long a, long b, long c) {
        if (a == b && b == c) {
            return LengthType.EQUILATERAL;
        }
        if (a == b || b == c || a == c) {
            return LengthType.ISOSCELES;
        }
        return LengthType.SCALENE;
    }

    public AngleType classifyByAngle(long a, long b, long c) {
        long max = Math.max(a, Math.max(b, c));
        long sumOfOtherSquares = a * a + b * b + c * c - max * max;
        long maxSquare = max * max;
        if (maxSquare == sumOfOtherSquares) {
            return AngleType.RIGHT_ANGLED;
        }
        return maxSquare < sumOfOtherSquares ? AngleType.ACUTE_ANGLED : AngleType.OBTUSE_ANGLED;
    }

    private boolean isNaturalNumber(String value) {
        if (value == null || !value.matches("[0-9]{1,9}")) {
            return false;
        }
        return Long.parseLong(value) > 0;
    }
}
