package com.example.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.example.Exceptions.ShowException;

public class Gastos {
    Scanner dados = new Scanner(System.in);
    
    private Map<String, Double> gastos;

    String name;
    Double gasto;

    double limite;

    public Gastos(){
        this.gastos = new HashMap<String,Double>();
    }

    public Map<String, Double> getGastos() {
        return gastos;
    }

    public void adicionarGasto(){

        System.out.println("Informe o nome do gasto:");
        name = dados.nextLine();

        System.out.println("Informe seu valor:");
        gasto = dados.nextDouble();

        gastos.put(name, gasto);
        System.out.println("Gasto adicionado!");
    }

    public void listarGastos(){

        if(gastos.isEmpty()){
            throw new ShowException("Nenhum gasto adicionado.");
        }

        gastos.entrySet().stream()
            .forEach(System.out::println);
    }

    public void deletarGastoPorNome(){

    }


    public void deletarTodosGastos(){

    }


    public void mediaGastos(){

    }

    public void limiteGastos(){

    }

    public void pesquisarGasto(){

    }

    public void modificarGasto(){

    }

}
