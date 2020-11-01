package course2.kg.task3;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private DrawPanel dp;


    public MainWindow() throws HeadlessException {
        /*JComboBox funcs = new JComboBox(new String[]{
                "Прямые линии",
                "Кривые Безье"
        }
        );*/
        dp = new DrawPanel();

        this.add(dp);
    }
}
