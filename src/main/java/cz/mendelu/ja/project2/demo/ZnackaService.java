package cz.mendelu.ja.project2.demo;

import cz.mendelu.ja.project2.annotations.Component;

@Component
public class ZnackaService {

    public boolean isValid(String znacka) {
        return "Honda".equals(znacka);
    }
}
