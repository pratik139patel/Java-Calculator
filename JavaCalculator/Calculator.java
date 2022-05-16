package JavaCalculator;

import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.FocusEvent;
import java.awt.Font;
import java.text.DecimalFormat;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JSlider;
import java.awt.GridBagConstraints;
import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.awt.Insets;

public class Calculator
{
    //Default System Settings
    final static int default_button_color_rgb = (new JButton()).getBackground().getRGB();
    final static String[] color_name = { "main_frame_color", "main_frame_bar_color", "error_msg_color", "matrix_frame_color", "matrix_oper_frame_color", "output_color", "calc_bg_color", "btn_bg_color", "btn_hover_color", "btn_press_color", "btn_border_color", "btn_font_color", "btn_disabled_color", "input_txt_field_color", "color_frame_color", "font_frame_color", "precision_frame_color", "button_layout_frame_color" };
    final static Color[] default_color_arr = { Color.WHITE, Color.GREEN, Color.RED, Color.WHITE, Color.WHITE, new Color(0,255,255), new Color(200, 204, 225), new Color(255,255,0), Color.LIGHT_GRAY, Color.GRAY, Color.BLACK, Color.BLACK, Color.DARK_GRAY, Color.WHITE, new Color(default_button_color_rgb), new Color(default_button_color_rgb), new Color(default_button_color_rgb), new Color(default_button_color_rgb) };
    final static String[] font_name = {"system_font", "main_frame_bar_font", "in_font", "out_font"};
    final static Font[] default_font_arr = { new Font(Font.SANS_SERIF, Font.BOLD, 14), new Font(Font.MONOSPACED, Font.BOLD, 13), new Font(Font.MONOSPACED, Font.BOLD, 13), new Font(Font.MONOSPACED, Font.BOLD, 13) };
    final static String default_df_precision_string = "0.00";
    final static int default_font_alignment = JTextField.CENTER;
    final static int default_btn_arc_height = 20;
    final static int default_btn_arc_width = 20;
    final static int max_font_size = 18;
    final static int min_font_size = 10;
    final static String max_precision = "0.0000";
    final static String min_precision = "0.0";
    final static int max_matrix_coordinate = 20;
    final static int min_matrix_coordinate = 1;
    final static int max_btn_arc_height = 50;
    final static int min_btn_arc_height = 0;
    final static int max_btn_arc_width = 50;
    final static int min_btn_arc_width = 0;

    //Current User Settings
    static Color[] color_arr;
    static Font[] font_arr;
    static DecimalFormat df;
    static int btn_arc_height;
    static int btn_arc_width;

    //main()
    static JFrame main_frame;
    static JMenuBar main_frame_bar;
    static CustomJButton main_frame_cal_btn;
    static CustomJButton main_frame_exit_btn;
    static CustomJButton main_frame_credit_btn;
    static CustomJButton main_frame_matrix_btn;
    static JMenu main_frame_sett_menu;
    static JMenu main_frame_app_menu;
    static JMenuItem main_frame_change_color_frame_item;
    static JMenuItem main_frame_output_precision_menu_item;
    static JMenuItem main_frame_font_style_menu_item;
    static JMenuItem main_frame_app_restart_menu_item;
    static JMenuItem main_frame_app_redeploy_menu_item;
    static JMenuItem main_frame_app_shut_down_menu_item;
    static JMenuItem main_frame_button_layout_menu_item;

    //chooseColor()
    static JFrame color_frame;
    static CustomJButton color_frame_main_frame_color_btn;
    static CustomJButton color_frame_main_frame_bar_color_btn; 
    static CustomJButton color_frame_error_msg_color_btn; 
    static CustomJButton color_frame_matrix_frame_color_btn;
    static CustomJButton color_frame_matrix_oper_frame_color_btn;
    static CustomJButton color_frame_output_color_btn; 
    static CustomJButton color_frame_calc_bg_color_btn;
    static CustomJButton color_frame_btn_bg_color_btn;
    static CustomJButton color_frame_btn_hover_color_btn;
    static CustomJButton color_frame_btn_press_color_btn;
    static CustomJButton color_frame_btn_border_color_btn;
    static CustomJButton color_frame_btn_font_color_btn;
    static CustomJButton color_frame_btn_disabled_color_btn;
    static CustomJButton color_frame_input_text_field_color_btn;
    static CustomJButton color_frame_color_btn;
    static CustomJButton color_frame_font_frame_color_btn;
    static CustomJButton color_frame_precision_frame_color_btn;
    static CustomJButton color_frame_button_layout_frame_color_btn;
    static CustomJButton color_frame_exit_btn;
    static CustomJButton color_frame_reset_color_btn;
    static JSlider color_frame_red_slider;
    static JSlider color_frame_green_slider;
    static JSlider color_frame_blue_slider;
    static CustomJButton color_frame_red_field; 
    static CustomJButton color_frame_green_field; 
    static CustomJButton color_frame_blue_field;
    static CustomJButton color_frame_total_field;
    
    //Matrix()
    static JFrame matrix_frame;
    static CustomJButton matrix_frame_oper_btn;
    static CustomJButton matrix_frame_exit_btn;
    static JLabel matrix_frame_row_label;
    static JLabel matrix_frame_col_label;
    static JTextField matrix_frame_err_bar;
    static JTextField matrix_frame_row_field;
    static JTextField matrix_frame_col_field;

    //MyCalculator()
    static JFrame cal_frame;
    static JTextField cal_frame_result_field;
    static JTextField cal_frame_num_1_field;
    static JTextField cal_frame_num_2_field;
    static CustomJButton cal_frame_add_btn;
    static CustomJButton cal_frame_sub_btn;
    static CustomJButton cal_frame_mul_btn;
    static CustomJButton cal_frame_div_btn;
    static CustomJButton cal_frame_mod_btn;
    static CustomJButton cal_frame_exit_btn;
    static JLabel cal_frame_field_1_label;
    static JLabel cal_frame_field_2_label;
    static JLabel cal_frame_result_field_label;

    //ChooseFont()
    static JFrame font_frame;
    static JTextField font_frame_perview_field;
    static JTextField font_frame_font_name_field;
    static JTextField font_frame_font_style_field;
    static JTextField font_frame_font_size_field;
    static CustomJButton font_frame_perview_field_clear_btn;
    static CustomJButton font_frame_font_name_field_leftbtn;
    static CustomJButton font_frame_font_name_field_rightbtn;
    static CustomJButton font_frame_font_style_field_leftbtn;
    static CustomJButton font_frame_font_style_field_rightbtn;
    static CustomJButton font_frame_font_size_field_decbtn;
    static CustomJButton font_frame_font_size_field_incbtn;
    static CustomJButton font_frame_reset_font_btn;
    static CustomJButton font_frame_exit_btn;
    static CustomJButton font_frame_system_font_btn;
    static CustomJButton font_frame_main_frame_bar_font_btn;
    static CustomJButton font_frame_in_font_btn;
    static CustomJButton font_frame_out_font_btn;
    static int font_frame_font_names_arr_location;
    static int font_frame_font_style_arr_location;
    static JLabel font_frame_font_name_label;
    static JLabel font_frame_font_style_label;
    static JLabel font_frame_font_size_label;

    //ChoosePrecision()
    static JFrame precision_frame;
    static JTextField precision_frame_precision_field;
    static JLabel precision_frame_precision_label;
    static CustomJButton precision_frame_exit_btn;
    static CustomJButton precision_frame_reset_btn;
    static CustomJButton precision_frame_dec_btn;
    static CustomJButton precision_frame_inc_btn;

    //MatrixOper()
    static JFrame matrix_oper_frame;
    static JTextField[][] matrix_oper_frame_user_field_arr;
    static JTextField[][] matrix_oper_frame_out_field_arr;
    static CustomJButton matrix_oper_frame_det_btn;
    static CustomJButton matrix_oper_frame_clear_btn;
    static CustomJButton matrix_oper_frame_exit_btn;
    static CustomJButton matrix_oper_frame_rref_btn;
    static CustomJButton matrix_oper_frame_inverse_btn;
    static JTextField matrix_oper_frame_err_bar;

    //ChooseButtonLayout()
    static JFrame button_layout_frame;
    static JTextField button_layout_frame_arc_height_field;
    static JTextField button_layout_frame_arc_width_field;
    static JLabel button_layout_frame_arc_height_label;
    static JLabel button_layout_frame_arc_width_label;
    static JLabel button_layout_frame_preview_label;
    static CustomJButton button_layout_frame_exit_btn;
    static CustomJButton button_layout_frame_reset_btn;
    static CustomJButton button_layout_frame_arc_height_dec_btn;
    static CustomJButton button_layout_frame_arc_height_inc_btn;
    static CustomJButton button_layout_frame_arc_width_dec_btn;
    static CustomJButton button_layout_frame_arc_width_inc_btn;
    static CustomJButton button_layout_frame_preview_btn;


    /**
     * Load previous user settings and deploy main menu to the user
     */
    public Calculator()
    {
        //Loads saved settings
        load_save_setting();

        //Fire up main menu
        MainMenu();
    }//Calculator()


    /**
     * Main menu of the calculator
     */
    protected void MainMenu()
    {
        //Create a new frame and set the image icon
        main_frame = new JFrame("Main Menu"); 
        main_frame.setIconImage(new ImageIcon("Images/pratik-patel.jpg").getImage());

        //Create a meny bar for the main frame, and set the layout of the main frame
        main_frame_bar = new JMenuBar();
        main_frame.setLayout(new GridBagLayout());

        //Initialize menu and its items along with their color and font
        main_frame_sett_menu = new JMenu("Settings");
        main_frame_app_menu = new JMenu("Application");
        main_frame_change_color_frame_item = new JMenuItem("Change Color");
        main_frame_output_precision_menu_item =  new JMenuItem("Decimal Precision");
        main_frame_button_layout_menu_item =  new JMenuItem("Button Layout");
        main_frame_font_style_menu_item = new JMenuItem("Fonts");
        main_frame_app_restart_menu_item = new JMenuItem("Restart");
        main_frame_app_redeploy_menu_item = new JMenuItem("Re-deploy");
        main_frame_app_shut_down_menu_item = new JMenuItem("Quit");

        set_main_frame_bar_color();
        set_main_frame_bar_font();

        //Add menu items to menu
        main_frame_sett_menu.add(main_frame_change_color_frame_item); 
        main_frame_sett_menu.add(main_frame_font_style_menu_item); 
        main_frame_sett_menu.add(main_frame_output_precision_menu_item); 
        main_frame_sett_menu.add(main_frame_button_layout_menu_item); 
        main_frame_app_menu.add(main_frame_app_restart_menu_item);
        main_frame_app_menu.add(main_frame_app_redeploy_menu_item);
        main_frame_app_menu.add(main_frame_app_shut_down_menu_item);

        main_frame_app_menu.addChangeListener(new ChangeListener() { public void stateChanged(ChangeEvent e) { main_frame_exit_btn.repaint(); } });

        //Add menu to menu bar and add menu bar to main frame
        main_frame_bar.add(main_frame_sett_menu);
        main_frame_bar.add(main_frame_app_menu);
        main_frame.setJMenuBar(main_frame_bar);

        //Add appropriate action to each menu iteam
        main_frame_change_color_frame_item.addActionListener(new ActionListener(){ @Override public void actionPerformed(ActionEvent e) { ChooseColor(); } });
        main_frame_font_style_menu_item.addActionListener(new ActionListener(){ @Override public void actionPerformed(ActionEvent e) { ChooseFont(); } });
        main_frame_output_precision_menu_item.addActionListener(new ActionListener(){ @Override public void actionPerformed(ActionEvent e) { ChoosePrecision(); } });
        main_frame_button_layout_menu_item.addActionListener(new ActionListener(){ @Override public void actionPerformed(ActionEvent e) { ChooseButtonLayout(); } });
        main_frame_app_restart_menu_item.addActionListener(new ActionListener(){ @Override public void actionPerformed(ActionEvent e) { main_frame_exit_btn.doClick(); new Calculator(); } });
        main_frame_app_redeploy_menu_item.addActionListener(new ActionListener()
        { 
            @Override public void actionPerformed(ActionEvent e) 
            { 
                main_frame.dispose();
                MainMenu();
                refresh_color_frame();
                refresh_font_frame();
                refresh_precision_frame();
                refresh_button_layout_frame();
                refresh_cal_frame();
                refresh_matrix_frame();
                refresh_matrix_oper_frame();
            } 
        });
        main_frame_app_shut_down_menu_item.addActionListener(new ActionListener(){ @Override public void actionPerformed(ActionEvent e) { main_frame_exit_btn.doClick(); } });

        //Create buttons to put on the main menu
        main_frame_cal_btn = new CustomJButton("Calculator");
        main_frame_exit_btn = new CustomJButton("Exit");
        main_frame_credit_btn = new CustomJButton("Credits");
        main_frame_matrix_btn = new CustomJButton("Matrix");

        main_frame_cal_btn.setPreferredSize(new Dimension(main_frame_cal_btn.getPreferredSize().width+30, main_frame_cal_btn.getPreferredSize().height+4)); 
        main_frame_exit_btn.setPreferredSize(main_frame_cal_btn.getPreferredSize());
        main_frame_credit_btn.setPreferredSize(main_frame_cal_btn.getPreferredSize());
        main_frame_matrix_btn.setPreferredSize(main_frame_cal_btn.getPreferredSize());

        //Add action to all button on click
        main_frame_cal_btn.addActionListener(new ActionListener() { @Override public void actionPerformed(ActionEvent e) { SimpleCalculator(); } });
        main_frame_credit_btn.addActionListener(new ActionListener() { @Override public void actionPerformed(ActionEvent e) { Credits(); } });
        main_frame_matrix_btn.addActionListener(new ActionListener() { @Override public void actionPerformed(ActionEvent e) { Matrix(); } });
        main_frame_exit_btn.addActionListener(new ActionListener() 
        { 
            @Override 
            public void actionPerformed(ActionEvent e) 
            {
                //Try to dispose all the open frames in the background
                dispose_color_frame();
                dispose_matrix_frame();
                dispose_matrix_oper_frame();
                dispose_cal_frame(); 
                dispose_font_frame();
                dispose_precision_frame();

                //Dispose main frame
                dispose_main_frame();
            } 
        });

        //Set the button bg color and font
        set_main_frame_button_color();
        set_main_frame_system_font();        
        
        GridBagConstraints main_frame_constr = new GridBagConstraints();

        //Add button to main frame in appropriate places
        main_frame_constr.insets = new Insets(8,10,5,5); 
        main_frame.add(main_frame_cal_btn, main_frame_constr); 
        main_frame_constr.gridx = 1; 
        main_frame_constr.insets = new Insets(8,5,5,10); 
        main_frame.add(main_frame_matrix_btn, main_frame_constr);
        main_frame_constr.gridy = 1; 
        main_frame_constr.insets = new Insets(5,5,8,10); 
        main_frame.add(main_frame_credit_btn, main_frame_constr);
        main_frame_constr.gridx = 0; 
        main_frame_constr.insets = new Insets(5,10,8,5); 
        main_frame.add(main_frame_exit_btn, main_frame_constr); 

        //Set bg color of main frame
        set_main_frame_color();

        //Deploy the main frame to the user
        main_frame.pack();
        main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main_frame.setLocationRelativeTo(null);
        main_frame.setResizable(false);
        main_frame.setVisible(true);
    } //MainMenu()

    /**
     * Disposes the main menu and sets the frame to null
     */
    protected void dispose_main_frame() { if(main_frame != null) { main_frame.dispose(); main_frame = null; } }

    /**
     * Repaints all the main frame buttons
     */
    protected void main_frame_btn_repaint()
    {
        if(main_frame != null) 
        {
            main_frame_cal_btn.repaint();
            main_frame_exit_btn.repaint();
            main_frame_credit_btn.repaint();
            main_frame_matrix_btn.repaint();
        }
    }
    
    /**
     * Set the bg color of the main menu frame
     */
    protected void set_main_frame_color() { if(main_frame != null) { main_frame.getContentPane().setBackground(color_arr[0]); } }

    /**
     * Set the bg color of the buttons on the main menu frame
     */
    protected void set_main_frame_button_color()
    {
        if(main_frame != null) 
        {
            set_main_frame_button_bg_color();
            set_main_frame_button_hover_color();
            set_main_frame_button_press_color();
            set_main_frame_button_border_color();
            set_main_frame_button_font_color();
            set_main_frame_button_disabled_color();
            set_main_frame_button_layout();
        }
    }

    /**
     * Set the main frame button corner cut-out
     */
    protected void set_main_frame_button_layout()
    {
        if(main_frame != null) 
        {
            set_main_frame_button_arcHeight_layout();
            set_main_frame_button_arcWidth_layout();
        }
    }

    /**
     * Set the main frame button corner arc height
     */
    protected void set_main_frame_button_arcHeight_layout()
    {
        if(main_frame != null) 
        {
            main_frame_cal_btn.setArcHeight(btn_arc_height);
            main_frame_exit_btn.setArcHeight(main_frame_cal_btn.getArcHeight());
            main_frame_credit_btn.setArcHeight(main_frame_cal_btn.getArcHeight());
            main_frame_matrix_btn.setArcHeight(main_frame_cal_btn.getArcHeight());
        }
    }
    
    /**
     * Set the main frame button corner arc width
     */
    protected void set_main_frame_button_arcWidth_layout()
    {
        if(main_frame != null) 
        {
            main_frame_cal_btn.setArcWidth(btn_arc_width);
            main_frame_exit_btn.setArcWidth(main_frame_cal_btn.getArcWidth());
            main_frame_credit_btn.setArcWidth(main_frame_cal_btn.getArcWidth());
            main_frame_matrix_btn.setArcWidth(main_frame_cal_btn.getArcWidth());
        }
    }

    /**
     * Sets the button bg color in main menu
     */
    protected void set_main_frame_button_bg_color()
    {
        if(main_frame != null) 
        {
            //If button color is default, then do not set the button background
            if(color_arr[7].getRGB() != default_button_color_rgb) { main_frame_cal_btn.setBackground(color_arr[7]); }
            else { main_frame_cal_btn.setBackground(default_color_arr[14]); }

            main_frame_exit_btn.setBackground(main_frame_cal_btn.getBackground());
            main_frame_credit_btn.setBackground(main_frame_cal_btn.getBackground()); 
            main_frame_matrix_btn.setBackground(main_frame_cal_btn.getBackground());
        }
    }

    /**
     * Sets the button hover color in main menu
     */
    protected void set_main_frame_button_hover_color()
    {   
        if(main_frame != null) 
        {
            main_frame_cal_btn.setHoverBackgroundColor(color_arr[8]);
            main_frame_exit_btn.setHoverBackgroundColor(main_frame_cal_btn.getHoverBackgroundColor());
            main_frame_credit_btn.setHoverBackgroundColor(main_frame_cal_btn.getHoverBackgroundColor()); 
            main_frame_matrix_btn.setHoverBackgroundColor(main_frame_cal_btn.getHoverBackgroundColor());
        }
    }

    /**
     * Sets the button press color in main menu
     */
    protected void set_main_frame_button_press_color()
    {   
        if(main_frame != null) 
        {
            main_frame_cal_btn.setPressedBackgroundColor(color_arr[9]);
            main_frame_exit_btn.setPressedBackgroundColor(main_frame_cal_btn.getPressedBackgroundColor());
            main_frame_credit_btn.setPressedBackgroundColor(main_frame_cal_btn.getPressedBackgroundColor()); 
            main_frame_matrix_btn.setPressedBackgroundColor(main_frame_cal_btn.getPressedBackgroundColor());
        }
    }

    /**
     * Sets the button border color in main menu
     */
    protected void set_main_frame_button_border_color()
    {   
        if(main_frame != null) 
        {
            main_frame_cal_btn.setBorderColor(color_arr[10]);
            main_frame_exit_btn.setBorderColor(main_frame_cal_btn.getBorderColor());
            main_frame_credit_btn.setBorderColor(main_frame_cal_btn.getBorderColor()); 
            main_frame_matrix_btn.setBorderColor(main_frame_cal_btn.getBorderColor());
        }
    }

    /**
     * Sets the button font color in main menu
     */
    protected void set_main_frame_button_font_color()
    {   
        if(main_frame != null) 
        {
            main_frame_cal_btn.setFontColor(color_arr[11]);
            main_frame_exit_btn.setFontColor(main_frame_cal_btn.getFontColor());
            main_frame_credit_btn.setFontColor(main_frame_cal_btn.getFontColor());
            main_frame_matrix_btn.setFontColor(main_frame_cal_btn.getFontColor());
        }
    }

    /**
     * Sets the button disabled color in main menu
     */
    protected void set_main_frame_button_disabled_color()
    {   
        if(main_frame != null) 
        {
            main_frame_cal_btn.setDisabledBackgroundColor(color_arr[12]);
            main_frame_exit_btn.setDisabledBackgroundColor(main_frame_cal_btn.getDisabledBackgroundColor());
            main_frame_credit_btn.setDisabledBackgroundColor(main_frame_cal_btn.getDisabledBackgroundColor());
            main_frame_matrix_btn.setDisabledBackgroundColor(main_frame_cal_btn.getDisabledBackgroundColor());
        }
    }

    /**
     * Repaints all the buttons in main menu
     */
    protected void main_frame_button_repaint()
    {   
        if(main_frame != null) 
        {
            main_frame_cal_btn.repaint();
            main_frame_exit_btn.repaint();
            main_frame_credit_btn.repaint(); 
            main_frame_matrix_btn.repaint();
        }
    }

    /**
     * Set the bg color of the menu bar as well as menu items on the main menu frame
     */
    protected void set_main_frame_bar_color()
    {   
        if(main_frame != null) 
        {
            main_frame_change_color_frame_item.setBackground(color_arr[1]);
            main_frame_sett_menu.setBackground(main_frame_change_color_frame_item.getBackground());
            main_frame_output_precision_menu_item.setBackground(main_frame_change_color_frame_item.getBackground());
            main_frame_button_layout_menu_item.setBackground(main_frame_change_color_frame_item.getBackground());
            main_frame_font_style_menu_item.setBackground(main_frame_change_color_frame_item.getBackground());
            main_frame_bar.setBackground(main_frame_change_color_frame_item.getBackground());
            main_frame_app_menu.setBackground(main_frame_change_color_frame_item.getBackground());
            main_frame_app_restart_menu_item.setBackground(main_frame_change_color_frame_item.getBackground());
            main_frame_app_redeploy_menu_item.setBackground(main_frame_change_color_frame_item.getBackground());
            main_frame_app_shut_down_menu_item.setBackground(main_frame_change_color_frame_item.getBackground());
        }
    }

    /**
     * Set font of all the buttons on the main menu frame
     */
    protected void set_main_frame_system_font()
    {   
        if(main_frame != null) 
        {
            main_frame_cal_btn.setFont(font_arr[0]); 
            main_frame_exit_btn.setFont(main_frame_cal_btn.getFont());
            main_frame_credit_btn.setFont(main_frame_cal_btn.getFont()); 
            main_frame_matrix_btn.setFont(main_frame_cal_btn.getFont());
        }
    }

    /**
     * Set the font of the menu bar as well as menu items on the main frame
     */
    protected void set_main_frame_bar_font()
    {   
        if(main_frame != null) 
        {
            main_frame_sett_menu.setFont(font_arr[1]);
            main_frame_change_color_frame_item.setFont(main_frame_sett_menu.getFont());
            main_frame_output_precision_menu_item.setFont(main_frame_sett_menu.getFont());
            main_frame_button_layout_menu_item.setFont(main_frame_sett_menu.getFont());
            main_frame_font_style_menu_item.setFont(main_frame_sett_menu.getFont());
            main_frame_app_menu.setFont(main_frame_sett_menu.getFont());
            main_frame_app_restart_menu_item.setFont(main_frame_sett_menu.getFont());
            main_frame_app_redeploy_menu_item.setFont(main_frame_sett_menu.getFont());
            main_frame_app_shut_down_menu_item.setFont(main_frame_sett_menu.getFont());
        }
    }


