package com.upc.cuptap_restapi.Models.Interfaces;

import java.lang.reflect.Field;

public interface UpdateEntity extends Cloneable, Entity {
    UpdateEntity cloneEntity();

    default <T> void update(T newObject) throws IllegalAccessException {
        Class<?> clazz = this.getClass();

        // Obtener todos los campos de la clase
        Field[] fields = clazz.getDeclaredFields();

        // Iterar sobre los campos y establecer los valores del objeto original
        for (Field field : fields) {
            // Permitir el acceso a campos privados
            field.setAccessible(true);

            // Obtener el valor del campo del nuevo objeto
            Object newValue = field.get(newObject);

            // Establecer el valor del campo en el objeto original
            field.set(this, newValue);
        }
    }
}
