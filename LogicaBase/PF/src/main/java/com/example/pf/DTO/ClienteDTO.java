package com.example.pf.DTO;

import com.example.pf.cliente.Cliente;

import java.sql.Date;

public class ClienteDTO {
    private String nome;
    private String email;
    private String cognome;
    private char sesso;
    private String codiceFiscale;
    private Date dataNascita;
    private String residenza;
    private String indirizzo;
    private String cellulare;

    // Metodi getter e setter per i campi

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public char getSesso() {
        return sesso;
    }

    public void setSesso(char sesso) {
        this.sesso = sesso;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public Date getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getResidenza() {
        return residenza;
    }

    public void setResidenza(String residenza) {
        this.residenza = residenza;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getCellulare() {
        return cellulare;
    }

    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }
    public static Cliente convertClienteToEntity(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setCognome(clienteDTO.getCognome());
        cliente.setSesso(clienteDTO.getSesso());
        cliente.setCodiceFiscale(clienteDTO.getCodiceFiscale());
        cliente.setDataNascita(clienteDTO.getDataNascita());
        cliente.setResidenza(clienteDTO.getResidenza());
        cliente.setIndirizzo(clienteDTO.getIndirizzo());
        cliente.setCellulare(clienteDTO.getCellulare());

        return cliente;
    }

    public static ClienteDTO convertClienteToDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setEmail(cliente.getEmail());
        clienteDTO.setCognome(cliente.getCognome());
        clienteDTO.setSesso(cliente.getSesso());
        clienteDTO.setCodiceFiscale(cliente.getCodiceFiscale());
        clienteDTO.setDataNascita(cliente.getDataNascita());
        clienteDTO.setResidenza(cliente.getResidenza());
        clienteDTO.setIndirizzo(cliente.getIndirizzo());
        clienteDTO.setCellulare(cliente.getCellulare());

        return clienteDTO;
    }
}

