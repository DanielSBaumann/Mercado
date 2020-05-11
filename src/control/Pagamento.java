package control;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Pagamento {

	public double alteraSubtotal(double sub, String codigo, ArrayList<String> lista) {
		double totalLinha = 0;
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).startsWith(codigo)) {
				String linha = lista.get(i).substring(lista.get(i).lastIndexOf(" ") + 1);
				linha = linha.replace(",", ".");
				totalLinha = Double.parseDouble(linha);
			}
		}
		sub -= totalLinha;
		return sub;
	}

	public void cancelarItem(String codigo, ArrayList<String> lista) {
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).startsWith(codigo)) {
				lista.remove(i);
				lista.add(i, codigo + "   *****ITEM CANCELADO*****");
			}
		}
	}

	public void salvarCompras(String pagamento, String total, ArrayList<String> lista) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(pagamento));
			for (int i = 0; i < lista.size(); i++) {
				bw.write(lista.get(i));
				bw.newLine();
			}
			// bw.write("Total : " + total);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String incluir(int aux, String valor, String quantidade, String descricao, String medida) {
		DecimalFormat s = new DecimalFormat("0.00");
		double totalLinha = Double.parseDouble(valor) * Double.parseDouble(quantidade);
		String linha = Integer.toString(aux) + String.format("%18.16s", descricao) + String.format("%4.2s", medida)
				+ String.format("%8.6s", valor) + String.format("%4.3s", quantidade) + "  " + s.format(totalLinha);
		return linha;
	}

	public double subtotal(double sub, String valor, String quantidade) {
		double totalLinha = Double.parseDouble(valor) * Double.parseDouble(quantidade);
		sub += totalLinha;
		return sub;
	}

	public String pagamento(String pay, String total, String dinheiro) {
		// ****************************************************************
		String troco = null;
		total = total.replace(",", ".");
		if (Double.parseDouble(dinheiro) >= Double.parseDouble(total)) {
			double tr = Double.parseDouble(dinheiro) - Double.parseDouble(total);
			DecimalFormat s = new DecimalFormat("0.00");
			troco = s.format(tr);
		}
		return troco;
	}

	public boolean salvarPagamento(String pagamento, String total, String pay, String dinheiro, String troco) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(pagamento, true));
			bw.write("Total : " + total);
			bw.newLine();
			bw.write(pay + " : " + dinheiro);
			bw.newLine();
			bw.write("Troco : " + troco);
			bw.newLine();
			bw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean salvarPagamento(String pagamento, String total, String pay, String dinheiro) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(pagamento, true));
			bw.write("Total : " + total);
			bw.newLine();
			bw.write(pay + " : " + dinheiro);
			bw.newLine();
			bw.write("Troco : 0,00");
			bw.newLine();
			bw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean verifica(String letra) {
		char letras[] = { 'a', 'A', 'b', 'B', 'c', 'C', 'd', 'D', 'e', 'E', 'f', 'F', 'g', 'G', 'h', 'H', 'i', 'I', 'j',
				'J', 'k', 'K', 'l', 'L', 'm', 'M', 'n', 'N', 'o', 'O', 'p', 'P', 'q', 'Q', 'r', 'R', 's', 'S', 't', 'T',
				'u', 'U', 'v', 'V', 'w', 'W', 'x', 'X', 'y', 'Y', 'z', 'Z', 'ç', 'Ç', ' ' };
		int auxLetras = 0;
		for (int i = 0; i < letras.length; i++) {
			for (int j = 0; j < letra.length(); j++) {
				if (letras[i] == letra.charAt(j)) {
					auxLetras++;
				}
			}
		}
		if (auxLetras != letra.length() || letra.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public boolean verifica(String letra, String n) {
		char letras[] = { 'a', 'A', 'b', 'B', 'c', 'C', 'd', 'D', 'e', 'E', 'f', 'F', 'g', 'G', 'h', 'H', 'i', 'I', 'j',
				'J', 'k', 'K', 'l', 'L', 'm', 'M', 'n', 'N', 'o', 'O', 'p', 'P', 'q', 'Q', 'r', 'R', 's', 'S', 't', 'T',
				'u', 'U', 'v', 'V', 'w', 'W', 'x', 'X', 'y', 'Y', 'z', 'Z', 'ç', 'Ç', ' ' };
		int auxLetras = 0;
		char numero[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.' };
		int auxNumero = 0;
		for (int i = 0; i < letras.length; i++) {
			for (int j = 0; j < letra.length(); j++) {
				if (letras[i] == letra.charAt(j)) {
					auxLetras++;
				}
			}
		}
		for (int i = 0; i < numero.length; i++) {
			for (int j = 0; j < n.length(); j++) {
				if (numero[i] == n.charAt(j)) {
					auxNumero++;
				}
			}
		}
		if (auxLetras != letra.length() || letra.isEmpty() || auxNumero != n.length() || n.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public boolean verificaNumero(String n) {
		char numero[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.' };
		int auxNumero = 0;
		for (int i = 0; i < numero.length; i++) {
			for (int j = 0; j < n.length(); j++) {
				if (numero[i] == n.charAt(j)) {
					auxNumero++;
				}
			}
		}
		if (auxNumero != n.length() || n.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	public void exibirCupom(String pagamento) {
		try {
			Runtime.getRuntime().exec("cmd.exe /C " + pagamento);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
