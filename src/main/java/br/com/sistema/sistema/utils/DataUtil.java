package br.com.sistema.sistema.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class DataUtil {

    public static LocalDate stringToLocalDate(String data) {
        if (data != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(data, formatter);
        } else {
            throw new RuntimeException("Data não fornecida.");
        }
    }

    public static int calcularIdadeStr(String dataNascimento) {
        if (dataNascimento != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            // Faça o parsing da string para um LocalDate
            LocalDate data = LocalDate.parse(dataNascimento, formatter);
            LocalDate hoje = LocalDate.now();
            return Period.between(data, hoje).getYears();
        } else {
            throw new RuntimeException("Data de nascimento não fornecida.");
        }
    }

    public static int calcularIdade(LocalDate dataNascimento) {
        if (dataNascimento != null) {
            LocalDate hoje = LocalDate.now();
            return Period.between(dataNascimento, hoje).getYears();
        } else {
            throw new RuntimeException("Data de nascimento não fornecida.");
        }
    }

}
