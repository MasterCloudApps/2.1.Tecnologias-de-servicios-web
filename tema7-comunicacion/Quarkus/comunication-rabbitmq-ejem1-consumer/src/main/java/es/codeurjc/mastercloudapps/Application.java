package es.codeurjc.mastercloudapps;

import io.quarkus.runtime.annotations.QuarkusMain;
import io.quarkus.runtime.Quarkus;

@QuarkusMain  
public class Application {

    public static void main(String ... args) {
        Quarkus.run(args); 
    }
}