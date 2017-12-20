package Principal;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.text.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/*
    PROJETO DA QUESTAO V - CALCULO NUMERICO - 2017.1;
    PROFESSORA: KATIA GALDINO
    
    JOÃO PAULO
    LEONARDO

 */

/**
 *
 * @author Leonardo
 */
public class Principal extends javax.swing.JFrame {

    double k1, k2, k3, k4, F,
            k1x1 = 0, k1x2 = 0, k1x3 = 0, k1x4 = 0,
            k2x1 = 0, k2x2 = 0, k2x3 = 0, k2x4 = 0,
            k3x1 = 0, k3x2 = 0, k3x3 = 0, k3x4 = 0,
            k4x1 = 0, k4x2 = 0, k4x3 = 0, k4x4 = 0,
            m1x1 = 0, m1x2 = 0, m1x3 = 0, m1x4 = 0,
            m2x1 = 0, m2x2 = 0, m2x3 = 0, m2x4 = 0,
            m3x1 = 0, m3x2 = 0, m3x3 = 0, m3x4 = 0,
            m4x1 = 0, m4x2 = 0, m4x3 = 0, m4x4 = 0,
            rs1 = 0, rs2 = 0, rs3 = 0, rs4 = 0;
    double[][] matrizOriginal = new double[4][5];
    int n = 4;

    /**
     * Creates new form Principal
     */
    public Principal() throws IOException {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        setIcon();
    }
    
    private void setIcon() throws IOException {
        setIconImage( ImageIO.read( getClass().getResourceAsStream("/Images/favicon.png") ) );
    }

    public void variveis() {
        this.k1 = Double.parseDouble(K1_tf.getText());
        this.k2 = Double.parseDouble(K2_tf.getText());
        this.k3 = Double.parseDouble(K3_tf.getText());
        this.k4 = Double.parseDouble(K4_tf.getText());
        this.F = Double.parseDouble(F_tf.getText());
    }

