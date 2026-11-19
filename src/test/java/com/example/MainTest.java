package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class MainTest {

    private static final String LINE = System.lineSeparator();
    private static final String PROMPT = "Enter three positive integers:" + LINE;

    @ParameterizedTest
    @CsvSource({
            "'3 4 5', 'SCALENE', 'RIGHT_ANGLED'",
            "'3\n4\n5', 'SCALENE', 'RIGHT_ANGLED'",
            "'4 4 5', 'ISOSCELES', 'ACUTE_ANGLED'",
            "'3 3 3', 'EQUILATERAL', 'ACUTE_ANGLED'",
            "'2 3 4', 'SCALENE', 'OBTUSE_ANGLED'"
    })
    void whenRun_givenTriangleSides_thenPrintsClassification(String input, String lengthType, String angleType) {
        //given
        InputStream in = toStream(input);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        String expected = PROMPT + "By length: " + lengthType + LINE + "By angle: " + angleType + LINE;

        //when
        Main.run(in, toPrintStream(buffer));
        String actual = buffer.toString(StandardCharsets.UTF_8);

        //then
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1 2 3", "1 1 5"})
    void whenRun_givenSidesNotFormingTriangle_thenPrintsNotATriangle(String input) {
        //given
        InputStream in = toStream(input);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        String expected = PROMPT + "Not a triangle" + LINE;

        //when
        Main.run(in, toPrintStream(buffer));
        String actual = buffer.toString(StandardCharsets.UTF_8);

        //then
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a b c", "0 4 5", "-3 4 5", "3 4", "3", "", "1.5 2 3"})
    void whenRun_givenInvalidInput_thenPrintsInvalidInput(String input) {
        //given
        InputStream in = toStream(input);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        String expected = PROMPT + "Invalid input" + LINE;

        //when
        Main.run(in, toPrintStream(buffer));
        String actual = buffer.toString(StandardCharsets.UTF_8);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void whenMain_givenRightTriangleOnSystemIn_thenPrintsClassificationToSystemOut() {
        //given
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        String expected = PROMPT + "By length: SCALENE" + LINE + "By angle: RIGHT_ANGLED" + LINE;
        System.setIn(toStream("3 4 5"));
        System.setOut(toPrintStream(buffer));

        //when
        String actual;
        try {
            Main.main(new String[0]);
            actual = buffer.toString(StandardCharsets.UTF_8);
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }

        //then
        assertEquals(expected, actual);
    }

    private InputStream toStream(String input) {
        return new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
    }

    private PrintStream toPrintStream(ByteArrayOutputStream buffer) {
        return new PrintStream(buffer, true, StandardCharsets.UTF_8);
    }
}
