package br.com.codenation.calculadora;

public class CalculadoraSalario {

	public long calcularSalarioLiquido(double salarioBase) {
		// Use o Math.round apenas no final do método para arredondar o valor final.
		// Documentação do método:
		// https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html#round-double-
		// double descontadoInss = salarioBase - descontoInss(salarioBase);
		// double descontadoIrrf = descontadoInss - descontoIrrf(descontadoInss);
		// return descontadoIrrf >= 1039.0 ? Math.round(descontadoIrrf) : 0;
		return new SalarioLiquido(salarioBase).valor();
	}

}

class SalarioLiquido {
	private double salarioBase;
	private static final double SALARIO_MINIMO = 1039.0;

	public SalarioLiquido(double salarioBase) {
		this.salarioBase = salarioBase;
	}

	public long valor() {
		double valor = this.descontadoIrrf();
		if (valor < SalarioLiquido.SALARIO_MINIMO)
			return 0L;
		return Math.round(valor);
	}

	private double descontadoIrrf() {
		return this.descontadoInss() * (1 - this.taxaIrrf());
	}

	private double taxaIrrf() {
		double descontado = this.descontadoInss();
		if (descontado <= 3000.0)
			return 0.0;
		if (descontado <= 6000.0)
			return 0.075;
		return 0.15;
	}

	private double descontadoInss() {
		return this.salarioBase * (1 - this.taxaInss());
	}

	private double taxaInss() {
		if (this.salarioBase <= 1500.0)
			return 0.08;
		if (this.salarioBase <= 4000.0)
			return 0.09;
		return 0.11;
	}

}
/*
 * Dúvidas ou Problemas? Manda e-mail para o meajuda@codenation.dev que iremos
 * te ajudar!
 */
