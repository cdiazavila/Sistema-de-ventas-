/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import static CONTROLADOR.ControladorLogin.vector;
import MODELO.*;
import VISTA.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author IESJOBE
 */
public class ControladorRegistroEmpleado implements ActionListener, MouseListener {

    private Login modeloLogin;
    private LoginDAO ModeloLoginDAO;
    private FormularioEmpleado formulatio;
    
    private VistaEmplado vistaE;

    public ControladorRegistroEmpleado(Login modeloLogin, LoginDAO ModeloLoginDAO,
            FormularioEmpleado formulatio, VistaEmplado vistaE) {
        this.modeloLogin = modeloLogin;
        this.ModeloLoginDAO = ModeloLoginDAO;
        this.formulatio = formulatio;
        this.vistaE = vistaE;
        
        this.vistaE.btnIngresar.addActionListener(this);
        this.formulatio.btnGuardar.addActionListener(this);
        this.vistaE.btnActualizar.addActionListener(this);
        this.formulatio.btnCancelar.addActionListener(this);
        this.vistaE.btnBuscarE.addActionListener(this);
        this.vistaE.btneliminar.addActionListener(this);
        this.vistaE.btnCancelar.addActionListener(this);
        
        this.vistaE.jMenuSalir.addMouseListener(this);
        this.vistaE.jMenuGenerarVentas.addMouseListener(this);
        this.vistaE.menuProductos.addMouseListener(this);
        this.vistaE.jMenuconsultarventas.addMouseListener(this);
    }
   
       public void IniciarVistaEmpleado() {
       vistaE.setLocationRelativeTo(null);
       vistaE.setTitle("Ventana Empleados");
      
    }
    
    
    
