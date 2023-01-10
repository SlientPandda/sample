package com.wuhao.tips.mock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */
class StaticUtilsTest {

    @Test
    void range() {
        MockedStatic<StaticUtils> demo = Mockito.mockStatic(StaticUtils.class);
        demo.when(() -> StaticUtils.range(2, 6)).thenReturn(Arrays.asList(10, 11, 12));
    }

    @Test
    void name() {
    }
}