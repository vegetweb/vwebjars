# vwebjars  - a proof of concept for java script loading in vaadin
## The problem
Using java script libraries like jquery in vaadin add-ons fast leat to version conflicts, since every add-on
has to ship its on version of jquery.

This is a proof of concept to solve this conflicts. The idea is to use [webjars](http://www.webjars.org) as dependencies, reference them in the add-ons and let maven solve the versions for you. 

## The idea
Based on the [virtin webjars branch](https://vaadin.com/web/matti/blog/-/blogs/using-webjars-with-vaadin) from 
Matti Tahvonen, some talk at the [vaadin meetup in Hamburg](http://www.meetup.com/de-DE/Vaadin-Germany/events/231100895/) and ideas from the [vaadin forum](https://vaadin.com/forum#!/thread/3390058/13445154) the following things have been done:
   * ~~Pack the java script like a webjar, but without version information in the path to the *.js file~~
   * Add a dependency to the ~~vwebjar~~ webjar in the addon
   * Use this in a Vaadin @JavaScript annotation, but with the `app://webjars` protocol and prefix
   * Serve the javascirpt with a thin servlet registered to the `webjars` path

## The contents of this project
   * ~~`jquery` - the packed jquery version - the pom.xml is copyed from the webjars project, and modified so there is no version information in the filename~~
   * `sticky` - a patched version of the [Sticky](https://vaadin.com/directory#!addon/sticky) Vaadin add-on using the ~~packed jquery library~~ webjars jquery library 
   * `vwebjars-servlet` - the servlet serving the packed java script resources,~~this is a dependency to the java script packages~~
   
## How to try this out
Run `mvn install` in the parent module, and then `mvn jetty:run` in the `sticky/sticky-demo` sub module. 

### How to use this in your projects / add-ons?
   1. Add the needed webjar as a dependency to your add-on
   2. Use the `@JavaScript` annotation in your addon. The script name must be `app://webjars/scriptFileName` or `app://webjars/webjarName/scriptFileName`.
   3. Use the `vwebjars-servlet` in your procejt to serve the webjars
   
## Ideas for improvement
   * ~~Is there a way to use the original webjar *.jar, so the repacking effort is not needed?~~