     public void Limpiar(){
        formulatio.txtcc.setText("");
        formulatio.txtnombre.setText("");
        formulatio.txtcontra.setText("");
        formulatio.txtuser.setText("");
        }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==vistaE.btnIngresar){
             formulatio.setTitle("FORMULARIO");
            formulatio.setVisible(true);
            vistaE.setEnabled(false);
           
             formulatio.txtcc.setEditable(true);
             formulatio.setLocationRelativeTo(null);
               this.Limpiar();
        }  
       
       
          if(e.getSource()==formulatio.btnCancelar){
            vistaE.setEnabled(true);
            formulatio.setVisible(false);
             
     
         }
       
    
        if (e.getSource()==formulatio.btnGuardar)
       {
           
           if(formulatio.txtcc.getText().isEmpty()|| formulatio.txtnombre.getText().isEmpty() || 
              formulatio.txtuser.getText().isEmpty() || formulatio.txtcontra.getText().isEmpty()){
               JOptionPane.showMessageDialog(null, "Algunos campos estan vacios ");
           }else{
            int Respuesta = JOptionPane.showConfirmDialog(null, "¿Seguro Que Quieres Registra este empleado?");
               if (Respuesta == 0) {
                   
               
                    modeloLogin.setIdE(Integer.parseInt(formulatio.txtcc.getText()));
                if(ModeloLoginDAO.Buscar(modeloLogin)==false){
             
             
              modeloLogin.setNombreE(formulatio.txtnombre.getText());
              modeloLogin.setUser(formulatio.txtuser.getText());
              modeloLogin.setClave(formulatio.txtcontra.getText());
              modeloLogin.setTipo(formulatio.combosTipo.getSelectedItem().toString());                 
                
              if( ModeloLoginDAO.Registra(modeloLogin)){
                this.Limpiar();
             JOptionPane.showMessageDialog(null ,"Se registro un empleado");  
               
             }else {
                 JOptionPane.showMessageDialog(null," datos no se registraron","Error",JOptionPane.ERROR_MESSAGE);
             }
                }
          else{
             modeloLogin.setIdE(Integer.parseInt(formulatio.txtcc.getText()));
             modeloLogin.setNombreE(formulatio.txtnombre.getText());
             modeloLogin.setUser(formulatio.txtuser.getText());
             modeloLogin.setClave(formulatio.txtcontra.getText());
              
                if (ModeloLoginDAO.Modificar(modeloLogin)) {
                JOptionPane.showMessageDialog(null, "  se  Modifico el empleado  Correctamente");
                this.Limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Al  Modificar los Datos","ERROR",JOptionPane.ERROR_MESSAGE);
            }
                }
               }
           }
          
       }
       
        
         if(e.getSource()==vistaE.btnBuscarE){
       // si el campo esta vacio debe llenarse 
       if(!vistaE.txtidE.getText().equalsIgnoreCase("")){
           modeloLogin.setIdE(Integer.parseInt(vistaE.txtidE.getText()));
           if(ModeloLoginDAO.Buscar(modeloLogin)){
              
               vistaE.txtNombre.setText(modeloLogin.getNombreE());
               vistaE.txtuser.setText(modeloLogin.getUser());
               vistaE.txtContra.setText(modeloLogin.getClave());
               vistaE.txtTipo.setText(modeloLogin.getTipo());
           }else {
               JOptionPane.showMessageDialog(null," El empleado no esta registardo","Error",JOptionPane.ERROR_MESSAGE); 
               this.Limpiar();
           }
       }else{
             JOptionPane.showMessageDialog(null,"Ingrese el id del empleado a buscar");
       }
       
   }
    
         
       
          if(e.getSource()==vistaE.btnActualizar){
            
              if(!vistaE.txtidE.getText().equalsIgnoreCase("")){
                   formulatio.setVisible(true);
                   formulatio.setLocationRelativeTo(null);
                   vistaE.setEnabled(false);
                  modeloLogin.setIdE(Integer.parseInt(vistaE.txtidE.getText()));
                if(ModeloLoginDAO.Buscar(modeloLogin)){
                  formulatio.txtcc.setText(String.valueOf(modeloLogin.getIdE()));
                  formulatio.txtnombre.setText(modeloLogin.getNombreE());
                  formulatio.txtuser.setText(modeloLogin.getUser());
                  formulatio.txtcontra.setText(modeloLogin.getClave());
          }
          formulatio.txtcc.setEditable(false); 
              }else{
             JOptionPane.showMessageDialog(null,"Ingrese el id del producto que quiere actualizar");
       }
             
       
            
    }    
      
          
           if(e.getSource()==vistaE.btneliminar){
                 if(!vistaE.txtidE.getText().equalsIgnoreCase("")){
               modeloLogin.setIdE(Integer.parseInt(vistaE.txtidE.getText()));
             if(ModeloLoginDAO.Buscar(modeloLogin)){
                  int Respuesta = JOptionPane.showConfirmDialog(null, "SEGURO QUE QUIERE ELIMINAR EL EMPLEADO: \n"
                         + " "+ modeloLogin.getNombreE()+" ?");

                    if (Respuesta == 0) {
                        modeloLogin.setIdE(Integer.parseInt(vistaE.txtidE.getText()));
                        ModeloLoginDAO.Eliminar(modeloLogin);
                        JOptionPane.showMessageDialog(null, "EL EMPLEADO SE ELIMINO CORRECTAMENTE!! ");
                        vistaE.txtidE.setText("");
                    }
                   
                    vistaE.txtidE.setText("");
           }else {
               JOptionPane.showMessageDialog(null," El empleado no esta registardo","Error",JOptionPane.ERROR_MESSAGE); 
               vistaE.txtidE.setText("");
           }
                 }else{
                 JOptionPane.showMessageDialog(null,"Ingrese el id del empleado a eliminar");
              
                 }
             
          }
       
        
             if(e.getSource()==vistaE.btnCancelar){
               VistaAdministrador vistaAdmi=new VistaAdministrador();
               VentaDAO modeloVentaDAO =new VentaDAO();
               ventas medoloventas = new ventas();
               venta_Detalle  ModeloVenta_Detalle = new venta_Detalle() ;
               Venta_DetalleDAO ModeloVenta_DetalleDAO = new Venta_DetalleDAO();
               Impresionn panel = new Impresionn();
               ControladorAdministrador control = new ControladorAdministrador(vistaAdmi, modeloVentaDAO, medoloventas
               , ModeloVenta_Detalle,ModeloVenta_DetalleDAO,panel);
               control.IniciarVistaAdmin();
               vistaAdmi.setVisible(true);
               vistaE.setVisible(false);
           }
           
           
    }

    @Override
    public void mouseClicked(MouseEvent e) {
         if(e.getSource()==vistaE.jMenuSalir)
      {
       Login modelologin = new Login();
       LoginDAO modeloLoginDAO= new LoginDAO();
       vistaLogin vistalogin=new vistaLogin();
       ControladorLogin login = new ControladorLogin(modelologin, modeloLoginDAO, vistalogin);
       login.InicarVistaLogin();
       vistalogin.setVisible(true);
       vistaE.setVisible(false);
      }
         
         
         
         if(e.getSource()==vistaE.jMenuGenerarVentas)
      {
       VistaAdministrador vistaAdmi =new VistaAdministrador();
       VentaDAO modeloVentaDAO = new VentaDAO();
       ventas medoloventas = new ventas();
       venta_Detalle  ModeloVenta_Detalle = new venta_Detalle() ;
      Venta_DetalleDAO ModeloVenta_DetalleDAO = new Venta_DetalleDAO();
      Impresionn panel = new Impresionn();
       ControladorAdministrador Admin = new ControladorAdministrador(vistaAdmi, modeloVentaDAO, medoloventas
       , ModeloVenta_Detalle,ModeloVenta_DetalleDAO,panel);
       Admin.IniciarVistaAdmin();
       vistaAdmi.setVisible(true);
       vistaE.setVisible(false);
        vistaAdmi.txtidvendedor.setText(String.valueOf(vector[0]));
        vistaAdmi.txtidvendedor.setEditable(false);
       
      }  
         
         
          if(e.getSource()== vistaE.menuProductos){
             producto modeloproducto = new producto();
             productoDAO modeloproductoDAO = new productoDAO();
              VistaProducto vistaP=new  VistaProducto();
              formularioIngresarPro vistaIngreso=new  formularioIngresarPro();
           ControladorProductos controlPro=new ControladorProductos(vistaP, modeloproducto, modeloproductoDAO,vistaIngreso);
              controlPro.IniciarVistaProducto();
              vistaP.setVisible(true);
              vistaE.setVisible(false);
      }
          
          if (e.getSource()==vistaE.jMenuconsultarventas){
           venta_Detalle modeloVentaDetalle=new venta_Detalle();
           Venta_DetalleDAO ModeloDetalleDAO=new Venta_DetalleDAO();
            VistaReporte vistaR = new VistaReporte();
             controladorGenerarReporte controlR=new controladorGenerarReporte(modeloVentaDetalle, ModeloDetalleDAO,
                     vistaR);
             controlR.InicioListaVentas();
             vistaR.setVisible(true);
             vistaE.setVisible(false);

      }
    }

    
    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       
    }

    @Override
    public void mouseExited(MouseEvent e) {
       
    }
    
}
