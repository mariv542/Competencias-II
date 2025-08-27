
package db;

import dao.DaoAsistencia;
import dao.DaoUsuario;

/**
 *
 * @author cristobalO.O
 */
public class DataManager {
    
    private Conexion conexion;
    private DaoUsuario dataUsuario;
    private DaoAsistencia dataAsistencia;
    
    public DataManager() {
        conexion = Conexion.getConnection("ControlAsistencia");
        dataUsuario = new DaoUsuario(conexion);
        dataAsistencia = new DaoAsistencia(conexion);
    }

    public DaoUsuario getDataUsuario() {
        return dataUsuario;
    }

    public DaoAsistencia getDataAsistencia() {
        return dataAsistencia;
    }
    
    
    
}
