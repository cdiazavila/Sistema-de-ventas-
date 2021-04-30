/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APP;

import CONTROLADOR.ControladorAdministrador;
import CONTROLADOR.ControladorLogin;
import MODELO.*;
import VISTA.VistaAdministrador;
import VISTA.vistaLogin;

/**
 *
 * @author IESJOBE
 */
public class Api {
     public static void main(String[] args) {
   
               

        Login Login = new Login();
        LoginDAO LoginDao = new LoginDAO();
        vistaLogin vistaLogin = new vistaLogin();
        
         ControladorLogin ControlLogin = new ControladorLogin(Login,LoginDao,vistaLogin);
        ControlLogin.InicarVistaLogin();
        vistaLogin.setVisible(true);
  
    }  
}
