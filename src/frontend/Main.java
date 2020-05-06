package frontend;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import backend.ArbolGeneradorMinimo;
import backend.Localidad;
import backend.Red;
import backend.Tramo;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;


public class Main {

	private JFrame frame;
	private JTextField loc;
	private JTextField lat;
	private JTextField lon;
	private JLabel localidad;
	private JLabel provincia;
	private JLabel latitud;
	private JLabel longitud;
	private JComboBox<String> prov;
	private JButton agregar;
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	private Red red;
	private ArbolGeneradorMinimo agm;
	private JLabel costoKm;
	private JLabel costoFijo;
	private JLabel aumento;
	private JTextField costoPorKm;
	private JTextField raise;
	private JTextField fijo;
	private JLabel resultado;
	private JLabel local;
	private JLabel km;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		red = new Red();
		
		costoKm = new JLabel("Costo por Km (en pesos)");
		costoKm.setHorizontalAlignment(SwingConstants.CENTER);
		costoKm.setBounds(25, 35, 154, 16);
		frame.getContentPane().add(costoKm);
		
		aumento = new JLabel("% aumento > 300 km");
		aumento.setHorizontalAlignment(SwingConstants.CENTER);
		aumento.setBounds(192, 35, 134, 16);
		frame.getContentPane().add(aumento);
		
		costoFijo = new JLabel("Costo Fijo (en pesos)");
		costoFijo.setHorizontalAlignment(SwingConstants.CENTER);
		costoFijo.setBounds(338, 35, 132, 16);
		frame.getContentPane().add(costoFijo);
		
		costoPorKm = new JTextField();
		costoPorKm.setBounds(46, 63, 120, 30);
		frame.getContentPane().add(costoPorKm);
		costoPorKm.setColumns(10);
		
		raise = new JTextField();
		raise.setBounds(202, 63, 120 , 30);
		frame.getContentPane().add(raise);
		raise.setColumns(10);
		
		fijo = new JTextField();
		fijo.setBounds(348, 63, 120, 30);
		frame.getContentPane().add(fijo);
		fijo.setColumns(10);
		
		
		localidad = new JLabel("Localidad");
		localidad.setHorizontalAlignment(SwingConstants.CENTER);
		localidad.setBounds(25, 100, 100, 30);
		frame.getContentPane().add(localidad);
		
		provincia = new JLabel("Provincia");
		provincia.setHorizontalAlignment(SwingConstants.CENTER);
		provincia.setBounds(25, 140, 100, 30);
		frame.getContentPane().add(provincia);
		
		latitud = new JLabel("latitud");
		latitud.setHorizontalAlignment(SwingConstants.CENTER);
		latitud.setBounds(25, 180, 100, 30);
		frame.getContentPane().add(latitud);
		
		longitud = new JLabel("longitud");
		longitud.setHorizontalAlignment(SwingConstants.CENTER);
		longitud.setBounds(25, 216, 100, 30);
		frame.getContentPane().add(longitud);
		
		loc = new JTextField();
		loc.setBounds(137, 100, 120, 30);
		frame.getContentPane().add(loc);
		loc.setColumns(10);
		
		prov = new JComboBox<String>();
		prov.setBounds(137, 141, 120, 30);
		prov.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Buenos Aires", "Catamarca", "Chaco", "Chubut", "Cordoba", "Corrientes", "Entre Rios", "Formosa", "Jujuy", "La Pampa", "La Rioja", "Mendoza", "Misiones" , "Neuquen", "Rio Negro", "Salta", "San Juan", "San Luis", "Santa Cruz", "Santa Fe", "Santiago del Estero", "Tierra del Fuego", "Tucuman"}));
		frame.getContentPane().add(prov);
		
		lat = new JTextField();
		lat.setBounds(137, 180, 120, 30);
		frame.getContentPane().add(lat);
		lat.setColumns(10);
		
		lon = new JTextField();
		lon.setBounds(137, 216, 120, 30);
		frame.getContentPane().add(lon);
		lon.setColumns(10);
		