    public void sistemaInicial() throws InterruptedException {

        tfResultado.append("Aguarde...\n");
        tfResultado.append("Criando variáveis...\n");
        tfResultado.append("Leitura dos campos...\n");
        tfResultado.append("Definindo constantes...\n\n");

        tfResultado.append("Sistema Inicial com as Constantes:\n");
        tfResultado.append(" |" + String.valueOf(k2) + " * (X2 - X1) = " + String.valueOf(k1) + " * X1\n");
        tfResultado.append(" |" + String.valueOf(k3) + " * (X3 - X2) = " + String.valueOf(k2) + " * (X2 - X1)\n");
        tfResultado.append(" |" + String.valueOf(k4) + " * (X4 - X3) = " + String.valueOf(k3) + " * (X3 - X2)\n");
        tfResultado.append(" |" + String.valueOf(F) + " = " + String.valueOf(k4) + " * (X4-X3)\n\n");

        tfResultado.append("Resolvendo Sistema Linear\n");
        
        /*
            Sistema Inicial:
            |K2*(X2-X1) = K1*X1
            |K3*(X3-X2) = K2*(X2-X1)
            |K4*(X4-X3) = K3*(X3-X2)
            |F = K4*(X4-X3)
        */
        
        // LINHA 1 DO SISTEMA LINEAR ----------- -----------  ----------- -----------  ----------- -----------  
        //k2x2 - k2x1 = k1*x1 <=> - k1x1 -k2x1 + k2x2 = 0
        k2x2 = k2 * 1.0;
        k2x1 = k2 * (-1.0);
        k1x1 = k1 * (1.0);
        k1x1 = mudaSinal(k1x1); // PASSANDO VALOR PRO PRIMEIRO MEMBRO PRA ZERAR O SEGUNDO MEMBRO
        double r1x1 = k1x1 + k2x1;
        tfResultado.append("| " + r1x1 + "x1 " + exibeSinal(k2x2) + k2x2 + "x2 = " + rs1 + "\n");
        m1x1 = r1x1;
        m1x2 = k2x2;
        matrizOriginal[0][0] = m1x1;
        matrizOriginal[0][1] = m1x2;
        matrizOriginal[0][2] = m1x3;
        matrizOriginal[0][3] = m1x4;
        matrizOriginal[0][4] = rs1;

        // LINHA 2 DO SISTEMA LINEAR ----------- -----------  ----------- -----------  ----------- -----------  
        // k3x3 - k3x2 = k2x2 - k2x1 <=> k2x1 - k2x2 - k3x2 + k3x3 = 0
        k3x3 = k3 * 1.0;
        k3x2 = k3 * (-1.0);
        k2x2 = k2 * (1.0);
        k2x1 = k2 * (-1.0);
        k2x2 = mudaSinal(k2x2); // PASSANDO VALOR PRO PRIMEIRO MEMBRO PRA ZERAR O SEGUNDO MEMBRO
        k2x1 = mudaSinal(k2x1);
        double r2x2 = k2x2 + k3x2;
        tfResultado.append("| " + k2x1 + "x1 " + exibeSinal(r2x2) + r2x2 + "x2 " + exibeSinal(k3x3) + k3x3 + "x3 = " + rs2 + "\n");
        m2x1 = k2x1;
        m2x2 = r2x2;
        m2x3 = k3x3;
        matrizOriginal[1][0] = m2x1;
        matrizOriginal[1][1] = m2x2;
        matrizOriginal[1][2] = m2x3;
        matrizOriginal[1][3] = m2x4;
        matrizOriginal[1][4] = rs2;

        // LINHA 3 DO SISTEMA LINEAR ----------- -----------  ----------- -----------  ----------- -----------  
        // k4x4 - k4x3 = k3x3 - k3x2  <=> k3x2 - k4x3 - k3x3 + k4x4 = 0
        k4x4 = k4 * (1.0);
        k4x3 = k4 * (-1.0);
        k3x3 = k3 * (1.0);
        k3x2 = k3 * (-1.0);
        k3x3 = mudaSinal(k3x3); // PASSANDO VALOR PRO PRIMEIRO MEMBRO PRA ZERAR O SEGUNDO MEMBRO
        k3x2 = mudaSinal(k3x2);
        double r3x3 = k3x3 + k4x3;
        tfResultado.append("| " + k3x2 + "x2 " + exibeSinal(r3x3) + r3x3 + "x3 " + exibeSinal(k4x4) + k4x4 + "x4 = " + rs3 + "\n");
        m3x2 = k3x2;
        m3x3 = r3x3;
        m3x4 = k4x4;
        matrizOriginal[2][0] = m3x1;
        matrizOriginal[2][1] = m3x2;
        matrizOriginal[2][2] = m3x3;
        matrizOriginal[2][3] = m3x4;
        matrizOriginal[2][4] = rs3;

        // LINHA 4 DO SISTEMA LINEAR ----------- -----------  ----------- -----------  ----------- -----------  
        // F = K4*(X4-X3) <=> k4x4 - k4x3 = F
        k4x4 = k4 * (1.0);
        k4x3 = k4 * (-1.0);
        k4x4 = mudaSinal(k4x4); // PASSANDO VALOR PRO PRIMEIRO MEMBRO PRA ZERAR O SEGUNDO MEMBRO
        k4x3 = mudaSinal(k4x3);
        rs4 = mudaSinal(F);
        tfResultado.append("| " + k4x3 + "x3 " + exibeSinal(k4x4) + k4x4 + "x4 = " + rs4 + "\n");
        m4x3 = k4x3;
        m4x4 = k4x4;
        matrizOriginal[3][0] = m4x1;
        matrizOriginal[3][1] = m4x2;
        matrizOriginal[3][2] = m4x3;
        matrizOriginal[3][3] = m4x4;
        matrizOriginal[3][4] = rs4;

        tfResultado.append("\nExibe a Matriz:\n");
        exibeMatriz();
        tfResultado.append("\n\n");
    }

