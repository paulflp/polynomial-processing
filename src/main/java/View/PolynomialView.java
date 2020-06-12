package View;

import javax.swing.*;

import Model.PolynomialModel;

import java.awt.*;
import java.awt.event.*;

/**
 * 
 * @author Paul Filip Clasa View pentru interfata grafica.
 */
public class PolynomialView extends JFrame {
	private JTextField firstPolynomialtF = new JTextField();
	private JTextField secondPolynomialtF = new JTextField();
	private JTextField resulttF = new JTextField();
	private JTextField resttF = new JTextField();
	private JButton multiplyBtn = new JButton("Multiply");
	private JButton divideBtn = new JButton("Divide");
	private JButton addBtn = new JButton("Add");
	private JButton subBtn = new JButton("Subtract");
	private JButton deriveBtn = new JButton("Derive");
	private JButton integrateBtn = new JButton("Integrate");
	private JButton clearBtn = new JButton("Clear");
	private JLabel restJL = new JLabel("Rest");

	private PolynomialModel polModel;

	public PolynomialView(PolynomialModel model) {
		this.setMinimumSize(new Dimension(700, 300));
		polModel = model;
		polModel.setValue(PolynomialModel.INITIAL_VALUE);

		resulttF.setText(polModel.getValue());
		resulttF.setEditable(false);

		resttF.setText(polModel.getRest());
		resttF.setEditable(false);

		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));

		JPanel firstLabel = new JPanel();
		firstLabel.add(new JLabel("Introduce the first polynomial"));
		content.add(firstLabel);

		content.add(firstPolynomialtF);

		JPanel secondLabel = new JPanel();
		secondLabel.add(new JLabel("Introduce the second polynomial"));
		content.add(secondLabel);

		content.add(secondPolynomialtF);

		JPanel singleBtn = new JPanel();
		singleBtn.add(clearBtn);

		content.add(singleBtn);

		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout());
		buttons.add(addBtn);
		buttons.add(Box.createHorizontalStrut(30));
		buttons.add(subBtn);
		buttons.add(Box.createHorizontalStrut(30));
		buttons.add(multiplyBtn);
		buttons.add(Box.createHorizontalStrut(30));
		buttons.add(divideBtn);
		buttons.add(Box.createHorizontalStrut(30));
		buttons.add(deriveBtn);
		buttons.add(Box.createHorizontalStrut(30));
		buttons.add(integrateBtn);

		content.add(buttons);

		content.add(new JLabel("Result"));
		content.add(resulttF);
		content.add(restJL);
		content.add(resttF);

		this.setContentPane(content);

		this.pack();

		this.setTitle("Polynomial Processing");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * reset pentru interfata grafica
	 */
	public void reset() {
		firstPolynomialtF.setText(PolynomialModel.INITIAL_VALUE);
		secondPolynomialtF.setText(PolynomialModel.INITIAL_VALUE);
		resulttF.setText("");
		resttF.setText("");
		resttF.setVisible(true);
	}

	/**
	 * 
	 * @return String-ul introdus in campul pentru primul polinom
	 */
	public String getUserInputFirstPolynomial() {
		return firstPolynomialtF.getText();
	}

	/**
	 * 
	 * @return String-ul introdus in campul pentru al doilea polinom
	 */
	public String getUserInputSecondPolynomial() {
		return secondPolynomialtF.getText();
	}

	/**
	 * Seteaza textul afisat in campul pentru rezultat
	 * 
	 * @param result
	 *            String rezultat
	 */
	public void setResult(String result) {
		resulttF.setText(result);
	}

	/**
	 * seteaza textul afisat in campul pentru rest
	 * 
	 * @param result
	 *            restul calculat la impartirea polinoamelor
	 */
	public void setRest(String result) {
		resttF.setText(result);
	}

	public void showError(String errMessage) {
		JOptionPane.showMessageDialog(this, errMessage);
	}

	public void addMultiplyListener(ActionListener mal) {
		multiplyBtn.addActionListener(mal);
	}

	public void addDivideListener(ActionListener mal) {
		divideBtn.addActionListener(mal);
	}

	public void addSumListener(ActionListener mal) {
		addBtn.addActionListener(mal);
	}

	public void addSubListener(ActionListener mal) {
		subBtn.addActionListener(mal);
	}

	public void addDeriveListener(ActionListener mal) {
		deriveBtn.addActionListener(mal);
	}

	public void addIntegrateListener(ActionListener mal) {
		integrateBtn.addActionListener(mal);
	}

	public void addClearListener(ActionListener mal) {
		clearBtn.addActionListener(mal);
	}

	/**
	 * 
	 * @param b
	 *            true pentru afisarea campurilor pentru rest, false altfel
	 */
	public void setRestVisibility(boolean b) {
		resttF.setVisible(b);
		restJL.setVisible(b);
	}

	/**
	 * 
	 * @param b
	 *            true pentru permiterea editarii textfield-ului pt al doilea
	 *            polinom, false altfel
	 */
	public void setSecondPolynomialField(boolean b) {
		secondPolynomialtF.setEditable(b);
	}

}
