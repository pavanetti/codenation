package br.com.codenation.calculadora;

public class CalculadoraSalario {

	public long calcularSalarioLiquido(double salarioBase) {
		// Use o Math.round apenas no final do método para arredondar o valor final.
		// Documentação do método:
		// https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html#round-double-
		double descontadoInss = salarioBase - descontoInss(salarioBase);
		double descontadoIrrf = descontadoInss - descontoIrrf(descontadoInss);
		return descontadoIrrf >= 1039.0 ? Math.round(descontadoIrrf) : 0;
	}

	// Exemplo de método que pode ser criado para separar melhor as
	// responsábilidades de seu algorítmo
	private double descontoInss(double salarioBase) {
		return salarioBase * taxaInss(salarioBase);
	}

	private double taxaInss(double salarioBase) {
		if (salarioBase <= 1500)
			return 0.08;
		if (salarioBase <= 4000)
			return 0.09;
		return 0.11;
	}

	private double descontoIrrf(double salarioDescontado) {
		return salarioDescontado * taxaIrrf(salarioDescontado);
	}

	private double taxaIrrf(double salarioDescontado) {
		if (salarioDescontado <= 3000)
			return 0.0;
		if (salarioDescontado <= 6000)
			return 0.075;
		return 0.15;
	}

}
/*
 * Dúvidas ou Problemas? Manda e-mail para o meajuda@codenation.dev que iremos
 * te ajudar!
 */
