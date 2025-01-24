# compose-app-template 🤖

[![Use this template](https://img.shields.io/badge/from-compose--app--template-brightgreen?logo=dropbox)](https://github.com/rhenwinch/compose-app-template/generate) ![Pre Merge Checks](https://github.com/rhenwinch/compose-app-template/workflows/Pre%20Merge%20Checks/badge.svg) ![License](https://img.shields.io/github/license/rhenwinch/compose-app-template.svg) ![Language](https://img.shields.io/github/languages/top/rhenwinch/compose-app-template?color=blue&logo=kotlin)

A multi-module compose app Github template that lets you create an **Android/Kotlin** project and be up and running in a **few seconds**.

This template is focused on delivering a project with **static analysis** and **continuous integration** already in place.

## How to use 👣

Just click on [![Use this template](https://img.shields.io/badge/-Use%20this%20template-brightgreen)](https://github.com/rhenwinch/compose-app-template/generate) button to create a new repo starting from this template.

## Features 🎨

- **100% Kotlin-only template**.
- 4 Sample modules (Android app, Android library, Kotlin library, Jetpack Compose Activity).
- Jetpack Compose setup ready to use.
- Sample Espresso, Instrumentation & JUnit tests.
- 100% Gradle Kotlin DSL setup.
- CI Setup with GitHub Actions.
- Publish to **Maven Central** with Github Actions.
- Dependency versions managed via `build-logic`.
- Kotlin Static Analysis via `detekt` and `ktlint`.
- Issues Template (bug report + feature request).
- Pull Request Template.

## Troubleshooting

For help with issues which you might encounter when using this template, please refer to [TROUBLESHOOTING.md](TROUBLESHOOTING.md)

## Gradle Setup 🐘

This template is using [**Gradle Kotlin DSL**](https://docs.gradle.org/current/userguide/kotlin_dsl.html) as well as the [Plugin DSL](https://docs.gradle.org/current/userguide/plugins.html#sec:plugins_block) to setup the build.

Dependencies are centralized inside the Gradle Version Catalog in the [libs.versions.toml](gradle/libs.versions.toml) file in the `gradle` folder.

## Static Analysis 🔍

This template is using [**detekt**](https://github.com/detekt/detekt) to analyze the source code, with the configuration that is stored in the [detekt.yml](config/detekt/detekt.yml) file (the file has been generated with the `detektGenerateConfig` task). It also uses the **detekt-formatting** plugin which includes the ktlint rules (see https://detekt.dev/docs/rules/formatting/).

## CI ⚙️

This template is using [**GitHub Actions**](https://github.com/rhenwinch/compose-app-template/actions) as CI. You don't need to setup any external service and you should have a running CI once you start using this template, just make sure that you turn on the "Read and Write permissions" on the Action Settings of your repository.

There are currently the following workflows available:
- [Builds APK and Creates a Release](.github/workflows/apk_release_ci.yaml) - Assembled a release APK and creates a GitHub release

## Contributing 🤝

Feel free to open a issue or submit a pull request for any bugs/improvements.

---

Original codebase is from [cortinico/kotlin-android-template](https://github.com/cortinico/kotlin-android-template)
