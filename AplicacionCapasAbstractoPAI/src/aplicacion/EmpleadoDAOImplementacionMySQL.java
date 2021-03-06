/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import com.sun.istack.internal.logging.Logger;
import datos.BaseDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

/**
 *
 * @author EST_2F_MCB_PC21_
 */
public class EmpleadoDAOImplementacionMySQL extends EmpleadoDAO{

    @Override
    public Collection<EmpleadoDTO> obtenerPrimerosEmpleados(int id) {
        
        PreparedStatement pstatement=null;
        ResultSet resultSet=null;
        String sql ="select e.identificacion, e.nombres, e.apellidos, curdate() ";
        sql = sql +"from empleados e";
        sql = sql +" order by 2";
        sql = sql +"  limit 0,"+id;
        
        Vector<EmpleadoDTO> listado = new Vector<EmpleadoDTO>();
        try{
            Connection conne = BaseDatos.getConection();
            pstatement = conne.prepareStatement(sql);
            resultSet = pstatement.executeQuery();
            while(resultSet.next()){
            
                EmpleadoDTO empleado = new EmpleadoDTO(resultSet.getDouble(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));
                listado.add(empleado);
            }
        }catch(SQLException ex){System.out.println(ex);}
        
        return listado;
    }
    
}
