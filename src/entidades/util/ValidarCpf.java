/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades.util;

/**
 *
 * @author rlopes
 */
/**
   *
   * @author Wellington de lima alves
   */
  public final class ValidarCpf {
  private String cpf; 
  public ValidarCpf() {
  }
  public boolean validarCpf(String cpf){  
      if ( cpf == null ){
          return false;
      }
      else{
              String cpfGerado = "";     
              this.cpf = cpf;           
               removerCaracteres();            
          if ( verificarSeTamanhoInvalido(this.cpf) )
               return false;       
          if ( verificarSeDigIguais(this.cpf) )
               return false;            
          cpfGerado = this.cpf.substring(0, 9);
          cpfGerado = cpfGerado.concat(calculoComCpf(cpfGerado));
          cpfGerado = cpfGerado.concat(calculoComCpf(cpfGerado));
          
          if ( !cpfGerado.equals(this.cpf))
               return false;
      } 
      return true;
  }
   
  private void removerCaracteres(){   
   this.cpf = this.cpf.replace("-","");
   this.cpf = this.cpf.replace(".","");   
  } 
  private boolean verificarSeTamanhoInvalido(String cpf){  
      if ( cpf.length() != 11 )
        return true;   
          return false;
  } 
  private boolean verificarSeDigIguais(String cpf){   
    //char primDig = cpf.charAt(0);
      char primDig = '0';
      char [] charCpf = cpf.toCharArray();  
         for( char c: charCpf  )
             if ( c != primDig )
             return false;        
             return true;
  } 
  private String calculoComCpf(String cpf){   
   int digGerado = 0;
   int mult = cpf.length()+1;
   char [] charCpf = cpf.toCharArray();
   for ( int i = 0; i < cpf.length(); i++ )
        digGerado += (charCpf[i]-48)* mult--;
   if ( digGerado % 11 < 2)
        digGerado = 0;
   else
       digGerado = 11 - digGerado % 11;
   return  String.valueOf(digGerado); 
  }
  }
 
