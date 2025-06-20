package com.example.testeableapp

import com.example.testeableapp.ui.Screens.calculateTip
import com.example.testeableapp.ui.Screens.totalPerPerson
import org.junit.Test

import org.junit.Assert.*
import kotlin.math.ceil
import kotlin.math.pow

class TipCalculatorTests {

    @Test
    fun Calculo37DePropinaRedondeo(){
        val porcentaje: Int = 37;
        val redondeo: Boolean = true;
        val monto: Double = 50.0;
        val resultadoEsperado: Double = ceil(monto * ( porcentaje / 100.0 ));
        val resultadoReal: Double = calculateTip(
            amount = monto,
            tipPercent = porcentaje,
            roundUp = redondeo
        );
        assertEquals(resultadoEsperado,resultadoReal, 10.0.pow(-20));
    }

    @Test
    fun CalculoCantidadNegativa(){
        val porcentaje: Int = 37;
        val redondeo: Boolean = true;
        val monto: Double = -50.0;
        val resultadoEsperado: Double = 0.0;
        val resultadoReal: Double = calculateTip(
            amount = monto,
            tipPercent = porcentaje,
            roundUp = redondeo
        );
        assertEquals(resultadoEsperado,resultadoReal, 10.0.pow(-20));
    }

    @Test
    fun CalculoPorPersona(){
        val porcentaje: Int = 37;
        val redondeo: Boolean = true;
        val monto: Double = 50.0;
        val personas: Int = 5;
        val propina: Double = ceil(monto * ( porcentaje / 100.0 ));
        val resultadoEsperado: Double = (propina + monto) / personas.toDouble()
        val propinaCalculada = calculateTip(monto, porcentaje, redondeo)
        val resultadoReal = totalPerPerson(personas, monto, propinaCalculada)
        assertEquals(resultadoEsperado,resultadoReal, 10.0.pow(-20));
    }

    // extras
    @Test
    fun CalculoPorcentajeNegativo(){
        val porcentaje: Int = -37;
        val redondeo: Boolean = true;
        val monto: Double = 50.0;
        val resultadoEsperado: Double = 0.0
        val resultadoReal: Double = calculateTip(
            amount = monto,
            tipPercent = porcentaje,
            roundUp = redondeo
        );
        assertEquals(resultadoEsperado,resultadoReal, 10.0.pow(-20))
    }

    @Test
    fun CalculoPorPersona0(){
        val porcentaje: Int = 37;
        val redondeo: Boolean = true;
        val monto: Double = 50.0;
        val personas: Int = 0;
        val propina: Double = ceil(monto * ( porcentaje / 100.0 ));
        val resultadoEsperado: Double = 0.0
        val propinaCalculada = calculateTip(monto, porcentaje, redondeo)
        val resultadoReal = totalPerPerson(personas, monto, propinaCalculada)
        assertEquals(resultadoEsperado,resultadoReal, 10.0.pow(-20));
    }

}