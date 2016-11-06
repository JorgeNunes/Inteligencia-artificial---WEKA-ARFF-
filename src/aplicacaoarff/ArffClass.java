/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacaoarff;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.lazy.IBk;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author jorge
 */
public class ArffClass {
    
       
    private int quantidadeInstancias;
    private int classeReal;
    private String nomeClasseArff;
    private String dataArff;
    private String diretorio;
    private FileWriter arquivoEscrita;
    private PrintWriter gravarArquivo;       
    private ArrayList<String> atributosArff;
    
    
    /*
    * Construtor e que popula Arralist seguindo modelo, e o nome da Classse
    * atributosArff.add("atributo", "tipo");
    */
    public ArffClass(ArrayList<String> atributosArff, String nomeClasseArff){ 
        this.atributosArff = new ArrayList<>();
        
        for(int i = 0; i < atributosArff.size(); i++){
            this.atributosArff.add(atributosArff.get(i));
        }
        
        this.nomeClasseArff = nomeClasseArff;
        this.diretorio = "baseArff"+nomeClasseArff+".arff";
    }
        
    private String cabecalhoArff(){
        String cabeçalhoArff = "% Arquivo ARFF IA - JORGE NUNES -  "
                + "A arte de programar em java %\n"
                + "@relation " + this.nomeClasseArff + "\n";          
        
        for(int i = 0; i < this.atributosArff.size(); i++){
            cabeçalhoArff += "@attribute " + this.atributosArff.get(i) + "\n";
        }
            cabeçalhoArff += "\n@data\n";
        
        return cabeçalhoArff;
    }
    
    public void xmlToArff(String diretorioXml){         
        if(abrirArquivo(this.diretorio, true) == true){
           gravarArquivo(cabecalhoArff());   
           xml(diretorioXml);  
           fecharArquivo();  
        }else{
           verificacaoInstancias();
        }    
        
    }
    
