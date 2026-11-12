# GX BloxTrap Mobile

Android companion app com interface Black Premium.

## Requisitos de build
- Android 11+ (minSdk 30)
- JDK 17
- Android SDK 35
- Android Gradle Plugin 8.7.3
- Gradle 8.9

## Recursos
- Launcher: abre o Roblox oficial se estiver instalado.
- Personalizador: salva localmente um perfil visual.
- Otimizador: apresenta recomendações gerais seguras.
- FastFlags: explica as limitações no Android.

## Limites
O app não modifica arquivos privados do Roblox, não injeta código e não promete aplicar FastFlags. O Android restringe o acesso de apps externos ao armazenamento privado e à memória de outros aplicativos.

## Compilar APK pelo celular com GitHub
1. Baixe este ZIP e extraia os arquivos.
2. Crie um repositório vazio no GitHub chamado `GX-BloxTrap`.
3. Envie o conteúdo inteiro da pasta extraída para a raiz do repositório.
4. IMPORTANTE: inclua a pasta oculta `.github`. Se o gerenciador de arquivos esconder arquivos ocultos, ative “Mostrar arquivos ocultos”.
5. No GitHub, confirme que aparece `.github/workflows/build-apk.yml`.
6. Abra Actions. Se solicitado, habilite workflows.
7. Execute `Build GX BloxTrap APK` usando Run workflow.
8. Quando a execução ficar verde, abra a execução e baixe o artefato `GX-BloxTrap-APK`.
9. Extraia o ZIP do artefato e instale `app-debug.apk`.

## Se o workflow não aparecer
- Confirme que `.github/workflows/build-apk.yml` está na branch padrão.
- Confirme que não ficou dentro de uma pasta extra (por exemplo `GXBloxTrapMobile-FINAL/.github/...`).
- Verifique Settings > Actions > General e permita Actions no repositório.

## Verificação
O pacote foi verificado quanto à presença dos arquivos obrigatórios, integridade do ZIP, XML válido do Manifest e presença do workflow. A compilação real do Android precisa ser executada no GitHub Actions; este ambiente não tem Android SDK configurado, então não afirmamos que o APK já foi compilado.
