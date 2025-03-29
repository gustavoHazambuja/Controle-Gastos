package com.example.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.example.Exceptions.DeleteException;
import com.example.Exceptions.LimitException;
import com.example.Exceptions.ModificationException;
import com.example.Exceptions.SearchException;
import com.example.Exceptions.ShowException;

public class Gastos {
    Scanner dados = new Scanner(System.in);
    
    private Map<String, List<Double>> gastos;

    String name;
    Double gasto;

    double soma = 0.0;

    double limiteGasto;

    public Gastos(){
        this.gastos = new HashMap<String, List<Double>>();
    }

    public Map<String, List<Double>> getGastos() {
        return gastos;
    }

    public void limiteGastos(){

        System.out.println("Informe o seu limite para gastos:");
        limiteGasto = dados.nextDouble();

        System.out.println("Seu limite de gastos é de: R$" + limiteGasto);
    }

    public void mostrarLimite(){

        if(limiteGasto == 0.0){
            throw new LimitException("Nenhum limite definido.");
        }

        System.out.println("Seu limite atual: " + limiteGasto);
    }

    public void adicionarGasto(){

        System.out.println("Informe o nome do gasto:");
        name = dados.nextLine().toLowerCase();

        System.out.println("Informe seu valor:");
        gasto = dados.nextDouble();

        dados.nextLine();

        verificaLimite(soma);

        gastos.computeIfAbsent(name, k -> new ArrayList<>()).add(gasto); // Armezena vários valores sem sobrescrever
        System.out.println("\nGasto adicionado.");

        soma += gasto;
    }

    public void listarGastos(){

        if(gastos.isEmpty()){
            throw new ShowException("Nenhum gasto adicionado.");
        }

        gastos.forEach((key,values) -> 
        System.out.println("Gasto: " + key + " | Valores: R$ " + values));
       

       System.out.println("Valor gasto até o momento: R$" + soma);     
    }

    public void removerGastoPorNome(){

        System.out.println("Informe o nome do gasto:");
        name = dados.nextLine().toLowerCase();

        verificaRemocao(name);

       double totalRemovido = gastos.get(name).stream()
            .mapToDouble(Double::doubleValue).sum();
       
       gastos.remove(name);
       System.out.println("\nGasto removido.");

       soma -= totalRemovido;
    }


    public void removerTodosGastos(){

        if(gastos.isEmpty()){
            throw new DeleteException("Nenhum gasto adicionado.");
        }

        gastos.clear();
        System.out.println("\nGastos removidos.");

        soma *= 0;
    }

    public void pesquisarGasto(){

        System.out.println("Informe o nome do gasto:");
        name = dados.nextLine().toLowerCase();

        List<Map.Entry<String,List<Double>>> searchGasto = gastos.entrySet().stream()
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

        verificaModificacao(name);
        
        System.out.println("Informe o novo valor:");
        Double newGasto =  dados.nextDouble();

        List<Double> valores = gastos.get(name);
        Double valorAntigo = valores.get(valores.size()-1);

        valores.set(valores.size()-1, newGasto);

        soma = soma - valorAntigo + newGasto;

        System.out.println("Valor atualizado.");

    }


    private void verificaRemocao(String name){

        boolean isExist = gastos.entrySet().stream()
            .anyMatch(g -> g.getKey().equalsIgnoreCase(name));

       if(!isExist){
        throw new DeleteException("Gasto não encontrado.");
       }

    }


    private void verificaModificacao(String name){

        boolean isExist = gastos.entrySet().stream()
        .anyMatch(g -> g.getKey().equalsIgnoreCase(name));

        if(!isExist){
            throw new ModificationException("Gasto não encontrado.");
        }
    }

    private void verificaLimite(double soma){

        if(soma >= limiteGasto){
            throw new LimitException("Limite atingido.");
        }
    }



}
