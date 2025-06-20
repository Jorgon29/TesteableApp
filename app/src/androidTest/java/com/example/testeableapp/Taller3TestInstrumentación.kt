package com.example.testeableapp

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTouchInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.testeableapp.ui.Screens.TipCalculatorScreen
import com.example.testeableapp.ui.theme.TesteableAppTheme
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class Taller3TestInstrumentación {
    @get:Rule
    val composeRule = createComposeRule()
    @Test
    fun verificarRedondeo(){
        composeRule.setContent {
            TesteableAppTheme {
                TipCalculatorScreen()
            }
        }
        composeRule.onNodeWithText("Monto de la cuenta").performClick()
        composeRule.onNodeWithText("Monto de la cuenta").performTextInput("50")

        composeRule.onNodeWithText("Propina: $7.50").assertExists()
        composeRule.onNodeWithTag("roundCheckBox").performClick()
        composeRule.onNodeWithText("Propina: $8.00").assertExists()
    }

    @Test
    fun comprobarSlider(){
        composeRule.setContent {
            TesteableAppTheme {
                TipCalculatorScreen()
            }
        }
        composeRule.onNodeWithText("Monto de la cuenta").performClick()
        composeRule.onNodeWithText("Monto de la cuenta").performTextInput("50")

        composeRule.onNodeWithText("Propina: $7.50").assertExists()
        composeRule.onNodeWithTag("percentSlider").performClick()
        composeRule.onNodeWithTag("percentSlider").performTouchInput {
            val sliderWidth = visibleSize.width.toFloat()
            val centerY = centerY
            val startX = centerLeft.x + (sliderWidth * (0.15f)/2f)
            val endX = centerRight.x
            down(Offset(startX, centerY))
            moveTo(Offset(endX, centerY))
            up()
        }
        composeRule.onNodeWithText("Propina: $25.00").assertExists()
    }

    @Test
    fun validarPresenciaDeElementosUI(){
        composeRule.setContent {
            TesteableAppTheme {
                TipCalculatorScreen()
            }
        }
        composeRule.onNodeWithText("Calculadora de propinas")
        composeRule.onNodeWithText("Monto de la cuenta").assertExists()
        composeRule.onNodeWithText("Porcentaje de propina: 15%").assertExists()
        composeRule.onNodeWithTag("roundCheckBox").assertExists()
        composeRule.onNodeWithText("Número de personas: 1").assertExists()
        composeRule.onNodeWithText("-").assertExists()
        composeRule.onNodeWithText("+").assertExists()
        composeRule.onNodeWithText("Redondear propina").assertExists()
        composeRule.onNodeWithText("Propina: $0.00").assertExists()
        composeRule.onNodeWithText("Total por persona: $0.00").assertExists()
    }

    // extras
    @Test
    fun ingresarStringEnMonto(){
        composeRule.setContent {
            TesteableAppTheme {
                TipCalculatorScreen()
            }
        }
        composeRule.onNodeWithText("Monto de la cuenta").performClick()
        composeRule.onNodeWithText("Monto de la cuenta").performTextInput("TestableApp")

        composeRule.onNodeWithText("Propina: $0.00").assertExists()
        composeRule.onNodeWithTag("roundCheckBox").performClick()
        composeRule.onNodeWithText("Propina: $0.00").assertExists()
    }

    @Test
    fun probarCantidadNegativaPersonas(){
        composeRule.setContent {
            TesteableAppTheme {
                TipCalculatorScreen()
            }
        }
        composeRule.onNodeWithText("-").performClick()
        composeRule.onNodeWithText("Número de personas: 1").assertExists()
    }
}