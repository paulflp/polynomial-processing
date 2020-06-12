package Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

/**
 * Clasa ajutatoare pentru lucrul cu interfata grafica
 * 
 * @author Paul Filip
 *
 */
public class PolynomialUtils {
	/**
	 * 
	 * @param input
	 *            polinom sub forma de String
	 * @return Polinomul ca structura de date
	 * @throws Exception
	 *             cand string-ul nu reprezinta un polinom
	 */
	public Polinom parsePolynomial(String input) throws Exception {
		System.out.println("Inputul este " + input);
		Polinom result = new Polinom();
		PolynomialUtils util = new PolynomialUtils();

		input = input.replaceAll(" ", "");
		String[] monomArray = util.divideString(input);
		for (String monom : monomArray) {
			Monom m;
			try {
				System.out.println(monom);
				if (!monom.equals("")) {
					if (monom.charAt(0) == '-') {
						m = util.matchPattern(monom.substring(1));
						m.setCoefficient(-m.getCoefficient());
					} else {

						m = util.matchPattern(monom);
					}

					System.out.println("Monomul este");
					System.out.println(m.toString());
					result.addMonom(m);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(null, "The input is invalid. Provide valide data!", "Error",
						JOptionPane.ERROR_MESSAGE);
				throw new Exception("");
			}
		}
		System.out.println("Polinomul este:");
		System.out.println(result.toString());
		return result;
	}

	/**
	 * 
	 * @param input
	 *            un text
	 * @return textul fara spatii
	 */
	private String removeBlanks(String input) {
		return input.replaceAll(" ", "");
	}

	/**
	 * 
	 * @param input
	 *            un String
	 * @return un sir de substringuri delimitat din Stringul initial de
	 *         caracterul '+'
	 */
	private String[] divideString(String input) {
		input = input.replaceAll("-", "+-");
		String[] monomArray = input.split("(\\+)");

		return monomArray;
	}

	/**
	 * 
	 * @param s
	 *            un posibil monom sub forma de string
	 * @return un monom ca structura de data cu coeficient si grad
	 * @throws Exception
	 *             cand string-ul nu reprezinta un monom
	 */
	private Monom matchPattern(String s) throws Exception {
		String pattern[] = new String[7];

		pattern[0] = "(^\\d+)(\\*)(x)(\\^)(\\d+$)";
		pattern[1] = "(^\\d+)(x)(\\^)(\\d+$)";
		pattern[2] = "(^x)(\\^)(\\d+$)";
		pattern[3] = "(^\\d+)(\\*)(x$)";
		pattern[4] = "(^\\d+)(x$)";
		pattern[5] = "(^x$)";
		pattern[6] = "(^\\d+$)";

		int i = 0;
		Pattern p = Pattern.compile(pattern[0]);
		Matcher mtch = p.matcher(s);
		for (i = 0; i < 7; i++) {
			p = Pattern.compile(pattern[i]);
			mtch = p.matcher(s);
			if (mtch.find()) {
				break;
			}
		}
		Monom newMonom = new Monom();
		if (i >= 7) {
			throw new Exception("The input is invalid. Provide valide data!");
		} else {
			switch (i) {
			case 0:
				newMonom.setDegree(Integer.parseInt(mtch.group(5)));
				newMonom.setCoefficient(Integer.parseInt(mtch.group(1)));
				break;
			case 1:
				newMonom.setDegree(Integer.parseInt(mtch.group(4)));
				newMonom.setCoefficient(Integer.parseInt(mtch.group(1)));
				break;
			case 2:
				newMonom.setDegree(Integer.parseInt(mtch.group(3)));
				newMonom.setCoefficient(1);
				break;
			case 3:
				newMonom.setDegree(1);
				newMonom.setCoefficient(Integer.parseInt(mtch.group(1)));
				break;
			case 4:
				newMonom.setDegree(1);
				newMonom.setCoefficient(Integer.parseInt(mtch.group(1)));
				break;
			case 5:
				newMonom.setDegree(1);
				newMonom.setCoefficient(1);
				break;
			case 6:
				newMonom.setDegree(0);
				newMonom.setCoefficient(Integer.parseInt(mtch.group(1)));
				break;
			}

		}
		return newMonom;
	}
}
