/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.hackathon.rest.services;

import br.com.hackathon.rest.business.ContaService;
import br.com.hackathon.rest.exception.NegocioException;
import br.com.hackathon.rest.model.Conta;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 * @date 19/02/2017
 *
 */
@Path("/contas")
public class ManterConta {

    @Inject
    private ContaService contaService;
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastraConta(Conta conta){
        try {
            return Response.ok().entity( contaService.cadastrar(conta)).build();
        } catch (NegocioException ex) {
            throw new WebApplicationException(ex);
        }
    }
    
}
