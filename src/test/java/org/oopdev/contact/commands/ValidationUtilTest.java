package org.oopdev.contact.commands;

import org.junit.Test;
import org.oopdev.contact.util.Utility;
import org.oopdev.contact.util.ValidationUtil;

import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
/**
 * Created by kamilbukum on 24/10/15.
 */
public class ValidationUtilTest {

    @Test
    public void parseCommandTest(){

        List<String> expected = Arrays.asList("-add", "example.xml", "example 2.xml");
        List<String> commands= Utility.parseCommand("-add example.xml \"example 2.xml\"");

        assertThat(commands, is(expected));
    }
}
