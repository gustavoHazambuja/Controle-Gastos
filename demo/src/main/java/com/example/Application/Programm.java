package com.example.Application;

import java.util.Scanner;

import com.example.Exceptions.DeleteException;
import com.example.Exceptions.LimitException;
import com.example.Exceptions.ModificationException;
import com.example.Exceptions.SearchException;
import com.example.Exceptions.ShowException;
import com.example.Service.Gastos;

public class Programm {
    public static void main(String[] args) {
        Scanner dados = new Scanner(System.in);

        Gastos gastos = new Gastos();

        int opcao;

        do{

            System.out.println("****************************");
            System.out.println("******* MENU DE OPÇÕES *****");
            System.out.println("****************************");
    
            System.out.println("(1) Definir limite para gastos");
            System.out.println("(2) Mostrar limite");
            System.out.println("(3) Adicionar gasto");
            System.out.println("(4) Listar gastos");
            System.out.println("(5) Remover todos os gastos");
            System.out.println("(6) Remover gasto pelo nome");
            System.out.println("(7) Pesquisar gasto");
            System.out.println("(8) Modificar gasto");
            System.out.println("(9) Sair");
            opcao = dados.nextInt();
            dados.nextLine();

            switch (opcao) {
                case 1:
                    gastos.limiteGastos();
                    break;

                case 2:
                    try{
                        gastos.mostrarLimite();
                    }catch(LimitException e){
                        System.out.println("Erro: " + e.getMessage());
                    }
                    
                    break;    

                case 3:
                    try{
                        gastos.adicionarGasto();
                    }catch(LimitException e){
                        System.out.println("Erro: " + e.getMessage());
                    }
                    
                    break;

                case 4:
                    try{
                        gastos.listarGastos();
                    }catch(ShowException e){
                        System.out.println("Erro. " + e.getMessage());
                    }   
                    break;
                    
                case 5:
                    try{
                        gastos.removerTodosGastos();
                    }catch(DeleteException e){
                        System.out.println("Erro. " + e.getMessage());
                    }  
                    break;
                    
                case 6:
                    try{
                        gastos.removerGastoPorNome();
                    }catch(DeleteException e){
                        System.out.println("Erro. " + e.getMessage());
                    }    
                    break;

                case 7:
                    try{
                        gastos.pesquisarGasto();
                    }catch(SearchException e){
                        System.out.println("Erro." + e.getMessage());
                    }   
                    break;
                    
                case 8:
                    try{
                        gastos.modificarGasto();
                    }catch(ModificationException e){
                        System.out.println("Erro." + e.getMessage());
                    }    
                    break;

                case 9:
                    System.out.println("Saindo...");
                    break;    

                default:
                   System.out.println("Opção inválida.");
                   break;
            }

    }while(opcao != 9);

    dados.close();
  }
}
