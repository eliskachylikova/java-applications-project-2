package cz.mendelu.ja.project2.demo;

import cz.mendelu.ja.project2.annotations.ComponentScan;
import cz.mendelu.ja.project2.annotations.Configuration;

@Configuration
@ComponentScan("cz.mendelu.ja.project2.demo") // where to scan packages for components
public class ApplicationConfig {
}
