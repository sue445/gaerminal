# GAErminal(GAE + terminal)
* master: [![Build Status](https://travis-ci.org/sue445/gaerminal.png?branch=master)](https://travis-ci.org/sue445/gaerminal)
* develop: [![Build Status](https://travis-ci.org/sue445/gaerminal.png?branch=develop)](https://travis-ci.org/sue445/gaerminal)

## Overview
run groovy script on your [Google App Engine](https://developers.google.com/appengine/) app
(like as Jenkins script console)

## Sample
![gaerminal](http://cdn-ak.f.st-hatena.com/images/fotolife/s/sue445/20130420/20130420175006_original.png)

## Install
### 1. add jar in your classpath
```sh
cp /path/to/app
cp gaerminal.x.x.x.jar ./war/WEB-INF/lib
cp groovy.x.x.x.jar ./war/WEB-INF/lib
```
If you use maven, add to `pom.xml`

```xml
<dependency>
    <groupId>net.sue445</groupId>
    <artifactId>gaerminal</artifactId>
    <version>0.0.2</version>
</dependency>
```

### 2. edit `web.xml`
example

https://github.com/sue445/gaerminal/blob/master/web/WEB-INF/web.xml

if you forget `<security-constraint>` section, anyone can access gaerminal !

### 3. run app and access
```sh
open http://localhost:8888/gaerminal/
```

## Commands
### Setup
```sh
cp gradle.properties.sample gradle.properties
vi gradle.properties
gradle idea
```

### Run stand-alone
```sh
gradle gaeRun
open http://localhost:8888/gaerminal/
```
