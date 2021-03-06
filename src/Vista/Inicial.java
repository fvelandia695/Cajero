package Vista;

import Logica.Cajero;
import Logica.Cliente;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;

public class Inicial extends JFrame implements ActionListener{
    
    JPanel cp;
    JLabel l1 = new JLabel("Bienvenido, ingrese el nombre de la tarjeta:", SwingConstants.CENTER);
    JTextField nombre = new JTextField();
    JButton ingresar = new JButton("Ingresar");
    Cajero cajero;
    
    public Inicial() throws HeadlessException {
        super("Ingresar tarjeta");
        this.initComp();
    }
    
    public Inicial(Cajero cajero) throws HeadlessException {
        super("Ingresar tarjeta");
        this.initComp();
        this.cajero=cajero;
    }
    
    private void initComp(){
        cajero=null;
        this.setSize(700, 200);
        this.setLocationRelativeTo(null);
        cp = new JPanel();
        
        this.setContentPane(cp);
        cp.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        cp.setBackground(new Color(165, 234, 70));
        
        l1.setBounds(0, 30, 700, 30);
        l1.setFont(new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 20));
        cp.add(l1);
        
        nombre.setBounds(250, 70, 150, 30);
        nombre.setFont(new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 20));
        cp.add(nombre); 
        
        ingresar.setBounds(250,110,150,30);
        ingresar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        ingresar.setBackground(Color.WHITE);
        cp.add(ingresar);
        ingresar.addActionListener(this);
        
        this.setVisible(true);          
    }
    
    public static void main (String[] args){
        new Inicial();
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e){
        if(e.getSource()==ingresar){
            try {
                this.setVisible(false);
                System.out.println("Ingresando");
                Cliente cliente=null;
                if(this.cajero==null){
                    cliente = new Cliente(nombre.getText());
                }
                else{
                    cliente = new Cliente(nombre.getText(),cajero);
                }
                if (cliente.getCajero().validarSerial())
                    new Operacion(cliente);
            } catch (SQLException ex) {
                System.out.println("No se pudo ingresar: "+ex);
            }
        }
    }
    
}
