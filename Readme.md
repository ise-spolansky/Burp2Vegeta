# Burp2Vegeta

This [Burp](https://portswigger.net/burp) plugin adds a context menu entry for copying the selected request(s) as [Vegeta](https://github.com/tsenart/vegeta) load tester attack definitions.

![Screenshot showing the "Copy as Vegeta Attack Definition" context menu entry](img/screenshot.png)

You can then paste them into a file or pipe them into Vegeta directly for use load testing.

## Usage

Download the release .jar, add it to Burp under the Extender tab, then right click on any request(s) and click the menu item to copy the corresponding Vegeta attack definition.

## Building

This project depends on Google GSon and uses Maven for building. Clone the respository, then use `mvn clean compile assembly:single` to build. 

This project was created using my [Burp Extension Maven Archetype](https://github.com/ise-spolansky/burp-extension-maven-archetype); check it out if you want a fast way to develop your own extensions!