    /**
     * Change Button Look within the system
     */
    protected void ChooseButtonLayout()
    {
        //Dispose the precision_menu if it is open in the background
        dispose_button_layout_frame();

        //Create a new precision menu frame
        button_layout_frame = new JFrame("Choose Button Layout"); 
        button_layout_frame.setIconImage(main_frame.getIconImage()); 
        button_layout_frame.setLayout(new GridBagLayout());

        //Create new buttons to increase and decrease the precision
        button_layout_frame_arc_height_dec_btn = new CustomJButton("<<<<"); 
        button_layout_frame_arc_height_inc_btn = new CustomJButton(">>>>");
        button_layout_frame_arc_width_dec_btn = new CustomJButton("<<<<"); 
        button_layout_frame_arc_width_inc_btn = new CustomJButton(">>>>");
        button_layout_frame_preview_btn = new CustomJButton("PREVIEW");

        //Create a text field to show the user selected precision
        button_layout_frame_arc_height_field = new JTextField(); 
        button_layout_frame_arc_width_field = new JTextField(); 
        button_layout_frame_arc_height_field.setText(Integer.toString(btn_arc_height));
        button_layout_frame_arc_width_field.setText(Integer.toString(btn_arc_width));
        button_layout_frame_arc_height_field.setEditable(false); 
        button_layout_frame_arc_width_field.setEditable(false); 
        button_layout_frame_arc_height_field.setHorizontalAlignment(default_font_alignment);
        button_layout_frame_arc_width_field.setHorizontalAlignment(default_font_alignment);
        button_layout_frame_arc_height_field.setPreferredSize(new Dimension(button_layout_frame_arc_height_field.getPreferredSize().width + 60, button_layout_frame_arc_height_field.getPreferredSize().height));
        button_layout_frame_arc_width_field.setPreferredSize(button_layout_frame_arc_height_field.getPreferredSize());

        set_button_layout_frame_output_color();
        set_button_layout_frame_out_font(); 

        //Create a label to put before the precision text field
        button_layout_frame_arc_height_label = new JLabel("Button Arc Height:");
        button_layout_frame_arc_width_label = new JLabel("Button Arc Width:");
        button_layout_frame_preview_label = new JLabel("Button Preview:");
        button_layout_frame_arc_height_label.setHorizontalAlignment(default_font_alignment);
        button_layout_frame_arc_width_label.setHorizontalAlignment(default_font_alignment);
        button_layout_frame_preview_label.setHorizontalAlignment(default_font_alignment);
        set_button_layout_frame_label_color();

        //Create reset and exit button to put on the choose percision frame
        button_layout_frame_reset_btn = new CustomJButton("Reset"); 
        button_layout_frame_exit_btn = new CustomJButton("Save & Exit");
        
        set_button_layout_frame_btn_color();
        set_button_layout_frame_system_font();
        
        //If the user selects the max percision, then disable the increase button
        if(btn_arc_height >= max_btn_arc_height) { button_layout_frame_arc_height_inc_btn.setEnabled(false); }
        //If the user selects the min percision, then disable the decrease button
        else if(btn_arc_height <= min_btn_arc_height) { button_layout_frame_arc_height_dec_btn.setEnabled(false); }

        //If the user selects the max percision, then disable the increase button
        if(btn_arc_width >= max_btn_arc_width) { button_layout_frame_arc_width_inc_btn.setEnabled(false); }
        //If the user selects the min percision, then disable the decrease button
        else if(btn_arc_width <= min_btn_arc_width) { button_layout_frame_arc_width_dec_btn.setEnabled(false); }

        //Create GridBagConstraints object to format the menu
        GridBagConstraints button_layout_frame_constr = new GridBagConstraints();
        button_layout_frame_constr.fill = GridBagConstraints.HORIZONTAL;

        //Add objects to percision menu frame
        button_layout_frame_constr.insets = new Insets(5,5,10,5); 
        button_layout_frame_constr.gridwidth = 2; 
        button_layout_frame.add(button_layout_frame_preview_label, button_layout_frame_constr); 
        button_layout_frame_constr.gridwidth = 5; 
        button_layout_frame_constr.gridx = 2; 
        button_layout_frame.add(button_layout_frame_preview_btn, button_layout_frame_constr); 
        button_layout_frame_constr.insets = new Insets(5,5,5,5); 
        button_layout_frame_constr.gridy = 1; 
        button_layout_frame_constr.gridx = 0; 
        button_layout_frame_constr.gridwidth = 2; 
        button_layout_frame.add(button_layout_frame_arc_height_label, button_layout_frame_constr); 
        button_layout_frame_constr.gridwidth = 1; 
        button_layout_frame_constr.gridx = 2; 
        button_layout_frame.add(button_layout_frame_arc_height_dec_btn, button_layout_frame_constr); 
        button_layout_frame_constr.gridx = 3; 
        button_layout_frame_constr.gridwidth = 3; 
        button_layout_frame.add(button_layout_frame_arc_height_field, button_layout_frame_constr); 
        button_layout_frame_constr.gridx = 6; 
        button_layout_frame_constr.gridwidth = 1; 
        button_layout_frame.add(button_layout_frame_arc_height_inc_btn, button_layout_frame_constr); 
        button_layout_frame_constr.gridy = 2; 
        button_layout_frame_constr.gridx = 0; 
        button_layout_frame_constr.gridwidth = 2; 
        button_layout_frame.add(button_layout_frame_arc_width_label, button_layout_frame_constr); 
        button_layout_frame_constr.gridwidth = 1; 
        button_layout_frame_constr.gridx = 2; 
        button_layout_frame.add(button_layout_frame_arc_width_dec_btn, button_layout_frame_constr); 
        button_layout_frame_constr.gridx = 3; 
        button_layout_frame_constr.gridwidth = 3; 
        button_layout_frame.add(button_layout_frame_arc_width_field, button_layout_frame_constr); 
        button_layout_frame_constr.gridx = 6; 
        button_layout_frame_constr.gridwidth = 1; 
        button_layout_frame.add(button_layout_frame_arc_width_inc_btn, button_layout_frame_constr); 
        button_layout_frame_constr.gridx = 0; 
        button_layout_frame_constr.gridy = 3; 
        button_layout_frame.add(button_layout_frame_reset_btn, button_layout_frame_constr);
        button_layout_frame_constr.gridx = 6; 
        button_layout_frame_constr.gridwidth = 2; 
        button_layout_frame.add(button_layout_frame_exit_btn, button_layout_frame_constr);

        //Save user settings of precision and dispose percision frame
        button_layout_frame_exit_btn.addActionListener(new ActionListener() 
        { 
            @Override 
            public void actionPerformed(ActionEvent e) 
            { 
                dispose_button_layout_frame();
                int temp_btn_arc_height = Integer.parseInt(button_layout_frame_arc_height_field.getText());
                int temp_btn_arc_width = Integer.parseInt(button_layout_frame_arc_width_field.getText());

                if((btn_arc_height != temp_btn_arc_height || btn_arc_width != temp_btn_arc_width) && 
                   JOptionPane.showOptionDialog(null, "Do you want to re-deploy the application for better results?", "Re-launch Prompt", 
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null) == JOptionPane.YES_OPTION)
                {
                    main_frame_app_redeploy_menu_item.doClick();
                }

                btn_arc_height = temp_btn_arc_height;
                btn_arc_width = temp_btn_arc_width;
                create_save_setting_file(); 
            } 
        });

        //Reset and save settings of precision
        button_layout_frame_reset_btn.addActionListener(new ActionListener() 
        { 
            @Override 
            public void actionPerformed(ActionEvent e) 
            {
                restore_btn_layout_setting();
                int temp_btn_arc_height = Integer.parseInt(button_layout_frame_arc_height_field.getText());
                int temp_btn_arc_width = Integer.parseInt(button_layout_frame_arc_width_field.getText());
                button_layout_frame_arc_height_field.setText(Integer.toString(btn_arc_height));
                button_layout_frame_arc_width_field.setText(Integer.toString(btn_arc_width));

                if((btn_arc_height != temp_btn_arc_height || btn_arc_width != temp_btn_arc_width) && 
                   JOptionPane.showOptionDialog(null, "Do you want to re-deploy the application for better results?", "Re-launch Prompt", 
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null) == JOptionPane.YES_OPTION) { main_frame_app_redeploy_menu_item.doClick(); }
                
                create_save_setting_file(); 
            } 
        });

        //Allows user to decrease precision of the output
        button_layout_frame_arc_height_dec_btn.addActionListener(new ActionListener() 
        { 
            @Override 
            public void actionPerformed(ActionEvent e) 
            { 
                if(button_layout_frame_arc_height_dec_btn.isEnabled())
                {
                    button_layout_frame_arc_height_field.setText(Integer.toString(Integer.parseInt(button_layout_frame_arc_height_field.getText())-1));
                    button_layout_frame_preview_btn.setArcHeight(Integer.parseInt(button_layout_frame_arc_height_field.getText()));
                    button_layout_frame_preview_btn.repaint();
                    //If current precision is equal to or less than min precision, then disable the decrease precision button
                    if(button_layout_frame_preview_btn.getArcHeight() <= min_btn_arc_height) { button_layout_frame_arc_height_dec_btn.setEnabled(false); }
                    //If increase percision button is disable and current percision is less than max percision allowed, then enable the increase precision button
                    if(!button_layout_frame_arc_height_inc_btn.isEnabled() && button_layout_frame_preview_btn.getArcHeight() < max_btn_arc_height) { button_layout_frame_arc_height_inc_btn.setEnabled(true); }
                }
            } 
        });

        //Allows user to increase precision of the output
        button_layout_frame_arc_height_inc_btn.addActionListener(new ActionListener() 
        { 
            @Override 
            public void actionPerformed(ActionEvent e) 
            { 
                if(button_layout_frame_arc_height_inc_btn.isEnabled())
                {
                    button_layout_frame_arc_height_field.setText(Integer.toString(Integer.parseInt(button_layout_frame_arc_height_field.getText())+1));
                    button_layout_frame_preview_btn.setArcHeight(Integer.parseInt(button_layout_frame_arc_height_field.getText()));
                    button_layout_frame_preview_btn.repaint();
                    //If current precision is equal to or greater than max precision, then disable the increase precision button
                    if(button_layout_frame_preview_btn.getArcHeight() >= max_btn_arc_height) { button_layout_frame_arc_height_inc_btn.setEnabled(false); }
                    //If decrease percision button is disable and current percision is greater than min percision allowed, then enable the decrease precision button
                    if(!button_layout_frame_arc_height_dec_btn.isEnabled() && button_layout_frame_preview_btn.getArcHeight() > min_btn_arc_height) { button_layout_frame_arc_height_dec_btn.setEnabled(true); }
                }
            } 
        });

        //Allows user to decrease precision of the output
        button_layout_frame_arc_width_dec_btn.addActionListener(new ActionListener() 
        { 
            @Override 
            public void actionPerformed(ActionEvent e) 
            { 
                if(button_layout_frame_arc_width_dec_btn.isEnabled())
                {
                    button_layout_frame_arc_width_field.setText(Integer.toString(Integer.parseInt(button_layout_frame_arc_width_field.getText())-1));
                    button_layout_frame_preview_btn.setArcWidth(Integer.parseInt(button_layout_frame_arc_width_field.getText()));
                    button_layout_frame_preview_btn.repaint();
                    //If current precision is equal to or less than min precision, then disable the decrease precision button
                    if(button_layout_frame_preview_btn.getArcWidth() <= min_btn_arc_width) { button_layout_frame_arc_width_dec_btn.setEnabled(false); }
                    //If increase percision button is disable and current percision is less than max percision allowed, then enable the increase precision button
                    if(!button_layout_frame_arc_width_inc_btn.isEnabled() && button_layout_frame_preview_btn.getArcWidth() < max_btn_arc_width) { button_layout_frame_arc_width_inc_btn.setEnabled(true); }
                }
            } 
        });

        //Allows user to increase precision of the output
        button_layout_frame_arc_width_inc_btn.addActionListener(new ActionListener() 
        { 
            @Override 
            public void actionPerformed(ActionEvent e) 
            { 
                if(button_layout_frame_arc_width_inc_btn.isEnabled())
                {
                    button_layout_frame_arc_width_field.setText(Integer.toString(Integer.parseInt(button_layout_frame_arc_width_field.getText())+1));
                    button_layout_frame_preview_btn.setArcWidth(Integer.parseInt(button_layout_frame_arc_width_field.getText()));
                    button_layout_frame_preview_btn.repaint();
                    //If current precision is equal to or greater than max precision, then disable the increase precision button
                    if(button_layout_frame_preview_btn.getArcWidth() >= max_btn_arc_width) { button_layout_frame_arc_width_inc_btn.setEnabled(false); }
                    //If decrease percision button is disable and current percision is greater than min percision allowed, then enable the decrease precision button
                    if(!button_layout_frame_arc_width_dec_btn.isEnabled() && button_layout_frame_preview_btn.getArcWidth() > min_btn_arc_width) { button_layout_frame_arc_width_dec_btn.setEnabled(true); }
                }
            } 
        });

        //Deploy the choose precision frame to the user
        button_layout_frame.pack();
        set_button_layout_frame_color();
        button_layout_frame.setLocationRelativeTo(null);
        button_layout_frame.setVisible(true);
        button_layout_frame.setResizable(false);
        button_layout_frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        button_layout_frame.addWindowListener(new WindowListener()
        {
            @Override public void windowClosing(WindowEvent e) { dispose_button_layout_frame(); }
            @Override public void windowOpened(WindowEvent e) {}
            @Override public void windowClosed(WindowEvent e) {}
            @Override public void windowIconified(WindowEvent e) {}
            @Override public void windowDeiconified(WindowEvent e) {}
            @Override public void windowActivated(WindowEvent e) {}
            @Override public void windowDeactivated(WindowEvent e) {}
        });
    }//ChoosePrecision()

    /**
     * Refresh precision frame while retaining user changes
     */
    protected void refresh_button_layout_frame()
    {
        if(button_layout_frame != null)
        {
            String button_layout_frame_arc_height_field_text = button_layout_frame_arc_height_field.getText();
            String button_layout_frame_arc_width_field_text = button_layout_frame_arc_width_field.getText();
            boolean button_layout_frame_arc_height_dec_btn_enable = button_layout_frame_arc_height_dec_btn.isEnabled();
            boolean button_layout_frame_arc_height_inc_btn_enable = button_layout_frame_arc_height_inc_btn.isEnabled();
            boolean button_layout_frame_arc_width_dec_btn_enable = button_layout_frame_arc_width_dec_btn.isEnabled();
            boolean button_layout_frame_arc_width_inc_btn_enable = button_layout_frame_arc_width_inc_btn.isEnabled();

            ChooseButtonLayout();

            button_layout_frame_arc_height_field.setText(button_layout_frame_arc_height_field_text);
            button_layout_frame_arc_width_field.setText(button_layout_frame_arc_width_field_text);
            button_layout_frame_arc_height_dec_btn.setEnabled(button_layout_frame_arc_height_dec_btn_enable);
            button_layout_frame_arc_height_inc_btn.setEnabled(button_layout_frame_arc_height_inc_btn_enable);
            button_layout_frame_arc_width_dec_btn.setEnabled(button_layout_frame_arc_width_dec_btn_enable);
            button_layout_frame_arc_width_inc_btn.setEnabled(button_layout_frame_arc_width_inc_btn_enable);
        }
    }

    /**
     * Sets the precision menu label color for better readability
     */
    protected void set_button_layout_frame_label_color()
    {
        if(button_layout_frame != null)
        {
            try { button_layout_frame_arc_height_label.setForeground(button_layout_frame_arc_height_dec_btn.getInvertedColor(color_arr[17], 100, 155, false)); } 
            catch(Exception ex) { return; }
            button_layout_frame_arc_width_label.setForeground(button_layout_frame_arc_height_label.getForeground());
            button_layout_frame_preview_label.setForeground(button_layout_frame_arc_height_label.getForeground());
        }
    }

    /**
     * Set the bg color of the precision menu frame
     */
    protected void set_button_layout_frame_color() 
    { 
        if(button_layout_frame != null) { button_layout_frame.getContentPane().setBackground(color_arr[17]); } 
    }

    /**
     * Set set font of the button and labels on the precision menu
     */
    protected void set_button_layout_frame_system_font()
    {
        if(button_layout_frame != null)
        {
            button_layout_frame_arc_height_dec_btn.setFont(font_arr[0]);  
            button_layout_frame_arc_height_inc_btn.setFont(button_layout_frame_arc_height_dec_btn.getFont());
            button_layout_frame_arc_width_dec_btn.setFont(button_layout_frame_arc_height_dec_btn.getFont());
            button_layout_frame_arc_width_inc_btn.setFont(button_layout_frame_arc_height_dec_btn.getFont());
            button_layout_frame_reset_btn.setFont(button_layout_frame_arc_height_dec_btn.getFont()); 
            button_layout_frame_exit_btn.setFont(button_layout_frame_arc_height_dec_btn.getFont()); 
            button_layout_frame_preview_btn.setFont(button_layout_frame_arc_height_dec_btn.getFont()); 
            button_layout_frame_arc_height_label.setFont(button_layout_frame_arc_height_dec_btn.getFont()); 
            button_layout_frame_arc_width_label.setFont(button_layout_frame_arc_height_dec_btn.getFont()); 
            button_layout_frame_preview_label.setFont(button_layout_frame_arc_height_dec_btn.getFont());
        }
    }
    
    /**
     * set bg color of the buttons on the precision menu
     */
    protected void set_button_layout_frame_btn_color()
    {
        if(button_layout_frame != null)
        {
            set_button_layout_frame_btn_bg_color();
            set_button_layout_frame_btn_hover_color();
            set_button_layout_frame_btn_press_color();
            set_button_layout_frame_btn_border_color();
            set_button_layout_frame_btn_font_color();
            set_button_layout_frame_btn_disabled_color();
            set_button_layout_frame_btn_layout();
        }
    }

    /**
     * Set the precision frame button corner cut-out
     */
    protected void set_button_layout_frame_btn_layout()
    {
        if(button_layout_frame != null) 
        {
            set_button_layout_frame_btn_arcHeight_layout();
            set_button_layout_frame_btn_arcWidth_layout();
        }
    }

    /**
     * Set the precision frame button corner arc height
     */
    protected void set_button_layout_frame_btn_arcHeight_layout()
    {
        if(button_layout_frame != null) 
        {
            button_layout_frame_arc_height_dec_btn.setArcHeight(btn_arc_height);
            button_layout_frame_arc_height_inc_btn.setArcHeight(button_layout_frame_arc_height_dec_btn.getArcHeight());
            button_layout_frame_arc_width_dec_btn.setArcHeight(button_layout_frame_arc_height_dec_btn.getArcHeight());
            button_layout_frame_arc_width_inc_btn.setArcHeight(button_layout_frame_arc_height_dec_btn.getArcHeight());
            button_layout_frame_reset_btn.setArcHeight(button_layout_frame_arc_height_dec_btn.getArcHeight());
            button_layout_frame_exit_btn.setArcHeight(button_layout_frame_arc_height_dec_btn.getArcHeight());
            button_layout_frame_preview_btn.setArcHeight(button_layout_frame_arc_height_dec_btn.getArcHeight());
        }
    }
    
    /**
     * Set the precision frame button corner arc width
     */
    protected void set_button_layout_frame_btn_arcWidth_layout()
    {
        if(button_layout_frame != null) 
        {
            button_layout_frame_arc_height_dec_btn.setArcWidth(btn_arc_width);
            button_layout_frame_arc_height_inc_btn.setArcWidth(button_layout_frame_arc_height_dec_btn.getArcWidth());
            button_layout_frame_arc_width_dec_btn.setArcWidth(button_layout_frame_arc_height_dec_btn.getArcWidth());
            button_layout_frame_arc_width_inc_btn.setArcWidth(button_layout_frame_arc_height_dec_btn.getArcWidth());
            button_layout_frame_reset_btn.setArcWidth(button_layout_frame_arc_height_dec_btn.getArcWidth());
            button_layout_frame_exit_btn.setArcWidth(button_layout_frame_arc_height_dec_btn.getArcWidth());
            button_layout_frame_preview_btn.setArcWidth(button_layout_frame_arc_height_dec_btn.getArcWidth());
        }
    }

    /**
     * Sets the button bg color in precision menu
     */
    protected void set_button_layout_frame_btn_bg_color()
    {
        if(button_layout_frame != null)
        {
            //If the button have default color, then do not set the background of the buttons
            if(color_arr[7].getRGB() != default_button_color_rgb) { button_layout_frame_arc_height_dec_btn.setBackground(color_arr[7]); }
            else { button_layout_frame_arc_height_dec_btn.setBackground(default_color_arr[14]); }
            
            button_layout_frame_arc_height_inc_btn.setBackground(button_layout_frame_arc_height_dec_btn.getBackground()); 
            button_layout_frame_arc_width_dec_btn.setBackground(button_layout_frame_arc_height_dec_btn.getBackground()); 
            button_layout_frame_arc_width_inc_btn.setBackground(button_layout_frame_arc_height_dec_btn.getBackground()); 
            button_layout_frame_reset_btn.setBackground(button_layout_frame_arc_height_dec_btn.getBackground()); 
            button_layout_frame_exit_btn.setBackground(button_layout_frame_arc_height_dec_btn.getBackground()); 
            button_layout_frame_preview_btn.setBackground(button_layout_frame_arc_height_dec_btn.getBackground()); 
        }
    }
    
    /**
     * Sets the button hover color in precision menu
     */
    protected void set_button_layout_frame_btn_hover_color()
    {
        if(button_layout_frame != null)
        {
            button_layout_frame_arc_height_dec_btn.setHoverBackgroundColor(color_arr[8]);
            button_layout_frame_arc_height_inc_btn.setHoverBackgroundColor(button_layout_frame_arc_height_dec_btn.getHoverBackgroundColor());
            button_layout_frame_arc_width_dec_btn.setHoverBackgroundColor(button_layout_frame_arc_height_dec_btn.getHoverBackgroundColor());
            button_layout_frame_arc_width_inc_btn.setHoverBackgroundColor(button_layout_frame_arc_height_dec_btn.getHoverBackgroundColor());
            button_layout_frame_reset_btn.setHoverBackgroundColor(button_layout_frame_arc_height_dec_btn.getHoverBackgroundColor()); 
            button_layout_frame_exit_btn.setHoverBackgroundColor(button_layout_frame_arc_height_dec_btn.getHoverBackgroundColor());
            button_layout_frame_preview_btn.setHoverBackgroundColor(button_layout_frame_arc_height_dec_btn.getHoverBackgroundColor());
        }
    }
    
    /**
     * Sets the button press color in precision menu
     */
    protected void set_button_layout_frame_btn_press_color()
    {
        if(button_layout_frame != null)
        {
            button_layout_frame_arc_height_dec_btn.setPressedBackgroundColor(color_arr[9]);
            button_layout_frame_arc_height_inc_btn.setPressedBackgroundColor(button_layout_frame_arc_height_dec_btn.getPressedBackgroundColor());
            button_layout_frame_arc_width_dec_btn.setPressedBackgroundColor(button_layout_frame_arc_height_dec_btn.getPressedBackgroundColor());
            button_layout_frame_arc_width_inc_btn.setPressedBackgroundColor(button_layout_frame_arc_height_dec_btn.getPressedBackgroundColor());
            button_layout_frame_reset_btn.setPressedBackgroundColor(button_layout_frame_arc_height_dec_btn.getPressedBackgroundColor()); 
            button_layout_frame_exit_btn.setPressedBackgroundColor(button_layout_frame_arc_height_dec_btn.getPressedBackgroundColor());
            button_layout_frame_preview_btn.setPressedBackgroundColor(button_layout_frame_arc_height_dec_btn.getPressedBackgroundColor());
        }
    }
    
    /**
     * Sets the button border color in precision menu
     */
    protected void set_button_layout_frame_btn_border_color()
    {
        if(button_layout_frame != null)
        {
            button_layout_frame_arc_height_dec_btn.setBorderColor(color_arr[10]);
            button_layout_frame_arc_height_inc_btn.setBorderColor(button_layout_frame_arc_height_dec_btn.getBorderColor());
            button_layout_frame_arc_width_dec_btn.setBorderColor(button_layout_frame_arc_height_dec_btn.getBorderColor());
            button_layout_frame_arc_width_inc_btn.setBorderColor(button_layout_frame_arc_height_dec_btn.getBorderColor());
            button_layout_frame_reset_btn.setBorderColor(button_layout_frame_arc_height_dec_btn.getBorderColor()); 
            button_layout_frame_exit_btn.setBorderColor(button_layout_frame_arc_height_dec_btn.getBorderColor());
            button_layout_frame_preview_btn.setBorderColor(button_layout_frame_arc_height_dec_btn.getBorderColor());
        }
    }
    
    /**
     * Sets the button font color in precision menu
     */
    protected void set_button_layout_frame_btn_font_color()
    {
        if(button_layout_frame != null)
        {
            button_layout_frame_arc_height_dec_btn.setFontColor(color_arr[11]);
            button_layout_frame_arc_height_inc_btn.setFontColor(button_layout_frame_arc_height_dec_btn.getFontColor());
            button_layout_frame_arc_width_dec_btn.setFontColor(button_layout_frame_arc_height_dec_btn.getFontColor());
            button_layout_frame_arc_width_inc_btn.setFontColor(button_layout_frame_arc_height_dec_btn.getFontColor());
            button_layout_frame_reset_btn.setFontColor(button_layout_frame_arc_height_dec_btn.getFontColor()); 
            button_layout_frame_exit_btn.setFontColor(button_layout_frame_arc_height_dec_btn.getFontColor());
            button_layout_frame_preview_btn.setFontColor(button_layout_frame_arc_height_dec_btn.getFontColor());
        }
    }

    /**
     * Sets the button disabled color in precision menu
     */
    protected void set_button_layout_frame_btn_disabled_color()
    {
        if(button_layout_frame != null)
        {
            button_layout_frame_arc_height_dec_btn.setDisabledBackgroundColor(color_arr[12]);
            button_layout_frame_arc_height_inc_btn.setDisabledBackgroundColor(button_layout_frame_arc_height_dec_btn.getDisabledBackgroundColor());
            button_layout_frame_arc_width_dec_btn.setDisabledBackgroundColor(button_layout_frame_arc_height_dec_btn.getDisabledBackgroundColor());
            button_layout_frame_arc_width_inc_btn.setDisabledBackgroundColor(button_layout_frame_arc_height_dec_btn.getDisabledBackgroundColor());
            button_layout_frame_reset_btn.setDisabledBackgroundColor(button_layout_frame_arc_height_dec_btn.getDisabledBackgroundColor()); 
            button_layout_frame_exit_btn.setDisabledBackgroundColor(button_layout_frame_arc_height_dec_btn.getDisabledBackgroundColor());
            button_layout_frame_preview_btn.setDisabledBackgroundColor(button_layout_frame_arc_height_dec_btn.getDisabledBackgroundColor());
        }
    }

    /**
     * Repaints all the buttons in precision menu
     */
    protected void button_layout_frame_btn_repaint()
    {
        if(button_layout_frame != null)
        {
            button_layout_frame_arc_height_dec_btn.repaint();
            button_layout_frame_arc_height_inc_btn.repaint();
            button_layout_frame_arc_width_dec_btn.repaint();
            button_layout_frame_arc_width_inc_btn.repaint();
            button_layout_frame_reset_btn.repaint();
            button_layout_frame_exit_btn.repaint();
            button_layout_frame_preview_btn.repaint();
        }
    }

    /**
     * Try to dispose the precision menu frame
     */
    protected void dispose_button_layout_frame() 
    { 
        if(button_layout_frame != null) 
        {
            button_layout_frame.dispose(); 
            button_layout_frame = null; 
        } 
    }

    /**
     * Set the bg color of the precision field on the precision menu
     */
    protected void set_button_layout_frame_output_color() 
    { 
        if(button_layout_frame != null) 
        { 
            button_layout_frame_arc_height_field.setBackground(color_arr[5]); 
            button_layout_frame_arc_width_field.setBackground(button_layout_frame_arc_height_field.getBackground()); 
        } 
    }

    /**
     * Set the precision field font on the precision menu
     */
    protected void set_button_layout_frame_out_font() 
    { 
        if(button_layout_frame != null) 
        {
            button_layout_frame_arc_height_field.setFont(font_arr[3]); 
            button_layout_frame_arc_width_field.setFont(button_layout_frame_arc_height_field.getFont()); 
        } 
    }


    /**
     * Change Output Precision within the system
     */
    protected void ChoosePrecision()
    {
        //Dispose the precision_menu if it is open in the background
        dispose_precision_frame();

        //Create a new precision menu frame
        precision_frame = new JFrame("Choose Precision"); 
        precision_frame.setIconImage(main_frame.getIconImage()); 
        precision_frame.setLayout(new GridBagLayout());

        //Create new buttons to increase and decrease the precision
        precision_frame_dec_btn = new CustomJButton("<<<<"); 
        precision_frame_inc_btn = new CustomJButton(">>>>");

        //Create a text field to show the user selected precision
        precision_frame_precision_field = new JTextField(df.format(0)); 
        precision_frame_precision_field.setEditable(false); 
        precision_frame_precision_field.setHorizontalAlignment(default_font_alignment);

        set_precision_frame_output_color();
        set_precision_frame_out_font(); 

        //Create a label to put before the precision text field
        precision_frame_precision_label = new JLabel("Output Precision:");
        precision_frame_precision_label.setHorizontalAlignment(default_font_alignment);
        set_precision_frame_label_color();

        //Create reset and exit button to put on the choose percision frame
        precision_frame_reset_btn = new CustomJButton("Reset"); 
        precision_frame_exit_btn = new CustomJButton("Save & Exit");
        precision_frame_reset_btn.setPreferredSize(new Dimension(precision_frame_reset_btn.getPreferredSize().width+18, precision_frame_inc_btn.getPreferredSize().height)); 
        precision_frame_exit_btn.setPreferredSize(new Dimension(precision_frame_exit_btn.getPreferredSize().width+37, precision_frame_reset_btn.getPreferredSize().height));
        set_precision_frame_btn_color();
        set_precision_frame_system_font();
        
        //If the user selects the max percision, then disable the increase button
        if(precision_frame_precision_field.getText().length() >= max_precision.length()) { precision_frame_inc_btn.setEnabled(false); }
        //If the user selects the min percision, then disable the decrease button
        else if(precision_frame_precision_field.getText().length() <= min_precision.length()) { precision_frame_dec_btn.setEnabled(false); }

        //Create GridBagConstraints object to format the menu
        GridBagConstraints precision_frame_constr = new GridBagConstraints();
        precision_frame_constr.fill = GridBagConstraints.HORIZONTAL;

        //Set the size of the button as well as the text field
        precision_frame_dec_btn.setPreferredSize(new Dimension(precision_frame_dec_btn.getPreferredSize().width+16, precision_frame_dec_btn.getPreferredSize().height-2)); 
        precision_frame_inc_btn.setPreferredSize(precision_frame_dec_btn.getPreferredSize());
        precision_frame_precision_field.setPreferredSize(new Dimension(100, precision_frame_precision_field.getPreferredSize().height+5)); 

        //Add objects to percision menu frame
        precision_frame_constr.insets = new Insets(5,5,5,5); 
        precision_frame_constr.gridwidth = 2; 
        precision_frame.add(precision_frame_precision_label, precision_frame_constr); 
        precision_frame_constr.gridwidth = 1; 
        precision_frame_constr.gridx = 2; 
        precision_frame.add(precision_frame_dec_btn, precision_frame_constr); 
        precision_frame_constr.gridx = 3; 
        precision_frame_constr.gridwidth = 3; 
        precision_frame.add(precision_frame_precision_field, precision_frame_constr); 
        precision_frame_constr.gridx = 6; 
        precision_frame_constr.gridwidth = 1; 
        precision_frame.add(precision_frame_inc_btn, precision_frame_constr); 
        precision_frame_constr.gridx = 0; 
        precision_frame_constr.gridy = 1; 
        precision_frame.add(precision_frame_reset_btn, precision_frame_constr);
        precision_frame_constr.gridx = 6; 
        precision_frame_constr.gridwidth = 2; 
        precision_frame.add(precision_frame_exit_btn, precision_frame_constr);

        //Save user settings of precision and dispose percision frame
        precision_frame_exit_btn.addActionListener(new ActionListener() 
        { 
            @Override 
            public void actionPerformed(ActionEvent e) 
            { 
                dispose_precision_frame();
                df = new DecimalFormat(precision_frame_precision_field.getText());
                create_save_setting_file(); 
            } 
        });

        //Reset and save settings of precision
        precision_frame_reset_btn.addActionListener(new ActionListener() 
        { 
            @Override 
            public void actionPerformed(ActionEvent e) 
            {
                precision_frame_precision_field.setText(default_df_precision_string);
                restore_decimal_precision_setting();
                create_save_setting_file(); 
            } 
        });

        //Allows user to decrease precision of the output
        precision_frame_dec_btn.addActionListener(new ActionListener() 
        { 
            @Override 
            public void actionPerformed(ActionEvent e) 
            { 
                if(precision_frame_dec_btn.isEnabled())
                {
                    precision_frame_precision_field.setText(precision_frame_precision_field.getText().substring(0, precision_frame_precision_field.getText().length()-1));
                    //If current precision is equal to or less than min precision, then disable the decrease precision button
                    if(precision_frame_precision_field.getText().length() <= min_precision.length()) { precision_frame_dec_btn.setEnabled(false); }
                    //If increase percision button is disable and current percision is less than max percision allowed, then enable the increase precision button
                    if(!precision_frame_inc_btn.isEnabled() && precision_frame_precision_field.getText().length() < max_precision.length()) { precision_frame_inc_btn.setEnabled(true); }
                }
            } 
        });

        //Allows user to increase precision of the output
        precision_frame_inc_btn.addActionListener(new ActionListener() 
        { 
            @Override 
            public void actionPerformed(ActionEvent e) 
            { 
                if(precision_frame_inc_btn.isEnabled())
                {
                    precision_frame_precision_field.setText(precision_frame_precision_field.getText() + "0");
                    //If current precision is equal to or greater than max precision, then disable the increase precision button
                    if(precision_frame_precision_field.getText().length() >= max_precision.length()) { precision_frame_inc_btn.setEnabled(false); }
                    //If decrease percision button is disable and current percision is greater than min percision allowed, then enable the decrease precision button
                    if(!precision_frame_dec_btn.isEnabled() && precision_frame_precision_field.getText().length() > min_precision.length()) { precision_frame_dec_btn.setEnabled(true); }
                }
            } 
        });

        //Deploy the choose precision frame to the user
        precision_frame.pack();
        set_precision_frame_color();
        precision_frame.setLocationRelativeTo(null);
        precision_frame.setVisible(true);
        precision_frame.setResizable(false);
        precision_frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        precision_frame.addWindowListener(new WindowListener()
        {
            @Override public void windowClosing(WindowEvent e) { dispose_precision_frame(); }
            @Override public void windowOpened(WindowEvent e) {}
            @Override public void windowClosed(WindowEvent e) {}
            @Override public void windowIconified(WindowEvent e) {}
            @Override public void windowDeiconified(WindowEvent e) {}
            @Override public void windowActivated(WindowEvent e) {}
            @Override public void windowDeactivated(WindowEvent e) {}
        });
    }//ChoosePrecision()

    /**
     * Refresh precision frame while retaining user changes
     */
    protected void refresh_precision_frame()
    {
        if(precision_frame != null)
        {
            String precision_frame_precision_field_text = precision_frame_precision_field.getText();
            boolean precision_frame_dec_btn_enable = precision_frame_dec_btn.isEnabled();
            boolean precision_frame_inc_btn_enable = precision_frame_inc_btn.isEnabled();
            ChoosePrecision();
            precision_frame_precision_field.setText(precision_frame_precision_field_text);
            precision_frame_dec_btn.setEnabled(precision_frame_dec_btn_enable);
            precision_frame_inc_btn.setEnabled(precision_frame_inc_btn_enable);
        }
    }

    /**
     * Sets the precision menu label color for better readability
     */
    protected void set_precision_frame_label_color()
    {
        if(precision_frame != null)
        {
            try { precision_frame_precision_label.setForeground(precision_frame_inc_btn.getInvertedColor(color_arr[16], 100, 155, false)); } 
            catch(Exception ex) { return; }
        }
    }

    /**
     * Set the bg color of the precision menu frame
     */
    protected void set_precision_frame_color() { if(precision_frame != null) { precision_frame.getContentPane().setBackground(color_arr[16]); } }

    /**
     * Set set font of the button and labels on the precision menu
     */
    protected void set_precision_frame_system_font()
    {
        if(precision_frame != null)
        {
            precision_frame_dec_btn.setFont(font_arr[0]);  
            precision_frame_inc_btn.setFont(precision_frame_dec_btn.getFont());
            precision_frame_precision_label.setFont(precision_frame_dec_btn.getFont()); 
            precision_frame_reset_btn.setFont(precision_frame_dec_btn.getFont()); 
            precision_frame_exit_btn.setFont(precision_frame_dec_btn.getFont()); 
        }
    }
    
