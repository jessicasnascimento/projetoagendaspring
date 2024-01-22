package br.com.sistema.sistema.dtos;

import lombok.Data;

@Data
public class EmailDTO {

    private String destinatario;
    private String assunto;
    private String corpo;

}
