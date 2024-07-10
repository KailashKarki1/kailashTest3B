package com.example.test3bkailash;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloControllerTest {
    @Test
    void testCalculateTotalBill() {
        assertEquals(2050, HelloController.calculateTotalBill("XL", 2));
    }
    }

