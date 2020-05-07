package frontend;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

import backend.ArbolGeneradorMinimo;
import backend.Localidad;
import backend.Red;
import backend.Tramo;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


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
	private DefaultTableModel model_1;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane;
	private Red red;
	private ArbolGeneradorMinimo agm;
	private JLabel costoKm;
	private JLabel costoFijo;
	private JLabel aumento;
	private JTextField costoPorKm;
	private JTextField raise;
	private JTextField fijo;
	private JPanel controlMapa;
	private JMapViewer mapa;
	private JButton salir;
	private JPanel panelCostos;
	private JButton limpiarTabla;
	private JTable table_1;
	private JLabel costoTotal;
	private JTextField total;

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
		
		//boton para abandonar el juego
		salir = new JButton("");
		salir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				salir.setBorderPainted(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				salir.setBorderPainted(false);
			}
		});
		salir.setContentAreaFilled(false);
		salir.setBorderPainted(false);
		salir.setIcon(new ImageIcon(Main.class.getResource("/frontend/exit.png")));
		salir.setFont(new Font("Century Gothic", Font.BOLD, 17));
		salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		salir.setBounds(837, 605, 57, 67);
		frame.getContentPane().add(salir);
		
		controlMapa = new JPanel();
		controlMapa.setBounds(542, 35, 335, 259);
		controlMapa.setLayout(null);
		
		
		red = new Red();
		
		panelCostos = new JPanel();
		panelCostos.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "costos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(70, 130, 180)));
		panelCostos.setBounds(19, 19, 457, 87);
		frame.getContentPane().add(panelCostos);
		panelCostos.setLayout(null);
		
		costoKm = new JLabel("por Km (en pesos)");
		costoKm.setBounds(6, 18, 154, 16);
		panelCostos.add(costoKm);
		costoKm.setHorizontalAlignment(SwingConstants.CENTER);
		costoKm.setFont(new Font("Century Gothic", Font.BOLD, 11));
		
		aumento = new JLabel("% aumento > 300 km");
		aumento.setBounds(173, 18, 134, 16);
		panelCostos.add(aumento);
		aumento.setHorizontalAlignment(SwingConstants.CENTER);
		aumento.setFont(new Font("Century Gothic", Font.BOLD, 11));
		
		costoFijo = new JLabel("Fijo (en pesos)");
		costoFijo.setBounds(319, 18, 132, 16);
		panelCostos.add(costoFijo);
		costoFijo.setHorizontalAlignment(SwingConstants.CENTER);
		costoFijo.setFont(new Font("Century Gothic", Font.BOLD, 11));
		
		costoPorKm = new JTextField();
		costoPorKm.setBounds(27, 44, 120, 30);
		panelCostos.add(costoPorKm);
		costoPorKm.setColumns(10);
		
		raise = new JTextField();
		raise.setBounds(183, 44, 120, 30);
		panelCostos.add(raise);
		raise.setColumns(10);
		
		fijo = new JTextField();
		fijo.setBounds(329, 44, 120, 30);
		panelCostos.add(fijo);
		fijo.setColumns(10);
		
		
		localidad = new JLabel("Localidad");
		localidad.setHorizontalAlignment(SwingConstants.LEFT);
		localidad.setFont(new Font("Century Gothic", Font.BOLD, 13));
		localidad.setBounds(35, 132, 100, 30);
		frame.getContentPane().add(localidad);
		
		provincia = new JLabel("Provincia");
		provincia.setHorizontalAlignment(SwingConstants.LEFT);
		provincia.setFont(new Font("Century Gothic", Font.BOLD, 13));
		provincia.setBounds(35, 169, 100, 30);
		frame.getContentPane().add(provincia);
		
		latitud = new JLabel("Latitud");
		latitud.setHorizontalAlignment(SwingConstants.LEFT);
		latitud.setFont(new Font("Century Gothic", Font.BOLD, 13));
		latitud.setBounds(35, 206, 100, 30);
		frame.getContentPane().add(latitud);
		
		longitud = new JLabel("Longitud");
		longitud.setHorizontalAlignment(SwingConstants.LEFT);
		longitud.setFont(new Font("Century Gothic", Font.BOLD, 13));
		longitud.setBounds(35, 243, 100, 30);
		frame.getContentPane().add(longitud);
		
		loc = new JTextField();
		loc.setBounds(137, 132, 120, 30);
		frame.getContentPane().add(loc);
		loc.setColumns(10);
		
		prov = new JComboBox<String>();
		prov.setBounds(137, 169, 120, 30);
		prov.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Buenos Aires", "Catamarca", "Chaco", "Chubut", "Cordoba", "Corrientes", "Entre Rios", "Formosa", "Jujuy", "La Pampa", "La Rioja", "Mendoza", "Misiones" , "Neuquen", "Rio Negro", "Salta", "San Juan", "San Luis", "Santa Cruz", "Santa Fe", "Santiago del Estero", "Tierra del Fuego", "Tucuman"}));
		frame.getContentPane().add(prov);
		
		lat = new JTextField();
		lat.setBounds(137, 206, 120, 30);
		frame.getContentPane().add(lat);
		lat.setColumns(10);
		
		lon = new JTextField();
		lon.setBounds(137, 243, 120, 30);
		frame.getContentPane().add(lon);
		lon.setColumns(10);
		
		agregar = new JButton("");
		agregar.setBorderPainted(false);
		agregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				agregar.setBorderPainted(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				agregar.setBorderPainted(false);
			}
		});
		agregar.setForeground(new Color(0, 0, 0));
		agregar.setIcon(new ImageIcon(Main.class.getResource("/frontend/confirm.png")));
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
					
					Coordinate coord = new Coordinate(latit, longit);
					mapa.setDisplayPosition(coord, 6);
					MapMarker mark = new MapMarkerDot (loc.getText(), coord);
					mapa.addMapMarker(mark);					
					
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
		agregar.setBounds(317, 175, 66, 61);
		agregar.setFont(new Font("Century Gothic", Font.BOLD, 17));
		frame.getContentPane().add(agregar);
		model = new DefaultTableModel();
		model.setColumnCount(4);
				
		scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 300, 400, 300);
		frame.getContentPane().add(scrollPane);
				
 		table = new JTable();
 		scrollPane.setViewportView(table);
 		table.setFillsViewportHeight(true);
 		table.setEnabled(false);
		table.setModel(model);
		
		//este boton es para realizar el arbol generador minimo
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
					int result = (int) agm.costoTotal();
					
					for (Tramo t : agm.getTramos()) {
						Object datos[] = {t.getLocalidadOrigen().getNombre(), t.getLocalidadDestino().getNombre(), String.valueOf(t.getCostoTramo())};
						
						model_1.addRow(datos);
					}
					total.setText(String.valueOf(result));
					
				}	
			}
		});
		generarArbol.setBounds(238, 605, 120, 30);
		generarArbol.setFont(new Font("Century Gothic", Font.BOLD, 12));
		frame.getContentPane().add(generarArbol);
				
		limpiarTabla = new JButton("Limpiar Tablas");
		limpiarTabla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int count = model.getRowCount();
				for (int i = 0; i<count; i++) {
					for (int j = 0; j<4; j++) {
						model.setValueAt("", i, j);
					}
				}
				
				int count2 = model_1.getRowCount();
				for (int k = 0; k<count2; k++) {
					for (int l = 0; l<3; l++) {
						model_1.setValueAt("", k, l);
					}
				}
				
				red = new Red();
				JOptionPane.showMessageDialog(frame, "Tablas borradas correctamente");
				
			}
		});
		limpiarTabla.setBounds(83, 605, 120, 30);
		limpiarTabla.setFont(new Font("Century Gothic", Font.BOLD, 12));
		frame.getContentPane().add(limpiarTabla);
		
		//mapa para mostrar las localidades
		mapa = new JMapViewer();
		frame.getContentPane().add(mapa);
		mapa.setZoomControlsVisible(true);
		mapa.setBounds(503, 35, 371, 269);
		mapa.setLayout(null);
		
		Coordinate coordin = new Coordinate(-34.521806,-58.700915);
		mapa.setDisplayPosition(coordin, 6);
			
		JLabel arbolGM = new JLabel("Arbol Generador Minimo");
		arbolGM.setBounds(571, 341, 181, 15);
		arbolGM.setFont(new Font("Century Gothic", Font.BOLD, 13));
		frame.getContentPane().add(arbolGM);
		
		table_1 = new JTable();
		table_1.setBounds(481, 380, 371, 61);
		//frame.getContentPane().add(table_1);
		model_1 = new DefaultTableModel();
		model_1.setColumnCount(3);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(481, 380, 371, 61);
		frame.getContentPane().add(scrollPane_1);			
 		scrollPane_1.setViewportView(table_1);
 		
 		
 		table_1.setFillsViewportHeight(true);
 		table_1.setEnabled(false);
		table_1.setModel(model_1);
		
		costoTotal = new JLabel("Costo Total $");
		costoTotal.setBounds(550, 500, 98, 15);
		costoTotal.setFont(new Font("Century Gothic", Font.BOLD, 13));
		frame.getContentPane().add(costoTotal);
		
		total = new JTextField();
		total.setEnabled(false);
		total.setEditable(false);
		total.setBounds(660, 494, 122, 27);
		total.setFont(new Font("Century Gothic", Font.BOLD, 13));
		frame.getContentPane().add(total);
		total.setColumns(10);
		
	    table_1.getColumnModel().getColumn(0).setHeaderValue("Localidad");
	    table_1.getTableHeader().resizeAndRepaint();
	    
	    table_1.getColumnModel().getColumn(1).setHeaderValue("Localidad");
	    table_1.getTableHeader().resizeAndRepaint();
	    
	    table_1.getColumnModel().getColumn(2).setHeaderValue("Costo Tramo");
	    table_1.getTableHeader().resizeAndRepaint();
		
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