    /**
     * set bg color of the buttons on the precision menu
     */
    protected void set_precision_frame_btn_color()
    {
        if(precision_frame != null)
        {
            set_precision_frame_btn_bg_color();
            set_precision_frame_btn_hover_color();
            set_precision_frame_btn_press_color();
            set_precision_frame_btn_border_color();
            set_precision_frame_btn_font_color();
            set_precision_frame_btn_disabled_color();
            set_precision_frame_btn_layout();
        }
    }

    /**
     * Set the precision frame button corner cut-out
     */
    protected void set_precision_frame_btn_layout()
    {
        if(precision_frame != null) 
        {
            set_precision_frame_btn_arcHeight_layout();
            set_precision_frame_btn_arcWidth_layout();
        }
    }

    /**
     * Set the precision frame button corner arc height
     */
    protected void set_precision_frame_btn_arcHeight_layout()
    {
        if(precision_frame != null) 
        {
            precision_frame_dec_btn.setArcHeight(btn_arc_height);
            precision_frame_inc_btn.setArcHeight(precision_frame_dec_btn.getArcHeight());
            precision_frame_reset_btn.setArcHeight(precision_frame_dec_btn.getArcHeight());
            precision_frame_exit_btn.setArcHeight(precision_frame_dec_btn.getArcHeight());
        }
    }
    
    /**
     * Set the precision frame button corner arc width
     */
    protected void set_precision_frame_btn_arcWidth_layout()
    {
        if(precision_frame != null) 
        {
            precision_frame_dec_btn.setArcWidth(btn_arc_width);
            precision_frame_inc_btn.setArcWidth(precision_frame_dec_btn.getArcWidth());
            precision_frame_reset_btn.setArcWidth(precision_frame_dec_btn.getArcWidth());
            precision_frame_exit_btn.setArcWidth(precision_frame_dec_btn.getArcWidth());
        }
    }

    /**
     * Sets the button bg color in precision menu
     */
    protected void set_precision_frame_btn_bg_color()
    {
        if(precision_frame != null)
        {
            //If the button have default color, then do not set the background of the buttons
            if(color_arr[7].getRGB() != default_button_color_rgb) { precision_frame_dec_btn.setBackground(color_arr[7]); }
            else { precision_frame_dec_btn.setBackground(default_color_arr[14]); }
            
            precision_frame_inc_btn.setBackground(precision_frame_dec_btn.getBackground()); 
            precision_frame_reset_btn.setBackground(precision_frame_dec_btn.getBackground()); 
            precision_frame_exit_btn.setBackground(precision_frame_dec_btn.getBackground()); 
        }
    }
    
    /**
     * Sets the button hover color in precision menu
     */
    protected void set_precision_frame_btn_hover_color()
    {
        if(precision_frame != null)
        {
            precision_frame_dec_btn.setHoverBackgroundColor(color_arr[8]);
            precision_frame_inc_btn.setHoverBackgroundColor(precision_frame_dec_btn.getHoverBackgroundColor());
            precision_frame_reset_btn.setHoverBackgroundColor(precision_frame_dec_btn.getHoverBackgroundColor()); 
            precision_frame_exit_btn.setHoverBackgroundColor(precision_frame_dec_btn.getHoverBackgroundColor());
        }
    }
    
    /**
     * Sets the button press color in precision menu
     */
    protected void set_precision_frame_btn_press_color()
    {
        if(precision_frame != null)
        {
            precision_frame_dec_btn.setPressedBackgroundColor(color_arr[9]);
            precision_frame_inc_btn.setPressedBackgroundColor(precision_frame_dec_btn.getPressedBackgroundColor());
            precision_frame_reset_btn.setPressedBackgroundColor(precision_frame_dec_btn.getPressedBackgroundColor()); 
            precision_frame_exit_btn.setPressedBackgroundColor(precision_frame_dec_btn.getPressedBackgroundColor());
        }
    }
    
    /**
     * Sets the button border color in precision menu
     */
    protected void set_precision_frame_btn_border_color()
    {
        if(precision_frame != null)
        {
            precision_frame_dec_btn.setBorderColor(color_arr[10]);
            precision_frame_inc_btn.setBorderColor(precision_frame_dec_btn.getBorderColor());
            precision_frame_reset_btn.setBorderColor(precision_frame_dec_btn.getBorderColor()); 
            precision_frame_exit_btn.setBorderColor(precision_frame_dec_btn.getBorderColor());
        }
    }
    
    /**
     * Sets the button font color in precision menu
     */
    protected void set_precision_frame_btn_font_color()
    {
        if(precision_frame != null)
        {
            precision_frame_dec_btn.setFontColor(color_arr[11]);
            precision_frame_inc_btn.setFontColor(precision_frame_dec_btn.getFontColor());
            precision_frame_reset_btn.setFontColor(precision_frame_dec_btn.getFontColor()); 
            precision_frame_exit_btn.setFontColor(precision_frame_dec_btn.getFontColor());
        }
    }

    /**
     * Sets the button disabled color in precision menu
     */
    protected void set_precision_frame_btn_disabled_color()
    {
        if(precision_frame != null)
        {
            precision_frame_dec_btn.setDisabledBackgroundColor(color_arr[12]);
            precision_frame_inc_btn.setDisabledBackgroundColor(precision_frame_dec_btn.getDisabledBackgroundColor());
            precision_frame_reset_btn.setDisabledBackgroundColor(precision_frame_dec_btn.getDisabledBackgroundColor()); 
            precision_frame_exit_btn.setDisabledBackgroundColor(precision_frame_dec_btn.getDisabledBackgroundColor());
        }
    }

    /**
     * Repaints all the buttons in precision menu
     */
    protected void precision_frame_btn_repaint()
    {
        if(precision_frame != null)
        {
            precision_frame_dec_btn.repaint();
            precision_frame_inc_btn.repaint();
            precision_frame_reset_btn.repaint();
            precision_frame_exit_btn.repaint();
        }
    }

    /**
     * Try to dispose the precision menu frame
     */
    protected void dispose_precision_frame() { if(precision_frame != null) { precision_frame.dispose(); precision_frame = null; } }

    /**
     * Set the bg color of the precision field on the precision menu
     */
    protected void set_precision_frame_output_color() { if(precision_frame != null) { precision_frame_precision_field.setBackground(color_arr[5]); } }

    /**
     * Set the precision field font on the precision menu
     */
    protected void set_precision_frame_out_font() { if(precision_frame != null) { precision_frame_precision_field.setFont(font_arr[3]); } }


    /**
     * Change font settings within the system
     */
    protected void ChooseFont()
    {
        //Try to dispose the font menu if it is already open
        dispose_font_frame();
        
        //Create a new choose font menu
        font_frame = new JFrame("Choose Font"); 
        font_frame.setIconImage(main_frame.getIconImage()); 
        font_frame.setLayout(new GridBagLayout());

        //Array of available font attributes
        final String[] font_names_arr = { Font.DIALOG, Font.MONOSPACED, Font.SANS_SERIF, Font.SERIF };
        final String[] font_style_arr_names = { "BOLD","PLAIN","ITALIC","BOLD + ITALIC" };
        final int[] font_style_arr = { Font.BOLD, Font.PLAIN, Font.ITALIC, Font.BOLD + Font.ITALIC };
        
        //User selection of font name and style
        font_frame_font_names_arr_location = 0; 
        font_frame_font_style_arr_location = 0;

        //Create a text field to show the user a preview of the font style and size
        font_frame_perview_field = new JTextField("Aa Bb Cc Dd 12345 @ Preview"); 
        font_frame_perview_field.selectAll(); 
        font_frame_perview_field.setFont(new Font(font_names_arr[font_frame_font_names_arr_location], font_style_arr[font_frame_font_style_arr_location], font_arr[0].getSize())); 
        set_font_frame_input_color();

        //Create three text fields to show the user current selection of font, font style, and font size
        font_frame_font_name_field = new JTextField(font_names_arr[font_frame_font_names_arr_location]); 
        font_frame_font_name_field.setHorizontalAlignment(default_font_alignment); 
        font_frame_font_name_field.setFont(font_frame_perview_field.getFont()); 
        font_frame_font_name_field.setEditable(false); 
        font_frame_font_style_field = new JTextField(font_style_arr_names[font_frame_font_style_arr_location]); 
        font_frame_font_style_field.setHorizontalAlignment(default_font_alignment); 
        font_frame_font_style_field.setFont(font_frame_perview_field.getFont()); 
        font_frame_font_style_field.setEditable(false); 
        font_frame_font_size_field = new JTextField(Integer.toString(font_arr[0].getSize())); 
        font_frame_font_size_field.setHorizontalAlignment(default_font_alignment); 
        font_frame_font_size_field.setFont(font_frame_perview_field.getFont()); 
        font_frame_font_size_field.setEditable(false); 
        set_font_frame_output_color();

        //Create buttons to change the font of specific group of objects throughout the application
        font_frame_main_frame_bar_font_btn = new CustomJButton("MenuBar Font"); 
        font_frame_system_font_btn = new CustomJButton("System Font"); 
        font_frame_in_font_btn = new CustomJButton("Input Font"); 
        font_frame_out_font_btn = new CustomJButton("Output Font");

        //Create choose font menu frame buttons
        font_frame_reset_font_btn = new CustomJButton("Reset"); 
        Dimension btn_dim = new Dimension(font_frame_reset_font_btn.getPreferredSize().width+18, font_frame_perview_field.getPreferredSize().height+3); 
        font_frame_reset_font_btn.setPreferredSize(btn_dim);
        font_frame_perview_field_clear_btn = new CustomJButton("Clear"); 
        font_frame_perview_field_clear_btn.setPreferredSize(btn_dim);
        font_frame_font_name_field_leftbtn = new CustomJButton("<<<<"); 
        font_frame_font_name_field_leftbtn.setPreferredSize(btn_dim); 
        font_frame_font_name_field_leftbtn.setEnabled(false);
        font_frame_font_name_field_rightbtn = new CustomJButton(">>>>"); 
        font_frame_font_name_field_rightbtn.setPreferredSize(btn_dim);
        font_frame_font_style_field_leftbtn = new CustomJButton("<<<<"); 
        font_frame_font_style_field_leftbtn.setPreferredSize(btn_dim); 
        font_frame_font_style_field_leftbtn.setEnabled(false);
        font_frame_font_style_field_rightbtn = new CustomJButton(">>>>"); 
        font_frame_font_style_field_rightbtn.setPreferredSize(btn_dim);
        font_frame_font_size_field_decbtn = new CustomJButton("----"); 
        font_frame_font_size_field_decbtn.setPreferredSize(btn_dim);
        font_frame_font_size_field_incbtn = new CustomJButton("++++"); 
        font_frame_font_size_field_incbtn.setPreferredSize(btn_dim);
        font_frame_exit_btn = new CustomJButton("Save & Exit"); 
        set_font_frame_system_font();

        //If the button color is not set to default
        set_font_frame_btn_color();

        GridBagConstraints font_frame_constr = new GridBagConstraints();
        font_frame_constr.fill = GridBagConstraints.HORIZONTAL;

        //Adding preview field to font menu
        font_frame_perview_field.setPreferredSize(new Dimension(font_frame_perview_field.getPreferredSize().width, font_frame_perview_field.getPreferredSize().height+12));
        font_frame_constr.gridwidth = 3; 
        font_frame_constr.weighty = 0.5; 
        font_frame_constr.weightx = 0.5; 
        font_frame_constr.insets = new Insets(5,5,5,5); 
        font_frame.add(font_frame_perview_field, font_frame_constr);

        //Adding preview field to font menu
        font_frame_constr.gridwidth = 1; 
        font_frame_constr.gridx = 3; 
        font_frame_constr.insets = new Insets(1,1,1,1); 
        font_frame.add(font_frame_perview_field_clear_btn, font_frame_constr);

        //Create label to put on font menu frame
        font_frame_font_name_label = new JLabel("Font Name:"); 
        font_frame_font_name_label.setHorizontalAlignment(default_font_alignment); 
        font_frame_font_name_label.setPreferredSize(new Dimension(font_frame_font_name_label.getPreferredSize().width+35, font_frame_font_name_label.getPreferredSize().height));
        font_frame_font_style_label = new JLabel("Font Style:"); 
        font_frame_font_style_label.setHorizontalAlignment(default_font_alignment); 
        font_frame_font_style_label.setPreferredSize(font_frame_font_name_label.getPreferredSize());
        font_frame_font_size_label = new JLabel("Font Size:"); 
        font_frame_font_size_label.setHorizontalAlignment(default_font_alignment);
        font_frame_font_size_label.setPreferredSize(font_frame_font_name_label.getPreferredSize());
        set_font_frame_label_color();
        set_font_frame_label_font();

        //Set the size for the button and text field
        font_frame_font_name_field.setPreferredSize(new Dimension(font_frame_font_name_field.getPreferredSize().width+100, font_frame_font_name_field.getPreferredSize().height+5)); 
        font_frame_font_style_field.setPreferredSize(font_frame_font_name_field.getPreferredSize()); 
        font_frame_font_size_field.setPreferredSize(font_frame_font_name_field.getPreferredSize());
        font_frame_system_font_btn.setPreferredSize(font_frame_main_frame_bar_font_btn.getPreferredSize());
        font_frame_in_font_btn.setPreferredSize(font_frame_system_font_btn.getPreferredSize()); 
        font_frame_out_font_btn.setPreferredSize(font_frame_system_font_btn.getPreferredSize()); 
        font_frame_exit_btn.setPreferredSize(font_frame_system_font_btn.getPreferredSize());

        //Adding components to the choose front menu frame
        font_frame_constr.gridx = 0; 
        font_frame_constr.gridy = 2; 
        font_frame_constr.insets = new Insets(1,5,1,1); 
        font_frame.add(font_frame_font_name_label, font_frame_constr); 
        font_frame_constr.insets = new Insets(1,1,1,1); 
        font_frame_constr.gridx = 1; 
        font_frame.add(font_frame_font_name_field_leftbtn, font_frame_constr); 
        font_frame_constr.gridx = 2; 
        font_frame.add(font_frame_font_name_field, font_frame_constr); 
        font_frame_constr.gridx = 3; 
        font_frame.add(font_frame_font_name_field_rightbtn, font_frame_constr);
        font_frame_constr.gridx = 0; 
        font_frame_constr.gridy = 3; 
        font_frame_constr.insets = new Insets(1,5,1,1); 
        font_frame.add(font_frame_font_style_label, font_frame_constr); 
        font_frame_constr.gridx = 1; 
        font_frame_constr.insets = new Insets(1,1,1,1); 
        font_frame.add(font_frame_font_style_field_leftbtn, font_frame_constr); 
        font_frame_constr.gridx = 2; 
        font_frame.add(font_frame_font_style_field, font_frame_constr); 
        font_frame_constr.gridx = 3; 
        font_frame.add(font_frame_font_style_field_rightbtn, font_frame_constr);
        font_frame_constr.gridx = 0; 
        font_frame_constr.gridy = 4; 
        font_frame_constr.insets = new Insets(1,5,1,1); 
        font_frame.add(font_frame_font_size_label, font_frame_constr); 
        font_frame_constr.gridx = 1; 
        font_frame_constr.insets = new Insets(1,1,1,1); 
        font_frame.add(font_frame_font_size_field_decbtn, font_frame_constr); 
        font_frame_constr.gridx = 2; 
        font_frame.add(font_frame_font_size_field, font_frame_constr); 
        font_frame_constr.gridx = 3; 
        font_frame.add(font_frame_font_size_field_incbtn, font_frame_constr);
        font_frame_constr.gridx = 0; 
        font_frame_constr.gridy = 5; 
        font_frame_constr.insets = new Insets(7,5,0,5); 
        font_frame.add(font_frame_reset_font_btn, font_frame_constr); 
        font_frame_constr.insets = new Insets(7,5,5,5); 
        font_frame_constr.gridx = 4; 
        font_frame.add(font_frame_exit_btn, font_frame_constr);
        font_frame_constr.gridx = 4; 
        font_frame_constr.gridy = 0; 
        font_frame_constr.insets = new Insets(5,5,-7,5); 
        font_frame.add(font_frame_system_font_btn, font_frame_constr);
        font_frame_constr.insets = new Insets(1,5,2,5); 
        font_frame_constr.gridy = 2; 
        font_frame.add(font_frame_main_frame_bar_font_btn, font_frame_constr); 
        font_frame_constr.gridy = 3; 
        font_frame.add(font_frame_in_font_btn, font_frame_constr);
        font_frame_constr.gridy = 4; 
        font_frame.add(font_frame_out_font_btn, font_frame_constr);

        //Dispose the font menu, save the settings, and ask the user to restart the application
        font_frame_exit_btn.addActionListener(new ActionListener() 
        { 
            @Override 
            public void actionPerformed(ActionEvent e) 
            { 
                dispose_font_frame();
                if(JOptionPane.showOptionDialog(null, "Do you want to re-deploy the application for better results?", "Re-launch Prompt", JOptionPane.YES_NO_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, null, null, null) == JOptionPane.YES_OPTION) { main_frame_app_redeploy_menu_item.doClick(); }
                create_save_setting_file(); 
            } 
        });

        //Clear the font perview field
        font_frame_perview_field_clear_btn.addActionListener(new ActionListener() { @Override public void actionPerformed(ActionEvent e) { font_frame_perview_field.setText(""); } });

        //If the user clicks on preview field, then select the entire text
        font_frame_perview_field.addFocusListener(new FocusListener()
        {
            @Override public void focusGained(FocusEvent e) { font_frame_perview_field.selectAll(); } 
            @Override public void focusLost(FocusEvent e) {/*Do nothing when the focus is lost*/}
        });

        //Change the font for every output throughout the application
        font_frame_out_font_btn.addActionListener(new ActionListener() 
        {
            @Override 
            public void actionPerformed(ActionEvent e) 
            { 
                //Set the output font to current user selection
                font_arr[3] = new Font(font_names_arr[font_frame_font_names_arr_location], font_style_arr[font_frame_font_style_arr_location], Integer.parseInt(font_frame_font_size_field.getText()));
                
                //Set the matrix operation menu output field font to current applied changes
                try
                { 
                    set_matrix_oper_frame_output_err_bar_font();

                    for(int i = 0; i < matrix_oper_frame_out_field_arr.length; ++i)
                    {
                        for(int j = 0; j < matrix_oper_frame_out_field_arr[0].length; ++j)
                        {
                            matrix_oper_frame_out_field_arr[i][j].setFont(font_arr[3]);
                        }
                    } 

                    matrix_oper_frame.pack();
                } catch(Exception ex){/*Matrix operation panel does not exist*/}
                
                //Set the matrix menu error output font to current user selection
                try
                { 
                    set_matrix_frame_output_font();
                    matrix_frame.pack(); 
                } catch(Exception ex){/*Matrix main panel does not exist*/}

                //Set the calculator menu result font to current user selection
                try
                { 
                    set_cal_frame_output_font();
                    cal_frame.pack(); 
                } catch(Exception ex){/*Simple Calculator panel does not exist*/}

                //Set the percision menu percision field font to current user selection
                try
                { 
                    set_precision_frame_out_font();
                    precision_frame.pack(); 
                } catch(Exception ex){/*Choose precision panel does not exist*/}
                
                //Set the percision menu percision field font to current user selection
                try
                { 
                    set_button_layout_frame_out_font();
                    button_layout_frame.pack(); 
                } catch(Exception ex){/*Choose button layout panel does not exist*/}
            } 
        });

        //Change the font for every input field throughout the application
        font_frame_in_font_btn.addActionListener(new ActionListener() 
        {
            @Override 
            public void actionPerformed(ActionEvent e) 
            { 
                //Set the input font to current user selection
                font_arr[2] = new Font(font_names_arr[font_frame_font_names_arr_location], font_style_arr[font_frame_font_style_arr_location], Integer.parseInt(font_frame_font_size_field.getText())); 
                
                //Set the matrix operation menu input field font to current applied changes
                try
                { 
                    for(int i = 0; i < matrix_oper_frame_user_field_arr.length; ++i)
                    {
                        for(int j = 0; j < matrix_oper_frame_user_field_arr[0].length; ++j)
                        {
                            matrix_oper_frame_user_field_arr[i][j].setFont(font_arr[2]);
                        }
                    } 
                    matrix_oper_frame.pack(); 
                } catch(Exception ex){/*Matrix operation panel does not exist*/}

                //Set the matrix menu row and column input font to current user selection
                try
                { 
                    set_matrix_frame_input_font();
                    matrix_frame.pack(); 
                } catch(Exception ex){/*Matrix main panel does not exist*/}

                //Set the calculator menu number input font to current user selection
                try
                { 
                    set_cal_frame_input_font();
                    cal_frame.pack(); 
                } catch(Exception ex){/*Simple Calculator panel does not exist*/}
            } 
        });

        //Change the font for main menu bar
        font_frame_main_frame_bar_font_btn.addActionListener(new ActionListener() 
        {
            @Override 
            public void actionPerformed(ActionEvent e) 
            {
                //Change the main menu bar font to current user selection
                font_arr[1] = new Font(font_names_arr[font_frame_font_names_arr_location], font_style_arr[font_frame_font_style_arr_location], Integer.parseInt(font_frame_font_size_field.getText())); 
                
                //Change the font for the main menu bar in the main frame
                try
                {
                    set_main_frame_bar_font();
                    main_frame.pack();
                } catch(Exception ex){/*Main panel does not exist*/}
            } 
        });

        //Change the system font
        font_frame_system_font_btn.addActionListener(new ActionListener() 
        {
            @Override 
            public void actionPerformed(ActionEvent e) 
            {
                //Set the system font to current user selection
                font_arr[0] = new Font(font_names_arr[font_frame_font_names_arr_location], font_style_arr[font_frame_font_style_arr_location], Integer.parseInt(font_frame_font_size_field.getText())); 

                //Change the system font in Choose Font menu
                set_font_frame_label_font();
                set_font_frame_system_font();
                font_frame.pack();

                //Change the system button font in the main menu frame
                try 
                {
                    set_main_frame_system_font();
                    main_frame.pack();
                } catch(Exception ex) {/*Main panel does not exist*/}

                //Change choose color menu system font
                try
                {
                    set_color_frame_system_font();
                    color_frame.pack();
                } catch(Exception ex) {/*Choose color panel does not exist*/}

                //Change system font in Matrix menu
                try 
                {
                    set_matrix_frame_system_font();
                    matrix_frame.pack();
                } catch(Exception ex) {/*Matrix main panel does not exist*/}

                //Change system font in Matrix Oper menu
                try 
                {
                    set_matrix_oper_frame_system_font();
                    matrix_oper_frame.pack();
                } catch(Exception ex) {/*Matrix operation panel does not exist*/}
                
                //Change the system font in Calculator menu
                try 
                {
                    set_cal_frame_system_font();
                    cal_frame.pack();
                } catch(Exception ex) {/*Simple Calculator panel does not exist*/}
                
                //Change the system font in Choose Precision menu
                try 
                {
                    set_precision_frame_system_font();
                    precision_frame.pack();
                } catch(Exception ex){/*Choose precision panel does not exist*/}
                
                //Change the system font in Choose Precision menu
                try 
                {
                    set_button_layout_frame_system_font();
                    button_layout_frame.pack();
                } catch(Exception ex){/*Choose button layout panel does not exist*/}
            } 
        });

        font_frame_font_name_field_leftbtn.setEnabled(false);
        //Set the action for font name left button
        font_frame_font_name_field_leftbtn.addActionListener(new ActionListener() 
        { 
            @Override 
            public void actionPerformed(ActionEvent e) 
            { 
                if(font_frame_font_name_field_leftbtn.isEnabled())
                {
                    --font_frame_font_names_arr_location;
                    if(font_frame_font_names_arr_location == 0) { font_frame_font_name_field_leftbtn.setEnabled(false); }
                    //If the name field right button is disabled and the user name selection is less than name array length, then make right name button visible 
                    if(!font_frame_font_name_field_rightbtn.isEnabled() && font_frame_font_names_arr_location < font_names_arr.length - 1) { font_frame_font_name_field_rightbtn.setEnabled(true); }
                    //Update the font of the text field
                    font_frame_font_name_field.setText(font_names_arr[font_frame_font_names_arr_location]);
                    font_frame_font_name_field.setFont(new Font(font_names_arr[font_frame_font_names_arr_location], font_style_arr[font_frame_font_style_arr_location], Integer.parseInt(font_frame_font_size_field.getText())));
                    font_frame_perview_field.setFont(font_frame_font_name_field.getFont());
                    font_frame_font_style_field.setFont(font_frame_font_name_field.getFont());
                    font_frame_font_size_field.setFont(font_frame_font_name_field.getFont());
                }
            } 
        });

        //Set the action for font name right button
        font_frame_font_name_field_rightbtn.addActionListener(new ActionListener() 
        { 
            @Override 
            public void actionPerformed(ActionEvent e) 
            { 
                if(font_frame_font_name_field_rightbtn.isEnabled())
                {
                    ++font_frame_font_names_arr_location;
                    if(font_frame_font_names_arr_location == font_names_arr.length-1) { font_frame_font_name_field_rightbtn.setEnabled(false); }
                    //If the name field left button is disabled and the user name selection is greater than 0, then make left name button visible 
                    if(!font_frame_font_name_field_leftbtn.isEnabled() && font_frame_font_names_arr_location > 0) { font_frame_font_name_field_leftbtn.setEnabled(true); }
                    //Update the font of the text field
                    font_frame_font_name_field.setText(font_names_arr[font_frame_font_names_arr_location]);
                    font_frame_font_name_field.setFont(new Font(font_names_arr[font_frame_font_names_arr_location], font_style_arr[font_frame_font_style_arr_location], Integer.parseInt(font_frame_font_size_field.getText())));
                    font_frame_perview_field.setFont(font_frame_font_name_field.getFont());
                    font_frame_font_style_field.setFont(font_frame_font_name_field.getFont());
                    font_frame_font_size_field.setFont(font_frame_font_name_field.getFont());
                }
            } 
        });

        font_frame_font_style_field_leftbtn.setEnabled(false);
        //Set the action for font style left button
        font_frame_font_style_field_leftbtn.addActionListener(new ActionListener() { @Override public void actionPerformed(ActionEvent e) 
        { 
            if(font_frame_font_style_field_leftbtn.isEnabled())
            {
                --font_frame_font_style_arr_location;
                if(font_frame_font_style_arr_location == 0) { font_frame_font_style_field_leftbtn.setEnabled(false); }
                //If the style field right button is disabled and the user style selection is less than style array length, then make right style button visible 
                if(!font_frame_font_style_field_rightbtn.isEnabled() && font_frame_font_style_arr_location < font_style_arr.length - 1) { font_frame_font_style_field_rightbtn.setEnabled(true); }
                //Update the font of the text field
                font_frame_font_style_field.setText(font_style_arr_names[font_frame_font_style_arr_location]);
                font_frame_font_style_field.setFont(new Font(font_names_arr[font_frame_font_names_arr_location], font_style_arr[font_frame_font_style_arr_location], Integer.parseInt(font_frame_font_size_field.getText())));
                font_frame_perview_field.setFont(font_frame_font_style_field.getFont());
                font_frame_font_name_field.setFont(font_frame_font_style_field.getFont());
                font_frame_font_size_field.setFont(font_frame_font_style_field.getFont());
            }
        } });

        //Set the action for font style right button
        font_frame_font_style_field_rightbtn.addActionListener(new ActionListener() { @Override public void actionPerformed(ActionEvent e) 
        { 
            if(font_frame_font_style_field_rightbtn.isEnabled())
            {
                ++font_frame_font_style_arr_location;
                if(font_frame_font_style_arr_location == font_style_arr.length-1) { font_frame_font_style_field_rightbtn.setEnabled(false); }
                //If the style field left button is disabled and the user style selection is greater than 0, then make left style button visible 
                if(!font_frame_font_style_field_leftbtn.isEnabled() && font_frame_font_style_arr_location > 0) { font_frame_font_style_field_leftbtn.setEnabled(true); }
                //Update the font of the text field
                font_frame_font_style_field.setText(font_style_arr_names[font_frame_font_style_arr_location]);
                font_frame_font_style_field.setFont(new Font(font_names_arr[font_frame_font_names_arr_location], font_style_arr[font_frame_font_style_arr_location], Integer.parseInt(font_frame_font_size_field.getText())));
                font_frame_perview_field.setFont(font_frame_font_style_field.getFont());
                font_frame_font_name_field.setFont(font_frame_font_style_field.getFont());
                font_frame_font_size_field.setFont(font_frame_font_style_field.getFont());
            }
        } });

        //If the font size is less than or equal to min limit, then disable the font size decrease button
        if(Integer.parseInt(font_frame_font_size_field.getText()) <= min_font_size) { font_frame_font_size_field_decbtn.setEnabled(false); }
        //Set action for font size decrease button
        font_frame_font_size_field_decbtn.addActionListener(new ActionListener() { @Override public void actionPerformed(ActionEvent e) 
        { 
            if(font_frame_font_size_field_decbtn.isEnabled())
            {
                int temp = Integer.parseInt(font_frame_font_size_field.getText()) - 1;
                if(temp <= min_font_size) { font_frame_font_size_field_decbtn.setEnabled(false); }
                //If the font size increase button is disabled and the current font size is less than max font size, then enable the font increase button
                if(!font_frame_font_size_field_incbtn.isEnabled() && temp < max_font_size) { font_frame_font_size_field_incbtn.setEnabled(true); }
                //Update the text field within the frame
                font_frame_font_size_field.setText(Integer.toString(temp));
                font_frame_font_size_field.setFont(new Font(font_names_arr[font_frame_font_names_arr_location], font_style_arr[font_frame_font_style_arr_location], temp));
                font_frame_perview_field.setFont(font_frame_font_size_field.getFont());
                font_frame_font_style_field.setFont(font_frame_font_size_field.getFont());
                font_frame_font_name_field.setFont(font_frame_font_size_field.getFont());
            }
        } });

        //If the font size is greater than or equal to max limit, then disable the font size increase button
        if(Integer.parseInt(font_frame_font_size_field.getText()) >= max_font_size) { font_frame_font_size_field_incbtn.setEnabled(false); }
        //Set action for font size increase button
        font_frame_font_size_field_incbtn.addActionListener(new ActionListener() { @Override public void actionPerformed(ActionEvent e) 
        { 
            if(font_frame_font_size_field_incbtn.isEnabled())
            {
                int temp = Integer.parseInt(font_frame_font_size_field.getText()) + 1;
                if(temp >= max_font_size) { font_frame_font_size_field_incbtn.setEnabled(false); }
                //If the font size decrease button is disabled and the current font size is greater than min font size, then enable the font decrease button
                if(!font_frame_font_size_field_decbtn.isEnabled() && temp > min_font_size) { font_frame_font_size_field_decbtn.setEnabled(true); }
                //Update the text field within the frame
                font_frame_font_size_field.setText(Integer.toString(temp));
                font_frame_font_size_field.setFont(new Font(font_names_arr[font_frame_font_names_arr_location], font_style_arr[font_frame_font_style_arr_location], temp));
                font_frame_perview_field.setFont(font_frame_font_size_field.getFont());
                font_frame_font_style_field.setFont(font_frame_font_size_field.getFont());
                font_frame_font_name_field.setFont(font_frame_font_size_field.getFont());
            }
        } });

        //Reset the font, font style, and font size. Save the settings. Update all the component's fonts within the application. 
        font_frame_reset_font_btn.addActionListener(new ActionListener() { @Override public void actionPerformed(ActionEvent e) 
        {
            restore_font_settings();

            if(JOptionPane.showOptionDialog(null, "Do you want to re-deploy the application for better results?", "Re-launch Prompt", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null) 
                == JOptionPane.YES_OPTION) { main_frame_app_redeploy_menu_item.doClick(); }
            else
            {
                //Reset all the fonts in Choose Font menu
                set_font_frame_label_font();
                set_font_frame_system_font();
                font_frame.pack();
                
                //Reset all the fonts in Main menu
                try 
                {
                    set_main_frame_bar_font();
                    set_main_frame_system_font();
                    main_frame.pack();
                } catch(Exception ex) {/*Main menu frame does not exist*/}

                //Reset all the fonts in Choose Color menu
                try
                {
                    set_color_frame_system_font();
                    color_frame.pack();
                } catch(Exception ex) {/*Choose Color menu frame does not exist*/}

                //Reset all the fonts in Matrix menu
                try 
                {
                    set_matrix_frame_input_font();
                    set_matrix_frame_output_font(); 
                    set_matrix_frame_system_font();
                    matrix_frame.pack();
                } catch(Exception ex) {/*Matrix menu frame does not exist*/}

                //Reset all the fonts in Matrix Operation menu
                try 
                {
                    for(int i = 0; i < matrix_oper_frame_user_field_arr.length; ++i)
                    {
                        for(int j = 0; j < matrix_oper_frame_user_field_arr[0].length; ++j)
                        {
                            matrix_oper_frame_user_field_arr[i][j].setFont(font_arr[2]); 
                            matrix_oper_frame_out_field_arr[i][j].setFont(font_arr[3]);
                        }
                    }

                    set_matrix_oper_frame_system_font();
                    matrix_oper_frame.pack();
                } catch(Exception ex) {/*Matrix Operation menu frame does not exist*/}

                //Reset all the fonts in Calculator menu
                try 
                {
                    set_cal_frame_input_font(); 
                    set_cal_frame_output_font();
                    set_cal_frame_system_font();
                    cal_frame.pack();
                } catch(Exception ex) {/*Calculator menu frame does not exist*/}

                //Reset all the fonts in Choose Precision menu
                try 
                { 
                    set_precision_frame_system_font(); 
                    set_precision_frame_out_font();
                    precision_frame.pack();
                } catch(Exception ex) {/*Choose Precision menu frame does not exist*/}

                //Reset all the fonts in Choose Button Layout menu
                try 
                { 
                    set_button_layout_frame_system_font(); 
                    set_button_layout_frame_out_font();
                    button_layout_frame.pack();
                } catch(Exception ex) {/*Choose Button Layout menu frame does not exist*/}
                
            }

            //Save the settings to a file, and ask the user to restart the application
            create_save_setting_file();
        } });

        //Deploy the frame to the user
        set_font_frame_color();
        font_frame.pack();
        font_frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        font_frame.addWindowListener(new WindowListener()
        {
            @Override public void windowClosing(WindowEvent e) { dispose_font_frame(); }
            @Override public void windowOpened(WindowEvent e) {}
            @Override public void windowClosed(WindowEvent e) {}
            @Override public void windowIconified(WindowEvent e) {}
            @Override public void windowDeiconified(WindowEvent e) {}
            @Override public void windowActivated(WindowEvent e) {}
            @Override public void windowDeactivated(WindowEvent e) {}
        });
        font_frame.setLocationRelativeTo(null);
        font_frame.setResizable(false);
        font_frame.setVisible(true);
    }//chooseFont()

