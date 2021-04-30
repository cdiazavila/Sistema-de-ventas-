/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import MODELO.*;
import VISTA.Impresionn;
import VISTA.VistaAdministrador;
import VISTA.vistaLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author IESJOBE
 */
public class ControladorLogin  implements ActionListener {
     // Usamos el patron de diseño Delegation
      private  Login modellogin;
      private LoginDAO modelLoginDAO;
      private vistaLogin vistaLogin;
     static  int vector[];
public  ControladorLogin( Login modellogin,LoginDAO modelLoginDAO, vistaLogin vistaLogin) {
        
        this.modellogin = modellogin;
        this.modelLoginDAO = modelLoginDAO;
        this.vistaLogin = vistaLogin;
        vector = new int [1];
         this.vistaLogin.btncancelar.addActionListener(this);
        this.vistaLogin.btnIniciar.addActionListener(this);
     
    }


    public void InicarVistaLogin() {
        vistaLogin.setLocationRelativeTo(null);
        vistaLogin.setTitle("Login");
    }         
   public void limpiar(){
       vistaLogin.txtUser.setText("");
       vistaLogin.txtpass.setText("");
   } 
   
   
  @Override
    public void actionPerformed(ActionEvent e) {
       
        if (e.getSource() == vistaLogin.btnIniciar) {
               
           
            
            modellogin.setUser(vistaLogin.txtUser.getText());
            modellogin.setClave(vistaLogin.txtpass.getText());
            modellogin.setTipo(vistaLogin.combosTipo.getSelectedItem().toString());
              if(vistaLogin.txtUser.getText().isEmpty()){ 
                    JOptionPane.showMessageDialog(null, "Ingrese el nombre de usuario");
              }else if(vistaLogin.txtpass.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Ingrese una contraseña");   
                  
              }else{
            //validamos estos datos 
            if (modelLoginDAO.ValidarLoginEmpleado(modellogin) && 
                    modellogin.getTipo().equalsIgnoreCase("Empleado")){
                
                
                 VistaAdministrador vistaAdmin = new VistaAdministrador();
                 VentaDAO modeloVentaDAO =new VentaDAO();
                  ventas medoloventas = new ventas();
                   venta_Detalle  ModeloVenta_Detalle = new venta_Detalle() ;
                  Venta_DetalleDAO ModeloVenta_DetalleDAO = new Venta_DetalleDAO();
                 Impresionn panel = new Impresionn();
                ControladorAdministrador ControlAdmin = new ControladorAdministrador(vistaAdmin, modeloVentaDAO, 
                medoloventas, ModeloVenta_Detalle, ModeloVenta_DetalleDAO,panel);
                 ControlAdmin.IniciarVistaAdmin();
                 vistaAdmin.setVisible(true);
                 vistaLogin.setVisible(false);
                 vistaAdmin.Menuconsultarventas.setVisible(false);
                 vistaAdmin.menuAdmistrarEmpleado.setVisible(false); 
                 vistaAdmin.menuProductos.setVisible(false);
                 
                   String Valor= vistaLogin.txtUser.getText();
                   int idE =modelLoginDAO.buscaridE(Valor).get(0).getIdE();
                   vistaAdmin.txtidvendedor.setText(String.valueOf(idE));
                    vistaAdmin.txtidvendedor.setEditable(false);
                    
                            
            }else if(modelLoginDAO.ValidarLoginEmpleado(modellogin) && 
                    modellogin.getTipo().equalsIgnoreCase("Administrador")){
                  VistaAdministrador vistaAdmin = new VistaAdministrador();
                 VentaDAO modeloVentaDAO =new VentaDAO();
                  ventas medoloventas = new ventas();
                   venta_Detalle  ModeloVenta_Detalle = new venta_Detalle() ;
                  Venta_DetalleDAO ModeloVenta_DetalleDAO = new Venta_DetalleDAO();
                Impresionn panel = new Impresionn();
                ControladorAdministrador ControlAdmin = new ControladorAdministrador(vistaAdmin, modeloVentaDAO, 
                medoloventas, ModeloVenta_Detalle, ModeloVenta_DetalleDAO,panel);
                ControlAdmin.IniciarVistaAdmin();
                vistaAdmin.setVisible(true);
                vistaLogin.setVisible(false);
                
                  String Valor= vistaLogin.txtUser.getText();

                  int idE =modelLoginDAO.buscaridE(Valor).get(0).getIdE();
                  vistaAdmin.txtidvendedor.setText(String.valueOf(idE));
                  vector[0]=idE;
                 vistaAdmin.txtidvendedor.setEditable(false);
                
            } else {
                JOptionPane.showMessageDialog(null, "Contraseña o Usuario Incorrecto ","Acceso al Sistema",
                        JOptionPane.ERROR_MESSAGE);
                limpiar();
            }
        }
        }
        if (e.getSource() == vistaLogin.btncancelar) {
            System.exit(0);
        }
    }  

  
}
