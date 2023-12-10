package com.estudarecompensa.ativityprovider.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Esta vai ser a classe singleton, pois, independentemente do número de 
// criações desta atividade, os parametros de configuração da atividade
// serão sempre os mesmos, logo faz sentido termos uma instancia única que,
// carrega esses parametros da base de dados uma unica vez (na criação da 1º atiividade)
// e as restantes atividades que poderão ser configuradas, utilizarão a mesma 
// instancia do objecto, que já tinha sido criado, com os dados nele armazenado

@Entity
@Table(name = "tb_config_params")
public class ConfigParameters{

    private static final long serialVersionUID = 1L;

    private static ConfigParameters instance;
    private static List<Map<String, String>> configList;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String attribute;
    private String type;

    
    private ConfigParameters()
    {
        configList = new ArrayList<Map<String, String>>();
        this.attribute = "";
        this.type = "";
    }

    // Para implementar o metodo que controla e retorna o objecto da classe, 
    // é utilizado o synchronized para ser thread-safe
    // é verificado antes se existe já algum objecto criado, se houver devolve-o 
    // caso contrario é que é feito o synchronized e criado um novo objecto (caso não exista), 
    // é uma abordagem com melhor desempenho pois o synchronized tem um custo grande 
    // em questões de desempenho

    // A abordagem adoptada aqui é chamada de bloqueio de verificação dupla(DLC). 
    // Isto existe para evitar condições de corrida entre multiplos threads que podem
    // tentar obter uma instancia Singleton ao mesmo tempo, criando uma instancia separada
    // como resultado.
    // Pode parecer que ter a variável `result` aqui é completamente inútil. Há, no entanto,
    // uma ressalva muito importante ao implementar o bloqueio de verificação dupla em Java, 
    // que é resolvida com a introdução desta variável local.

    // https://en.wikipedia.org/wiki/Double-checked_locking#Usage_in_Java
    public static ConfigParameters getInstance()
    {
        ConfigParameters result = instance;
        if (result != null) {
            return result;
        }
        synchronized(ConfigParameters.class) {
            if (instance == null) {
                instance = new ConfigParameters();
            }
            return instance;
        }
    }


    public static long getSerialversionid() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Map<String,String>> returnListConfigParams()
    {
        return configList;
    }

    @Override
    public String toString() {
        return "Test [id=" + id + ", atributo=" + attribute + ", tipo=" + type + "]";
    }

    public void addList(Map<String, String> map)
    {
        configList.add(map);
    }

    
    public void showMap()
    {
        for (Map<String, String> data : configList) {
            System.out.println("Dados:" + data);

            for (Map.Entry<String, String> entry : data.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                System.out.println(key + ": " + value);
            }

        }   
        System.out.println(); // Adicionar uma linha em branco entre os mapas
    }

   
  

}
