package com

import com.example.quizapp.testing.Utils
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ReverseTest {
    lateinit var sut:Utils

    @Before
    fun setUp(){
        sut=Utils()
    }

    @Test
    fun  testStringReversal_EmptyString_expectedEmptyString(){
        val result=sut.reverseString("")
        Assert.assertEquals("",result)
    }
    @Test
    fun  testStringReversal_1char_input_expectedSameChar(){
        val result=sut.reverseString("a")
        Assert.assertEquals("a",result)
    }
    @Test
    fun  testStringReversal_ValidString_expectedCorrectAnswer(){
        val result=sut.reverseString("arun")
        Assert.assertEquals("nura",result)
    }
}