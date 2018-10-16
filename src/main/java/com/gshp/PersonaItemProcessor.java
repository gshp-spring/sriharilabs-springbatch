/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gshp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author usuario
 */

import org.springframework.batch.item.ItemProcessor;

public class PersonaItemProcessor implements ItemProcessor<Persona, Persona> {

    private static final Logger LOG = LoggerFactory.getLogger(PersonaItemProcessor.class);

    @Override
    public Persona process(final Persona persona) throws Exception {
        final String nombre = persona.getNombre().toUpperCase();
        final String apellido = persona.getApellido().toUpperCase();

        final Persona transformada = new Persona(nombre, apellido);

        LOG.info("Conviertiendo (" + persona + ") en (" + transformada + ")");

        return transformada;
    }

}