		agregar = new JButton("AGREGAR");
		agregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (loc.getText().equals("") || lat.getText().equals("") || lon.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Tiene que completar todos los casilleros para poder agregar los datos");
				}
				else if(!isNumeric(lat.getText()) || !isNumeric(lon.getText())) {
					JOptionPane.showMessageDialog(frame, "Los campos Latitud  Longitud deben ser numericos");
				}
				else if(costoPorKm.getText().equals("") || raise.getText().equals("") || fijo.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Los campos de costos deben estar completos");
				}
				else if( !isNumeric(costoPorKm.getText()) || !isNumeric(fijo.getText()) || !isNumeric(raise.getText())) {
					JOptionPane.showMessageDialog(frame, "Los campos de costos deben ser numericos");
				}
				else {
					Object data[] = {loc.getText(), prov.getSelectedItem(), lat.getText(), lon.getText()};
					
					model.addRow(data);
					
					double ckm = Double.valueOf(costoPorKm.getText());
					double aum = Double.valueOf(raise.getText());
					double cf = Double.valueOf(fijo.getText());
					
					Tramo.setCostoPorKm(ckm);
					Tramo.setAumento(aum);
					Tramo.setCostoFijo(cf);
					
					double latit = Double.valueOf(lat.getText());
					double longit = Double.valueOf(lon.getText());
					String provin = String.valueOf(prov.getSelectedItem());
					
					Localidad local = new Localidad (loc.getText(), provin, latit,longit);
					red.agregarLocalidad(local);
					
					JOptionPane.showMessageDialog(frame, "Datos agregados correctamente");
					loc.setText("");
					lat.setText("");
					lon.setText("");
					prov.setSelectedIndex(0);

					
				}
			}
		});
		agregar.setBounds(312, 140, 113, 56);
		frame.getContentPane().add(agregar);
		model = new DefaultTableModel();
		model.setColumnCount(4);
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 275, 400, 300);
		frame.getContentPane().add(scrollPane);
		
		
		
 		table = new JTable();
 		scrollPane.setViewportView(table);
 		table.setFillsViewportHeight(true);
 		table.setEnabled(false);
		table.setModel(model);
		
		JButton generarArbol = new JButton("Generar AGM");
		generarArbol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ret = table.getRowCount();
				if (ret == 0){
					JOptionPane.showMessageDialog(frame, "Por favor agregue datos a la tabla");
				}
				else if (red.getLocalidades().size()==1) {
					JOptionPane.showMessageDialog(frame, "Debe haber mas de una Localidad en la tabla para poder generar un Arbol Generador Minimo");
				}
				else {
					agm = new ArbolGeneradorMinimo(red);
					double result = agm.costoTotal();
					String result1 = String.valueOf(result);
					String locc = String.valueOf(agm.getLocalidades().size());
					resultado.setText(result1);
					local.setText(locc);
					km.setText(String.valueOf(agm.totalKm()));
				}	
			}
		});
		generarArbol.setBounds(164, 587, 120, 30);
		frame.getContentPane().add(generarArbol);
		
		resultado = new JLabel("resultado");
		resultado.setBounds(627, 187, 148, 59);
		frame.getContentPane().add(resultado);
		
		local = new JLabel("cantlocalidades");
		local.setBounds(627, 256, 61, 16);
		frame.getContentPane().add(local);
		
		km = new JLabel("km");
		km.setBounds(627, 328, 61, 16);
		frame.getContentPane().add(km);
		

		
	    table.getColumnModel().getColumn(0).setHeaderValue(localidad.getText());
	    table.getTableHeader().resizeAndRepaint();
	    
	    table.getColumnModel().getColumn(1).setHeaderValue(provincia.getText());
	    table.getTableHeader().resizeAndRepaint();
	    
	    table.getColumnModel().getColumn(2).setHeaderValue(latitud.getText());
	    table.getTableHeader().resizeAndRepaint();
	    
	    table.getColumnModel().getColumn(3).setHeaderValue(longitud.getText());
	    table.getTableHeader().resizeAndRepaint();
	    
	}
		
	//devuelve true si el string pasasdo por parametro tiene equivalenta en formato entero
	public static boolean isNumeric(String string) {
	    if (string == null) {
	        return false;
	    }
	    try {
	        Double.valueOf(string);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
}
