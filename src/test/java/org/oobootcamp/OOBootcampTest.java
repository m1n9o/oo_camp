package org.oobootcamp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OOBootcampTest {

    @Test
    void should_welcome_to_oo_bootcamp() {
        assertThat(new OOBootcamp("Hello, Welcome to OOBootcamp").message()).isEqualTo("Hello, Welcome to OOBootcamp");
    }

}
