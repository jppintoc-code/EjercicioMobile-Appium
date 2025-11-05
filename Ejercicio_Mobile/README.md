# mobile-appium-starter

Proyecto base **Java + Maven + Appium** (Android/iOS) con **JUnit 5** y **Page Object + Appium PageFactory**.

## Requisitos
- Java 17+
- Maven 3.9+
- Appium Server en ejecución (`appium` o `appium server --address 0.0.0.0 --port 4723`)
- Android SDK / Xcode según plataforma

## Variables comunes (pasadas por -D)
- `-Dplatform=android|ios`
- `-Dapp=/ruta/a/tu.apk` (o .app/.ipa)
- `-Dudid=...` (si usas dispositivo real)
- `-DdeviceName=Pixel_3` (o el que corresponda)
- `-DappiumServerUrl=http://localhost:4723`

## Ejemplos
```bash
# Android con APK
mvn -Dplatform=android -Dapp="/ruta/app-debug.apk" -DdeviceName="Pixel_6" test

# Android con app instalada
mvn -Dplatform=android -DappPackage="pe.com.interbank.mobilebanking"         -DappActivity="pe.com.interbank.mobilebanking.features.splash.SplashActivity" test

# iOS con .app en simulador
mvn -Dplatform=ios -Dapp="/ruta/MiApp.app" -DdeviceName="iPhone 15" test
```

## Convertir en arquetipo Maven
```bash
mvn clean install
mvn archetype:create-from-project
cd target/generated-sources/archetype
mvn clean install
mvn archetype:generate       -DarchetypeGroupId=com.example       -DarchetypeArtifactId=mobile-appium-starter-archetype       -DarchetypeVersion=1.0.0-SNAPSHOT       -DgroupId=com.tu.org       -DartifactId=mi-proyecto-mobile       -Dversion=1.0.0-SNAPSHOT -DinteractiveMode=false
```
