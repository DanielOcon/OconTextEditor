package help;

import com.formdev.flatlaf.FlatLaf;

import java.awt.event.WindowAdapter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JEditorPane;
import java.awt.event.WindowEvent;

import danielocon.ocontexteditor.MainWindow;
import themes.MiTema;

/**
 *
 * @author 2dama
 */
public class Help extends javax.swing.JDialog {

    private MainWindow mainWindow;

    /**
     * Creates new form PantallaJDialog
     *
     * @param parent
     * @param modal
     */
    public Help(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        this.mainWindow = (MainWindow) parent;
        initComponents();
        otherInit();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JTabbedPane jTabbedPane = new javax.swing.JTabbedPane();
        javax.swing.JScrollPane jScrollPane_Intro = new javax.swing.JScrollPane();
        jEditorPane_Intro = new JEditorPane();
        javax.swing.JScrollPane jScrollPane_FileTools = new javax.swing.JScrollPane();
        jEditorPane_FileTools = new JEditorPane();
        javax.swing.JScrollPane jScrollPane_EditingTools = new javax.swing.JScrollPane();
        jEditorPane_EditingTools = new JEditorPane();
        javax.swing.JScrollPane jScrollPane_Credits = new javax.swing.JScrollPane();
        jEditorPane_Credits = new JEditorPane();
        javax.swing.JScrollPane jScrollPane_Logs = new javax.swing.JScrollPane();
        jEditorPane_Logs = new JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jEditorPane_Intro.setEditable(false);
        jScrollPane_Intro.setViewportView(jEditorPane_Intro);

        jTabbedPane.addTab("Introduction", jScrollPane_Intro);

        jEditorPane_FileTools.setEditable(false);
        jScrollPane_FileTools.setViewportView(jEditorPane_FileTools);

        jTabbedPane.addTab("File Tools", jScrollPane_FileTools);

        jEditorPane_EditingTools.setEditable(false);
        jScrollPane_EditingTools.setViewportView(jEditorPane_EditingTools);

        jTabbedPane.addTab("Editing Tools", jScrollPane_EditingTools);

        jEditorPane_Credits.setEditable(false);
        jScrollPane_Credits.setViewportView(jEditorPane_Credits);

        jTabbedPane.addTab("Credits", jScrollPane_Credits);

        jEditorPane_Logs.setEditable(false);
        jScrollPane_Logs.setViewportView(jEditorPane_Logs);

        jTabbedPane.addTab("Logs", jScrollPane_Logs);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File themes = new File("src/mian/java/themes");
        FlatLaf.registerCustomDefaultsSource(themes);
        MiTema.setup();
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            Help dialog = new Help(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JEditorPane jEditorPane_Credits;
    private JEditorPane jEditorPane_EditingTools;
    private JEditorPane jEditorPane_FileTools;
    private JEditorPane jEditorPane_Intro;
    private JEditorPane jEditorPane_Logs;
    // End of variables declaration//GEN-END:variables

    private void HTMLViewer(JEditorPane editorPane, String path) {
        try {
            File htmlFile = new File(path);
            editorPane.setPage(htmlFile.toURI().toURL());
        } catch (IOException e) {
            System.err.println("Error loading HTML file: " + e.getMessage());
        }
    }

    private void otherInit() {
        HTMLViewer(jEditorPane_Intro, "src/mian/java/html/help.html");
        HTMLViewer(jEditorPane_FileTools, "src/mian/java/html/fileTools.html");
        HTMLViewer(jEditorPane_EditingTools, "src/mian/java/html/editingTools.html");
        HTMLViewer(jEditorPane_Credits, "src/mian/java/html/credits.html");
        showLogs();
        // Add window listener
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                mainWindow.helpWindowClosed();
            }
        });

    }

    private void showLogs() {
        try {
            // Read the logs from the file and display them in an EditorPane
            File logFile = new File("src/mian/java/logs/logger.log");
            try (BufferedReader reader = new BufferedReader(new FileReader(logFile))) {
                FileReader fileReader = new FileReader(logFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                String logContent = sb.toString();

                // Display the content in an EditorPane
                jEditorPane_Logs.setContentType("text/html");
                jEditorPane_Logs.setText(logContent);
                reader.close();
            }
        } catch (IOException e) {
            System.err.println("Error opnening the log");
        }
    }
}
