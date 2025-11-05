package com.ct.mobile.config;

import java.io.InputStream;
import java.util.Properties;

public class Config {
    private final Properties properties = new Properties();

    public Config(String plataforma) {
        // Selecciona el archivo correcto
        String file = "/config/" + ("ios".equalsIgnoreCase(plataforma) ? "ios.properties" : "android.properties");
        try (InputStream is = getClass().getResourceAsStream(file)) {
            if (is == null) {
                throw new IllegalStateException("No se encontró el archivo de configuración: " + file);
            }
            properties.load(is);
        } catch (Exception e) {
            throw new RuntimeException("Error cargando configuración", e);
        }

        // Añade propiedades del sistema (por si se pasan por línea de comando)
        properties.putAll(System.getProperties());
    }

    // Devuelve una propiedad específica
    public String get(String key) {
        return properties.getProperty(key);
    }

    // Devuelve todas las propiedades cargadas
    public Properties getAll() {
        return properties;
    }
}
