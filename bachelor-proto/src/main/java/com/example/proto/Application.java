
package com.example.proto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // Debug: Zeige Datenbank-Konfiguration
        System.out.println("=== DATABASE CONFIG ===");
        System.out.println("PGHOST: " + System.getenv("PGHOST"));
        System.out.println("PGPORT: " + System.getenv("PGPORT"));
        System.out.println("PGDATABASE: " + System.getenv("PGDATABASE"));
        System.out.println("PGUSER: " + System.getenv("PGUSER"));
        System.out.println("======================");

        SpringApplication.run(Application.class, args);
    }
}