    public void exibeMatriz() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n + 1; j++) {
                tfResultado.append("\t" + matrizOriginal[i][j]);
                if (j == n) {
                    tfResultado.append("\n");
                }
            }
        }
    }

    public void metodoGauss() {
        double m;
        for (int k = 0; k < n - 1; k++) {
            for (int i = k + 1; i < n; i++) {
                //Multiplicadores
                m = -1 * (matrizOriginal[i][k] / matrizOriginal[k][k]);
                tfResultado.append("\nPivo da vez:\n" + matrizOriginal[k][k]);
                tfResultado.append("\nMultiplicador para a matriz A:\n" + m);
                for (int j = 0; j < n + 1; j++) {
                    matrizOriginal[i][j] = (matrizOriginal[k][j] * m) + matrizOriginal[i][j];
                }
            }
            tfResultado.append("\n\nMatriz A Transformada em Gauss: \n\n");
            exibeMatriz();
        }
    }

    public void exibeResultado() {
        int l, var;
        double termo, pos;
        double[] x = new double[matrizOriginal.length];
        for (int i = 0; i < n; i++) {
            termo = 0;
            l = n - i;
            for (int j = l; j < n; j++) {
                termo = termo + (x[j] * matrizOriginal[n - i - 1][j]);
            }
            x[n - i - 1] = (matrizOriginal[n - 1 - i][n] - termo) / matrizOriginal[n - i - 1][n - i - 1];
            var = n-i;
            pos = x[n-i-1];
            tfResultado.append("\nVariável: x" + var + " = " +  pos);
        }
    }
    
    public void creditos(){
        tfResultado.append("\n\nCRÉDITOS:\n");
        tfResultado.append("PROJETO DA QUESTAO V - CALCULO NUMERICO - 2017.1\n");
	tfResultado.append("PROFESSORA: KATIA GALDINO\n");
	tfResultado.append("ALUNOS: JOÃO PAULO - LEONARDO\n");
        tfResultado.append("-----------------------------------------------------\n\n\n");
    }

    public double mudaSinal(double num) {
        return -num;
    }

    public String exibeSinal(double num) {
        return (num < 0.0) ? "" : "+";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jbLimparResultados = new javax.swing.JButton();
        F_tf = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        K1_tf = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        K2_tf = new javax.swing.JTextField();
        K3_tf = new javax.swing.JTextField();
        K4_tf = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jbCalcule = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tfResultado = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PROJETO DA QUESTAO V - CALCULO NUMERICO ");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jbLimparResultados.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        jbLimparResultados.setText("Limpar Resultados");
        jbLimparResultados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbLimparResultadosActionPerformed(evt);
            }
        });

        F_tf.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        F_tf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        F_tf.setText("2000");

        jLabel2.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        jLabel2.setText("K3 =");

        jLabel6.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        jLabel6.setText("kg/s^2");

        jLabel3.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        jLabel3.setText("K1 =");

        jLabel7.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        jLabel7.setText("kg/s^2");

        jLabel4.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        jLabel4.setText("K4 =");

        jLabel8.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        jLabel8.setText("kg/s^2");

        jLabel5.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        jLabel5.setText("k2 =");

        jLabel9.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        jLabel9.setText("kg/s^2");

        K1_tf.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        K1_tf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        K1_tf.setText("150");

        jLabel10.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        jLabel10.setText("kg");

        K2_tf.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        K2_tf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        K2_tf.setText("50");

        K3_tf.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        K3_tf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        K3_tf.setText("75");

        K4_tf.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        K4_tf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        K4_tf.setText("225");

        jLabel1.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        jLabel1.setText("F   =");

        jbCalcule.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        jbCalcule.setText("Calcule");
        jbCalcule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCalculeActionPerformed(evt);
            }
        });

        tfResultado.setColumns(20);
        tfResultado.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        tfResultado.setRows(5);
        jScrollPane2.setViewportView(tfResultado);

        jPanel2.setBackground(new java.awt.Color(34, 171, 140));

        jPanel5.setBackground(new java.awt.Color(119, 36, 115));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 38, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logo.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(K2_tf))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(F_tf)
                                            .addComponent(K1_tf, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel4))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(K3_tf)
                                            .addComponent(K4_tf))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addGap(39, 39, 39))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jbCalcule, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbLimparResultados)
                                .addGap(30, 30, 30))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 646, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 964, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {K1_tf, K2_tf, K3_tf, K4_tf});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(F_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(K1_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(K2_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(K3_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(K4_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbCalcule, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                            .addComponent(jbLimparResultados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(32, 32, 32)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jbCalcule, jbLimparResultados});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {F_tf, K1_tf, K2_tf, K3_tf, K4_tf});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbCalculeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCalculeActionPerformed
        try {
            variveis();
            sistemaInicial();
            metodoGauss();
            exibeResultado();
            creditos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jbCalculeActionPerformed

    private void jbLimparResultadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbLimparResultadosActionPerformed
        tfResultado.setText("");
    }//GEN-LAST:event_jbLimparResultadosActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Principal().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField F_tf;
    private javax.swing.JTextField K1_tf;
    private javax.swing.JTextField K2_tf;
    private javax.swing.JTextField K3_tf;
    private javax.swing.JTextField K4_tf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jbCalcule;
    private javax.swing.JButton jbLimparResultados;
    private javax.swing.JTextArea tfResultado;
    // End of variables declaration//GEN-END:variables
}
