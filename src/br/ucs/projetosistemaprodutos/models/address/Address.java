package br.ucs.projetosistemaprodutos.models.address;

import java.io.Serial;
import java.io.Serializable;

public class Address implements Serializable {
    @Serial
    private static final long serialVersionUID = 1;

    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String cep;
    private String city;
    private String state;

    public Address(String street, String number, String complement, String neighborhood, String cep, String city, String state) {
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.cep = cep;
        this.city = city;
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    public String toString() {
    	return "Endereço: Rua: " + this.street + ", " + this.number + ", " +this.complement+ ", " + this.neighborhood + ", " + this.city + " - " + this.state + ", CEP: " + this.cep ;
    }
    
}
