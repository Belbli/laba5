import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.*;

public class Calculator {
    private static JTextField textField= new JTextField("0");
    private static int numberOperation = 0;
    private static double number=0;
    private static String number2= "";

    public static void main(String[] args) {
        createGUI();
    }

    private static void createGUI(){
        JFrame frame=new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contents1=new JPanel();
        contents1.setLayout(new BoxLayout(contents1,BoxLayout.Y_AXIS));

        //Окно для ввода чисел
        textField.setPreferredSize(new Dimension(215,50));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setFont(new Font("Arial", Font.PLAIN, 24));
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c =e.getKeyChar();
                if (c<'0' || c>'9'){
                    e.consume();
                }else {
                    textField.setText(number2);
                    number2+=c;

                }
            }
        });
        contents1.add(textField);

        //Кнопки
        JPanel contents2=new JPanel(new GridLayout(5,4,5,5));
        contents2.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        addButton(contents2);

        frame.add("North",contents1);
        frame.add("Center",contents2);
        frame.setSize(new Dimension(300,400));
        frame.setVisible(true);
    }

    private static void addButton(JPanel contents){
        String[] array={"÷","←","c","X²","×","9","8","7","-","6","5","4","+","3","2","1","=",".","0","+/-"};
        JButton[] button =new JButton[array.length];
        for (int i=0;i<array.length;i++){
                button[i] = new JButton(array[i]);
                contents.add(button[i]);
            int finalI = i;
            button[i].addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c =e.getKeyChar();
                    if (c<'0' || c>'9'){
                        e.consume();
                    }else {
                        number2+=c;
                        textField.setText(number2);
                    }
                }
            });
            button[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (Character.isDigit(button[finalI].getText().charAt(0)) || button[finalI].getText().equals(".") || button[finalI].getText().equals("+/-")) {
                            if (button[finalI].getText().equals("+/-")){
                                System.out.println(number2);
                                number2=String.valueOf(Integer.parseInt(number2)*(-1));
                            }else {
                                number2 += (button[finalI].getText());
                            }
                            textField.setText(number2);
                        }else{
                                switch (numberOperation) {
                                    case 1:
                                        textField.setText(String.valueOf(number/Double.parseDouble(number2)));
                                        numberOperation=0;
                                        break;
                                    case 2:
                                        textField.setText(String.valueOf(number*Double.parseDouble(number2)));
                                        numberOperation=0;
                                        break;
                                    case 3:
                                        textField.setText(String.valueOf(number-Double.parseDouble(number2)));
                                        numberOperation=0;
                                        break;
                                    case 4:
                                        textField.setText(String.valueOf(number+Double.parseDouble(number2)));
                                        numberOperation=0;
                                        break;
                                    default:
                                        numberOperation=0;
                                        break;
                                }

                                switch (button[finalI].getText()) {
                                    case "÷":
                                        numberOperation = 1;
                                        break;
                                    case "×":
                                        numberOperation = 2;
                                        break;
                                    case "-":
                                        numberOperation = 3;
                                        break;
                                    case "+":
                                        numberOperation = 4;
                                        break;
                                    case "=":
                                        numberOperation = 5;
                                        break;
                                    case "c":
                                        textField.setText("0");
                                        break;
                                    case "←":
                                        try {
                                            if (textField.getText().length()>1) {
                                                textField.setText(textField.getText(0, textField.getText().length() - 1));
                                            }else textField.setText("0");
                                        } catch (BadLocationException ex) {
                                            ex.printStackTrace();
                                        }
                                        break;
                                    case "X²":
                                        textField.setText(String.valueOf(Math.pow(Double.parseDouble(textField.getText()),2)));
                                        break;
                                    default:
                                        break;
                                }
                                number=Double.parseDouble(textField.getText());
                                number2="";

                        }
                    }
                });
        }
    }
}

