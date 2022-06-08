package cz.czechitas.lekce10.view;

import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.list.SelectionInList;
import cz.czechitas.lekce10.Aplikace;
import cz.czechitas.lekce10.controller.KontaktyController;
import cz.czechitas.lekce10.formbuilder.FormBuilder;
import cz.czechitas.lekce10.formbuilder.FormBuilderWithContainer;
import cz.czechitas.lekce10.model.OsobaBean;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

/**
 * Formulář – hlavní okno aplikace.
 */
public class HlavniOkno extends JFrame {
  private final KontaktyController controller;

  public HlavniOkno(KontaktyController controller) throws HeadlessException {
    super("Kontakty");
    this.controller = controller;
    this.init();
  }

  public void start() {
    setLocationRelativeTo(null);
    setVisible(true);
  }

  private void init() {
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setIconImage(new ImageIcon(Aplikace.class.getResource("czechitas-icon.png")).getImage());
    setLayout(new MigLayout("wrap 4", "[right]rel[50:75:250,grow,fill]unrel[right]rel[50:75:250,grow,fill]"));
    setMinimumSize(new Dimension(400, 200));

    FormBuilderWithContainer<OsobaBean> formBuilder = FormBuilder.create(controller.getModel())
            .container(this);

    formBuilder
            .label("&Jméno")
            .textField("jmeno")
            .add();
    formBuilder
            .label("&Příjmení")
            .textField("prijmeni")
            .add();

    formBuilder
            .label("Titul pře&d")
            .textField("titulPred")
            .add();
    formBuilder
            .label("Titul &za")
            .textField("titulZa")
            .add();

    formBuilder
            .label("Celé jméno")
            .textField("celeJmeno", tf -> tf.setEditable(false))
            .add("span");

    formBuilder
            .label("&Adresa")
            .textField("adresa")
            .add("span");

    formBuilder
            .label("&Kraj")
            .comboBox("kraj", KontaktyController.KRAJE)
            .add("span");

    formBuilder
            .label("&Datum narození")
            .dateField("datumNarozeniDate")
            .add();

    formBuilder
            .label("Věk")
            .numberField("vek", ftf -> ftf.setEditable(false))
            .add();

    formBuilder
            .label("&Pohlavi")
            .comboBox("pohlavi", KontaktyController.POHLAVI)
            .add("span");

    formBuilder
            .checkbox("Dospely", "dospely", c -> c.setEnabled(false))
            .add("span 2");

    formBuilder
            .panel(panel -> {
              JButton novyButton = new JButton(controller.getNovyAction());
              JButton ulozitButton = new JButton(controller.getUlozitAction());

              getRootPane().setDefaultButton(ulozitButton);

              panel.add(novyButton);
              panel.add(ulozitButton);
            })
            .add("right, span");

    pack();
  }

}
