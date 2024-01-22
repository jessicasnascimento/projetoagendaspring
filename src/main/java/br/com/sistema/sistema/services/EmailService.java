package br.com.sistema.sistema.services;

import br.com.sistema.sistema.dtos.AgendamentoDTO;
import br.com.sistema.sistema.dtos.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void enviarEmail(EmailDTO email) {
        try {
            SimpleMailMessage mensagem = new SimpleMailMessage();
            mensagem.setTo(email.getDestinatario());
            mensagem.setSubject(email.getAssunto());
            mensagem.setText(email.getCorpo());

            javaMailSender.send(mensagem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}