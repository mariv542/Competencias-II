/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelTM;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Asistencia;

/**
 *
 * @author cristobalO.O
 */
public class TMAsistencia extends AbstractTableModel{
     
    private List<Asistencia> lista;

    public TMAsistencia(List data) {
        this.lista = data;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return this.lista.get(rowIndex).getUsuario().getNombre();
            case 1:
                return this.lista.get(rowIndex).getFechaHoraEntrada();
            case 2:
                return this.lista.get(rowIndex).getFechaHoraSalida();
            case 3:
                return this.lista.get(rowIndex).getEstado();
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        return switch (column) {
            case 0 ->"Nombre";
            case 1 ->"Hora Entrada";
            case 2 ->"Hora Salida";
            case 3 ->"Estado";
            default ->null;
        };
    }
}
