package com.fiebtcc.barbersclub.barbersclub;

import com.fiebtcc.barbersclub.barbersclub.model.Servicos;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BarbersclubApplication {
	public static void main(String[] args) {
	SpringApplication.run(BarbersclubApplication.class, args);
	Servicos s1 = new Servicos();
	s1.setNome("");
	if (!s1.validarServicos()){
		System.out.println(s1.getMensagemErro());
	}

	}

}
