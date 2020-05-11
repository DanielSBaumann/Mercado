package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import control.Pagamento;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TPagamento extends TCaixa {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTotal;
	JComboBox<String> comboBox;
	private JTextField txtDinheiro;
	private JTextField txtTroco;

	public void limparTela() {
		txtTotal.setText("");
		txtDinheiro.setText("");
		txtTroco.setText("");
		comboBox.setSelectedIndex(-1);
	}

	public TPagamento(String pagamento, String total, JFrame TCaixa) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Pagamento");
		setBounds(100, 100, 537, 451);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblTotal = new JLabel("Total :");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTotal.setBounds(38, 78, 46, 14);
		panel.add(lblTotal);

		txtTotal = new JTextField();
		txtTotal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTotal.setHorizontalAlignment(SwingConstants.CENTER);
		txtTotal.setEditable(false);
		txtTotal.setBounds(111, 69, 290, 34);
		panel.add(txtTotal);
		txtTotal.setColumns(10);
		txtTotal.setText(total);

		JLabel lblFormaDePagamento = new JLabel("Forma de Pagamento :");
		lblFormaDePagamento.setBounds(10, 129, 118, 23);
		panel.add(lblFormaDePagamento);

		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Dinheiro", "Cart\u00E3o D\u00E9bito",
				"Cart\u00E3o Cr\u00E9dito", "Ticket", "Sodexo" }));
		comboBox.setBounds(150, 130, 167, 22);
		comboBox.setSelectedIndex(-1);
		panel.add(comboBox);

		JLabel lblDinheiro = new JLabel("Dinheiro :");
		lblDinheiro.setBounds(70, 254, 46, 14);
		panel.add(lblDinheiro);

		txtDinheiro = new JTextField();
		txtDinheiro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// *************************************************************************
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String pay = (String) comboBox.getSelectedItem();
					String dinheiro = txtDinheiro.getText();
					if (pay == null) {
						JOptionPane.showMessageDialog(null, "Preencha forma de pagamento!", null, ERROR_MESSAGE);
					} else {
						Pagamento p = new Pagamento();
						if (!dinheiro.isEmpty() && p.verificaNumero(txtDinheiro.getText())) {
							String troco = p.pagamento(pay, total, dinheiro);
							if (troco == null) {
								JOptionPane.showMessageDialog(null, "Pagamento insuficiente!", null, ERROR_MESSAGE);
							} else {
								txtTroco.setText(troco);
								if (p.salvarPagamento(pagamento, total, pay, dinheiro, troco)) {
									JOptionPane.showMessageDialog(null,
											"Pagamento salvo com sucesso!\nTroco :   " + troco);
									limparTela();
									p.exibirCupom(pagamento);
									setVisible(false);
									((view.TCaixa) TCaixa).limparPagamento();
									TCaixa.setVisible(true);
								}
							}
						}
					}
				}
				// *************************************************************************
			}
		});
		txtDinheiro.setHorizontalAlignment(SwingConstants.CENTER);
		txtDinheiro.setBounds(137, 244, 106, 34);
		panel.add(txtDinheiro);
		txtDinheiro.setColumns(10);

		JLabel lblTroco = new JLabel("Troco :");
		lblTroco.setBounds(82, 299, 46, 14);
		panel.add(lblTroco);

		txtTroco = new JTextField();
		txtTroco.setHorizontalAlignment(SwingConstants.CENTER);
		txtTroco.setEditable(false);
		txtTroco.setColumns(10);
		txtTroco.setBounds(137, 289, 106, 34);
		panel.add(txtTroco);

		JButton btnPagamento = new JButton("Registrar Pagamento");
		btnPagamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// *************************************************************************
				String pay = (String) comboBox.getSelectedItem();
				String dinheiro = txtDinheiro.getText();
				if (pay == null) {
					JOptionPane.showMessageDialog(null, "Preencha forma de pagamento!", null, ERROR_MESSAGE);
				} else {
					switch (pay) {
					case "Dinheiro": {
						Pagamento p = new Pagamento();
						if (!dinheiro.isEmpty() && p.verificaNumero(txtDinheiro.getText())) {
							String troco = p.pagamento(pay, total, dinheiro);
							if (troco == null) {
								JOptionPane.showMessageDialog(null, "Pagamento insuficiente!", null, ERROR_MESSAGE);
							} else {
								txtTroco.setText(troco);
								if (p.salvarPagamento(pagamento, total, pay, dinheiro, troco)) {
									JOptionPane.showMessageDialog(null,
											"Pagamento salvo com sucesso!\nTroco :   " + troco);
									limparTela();
									p.exibirCupom(pagamento);
									setVisible(false);
									((view.TCaixa) TCaixa).limparPagamento();
									TCaixa.setVisible(true);
								}
							}
						}
						break;
					}
					default: {
						Pagamento p = new Pagamento();
						if (p.salvarPagamento(pagamento, total, pay, total)) {
							JOptionPane.showMessageDialog(null, "Pagamento salvo com sucesso!");
							limparTela();
							p.exibirCupom(pagamento);
							setVisible(false);
							((view.TCaixa) TCaixa).limparPagamento();
							TCaixa.setVisible(true);
						}
						break;
					}
					}
				}
				// *************************************************************************
			}
		});
		btnPagamento.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnPagamento.setBounds(337, 235, 118, 41);
		panel.add(btnPagamento);

		JButton btnDados = new JButton("Base Dados\r\n Pagamento");
		btnDados.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnDados.setBounds(337, 287, 118, 41);
		panel.add(btnDados);

		JButton btnCaixa = new JButton("Voltar para Caixa");
		btnCaixa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// ***********************
				TCaixa.setVisible(true);
				setVisible(false);
				// ***********************
			}
		});
		btnCaixa.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnCaixa.setBounds(337, 337, 118, 41);
		panel.add(btnCaixa);
	}
}
