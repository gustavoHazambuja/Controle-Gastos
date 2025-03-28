package com.example.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.example.Exceptions.DeleteException;
import com.example.Exceptions.ModificationException;
import com.example.Exceptions.SearchException;
import com.example.Exceptions.ShowException;

public class Gastos {
    Scanner dados = new Scanner(System.in);
    
    private Map<String, Double> gastos;

    String name;
    Double gasto;

    double soma = 0.0;

    public Gastos(){
        this.gastos = new HashMap<String,Double>();
    }

    public Map<String, Double> getGastos() {
        return gastos;
    }

    public void adicionarGasto(){

        System.out.println("Informe o nome do gasto:");
        name = dados.nextLine().toLowerCase();

        System.out.println("Informe seu valor:");
        gasto = dados.nextDouble();

        gastos.put(name, gasto);
        System.out.println("\nGasto adicionado.");

        soma += gastos.get(name);
    }

    public void listarGastos(){

        if(gastos.isEmpty()){
            throw new ShowException("Nenhum gasto adicionado.");
        }

        gastos.entrySet().stream()
            .forEach(System.out::println);

       System.out.println("Valor gasto até o momento: R$" + soma);     
    }

    public void deletarGastoPorNome(){

        System.out.println("Informe o nome do gasto:");
        name = dados.nextLine().toLowerCase();

        boolean isExist = gastos.entrySet().stream()
            .anyMatch(g -> g.getKey().equalsIgnoreCase(name));

       if(!isExist){
        throw new DeleteException("Gasto não encontrado.");
       }
       
       gastos.remove(name);
       System.out.println("\nGasto deletado.");
    }


    public void deletarTodosGastos(){

        if(gastos.isEmpty()){
            throw new DeleteException("Nenhum gasto adicionado.");
        }

        gastos.clear();
    }


    public void mediaGastos(){


    }


    public void limiteGastos(){

        System.out.println("Informe o seu limite para gastos:");
        Double limGasto = dados.nextDouble();

        System.out.println("Seu limite de gastos é de: R$" + limGasto);
    }

    public void pesquisarGasto(){

        System.out.println("Informe o nome do gasto:");
        name = dados.nextLine().toLowerCase();

        List<Map.Entry<String,Double>> searchGasto = gastos.entrySet().stream()
            .filter(g -> g.getKey().toLowerCase().contains(name))
            .collect(Collectors.toList());

        if(searchGasto.isEmpty()){
            throw new SearchException("Nenhum gasto encontrado.");
        }
        
        searchGasto.forEach(System.out::println);
        System.out.println("\nGasto encontrado.");

          
    }

    public void modificarGasto(){

        System.out.println("Informe o nome do gasto:");
        name = dados.nextLine().toLowerCase();

        boolean isExist = gastos.entrySet().stream()
            .anyMatch(g -> g.getKey().equalsIgnoreCase(name));

        if(!isExist){
            throw new ModificationException("Gasto não encontrado.");
        }
        
        System.out.println("Informe o novo valor:");
        Double newGasto =  dados.nextDouble();

        gastos.put(name, newGasto);
        System.out.println("Valor atualizado.");
    }

}
