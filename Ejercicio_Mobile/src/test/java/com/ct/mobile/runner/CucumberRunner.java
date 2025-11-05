package com.ct.mobile.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Runner BDD
 * - Ubica los .feature en resources/features
 * - Usa los step definitions en com.ct.mobile.glue y los hooks en com.ct.mobile.hooks
 * - Genera reporte HTML legible
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",            // carpeta de features
        glue = { "com.ct.mobile.glue", "com.ct.mobile.hooks" }, // step defs + hooks
        plugin = {
                "pretty",                                    // salida legible en consola
                "html:target/cucumber-report.html"           // reporte HTML
                // "json:target/cucumber.json"               // (opcional) si luego quieres integrarlo con Allure/Serenity
        },
        monochrome = true,                                   // limpia caracteres raros en consola
        snippets = CucumberOptions.SnippetType.CAMELCASE     // nombres de métodos en camelCase
        // ,tags = "@test"                                    // (opcional) descomenta si filtras por tag
)
public class CucumberRunner { }