    /**
     * Refresh font frame components while keeping user changes intact
     */
    protected void refresh_font_frame()
    {
        if(font_frame != null)
        {
            String font_frame_perview_field_text = font_frame_perview_field.getText();
            String font_frame_font_name_field_text = font_frame_font_name_field.getText();
            String font_frame_font_style_field_text = font_frame_font_style_field.getText();
            String font_frame_font_size_field_text = font_frame_font_size_field.getText();
            boolean font_frame_font_name_field_leftbtn_enable = font_frame_font_name_field_leftbtn.isEnabled();
            boolean font_frame_font_name_field_rightbtn_enable = font_frame_font_name_field_rightbtn.isEnabled();
            boolean font_frame_font_size_field_decbtn_enable = font_frame_font_size_field_decbtn.isEnabled();
            boolean font_frame_font_size_field_incbtn_enable = font_frame_font_size_field_incbtn.isEnabled();
            boolean font_frame_font_style_field_leftbtn_enable = font_frame_font_style_field_leftbtn.isEnabled();
            boolean font_frame_font_style_field_rightbtn_enable = font_frame_font_style_field_rightbtn.isEnabled();

            ChooseFont();
            
            font_frame_perview_field.setText(font_frame_perview_field_text);
            font_frame_font_name_field.setText(font_frame_font_name_field_text);
            font_frame_font_style_field.setText(font_frame_font_style_field_text);
            font_frame_font_size_field.setText(font_frame_font_size_field_text);
            font_frame_font_name_field_leftbtn.setEnabled(font_frame_font_name_field_leftbtn_enable);
            font_frame_font_name_field_rightbtn.setEnabled(font_frame_font_name_field_rightbtn_enable);
            font_frame_font_size_field_decbtn.setEnabled(font_frame_font_size_field_decbtn_enable);
            font_frame_font_size_field_incbtn.setEnabled(font_frame_font_size_field_incbtn_enable);
            font_frame_font_style_field_leftbtn.setEnabled(font_frame_font_style_field_leftbtn_enable);
            font_frame_font_style_field_rightbtn.setEnabled(font_frame_font_style_field_rightbtn_enable);
        }
    }

    /**
     * Set the font menu label font
     */
    protected void set_font_frame_label_font()
    {
        if(font_frame != null)
        {
            font_frame_font_name_label.setFont(font_arr[0]); 
            font_frame_font_style_label.setFont(font_arr[0]); 
            font_frame_font_size_label.setFont(font_arr[0]); 
        }
    }
    
    /**
     * Set the font menu label font color
     */
    protected void set_font_frame_label_color()
    {
        if(font_frame != null)
        {
            try{ font_frame_font_name_label.setForeground(font_frame_exit_btn.getInvertedColor(color_arr[15], 100, 150, false)); } catch(Exception ex) { return; }
            font_frame_font_style_label.setForeground(font_frame_font_name_label.getForeground());
            font_frame_font_size_label.setForeground(font_frame_font_name_label.getForeground());
        }
    }

    /**
     * Set the bg color of the font menu frame
     */
    protected void set_font_frame_color() { if(font_frame != null) { font_frame.getContentPane().setBackground(color_arr[15]); } }

    /**
     * Set the bg color of the buttons on the font menu
     */
    protected void set_font_frame_btn_color()
    {
        if(font_frame != null)
        {
            set_font_frame_btn_bg_color();
            set_font_frame_btn_hover_color();
            set_font_frame_btn_press_color();
            set_font_frame_btn_border_color();
            set_font_frame_btn_font_color();
            set_font_frame_btn_disabled_color();
            set_font_frame_btn_layout();
        }
    }

    /**
     * Set the font frame button corner cut-out
     */
    protected void set_font_frame_btn_layout()
    {
        if(font_frame != null) 
        {
            set_font_frame_btn_arcHeight_layout();
            set_font_frame_btn_arcWidth_layout();
        }
    }

    /**
     * Set the font frame button corner arc height
     */
    protected void set_font_frame_btn_arcHeight_layout()
    {
        if(font_frame != null) 
        {
            font_frame_perview_field_clear_btn.setArcHeight(btn_arc_height);
            font_frame_font_name_field_leftbtn.setArcHeight(font_frame_perview_field_clear_btn.getArcHeight());
            font_frame_font_name_field_rightbtn.setArcHeight(font_frame_perview_field_clear_btn.getArcHeight());
            font_frame_font_style_field_leftbtn.setArcHeight(font_frame_perview_field_clear_btn.getArcHeight());
            font_frame_font_style_field_rightbtn.setArcHeight(font_frame_perview_field_clear_btn.getArcHeight());
            font_frame_font_size_field_decbtn.setArcHeight(font_frame_perview_field_clear_btn.getArcHeight());
            font_frame_font_size_field_incbtn.setArcHeight(font_frame_perview_field_clear_btn.getArcHeight());
            font_frame_reset_font_btn.setArcHeight(font_frame_perview_field_clear_btn.getArcHeight());
            font_frame_exit_btn.setArcHeight(font_frame_perview_field_clear_btn.getArcHeight());
            font_frame_system_font_btn.setArcHeight(font_frame_perview_field_clear_btn.getArcHeight());
            font_frame_main_frame_bar_font_btn.setArcHeight(font_frame_perview_field_clear_btn.getArcHeight());
            font_frame_in_font_btn.setArcHeight(font_frame_perview_field_clear_btn.getArcHeight());
            font_frame_out_font_btn.setArcHeight(font_frame_perview_field_clear_btn.getArcHeight());
        }
    }
    
    /**
     * Set the font frame button corner arc width
     */
    protected void set_font_frame_btn_arcWidth_layout()
    {
        if(font_frame != null) 
        {            
            font_frame_perview_field_clear_btn.setArcWidth(btn_arc_width);
            font_frame_font_name_field_leftbtn.setArcWidth(font_frame_perview_field_clear_btn.getArcWidth());
            font_frame_font_name_field_rightbtn.setArcWidth(font_frame_perview_field_clear_btn.getArcWidth());
            font_frame_font_style_field_leftbtn.setArcWidth(font_frame_perview_field_clear_btn.getArcWidth());
            font_frame_font_style_field_rightbtn.setArcWidth(font_frame_perview_field_clear_btn.getArcWidth());
            font_frame_font_size_field_decbtn.setArcWidth(font_frame_perview_field_clear_btn.getArcWidth());
            font_frame_font_size_field_incbtn.setArcWidth(font_frame_perview_field_clear_btn.getArcWidth());
            font_frame_reset_font_btn.setArcWidth(font_frame_perview_field_clear_btn.getArcWidth());
            font_frame_exit_btn.setArcWidth(font_frame_perview_field_clear_btn.getArcWidth());
            font_frame_system_font_btn.setArcWidth(font_frame_perview_field_clear_btn.getArcWidth());
            font_frame_main_frame_bar_font_btn.setArcWidth(font_frame_perview_field_clear_btn.getArcWidth());
            font_frame_in_font_btn.setArcWidth(font_frame_perview_field_clear_btn.getArcWidth());
            font_frame_out_font_btn.setArcWidth(font_frame_perview_field_clear_btn.getArcWidth());
        }
    }
    
    /**
     * Sets the button bg color in font menu
     */
    protected void set_font_frame_btn_bg_color()
    {
        if(font_frame != null)
        {
            if(color_arr[7].getRGB() != default_button_color_rgb) { font_frame_perview_field_clear_btn.setBackground(color_arr[7]); }
            else { font_frame_perview_field_clear_btn.setBackground(default_color_arr[14]); }

            font_frame_font_name_field_leftbtn.setBackground(font_frame_perview_field_clear_btn.getBackground()); 
            font_frame_font_name_field_rightbtn.setBackground(font_frame_perview_field_clear_btn.getBackground()); 
            font_frame_font_style_field_leftbtn.setBackground(font_frame_perview_field_clear_btn.getBackground()); 
            font_frame_font_style_field_rightbtn.setBackground(font_frame_perview_field_clear_btn.getBackground()); 
            font_frame_font_size_field_decbtn.setBackground(font_frame_perview_field_clear_btn.getBackground()); 
            font_frame_font_size_field_incbtn.setBackground(font_frame_perview_field_clear_btn.getBackground()); 
            font_frame_reset_font_btn.setBackground(font_frame_perview_field_clear_btn.getBackground()); 
            font_frame_exit_btn.setBackground(font_frame_perview_field_clear_btn.getBackground());
            font_frame_system_font_btn.setBackground(font_frame_perview_field_clear_btn.getBackground()); 
            font_frame_main_frame_bar_font_btn.setBackground(font_frame_perview_field_clear_btn.getBackground()); 
            font_frame_in_font_btn.setBackground(font_frame_perview_field_clear_btn.getBackground()); 
            font_frame_out_font_btn.setBackground(font_frame_perview_field_clear_btn.getBackground());
        }
    }
    
    /**
     * Sets the button hover color in font menu
     */
    protected void set_font_frame_btn_hover_color()
    {
        if(font_frame != null)
        {
            font_frame_perview_field_clear_btn.setHoverBackgroundColor(color_arr[8]);
            font_frame_font_name_field_leftbtn.setHoverBackgroundColor(font_frame_perview_field_clear_btn.getHoverBackgroundColor());
            font_frame_font_name_field_rightbtn.setHoverBackgroundColor(font_frame_perview_field_clear_btn.getHoverBackgroundColor()); 
            font_frame_font_style_field_leftbtn.setHoverBackgroundColor(font_frame_perview_field_clear_btn.getHoverBackgroundColor());
            font_frame_font_style_field_rightbtn.setHoverBackgroundColor(font_frame_perview_field_clear_btn.getHoverBackgroundColor()); 
            font_frame_font_size_field_decbtn.setHoverBackgroundColor(font_frame_perview_field_clear_btn.getHoverBackgroundColor()); 
            font_frame_font_size_field_incbtn.setHoverBackgroundColor(font_frame_perview_field_clear_btn.getHoverBackgroundColor()); 
            font_frame_reset_font_btn.setHoverBackgroundColor(font_frame_perview_field_clear_btn.getHoverBackgroundColor()); 
            font_frame_exit_btn.setHoverBackgroundColor(font_frame_perview_field_clear_btn.getHoverBackgroundColor());
            font_frame_system_font_btn.setHoverBackgroundColor(font_frame_perview_field_clear_btn.getHoverBackgroundColor()); 
            font_frame_main_frame_bar_font_btn.setHoverBackgroundColor(font_frame_perview_field_clear_btn.getHoverBackgroundColor()); 
            font_frame_in_font_btn.setHoverBackgroundColor(font_frame_perview_field_clear_btn.getHoverBackgroundColor()); 
            font_frame_out_font_btn.setHoverBackgroundColor(font_frame_perview_field_clear_btn.getHoverBackgroundColor());
        }
    }
    
    /**
     * Sets the button press color in font menu
     */
    protected void set_font_frame_btn_press_color()
    {
        if(font_frame != null)
        {
            font_frame_perview_field_clear_btn.setPressedBackgroundColor(color_arr[9]);
            font_frame_font_name_field_leftbtn.setPressedBackgroundColor(font_frame_perview_field_clear_btn.getPressedBackgroundColor());
            font_frame_font_name_field_rightbtn.setPressedBackgroundColor(font_frame_perview_field_clear_btn.getPressedBackgroundColor()); 
            font_frame_font_style_field_leftbtn.setPressedBackgroundColor(font_frame_perview_field_clear_btn.getPressedBackgroundColor());
            font_frame_font_style_field_rightbtn.setPressedBackgroundColor(font_frame_perview_field_clear_btn.getPressedBackgroundColor()); 
            font_frame_font_size_field_decbtn.setPressedBackgroundColor(font_frame_perview_field_clear_btn.getPressedBackgroundColor()); 
            font_frame_font_size_field_incbtn.setPressedBackgroundColor(font_frame_perview_field_clear_btn.getPressedBackgroundColor()); 
            font_frame_reset_font_btn.setPressedBackgroundColor(font_frame_perview_field_clear_btn.getPressedBackgroundColor()); 
            font_frame_exit_btn.setPressedBackgroundColor(font_frame_perview_field_clear_btn.getPressedBackgroundColor());
            font_frame_system_font_btn.setPressedBackgroundColor(font_frame_perview_field_clear_btn.getPressedBackgroundColor()); 
            font_frame_main_frame_bar_font_btn.setPressedBackgroundColor(font_frame_perview_field_clear_btn.getPressedBackgroundColor()); 
            font_frame_in_font_btn.setPressedBackgroundColor(font_frame_perview_field_clear_btn.getPressedBackgroundColor()); 
            font_frame_out_font_btn.setPressedBackgroundColor(font_frame_perview_field_clear_btn.getPressedBackgroundColor());
        }
    }
    
    /**
     * Sets the button border color in font menu
     */
    protected void set_font_frame_btn_border_color()
    {
        if(font_frame != null)
        {
            font_frame_perview_field_clear_btn.setBorderColor(color_arr[10]);
            font_frame_font_name_field_leftbtn.setBorderColor(font_frame_perview_field_clear_btn.getBorderColor());
            font_frame_font_name_field_rightbtn.setBorderColor(font_frame_perview_field_clear_btn.getBorderColor()); 
            font_frame_font_style_field_leftbtn.setBorderColor(font_frame_perview_field_clear_btn.getBorderColor());
            font_frame_font_style_field_rightbtn.setBorderColor(font_frame_perview_field_clear_btn.getBorderColor()); 
            font_frame_font_size_field_decbtn.setBorderColor(font_frame_perview_field_clear_btn.getBorderColor()); 
            font_frame_font_size_field_incbtn.setBorderColor(font_frame_perview_field_clear_btn.getBorderColor()); 
            font_frame_reset_font_btn.setBorderColor(font_frame_perview_field_clear_btn.getBorderColor()); 
            font_frame_exit_btn.setBorderColor(font_frame_perview_field_clear_btn.getBorderColor());
            font_frame_system_font_btn.setBorderColor(font_frame_perview_field_clear_btn.getBorderColor()); 
            font_frame_main_frame_bar_font_btn.setBorderColor(font_frame_perview_field_clear_btn.getBorderColor()); 
            font_frame_in_font_btn.setBorderColor(font_frame_perview_field_clear_btn.getBorderColor()); 
            font_frame_out_font_btn.setBorderColor(font_frame_perview_field_clear_btn.getBorderColor());
        }
    }

    /**
     * Repaint all the buttons in font menu
     */
    protected void font_frame_btn_repaint()
    {
        if(font_frame != null)
        {
            font_frame_perview_field_clear_btn.repaint();
            font_frame_font_name_field_leftbtn.repaint();
            font_frame_font_name_field_rightbtn.repaint();
            font_frame_font_style_field_leftbtn.repaint();
            font_frame_font_style_field_rightbtn.repaint();
            font_frame_font_size_field_decbtn.repaint();
            font_frame_font_size_field_incbtn.repaint();
            font_frame_reset_font_btn.repaint();
            font_frame_exit_btn.repaint();
            font_frame_system_font_btn.repaint();
            font_frame_main_frame_bar_font_btn.repaint();
            font_frame_in_font_btn.repaint();
            font_frame_out_font_btn.repaint();
        }
    }
    
    /**
     * Sets the button font color in font menu
     */
    protected void set_font_frame_btn_font_color()
    {
        if(font_frame != null)
        {
            font_frame_perview_field_clear_btn.setFontColor(color_arr[11]);
            font_frame_font_name_field_leftbtn.setFontColor(font_frame_perview_field_clear_btn.getFontColor());
            font_frame_font_name_field_rightbtn.setFontColor(font_frame_perview_field_clear_btn.getFontColor()); 
            font_frame_font_style_field_leftbtn.setFontColor(font_frame_perview_field_clear_btn.getFontColor());
            font_frame_font_style_field_rightbtn.setFontColor(font_frame_perview_field_clear_btn.getFontColor()); 
            font_frame_font_size_field_decbtn.setFontColor(font_frame_perview_field_clear_btn.getFontColor()); 
            font_frame_font_size_field_incbtn.setFontColor(font_frame_perview_field_clear_btn.getFontColor()); 
            font_frame_reset_font_btn.setFontColor(font_frame_perview_field_clear_btn.getFontColor()); 
            font_frame_exit_btn.setFontColor(font_frame_perview_field_clear_btn.getFontColor());
            font_frame_system_font_btn.setFontColor(font_frame_perview_field_clear_btn.getFontColor()); 
            font_frame_main_frame_bar_font_btn.setFontColor(font_frame_perview_field_clear_btn.getFontColor()); 
            font_frame_in_font_btn.setFontColor(font_frame_perview_field_clear_btn.getFontColor()); 
            font_frame_out_font_btn.setFontColor(font_frame_perview_field_clear_btn.getFontColor());
        }
    }

    /**
     * Sets the button disabled color in font menu
     */
    protected void set_font_frame_btn_disabled_color()
    {
        if(font_frame != null)
        {
            font_frame_perview_field_clear_btn.setDisabledBackgroundColor(color_arr[12]);
            font_frame_font_name_field_leftbtn.setDisabledBackgroundColor(font_frame_perview_field_clear_btn.getDisabledBackgroundColor());
            font_frame_font_name_field_rightbtn.setDisabledBackgroundColor(font_frame_perview_field_clear_btn.getDisabledBackgroundColor()); 
            font_frame_font_style_field_leftbtn.setDisabledBackgroundColor(font_frame_perview_field_clear_btn.getDisabledBackgroundColor());
            font_frame_font_style_field_rightbtn.setDisabledBackgroundColor(font_frame_perview_field_clear_btn.getDisabledBackgroundColor()); 
            font_frame_font_size_field_decbtn.setDisabledBackgroundColor(font_frame_perview_field_clear_btn.getDisabledBackgroundColor()); 
            font_frame_font_size_field_incbtn.setDisabledBackgroundColor(font_frame_perview_field_clear_btn.getDisabledBackgroundColor()); 
            font_frame_reset_font_btn.setDisabledBackgroundColor(font_frame_perview_field_clear_btn.getDisabledBackgroundColor()); 
            font_frame_exit_btn.setDisabledBackgroundColor(font_frame_perview_field_clear_btn.getDisabledBackgroundColor());
            font_frame_system_font_btn.setDisabledBackgroundColor(font_frame_perview_field_clear_btn.getDisabledBackgroundColor()); 
            font_frame_main_frame_bar_font_btn.setDisabledBackgroundColor(font_frame_perview_field_clear_btn.getDisabledBackgroundColor()); 
            font_frame_in_font_btn.setDisabledBackgroundColor(font_frame_perview_field_clear_btn.getDisabledBackgroundColor()); 
            font_frame_out_font_btn.setDisabledBackgroundColor(font_frame_perview_field_clear_btn.getDisabledBackgroundColor());
        }
    }

    /**
     * Set the bg color of textfields on the font menu
     */
    protected void set_font_frame_output_color()
    {
        if(font_frame != null)
        {
            font_frame_font_name_field.setBackground(color_arr[5]);
            font_frame_font_style_field.setBackground(font_frame_font_name_field.getBackground());
            font_frame_font_size_field.setBackground(font_frame_font_name_field.getBackground());
        }
    }

    /**
     * Set the bg color of the button on the font menu using current color scheme
     */
    protected void set_font_frame_system_font()
    {
        if(font_frame != null)
        {
            font_frame_reset_font_btn.setFont(font_arr[0]);
            font_frame_perview_field_clear_btn.setFont(font_frame_reset_font_btn.getFont());
            font_frame_font_name_field_leftbtn.setFont(font_frame_reset_font_btn.getFont());
            font_frame_font_name_field_rightbtn.setFont(font_frame_reset_font_btn.getFont());
            font_frame_font_style_field_leftbtn.setFont(font_frame_reset_font_btn.getFont());
            font_frame_font_style_field_rightbtn.setFont(font_frame_reset_font_btn.getFont());
            font_frame_font_size_field_decbtn.setFont(font_frame_reset_font_btn.getFont());
            font_frame_font_size_field_incbtn.setFont(font_frame_reset_font_btn.getFont());
            font_frame_exit_btn.setFont(font_frame_reset_font_btn.getFont());
            font_frame_main_frame_bar_font_btn.setFont(font_frame_reset_font_btn.getFont());
            font_frame_system_font_btn.setFont(font_frame_reset_font_btn.getFont());
            font_frame_in_font_btn.setFont(font_frame_reset_font_btn.getFont()); 
            font_frame_out_font_btn.setFont(font_frame_reset_font_btn.getFont());
        }
    }

    /**
     * Try to dispose the font menu
     */
    protected void dispose_font_frame() { if(font_frame != null) { font_frame.dispose(); font_frame = null; } }

    /**
     * Set the bg color of the font preview input textfield on the font menu
     */
    protected void set_font_frame_input_color() { if(font_frame != null) { font_frame_perview_field.setBackground(color_arr[13]); } }
    

    /**
     * Try to load user settings from the file, otherwise create a new save file
     */
    protected void load_save_setting()
    {        
        restore_color_settings();
        restore_font_settings();
        restore_decimal_precision_setting();
        restore_btn_layout_setting();

        //Create an input file, and set read error flag to false
        File in_file = new File("settings.txt");
        boolean read_error_flag = false;

        try
        {
            Scanner in = new Scanner(in_file);
            int case_state = 0; //0: Color Settings, 1: Font Settings, 2: Precision Settings, 3: Button Settings
            int count = 0; //Keeps track for the number of settings present under each case to update a specific array location
            String file_input_line = ""; //Each line extracted from the file

            while(in.hasNextLine())
            {
                file_input_line = in.nextLine();

                //If temp contains COLOR or it is blank, then move to the next line as it is just a heading
                if(file_input_line.isEmpty() || file_input_line.contains("COLOR")) { continue; }

                //If temp contains FONT or PRECISION, then move to the next line as it is just a heading after incrementing case_state and setting count to 0 (first location in the array)
                if(file_input_line.contains("FONT") || file_input_line.contains("PRECISION")|| file_input_line.contains("BUTTON")) 
                { 
                    ++case_state; 
                    count = 0; 
                    continue; 
                }

                //Retrive the color settings from the file
                if(case_state == 0) 
                { 
                    //Extract color RGB value
                    color_arr[count] = new Color(Integer.parseInt(file_input_line.substring(color_name[count].length() + 1))); 
                    ++count;
                }
                //Retrive font settings from the file
                else if(case_state == 1)
                {
                    //Extract font name, style, and size
                    String[] splitted_temp = file_input_line.split(" ");
                    String font_name = splitted_temp[1];
                    int font_style = Integer.parseInt(splitted_temp[2]); 
                    int font_size = Integer.parseInt(splitted_temp[3]);

                    //If the font size is greater than max allowed, then set current font size to max and issue a read error
                    if(font_size > max_font_size) 
                    {
                        font_size = max_font_size; 
                        read_error_flag = true;
                    }
                    //If the font size is smaller than min allowed, then set current font size to min and issue a read error
                    else if(font_size < min_font_size) 
                    {
                        font_size = min_font_size; 
                        read_error_flag = true;
                    }
                    
                    font_arr[count] = new Font(font_name, font_style, font_size);                        
                    ++count; 
                }
                //Retrive precision settings from the file
                else if(case_state == 2)
                {
                    //If the retrived percision is less than min percision allowed, then set current precision to min percision and issue a read error
                    if(file_input_line.length() < min_precision.length()) 
                    { 
                        file_input_line = min_precision; 
                        read_error_flag = true; 
                    }
                    //If the retrived percision is greater than max percision allowed, then set current precision to max percision and issue a read error
                    else if(file_input_line.length() > max_precision.length()) 
                    {
                        file_input_line = max_precision; 
                        read_error_flag = true; 
                    }
                    //Create a string precision to feed into DecimalFormat object
                    else 
                    {
                        String temp_precision = "0.";
                        for(int i = 0; i < file_input_line.split("\\.")[1].length(); ++i) { temp_precision += "0"; }
                        file_input_line = temp_precision;
                    }

                    //Set the precision of the application output
                    df = new DecimalFormat(file_input_line);
                }

                else
                {
                    int temp_measurement = Integer.parseInt(file_input_line.split(" ")[1]);

                    if(count == 0) 
                    {
                        if(temp_measurement < min_btn_arc_height) { temp_measurement = min_btn_arc_height; read_error_flag = true; }
                        else if(temp_measurement > max_btn_arc_height) { temp_measurement = max_btn_arc_height; read_error_flag = true; }
                        btn_arc_height = temp_measurement;
                        ++count;
                    }

                    else if(count == 1) 
                    {
                        if(temp_measurement < min_btn_arc_width) { temp_measurement = min_btn_arc_width; read_error_flag = true; }
                        else if(temp_measurement > max_btn_arc_width) { temp_measurement = max_btn_arc_width; read_error_flag = true; }
                        btn_arc_width = temp_measurement;
                        ++count;
                    }
                }
            }

            in.close();
        }
        //Set read error flag if there were any exceptions
        catch(Exception ex) { read_error_flag = true; }
        
        //Create a new settings file if there were any read errors
        if(read_error_flag) { create_save_setting_file(); }
    }//load_save_setting()

    /**
     * Restore button layout height and width
     */
    protected void restore_btn_layout_setting()
    {
        btn_arc_height = 20;
        btn_arc_width = 20;
    }

    /**
     * Restores system wide font scheme
     */
    protected void restore_font_settings() 
    { 
        font_arr = new Font[default_font_arr.length]; 
        for(int i = 0; i < default_font_arr.length; ++i) { font_arr[i] = new Font(default_font_arr[i].getAttributes()); } 
    }

    /**
     * Restores system wide color scheme
     */
    protected void restore_color_settings() 
    {  
        color_arr = new Color[default_color_arr.length]; 
        for(int i = 0; i < default_color_arr.length; ++i) { color_arr[i] = new Color(default_color_arr[i].getRGB()); } 
    }

    /**
     * Restores system decimal precision
     */
    protected void restore_decimal_precision_setting() { df = new DecimalFormat(default_df_precision_string); }