    private void verificacaoInstancias(){
        
         FileReader arquivoLeitura;
        
        try {
            arquivoLeitura = new FileReader(this.diretorio);
            BufferedReader lerArquivo = new BufferedReader(arquivoLeitura);
            
            try {
                String linha = lerArquivo.readLine();              
                boolean verificadorLinhaInicial = false;
                while (linha != null) {
                    
                    if(linha.trim().toUpperCase().equals("")){
                        verificadorLinhaInicial = false;
                    } 
                    else if(linha.trim().toUpperCase().equals("@DATA")){
                        verificadorLinhaInicial = true;
                        linha = lerArquivo.readLine();
                    }   
                    
                    if(verificadorLinhaInicial == true){
                        this.quantidadeInstancias++;
                    }
                    

                    linha = lerArquivo.readLine();
                }
                lerArquivo.close();
            } catch (IOException ex) {
                Logger.getLogger(ArffClass.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArffClass.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    
    private void xml (String diretorio){        
        
        ArrayList<String> arffData = new ArrayList<>();   
        FileReader arquivoLeitura;
        
        try {
            arquivoLeitura = new FileReader(diretorio);
            BufferedReader lerArquivo = new BufferedReader(arquivoLeitura);
            
            try {
                String linha = lerArquivo.readLine();
                String linhaArff = "";
                String verificadorFinalClass = "";
                
                
                while (linha != null) {
                    linhaArff += tratamenToXMLtoArff(linha.trim());
                    
                    if(linhaArff.equals("")){                        
                        verificadorFinalClass = linha.trim();     
                    }else if(!linha.trim().equals(verificadorFinalClass)){                        
                        if(verificadorFinalClass.equals(linha.replaceAll("/", "").trim())){                                                          
                            linhaArff = linhaArff.substring(0, linhaArff.length()-2)+"\n";                            
                            gravarArquivo(linhaArff);
                            linhaArff = "";                           
                            this.quantidadeInstancias++;                                    
                        }                       
                    }
                    

                    linha = lerArquivo.readLine();
                }                
                gravarArquivo("\n%\n%" + this.quantidadeInstancias + " Instancias \n%\n\n");
                lerArquivo.close();
            } catch (IOException ex) {
                Logger.getLogger(ArffClass.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArffClass.class.getName()).log(Level.SEVERE, null, ex);
        }    
        
    }
    
    private String tratamenToXMLtoArff(String linha){   
        
        boolean verificador = true;
        char verificadorCaracter = '>';
        String armazenadorInformacao = "";
        
        for(int i = 0; i < linha.length(); i++){
            if(verificador == false && verificadorCaracter != linha.charAt(i)){                
                armazenadorInformacao += linha.charAt(i);
            }else if(verificadorCaracter == linha.charAt(i)){
                if(verificador == false){                             
                    armazenadorInformacao = eliminarEspaçoVariavel(armazenadorInformacao);       
                    //System.out.print(armazenadorInformacao+"\n");
                    armazenadorInformacao += ", ";                    
                    break;
                }
                verificador = false;
                verificadorCaracter = '<';                    
            }
        }
        
        return armazenadorInformacao;
    }
    
        
    
    private boolean abrirArquivo(String diretorio, boolean verificarExisteArquivo){        
        boolean verificador = true;     
        
        File arquivo = new File(diretorio); 
        
        if(verificarExisteArquivo == true){
            if(arquivo.exists()){
                System.out.println("Arquivo já existe");
            }
        }else{
            verificador = false;
        }
        
        if(verificador == true){
            return verificador = false;
        }else{
            try {
                this.arquivoEscrita = new FileWriter(diretorio);
                this.gravarArquivo = new PrintWriter(arquivoEscrita);  
                verificador = false;
            } catch (IOException ex) {
                Logger.getLogger(ArffClass.class.getName()).log(Level.SEVERE, null, ex);
                verificador = false;
            } finally {
                return verificador;
            }     
        }       
    }
    
    private boolean gravarArquivo(String texto){        
        boolean verificador = true;      
        
        try {
            this.gravarArquivo.write(texto);                     
        } catch (Exception ex) {
            Logger.getLogger(ArffClass.class.getName()).log(Level.SEVERE, null, ex);
            verificador = false;
        } finally {
            return verificador;
        }      
        
        
    }
    
    private boolean fecharArquivo(){        
        boolean verificador = true;      
        
        try {           
            this.arquivoEscrita.close();           
        } catch (IOException ex) {
            Logger.getLogger(ArffClass.class.getName()).log(Level.SEVERE, null, ex);
            verificador = false;
        } finally {
            return verificador;
        }             
        
    }
    
    private String eliminarEspaçoVariavel(String variavel){
        variavel = variavel.trim();
        variavel = variavel.replaceAll(" ", "-");
        variavel = variavel.replaceAll("--", "-");
        variavel = variavel.replaceAll("---", "-");       
        variavel = variavel.replaceAll("/", "");
        variavel = variavel.replaceAll(",", "-");         
        variavel = variavel.replaceAll("s--", "s-");
        variavel = variavel.replaceAll("--", "-");
        variavel = variavel.replaceAll("---", "-");
        
        
        return variavel;
    }
    
    /* 
    *  Entrar com atributoClasse (posiçao do vetor/array)
    */
    public ArffClass variacaoCruzadaKnnNaive(int classeReal) throws Exception{
        // PARÂMETROS PARA A VALIDAÇÃO CRUZADA
        double atributoClasse = 0;
        int ITERACOES = this.quantidadeInstancias;  
        
        MatrizConfusao naiveMatriz = new MatrizConfusao(classeReal, this.atributosArff.get(classeReal), "NaiveBayes");
        MatrizConfusao knnMatriz = new MatrizConfusao(classeReal, this.atributosArff.get(classeReal), "Knn");
                      
        FileReader leitor = new FileReader("baseArff"+nomeClasseArff+".arff");          
        Instances baseArff = new Instances(leitor);
            
        baseArff.setClassIndex(classeReal);
        baseArff = baseArff.resample(new Random());
        System.out.println("Real;Naive;Knn");
        
        abrirArquivo("resultadoArff.txt", false);
        
        for (int i = 0; i < ITERACOES; i++) {
            // Obtendo as partições de treino e de teste
		Instances treino = baseArff.trainCV(ITERACOES, i);
		Instances teste = baseArff.testCV(ITERACOES, i);
        	// Definindo os classificadores
		NaiveBayes naive = new NaiveBayes();
		IBk knn = new IBk();
		// Treinando os classificadores
		naive.buildClassifier(treino);
		knn.buildClassifier(treino);
		// Anotando os resultados - Saída para um arquivo csv
            	for (int j = 0; j < teste.numInstances(); j++) {

                    // Obtendo o exemplo que a ser classificado
                    Instance exemplo = teste.instance(j);
                    
                    atributoClasse = exemplo.value(classeReal);
                    //System.out.print(atributoClasse); // classe real
                    exemplo.setClassMissing(); // removendo a classe
                    
                    double vizinhoRes = naive.classifyInstance(exemplo);                    
                    naiveMatriz.somaMatriz((int)atributoClasse, (int)vizinhoRes);                    
                    
                    double knnRes = knn.classifyInstance(exemplo);
                    knnMatriz.somaMatriz((int)atributoClasse, (int)knnRes);  
                                             
                    
		}
                
                
            }           
            gravarArquivo(naiveMatriz.getMatrizConfusao());
            gravarArquivo(knnMatriz.getMatrizConfusao());
            System.out.println(naiveMatriz.getMatrizConfusao());
            System.out.println(knnMatriz.getMatrizConfusao());
            fecharArquivo();  
            leitor.close();
        return this;
    } 
   
}