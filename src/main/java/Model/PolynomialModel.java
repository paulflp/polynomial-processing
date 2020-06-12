package Model;
/**
 * Clasa Model pentru interfata grafica
 * @author Paul Filip
 *
 */
public class PolynomialModel {
	public static final String INITIAL_VALUE = "";
	private String m_total;
	private String m_rest;

	public PolynomialModel() {
		reset();
	}

	public void reset() {
		m_total = new String(PolynomialModel.INITIAL_VALUE);
		m_rest = new String(PolynomialModel.INITIAL_VALUE);
	}

	public void setValue(String value) {
		m_total = new String(value);
	}

	public void setRest(String value) {
		m_rest = new String(value);
	}

	public String getValue() {
		return m_total;
	}

	public String getRest() {
		return m_rest;
	}

	public void multiply(String firstPolynomial, String secondPolynomial) {
		PolynomialUtils util = new PolynomialUtils();
		try {
			Polinom p1 = util.parsePolynomial(firstPolynomial);
			Polinom p2 = util.parsePolynomial(secondPolynomial);

			Polinom result = Polinom.multiplyPolynomials(p1, p2);

			m_total = "" + result.toString();
		} catch (Exception e) {
			m_total = "";
		}
	}

	public void divide(String firstPolynomial, String secondPolynomial) {
		PolynomialUtils util = new PolynomialUtils();
		try {
			Polinom p1 = util.parsePolynomial(firstPolynomial);
			Polinom p2 = util.parsePolynomial(secondPolynomial);

			/*Polinom cat = new Polinom();
			Polinom rest = new Polinom();

			p1.dividePolynomials(p2, cat, rest);*/
			Polinom cat=p1.dividePolynomials(p2);
			Polinom rest=p1;
			
			m_total = "" + cat.toString();
			m_rest = "" + rest.toString();
		}
		catch (IllegalArgumentException e){
			m_total=e.getMessage();
			m_rest="";
		}
		catch (Exception e) {
			m_total = "";
			m_rest = "";
		}
	}

	public void add(String firstPolynomial, String secondPolynomial) {
		PolynomialUtils util = new PolynomialUtils();
		try {
			Polinom p1 = util.parsePolynomial(firstPolynomial);
			Polinom p2 = util.parsePolynomial(secondPolynomial);

			Polinom result = p1.addPolynomials(p2);

			m_total = "" + result.toString();
		} catch (Exception e) {
			m_total = "";
		}
	}

	public void sub(String firstPolynomial, String secondPolynomial) {
		PolynomialUtils util = new PolynomialUtils();
		try {
			Polinom p1 = util.parsePolynomial(firstPolynomial);
			Polinom p2 = util.parsePolynomial(secondPolynomial);

			Polinom result = p1.subPolynomials(p2);

			m_total = "" + result.toString();
		} catch (Exception e) {
			m_total = "";
		}
	}

	public void derive(String polynomial) {
		PolynomialUtils util = new PolynomialUtils();
		try {
			Polinom p1 = util.parsePolynomial(polynomial);

			Polinom result = p1.derivePolynomial();

			m_total = "" + result.toString();
		} catch (Exception e) {
			m_total = "";
		}
	}

	public void integrate(String polynomial) {
		PolynomialUtils util = new PolynomialUtils();
		try {
			Polinom p1 = util.parsePolynomial(polynomial);

			Polinom result = p1.integratePolynomial();

			m_total = "" + result.toString();
		} catch (Exception e) {
			m_total = "";
		}
	}
}