    /**
     * Save current user settings
     */
    protected void create_save_setting_file()
    {
        try
        {
            File out_file = new File("settings.txt");
            FileWriter fw = new FileWriter(out_file);

            //Output color settings to the file
            fw.write("COLOR\n");
            for(int i = 0; i < color_name.length; ++i) { fw.write(color_name[i] + " " + color_arr[i].getRGB() + "\n"); }
            
            //Output Font settings to the file
            fw.write("\nFONT\n");
            for(int i = 0; i < font_name.length; ++i) { fw.write(font_name[i] + " " + font_arr[i].getName() + " " + font_arr[i].getStyle() + " " + font_arr[i].getSize() + "\n"); }

            //Output precision settings to the file
            fw.write("\nPRECISION\n" + df.format(0));

            //Output button settings to the file
            fw.write("\nBUTTON\nbtn_arc_height " + btn_arc_height + "\nbtn_arc_width " + btn_arc_width);

            fw.close();
        }
        catch(Exception ex) {/*Unable to create the File*/}
    }//create_save_setting_file()


    /**
     * Changes background colors of various objects within the system
     */
    protected void ChooseColor()
    {
        //Dispose the Choose Color frame if it exists
        dispose_color_frame();

        //Create Choose Color frame and set attributes
        color_frame = new JFrame("Choose Color"); 
        color_frame.setIconImage(main_frame.getIconImage());
        color_frame.setLayout(new GridBagLayout());
        
        //Create buttons and field for Choose Color menu
        color_frame_main_frame_color_btn = new CustomJButton("Main Menu");
        color_frame_main_frame_bar_color_btn = new CustomJButton("Main Menu-Bar"); 
        color_frame_error_msg_color_btn = new CustomJButton("Error Message"); 
        color_frame_matrix_frame_color_btn = new CustomJButton("Matrix Menu"); 
        color_frame_matrix_oper_frame_color_btn = new CustomJButton("Matrix Operation Menu"); 
        color_frame_output_color_btn = new CustomJButton("Output Field"); 
        color_frame_calc_bg_color_btn = new CustomJButton("Calculator");
        color_frame_btn_bg_color_btn = new CustomJButton("Button Background"); 
        color_frame_btn_hover_color_btn = new CustomJButton("Button Hover"); 
        color_frame_btn_press_color_btn = new CustomJButton("Button Press"); 
        color_frame_btn_border_color_btn = new CustomJButton("Button Border"); 
        color_frame_btn_font_color_btn = new CustomJButton("Button Font");
        color_frame_btn_disabled_color_btn = new CustomJButton("Disabled Button");
        color_frame_input_text_field_color_btn = new CustomJButton("Input Field"); 
        color_frame_color_btn = new CustomJButton("Choose Color Menu"); 
        color_frame_font_frame_color_btn = new CustomJButton("Choose Font Menu"); 
        color_frame_precision_frame_color_btn = new CustomJButton("Choose Precision Menu"); 
        color_frame_button_layout_frame_color_btn = new CustomJButton("Choose Button Layout"); 
        color_frame_exit_btn = new CustomJButton("Save & Exit");
        color_frame_reset_color_btn = new CustomJButton("Reset");
        color_frame_total_field = new CustomJButton();
        color_frame_red_field = new CustomJButton("255"); 
        color_frame_green_field = new CustomJButton("255"); 
        color_frame_blue_field = new CustomJButton("255");

        color_frame_main_frame_color_btn.setInvertedFontColorFlag(true);
        color_frame_main_frame_bar_color_btn.setInvertedFontColorFlag(true);
        color_frame_error_msg_color_btn.setInvertedFontColorFlag(true);
        color_frame_matrix_frame_color_btn.setInvertedFontColorFlag(true);
        color_frame_matrix_oper_frame_color_btn.setInvertedFontColorFlag(true);
        color_frame_output_color_btn.setInvertedFontColorFlag(true);
        color_frame_calc_bg_color_btn.setInvertedFontColorFlag(true);
        color_frame_btn_bg_color_btn.setInvertedFontColorFlag(true);
        color_frame_btn_hover_color_btn.setInvertedFontColorFlag(true);
        color_frame_btn_press_color_btn.setInvertedFontColorFlag(true);
        color_frame_btn_border_color_btn.setInvertedFontColorFlag(true);
        color_frame_btn_font_color_btn.setInvertedFontColorFlag(true);
        color_frame_btn_disabled_color_btn.setInvertedFontColorFlag(true);
        color_frame_input_text_field_color_btn.setInvertedFontColorFlag(true);
        color_frame_color_btn.setInvertedFontColorFlag(true);
        color_frame_font_frame_color_btn.setInvertedFontColorFlag(true);
        color_frame_precision_frame_color_btn.setInvertedFontColorFlag(true);
        color_frame_button_layout_frame_color_btn.setInvertedFontColorFlag(true);

        //Sets red, green, blue, and total (red + green + blue) field with appropriate settings
        color_frame_red_field.setDisabledBackgroundColor(Color.RED); 
        color_frame_red_field.setEnabled(false); 
        color_frame_red_field.setHorizontalAlignment(default_font_alignment);
        color_frame_red_field.setInvertedFontColorFlag(true);
        color_frame_green_field.setDisabledBackgroundColor(Color.GREEN); 
        color_frame_green_field.setEnabled(false); 
        color_frame_green_field.setHorizontalAlignment(default_font_alignment); 
        color_frame_green_field.setInvertedFontColorFlag(true);
        color_frame_blue_field.setDisabledBackgroundColor(Color.BLUE); 
        color_frame_blue_field.setEnabled(false); 
        color_frame_blue_field.setHorizontalAlignment(default_font_alignment); 
        color_frame_blue_field.setInvertedFontColorFlag(true);
        color_frame_total_field.setDisabledBackgroundColor(Color.WHITE); 
        color_frame_total_field.setEnabled(false);

        //Set the bg color and font of every component
        set_color_frame_color_scheme_btn_color();

        //If the button color is not set to default, then set the bg color of the button
        set_color_frame_btn_color();

        //Create red, blue, and green sliders to increase the intensity of each color component respectively, and set their font style
        color_frame_red_slider = new JSlider(JSlider.VERTICAL, 0, 255, 255); 
        color_frame_green_slider = new JSlider(JSlider.VERTICAL, 0, 255, 255); 
        color_frame_blue_slider = new JSlider(JSlider.VERTICAL, 0, 255, 255);
        set_color_frame_system_font(); 
        
        //Allows user to change the red color intensity
        color_frame_red_slider.addChangeListener(new ChangeListener() 
        { 
            @Override public void stateChanged(ChangeEvent e) 
            { 
                //Set the red field bg color and text to the current red slider value
                color_frame_red_field.setDisabledBackgroundColor(new Color(((JSlider)e.getSource()).getValue(),0,0)); 
                color_frame_red_field.setText(Integer.toString(((JSlider)e.getSource()).getValue()));
                //Set the bg color of total field with current red, green, and blue values
                color_frame_total_field.setDisabledBackgroundColor(new Color(((JSlider)e.getSource()).getValue(), color_frame_total_field.getDisabledBackgroundColor().getGreen(), 
                    color_frame_total_field.getDisabledBackgroundColor().getBlue()));
                color_frame_total_field.repaint();
            } 
        });

        //Allows user to change the green color intensity
        color_frame_green_slider.addChangeListener(new ChangeListener() 
        { 
            @Override public void stateChanged(ChangeEvent e) 
            { 
                //Set the green field bg color and text to the current green slider value
                color_frame_green_field.setDisabledBackgroundColor(new Color(0,((JSlider)e.getSource()).getValue(),0)); 
                color_frame_green_field.setText(Integer.toString(((JSlider)e.getSource()).getValue()));
                //Set the bg color of total field with current red, green, and blue values
                color_frame_total_field.setDisabledBackgroundColor(new Color(color_frame_total_field.getDisabledBackgroundColor().getRed(), ((JSlider)e.getSource()).getValue(), 
                    color_frame_total_field.getDisabledBackgroundColor().getBlue()));
                color_frame_total_field.repaint();
            } 
        });

        //Set the bg color of total field with current red, green, and blue values
        color_frame_blue_slider.addChangeListener(new ChangeListener() 
        { 
            @Override public void stateChanged(ChangeEvent e) 
            { 
                //Set the blue field bg color and text to the current blue slider value
                color_frame_blue_field.setDisabledBackgroundColor(new Color(0,0,((JSlider)e.getSource()).getValue())); 
                color_frame_blue_field.setText(Integer.toString(((JSlider)e.getSource()).getValue()));
                //Set the bg color of total field with current red, green, and blue values
                color_frame_total_field.setDisabledBackgroundColor(new Color(color_frame_total_field.getDisabledBackgroundColor().getRed(), 
                    color_frame_total_field.getDisabledBackgroundColor().getGreen(), ((JSlider)e.getSource()).getValue())); 
                color_frame_total_field.repaint();
            } 
        });

        //Dispose the Choose Color menu on click and save the settings to the file
        color_frame_exit_btn.addActionListener(new ActionListener() 
        { 
            @Override 
            public void actionPerformed(ActionEvent e) 
            { 
                dispose_color_frame();
                create_save_setting_file(); 
            } 
        });

        //Change bg color of Main menu frame
        color_frame_main_frame_color_btn.addActionListener(new ActionListener()
        { 
            @Override 
            public void actionPerformed(ActionEvent e) 
            { 
                color_arr[0] = color_frame_total_field.getDisabledBackgroundColor(); 
                //Change color of Main Menu color button in Choose Color menu
                color_frame_main_frame_color_btn.setBackground(color_arr[0]);
                //Change the Main frame bg color if it exists
                set_main_frame_color();
            } 
        });

        //Change bg color of the main menu bar
        color_frame_main_frame_bar_color_btn.addActionListener(new ActionListener()
        { 
            @Override 
            public void actionPerformed(ActionEvent e) 
            { 
                color_arr[1] = color_frame_total_field.getDisabledBackgroundColor();
                //Change Main Menu Bar color button in Choose Color menu
                color_frame_main_frame_bar_color_btn.setBackground(color_arr[1]);
                //Try to change the bg color of menu bar, menu, and menu items in the Main frame if it exists
                set_main_frame_bar_color();
            } 
        });

        //Change bg color of the error message field
        color_frame_error_msg_color_btn.addActionListener(new ActionListener()
        { 
            @Override 
            public void actionPerformed(ActionEvent e) 
            { 
                color_arr[2] = color_frame_total_field.getDisabledBackgroundColor(); 
                //Change bg color of error message field button in Choose Color menu
                color_frame_error_msg_color_btn.setBackground(color_arr[2]);
                //Change error message field color in Matrix frame
                set_matrix_frame_err_msg_color();
                //Change error message field color in Matrix Operation frame
                set_matrix_oper_frame_err_msg_color();
                //Change error message field color in Calculator frame
                set_cal_frame_error_msg_color();
            } 
        });

        //Change bg color of Matrix Menu frame
        color_frame_matrix_frame_color_btn.addActionListener(new ActionListener()
        { 
            @Override 
            public void actionPerformed(ActionEvent e) 
            { 
                color_arr[3] = color_frame_total_field.getDisabledBackgroundColor();
                //Change bg color of Marix Menu color button in Choose Color menu
                color_frame_matrix_frame_color_btn.setBackground(color_arr[3]);
                //Change bg color of Matrix menu
                set_matrix_frame_bg_color(); 
                set_matrix_frame_label_color();
            } 
        });

        //Change bg color of Matrix Operation
        color_frame_matrix_oper_frame_color_btn.addActionListener(new ActionListener()
        { 
            @Override 
            public void actionPerformed(ActionEvent e) 
            { 
                color_arr[4] = color_frame_total_field.getDisabledBackgroundColor();
                //Change bg color of Marix Operation Menu color button in Choose Color menu
                color_frame_matrix_oper_frame_color_btn.setBackground(color_arr[4]);
                //Change bg color of Marix Operation menu
                set_matrix_oper_frame_bg_color();
            } 
        });

        //Change bg color of output fields
        color_frame_output_color_btn.addActionListener(new ActionListener()
        { 
            @Override 
            public void actionPerformed(ActionEvent e)
            { 
                color_arr[5] = color_frame_total_field.getDisabledBackgroundColor();
                //Change bg color of output color button in Choose Color menu
                color_frame_output_color_btn.setBackground(color_arr[5]);

                //Change bg color of output fields in Matrix Operation menu
                try
                {
                    for(int i = 0; i < matrix_oper_frame_out_field_arr.length; ++i)
                    { 
                        for(int j = 0; j < matrix_oper_frame_out_field_arr[0].length; ++j)
                        {
                            matrix_oper_frame_out_field_arr[i][j].setBackground(color_arr[5]);
                        } 
                    }
                } catch(Exception ex){/*Matrix Operation frame does not exist*/} 
                
                //Change bg color of output fields in Choose Font menu
                set_font_frame_output_color();
                
                //Change bg color of output fields in Choose Precision menu
                set_precision_frame_output_color();
                
                //Change bg color of output fields in Choose Button Layout menu
                set_button_layout_frame_output_color();
            }
        });

        //Change bg color of Calculator frame
        color_frame_calc_bg_color_btn.addActionListener(new ActionListener()
        { 
            @Override 
            public void actionPerformed(ActionEvent e) 
            { 
                color_arr[6] = color_frame_total_field.getDisabledBackgroundColor();
                //Change bg color of calculator color button in Choose Color menu
                color_frame_calc_bg_color_btn.setBackground(color_arr[6]);
                //Change bg color of Calculator menu
                set_cal_frame_bg_color(); 
                set_cal_frame_label_color();
            }
        });

        //Change bg color of each button within the application
        color_frame_btn_bg_color_btn.addActionListener(new ActionListener() 
        { 
            @Override 
            public void actionPerformed(ActionEvent e) 
            {
                //Set bg color of button to current color of button field
                color_arr[7] = color_frame_total_field.getDisabledBackgroundColor();

                //Change bg color of the buttons within Choose Color frame
                set_color_frame_btn_bg_color();

                //Change bg color of the buttons of Main frame
                set_main_frame_button_bg_color();

                //Change bg color of the buttons of Matrix frame
                set_matrix_frame_btn_bg_color();
                
                //Change bg color of the buttons of Calculator frame
                set_cal_frame_btn_bg_color();
                
                //Change bg color of the buttons of Choose Font frame
                set_font_frame_btn_bg_color();
                
                //Change bg color of the buttons of Choose Precision frame
                set_precision_frame_btn_bg_color();
                
                //Change bg color of the buttons of Matrix Operation frame
                set_matrix_oper_frame_btn_bg_color();
                
                //Change bg color of the buttons of Choose Button Layout frame
                set_button_layout_frame_btn_bg_color();
            }
        });

        color_frame_btn_hover_color_btn.addActionListener(new ActionListener() 
        { 
            @Override 
            public void actionPerformed(ActionEvent e) 
            {
                //Set bg color of button to current color of button field
                color_arr[8] = color_frame_total_field.getDisabledBackgroundColor();
                color_frame_btn_hover_color_btn.setBackground(color_arr[8]);

                //Change bg color of the buttons within Choose Color frame
                set_color_frame_btn_hover_color();

                //Change bg color of the buttons of Main frame
                set_main_frame_button_hover_color();

                //Change bg color of the buttons of Matrix frame
                set_matrix_frame_btn_hover_color();
                
                //Change bg color of the buttons of Calculator frame
                set_cal_frame_btn_hover_color();
                
                //Change bg color of the buttons of Choose Font frame
                set_font_frame_btn_hover_color();
                
                //Change bg color of the buttons of Choose Precision frame
                set_precision_frame_btn_hover_color();
                
                //Change bg color of the buttons of Matrix Operation frame
                set_matrix_oper_frame_btn_hover_color();
                
                //Change bg color of the buttons of Choose Button Layout frame
                set_button_layout_frame_btn_hover_color();
            }
        });

        color_frame_btn_press_color_btn.addActionListener(new ActionListener() 
        { 
            @Override 
            public void actionPerformed(ActionEvent e) 
            {
                //Set bg color of button to current color of button field
                color_arr[9] = color_frame_total_field.getDisabledBackgroundColor();
                color_frame_btn_press_color_btn.setBackground(color_arr[9]);

                //Change bg color of the buttons within Choose Color frame
                set_color_frame_btn_press_color();

                //Change bg color of the buttons of Main frame
                set_main_frame_button_press_color();

                //Change bg color of the buttons of Matrix frame
                set_matrix_frame_btn_press_color();
                
                //Change bg color of the buttons of Calculator frame
                set_cal_frame_btn_press_color();
                
                //Change bg color of the buttons of Choose Font frame
                set_font_frame_btn_press_color();
                
                //Change bg color of the buttons of Choose Precision frame
                set_precision_frame_btn_press_color();
                
                //Change bg color of the buttons of Matrix Operation frame
                set_matrix_oper_frame_btn_press_color();
                
                //Change bg color of the buttons of Choose Button Layout frame
                set_button_layout_frame_btn_press_color();
            }
        });

        color_frame_btn_border_color_btn.addActionListener(new ActionListener() 
        { 
            @Override 
            public void actionPerformed(ActionEvent e) 
            {
                //Set bg color of button to current color of button field
                color_arr[10] = color_frame_total_field.getDisabledBackgroundColor();
                color_frame_btn_border_color_btn.setBackground(color_arr[10]);

                //Change bg color of the buttons within Choose Color frame
                set_color_frame_btn_border_color();
                color_frame_btn_repaint();

                //Change bg color of the buttons of Main frame
                set_main_frame_button_border_color(); 
                main_frame_button_repaint();

                //Change bg color of the buttons of Matrix frame
                set_matrix_frame_btn_border_color(); 
                matrix_frame_btn_repaint();
                
                //Change bg color of the buttons of Calculator frame
                set_cal_frame_btn_border_color(); 
                cal_frame_btn_repaint();
                
                //Change bg color of the buttons of Choose Font frame
                set_font_frame_btn_border_color(); 
                font_frame_btn_repaint();
                
                //Change bg color of the buttons of Choose Precision frame
                set_precision_frame_btn_border_color(); 
                precision_frame_btn_repaint();
                
                //Change bg color of the buttons of Matrix Operation frame
                set_matrix_oper_frame_btn_border_color(); 
                matrix_oper_frame_btn_repaint();
                
                //Change bg color of the buttons of Choose Button Layout frame
                set_button_layout_frame_btn_border_color(); 
                button_layout_frame_btn_repaint();
            }
        });

        color_frame_btn_font_color_btn.addActionListener(new ActionListener() 
        { 
            @Override 
            public void actionPerformed(ActionEvent e) 
            {
                //Set bg color of button to current color of button field
                color_arr[11] = color_frame_total_field.getDisabledBackgroundColor();
                color_frame_btn_font_color_btn.setBackground(color_arr[11]);

                //Change bg color of the buttons within Choose Color frame
                set_color_frame_btn_font_color();
                color_frame_btn_repaint();

                //Change bg color of the buttons of Main frame
                set_main_frame_button_font_color(); 
                main_frame_button_repaint();

                //Change bg color of the buttons of Matrix frame
                set_matrix_frame_btn_font_color(); 
                matrix_frame_btn_repaint();
                
                //Change bg color of the buttons of Calculator frame
                set_cal_frame_btn_font_color(); 
                cal_frame_btn_repaint();
                
                //Change bg color of the buttons of Choose Font frame
                set_font_frame_btn_font_color(); 
                font_frame_btn_repaint();
                
                //Change bg color of the buttons of Choose Precision frame
                set_precision_frame_btn_font_color(); 
                precision_frame_btn_repaint();
                
                //Change bg color of the buttons of Matrix Operation frame
                set_matrix_oper_frame_btn_font_color(); 
                matrix_oper_frame_btn_repaint();
                
                //Change bg color of the buttons of Choose Button Layout frame
                set_button_layout_frame_btn_font_color(); 
                button_layout_frame_btn_repaint();
            }
        });

        color_frame_btn_disabled_color_btn.addActionListener(new ActionListener() 
        { 
            @Override 
            public void actionPerformed(ActionEvent e) 
            {
                //Set bg color of button to current color of button field
                color_arr[12] = color_frame_total_field.getDisabledBackgroundColor();
                color_frame_btn_disabled_color_btn.setBackground(color_arr[12]);

                //Change bg color of the buttons within Choose Color frame
                set_color_frame_btn_disabled_color();
                color_frame_btn_repaint();

                //Change bg color of the buttons of Main frame
                set_main_frame_button_disabled_color(); 
                main_frame_button_repaint();

                //Change bg color of the buttons of Matrix frame
                set_matrix_frame_btn_disabled_color(); 
                matrix_frame_btn_repaint();
                
                //Change bg color of the buttons of Calculator frame
                set_cal_frame_btn_disabled_color(); 
                cal_frame_btn_repaint();
                
                //Change bg color of the buttons of Choose Font frame
                set_font_frame_btn_disabled_color(); 
                font_frame_btn_repaint();
                
                //Change bg color of the buttons of Choose Precision frame
                set_precision_frame_btn_disabled_color(); 
                precision_frame_btn_repaint();
                
                //Change bg color of the buttons of Matrix Operation frame
                set_matrix_oper_frame_btn_disabled_color(); 
                matrix_oper_frame_btn_repaint();
                
                //Change bg color of the buttons of Choose Precision frame
                set_button_layout_frame_btn_disabled_color(); 
                button_layout_frame_btn_repaint();
            }
        });

        //Change bg color of each input field within the application
        color_frame_input_text_field_color_btn.addActionListener(new ActionListener()
        { 
            @Override public void actionPerformed(ActionEvent e) 
            {
                //Change bg color of input color button in Choose Color
                color_arr[13] = color_frame_total_field.getDisabledBackgroundColor(); 
                color_frame_input_text_field_color_btn.setBackground(color_arr[13]); 

                //Change bg color of the input fields in Choose Font frame
                set_font_frame_input_color();
                
                //Change bg color of the input fields in Matrix frame
                set_matrix_frame_input_color();
                
                //Change bg color of the input fields in Calculator frame
                set_cal_frame_input_text_field_color();
                
                //Change bg color of the input fields in Matrix Operation frame
                try
                {
                    for(int i = 0; i < matrix_oper_frame_user_field_arr.length; ++i)
                    { 
                        for(int j = 0; j < matrix_oper_frame_user_field_arr[0].length; ++j)
                        {
                            matrix_oper_frame_user_field_arr[i][j].setBackground(color_arr[13]);
                        }
                    }
                } catch(Exception ex){/*Matrix Operation frame does not exist*/}
            }
        });

        //Change bg color of color menu frame
        color_frame_color_btn.addActionListener(new ActionListener()
        { 
            @Override 
            public void actionPerformed(ActionEvent e) 
            { 
                color_arr[14] = color_frame_total_field.getDisabledBackgroundColor(); 
                //Change color of Main Menu color button in Choose Color menu
                color_frame_color_btn.setBackground(color_arr[14]);
                //Change the Main frame bg color if it exists
                set_color_frame_color(); 
                set_color_frame_slider_color();
            } 
        });

        //Change bg color of Main menu frame
        color_frame_font_frame_color_btn.addActionListener(new ActionListener()
        { 
            @Override 
            public void actionPerformed(ActionEvent e) 
            { 
                color_arr[15] = color_frame_total_field.getDisabledBackgroundColor(); 
                //Change color of Main Menu color button in Choose Color menu
                color_frame_font_frame_color_btn.setBackground(color_arr[15]);
                //Change the Main frame bg color if it exists
                set_font_frame_color(); 
                set_font_frame_label_color();
            } 
        });

        //Change bg color of Main menu frame
        color_frame_precision_frame_color_btn.addActionListener(new ActionListener()
        { 
            @Override 
            public void actionPerformed(ActionEvent e) 
            { 
                color_arr[16] = color_frame_total_field.getDisabledBackgroundColor(); 
                //Change color of Main Menu color button in Choose Color menu
                color_frame_precision_frame_color_btn.setBackground(color_arr[16]);
                //Change the Main frame bg color if it exists
                set_precision_frame_color(); 
                set_precision_frame_label_color();
            } 
        });

        //Change bg color of Main menu frame
        color_frame_button_layout_frame_color_btn.addActionListener(new ActionListener()
        { 
            @Override 
            public void actionPerformed(ActionEvent e) 
            { 
                color_arr[17] = color_frame_total_field.getDisabledBackgroundColor(); 
                //Change color of Button Layout Frame color button in Choose Color menu
                color_frame_button_layout_frame_color_btn.setBackground(color_arr[17]);
                //Change the Main frame bg color if it exists
                set_button_layout_frame_color(); 
                set_button_layout_frame_label_color();
            } 
        });

        //Reset the color settings of the application, and save the settings
        color_frame_reset_color_btn.addActionListener(new ActionListener()
        { 
            @Override public void actionPerformed(ActionEvent e) 
            { 
                //Copy all the default color profile to current color profile
                restore_color_settings();

                //Reset all the button bg color in Choose Color menu
                set_color_frame_color_scheme_btn_color();
                set_color_frame_btn_color();
                color_frame_btn_repaint();
                set_color_frame_color();
                set_color_frame_slider_color();
                
                //Reset all bg color in Main frame
                set_main_frame_color();
                set_main_frame_bar_color();
                set_main_frame_button_color();
                main_frame_button_repaint();

                //Reset all bg color in Matrix frame
                set_matrix_frame_err_msg_color();
                set_matrix_frame_bg_color();
                set_matrix_frame_btn_color();
                set_matrix_frame_input_color();
                set_matrix_frame_label_color();
                matrix_frame_btn_repaint();

                //Reset all bg color in Matrix Operation frame
                set_matrix_oper_frame_err_msg_color();
                set_matrix_oper_frame_bg_color();
                set_matrix_oper_frame_btn_color();
                matrix_oper_frame_btn_repaint();

                //Reset all bg color in Calculator frame
                set_cal_frame_error_msg_color();
                set_cal_frame_bg_color();
                set_cal_frame_btn_color();
                set_cal_frame_input_text_field_color();
                set_cal_frame_label_color();
                cal_frame_btn_repaint();
                            
                //Reset all bg color in Choose Precision frame
                set_precision_frame_btn_color();
                set_precision_frame_output_color();
                precision_frame_btn_repaint();
                set_precision_frame_color();
                set_precision_frame_label_color();
                
                //Reset all bg color in Choose Font frame
                set_font_frame_output_color();
                set_font_frame_btn_color();
                set_font_frame_input_color();
                font_frame_btn_repaint();
                set_font_frame_color();
                set_font_frame_label_color();

                //Reset all bg color in Choose Button Layout frame
                set_button_layout_frame_output_color();
                set_button_layout_frame_btn_color();
                button_layout_frame_btn_repaint();
                set_button_layout_frame_color();
                set_button_layout_frame_label_color();
                
                //Reset all bg color in Matrix Operation frame     
                try
                {
                    for(int i = 0; i < matrix_oper_frame_out_field_arr.length; ++i)
                    { 
                        for(int j = 0; j < matrix_oper_frame_out_field_arr[0].length; ++j)
                        {
                            matrix_oper_frame_out_field_arr[i][j].setBackground(color_arr[5]); 
                            matrix_oper_frame_user_field_arr[i][j].setBackground(color_arr[13]);
                        }
                    }
                } catch(Exception ex){/*Matrix Operation frame does not exist*/}
                
                //Save all the changes to the file
                create_save_setting_file();
            } 
        });

        //Set various attributes of red, green, and blue slider
        color_frame_red_slider.setMajorTickSpacing(50);
        color_frame_green_slider.setMajorTickSpacing(50);
        color_frame_blue_slider.setMajorTickSpacing(50);

        color_frame_red_slider.setMinorTickSpacing(10);
        color_frame_green_slider.setMinorTickSpacing(10);
        color_frame_blue_slider.setMinorTickSpacing(10);

        color_frame_red_slider.setPaintTicks(true);
        color_frame_green_slider.setPaintTicks(true);
        color_frame_blue_slider.setPaintTicks(true);

        color_frame_red_slider.setPaintLabels(true);
        color_frame_green_slider.setPaintLabels(true);
        color_frame_blue_slider.setPaintLabels(true);

        color_frame_red_slider.setPreferredSize(new Dimension(color_frame_red_slider.getPreferredSize().width+30, color_frame_main_frame_color_btn.getPreferredSize().height*color_arr.length));
        color_frame_green_slider.setPreferredSize(color_frame_red_slider.getPreferredSize());
        color_frame_blue_slider.setPreferredSize(color_frame_red_slider.getPreferredSize());

        GridBagConstraints color_frame_constr = new GridBagConstraints();
        color_frame_constr.fill = GridBagConstraints.HORIZONTAL;
        
        //Add sliders to Choose Color frame
        color_frame_constr.weightx = 1; 
        color_frame_constr.gridheight = color_arr.length; 
        color_frame_constr.insets = new Insets(0,10,0,0); 
        color_frame.add(color_frame_red_slider, color_frame_constr); 
        color_frame_constr.gridx = 1; 
        color_frame_constr.insets = new Insets(0,0,0,0); 
        color_frame.add(color_frame_green_slider, color_frame_constr); 
        color_frame_constr.gridx = 2; 
        color_frame.add(color_frame_blue_slider, color_frame_constr);

        //Add red, green, blue, and total color fields to Choose Color menu
        color_frame_constr.gridx = 0; 
        color_frame_constr.gridy = color_frame_constr.gridheight; 
        color_frame_constr.ipady = 0; 
        color_frame_constr.weighty = 2; 
        color_frame_constr.gridheight = 1; 
        color_frame_constr.insets = new Insets(10,5,2,2); 
        color_frame.add(color_frame_red_field, color_frame_constr);
        color_frame_constr.gridx = 1; 
        color_frame_constr.insets = new Insets(10,2,2,2); 
        color_frame.add(color_frame_green_field, color_frame_constr);
        color_frame_constr.gridx = 2; 
        color_frame_constr.insets = new Insets(10,2,2,2); 
        color_frame.add(color_frame_blue_field, color_frame_constr);
        color_frame_constr.gridx = 3; 
        color_frame_constr.insets = new Insets(10,2,2,5);
        color_frame_total_field.setPreferredSize(new Dimension(color_frame_matrix_oper_frame_color_btn.getPreferredSize().width, color_frame_blue_field.getPreferredSize().height)); 
        color_frame.add(color_frame_total_field, color_frame_constr);

        //Add various buttons to Choose Color frame
        color_frame_constr.weightx = 0.1; 
        color_frame_constr.weighty = 0.1;
        color_frame_constr.insets = new Insets(5,10,1,5); 
        color_frame_constr.gridy = 0; 
        color_frame.add(color_frame_main_frame_color_btn, color_frame_constr);
        color_frame_constr.insets = new Insets(1,10,1,5); 
        color_frame_constr.gridy = 1; 
        color_frame.add(color_frame_main_frame_bar_color_btn, color_frame_constr);
        color_frame_constr.gridy = 2; 
        color_frame.add(color_frame_error_msg_color_btn, color_frame_constr);
        color_frame_constr.gridy = 3; 
        color_frame.add(color_frame_matrix_frame_color_btn, color_frame_constr);
        color_frame_constr.gridy = 4; 
        color_frame.add(color_frame_matrix_oper_frame_color_btn, color_frame_constr);
        color_frame_constr.gridy = 5; 
        color_frame.add(color_frame_output_color_btn, color_frame_constr);
        color_frame_constr.gridy = 6; 
        color_frame.add(color_frame_calc_bg_color_btn, color_frame_constr);
        color_frame_constr.gridy = 7; 
        color_frame.add(color_frame_btn_bg_color_btn, color_frame_constr);
        color_frame_constr.gridy = 8; 
        color_frame.add(color_frame_btn_hover_color_btn, color_frame_constr);
        color_frame_constr.gridy = 9; 
        color_frame.add(color_frame_btn_press_color_btn, color_frame_constr);
        color_frame_constr.gridy = 10; 
        color_frame.add(color_frame_btn_border_color_btn, color_frame_constr);
        color_frame_constr.gridy = 11; 
        color_frame.add(color_frame_btn_font_color_btn, color_frame_constr);
        color_frame_constr.gridy = 12; 
        color_frame.add(color_frame_btn_disabled_color_btn, color_frame_constr);
        color_frame_constr.gridy = 13; 
        color_frame.add(color_frame_input_text_field_color_btn, color_frame_constr);
        color_frame_constr.gridy = 14; 
        color_frame.add(color_frame_color_btn, color_frame_constr);
        color_frame_constr.gridy = 15; 
        color_frame.add(color_frame_font_frame_color_btn, color_frame_constr);
        color_frame_constr.gridy = 16; 
        color_frame.add(color_frame_precision_frame_color_btn, color_frame_constr);
        color_frame_constr.gridy = 17; 
        color_frame.add(color_frame_button_layout_frame_color_btn, color_frame_constr);
        color_frame_constr.gridy += 2; 
        color_frame_exit_btn.setPreferredSize(color_frame_reset_color_btn.getPreferredSize());
        color_frame_constr.insets = new Insets(10,0,10,20); 
        color_frame.add(color_frame_exit_btn, color_frame_constr);
        color_frame_constr.gridx = 0; 
        color_frame_constr.gridwidth = 3; 
        color_frame_constr.insets = new Insets(10,20,10,50); 
        color_frame.add(color_frame_reset_color_btn, color_frame_constr);

        //Deploy the frame to the user
        set_color_frame_color();
        set_color_frame_slider_color();
        color_frame.pack();
        color_frame.setLocationRelativeTo(null);
        color_frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        color_frame.addWindowListener(new WindowListener()
        {
            @Override public void windowClosing(WindowEvent e) { dispose_color_frame(); }
            @Override public void windowOpened(WindowEvent e) {}
            @Override public void windowClosed(WindowEvent e) {}
            @Override public void windowIconified(WindowEvent e) {}
            @Override public void windowDeiconified(WindowEvent e) {}
            @Override public void windowActivated(WindowEvent e) {}
            @Override public void windowDeactivated(WindowEvent e) {}
        });
        color_frame.setResizable(false);
        color_frame.setVisible(true);
    }//ChooseColor()
    
