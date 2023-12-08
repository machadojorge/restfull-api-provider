package com.estudarecompensa.ativityprovider.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "tb_test")
public class Test {
    private static Test instance;
    private static List<Map<String, String>> list; 


    private static final long serialVersionID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String atributo;
    private String tipo;

   
    



    private Test()
    {
        // aqui posso chamar o database para obter os dados
      list = new ArrayList<Map<String,String>>();


    }

    public void addToMap(List<Test> test)
    {
       
        for(Test t : test)
        {
             Map<String, String> map = new HashMap<String, String>();
            System.out.println("atributo: -> " + t.getAtributo());
            System.out.println("tipo: -> " + t.getTipo());
            map.put("atributo",t.getAtributo());
            map.put("tipo", t.getTipo());
            list.add(map);
            System.out.println("Lista: " + list);
        }

        

    }

    public void showMap()
    {
        for (Map<String, String> data : list) {
            System.out.println("Dados:" + data);

            for (Map.Entry<String, String> entry : data.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                System.out.println(key + ": " + value);
            }

        }   
        System.out.println(); // Adicionar uma linha em branco entre os mapas
    }

    public void addList(Map<String, String> map)
    {
        list.add(map);
    }

     public List<Map<String, String>> getList()
    {
        return list;
    }

    public List<Map<String, String>> returnMap()
    {
        return list;
    }

    public static Test getInstance()
    {
        
        if (instance == null) {
            instance = new Test();
            System.out.println("Siglton: " + instance.hashCode());
        }
        return instance;
    }

    public static void setInstance(Test instance) {
        Test.instance = instance;
    }

    public static long getSerialversionid() {
        return serialVersionID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAtributo() {
        return atributo;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return super.clone();
    }

    // public List<Test> getList() {
    //     return list;
    // }

    // public void setList(List<Test> list_) {
    //     list =list_;
       
    // }

    @Override
    public String toString() {
        return "Test [id=" + id + ", atributo=" + atributo + ", tipo=" + tipo + "]";
    }

    // ter um outro metodo que devolve os dados em formato de dicionario/map
}
