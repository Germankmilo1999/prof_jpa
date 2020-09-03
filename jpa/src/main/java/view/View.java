package view;

import persistence.BillsJPA;
import persistence.CustomersJPA;
import persistence.ProductsJPA;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import model.Customer;
import model.Product;
import model.Bill;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.JScrollPane;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;

public class View {
	private int totalPrice = 0;
	List <Integer> productsTotalPrice = new ArrayList<Integer>();
	List <Integer> products = new ArrayList<Integer>();

	private static JFrame frame;
	private JTextField inputNewBillCustomerId;
	private JTextField customerInfo;
	private JTable tableCustomers;
	private JTextField txtCustomerID;
	private JTextField lblCustomerID;
	private JTextField txtId;
	private JTextField inputNewCustomerId;
	private JTextField lblCustomerName;
	private JTextField inputNewCustomerName;
	private JTextField lblCustomerPhone;
	private JTextField inputNewCustomerPhone;
	private JTextField inputNumBill;
	private JTextField lblNumBill;
	private JTable tableBills;
	private JTable tableProducts;
	private JTextField lblProductoId;
	private JTextField inputProductId;
	private JTable tableBill;
	private JTextField lblNewProductId;
	private JTextField inputNewProductId;
	private JTextField lblNewProductName;
	private JTextField inputNewProductName;
	private JTextField lblNewProductPrice;
	private JTextField inputNewProductPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View window = new View();
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
	public View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 600, 362);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 574, 311);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panelBill = new JPanel();
		tabbedPane.addTab("Facturar", null, panelBill, null);
		panelBill.setLayout(null);
		
		inputNewBillCustomerId = new JTextField();
		inputNewBillCustomerId.setBounds(96, 11, 140, 20);
		panelBill.add(inputNewBillCustomerId);
		inputNewBillCustomerId.setColumns(10);
		
		final JButton btnFinishBill = new JButton("Finalizar");
		btnFinishBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				finishBill();
			}
		});
		btnFinishBill.setEnabled(false);
		btnFinishBill.setBounds(231, 240, 115, 32);
		panelBill.add(btnFinishBill);
		
		JButton btnNewButton = new JButton("Crear Factura");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(inputNewBillCustomerId.getText() != "")
				{
					Customer ct = CustomersJPA.listCustomer(Integer.parseInt(inputNewBillCustomerId.getText()));
					customerInfo.setText(ct.getIdCustomer() + " - " + ct.getNameCustomer() + " - " + ct.getPhoneCustomer());
					btnFinishBill.setEnabled(true);
					createBill();
				} else
				{
					JOptionPane.showMessageDialog(frame, "Usuario no valido");
				}
			}
		});
		btnNewButton.setBounds(419, 11, 140, 32);
		panelBill.add(btnNewButton);
		
		JTextPane txtpnClienteid = new JTextPane();
		txtpnClienteid.setText("Cliente (ID)");
		txtpnClienteid.setEditable(false);
		txtpnClienteid.setBounds(10, 11, 76, 20);
		panelBill.add(txtpnClienteid);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 70, 549, 159);
		panelBill.add(scrollPane);
		
		tableBill = new JTable();
		scrollPane.setViewportView(tableBill);
		
		customerInfo = new JTextField();
		customerInfo.setEditable(false);
		customerInfo.setEnabled(true);
		customerInfo.setBounds(10, 39, 268, 20);
		panelBill.add(customerInfo);
		customerInfo.setColumns(10);
		
		JPanel panelProducts = new JPanel();
		tabbedPane.addTab("Productos", null, panelProducts, null);
		panelProducts.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 45, 549, 167);
		panelProducts.add(scrollPane_3);

		JButton btnCreateProduct = new JButton("Crear");
		btnCreateProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Product pd = new Product(inputNewProductName.getText(), Integer.parseInt(inputNewProductPrice.getText()));
				try
				{
					ProductsJPA.createProduct(pd);
					JOptionPane.showMessageDialog(frame, "Producto Creado");
				} catch (Exception error)
				{
					JOptionPane.showMessageDialog(frame, "Error: " + error);
				}
				
				inputNewProductName.setText("");
				inputNewProductPrice.setText("");
				getProductsTable(0);
			}
		});
		btnCreateProduct.setBounds(10, 249, 89, 23);
		panelProducts.add(btnCreateProduct);
		
		final JButton btnModifyProduct = new JButton("Modificar");
		btnModifyProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					ProductsJPA.updateProduct(Integer.parseInt(inputNewProductId.getText()), inputNewProductName.getText(), Integer.parseInt(inputNewProductPrice.getText()));
					JOptionPane.showMessageDialog(frame, "Producto Modificado");
					getProductsTable(0);
				} catch (Exception error)
				{
					JOptionPane.showMessageDialog(frame, "Error: " + error);
				}
			}
		});
		btnModifyProduct.setEnabled(false);
		btnModifyProduct.setBounds(248, 248, 89, 23);
		panelProducts.add(btnModifyProduct);
		
		final JButton btnDeleteProduct = new JButton("Eliminar");
		btnDeleteProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					ProductsJPA.deleteProduct(Integer.parseInt(inputNewProductId.getText()));
					JOptionPane.showMessageDialog(frame, "Producto Eliminado");
					inputNewProductName.setText("");
					inputNewProductPrice.setText("");
					getProductsTable(0);
				} catch (Exception error)
				{
					JOptionPane.showMessageDialog(frame, "Error: " + error);
				}
			}
		});
		btnDeleteProduct.setEnabled(false);
		btnDeleteProduct.setBounds(470, 249, 89, 23);
		panelProducts.add(btnDeleteProduct);
		
		tableProducts = new JTable();
		tableProducts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				inputNewProductId.setText(tableProducts.getValueAt(tableProducts.getSelectedRow(), 0).toString());
				inputNewProductName.setText(tableProducts.getValueAt(tableProducts.getSelectedRow(), 1).toString());
				inputNewProductPrice.setText(tableProducts.getValueAt(tableProducts.getSelectedRow(), 2).toString());
				
				btnModifyProduct.setEnabled(true);
				btnDeleteProduct.setEnabled(true);
			}
		});
		scrollPane_3.setViewportView(tableProducts);
		
		JButton btnListProducts = new JButton("Listar Productos");
		btnListProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getProductsTable(0);
			}
		});
		btnListProducts.setBounds(426, 11, 133, 23);
		panelProducts.add(btnListProducts);
		
		lblProductoId = new JTextField();
		lblProductoId.setEditable(false);
		lblProductoId.setText("Producto (ID)");
		lblProductoId.setBounds(10, 11, 86, 20);
		panelProducts.add(lblProductoId);
		lblProductoId.setColumns(10);
		
		inputProductId = new JTextField();
		inputProductId.setText("");
		inputProductId.setBounds(111, 12, 86, 20);
		panelProducts.add(inputProductId);
		inputProductId.setColumns(10);
		
		JButton btnListProduct = new JButton("Listar Producto");
		btnListProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getProductsTable(Integer.parseInt(inputProductId.getText()));
			}
		});
		btnListProduct.setBounds(215, 11, 122, 23);
		panelProducts.add(btnListProduct);
		
		lblNewProductId = new JTextField();
		lblNewProductId.setText("ID");
		lblNewProductId.setEditable(false);
		lblNewProductId.setBounds(10, 223, 27, 20);
		panelProducts.add(lblNewProductId);
		lblNewProductId.setColumns(10);
		
		inputNewProductId = new JTextField();
		inputNewProductId.setEditable(false);
		inputNewProductId.setBounds(47, 223, 86, 20);
		panelProducts.add(inputNewProductId);
		inputNewProductId.setColumns(10);
		
		lblNewProductName = new JTextField();
		lblNewProductName.setEditable(false);
		lblNewProductName.setText("Nombre");
		lblNewProductName.setBounds(172, 223, 57, 20);
		panelProducts.add(lblNewProductName);
		lblNewProductName.setColumns(10);
		
		inputNewProductName = new JTextField();
		inputNewProductName.setBounds(239, 223, 122, 20);
		panelProducts.add(inputNewProductName);
		inputNewProductName.setColumns(10);
		
		lblNewProductPrice = new JTextField();
		lblNewProductPrice.setText("Precio");
		lblNewProductPrice.setEditable(false);
		lblNewProductPrice.setBounds(412, 223, 51, 20);
		panelProducts.add(lblNewProductPrice);
		lblNewProductPrice.setColumns(10);
		
		inputNewProductPrice = new JTextField();
		inputNewProductPrice.setBounds(473, 223, 86, 20);
		panelProducts.add(inputNewProductPrice);
		inputNewProductPrice.setColumns(10);

		JPanel panelCustomers = new JPanel();
		tabbedPane.addTab("Clientes", null, panelCustomers, null);
		panelCustomers.setLayout(null);
		
		JButton btnListCustomers = new JButton("Listar Todos");
		btnListCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getCustomersTable(0);
			}
		});
		btnListCustomers.setBounds(430, 11, 129, 23);
		panelCustomers.add(btnListCustomers);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 45, 549, 166);
		panelCustomers.add(scrollPane_1);
		
		final JButton btnModifyCustomer = new JButton("Modificar");
		btnModifyCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					CustomersJPA.updateCustomers(Integer.parseInt(inputNewCustomerId.getText()), inputNewCustomerName.getText(), Integer.parseInt(inputNewCustomerPhone.getText()));
					JOptionPane.showMessageDialog(frame, "Usuario Modificado");
					getCustomersTable(0);
				} catch (Exception error)
				{
					JOptionPane.showMessageDialog(frame, "Error: " + error);
				}
			}
		});
		btnModifyCustomer.setEnabled(false);
		btnModifyCustomer.setBounds(242, 249, 89, 23);
		panelCustomers.add(btnModifyCustomer);

		final JButton btnDeleteCustomer = new JButton("Eliminar");
		btnDeleteCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					CustomersJPA.deleteCustomers(Integer.parseInt(inputNewCustomerId.getText()));
					JOptionPane.showMessageDialog(frame, "Usuario Eliminado");
					inputNewCustomerName.setText("");
					inputNewCustomerPhone.setText("");
					getCustomersTable(0);
				} catch (Exception error)
				{
					JOptionPane.showMessageDialog(frame, "Error: " + error);
				}
			}
		});
		btnDeleteCustomer.setEnabled(false);
		btnDeleteCustomer.setBounds(470, 249, 89, 23);
		panelCustomers.add(btnDeleteCustomer);
		
		tableCustomers = new JTable();
		tableCustomers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				inputNewCustomerId.setText(tableCustomers.getValueAt(tableCustomers.getSelectedRow(), 0).toString());
				inputNewCustomerName.setText(tableCustomers.getValueAt(tableCustomers.getSelectedRow(), 1).toString());
				inputNewCustomerPhone.setText(tableCustomers.getValueAt(tableCustomers.getSelectedRow(), 2).toString());
				
				btnModifyCustomer.setEnabled(true);
				btnDeleteCustomer.setEnabled(true);
			}
		});
		scrollPane_1.setViewportView(tableCustomers);
		
		JButton btnListCustomer = new JButton("Listar Cliente");
		btnListCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer customerID = Integer.parseInt(txtCustomerID.getText());
				getCustomersTable(customerID);
			}
		});
		btnListCustomer.setBounds(222, 11, 119, 23);
		panelCustomers.add(btnListCustomer);
		
		txtCustomerID = new JTextField();
		txtCustomerID.setBounds(108, 12, 86, 20);
		panelCustomers.add(txtCustomerID);
		txtCustomerID.setColumns(10);
		
		lblCustomerID = new JTextField();
		lblCustomerID.setEnabled(true);
		lblCustomerID.setEditable(false);
		lblCustomerID.setText("Cliente (ID)");
		lblCustomerID.setBounds(10, 11, 86, 20);
		panelCustomers.add(lblCustomerID);
		lblCustomerID.setColumns(10);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setText("ID");
		txtId.setBounds(10, 222, 22, 20);
		panelCustomers.add(txtId);
		txtId.setColumns(10);
		
		inputNewCustomerId = new JTextField();
		inputNewCustomerId.setEditable(false);
		inputNewCustomerId.setBounds(39, 222, 79, 20);
		panelCustomers.add(inputNewCustomerId);
		inputNewCustomerId.setColumns(10);
		
		lblCustomerName = new JTextField();
		lblCustomerName.setText("Nombre");
		lblCustomerName.setEditable(false);
		lblCustomerName.setBounds(162, 222, 66, 20);
		panelCustomers.add(lblCustomerName);
		lblCustomerName.setColumns(10);
		
		inputNewCustomerName = new JTextField();
		inputNewCustomerName.setColumns(10);
		inputNewCustomerName.setBounds(242, 222, 111, 20);
		panelCustomers.add(inputNewCustomerName);
		
		lblCustomerPhone = new JTextField();
		lblCustomerPhone.setText("Telefono");
		lblCustomerPhone.setEditable(false);
		lblCustomerPhone.setColumns(10);
		lblCustomerPhone.setBounds(389, 222, 74, 20);
		panelCustomers.add(lblCustomerPhone);
		
		inputNewCustomerPhone = new JTextField();
		inputNewCustomerPhone.setColumns(10);
		inputNewCustomerPhone.setBounds(473, 222, 86, 20);
		panelCustomers.add(inputNewCustomerPhone);
		
		JButton btnCreateCustomer = new JButton("Crear");
		btnCreateCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Customer ct = new Customer(inputNewCustomerName.getText(), Integer.parseInt(inputNewCustomerPhone.getText()));
				try
				{
					CustomersJPA.createCustomers(ct);
					JOptionPane.showMessageDialog(frame, "Usuario Creado Exitosamente");
				} catch (Exception error)
				{
					JOptionPane.showMessageDialog(frame, "Error: " + error);
				}
				
				inputNewCustomerName.setText("");
				inputNewCustomerPhone.setText("");
				getCustomersTable(0);
			}
		});
		btnCreateCustomer.setBounds(10, 249, 89, 23);
		panelCustomers.add(btnCreateCustomer);
		
		JPanel panelBills = new JPanel();
		tabbedPane.addTab("Facturas", null, panelBills, null);
		panelBills.setLayout(null);
		
		JButton btnListBills = new JButton("Listar Todas");
		btnListBills.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getBillsTable(0);
			}
		});
		btnListBills.setBounds(445, 11, 114, 23);
		panelBills.add(btnListBills);
		
		inputNumBill = new JTextField();
		inputNumBill.setBounds(106, 12, 86, 20);
		panelBills.add(inputNumBill);
		inputNumBill.setColumns(10);
		
		JButton btnListBill = new JButton("Listar Factura");
		btnListBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 getBillsTable(Integer.parseInt(inputNumBill.getText()));
			}
		});
		btnListBill.setBounds(202, 11, 131, 23);
		panelBills.add(btnListBill);
		
		lblNumBill = new JTextField();
		lblNumBill.setText("Factura N\u00B0:");
		lblNumBill.setEditable(false);
		lblNumBill.setBounds(10, 12, 86, 20);
		panelBills.add(lblNumBill);
		lblNumBill.setColumns(10);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 45, 549, 227);
		panelBills.add(scrollPane_2);
		
		tableBills = new JTable();
		scrollPane_2.setViewportView(tableBills);
	}
	
	public void createBill()
	{
		List <Integer> productsQuantity = new ArrayList<Integer>();
		
		boolean finish = false;
		
		while(finish == false)
		{
			String productId = JOptionPane.showInputDialog("Ingrese ID del producto:");
			int currentProduct = Integer.parseInt(productId);

			String productQuantity = JOptionPane.showInputDialog("Cantidad del producto:");
			int currentProductQuantity = Integer.parseInt(productQuantity);
			
			products.add(currentProduct);
			productsQuantity.add(currentProductQuantity);
			
			Product pd = ProductsJPA.listProduct(currentProduct);
			productsTotalPrice.add(currentProductQuantity * pd.getPrice());
			
			getBillTable(products, productsQuantity);
			if(currentProduct == 0)
			{
				finish = true;
				JOptionPane.showMessageDialog(frame, "Finished");
			}
		}
	}
	
	private void getCustomersTable(Integer id)
	{
		String cols[] = {"ID", "Nombre", "Telefono"};
		DefaultTableModel model = new DefaultTableModel(cols, 0);
		
		if(id == 0 || id == null)
		{
			Object [] obj = new Object[3];
			try
			{
				
				List ls = CustomersJPA.listCustomers();
				List<Customer> resultList = ls;
				for (Customer ct : resultList) {
					obj[0] = ct.getIdCustomer();
					obj[1] = ct.getNameCustomer();
					obj[2] = ct.getPhoneCustomer();
					model.addRow(obj);
				}
				this.tableCustomers.setModel(model);
				
			} catch (Exception e)
			{
				JOptionPane.showMessageDialog(frame, "Error al cargar los datos");
			}
		} else 
		{
			//List specific customer
			Object [] obj = new Object[3];
			try
			{
				
				Customer ct = CustomersJPA.listCustomer(id);
				obj[0] = ct.getIdCustomer();
				obj[1] = ct.getNameCustomer();
				obj[2] = ct.getPhoneCustomer();
				model.addRow(obj);

				this.tableCustomers.setModel(model);
				
			} catch (Exception e)
			{
				JOptionPane.showMessageDialog(frame, "Cliente no encontrado");
			}
		}
		
	}
	
	private void getBillsTable(Integer id)
	{
		String cols[] = {"NUMERO", "CLIENTE ID", "PRODUCTOS", "PAGADO", "FECHA"};
		DefaultTableModel model = new DefaultTableModel(cols, 0);
		
		if(id == 0 || id == null)
		{
			Object [] obj = new Object[5];
			try
			{

				
				List ls = BillsJPA.listBills();
				List<Bill> resultList = ls;
				
				for (Bill bl : resultList) {
					StringJoiner allProducts = new StringJoiner(" ");
					String[] tProducts = bl.getProducts().split(",");
					
					Customer ct = CustomersJPA.listCustomer(bl.getIdCustomer());

					System.out.println("Antes: " + allProducts);
					for(int i = 0; i < tProducts.length; i++)
					{
						Product pd = ProductsJPA.listProduct(Integer.parseInt(tProducts[i]));
						allProducts.add(pd.getName() + ", ");
					}
					System.out.println("Despues: " + allProducts);
					
					obj[0] = bl.getNumBill();
					obj[1] = ct.getNameCustomer();
					obj[2] = allProducts;
					obj[3] = bl.getTotalPrice();
					obj[4] = bl.getDate();
					model.addRow(obj);
				}
				this.tableBills.setModel(model);
				
			} catch (Exception e)
			{
				System.out.println("Error: " + e);
				JOptionPane.showMessageDialog(frame, "Error al cargar los datos"+ e);
			}
		} else 
		{
			//List specific Bill
			Object [] obj = new Object[5];
			try
			{
				StringJoiner allProducts = new StringJoiner(" ");
				
				Bill bl = BillsJPA.listBill(id);
				
				String[] tProducts = bl.getProducts().split(",");
				
				Customer ct = CustomersJPA.listCustomer(bl.getIdCustomer());
				for(int i = 0; i < tProducts.length; i++)
				{
					Product pd = ProductsJPA.listProduct(Integer.parseInt(tProducts[i]));
					allProducts.add(pd.getName() + ", ");
				}
				
				obj[0] = bl.getNumBill();
				obj[1] = ct.getNameCustomer();
				obj[2] = allProducts;
				obj[3] = bl.getTotalPrice();
				obj[4] = bl.getDate();
				model.addRow(obj);
				
				this.tableBills.setModel(model);
				
			} catch (Exception e)
			{
				JOptionPane.showMessageDialog(frame, "Factura no encontrado");
			}
		}
	}
	
	private void getProductsTable(Integer id)
	{
		String cols[] = {"ID", "Nombre", "Precio"};
		DefaultTableModel model = new DefaultTableModel(cols, 0);
		
		if(id == 0 || id == null)
		{
			Object [] obj = new Object[3];
			try
			{
				
				List ls = ProductsJPA.listProducts();
				List<Product> resultList = ls;
				for (Product pd : resultList) {
					obj[0] = pd.getId();
					obj[1] = pd.getName();
					obj[2] = pd.getPrice();
					model.addRow(obj);
				}
				this.tableProducts.setModel(model);
				
			} catch (Exception e)
			{
				System.out.println("Error: " + e);
				JOptionPane.showMessageDialog(frame, "Error al cargar los datos");
			}
		} else 
		{
			//List specific Product
			Object [] obj = new Object[3];
			try
			{
				Product pd = ProductsJPA.listProduct(id);
				obj[0] = pd.getId();
				obj[1] = pd.getName();
				obj[2] = pd.getPrice();
				model.addRow(obj);
				
				this.tableProducts.setModel(model);
				
			} catch (Exception e)
			{
				JOptionPane.showMessageDialog(frame, "Producto no encontrado");
			}
		}
		
	}
	
	// ARREGLAR
	private void getBillTable(List products, List quantity)
	{
		String cols[] = {"PRODUCTO", "CANTIDAD", "PRECIO", "TOTAL"};
		DefaultTableModel model = new DefaultTableModel(cols, 0);
		
		Object [] obj = new Object[4];
		
		for(int i = 0; i < products.size(); i++)
		{
			Product pd = ProductsJPA.listProduct(Integer.parseInt(products.get(i).toString()));
			obj[0] = pd.getName();
			obj[1] = quantity.get(i);
			obj[2] = pd.getPrice();
			obj[3] = productsTotalPrice.get(i);
			
			model.addRow(obj);
		}
		this.tableBill.setModel(model);
	}
	
	private void finishBill()
	{
		String productsString = "";
		
		for(int i = 0; i < productsTotalPrice.size(); i++)
		{
			totalPrice = totalPrice + productsTotalPrice.get(i);
		}
		
		for (Integer s : products)
		{
			productsString += s + ",";
		}
		
		JOptionPane.showMessageDialog(frame, "Total a pagar: " + totalPrice);
		
		Bill bl = new Bill(Integer.parseInt(inputNewBillCustomerId.getText().toString()), productsString, totalPrice, LocalDateTime.now());
		System.out.println(Integer.parseInt(inputNewBillCustomerId.getText().toString()) + "|" + productsString + "|" + totalPrice + "" + LocalDateTime.now());
		try
		{
			BillsJPA.createBill(bl);
			JOptionPane.showMessageDialog(frame, "Factura registrada");
		} catch (Exception error)
		{
			JOptionPane.showMessageDialog(frame, "Error: " + error);
		}
		
		totalPrice = 0;
		DefaultTableModel model = new DefaultTableModel(0, 0);
		this.tableBill.setModel(model);
	}
}
