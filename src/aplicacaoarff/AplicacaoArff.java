/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacaoarff;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jorge
 */
public class AplicacaoArff {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       
        teste();
        
    }
    
    private static void teste(){
        ArrayList<String> atributosArff  = new ArrayList<>();
        atributosArff.add("regiao                 {CO, N, NE, S, SE}");
        atributosArff.add("sexo                   {M, F}");
        atributosArff.add("faixaEtaria            {até-20-anos, entre-21-a-30-anos, entre-31-a-40-anos, entre-41-a-50-anos, entre-51-a-60-anos, entre-61-a-70-anos, mais-de-70-anos}");
        atributosArff.add("tempoResposta          NUMERIC");
        atributosArff.add("nomeFantasia           {Banco-Votorantim, Bradesco-AutoRE, Aliança-do-Brasil, American-Express-Amex, Americanas-Viagens, Americanas.com, AOC, Ativos-S.A, Amil, Atlas-Eletrodomésticos, Avianca-Oceanair, Banco-BMG, Banco-Bradesco, Banco-Cifra, Banco-do-Brasil, Banco-Fiat, Banco-Itaú-Unibanco, Banco-Pan-(Panamericano), Banco-Santander, Banco-Santander-Cartões, Banco Votorantim, BB-Consórcios, BIG, Bradescard, Bradesco-Auto/RE, Bradesco-Cartões, Bradesco-Consórcio, Bradesco-Financiamentos, Bradesco-Promotora,  Bradesco-Saúde, Bradesco-Seguros, Bradesco-Vida-e-Previdência, Brasilprev-Seguros-e-Previdência, Brastemp, BV-Financeira, BV-Leasing, Caixa-Capitalização, Caixa-Consórcios, Caixa-Econômica-Federal, Caixa-Previdência, Caixa-Seguros, Cardif-Seguros-e-Garantias, Carrefour, Cartão-Carrefour, Cartão-Quero-Quero-VerdeCard, Cartão-Submarino, Casas-Bahia, Casasbahia.com, Centauro, Centauro.com.br, City-Lar, Claro-Celular, Claro-Fixo-Embratel, Claro-TV, Compra-Certa, Consórcio-Fiat, Consul, CPFL-Energia, Credicard, Credipar, Decolar.com, Dental-Uni, Drogasil, Eletro-Shopping, Extra, Extra.com, Financeira-Itaú-Americanas, Garantec, Girafa-Comércio-Eletrônico, Gol-Linhas-Aéreas, GVT, Hiper-Bompreço, Hipercard, HSBC, Ingresso.com, Insinuante, Intelig, Itaú-BMG-Consignado, Itaú-Consórcio, Itaú-Seguros, Itaú-Seguros-Auto-e-Residência, Itaú-Unibanco-Consignado, Itaú-Unibanco-Crédito-Imobiliário, Itaucard, Itauleasing, KaBuM!, Komeco, Latina-Eletrodomésticos, LG-Electronics, Lojas-Americanas, Lojas-Colombo, Lojas-Colombo.com, Lojas-Quero-Quero, Losango, Luizacred, Luizaseg, Magazine-Luiza, Magazineluiza.com, Mapfre-Seguros, Moip, Nacional, Netfarma, Novo-Mundo, O-Boticário, Odontoprev, Oi-Celular, Oi-Fixo, Oi-Paggo-Administradora-de-Crédito, Ourocard, Panvel-Farmácias, Pão-de-Açúcar, Philips-Áudio-e-Vídeo, Philips-TV-e-Monitores, Philips-Walita-Avent-Saeco-e-Sonicare, Polishop, Polishop.com.br, Ponto-Frio, Pontofrio.com, Portal-Terra, Purificador-de-Água-Brastemp, Recovery-do-Brasil-Consultoria, RGE-Rio-Grande-Energia, Ricardo-Eletro, Ricardo-Eletro.com, Sam´s-Club, Samsung, Santander-Financiamentos, Senff, Serasa-Experian, Shoptime, SKY, Smiles, Sou-barato, Submarino, Submarino-Viagens, SuperMuffato.com, Tam, Tim, Unimed-Paulistana, ViajaNet, Vivo-Telefônica, Walmart, Walmart.com, Wine, Yasuda-Marítima-Seguros}");
        atributosArff.add("segmentodeMercado      {Fabricantes-Eletroeletrônicos-Produtos-de-Telefonia-e-Informática, Fabricantes-Linha-Branca, Administradoras-de-Consórcios, Agências-de-Viagens, Artigos-Esportivos, Corretoras-e-Sociedades-de-Seguros-Capitalização-e-Previdência, Bancos-de-Dados-e-Cadastros-de-Consumidores, Bancos, Bancos-Financeiras-e-Administradoras-de-Cartão, Financeiras-e-Administradoras-de-Cartão, Comércio-Eletrônico, Corretoras-e-Sociedades-de-Seguros, Capitalização-e-Previdência, Empresas-de-Pagamento-Online, Empresas-de-Recuperação-de-Crédito, Energia-Elétrica-Gás-Água-e-Esgoto, Fabricantes-Eletroeletrônicos,  Produtos-de-Telefonia-e-Informática, Fabricantes-Eletroportáteis-e-Artigos-de-Uso-Doméstico-e-Pessoal, Fabricantes-Linha Branca, Farmácias, Operadoras-de-Planos-de-Saúde-e-Administradoras-de-Benefícios, Operadoras-de-Telecomunicações-(Telefonia-Internet-TV-por-assinatura), Perfumaria-Cosméticos-e-Higiene-Pessoal, Programas-de-Fidelidade, Provedores-de-Conteúdo-e-Outros-Serviços-de-Informação-na-Internet, Supermercados, Transporte-Aéreo, Varejo}");
        atributosArff.add("grupoProblema          {Atendimento-SAC, Cobrança-Contestação, Contrato-Oferta, Entrega-do-Produto, Informação, Saúde-e-Segurança, Vício-de-Qualidade}");
        atributosArff.add("comoComprouContratou   {Não-comprei-contratei, Internet, Telefone, Domicílio, Loja-física, Ganhei-de-presente, Catálogo, Stand, Stand-feiras-e-eventos}");
        atributosArff.add("procurouEmpresa        {S, N}");
        atributosArff.add("respondida             {S, N}");
        atributosArff.add("avaliacaoReclamacao    {Resolvida, Não-Resolvida}");
        
        
        ArffClass arffClass = new ArffClass(atributosArff, "ReclamacoesDoConsumidor");
        arffClass.xmlToArff("C:\\Users\\jorge\\Desktop\\base\\baseXml.xml");
        try {
            arffClass.variacaoCruzadaKnnNaive(10);            
        } catch (Exception ex) {
            Logger.getLogger(AplicacaoArff.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
