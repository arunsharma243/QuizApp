package com

import com.example.quizapp.testing.Utils
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PasswordTest {

    lateinit var sut:Utils
    @Before
    fun setUp(){
         sut=Utils()
    }
    @Test
    fun validatePassword_blankInput_expectedRequiredField(){
        val result=sut.validatePassword("  ")
        Assert.assertEquals("Password is required field",result)
    }

    @Test
    fun validatePassword_2CharInput_expectedValidationMsg(){

        val result=sut.validatePassword("Na")
        Assert.assertEquals("Length of the password should be greater than 6",result)
    }

    @Test
    fun validatePassword_CorrectInput_expectedValidPassword(){


        val result=sut.validatePassword("Pass123")
        Assert.assertEquals("Valid",result)
    }
}