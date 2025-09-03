/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelTM;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Usuario;

/**
 *
 * @author cristobalO.O
 */
public class TMUsuario extends AbstractTableModel{
    
    private List<Usuario> lista;
    private Boolean[] modificarColumna;

    public TMUsuario(List<Usuario> dataU) {
        this.lista = dataU;
        this.modificarColumna = new Boolean[dataU.size()];
        for (int i = 0; i < modificarColumna.length; i++) {
            modificarColumna[i] = false;
        }
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 9; // 5 datos + 1 columna de checkboxes
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 8) {
            return Boolean.class;
        }
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 8;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "IDUsuario";
            case 1:
                return "Rut";
            case 2:
                return "Nombre";
            case 3:
                return "Apellido";
            case 4:
                return "Correo";
            case 5:
                return "Contraseña";
            case 6:
                return "Rol";
            case 7:
                return "Estado";
            case 8:
                return "Editar";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Usuario u = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return u.getIdUsuario();
            case 1:
                return u.getRut();
            case 2:
                return u.getNombre();
            case 3:
                return u.getApellido();
            case 4:
                return u.getCorreo();
            case 5:
                return u.getContraseña();
            case 6:
                return u.getRol();
            case 7:
                return u.getEstado();
            case 8:
                return modificarColumna[rowIndex];
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 8 && aValue instanceof Boolean) {
            modificarColumna[rowIndex] = (Boolean) aValue;
            fireTableCellUpdated(rowIndex, columnIndex);
        }
    }
    
}