    /**
     * Refresh the color frame while keeping user data intact
     */
    protected void refresh_color_frame()
    {
        if(color_frame != null)
        {
            int color_frame_red_field_num = Integer.parseInt(color_frame_red_field.getText());
            int color_frame_green_field_num = Integer.parseInt(color_frame_green_field.getText());
            int color_frame_blue_field_num = Integer.parseInt(color_frame_blue_field.getText());
            ChooseColor();
            color_frame_red_slider.setValue(color_frame_red_field_num);
            color_frame_green_slider.setValue(color_frame_green_field_num);
            color_frame_blue_slider.setValue(color_frame_blue_field_num);
        }
    }

    /**
     * Set the bg color of the color menu frame
     */
    protected void set_color_frame_color() 
    {
        if(color_frame != null)
        {
            color_frame.getContentPane().setBackground(color_arr[14]); 
            color_frame_red_slider.setBackground(color_frame.getContentPane().getBackground());
            color_frame_green_slider.setBackground(color_frame.getContentPane().getBackground());
            color_frame_blue_slider.setBackground(color_frame.getContentPane().getBackground());
        }
    }

    /**
     * Sets the bg color of the sliders in the choose color menu
     */
    protected void set_color_frame_slider_color()
    {
        if(color_frame != null)
        {
            try { color_frame_red_slider.setForeground(color_frame_main_frame_color_btn.getInvertedColor(color_frame.getContentPane().getBackground(), 100, 155, false)); } 
            catch(Exception ex) { return; }
            color_frame_green_slider.setForeground(color_frame_red_slider.getForeground());
            color_frame_blue_slider.setForeground(color_frame_red_slider.getForeground());
        }
    }

    /**
     * Set the font of buttons, textfields, and sliders on the color menu
     */
    protected void set_color_frame_system_font()
    {
        if(color_frame != null)
        {
            color_frame_red_field.setFont(font_arr[0]);
            color_frame_green_field.setFont(color_frame_red_field.getFont());
            color_frame_blue_field.setFont(color_frame_red_field.getFont());
            color_frame_main_frame_color_btn.setFont(color_frame_red_field.getFont()); 
            color_frame_main_frame_bar_color_btn.setFont(color_frame_red_field.getFont());
            color_frame_error_msg_color_btn.setFont(color_frame_red_field.getFont());
            color_frame_matrix_frame_color_btn.setFont(color_frame_red_field.getFont());
            color_frame_matrix_oper_frame_color_btn.setFont(color_frame_red_field.getFont());
            color_frame_output_color_btn.setFont(color_frame_red_field.getFont());
            color_frame_calc_bg_color_btn.setFont(color_frame_red_field.getFont()); 
            color_frame_input_text_field_color_btn.setFont(color_frame_red_field.getFont());
            color_frame_color_btn.setFont(color_frame_red_field.getFont());
            color_frame_font_frame_color_btn.setFont(color_frame_red_field.getFont());
            color_frame_precision_frame_color_btn.setFont(color_frame_red_field.getFont());
            color_frame_button_layout_frame_color_btn.setFont(color_frame_red_field.getFont());
            color_frame_exit_btn.setFont(color_frame_red_field.getFont()); 
            color_frame_reset_color_btn.setFont(color_frame_red_field.getFont()); 
            color_frame_btn_bg_color_btn.setFont(color_frame_red_field.getFont());
            color_frame_btn_hover_color_btn.setFont(color_frame_red_field.getFont());
            color_frame_btn_press_color_btn.setFont(color_frame_red_field.getFont());
            color_frame_btn_border_color_btn.setFont(color_frame_red_field.getFont());
            color_frame_btn_disabled_color_btn.setFont(color_frame_red_field.getFont());
            color_frame_btn_font_color_btn.setFont(color_frame_red_field.getFont());
            color_frame_blue_slider.setFont(color_frame_red_field.getFont());
            color_frame_green_slider.setFont(color_frame_red_field.getFont());
            color_frame_red_slider.setFont(color_frame_red_field.getFont());
        }
    }

    /**
     * Set the button bg color on the color menu
     */
    protected void set_color_frame_btn_color()
    {
        if(color_frame != null)
        {
            set_color_frame_btn_bg_color();
            set_color_frame_btn_hover_color();
            set_color_frame_btn_press_color();
            set_color_frame_btn_border_color();
            set_color_frame_btn_font_color();
            set_color_frame_btn_disabled_color();
            set_color_frame_btn_layout();
        }
    }

    /**
     * Set the color frame button corner cut-out
     */
    protected void set_color_frame_btn_layout()
    {
        if(color_frame!= null) 
        {
            set_color_frame_btn_arcHeight_layout();
            set_color_frame_btn_arcWidth_layout();
        }
    }

    /**
     * Set the color frame button corner arc height
     */
    protected void set_color_frame_btn_arcHeight_layout()
    {
        if(color_frame != null) 
        {
            color_frame_btn_bg_color_btn.setArcHeight(btn_arc_height);
            color_frame_btn_hover_color_btn.setArcHeight(color_frame_btn_bg_color_btn.getArcHeight());
            color_frame_exit_btn.setArcHeight(color_frame_btn_bg_color_btn.getArcHeight());
            color_frame_reset_color_btn.setArcHeight(color_frame_btn_bg_color_btn.getArcHeight()); 
            color_frame_main_frame_color_btn.setArcHeight(color_frame_btn_bg_color_btn.getArcHeight());
            color_frame_main_frame_bar_color_btn.setArcHeight(color_frame_btn_bg_color_btn.getArcHeight());
            color_frame_error_msg_color_btn.setArcHeight(color_frame_btn_bg_color_btn.getArcHeight());
            color_frame_matrix_frame_color_btn.setArcHeight(color_frame_btn_bg_color_btn.getArcHeight());
            color_frame_matrix_oper_frame_color_btn.setArcHeight(color_frame_btn_bg_color_btn.getArcHeight());
            color_frame_output_color_btn.setArcHeight(color_frame_btn_bg_color_btn.getArcHeight());
            color_frame_calc_bg_color_btn.setArcHeight(color_frame_btn_bg_color_btn.getArcHeight());
            color_frame_input_text_field_color_btn.setArcHeight(color_frame_btn_bg_color_btn.getArcHeight());
            color_frame_color_btn.setArcHeight(color_frame_btn_bg_color_btn.getArcHeight());
            color_frame_font_frame_color_btn.setArcHeight(color_frame_btn_bg_color_btn.getArcHeight());
            color_frame_precision_frame_color_btn.setArcHeight(color_frame_btn_bg_color_btn.getArcHeight());
            color_frame_button_layout_frame_color_btn.setArcHeight(color_frame_btn_bg_color_btn.getArcHeight());
            color_frame_btn_press_color_btn.setArcHeight(color_frame_btn_bg_color_btn.getArcHeight());
            color_frame_btn_border_color_btn.setArcHeight(color_frame_btn_bg_color_btn.getArcHeight());
            color_frame_btn_font_color_btn.setArcHeight(color_frame_btn_bg_color_btn.getArcHeight());
            color_frame_btn_disabled_color_btn.setArcHeight(color_frame_btn_bg_color_btn.getArcHeight());
        }
    }
    
    /**
     * Set the color frame button corner arc width
     */
    protected void set_color_frame_btn_arcWidth_layout()
    {
        if(color_frame != null) 
        {
            color_frame_btn_bg_color_btn.setArcWidth(btn_arc_width);
            color_frame_btn_hover_color_btn.setArcWidth(color_frame_btn_bg_color_btn.getArcWidth());
            color_frame_exit_btn.setArcWidth(color_frame_btn_bg_color_btn.getArcWidth());
            color_frame_reset_color_btn.setArcWidth(color_frame_btn_bg_color_btn.getArcWidth()); 
            color_frame_main_frame_color_btn.setArcWidth(color_frame_btn_bg_color_btn.getArcWidth());
            color_frame_main_frame_bar_color_btn.setArcWidth(color_frame_btn_bg_color_btn.getArcWidth());
            color_frame_error_msg_color_btn.setArcWidth(color_frame_btn_bg_color_btn.getArcWidth());
            color_frame_matrix_frame_color_btn.setArcWidth(color_frame_btn_bg_color_btn.getArcWidth());
            color_frame_matrix_oper_frame_color_btn.setArcWidth(color_frame_btn_bg_color_btn.getArcWidth());
            color_frame_output_color_btn.setArcWidth(color_frame_btn_bg_color_btn.getArcWidth());
            color_frame_calc_bg_color_btn.setArcWidth(color_frame_btn_bg_color_btn.getArcWidth());
            color_frame_input_text_field_color_btn.setArcWidth(color_frame_btn_bg_color_btn.getArcWidth());
            color_frame_color_btn.setArcWidth(color_frame_btn_bg_color_btn.getArcWidth());
            color_frame_font_frame_color_btn.setArcWidth(color_frame_btn_bg_color_btn.getArcWidth());
            color_frame_precision_frame_color_btn.setArcWidth(color_frame_btn_bg_color_btn.getArcWidth());
            color_frame_button_layout_frame_color_btn.setArcWidth(color_frame_btn_bg_color_btn.getArcWidth());
            color_frame_btn_press_color_btn.setArcWidth(color_frame_btn_bg_color_btn.getArcWidth());
            color_frame_btn_border_color_btn.setArcWidth(color_frame_btn_bg_color_btn.getArcWidth());
            color_frame_btn_font_color_btn.setArcWidth(color_frame_btn_bg_color_btn.getArcWidth());
            color_frame_btn_disabled_color_btn.setArcWidth(color_frame_btn_bg_color_btn.getArcWidth());
        }
    }
    
    /**
     * Repaints all the buttons in color menu
     */
    protected void color_frame_btn_repaint()
    {
        if(color_frame != null)
        {
            color_frame_btn_bg_color_btn.repaint();
            color_frame_btn_hover_color_btn.repaint();
            color_frame_exit_btn.repaint();
            color_frame_reset_color_btn.repaint(); 
            color_frame_main_frame_color_btn.repaint();
            color_frame_main_frame_bar_color_btn.repaint();
            color_frame_error_msg_color_btn.repaint();
            color_frame_matrix_frame_color_btn.repaint();
            color_frame_matrix_oper_frame_color_btn.repaint();
            color_frame_output_color_btn.repaint();
            color_frame_calc_bg_color_btn.repaint();
            color_frame_input_text_field_color_btn.repaint();
            color_frame_color_btn.repaint();
            color_frame_font_frame_color_btn.repaint();
            color_frame_precision_frame_color_btn.repaint();
            color_frame_button_layout_frame_color_btn.repaint();
            color_frame_btn_press_color_btn.repaint();
            color_frame_btn_border_color_btn.repaint();
            color_frame_btn_font_color_btn.repaint();
            color_frame_btn_disabled_color_btn.repaint();
        }
    }

    /**
     * Sets the button disabled color in color menu
     */
    protected void set_color_frame_btn_disabled_color()
    {
        if(color_frame != null)
        {
            color_frame_btn_bg_color_btn.setDisabledBackgroundColor(color_arr[12]);
            color_frame_btn_hover_color_btn.setDisabledBackgroundColor(color_frame_btn_bg_color_btn.getDisabledBackgroundColor());
            color_frame_exit_btn.setDisabledBackgroundColor(color_frame_btn_bg_color_btn.getDisabledBackgroundColor());
            color_frame_reset_color_btn.setDisabledBackgroundColor(color_frame_btn_bg_color_btn.getDisabledBackgroundColor()); 
            color_frame_main_frame_color_btn.setDisabledBackgroundColor(color_frame_btn_bg_color_btn.getDisabledBackgroundColor());
            color_frame_main_frame_bar_color_btn.setDisabledBackgroundColor(color_frame_btn_bg_color_btn.getDisabledBackgroundColor());
            color_frame_error_msg_color_btn.setDisabledBackgroundColor(color_frame_btn_bg_color_btn.getDisabledBackgroundColor());
            color_frame_matrix_frame_color_btn.setDisabledBackgroundColor(color_frame_btn_bg_color_btn.getDisabledBackgroundColor());
            color_frame_matrix_oper_frame_color_btn.setDisabledBackgroundColor(color_frame_btn_bg_color_btn.getDisabledBackgroundColor());
            color_frame_output_color_btn.setDisabledBackgroundColor(color_frame_btn_bg_color_btn.getDisabledBackgroundColor());
            color_frame_calc_bg_color_btn.setDisabledBackgroundColor(color_frame_btn_bg_color_btn.getDisabledBackgroundColor());
            color_frame_input_text_field_color_btn.setDisabledBackgroundColor(color_frame_btn_bg_color_btn.getDisabledBackgroundColor());
            color_frame_color_btn.setDisabledBackgroundColor(color_frame_btn_bg_color_btn.getDisabledBackgroundColor());;
            color_frame_font_frame_color_btn.setDisabledBackgroundColor(color_frame_btn_bg_color_btn.getDisabledBackgroundColor());
            color_frame_precision_frame_color_btn.setDisabledBackgroundColor(color_frame_btn_bg_color_btn.getDisabledBackgroundColor());
            color_frame_button_layout_frame_color_btn.setDisabledBackgroundColor(color_frame_btn_bg_color_btn.getDisabledBackgroundColor());
            color_frame_btn_press_color_btn.setDisabledBackgroundColor(color_frame_btn_bg_color_btn.getDisabledBackgroundColor());
            color_frame_btn_border_color_btn.setDisabledBackgroundColor(color_frame_btn_bg_color_btn.getDisabledBackgroundColor());
            color_frame_btn_font_color_btn.setDisabledBackgroundColor(color_frame_btn_bg_color_btn.getDisabledBackgroundColor());
            color_frame_btn_disabled_color_btn.setDisabledBackgroundColor(color_frame_btn_bg_color_btn.getDisabledBackgroundColor());
        }
    }
    
    /**
     * Sets the button bg color in color menu
     */
    protected void set_color_frame_btn_bg_color()
    {
        if(color_frame != null)
        {
            if(color_arr[7].getRGB() != default_button_color_rgb) { color_frame_btn_bg_color_btn.setBackground(color_arr[7]); } 
            else { color_frame_btn_bg_color_btn.setBackground(default_color_arr[14]); }

            color_frame_exit_btn.setBackground(color_frame_btn_bg_color_btn.getBackground()); 
            color_frame_reset_color_btn.setBackground(color_frame_btn_bg_color_btn.getBackground()); 
        }
    }
    
    /**
     * Sets the button hover color in color menu
     */
    protected void set_color_frame_btn_hover_color()
    {
        if(color_frame != null)
        {
            color_frame_btn_bg_color_btn.setHoverBackgroundColor(color_arr[8]);
            color_frame_btn_hover_color_btn.setHoverBackgroundColor(color_frame_btn_bg_color_btn.getHoverBackgroundColor());
            color_frame_exit_btn.setHoverBackgroundColor(color_frame_btn_bg_color_btn.getHoverBackgroundColor());
            color_frame_reset_color_btn.setHoverBackgroundColor(color_frame_btn_bg_color_btn.getHoverBackgroundColor());
            color_frame_main_frame_color_btn.setHoverBackgroundColor(color_frame_btn_bg_color_btn.getHoverBackgroundColor());
            color_frame_main_frame_bar_color_btn.setHoverBackgroundColor(color_frame_btn_bg_color_btn.getHoverBackgroundColor());
            color_frame_error_msg_color_btn.setHoverBackgroundColor(color_frame_btn_bg_color_btn.getHoverBackgroundColor());
            color_frame_matrix_frame_color_btn.setHoverBackgroundColor(color_frame_btn_bg_color_btn.getHoverBackgroundColor());
            color_frame_matrix_oper_frame_color_btn.setHoverBackgroundColor(color_frame_btn_bg_color_btn.getHoverBackgroundColor());
            color_frame_output_color_btn.setHoverBackgroundColor(color_frame_btn_bg_color_btn.getHoverBackgroundColor());
            color_frame_calc_bg_color_btn.setHoverBackgroundColor(color_frame_btn_bg_color_btn.getHoverBackgroundColor());
            color_frame_input_text_field_color_btn.setHoverBackgroundColor(color_frame_btn_bg_color_btn.getHoverBackgroundColor());
            color_frame_color_btn.setHoverBackgroundColor(color_frame_btn_bg_color_btn.getHoverBackgroundColor());
            color_frame_font_frame_color_btn.setHoverBackgroundColor(color_frame_btn_bg_color_btn.getHoverBackgroundColor());
            color_frame_precision_frame_color_btn.setHoverBackgroundColor(color_frame_btn_bg_color_btn.getHoverBackgroundColor());
            color_frame_button_layout_frame_color_btn.setHoverBackgroundColor(color_frame_btn_bg_color_btn.getHoverBackgroundColor());
            color_frame_btn_press_color_btn.setHoverBackgroundColor(color_frame_btn_bg_color_btn.getHoverBackgroundColor());
            color_frame_btn_border_color_btn.setHoverBackgroundColor(color_frame_btn_bg_color_btn.getHoverBackgroundColor());
            color_frame_btn_font_color_btn.setHoverBackgroundColor(color_frame_btn_bg_color_btn.getHoverBackgroundColor());
            color_frame_btn_disabled_color_btn.setHoverBackgroundColor(color_frame_btn_bg_color_btn.getHoverBackgroundColor());
        }
    }
    
    /**
     * Sets the button press color in color menu
     */
    protected void set_color_frame_btn_press_color()
    {
        if(color_frame != null)
        {
            color_frame_btn_bg_color_btn.setPressedBackgroundColor(color_arr[9]);
            color_frame_btn_hover_color_btn.setPressedBackgroundColor(color_frame_btn_bg_color_btn.getPressedBackgroundColor());
            color_frame_exit_btn.setPressedBackgroundColor(color_frame_btn_bg_color_btn.getPressedBackgroundColor());
            color_frame_reset_color_btn.setPressedBackgroundColor(color_frame_btn_bg_color_btn.getPressedBackgroundColor()); 
            color_frame_main_frame_color_btn.setPressedBackgroundColor(color_frame_btn_bg_color_btn.getPressedBackgroundColor());
            color_frame_main_frame_bar_color_btn.setPressedBackgroundColor(color_frame_btn_bg_color_btn.getPressedBackgroundColor());
            color_frame_error_msg_color_btn.setPressedBackgroundColor(color_frame_btn_bg_color_btn.getPressedBackgroundColor());
            color_frame_matrix_frame_color_btn.setPressedBackgroundColor(color_frame_btn_bg_color_btn.getPressedBackgroundColor());
            color_frame_matrix_oper_frame_color_btn.setPressedBackgroundColor(color_frame_btn_bg_color_btn.getPressedBackgroundColor());
            color_frame_output_color_btn.setPressedBackgroundColor(color_frame_btn_bg_color_btn.getPressedBackgroundColor());
            color_frame_calc_bg_color_btn.setPressedBackgroundColor(color_frame_btn_bg_color_btn.getPressedBackgroundColor());
            color_frame_input_text_field_color_btn.setPressedBackgroundColor(color_frame_btn_bg_color_btn.getPressedBackgroundColor());
            color_frame_color_btn.setPressedBackgroundColor(color_frame_btn_bg_color_btn.getPressedBackgroundColor());
            color_frame_font_frame_color_btn.setPressedBackgroundColor(color_frame_btn_bg_color_btn.getPressedBackgroundColor());
            color_frame_precision_frame_color_btn.setPressedBackgroundColor(color_frame_btn_bg_color_btn.getPressedBackgroundColor());
            color_frame_button_layout_frame_color_btn.setPressedBackgroundColor(color_frame_btn_bg_color_btn.getPressedBackgroundColor());
            color_frame_btn_press_color_btn.setPressedBackgroundColor(color_frame_btn_bg_color_btn.getPressedBackgroundColor());
            color_frame_btn_border_color_btn.setPressedBackgroundColor(color_frame_btn_bg_color_btn.getPressedBackgroundColor());
            color_frame_btn_font_color_btn.setPressedBackgroundColor(color_frame_btn_bg_color_btn.getPressedBackgroundColor());
            color_frame_btn_disabled_color_btn.setPressedBackgroundColor(color_frame_btn_bg_color_btn.getPressedBackgroundColor());
        }
    }
    
    /**
     * Sets the button border color in color menu
     */
    protected void set_color_frame_btn_border_color()
    {
        if(color_frame != null)
        {
            color_frame_btn_bg_color_btn.setBorderColor(color_arr[10]);
            color_frame_btn_hover_color_btn.setBorderColor(color_frame_btn_bg_color_btn.getBorderColor());
            color_frame_exit_btn.setBorderColor(color_frame_btn_bg_color_btn.getBorderColor());
            color_frame_reset_color_btn.setBorderColor(color_frame_btn_bg_color_btn.getBorderColor()); 
            color_frame_main_frame_color_btn.setBorderColor(color_frame_btn_bg_color_btn.getBorderColor());
            color_frame_main_frame_bar_color_btn.setBorderColor(color_frame_btn_bg_color_btn.getBorderColor());
            color_frame_error_msg_color_btn.setBorderColor(color_frame_btn_bg_color_btn.getBorderColor());
            color_frame_matrix_frame_color_btn.setBorderColor(color_frame_btn_bg_color_btn.getBorderColor());
            color_frame_matrix_oper_frame_color_btn.setBorderColor(color_frame_btn_bg_color_btn.getBorderColor());
            color_frame_output_color_btn.setBorderColor(color_frame_btn_bg_color_btn.getBorderColor());
            color_frame_calc_bg_color_btn.setBorderColor(color_frame_btn_bg_color_btn.getBorderColor());
            color_frame_input_text_field_color_btn.setBorderColor(color_frame_btn_bg_color_btn.getBorderColor());
            color_frame_color_btn.setBorderColor(color_frame_btn_bg_color_btn.getBorderColor());
            color_frame_font_frame_color_btn.setBorderColor(color_frame_btn_bg_color_btn.getBorderColor());
            color_frame_precision_frame_color_btn.setBorderColor(color_frame_btn_bg_color_btn.getBorderColor());
            color_frame_button_layout_frame_color_btn.setBorderColor(color_frame_btn_bg_color_btn.getBorderColor());
            color_frame_btn_press_color_btn.setBorderColor(color_frame_btn_bg_color_btn.getBorderColor());
            color_frame_btn_border_color_btn.setBorderColor(color_frame_btn_bg_color_btn.getBorderColor());
            color_frame_btn_font_color_btn.setBorderColor(color_frame_btn_bg_color_btn.getBorderColor());
            color_frame_btn_disabled_color_btn.setBorderColor(color_frame_btn_bg_color_btn.getBorderColor());
        }
    }
    
    /**
     * Sets the button font color in color menu
     */
    protected void set_color_frame_btn_font_color()
    {
        if(color_frame != null)
        {
            color_frame_btn_bg_color_btn.setFontColor(color_arr[11]);
            color_frame_btn_hover_color_btn.setFontColor(color_frame_btn_bg_color_btn.getFontColor());
            color_frame_exit_btn.setFontColor(color_frame_btn_bg_color_btn.getFontColor());
            color_frame_reset_color_btn.setFontColor(color_frame_btn_bg_color_btn.getFontColor());
            color_frame_main_frame_color_btn.setFontColor(color_frame_btn_bg_color_btn.getFontColor()); 
            color_frame_main_frame_bar_color_btn.setFontColor(color_frame_btn_bg_color_btn.getFontColor());
            color_frame_error_msg_color_btn.setFontColor(color_frame_btn_bg_color_btn.getFontColor());
            color_frame_matrix_frame_color_btn.setFontColor(color_frame_btn_bg_color_btn.getFontColor());
            color_frame_matrix_oper_frame_color_btn.setFontColor(color_frame_btn_bg_color_btn.getFontColor());
            color_frame_output_color_btn.setFontColor(color_frame_btn_bg_color_btn.getFontColor());
            color_frame_calc_bg_color_btn.setFontColor(color_frame_btn_bg_color_btn.getFontColor()); 
            color_frame_input_text_field_color_btn.setFontColor(color_frame_btn_bg_color_btn.getFontColor());
            color_frame_color_btn.setFontColor(color_frame_btn_bg_color_btn.getFontColor());
            color_frame_font_frame_color_btn.setFontColor(color_frame_btn_bg_color_btn.getFontColor());
            color_frame_precision_frame_color_btn.setFontColor(color_frame_btn_bg_color_btn.getFontColor());
            color_frame_button_layout_frame_color_btn.setFontColor(color_frame_btn_bg_color_btn.getFontColor());
            color_frame_btn_press_color_btn.setFontColor(color_frame_btn_bg_color_btn.getFontColor());
            color_frame_btn_border_color_btn.setFontColor(color_frame_btn_bg_color_btn.getFontColor());
            color_frame_btn_font_color_btn.setFontColor(color_frame_btn_bg_color_btn.getFontColor());
            color_frame_btn_disabled_color_btn.setFontColor(color_frame_btn_bg_color_btn.getFontColor());
        }
    }

    /**
     * Set the bg color of various representative buttons based on the selected current user color scheme
     */
    protected void set_color_frame_color_scheme_btn_color()
    {
        if(color_frame != null)
        {
            color_frame_main_frame_color_btn.setBackground(color_arr[0]); 
            color_frame_main_frame_bar_color_btn.setBackground(color_arr[1]);
            color_frame_error_msg_color_btn.setBackground(color_arr[2]); 
            color_frame_matrix_frame_color_btn.setBackground(color_arr[3]); 
            color_frame_matrix_oper_frame_color_btn.setBackground(color_arr[4]); 
            color_frame_output_color_btn.setBackground(color_arr[5]); 
            color_frame_calc_bg_color_btn.setBackground(color_arr[6]);
            color_frame_btn_hover_color_btn.setBackground(color_arr[8]);
            color_frame_btn_press_color_btn.setBackground(color_arr[9]);
            color_frame_btn_border_color_btn.setBackground(color_arr[10]);
            color_frame_btn_font_color_btn.setBackground(color_arr[11]);
            color_frame_btn_disabled_color_btn.setBackground(color_arr[12]);
            color_frame_input_text_field_color_btn.setBackground(color_arr[13]);
            color_frame_color_btn.setBackground(color_arr[14]);
            color_frame_font_frame_color_btn.setBackground(color_arr[15]);
            color_frame_precision_frame_color_btn.setBackground(color_arr[16]);
            color_frame_button_layout_frame_color_btn.setBackground(color_arr[17]);
        }
    }

