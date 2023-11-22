package Interface;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JList;

import Executor.*;

/* TODO:
 * Tem o step -> instrução por instrução (PC+1 e executa)
 * read tem q parar a execução do programa
 * desabilitar o step e o run quando o programa terminar
 * tem q criar um função pra atualizar a memória e os registradores
 * output ser um textfield tbm
 */

//executor.executarPrograma();

public class ExecutorInterface extends javax.swing.JFrame {

    public ExecutorInterface() {
        executor = new Executor();
        addressLoaded = false;
        initComponents();
    }

    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        registerTable = new javax.swing.JTable();
        registerLabel = new javax.swing.JLabel();
        outputField = new javax.swing.JLabel();
        memoryLabel = new javax.swing.JLabel();
        loadButton = new javax.swing.JButton();
        stepButton = new javax.swing.JButton();
        runButton = new javax.swing.JButton();
        javax.swing.JList<String> memoryList = new javax.swing.JList<>();
        inputField = new javax.swing.JTextField();
        fileChooser = new javax.swing.JFileChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        // REGISTER TABLE AND LABEL 
        registerTable.setBackground(new java.awt.Color(204, 204, 204));
        registerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"PC", ""},
                {"A", ""},
                {"X", ""},
                {"L", ""},
                {"B", ""},
                {"S", ""},
                {"T", ""},
                {"SW", ""}
            },
            new String [] {
                "Name", "Value"
            }
        ));
        registerTable.setAlignmentY(1.0F);
        registerTable.setEnabled(false);
        registerTable.setGridColor(new java.awt.Color(0, 0, 0));
        registerTable.setRowHeight(30);
        registerTable.setSelectionBackground(new java.awt.Color(204, 204, 255));
        registerTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        registerTable.setShowGrid(true);
        jScrollPane2.setViewportView(registerTable);
        if (registerTable.getColumnModel().getColumnCount() > 0) {
            registerTable.getColumnModel().getColumn(0).setMinWidth(50);
            registerTable.getColumnModel().getColumn(0).setPreferredWidth(50);
            registerTable.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        registerLabel.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        registerLabel.setText("Registers");
        // END

        // MEMORY LIST AND LABEL
        attMemoria(memoryList);
        memoryList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        memoryList.setVisibleRowCount(20);
        jScrollPane1.setViewportView(memoryList);

        memoryLabel.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        memoryLabel.setText("Memory");
        // END

        // INPUT AND OUTPUT
        inputField.setText("Input");
        inputField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputFieldActionPerformed(evt);
            }
        });

        outputField.setText("Output");
        // END


        // BUTTONS
        loadButton.setText("Load Addresses");
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt, memoryList);
            }
        });

        runButton.setText("Run");
        runButton.setEnabled(false);
        runButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runButtonActionPerformed(evt);
            }
        });

        stepButton.setText("Step");
        stepButton.setEnabled(false);
        stepButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stepButtonActionPerformed(evt);
            }
        });
        // END

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(runButton, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)
                        .addComponent(stepButton, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(outputField, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inputField, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(loadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(memoryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(registerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(registerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(loadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(memoryLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(inputField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(outputField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(runButton, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(stepButton, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(66, 66, 66))
        );
        pack();
    }

    private void inputFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputFieldActionPerformed
        // TODO: tem que fazer pra pegar o texto e mandar pra memoria -- ser entre 1-255
        String enteredText = inputField.getText();
        try {
            int value = Integer.parseInt(enteredText);
            System.out.println(value);
            if ( addressLoaded ) {
                runButton.setEnabled(true);
                stepButton.setEnabled(true);
            }
        } catch (NumberFormatException e) {
            System.out.println("Não é um inteiro válido");
        }
    }

    private void runButtonActionPerformed(ActionEvent evt) {
        stepButton.setEnabled(false);
        loadButton.setEnabled(false);
        executor.executarPrograma();
    }

    private void stepButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stepButtonActionPerformed
        // TODO: terminar o step
        loadButton.setEnabled(false);
    }

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt, JList<String> memoryList) {
        try {
            if( fileChooser.showSaveDialog(rootPane) == javax.swing.JFileChooser.APPROVE_OPTION ) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println(selectedFile.getAbsolutePath());
            addressLoaded = true;
            executor.setPrograma(selectedFile.getAbsolutePath());
            attMemoria(memoryList);
        }
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo.");
        }
        
    }

    private void attRegistradores() {
        registerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"PC", executor.getRegistrador("PC").getValor()},
                {"A", executor.getRegistrador("A").getValor()},
                {"X", executor.getRegistrador("X").getValor()},
                {"L", executor.getRegistrador("L").getValor()},
                {"B", executor.getRegistrador("B").getValor()},
                {"S", executor.getRegistrador("S").getValor()},
                {"T", executor.getRegistrador("T").getValor()},
                {"SW", executor.getRegistrador("SW").getValor()}
            },
            new String [] {
                "Name", "Value"
            }
        ));
    }

    private void attMemoria(JList<String> memoryList) {
        memoryList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = executor.getMemoria().toArray(new String[0]);
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ExecutorInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ExecutorInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ExecutorInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ExecutorInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }


        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ExecutorInterface().setVisible(true);
            }
        });
    }

    private Executor executor;
    private boolean addressLoaded;
    private javax.swing.JTextField inputField;
    private javax.swing.JButton loadButton;
    private javax.swing.JButton stepButton;
    private javax.swing.JButton runButton;
    private javax.swing.JLabel outputField;
    private javax.swing.JLabel registerLabel;
    private javax.swing.JLabel memoryLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable registerTable;
    private javax.swing.JFileChooser fileChooser;
}
