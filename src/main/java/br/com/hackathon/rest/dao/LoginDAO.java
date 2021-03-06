/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hackathon.rest.dao;

import br.com.hackathon.rest.criptografia.Encriptor;
import br.com.hackathon.rest.enumeracoes.MensagensCodigo;
import br.com.hackathon.rest.exception.DAOException;
import br.com.hackathon.rest.exception.PersistenciaException;
import br.com.hackathon.rest.interfaces.Persistencia;
import br.com.hackathon.rest.model.Conta;
import br.com.hackathon.rest.util.MensagensBase;
import br.com.hackathon.rest.validador.ListaValidador;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.NoSuchPaddingException;
import javax.inject.Inject;

/**
 *
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 * @date 18/02/2017
 *
 */
public class LoginDAO {

    @Inject
    private Persistencia<Conta, Long> dao;
    
    @Inject
    private ListaValidador listaValidador;
    
    @Inject
    private MensagensBase mensagensBase;
    
    @Inject
    private Encriptor encriptor;
    
    public Conta consultarContaPorTelefoneSenha(String telefone, String senha) throws DAOException {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("telefone", telefone);
            parametros.put("senha", encriptor.encrypt(senha) );
            List<Conta> contas = dao.consultar("conta.consultar.conta.por.login.senha", parametros);
            return listaValidador.isValid(contas) ? contas.get(0) : null;
        } catch (PersistenciaException | NoSuchAlgorithmException | NoSuchPaddingException ex) {
            throw new DAOException(mensagensBase.get(MensagensCodigo.MS002), ex);
        }
    }
}
