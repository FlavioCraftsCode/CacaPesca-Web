
package pi;

import DAO.UsuarioDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import model.Usuario;

/**
 *
 * @author User
 */
public class Login extends javax.swing.JFrame {

  Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    
    public Login() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Email = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Password = new javax.swing.JTextField();
        box = new javax.swing.JComboBox<>();
        Login = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Franklin Gothic Medium", 3, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 0));
        jLabel3.setText("HUNTING AND FISHING");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, -1, -1));

        jLabel1.setFont(new java.awt.Font("Franklin Gothic Medium", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 204, 0));
        jLabel1.setText("LOGIN");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, -1, -1));

        Email.setBackground(new java.awt.Color(255, 255, 255));
        Email.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jPanel2.add(Email, new org.netbeans.lib.awtextra.AbsoluteConstraints(157, 204, 276, 42));

        jLabel2.setFont(new java.awt.Font("Franklin Gothic Medium", 3, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 204, 0));
        jLabel2.setText("Email");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 207, -1, -1));

        jLabel4.setFont(new java.awt.Font("Franklin Gothic Medium", 3, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 204, 0));
        jLabel4.setText("Password");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 292, -1, -1));

        Password.setBackground(new java.awt.Color(255, 255, 255));
        Password.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jPanel2.add(Password, new org.netbeans.lib.awtextra.AbsoluteConstraints(157, 288, 276, 44));

        box.setBackground(new java.awt.Color(51, 51, 51));
        box.setFont(new java.awt.Font("Dialog", 3, 11)); // NOI18N
        box.setForeground(new java.awt.Color(0, 204, 0));
        box.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ADM", "OPERADOR", "USER" }));
        jPanel2.add(box, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 360, 160, 40));

        Login.setBackground(new java.awt.Color(0, 0, 0));
        Login.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        Login.setForeground(new java.awt.Color(0, 204, 0));
        Login.setText("LOGIN");
        Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginActionPerformed(evt);
            }
        });
        jPanel2.add(Login, new org.netbeans.lib.awtextra.AbsoluteConstraints(189, 449, 214, 46));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pi/bandeira de israel e estados unidos com vara de pesca (1).jpeg"))); // NOI18N
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 0, 620, 530));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 623, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginActionPerformed
    // 1. Captura de dados da interface (View)
    String emailDigitado = Email.getText();
    String senhaDigitada = Password.getText();
    String tipoSelecionado = String.valueOf(box.getSelectedItem());

    try {
        // 2. Chamada da Regra de Negócio/Persistência via DAO
        // Aplicando o Princípio da Responsabilidade Única (SRP)
        UsuarioDAO dao = new UsuarioDAO();
        Usuario userLogado = dao.autenticar(emailDigitado, senhaDigitada, tipoSelecionado);

        // 3. Lógica de decisão baseada no retorno do DAO
        if (userLogado != null) {
            JOptionPane.showMessageDialog(this, "Login realizado com sucesso como " + userLogado.getTipoUsuario());
            
            // Método auxiliar para navegação (melhora a legibilidade)
            direcionarParaTelaCorrespondente(userLogado.getTipoUsuario());
        } else {
            JOptionPane.showMessageDialog(this, "E-mail ou senha incorretos.");
        }

    } catch (Exception ex) {
        // Tratamento genérico para erros de conexão ou SQL
        JOptionPane.showMessageDialog(this, "Erro ao conectar com o servidor: " + ex.getMessage());
    }
} 

/**
 * Método auxiliar para separar a lógica de navegação da lógica de clique.
 */
private void direcionarParaTelaCorrespondente(String tipo) {
    this.dispose(); // Fecha a tela de login atual
    
    switch (tipo.toUpperCase()) {
        case "ADM":
            new adm().setVisible(true);
            break;
        case "OPERADOR":
            new Operador().setVisible(true);
            break;
        case "USER":
            new user().setVisible(true);
            break;
        default:
            JOptionPane.showMessageDialog(this, "Tipo de usuário desconhecido.");
            this.setVisible(true); // Reabre o login se der erro
            break;
    }

    }//GEN-LAST:event_LoginActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Email;
    private javax.swing.JButton Login;
    private javax.swing.JTextField Password;
    private javax.swing.JComboBox<String> box;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
