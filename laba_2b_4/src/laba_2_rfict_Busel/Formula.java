package laba_2_rfict_Busel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
@SuppressWarnings("serial")   // пропуск предупреждений связаных с неправильным сохранением объектов в виде байт



public class Formula extends JFrame{    //JFrame- окна с рамкой
    private static final int WIDTH = 500;
    private static final int HEIGHT = 420;

    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    private JTextField textFieldResult;
    private JTextField textFieldMemory;

    private ButtonGroup radioButtons = new ButtonGroup();
    private Box hboxFormulaType = Box.createHorizontalBox();
    private int formulaId = 1;
    private double sum = 0;
    private boolean flag = false;

    public Double caculated1(Double x,Double y,Double z)
    {
        return Math.pow(Math.pow(Math.sin(y)+y*y+Math.pow(Math.E,Math.cos(y)),1/2)+
                Math.pow( Math.log(z*z)+Math.sin(Math.PI*x*x),1/3),1/2);
    }

    public Double caculated2(Double x,Double y,Double z) {
      if(y==-1){
         JOptionPane.showMessageDialog(Formula.this,
               "Деление на ноль", "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
       return 0.0;
         }
        return Math.pow(y,1/2)*(3*Math.pow(z,x))/Math.pow(1+y*y*y,1/2);
    }

    private void addRadioButton(String buttonName, final int formulaId){  //функция для радиокнопки(один кружок тру, второй фолз
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                Formula.this.formulaId = formulaId;
            } });
        radioButtons.add(button);
        hboxFormulaType.add(button);
        }

        public Formula(){
        super("Вычичление формулы");
        setSize(WIDTH,HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit(); //связывает различные компоненты
             setLocation((kit.getScreenSize().width - WIDTH)/2,
                    (kit.getScreenSize().height - HEIGHT)/2);
             hboxFormulaType.add(Box.createHorizontalGlue()); addRadioButton("Формула 1", 1);
            addRadioButton("Формула 2", 2); radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(), true);
            hboxFormulaType.add(Box.createHorizontalGlue()); hboxFormulaType.setBorder(
                    BorderFactory.createLineBorder(Color.PINK));
            JLabel labelForX=new JLabel("X:");
            textFieldX = new JTextField("0", 10);
            textFieldX.setMaximumSize(textFieldX.getPreferredSize());

            JLabel labelForY=new JLabel("Y:");
            textFieldY = new JTextField("0", 10);
            textFieldY.setMaximumSize(textFieldY.getPreferredSize());

            JLabel labelForZ=new JLabel("Z:");
            textFieldZ = new JTextField("0", 10);
            textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());

            Box hboxVariables = Box.createHorizontalBox(); //создание контейнера коробка с горизонтальной укладкой
            hboxVariables.setBorder(BorderFactory.createLineBorder(Color.PINK)); //рамка
            hboxVariables.add(Box.createHorizontalGlue()); //клей для максимального удаления
            hboxVariables.add(labelForX);  //подпись для х
            hboxVariables.add(Box.createHorizontalStrut(10)); //распорка между надписью и полем для значений
            hboxVariables.add(textFieldX);
            hboxVariables.add(Box.createHorizontalStrut(100));

            hboxVariables.add(labelForY);
            hboxVariables.add(Box.createHorizontalStrut(10));
            hboxVariables.add(textFieldY);
            hboxVariables.add(Box.createHorizontalStrut(100));

            hboxVariables.add(labelForZ);
            hboxVariables.add(Box.createHorizontalStrut(10));
            hboxVariables.add(textFieldZ);
            hboxVariables.add(Box.createHorizontalStrut(100));

            JLabel labelForResult = new JLabel("Результат:"); // создание подписи для поля с результатом
            textFieldResult = new JTextField("0", 10);// создание текстового поля
            textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());

            JLabel labelForMemory = new JLabel("Память:"); // создание подписи для поля с результатом
            textFieldMemory = new JTextField("0", 10);// создание текстового поля
            textFieldMemory.setMaximumSize(textFieldMemory.getPreferredSize());

            Box hboxResult=Box.createHorizontalBox();
            hboxResult.add(Box.createHorizontalGlue());
            hboxResult.add(labelForResult);
            hboxResult.add(Box.createHorizontalStrut(10));
            hboxResult.add(textFieldResult);
            hboxResult.add(Box.createHorizontalStrut(100));

            hboxResult.add(labelForMemory);
            hboxResult.add(Box.createHorizontalStrut(10));
            hboxResult.add(textFieldMemory);
            hboxResult.add(Box.createHorizontalGlue());
            hboxResult.setBorder(BorderFactory.createLineBorder(Color.PINK));
            JButton MC = new JButton("MC");
            JButton M = new JButton("M+");


            JButton buttonCalc = new JButton("Вычислить");
            buttonCalc.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {//обработчик нажатия на кнопку
                    try {
                        Double x = Double.parseDouble(textFieldX.getText());
                        Double  y= Double.parseDouble(textFieldY.getText());
                        Double z = Double.parseDouble(textFieldZ.getText());
                        Double result;
                        if(formulaId==1)
                            result=caculated1(x,y,z);
                        else
                            result=caculated2(x,y,z);
                        if(flag) sum+=result;
                        else sum=0;
                    textFieldResult.setText(toString().format("%-10.3f%n", (result + sum)));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Formula.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
                } }
            });


            M.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ev) {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double  y= Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double result;
                    if(formulaId==1)
                        result=caculated1(x,y,z);
                    else
                        result=caculated2(x,y,z);
                   // if(flag)
                        sum+=result;
                   // else sum=0;
                    textFieldMemory.setText(toString().format("%-10.3f%n", (sum)));
                   // flag = false;
                }
            });

            MC.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ev) {
                    textFieldMemory.setText("0");
                }
            });


            JButton buttonReset = new JButton("Очистить поля");
            buttonReset.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ev) {
                    textFieldX.setText("0");
                    textFieldZ.setText("0");
                    textFieldY.setText("0");
                    textFieldResult.setText("0");
                } });
            Box hboxButtons = Box.createHorizontalBox();
            hboxButtons.add(Box.createHorizontalGlue());
            hboxButtons.add(buttonCalc);
            hboxButtons.add(MC);
            hboxButtons.add(M);
            hboxButtons.add(Box.createHorizontalStrut(30));
            hboxButtons.add(buttonReset);
            hboxButtons.add(Box.createHorizontalGlue());
            hboxButtons.setBorder(BorderFactory.createLineBorder(Color.PINK));
            Box contentBox = Box.createVerticalBox();
            contentBox.add(Box.createVerticalGlue());
            contentBox.add(hboxFormulaType);
            contentBox.add(hboxVariables);
            contentBox.add(hboxResult);
            contentBox.add(hboxButtons);
            contentBox.add(Box.createVerticalGlue());
            getContentPane().add(contentBox, BorderLayout.CENTER);
        }
}
