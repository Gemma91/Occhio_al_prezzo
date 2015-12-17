/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.account;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import it.unisa.account.Account;
import it.unisa.account.AccountManager;
import it.unisa.autenticazione.AutenticazioneManager;
import it.unisa.exception.ConnectionException;
import it.unisa.exception.ValueNullException;
import it.unisa.utility.UtilityVar;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author  raffaele donadio
 */

@WebServlet(name = "addAccountServlet", urlPatterns = {"/addAccountServlet"})
public class AddAccountServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
        
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();
        Account acc=new Account();
        acc.setNome(request.getParameter("nome"));
        acc.setEmail(request.getParameter("email"));
        acc.setPassword(request.getParameter("password"));
        acc.setDomicilio(request.getParameter("domicilio"));
        
        /*il ruolo non lo settiamo perchè di default è un utente*/
        
        acc.setComuneDiResidenza(request.getParameter("comune_di_residenza"));
        acc.setCognome(request.getParameter("cognome"));
        acc.setDataDiNascita(UtilityVar.parseData(request.getParameter("data_di_nascita")));
        
        AccountManager instance =  AccountManager.getInstance();
        try {
            instance.add(acc);
            out.print("<h1>account registrato correttamente</h1>");
        } catch (SQLException ex) {
            out.print("<h1>errore database</h1>");
        } catch (ValueNullException ex) {
            
            out.print("<h1>non hai inserito qualche valore</h1>");
        }
        
         
    }

    
 
}