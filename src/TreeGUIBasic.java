import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


//Generic Binary Search Tree with Duplicates  
//With a Simple Output Graphical User Interface

//Illustrates Several Design Patterns - Can You Tell Which Ones?


public class TreeGUIBasic extends JFrame {

	private static final long serialVersionUID = 1L;
	String max, min;
	Abs_tree tree;
	boolean is_new_tree = true;
	Choice tree_kind, element_kind;
	JTextField input_elem_text, min_text, max_text;
	String nodeValue;
	JLabel tree_kind_label, elem_kind_label;
	JPanel input1, input2;
	JPanel output;
	JButton insertButton, clearButton, minButton, maxButton, printButton;
	JTextArea output_text;
	JPanel inputPanel;

	public TreeGUIBasic() {
		super("GUI for Tree Operations");

		Container c = getContentPane();
		c.setLayout(new BorderLayout());

		inputPanel = new JPanel();
		inputPanel.setLayout(new BorderLayout());
		// create input panel to take input from user.

		input1 = new JPanel(new FlowLayout()); // The First Panel

		tree_kind_label = new JLabel("Tree Kind:");
		input1.add(tree_kind_label);
		tree_kind = new Choice();
		tree_kind.addItem("Normal Tree");
		tree_kind.addItem("Dup Tree");
		input1.add(tree_kind);

		elem_kind_label = new JLabel("   Element Kind:");
		input1.add(elem_kind_label);
		element_kind = new Choice();
		element_kind.addItem("Integer");
		element_kind.addItem("String");
		input1.add(element_kind);

		input_elem_text = new JTextField("enter value here");
		input_elem_text.requestFocus(true);
		input_elem_text.selectAll();

		insertButton = new JButton("Insert");
		insertButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = input_elem_text.getText();
				int index = element_kind.getSelectedIndex();
				{
					if (is_new_tree) {
						if (index == 0) {
							if (tree_kind.getSelectedIndex() == 0)
								tree = new Tree(
										new IntElem(Integer.parseInt(s)));
							else
								tree = new Duptree(new IntElem(Integer
										.parseInt(s)));
						} else {
							if (tree_kind.getSelectedIndex() == 0)
								tree = new Tree(new StringElem(s));
							else
								tree = new Duptree(new StringElem(s));
						}
						is_new_tree = false;
					} 
					else {
						if (index == 0)
							tree.insert(new IntElem(Integer.parseInt(s)));
						else
							tree.insert(new StringElem(s));
					}
				} 
				input_elem_text.selectAll();
			}
		});

		input_elem_text.setEditable(true);
		input1.add(input_elem_text);
		input1.add(insertButton);

		input2 = new JPanel(); 			// The Second Panel

		minButton = new JButton("Minimum");
		minButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				min = tree.min().get_value();
				min_text.setText(min);
			}
		});
		input2.add(minButton);
		min_text = new JTextField(10);
		min_text.setEditable(false);
		input2.add(min_text);

		maxButton = new JButton("Maximum");
		maxButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				max = tree.max().get_value();
				max_text.setText(max);
			}
		});
		input2.add(maxButton);
		max_text = new JTextField(10);
		max_text.setEditable(false);
		input2.add(max_text);

		clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				is_new_tree = true;
				input_elem_text.setText("");
				min_text.setText("");
				max_text.setText("");
			}
		});
		input2.add(clearButton);
		
		output = new JPanel();             // The Third Panel
		
		
		printButton = new JButton("Print Values");
		output.add(printButton);
		output_text  = new JTextArea("",1,30);
		output.add(output_text);  
		
		printButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String str;
				str = tree.inorder("");
				output_text.setText(str);
			}
		});
		

		inputPanel.add("North", input1);
		inputPanel.add("South", input2);

		add("North", inputPanel);
		add("Center", output);

		setSize(600, 400); // for the frame
		setVisible(true);
	}

	public static void main(String[] args) {
		new TreeGUIBasic();
	}

}

//*********************************************************
//Generalized Binary Search Tree with Duplicates in Java
//*********************************************************

abstract class Abs_tree {
	public Abs_tree(Element n) {
		node = n;
		left = null;
		right = null;
	};

	public void insert(Element n) {
		if (node.equal(n))
			count_duplicates();
		else if (node.less_than(n))
			if (right == null)
				right = add_node(n);
			else
				right.insert(n);
		else if (left == null)
			left = add_node(n);
		else
			left.insert(n);
	}

	public Element min() {
		if (left != null)
			return left.min();
		else
			return node;
	}

	public Element max() {
		if (right != null)
			return right.max();
		else
			return node;
	}

	public String inorder(String str) {
		if (left != null)
			str = left.inorder(str);
		str = str + " " + this.get_value();
		if (right != null)
			str = right.inorder(str);
		return str;
	}

	public Abs_tree get_left() {
		return left;
	}

	public Abs_tree get_right() {
		return right;
	}

	protected Element node;
	protected Abs_tree left;
	protected Abs_tree right;

	protected abstract void count_duplicates();
	protected abstract Abs_tree add_node(Element n);
	public abstract String get_value();
}

//************************* Tree ******************************

class Tree extends Abs_tree {
	public Tree(Element n) {
		super(n);
	}

	protected Abs_tree add_node(Element n) {
		return new Tree(n);
	}

	protected void count_duplicates() {
	}

	public String get_value() {
		return (node.get_value());
	}
}

//************************ Duptree ******************************

class Duptree extends Abs_tree {
	public Duptree(Element n) {
		super(n);
		count = 1;
	}

	protected Abs_tree add_node(Element n) {
		return new Duptree(n);
	}

	protected void count_duplicates() {
		count++;
	}

	public String get_value() {
		return (node.get_value() + '/' + count);
	}

	protected int count;
}

//*************** Tree Element definitions *************

interface Element {
	public boolean equal(Element n);

	public boolean less_than(Element n);

	public String get_value();
}

class IntElem implements Element {
	private int value;

	public IntElem(int i) {
		value = i;
	}

	public boolean equal(Element n) {
		return (this.value == ((IntElem) n).getValue());
	}

	public boolean less_than(Element n) {
		return (this.value < ((IntElem) n).getValue());
	}

	public String get_value() {
		return (String.valueOf(value));
	}

	public int getValue() {
		return value;
	}
}

class StringElem implements Element {
	private String value;

	public StringElem(String s) {
		value = s;
	}

	public boolean equal(Element n) {
		return (this.value.equals(((StringElem) n).get_value()));
	}

	public boolean less_than(Element n) {
		return (value.compareTo(((StringElem) n).get_value()) < 0);
	}

	public String get_value() {
		return (value);
	}
}





