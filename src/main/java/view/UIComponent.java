package view;

import service.Backend;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Page extends JPanel {
    protected final Backend backend;
    protected final JFrame frame;

    public Page(JFrame frame, Backend backend) {
        this.backend = backend;
        this.frame = frame;
    }
}
