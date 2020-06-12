package Model;

/**
 * Clasa pentru implementarea monomului
 * 
 * @author Paul Filip
 */
public class Monom implements Comparable<Object> {
	private int degree;
	private double coefficient;

	public int getDegree() {
		return degree;
	}

	public double getCoefficient() {
		return coefficient;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}

	public Monom() {
		super();
		this.degree = 0;
		this.coefficient = 0.0f;
	}

	public Monom(int degree, double coefficient) {
		super();
		this.degree = degree;
		this.coefficient = coefficient;
	}

	public Monom add(Monom m) {
		return new Monom(m.getDegree(), this.getCoefficient() + m.getCoefficient());
	}

	public Monom sub(Monom m) {
		return new Monom(m.getDegree(), this.coefficient - m.getCoefficient());

	}

	public Monom multiply(Monom m) {
		return new Monom(this.getDegree() + m.getDegree(), this.getCoefficient() * m.getCoefficient());

	}

	public Monom divide(Monom m) {
		return new Monom(this.getDegree() - m.getDegree(), this.getCoefficient() / m.getCoefficient());
	}

	public Monom derive() {
		if (this.degree == 0)
			return new Monom();
		return new Monom(this.getDegree() - 1, this.getCoefficient() * this.getDegree());
	}

	public Monom integrate() {
		return new Monom(this.getDegree() + 1, this.getCoefficient() / ((double) this.getDegree() + 1));
	}

	public int compareTo(Object arg0) {
		Monom m = (Monom) arg0;
		if (this.getDegree() == m.getDegree())
			return 0;
		else if (this.getDegree() > m.getDegree())
			return -1;
		else
			return 1;
	}

	@Override
	public String toString() {
		String m = new String();
		if (this.getCoefficient() == 0)
			return "";
		else {
			if (this.getDegree() == 0) {
				if (this.getCoefficient() < 0)
					m += this.getCoefficient();
				else
					m = "+" + this.getCoefficient();
			} else {
				if (this.getDegree() == 1) {
					if (this.getCoefficient() == 1) {
						m = "+x";
					} else {
						if (this.getCoefficient() == -1)
							m = "-x";
						else {
							if (this.getCoefficient() < 0)
								m = this.getCoefficient() + "x";
							else
								m = "+" + this.getCoefficient() + "x";
						}
					}
				} else {
					if (this.getCoefficient() == 1) {
						m = "+x^" + this.getDegree();
					} else {
						if (this.getCoefficient() == -1) {
							m = "-x^" + this.getDegree();
						} else {
							if (this.getCoefficient() < 0)
								m = this.getCoefficient() + "x^" + this.getDegree();
							else
								m = "+" + this.getCoefficient() + "x^" + this.getDegree();
						}
					}
				}
			}
			return m;
		}
	}
/**
 * 
 * @param m un monom
 * @return true daca monoamele sunt egale, false altfel
 */
	public boolean equals(Monom m) {
		if (this.getDegree() != m.getDegree())
			return false;
		if (this.getCoefficient() != m.getCoefficient())
			return false;

		return true;
	}
}
