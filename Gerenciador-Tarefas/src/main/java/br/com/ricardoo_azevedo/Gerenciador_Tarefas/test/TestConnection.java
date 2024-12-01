package br.com.ricardoo_azevedo.Gerenciador_Tarefas.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class TestConnection implements CommandLineRunner{

    @Autowired
    private JdbcTemplate jdbctemplete;


    @Override
    public void run(String... args) throws Exception {
        jdbctemplete.execute("SELECT 1");
        System.out.println("Conexão bem sucedida!");
    }
    
}
