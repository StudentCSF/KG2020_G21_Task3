package course2.kg.task3;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private DrawPanel dp;
   /* private JComboBox funcs = new JComboBox(new String[] {
            "Прямые линии",
            "Кривые Безье"
    }
    );*/

    public MainWindow() throws HeadlessException {
        dp = new DrawPanel();
        //this.add(funcs);
        this.add(dp);
    }
}
