package com.example;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        run(System.in, System.out);
    }

    static void run(InputStream in, PrintStream out) {
        TriangleService service = new TriangleService();
        Scanner scanner = new Scanner(in);

        out.println("Enter three positive integers:");
        String a = scanner.hasNext() ? scanner.next() : null;
        String b = scanner.hasNext() ? scanner.next() : null;
        String c = scanner.hasNext() ? scanner.next() : null;

        if (!service.validateInput(a, b, c)) {
            out.println("Invalid input");
            return;
        }

        long x = Long.parseLong(a);
        long y = Long.parseLong(b);
        long z = Long.parseLong(c);

        if (!service.isTriangle(x, y, z)) {
            out.println("Not a triangle");
            return;
        }

        out.println("By length: " + service.classifyByLength(x, y, z));
        out.println("By angle: " + service.classifyByAngle(x, y, z));
    }
}
