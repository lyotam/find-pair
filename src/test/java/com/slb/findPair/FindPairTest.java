package com.slb.findPair;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FindPairTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private final String path = setupPath("prices.txt");

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @ParameterizedTest
    @MethodSource("validBalancesPairs")
    @DisplayName("Given a file with and a pair with possible balance sum, the pair is printed")
    void test_pair_exists(int balance, String expected) throws IOException {
        FindPair.findPair(path, balance);
        assertEquals(expected, outContent.toString());
    }

    @ParameterizedTest
    @MethodSource("invalidBalancesPairs")
    @DisplayName("Given a file with and a pair with impossible balance sum, a Not possible message is printed")
    void test_pair_doesnt_exist(int balance, String expected) throws IOException {
        FindPair.findPair(path, balance);
        assertEquals(expected, outContent.toString());
    }

    private static Stream<Arguments> validBalancesPairs() {
        return Stream.of(
                Arguments.of(2500, "Candy Bar 500, Earmuffs 2000"),
                Arguments.of(2300, "Paperback Book 700, Headphones 1400"),
                Arguments.of(10000, "Earmuffs 2000, Bluetooth Stereo 6000")
        );
    }

    private static Stream<Arguments> invalidBalancesPairs() {
        return Stream.of(
                Arguments.of(1100, "Not possible"),
                Arguments.of(50, "Not possible")
        );
    }

    private String setupPath(String filename) {
        try {
            URL url = getClass().getClassLoader().getResource(filename);
            return Paths.get(url.toURI()).toFile().getAbsolutePath();
        } catch (URISyntaxException e) {
            throw new RuntimeException();
        }
    }
}