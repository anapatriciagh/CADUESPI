/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades.util;

import java.util.Date;

/**
 *
 * @author Rubens
 */
public class Rg {
    
    private String rg;
    private String rgOrgaoEmissor;
    private String rgOrgaoEmissorUf;
    private Date rgDataEmissao;

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getRgOrgaoEmissor() {
        return rgOrgaoEmissor;
    }

    public void setRgOrgaoEmissor(String rgOrgaoEmissor) {
        this.rgOrgaoEmissor = rgOrgaoEmissor;
    }

    public String getRgOrgaoEmissorUf() {
        return rgOrgaoEmissorUf;
    }

    public void setRgOrgaoEmissorUf(String rgOrgaoEmissorUf) {
        this.rgOrgaoEmissorUf = rgOrgaoEmissorUf;
    }

    public Date getRgDataEmissao() {
        return rgDataEmissao;
    }

    public void setRgDataEmissao(Date rgDataEmissao) {
        this.rgDataEmissao = rgDataEmissao;
    }
        
    
}
