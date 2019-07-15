package com.ljq.tool.test;
import com.ljq.tool.HelloWorld;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by creasypita on 7/15/2019.
 */
public class HelloWorldTest {
    @Test
    public void SayHello_ReturnHello()
    {
        HelloWorld helloWorld = new HelloWorld();
        String result = helloWorld.SayHello();

        assertEquals(result, "hello java !");
    }
}
