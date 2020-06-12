package Controller;

import java.awt.event.*;

import javax.swing.JOptionPane;

import Model.PolynomialModel;
import View.PolynomialView;
/**
 * 
 * @author Paul Filip
 *	Clasa Controller pentru interfata grafica.
 */
public class PolynomialController {
	private PolynomialModel m_model;
	private PolynomialView m_view;

	public PolynomialController(PolynomialModel model, PolynomialView view) {

		m_model = model;
		m_view = view;

		view.addMultiplyListener(new MultiplyListener());
		view.addDivideListener(new DivideListener());
		view.addSumListener(new SumListener());
		view.addSubListener(new SubListener());
		view.addDeriveListener(new DeriveListener());
		view.addIntegrateListener(new IntegrateListener());
		view.addClearListener(new ClearListener()); // add listeners for view

	}

	class MultiplyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String firstPo = "";
			String secondPo = "";
			try {
				firstPo = m_view.getUserInputFirstPolynomial();
				secondPo = m_view.getUserInputSecondPolynomial();

				m_model.multiply(firstPo, secondPo);

				m_view.setResult(m_model.getValue());

				m_view.setRestVisibility(false);
				m_view.setSecondPolynomialField(true);

			} catch (Exception err) {
				m_view.showError(err.getMessage());

			}
		}
	}

	class DivideListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String firstPo = "";
			String secondPo = "";
			try {
				firstPo = m_view.getUserInputFirstPolynomial();
				secondPo = m_view.getUserInputSecondPolynomial();

				m_model.divide(firstPo, secondPo);

				m_view.setRestVisibility(true);
				m_view.setResult(m_model.getValue());
				m_view.setRest(m_model.getRest());
				m_view.setSecondPolynomialField(true);

			} catch (Exception err) {
				m_view.showError(err.getMessage());

			}
		}
	}

	class SumListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String firstPo = "";
			String secondPo = "";
			try {
				firstPo = m_view.getUserInputFirstPolynomial();
				secondPo = m_view.getUserInputSecondPolynomial();

				m_model.add(firstPo, secondPo);
				m_view.setRestVisibility(false);

				m_view.setResult(m_model.getValue());
				m_view.setSecondPolynomialField(true);

			} catch (Exception err) {
				m_view.showError(err.getMessage());

			}
		}
	}

	class SubListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String firstPo = "";
			String secondPo = "";
			try {
				firstPo = m_view.getUserInputFirstPolynomial();
				secondPo = m_view.getUserInputSecondPolynomial();

				m_model.sub(firstPo, secondPo);
				m_view.setRestVisibility(false);
				m_view.setResult(m_model.getValue());
				m_view.setSecondPolynomialField(true);

			} catch (Exception err) {
				m_view.showError(err.getMessage());

			}
		}
	}

	class DeriveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String firstPo = "";
			// String secondPo="";
			try {
				firstPo = m_view.getUserInputFirstPolynomial();
				// secondPo=m_view.getUserInputSecondPolynomial();
				m_model.derive(firstPo);
				m_view.setRestVisibility(false);
				m_view.setResult(m_model.getValue());
				m_view.setSecondPolynomialField(false);

			} catch (Exception err) {
				m_view.showError(err.getMessage());

			}
		}
	}

	class IntegrateListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String firstPo = "";
			// String secondPo="";
			try {
				firstPo = m_view.getUserInputFirstPolynomial();
				// secondPo=m_view.getUserInputSecondPolynomial();
				m_model.integrate(firstPo);
				m_view.setRestVisibility(false);
				m_view.setResult(m_model.getValue());
				m_view.setSecondPolynomialField(false);

			} catch (Exception err) {
				m_view.showError(err.getMessage());

			}
		}
	}

	class ClearListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			m_view.reset();
			m_model.reset();
			m_view.setSecondPolynomialField(true);
		}
	}
}
