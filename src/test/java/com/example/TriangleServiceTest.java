package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.TriangleService.AngleType;
import com.example.TriangleService.LengthType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class TriangleServiceTest {

    private final TriangleService service = new TriangleService();

    @Test
    void whenValidateInput_givenNaturalNumbers_thenReturnsTrue() {
        //given
        String a = "3";
        String b = "4";
        String c = "999999999";
        boolean expected = true;

        //when
        boolean actual = service.validateInput(a, b, c);

        //then
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-1", "abc", "1.5", "", " ", "1e3", "+5", "1000000000", "99999999999999999999"})
    void whenValidateInput_givenInvalidFirstValue_thenReturnsFalse(String invalid) {
        //given
        String b = "4";
        String c = "5";
        boolean expected = false;

        //when
        boolean actual = service.validateInput(invalid, b, c);

        //then
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-1", "x"})
    void whenValidateInput_givenInvalidSecondValue_thenReturnsFalse(String invalid) {
        //given
        String a = "3";
        String c = "5";
        boolean expected = false;

        //when
        boolean actual = service.validateInput(a, invalid, c);

        //then
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-1", "x"})
    void whenValidateInput_givenInvalidThirdValue_thenReturnsFalse(String invalid) {
        //given
        String a = "3";
        String b = "4";
        boolean expected = false;

        //when
        boolean actual = service.validateInput(a, b, invalid);

        //then
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @NullSource
    void whenValidateInput_givenNullValue_thenReturnsFalse(String nullValue) {
        //given
        boolean expected = false;

        //when
        boolean actualFirst = service.validateInput(nullValue, "4", "5");
        boolean actualSecond = service.validateInput("3", nullValue, "5");
        boolean actualThird = service.validateInput("3", "4", nullValue);

        //then
        assertEquals(expected, actualFirst);
        assertEquals(expected, actualSecond);
        assertEquals(expected, actualThird);
    }

    @ParameterizedTest
    @CsvSource({"3,4,5", "2,2,3", "1,1,1", "5,4,3", "3,5,4", "999999999,999999999,999999999"})
    void whenIsTriangle_givenSidesFormTriangle_thenReturnsTrue(long a, long b, long c) {
        //given
        boolean expected = true;

        //when
        boolean actual = service.isTriangle(a, b, c);

        //then
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({"1,2,3", "3,2,1", "1,3,2", "1,1,3", "10,2,3", "2,10,3", "2,3,10"})
    void whenIsTriangle_givenSidesDoNotFormTriangle_thenReturnsFalse(long a, long b, long c) {
        //given
        boolean expected = false;

        //when
        boolean actual = service.isTriangle(a, b, c);

        //then
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({"3,3,3", "1,1,1"})
    void whenClassifyByLength_givenAllSidesEqual_thenReturnsEquilateral(long a, long b, long c) {
        //given
        LengthType expected = LengthType.EQUILATERAL;

        //when
        LengthType actual = service.classifyByLength(a, b, c);

        //then
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({"3,3,4", "3,4,3", "4,3,3"})
    void whenClassifyByLength_givenTwoSidesEqual_thenReturnsIsosceles(long a, long b, long c) {
        //given
        LengthType expected = LengthType.ISOSCELES;

        //when
        LengthType actual = service.classifyByLength(a, b, c);

        //then
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({"3,4,5", "5,4,3"})
    void whenClassifyByLength_givenAllSidesDifferent_thenReturnsScalene(long a, long b, long c) {
        //given
        LengthType expected = LengthType.SCALENE;

        //when
        LengthType actual = service.classifyByLength(a, b, c);

        //then
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({"3,4,5", "5,3,4", "4,5,3", "5,12,13"})
    void whenClassifyByAngle_givenSquaresSumEqualsHypotenuseSquare_thenReturnsRightAngled(long a, long b, long c) {
        //given
        AngleType expected = AngleType.RIGHT_ANGLED;

        //when
        AngleType actual = service.classifyByAngle(a, b, c);

        //then
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({"3,3,3", "4,5,6", "6,5,4", "5,6,4", "4,4,5"})
    void whenClassifyByAngle_givenLargestSquareLessThanSumOfOthers_thenReturnsAcuteAngled(long a, long b, long c) {
        //given
        AngleType expected = AngleType.ACUTE_ANGLED;

        //when
        AngleType actual = service.classifyByAngle(a, b, c);

        //then
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({"2,2,3", "2,3,4", "4,3,2", "3,4,2", "999999999,999999999,1999999997"})
    void whenClassifyByAngle_givenLargestSquareGreaterThanSumOfOthers_thenReturnsObtuseAngled(long a, long b, long c) {
        //given
        AngleType expected = AngleType.OBTUSE_ANGLED;

        //when
        AngleType actual = service.classifyByAngle(a, b, c);

        //then
        assertEquals(expected, actual);
    }
}
