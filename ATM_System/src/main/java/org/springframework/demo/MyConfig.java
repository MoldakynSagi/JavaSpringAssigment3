package org.springframework.demo;import org.springframework.context.annotation.ComponentScan;import org.springframework.context.annotation.Configuration;import org.springframework.context.annotation.PropertySource;@Configuration@ComponentScan("org.springframework.demo")@PropertySource("/DBC.properties")public class MyConfig {}