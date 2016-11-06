/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacaoarff;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author jorge
 */
public class MatrizConfusao {
    
    private int[][] vetorDadosMatrizConfusao;
    private ArrayList<String> arrayNomeMatrizConfusao;
    private int classeReal;
    private int quantidadeItensArrayClasse;
    private String nomeAlgoritmo;
    private String precisao;
    private String revocacao;
    private String acuracia;
    private String taxaDeErro;
    
    
    public MatrizConfusao(int classeReal, String atributosClasse, String nomeAlgoritmo){
        this.classeReal = classeReal;
        this.vetorDadosMatrizConfusao = new int [classeReal+1][classeReal+1];
        this.arrayNomeMatrizConfusao = new ArrayList<>();
        this.quantidadeItensArrayClasse = 0;
        this.nomeAlgoritmo = nomeAlgoritmo;
        getItensArrayClasse(atributosClasse);
        
    }
    
    private void getItensArrayClasse(String atributosClasse){

        String armazenadorNome = "";
        this.quantidadeItensArrayClasse++;
        
        for(int i = 0; i < atributosClasse.length(); i++){
            if(atributosClasse.charAt(i) == ','){
                this.quantidadeItensArrayClasse++;
            }
            if(atributosClasse.charAt(i) == '{'){
                for(int x = i+1; x < atributosClasse.length(); x++){
                    if(atributosClasse.charAt(x) == ',' || atributosClasse.charAt(x) == '}'){
                        this.arrayNomeMatrizConfusao.add(armazenadorNome);
                                              
                        armazenadorNome = "";
                    }else if (atributosClasse.charAt(x) != ' '){
                        armazenadorNome += atributosClasse.charAt(x);
                    }
                }          
            }
        }        
    }
    
    public void somaMatriz(int atributo1, int atributo2){        
        if(atributo1 == atributo2){
            vetorDadosMatrizConfusao[atributo1][atributo2] += 1;
        }else{
            vetorDadosMatrizConfusao[atributo2][atributo1] += 1;
        }      
    }
    
    private void somaValoresMatrizConfusao(){
        
        int soma = 0;
        double auxiliar = 0;
        String teste;
        DecimalFormat formato = new DecimalFormat("#.####"); 
        
            
        for(int i = 0; i < quantidadeItensArrayClasse; i++){
            for(int x = 0; x < quantidadeItensArrayClasse; x++){
                soma += vetorDadosMatrizConfusao[i][x];
            }                
        }        
        vetorDadosMatrizConfusao[quantidadeItensArrayClasse][quantidadeItensArrayClasse] = soma;
        soma = 0;
            
        for(int i = 0; i < quantidadeItensArrayClasse; i++){
            for(int x = 0; x < quantidadeItensArrayClasse; x++){
                soma += vetorDadosMatrizConfusao[i][x];
            }
            vetorDadosMatrizConfusao[i][quantidadeItensArrayClasse] = soma;
            soma = 0;
        }
            
        for(int i = 0; i < quantidadeItensArrayClasse; i++){
            for(int x = 0; x < quantidadeItensArrayClasse; x++){
                soma += vetorDadosMatrizConfusao[x][i];
            }
            vetorDadosMatrizConfusao[quantidadeItensArrayClasse][i] = soma;
            soma = 0;
        }       
        
        
        this.precisao = "\nPrecisao";
        for(int i = 0; i < this.quantidadeItensArrayClasse; i++){          
            auxiliar =  this.vetorDadosMatrizConfusao[i][i];
            auxiliar = auxiliar / this.vetorDadosMatrizConfusao[quantidadeItensArrayClasse][i];            
            this.precisao += " | " + formato.format(auxiliar);
        }
        
        this.revocacao = "Revocaçao";
        for(int i = 0; i < this.quantidadeItensArrayClasse; i++){          
            auxiliar =  this.vetorDadosMatrizConfusao[i][i];
            auxiliar = auxiliar / this.vetorDadosMatrizConfusao[i][quantidadeItensArrayClasse];           
            this.revocacao += " | " + formato.format(auxiliar);
        }     
        
        this.acuracia = "Acuracia";
        this.taxaDeErro = "Taxa de Erro";
        for(int i = 0; i < this.quantidadeItensArrayClasse; i++){          
            auxiliar += this.vetorDadosMatrizConfusao[i][i];            
        }  
        
        auxiliar = auxiliar / this.vetorDadosMatrizConfusao[quantidadeItensArrayClasse][quantidadeItensArrayClasse]; 
        this.acuracia += " | " + formato.format(auxiliar);
        auxiliar = 0;
        
        this.taxaDeErro = "Taxa de Erro";
        for(int i = 0; i < this.quantidadeItensArrayClasse; i++){          
            for(int a = 0; a < this.quantidadeItensArrayClasse; a++){
                if(i != a){
                    auxiliar += this.vetorDadosMatrizConfusao[i][a];
                }                       
            }             
        }
        
        auxiliar = auxiliar / this.vetorDadosMatrizConfusao[quantidadeItensArrayClasse][quantidadeItensArrayClasse]; 
        this.taxaDeErro += " | " + formato.format(auxiliar);
    }
    
    public String getMatrizConfusao(){       
        
        somaValoresMatrizConfusao();
        
        String resultado = "\n\nResultados matriz de confusao algoritmo "+this.nomeAlgoritmo+"\n";
        
        resultado += "Atributos: ";
        
        for(int i = 0; i < arrayNomeMatrizConfusao.size(); i++){
            resultado += arrayNomeMatrizConfusao.get(i)+ " | ";
        }      
        resultado += "Total\nSeguindo essa ordem na horizontal e vertical\n";
        
        for(int i = 0; i <= this.quantidadeItensArrayClasse; i++){          
            for(int a = 0; a <= this.quantidadeItensArrayClasse; a++){
                resultado += this.vetorDadosMatrizConfusao[i][a] + " | ";           
            }
            resultado += "\n";
        }
        
        resultado += this.precisao + "\n" + this.revocacao 
                  + "\n" + this.acuracia + "\n" + this.taxaDeErro + "\n\n";
                
        return resultado;
    }
}
