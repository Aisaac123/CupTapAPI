package com.upc.cuptap_restapi.Models.Interfaces.Entities;

import com.upc.cuptap_restapi.Models.Utils.NoUpdate;
import jakarta.persistence.Id;

import java.lang.reflect.Field;

public interface UpdateEntity<E> extends Cloneable, Entity {
    E clone();


    // Uso de AOP Para actualizar los campos de una clase excluyendo los campos con @Id
    // Documentacion by TubbsitoAisaac el mas insano de la region.
    default <T extends Entity> void update(T newObject) throws IllegalAccessException {
        Class<?> clazz = this.getClass();

        // Obtiene los campos de la clase
        Field[] fields = clazz.getDeclaredFields();

        // Itera con respecto a los campos de la clase
        for (Field field : fields) {
            // Permite el acceso a los campos
            field.setAccessible(true);

            // Si el campo está anotado con @Id o @NoUpdate, omite su actualizacion
            if (!field.isAnnotationPresent(Id.class) || !field.isAnnotationPresent(NoUpdate.class)) continue;

            // Obtener el campo con el valor del nuevo objeto
            Object newValue = field.get(newObject);

            // Establecer el campo al campo del nuevo objeto
            field.set(this, newValue);
        }
    }
}