    /**
     * Try to dispose the color menu
     */
    protected void dispose_color_frame() { if(color_frame != null) { color_frame.dispose(); color_frame = null; } }
    
    
    /**
     * Matrix operations calculator menu
     */
    protected void Matrix()
    {
        //Try to destroy the matrix frame if it exists
        dispose_matrix_frame();

        //Try to create a new Matrix frame
        matrix_frame = new JFrame("Matrix"); 
        matrix_frame.setIconImage(main_frame.getIconImage());
        matrix_frame.setLayout(new GridBagLayout());

        //Create lables to put on the Matrix frame
        matrix_frame_row_label = new JLabel("ROWS:");
        matrix_frame_col_label = new JLabel("COLS:");
        matrix_frame_exit_btn = new CustomJButton("Exit");
        matrix_frame_oper_btn = new CustomJButton("MAT");
        set_matrix_frame_label_color();

        //Set the bg color of the buttons
        set_matrix_frame_btn_color(); 
        
        //Create input fields for rows and columns
        matrix_frame_err_bar = new JTextField(19);
        matrix_frame_row_field = new JTextField(2); 
        matrix_frame_row_field.setPreferredSize(new Dimension(20,26));
        matrix_frame_col_field = new JTextField(2); 
        matrix_frame_col_field.setPreferredSize(matrix_frame_row_field.getPreferredSize());

        //Set the bg color and font of input fields and buttons
        set_matrix_frame_err_msg_color();
        set_matrix_frame_input_color();
        set_matrix_frame_system_font();
        set_matrix_frame_input_font();
        set_matrix_frame_output_font(); 
        matrix_frame_err_bar.setEnabled(false);

        matrix_frame_row_label.setPreferredSize(new Dimension(matrix_frame_exit_btn.getPreferredSize().width+10, matrix_frame_exit_btn.getPreferredSize().height-5)); 
        matrix_frame_exit_btn.setPreferredSize(new Dimension(matrix_frame_row_label.getPreferredSize().width+8, matrix_frame_row_label.getPreferredSize().height)); 
        matrix_frame_col_label.setPreferredSize(matrix_frame_row_label.getPreferredSize()); 
        matrix_frame_oper_btn.setPreferredSize(matrix_frame_exit_btn.getPreferredSize());

        matrix_frame_col_label.setHorizontalAlignment(default_font_alignment); 
        matrix_frame_row_label.setHorizontalAlignment(default_font_alignment); 
        matrix_frame_err_bar.setHorizontalAlignment(default_font_alignment);
        
        GridBagConstraints matrix_frame_constr = new GridBagConstraints();
        matrix_frame_constr.fill = GridBagConstraints.HORIZONTAL;
        
        //Padding the frame with blank labels for better presentation
        {
            Dimension dim = (new Dimension(matrix_frame_row_label.getPreferredSize().width/3, matrix_frame_row_field.getPreferredSize().height));
            JLabel[] empty_label_arr = new JLabel[10];

            for(int i = 0; i < empty_label_arr.length; ++i) 
            { 
                empty_label_arr[i] = new JLabel(); 
                empty_label_arr[i].setPreferredSize(dim); 
            }
            
            matrix_frame.add(empty_label_arr[0], matrix_frame_constr);
            matrix_frame_constr.gridx = 7; 
            matrix_frame.add(empty_label_arr[1], matrix_frame_constr);            
            matrix_frame_constr.gridx = 0; 
            matrix_frame_constr.gridy = 1; 
            matrix_frame.add(empty_label_arr[2], matrix_frame_constr);
            matrix_frame_constr.gridx = 1; 
            matrix_frame.add(empty_label_arr[3], matrix_frame_constr);
            matrix_frame_constr.gridx = 3; 
            matrix_frame.add(empty_label_arr[4], matrix_frame_constr);
            matrix_frame_constr.gridx = 4; 
            matrix_frame.add(empty_label_arr[5], matrix_frame_constr);
            matrix_frame_constr.gridx = 6; 
            matrix_frame.add(empty_label_arr[6], matrix_frame_constr);
            matrix_frame_constr.gridx = 7; 
            matrix_frame.add(empty_label_arr[7], matrix_frame_constr);
            matrix_frame_constr.gridx = 0; 
            matrix_frame_constr.gridy = 2; 
            matrix_frame.add(empty_label_arr[8], matrix_frame_constr);
            matrix_frame_constr.gridx = 7; 
            matrix_frame.add(empty_label_arr[9], matrix_frame_constr);
        }

        //Put row and column labels on the frame
        matrix_frame_constr.insets = new Insets(5,0,2,0); 
        matrix_frame_constr.gridx = 1; 
        matrix_frame_constr.gridy = 0; 
        matrix_frame_constr.gridwidth = 3; 
        matrix_frame.add(matrix_frame_row_label, matrix_frame_constr);
        matrix_frame_constr.gridx = 4; 
        matrix_frame.add(matrix_frame_col_label, matrix_frame_constr);
        
        //Put row and column field inputs on the frame
        matrix_frame_constr.insets = new Insets(0,3,5,0);  
        matrix_frame_constr.gridwidth = 1; 
        matrix_frame_constr.gridy = 1; 
        matrix_frame_constr.gridx = 2; 
        matrix_frame_constr.ipadx = 15;  
        matrix_frame_constr.ipady = -4; 
        matrix_frame.add(matrix_frame_row_field, matrix_frame_constr); 
        matrix_frame_constr.gridx = 5; 
        matrix_frame_constr.ipadx = 16;  
        matrix_frame.add(matrix_frame_col_field, matrix_frame_constr);
        
        //Put buttons on the Matrix frame
        matrix_frame_constr.ipadx = 0; 
        matrix_frame_constr.ipady = 0;
        matrix_frame_constr.insets = new Insets(2,0,5,10); 
        matrix_frame_constr.gridx = 1; 
        matrix_frame_constr.gridy = 2; 
        matrix_frame_constr.gridwidth = 3; 
        matrix_frame.add(matrix_frame_oper_btn, matrix_frame_constr);
        matrix_frame_constr.insets = new Insets(2,10,5,0); 
        matrix_frame_constr.gridx = 4; 
        matrix_frame.add(matrix_frame_exit_btn, matrix_frame_constr);

        //Put error bar on the Matrix frame
        matrix_frame_constr.insets = new Insets(2,2,2,2); 
        matrix_frame_constr.gridy = 3; 
        matrix_frame_constr.gridx = 0; 
        matrix_frame_constr.gridwidth = 8; 
        matrix_frame.add(matrix_frame_err_bar, matrix_frame_constr);

        //Select the entire input for column when user clicks on the field
        matrix_frame_row_field.addFocusListener(new FocusListener()
        {
            @Override public void focusGained(FocusEvent e) { matrix_frame_row_field.selectAll(); }
            @Override public void focusLost(FocusEvent e) {}
        });

        //Select the entire input for row when user clicks on the field
        matrix_frame_col_field.addFocusListener(new FocusListener()
        {
            @Override public void focusGained(FocusEvent e) { matrix_frame_col_field.selectAll(); }
            @Override public void focusLost(FocusEvent e) {}
        });

        //Checks the user row and column inputs, and creates a Matrix Operation frame
        matrix_frame_oper_btn.addActionListener(new ActionListener() 
        { 
            @Override public void actionPerformed(ActionEvent e) 
            {
                //Check if the row and column inputs are positive int
                try
                {
                    MatrixOper(Integer.parseInt(matrix_frame_row_field.getText()), Integer.parseInt(matrix_frame_col_field.getText())); 
                    matrix_frame_err_bar.setText(""); 
                }
                catch (Exception ex) 
                { 
                    if(ex instanceof NumberFormatException) { matrix_frame_err_bar.setText("ERROR: Row/Col must be pos int"); }
                    else { matrix_frame_err_bar.setText(ex.getMessage()); }
                }
            } 
        });

        //Dispose the Matrix frame when clicked on exit
        matrix_frame_exit_btn.addActionListener(new ActionListener() { @Override public void actionPerformed(ActionEvent e) { dispose_matrix_frame(); } });
        
        //Deploy the frame to the user
        set_matrix_frame_bg_color();
        matrix_frame.pack();
        if(matrix_frame.getBounds().width < 220) { matrix_frame.setBounds(0, 0, 220, matrix_frame.getBounds().height); }
        matrix_frame.setLocationRelativeTo(null);
        matrix_frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        matrix_frame.addWindowListener(new WindowListener()
        {
            @Override public void windowClosing(WindowEvent e) { dispose_matrix_frame(); }
            @Override public void windowOpened(WindowEvent e) {}
            @Override public void windowClosed(WindowEvent e) {}
            @Override public void windowIconified(WindowEvent e) {}
            @Override public void windowDeiconified(WindowEvent e) {}
            @Override public void windowActivated(WindowEvent e) {}
            @Override public void windowDeactivated(WindowEvent e) {}
        });
        matrix_frame.setResizable(false);
        matrix_frame.setVisible(true);
    }//Matrix()

    /**
     * Refresh the components of matrix frame while keeping user changes intact
     */
    protected void refresh_matrix_frame()
    {
        if(matrix_frame != null)
        {
            String matrix_frame_row_field_text = matrix_frame_row_field.getText();
            String matrix_frame_col_field_text = matrix_frame_col_field.getText();
            String matrix_frame_err_bar_text = matrix_frame_err_bar.getText();

            Matrix();

            matrix_frame_row_field.setText(matrix_frame_row_field_text);
            matrix_frame_col_field.setText(matrix_frame_col_field_text);
            matrix_frame_err_bar.setText(matrix_frame_err_bar_text);
        }
    }

    /**
     * Set the font color of the labels within the main matrix menu
     */
    protected void set_matrix_frame_label_color()
    {
        if(matrix_frame != null)
        {
            try { matrix_frame_row_label.setForeground(matrix_frame_oper_btn.getInvertedColor(color_arr[3], 100, 150, false)); } catch(Exception ex) { return; }
            matrix_frame_col_label.setForeground(matrix_frame_row_label.getForeground());
        }
    }

    /**
     * Set the font of the button and labels on the matrix panel
     */
    protected void set_matrix_frame_system_font()
    {
        if(matrix_frame != null)
        {
            matrix_frame_oper_btn.setFont(font_arr[0]); 
            matrix_frame_exit_btn.setFont(matrix_frame_oper_btn.getFont());
            matrix_frame_row_label.setFont(matrix_frame_oper_btn.getFont()); 
            matrix_frame_col_label.setFont(matrix_frame_oper_btn.getFont());
        }
    }

    /**
     * Set the font of the input textfields
     */
    protected void set_matrix_frame_input_font()
    {
        if(matrix_frame != null)
        {
            matrix_frame_row_field.setFont(font_arr[2]);
            matrix_frame_col_field.setFont(matrix_frame_row_field.getFont());
        }
    }

    /**
     * Set the bg color of the buttons on the matrix panel
     */
    protected void set_matrix_frame_btn_color()
    {
        if(matrix_frame != null)
        {
            set_matrix_frame_btn_bg_color();
            set_matrix_frame_btn_hover_color();
            set_matrix_frame_btn_press_color();
            set_matrix_frame_btn_border_color();
            set_matrix_frame_btn_font_color();
            set_matrix_frame_btn_disabled_color();
            set_matrix_frame_btn_layout();
        }
    }

    /**
     * Set the matrix frame button corner cut-out
     */
    protected void set_matrix_frame_btn_layout()
    {
        if(matrix_frame!= null) 
        {
            set_matrix_frame_btn_arcHeight_layout();
            set_matrix_frame_btn_arcWidth_layout();
        }
    }

    /**
     * Set the matrix frame button corner arc height
     */
    protected void set_matrix_frame_btn_arcHeight_layout()
    {
        if(matrix_frame != null) 
        {
            matrix_frame_exit_btn.setArcHeight(btn_arc_height);
            matrix_frame_oper_btn.setArcHeight(matrix_frame_exit_btn.getArcHeight());
        }
    }
    
    /**
     * Set the matrix frame button corner arc width
     */
    protected void set_matrix_frame_btn_arcWidth_layout()
    {
        if(matrix_frame != null) 
        {
            matrix_frame_exit_btn.setArcWidth(btn_arc_width);
            matrix_frame_oper_btn.setArcWidth(matrix_frame_exit_btn.getArcWidth());
        }
    }

    /**
     * Sets the button bg color in matrix main menu
     */
    protected void set_matrix_frame_btn_bg_color()
    {
        if(matrix_frame != null)
        {
            if(color_arr[7].getRGB() != default_button_color_rgb) { matrix_frame_exit_btn.setBackground(color_arr[7]); }
            else { matrix_frame_exit_btn.setBackground(default_color_arr[14]); }

            matrix_frame_oper_btn.setBackground(matrix_frame_exit_btn.getBackground());
        }
    }

    /**
     * Sets the button hover color in matrix main menu
     */
    protected void set_matrix_frame_btn_hover_color()
    {
        if(matrix_frame != null)
        {
            matrix_frame_exit_btn.setHoverBackgroundColor(color_arr[8]);
            matrix_frame_oper_btn.setHoverBackgroundColor(matrix_frame_exit_btn.getHoverBackgroundColor());
        }
    }

    /**
     * Sets the button press color in matrix main menu
     */
    protected void set_matrix_frame_btn_press_color()
    {
        if(matrix_frame != null)
        {
            matrix_frame_exit_btn.setPressedBackgroundColor(color_arr[9]);
            matrix_frame_oper_btn.setPressedBackgroundColor(matrix_frame_exit_btn.getPressedBackgroundColor());
        }
    }

    /**
     * Sets the button border color in matrix main menu
     */
    protected void set_matrix_frame_btn_border_color()
    {
        if(matrix_frame != null)
        {
            matrix_frame_exit_btn.setBorderColor(color_arr[10]);
            matrix_frame_oper_btn.setBorderColor(matrix_frame_exit_btn.getBorderColor());
        }
    }

    /**
     * Repaints all the buttons in matrix main menu
     */
    protected void matrix_frame_btn_repaint()
    {
        if(matrix_frame != null)
        {
            matrix_frame_exit_btn.repaint();
            matrix_frame_oper_btn.repaint();
        }
    }

    /**
     * Sets the button font color in matrix main menu
     */
    protected void set_matrix_frame_btn_font_color()
    {
        if(matrix_frame != null)
        {
            matrix_frame_exit_btn.setFontColor(color_arr[11]);
            matrix_frame_oper_btn.setFontColor(matrix_frame_exit_btn.getFontColor());
        }
    }

    /**
     * Sets the button disabled color in matrix main menu
     */
    protected void set_matrix_frame_btn_disabled_color()
    {
        if(matrix_frame != null)
        {
            matrix_frame_exit_btn.setDisabledBackgroundColor(color_arr[12]);
            matrix_frame_oper_btn.setDisabledBackgroundColor(matrix_frame_exit_btn.getDisabledBackgroundColor());
        }
    }

    /**
     * Set the bg color of the textfield on the matrix panel
     */
    protected void set_matrix_frame_input_color()
    {
        if(matrix_frame != null)
        {
            matrix_frame_row_field.setBackground(color_arr[13]); 
            matrix_frame_col_field.setBackground(matrix_frame_row_field.getBackground()); 
        }
    }

    /**
     * Set the bg color of the error bar on the matrix panel
     */
    protected void set_matrix_frame_err_msg_color() { if(matrix_frame != null) { matrix_frame_err_bar.setBackground(color_arr[2]); } }

    /**
     * Try to dispose the matrix panel
     */
    protected void dispose_matrix_frame() { if(matrix_frame != null) { matrix_frame.dispose(); matrix_frame = null; } }

    /**
     * Set the error bar font on the matrix panel
     */
    protected void set_matrix_frame_output_font() { if(matrix_frame != null) { matrix_frame_err_bar.setFont(font_arr[3]); } }

    /**
     * Set the bg color of the matrix panel
     */
    protected void set_matrix_frame_bg_color() { if(matrix_frame != null) { matrix_frame.getContentPane().setBackground(color_arr[3]); } }
    

