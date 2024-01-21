package com.estudarecompensa.ativityprovider.entities.ConfigManager;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_ativity_instance")
public class DeployActivity {
    
    private static final long serialVersionUID = 1L;


    // Esta classe vai estar associada à tabela da base de dados responsavel por
    // armazenar todos os deploys de uma atividade
    // Sempre que existir um novo deploy, vai ser guardado o id desse deploy na base de dados
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long instance_id;
    private String intance_ativity;

    public DeployActivity() {
       
    }
    
    public DeployActivity(String intance_ativity) {
        this.intance_ativity = intance_ativity;
    }

    public String getintance_ativity() {
        return intance_ativity;
    }

    public void setintance_ativity(String intance_ativity) {
        this.intance_ativity = intance_ativity;
    }

    @Override
    public String toString() {
        return "DeployActivity [id=" + instance_id + ", intance_ativity=" + intance_ativity + "]";
    }

    
}
