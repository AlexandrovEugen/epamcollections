package com.epam.java.se;


import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class CustomHashMapTest {

    CustomHashMap<Integer,String> hashMap;

    @Before
    public void init(){
        hashMap = new CustomHashMap<Integer, String>();
    }

    @Test
    public void testThatThisClassCanInitializeObject(){
        assertThat(hashMap, is(notNullValue()));
    }



}