    /**
     * Creates a menu of input and output matrix to enable the user to calculate RREF, determinant, and inverse of the matrix
     * @param r Number of rows
     * @param c Number of Columns
     */
    protected void MatrixOper(final int r, final int c) throws Exception
    {
        if(r < min_matrix_coordinate || c < min_matrix_coordinate || r > max_matrix_coordinate || c > max_matrix_coordinate )
        { throw new Exception("ERROR: Row/Col must be [" + min_matrix_coordinate + "," + max_matrix_coordinate + "]"); }

        //Dispose the Matrix Operation frame if it exists
        dispose_matrix_oper_frame();

        //Create and set attributes of the new Matrix Operation frame
        matrix_oper_frame = new JFrame("Matrix Operations"); 
        matrix_oper_frame.setIconImage(main_frame.getIconImage());
        matrix_oper_frame.setLayout(new GridBagLayout());

        //Create buttons to put on the Matrix Operation frame
        matrix_oper_frame_clear_btn = new CustomJButton("Clear"); 
        matrix_oper_frame_clear_btn.setPreferredSize(new Dimension(matrix_oper_frame_clear_btn.getPreferredSize().width+19, matrix_oper_frame_clear_btn.getPreferredSize().height));
        matrix_oper_frame_rref_btn = new CustomJButton("RREF"); 
        matrix_oper_frame_rref_btn.setPreferredSize(matrix_oper_frame_clear_btn.getPreferredSize());
        matrix_oper_frame_det_btn = new CustomJButton("DET"); 
        matrix_oper_frame_det_btn.setPreferredSize(matrix_oper_frame_clear_btn.getPreferredSize());
        matrix_oper_frame_inverse_btn = new CustomJButton("INV");
        matrix_oper_frame_inverse_btn.setPreferredSize(matrix_oper_frame_clear_btn.getPreferredSize());
        matrix_oper_frame_exit_btn = new CustomJButton("Exit");

        set_matrix_oper_frame_system_font();
        set_matrix_oper_frame_btn_color();

        matrix_oper_frame_exit_btn.setPreferredSize(matrix_oper_frame_clear_btn.getPreferredSize());
        matrix_oper_frame_err_bar = new JTextField(); 
        matrix_oper_frame_err_bar.setEnabled(false); 
        matrix_oper_frame_err_bar.setHorizontalAlignment(default_font_alignment);

        set_matrix_oper_frame_output_err_bar_font();
        set_matrix_oper_frame_err_msg_color();
        set_matrix_oper_frame_bg_color();

        GridBagConstraints matrix_oper_frame_constr = new GridBagConstraints(); 
        matrix_oper_frame_constr.fill = GridBagConstraints.HORIZONTAL;

        //Create user input matrix
        matrix_oper_frame_user_field_arr = new JTextField[r][c];
        Dimension dim = new Dimension(65, matrix_oper_frame_clear_btn.getPreferredSize().height-5);

        for(int i = 0; i < r; ++i)
        {
            for(int j = 0; j < c; ++j)
            {
                //First row
                if(i == 0) 
                {
                    //First row and first column
                    if(j == 0) { matrix_oper_frame_constr.insets = new Insets(5,5,1,1); }
                    //First row and last column
                    else if (j == c-1) { matrix_oper_frame_constr.insets = new Insets(5,1,1,5); }
                    //First row and not first column and not last column
                    else { matrix_oper_frame_constr.insets = new Insets(5,1,1,1); }
                }
                //First column
                else if(j == 0)
                {
                    //First column and last row
                    if(i == r-1) { matrix_oper_frame_constr.insets = new Insets(1,5,5,1); }
                    //First column and not last row and not first row
                    else { matrix_oper_frame_constr.insets = new Insets(1,5,1,1); }
                }
                //Last row
                else if(i == r-1)
                {
                    //Last row and last column
                    if(j == c-1) { matrix_oper_frame_constr.insets = new Insets(1,1,5,5); }
                    //Last row and not last column and not first column
                    else { matrix_oper_frame_constr.insets = new Insets(1,1,5,1); }
                }
                //Last column
                else if(j == c-1) { matrix_oper_frame_constr.insets = new Insets(1,1,1,5); }
                //Every other cell in the matrix
                else { matrix_oper_frame_constr.insets = new Insets(1,1,1,1); }

                if(j>2 && j<c-1) { matrix_oper_frame_constr.ipadx = 19; }
                else { matrix_oper_frame_constr.ipadx = 0; }

                //Set the bg color, font, and size of the user matrix input. Then put it on Matrix Operation frame
                matrix_oper_frame_constr.gridx = j; 
                matrix_oper_frame_constr.gridy = i;
                matrix_oper_frame_user_field_arr[i][j] = new JTextField(); 
                matrix_oper_frame_user_field_arr[i][j].setBackground(color_arr[13]); 
                matrix_oper_frame_user_field_arr[i][j].setFont(font_arr[2]); 
                matrix_oper_frame_user_field_arr[i][j].setPreferredSize(dim);
                matrix_oper_frame.add(matrix_oper_frame_user_field_arr[i][j], matrix_oper_frame_constr);

                //Select all input in the matrix input field when user clicks on the field
                //JTextField temp = matrix_oper_frame_user_field_arr[i][j];
                matrix_oper_frame_user_field_arr[i][j].addFocusListener(new FocusListener()
                { 
                    @Override public void focusGained(FocusEvent e) { ((JTextField)e.getSource()).selectAll(); }
                    @Override public void focusLost(FocusEvent e) {/*Do nothing when the focus is lost */}
                });
            }
        }//for

        //Put the buttons and error bar on the Matrix Operation frame
        matrix_oper_frame_constr.gridx = 0; 
        matrix_oper_frame_constr.gridy = r; 
        matrix_oper_frame_constr.insets = new Insets(5,5,5,5); 
        matrix_oper_frame.add(matrix_oper_frame_det_btn, matrix_oper_frame_constr);
        matrix_oper_frame_constr.gridx = 1; 
        matrix_oper_frame.add(matrix_oper_frame_rref_btn, matrix_oper_frame_constr);
        matrix_oper_frame_constr.gridx = 2; 
        matrix_oper_frame.add(matrix_oper_frame_inverse_btn, matrix_oper_frame_constr);
        matrix_oper_frame_constr.gridy = r+1; 
        matrix_oper_frame_constr.gridx = 0; 
        matrix_oper_frame_constr.gridwidth = (c>3)?(c):(3); 
        matrix_oper_frame.add(matrix_oper_frame_err_bar, matrix_oper_frame_constr);
        matrix_oper_frame_constr.gridy = r+2; 
        matrix_oper_frame_constr.gridwidth = 1; 
        matrix_oper_frame.add(matrix_oper_frame_exit_btn, matrix_oper_frame_constr);
        matrix_oper_frame_constr.gridx = (c>3) ? (c-1) : (2); 
        matrix_oper_frame.add(matrix_oper_frame_clear_btn, matrix_oper_frame_constr);

        //Create output field for the matrix input
        matrix_oper_frame_out_field_arr = new JTextField[r][c];

        for(int i = 0; i < r; ++i)
        {
            for(int j = 0; j < c; ++j)
            {
                //First row
                if(i == 0) 
                { 
                    //First row and first column
                    if(j == 0) { matrix_oper_frame_constr.insets = new Insets(5,5,1,1); }
                    //First row and last column
                    else if (j == c-1) { matrix_oper_frame_constr.insets = new Insets(5,1,1,5); }
                    //First row and not first column and not last column
                    else { matrix_oper_frame_constr.insets = new Insets(5,1,1,1); }
                }
                //First column
                else if(j == 0)
                {
                    //First column and last row
                    if(i == r-1) { matrix_oper_frame_constr.insets = new Insets(1,5,5,1); }
                    //First column and not last row and not first row
                    else { matrix_oper_frame_constr.insets = new Insets(1,5,1,1); }
                }
                //Last row
                else if(i == r-1)
                {
                    //Last row and last column
                    if(j == c-1) { matrix_oper_frame_constr.insets = new Insets(1,1,5,5); }
                    //Last row and not last column and not first column
                    else { matrix_oper_frame_constr.insets = new Insets(1,1,5,1); }
                }
                //Last column
                else if(j == c-1) { matrix_oper_frame_constr.insets = new Insets(1,1,1,5); }
                //Every other cell in the matrix
                else { matrix_oper_frame_constr.insets = new Insets(1,1,1,1); }

                //Put the output field on the Matrix Operation frame
                matrix_oper_frame_constr.gridx = j; 
                matrix_oper_frame_constr.gridy = r+i+3;
                matrix_oper_frame_out_field_arr[i][j] = new JTextField(); 
                matrix_oper_frame_out_field_arr[i][j].setEditable(false); 
                matrix_oper_frame_out_field_arr[i][j].setBackground(color_arr[5]); 
                matrix_oper_frame_out_field_arr[i][j].setFont(font_arr[3]); 
                matrix_oper_frame_out_field_arr[i][j].setHorizontalAlignment(default_font_alignment); 
                matrix_oper_frame_out_field_arr[i][j].setPreferredSize(dim);
                matrix_oper_frame.add(matrix_oper_frame_out_field_arr[i][j], matrix_oper_frame_constr);
            }
        }//for

        //If the row and column are of different sizes, then disable the inverse and determinant button
        if(r != c)
        { 
            matrix_oper_frame_det_btn.setEnabled(false); 
            matrix_oper_frame_inverse_btn.setEnabled(false); 
        }

        //Calculate the determinant of the input matrix on click
        matrix_oper_frame_det_btn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                matrix_oper_frame_err_bar.setText("");
                clear_matrix_oper_frame_user_output_field();

                try { matrix_oper_frame_err_bar.setText(df.format((new Matrix(get_matrix_oper_frame_user_input_matrix())).Det__InPlace())); }
                //Set the error bar if there is any error
                catch(Exception ex){ matrix_oper_frame_err_bar.setText("ERROR: " + ex.getMessage()); }
            }
        });//actionListener()

        //Calculates reduced row echolan form of the input matrix on click
        matrix_oper_frame_rref_btn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                matrix_oper_frame_err_bar.setText("");
                clear_matrix_oper_frame_user_output_field(); 

                try
                {                    
                    //Calculate the RREF of the input matrix
                    Matrix new_rref_matrix = new Matrix(get_matrix_oper_frame_user_input_matrix());
                    new_rref_matrix.RREF__InPlace();
                    
                    for(int i = 0; i < matrix_oper_frame_out_field_arr.length; ++i)
                    {
                        for(int j = 0; j < matrix_oper_frame_out_field_arr[0].length; ++j) 
                        {
                            //If the output of the RREF matrix is 0, then set the output cell to zero
                            matrix_oper_frame_out_field_arr[i][j].setText(df.format(new_rref_matrix.GetExactMatrix()[i][j]));
                        }
                    }
                    matrix_oper_frame_err_bar.setText("");
                }
                //If there is any error, then set the error bar and blank out the output RREF matrix
                catch(Exception ex)
                {
                    matrix_oper_frame_err_bar.setText("ERROR: " + ex.getMessage()); 
                    clear_matrix_oper_frame_user_output_field();
                }
            }
        });//actionListener()

        //Calculates the inverse of the input matrix on click
        matrix_oper_frame_inverse_btn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                matrix_oper_frame_err_bar.setText("");
                clear_matrix_oper_frame_user_output_field();

                try
                {
                    //Calculate the inverse of the input matrix
                    Matrix inverse_matrix = new Matrix(get_matrix_oper_frame_user_input_matrix());
                    inverse_matrix.Inverse__InPlace();
                    
                    for(int i = 0; i < matrix_oper_frame_out_field_arr.length; ++i)
                    {
                        for(int j = 0; j < matrix_oper_frame_out_field_arr[0].length; ++j) 
                        {
                            //If the output of the RREF matrix is 0, then set the output cell to zero
                            matrix_oper_frame_out_field_arr[i][j].setText(df.format(inverse_matrix.GetExactMatrix()[i][j] == 0 ? 0 : inverse_matrix.GetExactMatrix()[i][j])); 
                        }
                    }
                    matrix_oper_frame_err_bar.setText("");                               
                }
                //If there is any error, then set the error bar and blank out the output RREF matrix
                catch(Exception ex)
                {
                    matrix_oper_frame_err_bar.setText("ERROR: " + ex.getMessage()); 
                    clear_matrix_oper_frame_user_output_field();
                }
            }
        });//actionListener()

        //Clears the input and output field when clicked
        matrix_oper_frame_clear_btn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                matrix_oper_frame_err_bar.setText("");
                for(int i = 0; i < matrix_oper_frame_user_field_arr.length; ++i) 
                { 
                    for(int j = 0; j < matrix_oper_frame_user_field_arr[0].length; ++j) 
                    { 
                        matrix_oper_frame_user_field_arr[i][j].setText(""); 
                        matrix_oper_frame_out_field_arr[i][j].setText(""); 
                    } 
                } 
            }
        });//actionListener()

        //Dispose the Matrix Operation frame when clicked
        matrix_oper_frame_exit_btn.addActionListener(new ActionListener() { @Override public void actionPerformed(ActionEvent e) { dispose_matrix_oper_frame(); } });

        //Deploy the frame to the user
        matrix_oper_frame.pack();
        if(matrix_oper_frame.getBounds().width < 279) { matrix_oper_frame.setBounds(0,0, 279, matrix_oper_frame.getBounds().height); }
        matrix_oper_frame.setLocationRelativeTo(null);
        matrix_oper_frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        matrix_oper_frame.addWindowListener(new WindowListener()
        {
            @Override public void windowClosing(WindowEvent e) { dispose_matrix_oper_frame(); }
            @Override public void windowOpened(WindowEvent e) {}
            @Override public void windowClosed(WindowEvent e) {}
            @Override public void windowIconified(WindowEvent e) {}
            @Override public void windowDeiconified(WindowEvent e) {}
            @Override public void windowActivated(WindowEvent e) {}
            @Override public void windowDeactivated(WindowEvent e) {}
        });
        matrix_oper_frame.setResizable(false);
        matrix_oper_frame.setVisible(true);
    }

    /**
     * Refresh the matrix operation frame while keeping the user changes intact
     */
    protected void refresh_matrix_oper_frame()
    {
        if(matrix_oper_frame != null)
        {
            String[][] matrix_oper_frame_user_field_arr_text_arr = new String[matrix_oper_frame_user_field_arr.length][matrix_oper_frame_user_field_arr[0].length];
            String[][] matrix_oper_frame_out_field_arr_text_arr = new String[matrix_oper_frame_user_field_arr.length][matrix_oper_frame_user_field_arr[0].length];
            String matrix_oper_frame_err_bar_text = matrix_oper_frame_err_bar.getText();

            for(int i = 0; i < matrix_oper_frame_user_field_arr_text_arr.length; ++i)
            {
                for(int j = 0; j < matrix_oper_frame_user_field_arr_text_arr[0].length; ++j)
                {
                    matrix_oper_frame_user_field_arr_text_arr[i][j] = matrix_oper_frame_user_field_arr[i][j].getText();
                    matrix_oper_frame_out_field_arr_text_arr[i][j] = matrix_oper_frame_out_field_arr[i][j].getText();
                }
            }

            try { MatrixOper(matrix_oper_frame_user_field_arr_text_arr.length, matrix_oper_frame_user_field_arr_text_arr[0].length); } catch (Exception ex) {/*If the number of rows or cols is entered wrong*/}

            matrix_oper_frame_err_bar.setText(matrix_oper_frame_err_bar_text);

            for(int i = 0; i < matrix_oper_frame_user_field_arr_text_arr.length; ++i)
            {
                for(int j = 0; j < matrix_oper_frame_user_field_arr_text_arr[0].length; ++j)
                {
                    matrix_oper_frame_user_field_arr[i][j].setText(matrix_oper_frame_user_field_arr_text_arr[i][j]);
                    matrix_oper_frame_out_field_arr[i][j].setText(matrix_oper_frame_out_field_arr_text_arr[i][j]);
                }
            }
        }
    }

    /**
     * Set the bg color of the buttons on the matrix operation panel
     */
    protected void set_matrix_oper_frame_btn_color()
    {
        if(matrix_oper_frame != null)
        {
            set_matrix_oper_frame_btn_bg_color();
            set_matrix_oper_frame_btn_hover_color();
            set_matrix_oper_frame_btn_press_color();
            set_matrix_oper_frame_btn_border_color();
            set_matrix_oper_frame_btn_font_color();
            set_matrix_oper_frame_btn_disabled_color();
            set_matrix_oper_frame_btn_layout();
        }
    }

    /**
     * Set the matrix operation frame button corner cut-out
     */
    protected void set_matrix_oper_frame_btn_layout()
    {
        if(matrix_oper_frame != null) 
        {
            set_matrix_oper_frame_btn_arcHeight_layout();
            set_matrix_oper_frame_btn_arcWidth_layout();
        }
    }

    /**
     * Set the matrix operation frame button corner arc height
     */
    protected void set_matrix_oper_frame_btn_arcHeight_layout()
    {
        if(matrix_oper_frame != null) 
        {
            matrix_oper_frame_clear_btn.setArcHeight(btn_arc_height);
            matrix_oper_frame_rref_btn.setArcHeight(matrix_oper_frame_clear_btn.getArcHeight());
            matrix_oper_frame_det_btn.setArcHeight(matrix_oper_frame_clear_btn.getArcHeight());
            matrix_oper_frame_inverse_btn.setArcHeight(matrix_oper_frame_clear_btn.getArcHeight());
            matrix_oper_frame_exit_btn.setArcHeight(matrix_oper_frame_clear_btn.getArcHeight());
        }
    }
    
    /**
     * Set the matrix operation frame button corner arc width
     */
    protected void set_matrix_oper_frame_btn_arcWidth_layout()
    {
        if(matrix_oper_frame != null) 
        {
            matrix_oper_frame_clear_btn.setArcWidth(btn_arc_width);
            matrix_oper_frame_rref_btn.setArcWidth(matrix_oper_frame_clear_btn.getArcWidth());
            matrix_oper_frame_det_btn.setArcWidth(matrix_oper_frame_clear_btn.getArcWidth());
            matrix_oper_frame_inverse_btn.setArcWidth(matrix_oper_frame_clear_btn.getArcWidth());
            matrix_oper_frame_exit_btn.setArcWidth(matrix_oper_frame_clear_btn.getArcWidth());
        }
    }

    /**
     * Sets the button bg color in matrix operation panel
     */
    protected void set_matrix_oper_frame_btn_bg_color()
    {
        if(matrix_oper_frame != null)
        {
            //If the button color is not set to default, then set bg color of the buttons for Matrix Operation frame
            if(color_arr[7].getRGB() != default_button_color_rgb) { matrix_oper_frame_clear_btn.setBackground(color_arr[7]); }
            else { matrix_oper_frame_clear_btn.setBackground(default_color_arr[14]); }

            matrix_oper_frame_rref_btn.setBackground(matrix_oper_frame_clear_btn.getBackground()); 
            matrix_oper_frame_det_btn.setBackground(matrix_oper_frame_clear_btn.getBackground()); 
            matrix_oper_frame_inverse_btn.setBackground(matrix_oper_frame_clear_btn.getBackground()); 
            matrix_oper_frame_exit_btn.setBackground(matrix_oper_frame_clear_btn.getBackground());
        }
    }

    /**
     * Sets the button hover color in matrix operation panel
     */
    protected void set_matrix_oper_frame_btn_hover_color()
    {
        if(matrix_oper_frame != null)
        {
            matrix_oper_frame_clear_btn.setHoverBackgroundColor(color_arr[8]);
            matrix_oper_frame_rref_btn.setHoverBackgroundColor(matrix_oper_frame_clear_btn.getHoverBackgroundColor());
            matrix_oper_frame_det_btn.setHoverBackgroundColor(matrix_oper_frame_clear_btn.getHoverBackgroundColor());
            matrix_oper_frame_inverse_btn.setHoverBackgroundColor(matrix_oper_frame_clear_btn.getHoverBackgroundColor());
            matrix_oper_frame_exit_btn.setHoverBackgroundColor(matrix_oper_frame_clear_btn.getHoverBackgroundColor());
        }
    }

    /**
     * Sets the button press color in matrix operation panel
     */
    protected void set_matrix_oper_frame_btn_press_color()
    {
        if(matrix_oper_frame != null)
        {
            matrix_oper_frame_clear_btn.setPressedBackgroundColor(color_arr[9]);
            matrix_oper_frame_rref_btn.setPressedBackgroundColor(matrix_oper_frame_clear_btn.getPressedBackgroundColor());
            matrix_oper_frame_det_btn.setPressedBackgroundColor(matrix_oper_frame_clear_btn.getPressedBackgroundColor());
            matrix_oper_frame_inverse_btn.setPressedBackgroundColor(matrix_oper_frame_clear_btn.getPressedBackgroundColor());
            matrix_oper_frame_exit_btn.setPressedBackgroundColor(matrix_oper_frame_clear_btn.getPressedBackgroundColor());
        }
    }

    /**
     * Sets the button border color in matrix operation panel
     */
    protected void set_matrix_oper_frame_btn_border_color()
    {
        if(matrix_oper_frame != null)
        {
            matrix_oper_frame_clear_btn.setBorderColor(color_arr[10]);
            matrix_oper_frame_rref_btn.setBorderColor(matrix_oper_frame_clear_btn.getBorderColor());
            matrix_oper_frame_det_btn.setBorderColor(matrix_oper_frame_clear_btn.getBorderColor());
            matrix_oper_frame_inverse_btn.setBorderColor(matrix_oper_frame_clear_btn.getBorderColor());
            matrix_oper_frame_exit_btn.setBorderColor(matrix_oper_frame_clear_btn.getBorderColor());
        }
    }

    /**
     * Repaint the buttons in matrix operation panel
     */
    protected void matrix_oper_frame_btn_repaint()
    {
        if(matrix_oper_frame != null)
        {
            matrix_oper_frame_clear_btn.repaint();
            matrix_oper_frame_rref_btn.repaint();
            matrix_oper_frame_det_btn.repaint();
            matrix_oper_frame_inverse_btn.repaint();
            matrix_oper_frame_exit_btn.repaint();
        }
    }

    /**
     * Sets the button font color in matrix operation panel
     */
    protected void set_matrix_oper_frame_btn_font_color()
    {
        if(matrix_oper_frame != null)
        {
            matrix_oper_frame_clear_btn.setFontColor(color_arr[11]);
            matrix_oper_frame_rref_btn.setFontColor(matrix_oper_frame_clear_btn.getFontColor());
            matrix_oper_frame_det_btn.setFontColor(matrix_oper_frame_clear_btn.getFontColor());
            matrix_oper_frame_inverse_btn.setFontColor(matrix_oper_frame_clear_btn.getFontColor());
            matrix_oper_frame_exit_btn.setFontColor(matrix_oper_frame_clear_btn.getFontColor());
        }
    }

    /**
     * Sets the button disabled color in matrix operation panel
     */
    protected void set_matrix_oper_frame_btn_disabled_color()
    {
        if(matrix_oper_frame != null)
        {
            matrix_oper_frame_clear_btn.setDisabledBackgroundColor(color_arr[12]);
            matrix_oper_frame_rref_btn.setDisabledBackgroundColor(matrix_oper_frame_clear_btn.getDisabledBackgroundColor());
            matrix_oper_frame_det_btn.setDisabledBackgroundColor(matrix_oper_frame_clear_btn.getDisabledBackgroundColor());
            matrix_oper_frame_inverse_btn.setDisabledBackgroundColor(matrix_oper_frame_clear_btn.getDisabledBackgroundColor());
            matrix_oper_frame_exit_btn.setDisabledBackgroundColor(matrix_oper_frame_clear_btn.getDisabledBackgroundColor());
        }
    }

    /**
     * Set the button font on the matrix operation panel
     */
    protected void set_matrix_oper_frame_system_font()
    {
        if(matrix_oper_frame != null)
        {
            matrix_oper_frame_clear_btn.setFont(font_arr[0]); 
            matrix_oper_frame_rref_btn.setFont(matrix_oper_frame_clear_btn.getFont()); 
            matrix_oper_frame_det_btn.setFont(matrix_oper_frame_clear_btn.getFont()); 
            matrix_oper_frame_inverse_btn.setFont(matrix_oper_frame_clear_btn.getFont()); 
            matrix_oper_frame_exit_btn.setFont(matrix_oper_frame_clear_btn.getFont());
        }
    }

    /**
     * Clear the output matrix textfields
     */
    protected void clear_matrix_oper_frame_user_output_field()
    {
        if(matrix_oper_frame != null)
        {
            for(int i = 0; i < matrix_oper_frame_out_field_arr.length; ++i) 
            { 
                for(int j = 0; j < matrix_oper_frame_out_field_arr[0].length; ++j) 
                {
                    matrix_oper_frame_out_field_arr[i][j].setText(""); 
                } 
            } 
        }
    }

    /**
     * @return The user input matrix in 2D double array
     */
    protected double[][] get_matrix_oper_frame_user_input_matrix()
    {   
        double[][] temp_matrix = null;

        if(matrix_oper_frame != null)
        {
            temp_matrix = new double[matrix_oper_frame_user_field_arr.length][matrix_oper_frame_user_field_arr[0].length];
            for(int i = 0; i < matrix_oper_frame_user_field_arr.length; ++i) 
            { 
                for(int j = 0; j < matrix_oper_frame_user_field_arr[0].length; ++j) 
                {
                    //Set all the blank inputs to zero
                    temp_matrix[i][j] = Double.parseDouble( (matrix_oper_frame_user_field_arr[i][j].getText().isEmpty()) ? ("0") : (matrix_oper_frame_user_field_arr[i][j].getText()) ); 
                } 
            }
        }

        return temp_matrix;
    }

    /**
     * Try to dispose the matrix operation panel
     */
    protected void dispose_matrix_oper_frame() { if(matrix_oper_frame != null) { matrix_oper_frame.dispose(); matrix_oper_frame = null; } }

    /**
     * Set the font of the error bar on the matrix operation panel
     */
    protected void set_matrix_oper_frame_output_err_bar_font() { if(matrix_oper_frame != null) { matrix_oper_frame_err_bar.setFont(font_arr[3]); } }

    /**
     * Set the error bar bg color on the matrix operation panel
     */
    protected void set_matrix_oper_frame_err_msg_color() { if(matrix_oper_frame != null) { matrix_oper_frame_err_bar.setBackground(color_arr[2]); } }

    /**
     * Set the bg color of the matrix operation panel
     */
    protected void set_matrix_oper_frame_bg_color() { if(matrix_oper_frame != null) { matrix_oper_frame.getContentPane().setBackground(color_arr[4]); } }
    

    /**
     * Simple calculator frame which can perform addition, subtraction, division, multiplication, and modulus
     */
    protected void SimpleCalculator()
    {
        //Dispose the Calculator frame if it exists
        dispose_cal_frame();
        
        //Create new Calculator frame
        cal_frame = new JFrame("Calculator");

        //Instantiate input and output fields
        cal_frame_result_field = new JTextField(10);
        cal_frame_num_1_field = new JTextField(10);
        cal_frame_num_2_field = new JTextField(10);

        //Create basic arithmetic buttons
        cal_frame_add_btn = new CustomJButton("Add");
        cal_frame_sub_btn = new CustomJButton("Subtract");
        cal_frame_mul_btn = new CustomJButton("Multiply");
        cal_frame_div_btn = new CustomJButton("Divide");
        cal_frame_mod_btn = new CustomJButton("Modulo");
        cal_frame_exit_btn = new CustomJButton("Exit");

        set_cal_frame_btn_color();

        //Create labels for input and result fields
        cal_frame_field_1_label = new JLabel("Number 1: ");
        cal_frame_field_2_label = new JLabel("Number 2: ");
        cal_frame_result_field_label = new JLabel("Result: ");
        set_cal_frame_label_color();

        cal_frame.setIconImage(main_frame.getIconImage());

        cal_frame_result_field.setText(""); 
        cal_frame_num_1_field.setText(""); 
        cal_frame_num_2_field.setText(""); 

        //Set cal panel system button and label fonts
        //Set BG color and font of the input and output fields
        set_cal_frame_system_font();        
        set_cal_frame_input_text_field_color();
        set_cal_frame_error_msg_color();
        set_cal_frame_input_font();
        set_cal_frame_output_font();
        cal_frame_result_field.setEnabled(false); 

        cal_frame.setLayout(new GridBagLayout());
        set_cal_frame_bg_color();
        
        //Set alignment of the labels and output field
        cal_frame_field_1_label.setHorizontalAlignment(default_font_alignment); 
        cal_frame_field_2_label.setHorizontalAlignment(default_font_alignment);
        cal_frame_result_field_label.setHorizontalAlignment(default_font_alignment);
        cal_frame_result_field.setHorizontalAlignment(default_font_alignment);

        GridBagConstraints cal_frame_constr = new GridBagConstraints();
        cal_frame_constr.fill = GridBagConstraints.HORIZONTAL; 
        
        //Insert the label, input field, and buttons on the Calculator frame
        cal_frame_constr.insets = new Insets(5,5,2,2); 
        cal_frame.add(cal_frame_field_1_label, cal_frame_constr);
        cal_frame_constr.insets = new Insets(5,2,2,2); 
        cal_frame_constr.gridx = 1; 
        cal_frame.add(cal_frame_num_1_field, cal_frame_constr);
        cal_frame_constr.gridx = 2; 
        cal_frame.add(cal_frame_field_2_label, cal_frame_constr); 
        cal_frame_constr.gridx = 3; 
        cal_frame.add(cal_frame_num_2_field, cal_frame_constr);
        cal_frame_constr.gridx = 4; 
        cal_frame.add(cal_frame_result_field_label, cal_frame_constr);
        cal_frame_constr.insets = new Insets(5,2,2,5); 
        cal_frame_constr.gridx = 5; 
        cal_frame.add(cal_frame_result_field, cal_frame_constr);
        cal_frame_constr.insets = new Insets(2,5,5,2); 
        cal_frame_constr.gridy = 1; 
        cal_frame_constr.gridx = 0; 
        cal_frame.add(cal_frame_add_btn, cal_frame_constr); 
        cal_frame_constr.gridx = 1; 
        cal_frame.add(cal_frame_sub_btn, cal_frame_constr); 
        cal_frame_constr.gridx = 2;
        cal_frame.add(cal_frame_mul_btn, cal_frame_constr); 
        cal_frame_constr.gridx = 3; 
        cal_frame.add(cal_frame_div_btn, cal_frame_constr); 
        cal_frame_constr.gridx = 4; 
        cal_frame.add(cal_frame_mod_btn, cal_frame_constr); 
        cal_frame_constr.insets = new Insets(2,2,5,5); 
        cal_frame_constr.gridx = 5; 
        cal_frame.add(cal_frame_exit_btn, cal_frame_constr);

        //Select the entire input when user clicks on the field
        cal_frame_num_1_field.addFocusListener(new FocusListener()
        { 
            @Override public void focusGained(FocusEvent e) { cal_frame_num_1_field.selectAll(); } 
            @Override public void focusLost(FocusEvent e) {/*Do nothing when the focus is lost*/}
        });

        //Select the entire input when user clicks on the field
        cal_frame_num_2_field.addFocusListener(new FocusListener()
        { 
            @Override public void focusGained(FocusEvent e) { cal_frame_num_2_field.selectAll(); } 
            @Override public void focusLost(FocusEvent e) {/*Do nothing when the focus is lost*/}
        });

        //Adds the two inputs when clicked
        cal_frame_add_btn.addActionListener(new ActionListener() 
        { 
            @Override public void actionPerformed(ActionEvent e) 
            { 
                try { cal_frame_result_field.setText(df.format(Double.parseDouble(cal_frame_num_1_field.getText()) + Double.parseDouble(cal_frame_num_2_field.getText()))); } 
                catch(Exception ex) { cal_frame_result_field.setText("ERROR: " + ex.getMessage()); } 
            }; 
        });

        //Subtracts the two inputs when clicked
        cal_frame_sub_btn.addActionListener(new ActionListener() 
        { 
            @Override public void actionPerformed(ActionEvent e) 
            { 
                try { cal_frame_result_field.setText(df.format(Double.parseDouble(cal_frame_num_1_field.getText()) - Double.parseDouble(cal_frame_num_2_field.getText()))); } 
                catch(Exception ex) { cal_frame_result_field.setText("ERROR: " + ex.getMessage()); } 
            }; 
        });
        
        //Multiplies the two inputs when clicked
        cal_frame_mul_btn.addActionListener(new ActionListener() 
        { 
            @Override public void actionPerformed(ActionEvent e) 
            { 
                try { cal_frame_result_field.setText(df.format(Double.parseDouble(cal_frame_num_1_field.getText()) * Double.parseDouble(cal_frame_num_2_field.getText()))); } 
                catch(Exception ex) { cal_frame_result_field.setText("ERROR: " + ex.getMessage()); } 
            }; 
        });
        
        //Divides the two inputs when clicked
        cal_frame_div_btn.addActionListener(new ActionListener() 
        { 
            @Override public void actionPerformed(ActionEvent e) 
            { 
                try { cal_frame_result_field.setText(df.format(Double.parseDouble(cal_frame_num_1_field.getText()) / Double.parseDouble(cal_frame_num_2_field.getText()))); } 
                catch(Exception ex) { cal_frame_result_field.setText("ERROR: " + ex.getMessage()); } 
            }; 
        });
        
        //Modulus the two inputs when clicked
        cal_frame_mod_btn.addActionListener(new ActionListener() 
        { 
            @Override public void actionPerformed(ActionEvent e) 
            { 
                try { cal_frame_result_field.setText(df.format(Double.parseDouble(cal_frame_num_1_field.getText()) % Double.parseDouble(cal_frame_num_2_field.getText()))); } 
                catch(Exception ex) { cal_frame_result_field.setText("ERROR: " + ex.getMessage()); } 
            }; 
        });
        
        //Dispose Calculator frame when clicked
        cal_frame_exit_btn.addActionListener(new ActionListener() { @Override public void actionPerformed(ActionEvent e) { dispose_cal_frame(); } });
        
        //Deploy the Calculator frame to the user
        cal_frame.pack();
        cal_frame.setLocationRelativeTo(null);
        cal_frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        cal_frame.addWindowListener(new WindowListener()
        {
            @Override public void windowClosing(WindowEvent e) { dispose_cal_frame(); }
            @Override public void windowOpened(WindowEvent e) {}
            @Override public void windowClosed(WindowEvent e) {}
            @Override public void windowIconified(WindowEvent e) {}
            @Override public void windowDeiconified(WindowEvent e) {}
            @Override public void windowActivated(WindowEvent e) {}
            @Override public void windowDeactivated(WindowEvent e) {}
        });
        cal_frame.setResizable(false);
        cal_frame.setVisible(true);
    }//MyCalculator()

    /**
     * Refresh all the simple calculator frame components while preserving data
     */
    protected void refresh_cal_frame()
    {
        if(cal_frame != null)
        {
            String cal_frame_num_1_field_text = cal_frame_num_1_field.getText();
            String cal_frame_num_2_field_text = cal_frame_num_2_field.getText();
            String cal_frame_result_field_text = cal_frame_result_field.getText();
            SimpleCalculator();
            cal_frame_num_1_field.setText(cal_frame_num_1_field_text);
            cal_frame_num_2_field.setText(cal_frame_num_2_field_text);
            cal_frame_result_field.setText(cal_frame_result_field_text);
        }
    }

    /**
     * Sets the label font color in simple calculator frame
     */
    protected void set_cal_frame_label_color()
    {
        if(cal_frame != null)
        {
            try { cal_frame_field_1_label.setForeground(cal_frame_exit_btn.getInvertedColor(color_arr[6], 100, 155, false)); } catch(Exception ex) { return; }
            cal_frame_field_2_label.setForeground(cal_frame_field_1_label.getForeground());
            cal_frame_result_field_label.setForeground(cal_frame_field_1_label.getForeground());
        }
    }

    /**
     * Set the input text field font on the simple calculator menu
     */
    protected void set_cal_frame_input_font()
    {
        if(cal_frame != null)
        {
            cal_frame_num_1_field.setFont(font_arr[2]);
            cal_frame_num_2_field.setFont(cal_frame_num_1_field.getFont());
        }
    }

    /**
     * Set the font of the button and labels on the simple calculator menu
     */
    protected void set_cal_frame_system_font()
    {
        if(cal_frame != null)
        {
            cal_frame_add_btn.setFont(font_arr[0]); 
            cal_frame_sub_btn.setFont(cal_frame_add_btn.getFont()); 
            cal_frame_mul_btn.setFont(cal_frame_add_btn.getFont()); 
            cal_frame_div_btn.setFont(cal_frame_add_btn.getFont()); 
            cal_frame_mod_btn.setFont(cal_frame_add_btn.getFont()); 
            cal_frame_exit_btn.setFont(cal_frame_add_btn.getFont());
            cal_frame_field_1_label.setFont(cal_frame_add_btn.getFont()); 
            cal_frame_field_2_label.setFont(cal_frame_add_btn.getFont()); 
            cal_frame_result_field_label.setFont(cal_frame_add_btn.getFont());
        } 
    }

    /**
     * Set the bg color of the buttons on the simple calculator menu
     */
    protected void set_cal_frame_btn_color()
    {
        if(cal_frame != null)
        {
            set_cal_frame_btn_bg_color();
            set_cal_frame_btn_hover_color();
            set_cal_frame_btn_press_color();
            set_cal_frame_btn_border_color();
            set_cal_frame_btn_font_color();
            set_cal_frame_btn_disabled_color();
            set_cal_frame_btn_layout();
        }
    }

    /**
     * Set the cal frame button corner cut-out
     */
    protected void set_cal_frame_btn_layout()
    {
        if(cal_frame != null) 
        {
            set_cal_frame_btn_arcHeight_layout();
            set_cal_frame_btn_arcWidth_layout();
        }
    }

    /**
     * Set the cal frame button corner arc height
     */
    protected void set_cal_frame_btn_arcHeight_layout()
    {
        if(cal_frame != null) 
        {
            cal_frame_add_btn.setArcHeight(btn_arc_height);
            cal_frame_sub_btn.setArcHeight(cal_frame_add_btn.getArcHeight());
            cal_frame_mul_btn.setArcHeight(cal_frame_add_btn.getArcHeight());
            cal_frame_div_btn.setArcHeight(cal_frame_add_btn.getArcHeight());
            cal_frame_mod_btn.setArcHeight(cal_frame_add_btn.getArcHeight());
            cal_frame_exit_btn.setArcHeight(cal_frame_add_btn.getArcHeight());
        }
    }
    
    /**
     * Set the cal frame button corner arc width
     */
    protected void set_cal_frame_btn_arcWidth_layout()
    {
        if(cal_frame != null) 
        {
            cal_frame_add_btn.setArcWidth(btn_arc_width);
            cal_frame_sub_btn.setArcWidth(cal_frame_add_btn.getArcWidth());
            cal_frame_mul_btn.setArcWidth(cal_frame_add_btn.getArcWidth());
            cal_frame_div_btn.setArcWidth(cal_frame_add_btn.getArcWidth());
            cal_frame_mod_btn.setArcWidth(cal_frame_add_btn.getArcWidth());
            cal_frame_exit_btn.setArcWidth(cal_frame_add_btn.getArcWidth());
        }
    }

    /**
     * Sets the button bg color in simple calulator menu
     */
    protected void set_cal_frame_btn_bg_color() 
    {
        if(cal_frame != null)
        {
            //If the button color is not set to default, then change the bg color of the button
            if(color_arr[7].getRGB() != default_button_color_rgb) { cal_frame_add_btn.setBackground(color_arr[7]); }
            else { cal_frame_add_btn.setBackground(default_color_arr[14]); }

            cal_frame_sub_btn.setBackground(cal_frame_add_btn.getBackground()); 
            cal_frame_mul_btn.setBackground(cal_frame_add_btn.getBackground()); 
            cal_frame_div_btn.setBackground(cal_frame_add_btn.getBackground()); 
            cal_frame_mod_btn.setBackground(cal_frame_add_btn.getBackground()); 
            cal_frame_exit_btn.setBackground(cal_frame_add_btn.getBackground()); 
        }
    }

    /**
     * Sets the button hover color in simple calulator menu
     */
    protected void set_cal_frame_btn_hover_color() 
    {
        if(cal_frame != null)
        {
            cal_frame_add_btn.setHoverBackgroundColor(color_arr[8]);
            cal_frame_sub_btn.setHoverBackgroundColor(cal_frame_add_btn.getHoverBackgroundColor());
            cal_frame_mul_btn.setHoverBackgroundColor(cal_frame_add_btn.getHoverBackgroundColor());
            cal_frame_div_btn.setHoverBackgroundColor(cal_frame_add_btn.getHoverBackgroundColor());
            cal_frame_mod_btn.setHoverBackgroundColor(cal_frame_add_btn.getHoverBackgroundColor());
            cal_frame_exit_btn.setHoverBackgroundColor(cal_frame_add_btn.getHoverBackgroundColor());
        }
    }

    /**
     * Sets the button press color in simple calulator menu
     */
    protected void set_cal_frame_btn_press_color() 
    {
        if(cal_frame != null)
        {
            cal_frame_add_btn.setPressedBackgroundColor(color_arr[9]);
            cal_frame_sub_btn.setPressedBackgroundColor(cal_frame_add_btn.getPressedBackgroundColor());
            cal_frame_mul_btn.setPressedBackgroundColor(cal_frame_add_btn.getPressedBackgroundColor());
            cal_frame_div_btn.setPressedBackgroundColor(cal_frame_add_btn.getPressedBackgroundColor());
            cal_frame_mod_btn.setPressedBackgroundColor(cal_frame_add_btn.getPressedBackgroundColor());
            cal_frame_exit_btn.setPressedBackgroundColor(cal_frame_add_btn.getPressedBackgroundColor());
        }
    }

    /**
     * Sets the button border color in simple calulator menu
     */
    protected void set_cal_frame_btn_border_color() 
    {
        if(cal_frame != null)
        {
            cal_frame_add_btn.setBorderColor(color_arr[10]);
            cal_frame_sub_btn.setBorderColor(cal_frame_add_btn.getBorderColor());
            cal_frame_mul_btn.setBorderColor(cal_frame_add_btn.getBorderColor());
            cal_frame_div_btn.setBorderColor(cal_frame_add_btn.getBorderColor());
            cal_frame_mod_btn.setBorderColor(cal_frame_add_btn.getBorderColor());
            cal_frame_exit_btn.setBorderColor(cal_frame_add_btn.getBorderColor());
        }
    }

    /**
     * Repaints the buttons in simple calculator menu
     */
    protected void cal_frame_btn_repaint()
    {
        if(cal_frame != null)
        {
            cal_frame_add_btn.repaint();
            cal_frame_sub_btn.repaint();
            cal_frame_mul_btn.repaint();
            cal_frame_div_btn.repaint();
            cal_frame_mod_btn.repaint();
            cal_frame_exit_btn.repaint();
        }
    }

    /**
     * Sets the button font color in simple calulator menu
     */
    protected void set_cal_frame_btn_font_color() 
    {
        if(cal_frame != null)
        {
            cal_frame_add_btn.setFontColor(color_arr[11]);
            cal_frame_sub_btn.setFontColor(cal_frame_add_btn.getFontColor());
            cal_frame_mul_btn.setFontColor(cal_frame_add_btn.getFontColor());
            cal_frame_div_btn.setFontColor(cal_frame_add_btn.getFontColor());
            cal_frame_mod_btn.setFontColor(cal_frame_add_btn.getFontColor());
            cal_frame_exit_btn.setFontColor(cal_frame_add_btn.getFontColor());
        }
    }

    /**
     * Sets the button disabled color in simple calulator menu
     */
    protected void set_cal_frame_btn_disabled_color()
    {
        if(cal_frame != null)
        {
            cal_frame_add_btn.setDisabledBackgroundColor(color_arr[12]);
            cal_frame_sub_btn.setDisabledBackgroundColor(cal_frame_add_btn.getDisabledBackgroundColor());
            cal_frame_mul_btn.setDisabledBackgroundColor(cal_frame_add_btn.getDisabledBackgroundColor());
            cal_frame_div_btn.setDisabledBackgroundColor(cal_frame_add_btn.getDisabledBackgroundColor());
            cal_frame_mod_btn.setDisabledBackgroundColor(cal_frame_add_btn.getDisabledBackgroundColor());
            cal_frame_exit_btn.setDisabledBackgroundColor(cal_frame_add_btn.getDisabledBackgroundColor());
        }
    }

    /**
     * Set the bg color of the input text field on the simple calculator menu
     */
    protected void set_cal_frame_input_text_field_color()
    {
        if(cal_frame != null)
        {
            cal_frame_num_1_field.setBackground(color_arr[13]);
            cal_frame_num_2_field.setBackground(cal_frame_num_1_field.getBackground());
        }
    }

    /**
     * Set the font of the result text field on the simple calculator menu
     */
    protected void set_cal_frame_output_font() { if(cal_frame != null) { cal_frame_result_field.setFont(font_arr[3]); } }

    /**
     * Try to dispose the simple calculator menu
     */
    protected void dispose_cal_frame() { if(cal_frame != null) { cal_frame.dispose(); cal_frame = null; } }

    /**
     * Set the bg color of the error bar on the simple calculator menu
     */
    protected void set_cal_frame_error_msg_color() { if(cal_frame != null) { cal_frame_result_field.setBackground(color_arr[2]); } }

    /**
     * Set the bg color of the simple calculator menu
     */
    protected void set_cal_frame_bg_color() { if(cal_frame != null) { cal_frame.getContentPane().setBackground(color_arr[6]); } }
    
    
    /**
     * Credits for the creator: Pratik Patel
     */
    protected void Credits()
    {
        JOptionPane.showMessageDialog(null, "Creator: PRATIK M PATEL\nDATE: 4/23/2020", "Credits", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(main_frame.getIconImage().getScaledInstance(100, 100, 0)) );
    }//MyCredits()    


}//class
