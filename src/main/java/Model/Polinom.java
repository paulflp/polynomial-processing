package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Clasa pentru implementarea Polinomului
 * 
 * @author Paul Filip
 *
 */
public class Polinom {
	private ArrayList<Monom> polynom;
	private int polynomialDegree;

	public ArrayList<Monom> getPolynom() {
		return polynom;
	}

	public void setPolynom(ArrayList<Monom> polynom) {
		this.polynom = polynom;
	}

	public int getPolynomialDegree() {
		return polynomialDegree;
	}

	public void setPolynomialDegree(int polynomialDegree) {
		this.polynomialDegree = polynomialDegree;
	}

	public Polinom() {
		super();
		this.polynom = new ArrayList<Monom>();
		this.polynomialDegree = 0;
	}

	/**
	 * Update la gradul polinomului
	 */
	public void updatePolynomial() {
		int grad = 0;
		for (Monom a : this.getPolynom()) {
			if (a.getCoefficient() != 0) {
				if (a.getDegree() > grad)
					grad = a.getDegree();
			}
		}
		this.setPolynomialDegree(grad);
	}

	/**
	 * adauga un monom la un polinom
	 * 
	 * @param m
	 *            Monom
	 */
	public void addMonom(Monom m) {
		if (m.getCoefficient() == 0) {
			return;
		} else {
			if (m.getDegree() > this.getPolynomialDegree()) {
				this.polynomialDegree = m.getDegree();
				this.polynom.add(0, m);
			} else {
				boolean ok = false;
				int position = 0;
				for (Monom a : this.getPolynom()) {
					if (a.getDegree() == m.getDegree()) {
						this.getPolynom().set(position, a.add(m));
						ok = true;
						break;
					}
					if (a.getDegree() < m.getDegree()) {
						break;
					}
					position++;
				}
				if (!ok) {
					this.getPolynom().add(position, m);
				}
			}
		}
	}

	@Override
	public String toString() {
		String s = new String();
		int firstCoef = (int) this.getFirstNotNull().getCoefficient();
		for (Monom a : this.getPolynom()) {
			s += a.toString();
		}
		if (firstCoef > 0)
			return s.substring(1);
		else {
			if (s.equals(""))
				return "0";
			else
				return s;
		}
	}

	public Polinom addPolynomials(Polinom b) {
		Polinom result = new Polinom();
		for (Monom a : this.getPolynom()) {
			result.addMonom(a);
		}
		for (Monom a : b.getPolynom()) {
			result.addMonom(a);
		}
		result.updatePolynomial();
		return result;
	}

	public Polinom subPolynomials(Polinom b) {
		Polinom result = new Polinom();
		for (Monom a : this.getPolynom()) {
			result.addMonom(a);
		}
		for (Monom a : b.getPolynom()) {
			result.addMonom(new Monom(a.getDegree(), -a.getCoefficient()));
		}
		result.updatePolynomial();
		return result;
	}

	public static Polinom multiplyPolynomials(Polinom a, Polinom b) {
		Polinom result = new Polinom();
		for (Monom a1 : a.getPolynom()) {
			for (Monom b1 : b.getPolynom()) {
				Monom rez = a1.multiply(b1);
				result.addMonom(rez);
			}
		}
		result.updatePolynomial();
		return result;
	}

