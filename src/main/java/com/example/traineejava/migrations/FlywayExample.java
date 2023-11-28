package com.example.traineejava.migrations;
import org.flywaydb.core.Flyway;

public class FlywayExample {
    public static void main(String[] args) {
        // Создание экземпляра Flyway
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:mysql://localhost:3306/innowise", "root", "12345")
                .locations("/db/migration")
                .load();

        // Запуск миграций
        flyway.migrate();
    }
}