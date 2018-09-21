package gui.AdditionalWindows;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;
import GameSquares.Cab;
import GameSquares.GameSquare;
import GameSquares.Land;
import GameSquares.Ownable;
import GameSquares.StockExchange.stockType;
import GameSquares.Land.state;
import GameSquares.Utility;
import Main.Player;

import java.awt.event.*;
import java.awt.*;

// ListDemo.java requires no other files.
public class List extends JPanel implements ListSelectionListener, ActionListener {

	private static final long            serialVersionUID = 1L;
	private JList<Object>            	 list;
	private DefaultListModel<Object> 	 listModel;
	private int[]						 stocks;
	private Player						 player;
	private JButton                      sellButton, mortgageButton;
	ArrayList<GameSquare>                squares;
	private JLabel                       label;

	public List(ArrayList<GameSquare> squares,Player player) {
		super(new BorderLayout());
		this.player = player;
		this.squares = squares;
		this.stocks = player.getStocks();

		list = new JList<Object>();
		listModel = new DefaultListModel<Object>();

		for (GameSquare a : squares) {
			listModel.addElement(a);
		}
		for(int i=0;i<stocks.length;i++){
			if(stocks[i] != 0){
				stockType st = stockType.getStock(i);
				listModel.addElement(st);
			}}

		if (!(squares.isEmpty())) {
			label = new JLabel(squares.get(0).toString());
		} else
			label = new JLabel();

		// Create the list and put it in a scroll pane.
		list = new JList<Object>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		list.addListSelectionListener(this);
		list.setVisibleRowCount(12);
		JScrollPane listScrollPane = new JScrollPane(list);
		listScrollPane.setPreferredSize(new Dimension(((int) listScrollPane.getPreferredSize().getWidth() + 20),
				((int) listScrollPane.getPreferredSize().getHeight())));

		sellButton = new JButton("SELL");
		sellButton.setActionCommand("SELL");
		sellButton.addActionListener(this);

		mortgageButton = new JButton("MORTGAGE");
		mortgageButton.setActionCommand("MORTGAGE");
		mortgageButton.addActionListener(this);

		add(listScrollPane, BorderLayout.WEST);
		label.setPreferredSize(new Dimension(((int) label.getPreferredSize().getWidth()), ((int) label
				.getPreferredSize().getHeight())));
		add(label);
		add(sellButton, BorderLayout.AFTER_LAST_LINE);
		add(mortgageButton, BorderLayout.NORTH);
	}

	public void actionPerformed(ActionEvent e) {
		GameSquare temp = null;
		stockType  st	= null;
		if (e.getSource() == sellButton) {
			int index = list.getSelectedIndex();

			if(list.getSelectedValue() instanceof GameSquare)
				temp = (GameSquare) list.getSelectedValue();
			else
				st = (stockType) list.getSelectedValue();


			if (temp instanceof Ownable) {
				if (!(temp == null))
					if (temp instanceof Land) {
						if (((Land) temp).getState() == state.unImproved) {
							((Land) temp).sell();
							if (index != -1) listModel.remove(index);
						} else {
							((Land) temp).downgrade();

						}
					} else {
						if((((Ownable) temp).getOwner())!=null) 
							((Ownable) temp).sell();
						if (index != -1) listModel.remove(index);
					}
			}
			if(!(st==null)){
				stocks[st.getOrder()]--;
				System.out.println(player.getName()+ " sold " + st.getValue());
				player.addMoney(st.getPrice()/2);
				if (index != -1 && stocks[st.getOrder()] == 0) listModel.remove(index);
			}

			int size = listModel.getSize();

			if (size == 0) { // Nobody's left, disable firing.
				sellButton.setEnabled(false);
				label.setText(""); // kimse kalmadi bos don veya
				// this.dispose(); ekrani kapatir

			} else { // Select an index.
				if (index == listModel.getSize()) {
					// removed item in last position
					index--;
				}

				list.setSelectedIndex(index);
				list.ensureIndexIsVisible(index);
			}
		} 

		else if (e.getSource() == mortgageButton) {

			int index = list.getSelectedIndex();

			if(list.getSelectedValue() instanceof GameSquare)
				temp = (GameSquare) list.getSelectedValue();
			else
				st = (stockType) list.getSelectedValue();

			if (temp instanceof Land) {

				if (((Land) temp).getState() == state.mortgage) {
					if (!(temp == null)) ((Land) temp).leaveMortgage();

				} else {

					if (!(temp == null)) ((Land) temp).mortgage();

					if (index == listModel.getSize()) {
						// removed item in last position
						index--;
					}

					list.setSelectedIndex(index);
					list.ensureIndexIsVisible(index);
				}
			} else if (temp instanceof Cab) {
				if (((Cab) temp).isMortgaged()) {
					if (!(temp == null)) ((Cab) temp).leaveMortgage();
				} else {
					if (!(temp == null)) ((Cab) temp).mortgage();

					if (index == listModel.getSize()) {
						index--;
					}
				}
			} else if (temp instanceof Utility) {
				if (((Utility) temp).isMortgaged()) {
					if (!(temp == null)) ((Utility) temp).leaveMortgage();
				} else {
					if (!(temp == null)) ((Utility) temp).mortgage();

					if (index == listModel.getSize()) {
						index--;
					}
				}
			}
		}

	}

	public void valueChanged(ListSelectionEvent e) {

		// BURAYA DOKUNMA LABEL DEGISIYR SADECE

		GameSquare temp = null;
		stockType  st	= null;
		if(list.getSelectedValue() instanceof GameSquare)
			temp = (GameSquare) list.getSelectedValue();
		else
			st = (stockType) list.getSelectedValue();

		if (!(temp == null)) {
			if (temp instanceof Land)
				label.setText(((Land) temp).toString2());
			else
				label.setText(temp.toString());
			label.setPreferredSize(new Dimension(((int) label.getPreferredSize().getWidth()) + 10, ((int) label
					.getPreferredSize().getHeight())));
		} 

		if(!(st==null)){
			label.setText(st.getValue() + " with a price of:" + st.getPrice());
		}

		if (list.getSelectedIndex() == -1) {
			// No selection, disable sell button.
			sellButton.setEnabled(false);

		} else {
			// Selection, enable the sell button.
			sellButton.setEnabled(true);
		}
	}


	@SuppressWarnings("unchecked")
	public static void createAndShowGUI(ArrayList<?> ownables,Player player) {
		// Create and set up the window.
		if (ownables != null) {
			JFrame frame = null;
			frame = new JFrame("Owned properties to Sell");
			List a = new List((ArrayList<GameSquare>) ownables,player);
			frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			frame.add(a);
			frame.setSize(380, 280);
			frame.setVisible(true);
		}

	}
}