	public void dividePolynomials(Polinom impartitor, Polinom cat, Polinom rest) {
		if (impartitor.getPolynomialDegree() == 0) {
			rest.setPolynomialDegree(0);
			rest.setPolynom(new ArrayList<Monom>());

			cat.setPolynomialDegree(this.getPolynomialDegree());
			cat.setPolynom(this.getPolynom());

			for (Monom a : cat.getPolynom()) {
				a.setCoefficient(a.getCoefficient() / impartitor.getFirstNotNull().getCoefficient());
			}
			return;
		}
		if (this.getPolynomialDegree() < impartitor.getPolynomialDegree()) {
			cat.setPolynomialDegree(0);
			cat.setPolynom(new ArrayList<Monom>());
			cat.addMonom(new Monom());

			rest.setPolynomialDegree(this.getPolynomialDegree());
			rest.setPolynom(this.getPolynom());
		} else {
			int grad = Integer.MAX_VALUE;
			Polinom catR = new Polinom();
			Polinom restR = new Polinom();

			Polinom deimp = this;
			while (grad >= impartitor.getPolynomialDegree()) {
				Polinom aux = new Polinom();

				Monom d1 = deimp.getFirstNotNull();
				Monom i1 = impartitor.getFirstNotNull();
				Monom firstDivide = d1.divide(i1);
				catR.addMonom(firstDivide);

				for (Monom imp : impartitor.getPolynom()) {
					aux.addMonom(firstDivide.multiply(imp));
				}
				Polinom rest1 = deimp.subPolynomials(aux);
				rest1.updatePolynomial();
				grad = rest1.getPolynomialDegree();

				restR.setPolynom(rest1.getPolynom());
				restR.setPolynomialDegree(rest1.getPolynomialDegree());

				deimp = rest1;
			}
			cat.setPolynom(catR.getPolynom());
			cat.setPolynomialDegree(catR.getPolynomialDegree());

			rest.setPolynom(restR.getPolynom());
			rest.setPolynomialDegree(restR.getPolynomialDegree());
		}

	}

	public Polinom dividePolynomials(Polinom b) throws IllegalArgumentException {
		Polinom cat = new Polinom();
		if (b.getPolynomialDegree() == 0) {
			if (b.getFirstNotNull().getCoefficient() == 0) {
				throw new IllegalArgumentException("Error: Divide by 0 is illegal");
			} else {
				for (Monom a : this.getPolynom()) {
					cat.addMonom(new Monom(a.getDegree(), a.getCoefficient() / b.getFirstNotNull().getCoefficient()));
					a.setCoefficient(0);
				}
			}
		} else {
			while (this.getPolynomialDegree() >= b.getPolynomialDegree()) {
				Polinom aux = new Polinom();
				Monom d1 = this.getFirstNotNull();
				Monom i1 = b.getFirstNotNull();
				Monom firstDivide = d1.divide(i1);
				cat.addMonom(firstDivide);
				for (Monom imp : b.getPolynom()) {
					aux.addMonom(firstDivide.multiply(imp));
				}
				this.setPolynom(this.subPolynomials(aux).getPolynom());
				this.updatePolynomial();
			}
		}
		return cat;
	}

	public Polinom derivePolynomial() {
		Polinom result = new Polinom();
		for (Monom a : this.getPolynom()) {
			result.addMonom(a.derive());
		}
		return result;
	}

	public Polinom integratePolynomial() {
		Polinom result = new Polinom();
		for (Monom a : this.getPolynom()) {
			result.addMonom(a.integrate());
		}
		return result;
	}

	public Monom getFirstNotNull() {
		Monom result = new Monom();
		for (Monom a : this.getPolynom()) {
			if (a.getCoefficient() != 0) {
				result = a;
				break;
			}
		}
		return result;
	}

	public boolean equals(Polinom b) {
		for (Monom a : this.getPolynom()) {
			if (a.getCoefficient() == 0) {
				this.getPolynom().remove(a);
			}
		}
		for (Monom a : b.getPolynom()) {
			if (a.getCoefficient() == 0) {
				b.getPolynom().remove(a);
			}
		}
		Collections.sort(this.getPolynom());
		Collections.sort(b.getPolynom());
		Iterator<Monom> i = this.getPolynom().iterator();
		Iterator<Monom> j = b.getPolynom().iterator();

		while (i.hasNext()) {
			Monom m1 = (Monom) i.next();
			Monom m2 = (Monom) j.next();
			if (!m1.equals(m2)) {
				return false;
			}
		}

		return true;
	}
}
