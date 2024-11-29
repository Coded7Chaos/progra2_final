package com.transporte.exceptions;


public class FieldValidator
{
    public static void validateTextField(String fieldName, String value) throws EmptyFieldException
    {
        if (value == null || value.trim().isEmpty())
        {
            throw new EmptyFieldException("El campo '" + fieldName + "' no puede estar vacío.");
        }
    }
    public static void validateField(String value) throws EmptyFieldException
    {
        String fieldName = "zona";
        if (value =="Seleccionar zona")
        {
            throw new EmptyFieldException("Debe seleccionar una '" + fieldName + "' de manera obligatoria.");
        }
    }
}