[![Build Status](https://travis-ci.org/dsokolov/juggler.svg?branch=master)](https://travis-ci.org/dsokolov/juggler)
[ ![Download](https://api.bintray.com/packages/dsokolov/maven/juggler/images/download.svg) ](https://bintray.com/dsokolov/maven/juggler/_latestVersion)

# Juggler

## What is it?

Juggler is Android framework represents navigation as a state machine.

## How to use it?

1. Add dependency.

In your root build.gradle

allprojects {
    repositories {
        ...
        maven {
            url 'https://dl.bintray.com/dsokolov/maven'
        }
    }
}

In your project build.gradle (usaly app/build.gralde or mobile/build.gralde):

dependencies {
    ...
    compile 'me.ilich:juggler:0.0.16'
}



## Known issues

переход на активити из PendingIntent

в бекстеке накапливаются транзакции

феллбек для методов dig